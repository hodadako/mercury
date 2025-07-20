package com.mercury.utils;

import static com.mercury.utils.NamedArgumentsFactory.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.mercury.exception.DNADecodingExceptionMessages;
import com.mercury.exception.DNAEncodingExceptionMessages;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public final class ExceptionTestDataProvider {
	private ExceptionTestDataProvider() {
	}

	static Stream<Arguments> simpleDNAEncoder() {
		return Stream.of(
			exception("if input is null", (byte[])null, DNAEncodingExceptionMessages.NULL_OR_EMPTY.getDescription()),
			exception("if input is empty", new byte[0], DNAEncodingExceptionMessages.NULL_OR_EMPTY.getDescription())
		);
	}

	static Stream<Arguments> encodingUtils() {
		return Stream.of(
			exception("if input is null", (String)null, DNAEncodingExceptionMessages.NULL_OR_EMPTY.getDescription()),
			exception("if input is empty", "", DNAEncodingExceptionMessages.NULL_OR_EMPTY.getDescription())
		);
	}

	static Stream<Arguments> simpleDNADecoder() {
		return Stream.of(
			exception("if input is null", (String)null, DNADecodingExceptionMessages.NULL_OR_EMPTY.getDescription()),
			exception("if input is empty", "", DNADecodingExceptionMessages.NULL_OR_EMPTY.getDescription()),
			exception("if input is too short", "ATG", DNADecodingExceptionMessages.TOO_SHORT.getDescription()),
			exception("if input is missing start codon", "AAACCCTAA",
				DNADecodingExceptionMessages.MISSING_START_CODON.getDescription()),
			exception("if input is missing stop codon", "ATGACGTACXXX",
				DNADecodingExceptionMessages.INVALID_STOP_CODON.getDescription()),
			exception("if input length / codon alignment is invalid", "ATGACGTAA",
				DNADecodingExceptionMessages.INVALID_PAYLOAD.getDescription()),
			exception("if input has invalid base", "ATGTTAGZATGCGCATAA",
				DNADecodingExceptionMessages.INVALID_BASE.getDescription())
		);
	}
}
