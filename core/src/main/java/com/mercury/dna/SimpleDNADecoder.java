package com.mercury.dna;

import java.nio.charset.StandardCharsets;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public class SimpleDNADecoder implements DNADecoder {
    @Override
    public String decode(String dna) {
        if (dna == null || dna.length() < 9) { // ATG + 1 codon + stop
            throw new DNAEncodingException("DNA sequence too short or null.");
        }
        if (!dna.startsWith(DNAConversionConstants.START_CODON)) {
            throw new DNAEncodingException("DNA sequence missing start codon.");
        }
        String stopCodon = dna.substring(dna.length() - 3);
        int paddingLength = DNAConversionConstants.STOP_CODONS.indexOf(stopCodon);
        if (paddingLength == -1) {
            throw new DNAEncodingException("Invalid or missing stop codon.");
        }
        String payload = dna.substring(3, dna.length() - 3);
        if (payload.length() < 1 || (payload.length() % 3) != 0) {
            throw new DNAEncodingException("Invalid payload length or codon alignment.");
        }
        if (paddingLength > 0) {
            if (!payload.endsWith("A".repeat(paddingLength))) {
                throw new DNAEncodingException("Padding bases do not match expected padding length.");
            }
            payload = payload.substring(0, payload.length() - paddingLength);
        }
        // Convert DNA bases to bytes (2 bits per base)
        int byteCount = payload.length() / 4;
        byte[] bytes = new byte[byteCount];
        for (int i = 0; i < byteCount; i++) {
            int b = 0;
            for (int j = 0; j < 4; j++) {
                char base = payload.charAt(i * 4 + j);
                Integer bits = DNAConversionConstants.BASE_TO_BITS_MAP.get(base);
                if (bits == null) {
                    throw new DNAEncodingException("Invalid DNA base: " + base);
                }
                b = (b << 2) | bits;
            }
            bytes[i] = (byte) b;
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
} 