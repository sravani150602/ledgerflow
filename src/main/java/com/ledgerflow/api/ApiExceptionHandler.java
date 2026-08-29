package com.ledgerflow.api;
import com.ledgerflow.api.ApiDtos.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.NoSuchElementException;
@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(NoSuchElementException.class) ResponseEntity<ErrorResponse> notFound(Exception e,HttpServletRequest r){return build(404,"Not Found",e.getMessage(),r);}
  @ExceptionHandler({IllegalArgumentException.class,MethodArgumentNotValidException.class}) ResponseEntity<ErrorResponse> badRequest(Exception e,HttpServletRequest r){return build(400,"Bad Request",e.getMessage(),r);}
  private ResponseEntity<ErrorResponse> build(int status,String error,String msg,HttpServletRequest r){return ResponseEntity.status(status).body(new ErrorResponse(Instant.now(),status,error,msg,r.getRequestURI()));}
}
