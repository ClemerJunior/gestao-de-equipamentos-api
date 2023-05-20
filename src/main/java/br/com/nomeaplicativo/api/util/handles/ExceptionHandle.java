package br.com.nomeaplicativo.api.util.handles;

import br.com.nomeaplicativo.api.exceptions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandle {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleValidationExceptions(MethodArgumentNotValidException ex) {

        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        responseError.setDescription("Erro de validação de campos.");

        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String errorMessage = e.getDefaultMessage();
            responseError.getFields().put(fieldName, errorMessage);
        });

        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseError handleConstraintViolationException(ConstraintViolationException ex) {

        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        responseError.setDescription(ex.getMessage());

        ex.getConstraintViolations().forEach(cv -> {
            String fieldName = cv.getPropertyPath() == null ? "" : cv.getPropertyPath().toString();
            String errorMessage = cv.getMessage();
            responseError.getFields().put(fieldName, errorMessage);
        });

        return responseError;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseError handleAuthenticationException(AuthenticationException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.UNAUTHORIZED.value());
        responseError.setDescription(ex.getMessage());

        return responseError;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseError handleNotFoundException(NoSuchElementException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.NOT_FOUND.value());
        responseError.setDescription("Recurso não encontrado.");
        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NegocioException.class)
    public ResponseError handleNegocioExceptionException(NegocioException e) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.BAD_REQUEST.value());
        responseError.setDescription(e.getMessage());
        return responseError;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseError handleException(Exception ex) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.setDescription("Ocorreu um erro interno em nosso servidor.");
        return responseError;
    }
}
