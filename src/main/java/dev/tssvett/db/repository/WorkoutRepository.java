package dev.tssvett.db.repository;

import dev.tssvett.dto.WorkoutCreateDto;
import dev.tssvett.dto.WorkoutUpdateDto;
import dev.tssvett.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jooq.events.tables.Workout;
import jooq.events.tables.records.WorkoutRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkoutRepository {
    private final DSLContext dslContext;
    private final Workout workoutTable = Workout.WORKOUT;

    /**
     * Создает новую тренировку в базе данных.
     *
     * @param dto DTO с данными для создания
     * @return созданная тренировка в виде DTO
     */
    public WorkoutRecord create(WorkoutCreateDto dto) {
        return dslContext.insertInto(workoutTable)
                .set(workoutTable.ID, UUID.randomUUID())
                .set(workoutTable.TITLE, dto.title())
                .set(workoutTable.DESCRIPTION, dto.description())
                .set(workoutTable.START_TIME, dto.startTime())
                .set(workoutTable.END_TIME, dto.endTime())
                .set(workoutTable.LOCATION, dto.location())
                .set(workoutTable.INTENSITY_LEVEL, dto.intensityLevel().name())
                .returning()
                .fetchOne();
    }

    /**
     * Обновляет существующую тренировку.
     *
     * @param id  UUID тренировки для обновления
     * @param dto DTO с обновленными данными
     * @return обновленная тренировка (Optional)
     */
    public Optional<WorkoutRecord> update(UUID id, WorkoutUpdateDto dto) {
        return dslContext.update(workoutTable)
                .set(workoutTable.TITLE, dto.title())
                .set(workoutTable.DESCRIPTION, dto.description())
                .set(workoutTable.START_TIME, dto.startTime())
                .set(workoutTable.END_TIME, dto.endTime())
                .set(workoutTable.LOCATION, dto.location())
                .set(workoutTable.INTENSITY_LEVEL, dto.intensityLevel().name())
                .set(workoutTable.STATUS, dto.status().name())
                .set(workoutTable.UPDATED_AT, LocalDateTime.now())
                .where(workoutTable.ID.eq(id))
                .returning()
                .fetchOptional();
    }

    /**
     * Находит тренировку по идентификатору.
     *
     * @param id UUID тренировки
     * @return найденная тренировка (Optional)
     */
    public Optional<WorkoutRecord> findById(UUID id) {
        return dslContext.selectFrom(workoutTable)
                .where(workoutTable.ID.eq(id))
                .fetchOptional();
    }

    /**
     * Возвращает список предстоящих тренировок.
     * Фильтрует по дате (старт в будущем) и статусу PLANNED.
     *
     * @return список предстоящих тренировок
     */
    public List<WorkoutRecord> findAllUpcoming() {
        return dslContext.selectFrom(workoutTable)
                .where(workoutTable.START_TIME.greaterThan(LocalDateTime.now()))
                .and(workoutTable.STATUS.eq(Status.PLANNED.name()))
                .orderBy(workoutTable.START_TIME.asc())
                .fetch();
    }

    /**
     * Удаляет тренировку по идентификатору.
     *
     * @param id UUID тренировки для удаления
     * @return true если удаление успешно
     */
    public Optional<WorkoutRecord> delete(UUID id) {
        return dslContext.deleteFrom(workoutTable)
                .where(workoutTable.ID.eq(id))
                .returning()
                .fetchOptional();
    }

    /**
     * Обновляет статус тренировки.
     *
     * @param id        UUID тренировки
     * @param newStatus новый статус
     * @return true если обновление успешно
     */
    public Optional<WorkoutRecord> updateStatus(UUID id, Status newStatus) {
        return dslContext.update(workoutTable)
                .set(workoutTable.STATUS, newStatus.name())
                .set(workoutTable.UPDATED_AT, LocalDateTime.now())
                .where(workoutTable.ID.eq(id))
                .returning()
                .fetchOptional();
    }

    /**
     * Находит тренировки по статусу.
     *
     * @param status статус для фильтрации
     * @return список тренировок с указанным статусом
     */
    public List<WorkoutRecord> findByStatus(Status status) {
        return dslContext.selectFrom(workoutTable)
                .where(workoutTable.STATUS.eq(status.name()))
                .orderBy(workoutTable.START_TIME.asc())
                .fetch();
    }
}