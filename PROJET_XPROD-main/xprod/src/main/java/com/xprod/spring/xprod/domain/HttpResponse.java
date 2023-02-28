package com.xprod.spring.xprod.domain;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HttpResponse {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+1")
	private Date timeStamp; //timeStamp: 18765744893939 ms pour 5jours alors que je veux que ce soit lisible donc je rajoute un format
	private int httpStatusCode;  //200, 201, 400, 500
	private HttpStatus httpStatus;
	private String reason;
	private String message;
	
	
	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getReason() {
		return reason;
	}

	public String getMessage() {
		return message;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public HttpResponse() {
		super();
	}

	public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message) {
		super();
		this.timeStamp = new Date();
		this.httpStatusCode = httpStatusCode;
		this.httpStatus = httpStatus;
		this.reason = reason;
		this.message = message;
	}

	

	

	
	
	
	
	

}
