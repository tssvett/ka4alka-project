package dev.tssvett.dto;

import dev.tssvett.enums.IntensityLevel;
import dev.tssvett.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record WorkoutUpdateDto(
        @NotNull String title,
        @Nullable String description,
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        @NotNull Status status,
        @NotNull String location,
        @NotNull IntensityLevel intensityLevel
) {
}

