package pl.morstern.PizzaCQRS.service.queries.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OrderNotFoundException extends RuntimeException {
    String message;
    HttpStatus httpStatus;
    public OrderNotFoundException(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
