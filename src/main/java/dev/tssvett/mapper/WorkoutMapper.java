package dev.tssvett.mapper;

import dev.tssvett.dto.WorkoutDto;
import jooq.events.tables.records.WorkoutRecord;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface WorkoutMapper {

    WorkoutDto toDto(WorkoutRecord record);
}
