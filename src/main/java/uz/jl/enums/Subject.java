package uz.jl.enums;

import lombok.Getter;

@Getter
public enum Subject {
    MATH, ENGLISH;

    public static Subject getByName(String subject) {
        for (Subject value : Subject.values()) {
            if (value.name().equals(subject)) {
                return value;
            }
        }
        return ENGLISH;
    }
}
