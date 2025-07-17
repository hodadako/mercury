package com.mercury.dna;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mercury.exception.DNADecodingException;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
class SimpleDNADecoderTest {

	@ParameterizedTest(name = "{0}")
	@MethodSource(value = {"com.mercury.utils.ExceptionTestDataProvider#simpleDNADecoder"})
	void shouldThrowException(String payload, String expectedMessage) {
		SimpleDNADecoder decoder = new SimpleDNADecoder();
		assertThatThrownBy(() -> decoder.decode(payload))
			.isInstanceOf(DNADecodingException.class)
			.hasMessageContaining(expectedMessage);
	}
} 