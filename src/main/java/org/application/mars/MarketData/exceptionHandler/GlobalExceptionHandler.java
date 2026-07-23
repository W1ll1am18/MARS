package org.application.mars.MarketData.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(IllegalArgumentException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TickerNotFoundException.class)
    public ProblemDetail handleTickerNotFound(TickerNotFoundException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problem.setTitle("Ticker Not Found Within MASSIVE API or Unsupported");
        problem.setProperty("errorCode", "TICKER_NOT_FOUND");
        return problem;
    }

    @ExceptionHandler(InsufficientPredictionDataException.class)
    public ProblemDetail handleInsufficientData(InsufficientPredictionDataException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        problem.setTitle("Insufficient Prediction Data");
        problem.setProperty("errorCode", "INSUFFICIENT_DATA");
        return problem;
    }

    @ExceptionHandler(ExternalDataUnavailableException.class)
    public ProblemDetail handleExternalDataUnavailable(ExternalDataUnavailableException e) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.SERVICE_UNAVAILABLE);
        pd.setDetail(e.getMessage());
        return pd;
    }
}