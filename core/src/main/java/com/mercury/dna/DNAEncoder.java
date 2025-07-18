package com.mercury.dna;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public interface DNAEncoder {
	String encode(String data);
	String encode(byte[] data);
}
