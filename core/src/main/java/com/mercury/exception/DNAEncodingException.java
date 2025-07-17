package com.mercury.exception;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public class DNAEncodingException extends RuntimeException {
	public DNAEncodingException(DNAEncodingExceptionMessages message) {
		super(message.getDescription());
	}
}
