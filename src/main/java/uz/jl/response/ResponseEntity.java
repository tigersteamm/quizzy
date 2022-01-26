package uz.jl.response;


import lombok.*;
import uz.jl.enums.HttpStatus;

@Getter
@Setter
@ToString(callSuper = true)
public class ResponseEntity<D> {
    private D data;
    private Integer status;

    public ResponseEntity(D data) {
        this(data, 0);
    }

    public ResponseEntity(D data, HttpStatus status) {
        this(data, status.getCode());
    }

    public ResponseEntity(D data, Integer status) {
        this.data = data;
        this.status = status;
    }
}
