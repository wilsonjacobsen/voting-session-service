package com.sicredi.votingsessionservice.exception;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.nio.file.AccessDeniedException;
import java.util.Map;

/**
 * @author Douglas Z Rossi
 *
 */
@RestControllerAdvice
@SuppressWarnings("static-method")
public class GlobalExceptionHandlerController extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6068704083904763831L;

	public ErrorAttributes errorAttributes() {
        // Hide exception field in the return object
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(ServerRequest requestAttributes, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                errorAttributes.remove("exception");
                return errorAttributes;
            }
        };
    }

//    @ExceptionHandler(StockHoldException.class)
//	public ResponseEntity<List<StockResponse>> handleStockHoldException(final StockHoldException stock) {
//		return new ResponseEntity<>(stock.getList(), HttpStatus.UNPROCESSABLE_ENTITY);
//	}
    
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex) {
    	return ResponseEntity.badRequest().body(ex.getMessage());
//        res.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public ResponseEntity<?> handleInvalidDefinitionException(InvalidDefinitionException ex) {
//        res.sendError(HttpStatus.BAD_REQUEST.value(), ex.getOriginalMessage());
        return ResponseEntity.badRequest().body(ex.getOriginalMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
       // res.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(PreconditionFailed.class)
    public ResponseEntity<?> handlePreConditionFailedException(PreconditionFailed ex) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(ex.getMessage());
//        res.sendError(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException()  {
    	return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        //res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
    }
    

}
