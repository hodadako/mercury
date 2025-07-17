package com.mercury.exception;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public enum DNAEncodingExceptionMessages {
	NULL_OR_EMPTY("Input data must not be null or empty."),
	INVALID_CHARACTER_ENCODING("Failed to detect character encoding."),
	UNSUPPORTED_CHARSET("Detected unsupported charset");

	private final String description;

	DNAEncodingExceptionMessages(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
