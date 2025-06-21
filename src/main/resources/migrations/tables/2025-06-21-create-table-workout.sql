CREATE TABLE events.workout (
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PLANNED' CHECK (status IN ('PLANNED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    location TEXT NOT NULL,
    intensity_level TEXT NOT NULL CHECK (intensity_level IN ('LIGHT', 'MEDIUM', 'INTENSE', 'EXTREME')),
    CHECK (end_time > start_time)
);

-- Комментарии для документации
COMMENT ON TABLE events.workout IS 'События тренировок в тренажерном зале';
COMMENT ON COLUMN events.workout.location IS 'Место проведения (название зала/локации)';
COMMENT ON COLUMN events.workout.intensity_level IS 'Уровень интенсивности тренировки';
