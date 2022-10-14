package co.inventorsoft.academy.springBootTask.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ServiceException extends RuntimeException {

    protected ServiceException(String message) {
        super(message);
    }

}
