package com.mercury.exception;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public class DNADecodingException extends RuntimeException {
	public DNADecodingException(DNADecodingExceptionMessages message) {
		super(message.getDescription());
	}

	public DNADecodingException(DNADecodingExceptionMessages message, Character... args) {
		super(message.format(args));
	}
}
