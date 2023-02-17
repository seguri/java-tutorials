package name.seguri.java.tutorials.springoptionalpathvariable;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.time.format.DateTimeParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {

  @ExceptionHandler(DateTimeParseException.class)
  @ResponseStatus(value = BAD_REQUEST)
  public ResponseEntity<?> dateTimeParseException(DateTimeParseException ex) {
    return new ResponseEntity<>(BAD_REQUEST.getReasonPhrase(), BAD_REQUEST);
  }
}
