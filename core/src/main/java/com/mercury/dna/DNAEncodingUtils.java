package com.mercury.dna;

import static com.mercury.dna.DNAConversionConstants.*;

import java.nio.charset.StandardCharsets;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public final class DNAEncodingUtils {
	private DNAEncodingUtils() {
	}

	public static String encodeToDNA(String data) {
		if (data == null || data.isEmpty()) {
			throw new DNAEncodingException("no data provided to encode to DNA.");
		}
		byte[] binaryData = data.getBytes(StandardCharsets.UTF_8);

		StringBuilder dnaPayload = new StringBuilder();

		for (byte b : binaryData) {
			int unsignedByte = b & 0xFF;
			for (int i = 6; i >= 0; i -= 2) {
				int twoBits = (unsignedByte >> i) & 0b11;
				dnaPayload.append(TWO_BITS_TO_BASE_MAP.get(twoBits));
			}
		}

		int remainder = dnaPayload.length() % 3;
		if (remainder == 0) {
			return addStartAndStopCodon(dnaPayload.toString());
		}

		int paddingLength = 3 - remainder;
		for (int i = 0; i < paddingLength; i++) {
			dnaPayload.append('A');
		}
		return addStartAndStopCodon(dnaPayload.toString(), paddingLength);
	}

	private static String addStartAndStopCodon(String dna) {
		return addStartAndStopCodon(dna, 0);
	}

	private static String addStartAndStopCodon(String dna, int paddingLength) {
		return START_CODON + dna + STOP_CODONS.get(paddingLength);
	}
}
