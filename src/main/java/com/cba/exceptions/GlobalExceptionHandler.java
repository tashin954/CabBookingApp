package com.cba.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cba.responses.ErrorInfo;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handlingException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new LinkedHashMap<>();

		List<FieldError> list = ex.getFieldErrors();
		for (FieldError temp : list) {
			String fieldName = temp.getField();
			String errorMsg = temp.getDefaultMessage();
			errorMap.put(fieldName, errorMsg);
		}

		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TripNotFoundException.class)
	public ResponseEntity<ErrorInfo> handlingException(TripNotFoundException ex, HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());

		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomerIdNotFoundException.class)
	public ResponseEntity<ErrorInfo> handlingException(CustomerIdNotFoundException ex, HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());

		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DriverIdNotFoundException.class)
	public ResponseEntity<ErrorInfo> handlingException(DriverIdNotFoundException ex, HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());

		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoBestDriverFoundException.class)
	public ResponseEntity<ErrorInfo> handlingException(NoBestDriverFoundException ex, HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());

		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomersNotFoundException.class)
	public ResponseEntity<ErrorInfo> handlingException(CustomersNotFoundException ex, HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());
		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CabIdNotFoundException.class)
	public ResponseEntity<ErrorInfo> handlingException(CabIdNotFoundException ex, HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());
		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CarTypeNotFoundException.class)
	public ResponseEntity<ErrorInfo> handlingException(CarTypeNotFoundException ex, HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());
		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomerNotFoundInDatabaseException.class)
	public ResponseEntity<ErrorInfo> handlingException(CustomerNotFoundInDatabaseException ex,
			HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());
		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoTripsOnThisDateException.class)
	public ResponseEntity<ErrorInfo> handlingException(NoTripsOnThisDateException ex, HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());
		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AdminIdNotFoundException.class)
	public ResponseEntity<ErrorInfo> handlingException(AdminIdNotFoundException ex, HttpServletRequest request) {
		String msg = ex.getMessage();
		ErrorInfo eInfo = new ErrorInfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
				msg, request.getRequestURI());
		return new ResponseEntity<>(eInfo, HttpStatus.NOT_FOUND);
	}

}
