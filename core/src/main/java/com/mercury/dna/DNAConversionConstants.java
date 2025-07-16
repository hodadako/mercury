package com.mercury.dna;

import java.util.List;
import java.util.Map;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public final class DNAConversionConstants {
	public static final String START_CODON = "ATG";
	public static final List<String> STOP_CODONS = List.of("TAA", "TAG", "TGA");
	public static final Map<Integer, Character> TWO_BITS_TO_BASE_MAP = Map.of(
		0b00, 'A', 
		0b01, 'C',
		0b10, 'G',
		0b11, 'T'
	);

	/**
	 * Maps DNA base characters to their 2-bit integer representation.
	 */
	public static final Map<Character, Integer> BASE_TO_BITS_MAP = Map.of(
		'A', 0b00,
		'C', 0b01,
		'G', 0b10,
		'T', 0b11
	);

	private DNAConversionConstants() {
	}
}
