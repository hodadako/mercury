package com.mercury.dna;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mercury.exception.DNAEncodingException;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
class DNAEncodingUtilsTest {

	@ParameterizedTest(name = "{0}")
	@MethodSource(value = "com.mercury.utils.ExceptionTestDataProvider#encodingUtils")
	void shouldThrowException(String payload, String expectedMessage) {
		assertThatThrownBy(() -> DNAEncodingUtils.encodeToDNA(payload))
			.isInstanceOf(DNAEncodingException.class)
			.hasMessage(expectedMessage);
	}

	@Test
	void shouldEncodeAsciiCharacterCorrectly() {
		String result = DNAEncodingUtils.encodeToDNA("A");
		assertAll(
			() -> assertThat(result).startsWith("ATG"),
			() -> assertThat(result).endsWith("TGA")
		);
	}

	@Test
	void shouldEncodeKoreanCharacterCorrectly() {
		String result = DNAEncodingUtils.encodeToDNA("가");
		assertAll(
			() -> assertThat(result).startsWith("ATG"),
			() -> assertThat(result).hasSizeGreaterThan(10)
		);
	}

	@Test
	void shouldUseTaaWhenNoPaddingRequired() {
		String result = DNAEncodingUtils.encodeToDNA("ABC");
		assertThat(result).endsWith("TAA");
	}

	@Test
	void shouldUseTgaWhenTwoPaddingRequired() {
		String result = DNAEncodingUtils.encodeToDNA("A");
		assertThat(result).endsWith("TGA");
	}

	@Test
	void shouldUseTagWhenOnePaddingRequired() {
		String result = DNAEncodingUtils.encodeToDNA("AB");
		assertThat(result).endsWith("TAG");
	}

	@Test
	void shouldAlwaysStartWithStartCodon() {
		String result = DNAEncodingUtils.encodeToDNA("Hello");
		assertThat(result).startsWith("ATG");
	}

	@Test
	void shouldAppendCorrectPaddingBases() {
		String result = DNAEncodingUtils.encodeToDNA("AB");
		int payloadLength = result.length() - 3 - 3;
		assertAll(
			() -> assertThat(payloadLength % 3).isZero(),
			() -> assertThat(result.charAt(result.length() - 4)).isEqualTo('A')
		);
	}
}