package com.workshop.exemplo.controller.exceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
public class WorkShopExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String userMsg	= messageSource.getMessage("resources.messagenotreadable", null, LocaleContextHolder.getLocale());
		String devsMsg	= ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> error = Arrays.asList(new Error(userMsg, devsMsg, ex));
		return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errors = createErrorList(ex.getBindingResult(), ex);
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String userMsg = messageSource.getMessage("resources.not-found", null, LocaleContextHolder.getLocale());
		String msgDev = ex.toString();
		List<Error> erros = Arrays.asList(new Error(userMsg, msgDev, ex));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class } )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String userMsg = messageSource.getMessage("resources.integrity-violation", null, LocaleContextHolder.getLocale());
		String msgDev = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> erros = Arrays.asList(new Error(userMsg, msgDev, ex));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler( { IncorrectResultSizeDataAccessException.class })
	public ResponseEntity<Object>  handleIncorrectResultSizeDataAccessException( 
			IncorrectResultSizeDataAccessException ex, WebRequest request){
		
		String userMsg = messageSource.getMessage("resources.incorrectresultsizedataaccessexception",
				null, LocaleContextHolder.getLocale());
		String msgDev = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> erros = Arrays.asList(new Error(userMsg, msgDev, ex));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	
	
//	parei aqui - Testar incluir novo usuário sem senha
	
	@ExceptionHandler({ Exception.class } )
	public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
		String userMsg = messageSource.getMessage("resources.exception", null, LocaleContextHolder.getLocale());
		String msgDev = "Method: handleOtherException.  ";

		List<Error> erros = Arrays.asList(new Error(userMsg, msgDev, ex));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	
	private List<Error> createErrorList(BindingResult bindingResult, Exception ex) {
		List<Error> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String userMsg = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String msgDev = fieldError.toString();
			erros.add(new Error(userMsg, msgDev, ex));
		}
		
		return erros;
	}
	
	@Slf4j  //RI: Annotation do Lombok para log.  Cria automaticamente a variável estática 'log'
	public static class Error {
		
		private String userMsg;
		private String devsMsg;
		private String stackTrace[];
		
		public String[] getStackTrace() {
			return stackTrace;
		}

		public void setStackTrace(String[] stackTrace) {
			this.stackTrace = stackTrace;
		}

		public Error(String userMsg, String devsMsg, Exception ex) {
			this.userMsg = userMsg;
			this.devsMsg = devsMsg;
			
			StackTraceElement[] stackTrace = ex.getStackTrace();
			String[] argStackTrace = new String[10];
			
			
			for( int x = 0; x < stackTrace.length && x < 6 ; x++ ){
				if( this.devsMsg.length() > 0 ) this.devsMsg = this.devsMsg+".  "; 
				if( x < 2 ) this.devsMsg = this.devsMsg + stackTrace[x];
				argStackTrace[x] = stackTrace[x].toString();
			}
			ex.printStackTrace();
			log.error(ex.getMessage());
		}

		public String getUserMsg() {
			return userMsg;
		}

		public String getDevsMsg() {
			return devsMsg;
		}
		
	}

}
