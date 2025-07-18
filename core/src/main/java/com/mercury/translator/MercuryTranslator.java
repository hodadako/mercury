package com.mercury.translator;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public interface MercuryTranslator {
    String encode(String text);
    String encode(byte[] data);
    String decode(String dna);
    byte[] decodeToBytes(String dna);
} 