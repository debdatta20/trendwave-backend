
package com.examly.springapp.exceptions;

public class DuplicateOrderException extends RuntimeException{
	public DuplicateOrderException(String msg) {
		super(msg);
	}
}
