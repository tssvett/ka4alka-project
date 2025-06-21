package dev.tssvett.controller;

import dev.tssvett.dto.WorkoutCreateDto;
import dev.tssvett.dto.WorkoutDto;
import dev.tssvett.dto.WorkoutUpdateDto;
import dev.tssvett.enums.Status;
import dev.tssvett.service.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@Tag(name = WorkoutController.WORKOUT_CONTROLLER, description = "API для управления тренировочными событиями")
@RequestMapping(WorkoutController.API_WORKOUT)
@RequiredArgsConstructor
public class WorkoutController {
    static final String WORKOUT_CONTROLLER = "workout-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    static final String API_WORKOUT = API_PREFIX + "/workouts";

    private final WorkoutService workoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать новую тренировку",
            tags = {WORKOUT_CONTROLLER}
    )
    public WorkoutDto createWorkout(@Valid @RequestBody WorkoutCreateDto dto) {
        return workoutService.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновить тренировку",
            tags = {WORKOUT_CONTROLLER}
    )
    public WorkoutDto updateWorkout(
            @PathVariable UUID id,
            @Valid @RequestBody WorkoutUpdateDto dto) {
        return workoutService.update(id, dto);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить тренировку по ID",
            tags = {WORKOUT_CONTROLLER}
    )
    public WorkoutDto getWorkoutById(@PathVariable UUID id) {
        return workoutService.findById(id);
    }

    @GetMapping("/upcoming")
    @Operation(
            summary = "Получить предстоящие тренировки",
            tags = {WORKOUT_CONTROLLER}
    )
    public List<WorkoutDto> getUpcomingWorkouts() {
        return workoutService.findAllUpcoming();
    }

    @GetMapping
    @Operation(
            summary = "Получить тренировки по статусу",
            tags = {WORKOUT_CONTROLLER}
    )
    public List<WorkoutDto> getWorkoutsByStatus(@RequestParam Status status) {
        return workoutService.findByStatus(status);
    }

    @PatchMapping("/{id}/status")
    @Operation(
            summary = "Обновить статус тренировки",
            tags = {WORKOUT_CONTROLLER}
    )
    public WorkoutDto updateWorkoutStatus(
            @PathVariable UUID id,
            @RequestParam Status newStatus) {
        return workoutService.updateStatus(id, newStatus);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Удалить тренировку",
            tags = {WORKOUT_CONTROLLER}
    )
    public void deleteWorkout(@PathVariable UUID id) {
        log.info("Удаление тренировки с ID: {}", id);
        workoutService.delete(id);
    }
}
