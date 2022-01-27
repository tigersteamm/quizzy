package uz.jl.enums;

import lombok.Getter;

@Getter
public enum Level {
    EASY, MEDIUM, HARD;

    public static Level getByName(String level) {
        for (Level value : Level.values()) {
            if (value.name().equals(level)) {
                return value;
            }
        }
        return EASY;
    }
}
