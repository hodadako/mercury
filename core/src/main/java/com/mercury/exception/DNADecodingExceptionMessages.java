package com.mercury.exception;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public enum DNADecodingExceptionMessages {
	NULL_OR_EMPTY("DNA sequence is empty or null."),
	TOO_SHORT("DNA sequence is too short."),
	MISSING_START_CODON("DNA sequence missing start codon."),
	INVALID_LENGTH("DNA length must be a multiple of 3."),
	INVALID_STOP_CODON("Invalid or missing stop codon."),
	INVALID_PAYLOAD("Invalid payload length or codon alignment."),
	INVALID_BASE("Invalid DNA base: %s");

	private final String description;

	DNADecodingExceptionMessages(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String format(Character... args) {
		return String.format(description, args);
	}
}
