package uz.jl.exceptions;


import lombok.Getter;
import uz.jl.enums.HttpStatus;

public class ApiRuntimeException extends RuntimeException {
    @Getter
    private Integer status;

    public ApiRuntimeException(String message, HttpStatus status) {
        super(message);
        this.status = status.getCode();
    }
}
