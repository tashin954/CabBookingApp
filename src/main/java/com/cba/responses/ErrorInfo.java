package com.cba.responses;

import java.time.LocalDateTime;

public class ErrorInfo {
	private LocalDateTime timestamp;
	private String status;
	private int statusCode;
	private String error;
	private String path;

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public ErrorInfo(LocalDateTime timestamp, String status, int statusCode, String error, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.statusCode = statusCode;
		this.error = error;
		this.path = path;
	}

	public ErrorInfo() {
	}
}
