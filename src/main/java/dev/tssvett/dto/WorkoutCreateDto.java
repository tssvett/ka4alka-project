package dev.tssvett.dto;

import dev.tssvett.enums.IntensityLevel;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record WorkoutCreateDto(
        @NotNull String title,
        @Nullable String description,
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        @NotNull String location,
        @NotNull IntensityLevel intensityLevel
) {
}