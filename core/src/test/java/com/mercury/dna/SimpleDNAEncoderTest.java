package com.mercury.dna;

import static com.mercury.exception.DNAEncodingExceptionMessages.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mercury.encoding.EncodingDetector;
import com.mercury.exception.DNAEncodingException;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
class SimpleDNAEncoderTest {

	@ParameterizedTest(name = "{0}")
	@MethodSource("com.mercury.utils.ExceptionTestDataProvider#simpleDNAEncoder")
	void shouldThrowException(byte[] payload, String expectedMessage) {
		SimpleDNAEncoder encoder = new SimpleDNAEncoder();
		assertThatThrownBy(() -> encoder.encode(payload))
			.isInstanceOf(DNAEncodingException.class)
			.hasMessageMatching(expectedMessage);
	}

	@Test
	void shouldThrowExceptionOnUnknownCharset() {
		EncodingDetector detector = data -> null;
		SimpleDNAEncoder encoder = new SimpleDNAEncoder(detector);
		byte[] data = "test".getBytes();
		assertThatThrownBy(() -> encoder.encode(data))
			.isInstanceOf(DNAEncodingException.class)
			.hasMessageMatching(INVALID_CHARACTER_ENCODING.getDescription());
	}

	@Test
	void shouldThrowExceptionOnUnsupportedCharset() {
		EncodingDetector detector = data -> "UNSUPPORTED_CHARSET";
		SimpleDNAEncoder encoder = new SimpleDNAEncoder(detector);
		byte[] data = "test".getBytes();
		assertThatThrownBy(() -> encoder.encode(data))
			.isInstanceOf(DNAEncodingException.class)
			.hasMessageMatching(UNSUPPORTED_CHARSET.getDescription());
	}

	@Test
	void shouldEncodeValidByteArray() {
		SimpleDNAEncoder encoder = new SimpleDNAEncoder();
		byte[] data = "A".getBytes();
		String dna = encoder.encode(data);
		assertAll(
			() -> assertThat(dna).startsWith("ATG"),
			() -> assertThat(dna).endsWith("TGA")
		);
	}

	@Test
	void shouldEncodeValidString() {
		SimpleDNAEncoder encoder = new SimpleDNAEncoder();
		String dna = encoder.encode("A");
		assertAll(
			() -> assertThat(dna).startsWith("ATG"),
			() -> assertThat(dna).endsWith("TGA")
		);
	}
} 