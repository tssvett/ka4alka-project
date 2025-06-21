package dev.tssvett.service;

import dev.tssvett.db.repository.WorkoutRepository;
import dev.tssvett.dto.WorkoutCreateDto;
import dev.tssvett.dto.WorkoutDto;
import dev.tssvett.dto.WorkoutUpdateDto;
import dev.tssvett.enums.Status;
import dev.tssvett.mapper.WorkoutMapper;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import jooq.events.tables.records.WorkoutRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository repository;
    private final WorkoutMapper mapper;

    public WorkoutDto create(WorkoutCreateDto dto) {
        WorkoutRecord workoutRecord = repository.create(dto);
        WorkoutDto result = mapper.toDto(workoutRecord);
        log.debug("Создана тренировка: {}", result);

        return result;
    }


    public WorkoutDto update(UUID id, WorkoutUpdateDto dto) {
        WorkoutRecord workoutRecord = repository.update(id, dto)
                .orElseThrow(() -> new NoSuchElementException("Тренировка с идентификатором " + id + " не найдена"));
        WorkoutDto result = mapper.toDto(workoutRecord);
        log.debug("Обновлена тренировка: {}", result);

        return result;
    }

    public WorkoutDto findById(UUID id) {
        WorkoutRecord workoutRecord = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Тренировка с идентификатором " + id + " не найдена"));
        WorkoutDto result = mapper.toDto(workoutRecord);
        log.debug("Найдена тренировка: {}", result);

        return result;
    }

    public List<WorkoutDto> findAllUpcoming() {
        List<WorkoutRecord> records = repository.findAllUpcoming();
        List<WorkoutDto> result = records.stream()
                .map(mapper::toDto)
                .toList();
        log.debug("Найдены предстоящие тренировки: {}", result);

        return result;
    }

    public List<WorkoutDto> findByStatus(Status status) {
        List<WorkoutRecord> records = repository.findByStatus(status);
        List<WorkoutDto> result = records.stream()
                .map(mapper::toDto)
                .toList();
        log.debug("Найдены тренировки со статусом {}: {}", status, result);

        return result;
    }

    public WorkoutDto updateStatus(UUID id, Status newStatus) {
        WorkoutRecord workoutRecord = repository.updateStatus(id, newStatus)
                .orElseThrow(() -> new NoSuchElementException("Тренировка с идентификатором " + id + " не найдена"));
        WorkoutDto result = mapper.toDto(workoutRecord);
        log.debug("Обновлен статус тренировки: {}", result);

        return result;
    }

    public WorkoutDto delete(UUID id) {
        WorkoutRecord workoutRecord = repository.delete(id)
                .orElseThrow(() -> new NoSuchElementException("Тренировка с идентификатором " + id + " не найдена"));
        WorkoutDto result = mapper.toDto(workoutRecord);
        log.debug("Удалена тренировка: {}", result);

        return result;
    }
}
