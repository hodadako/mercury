package com.mercury.dna;

import java.nio.charset.StandardCharsets;

import com.mercury.exception.DNADecodingExceptionMessages;
import com.mercury.exception.DNADecodingException;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public class SimpleDNADecoder implements DNADecoder {

    @Override
    public String decode(String dna) {
        validate(dna);

        String stopCodon = dna.substring(dna.length() - 3);
        int paddingLength = DNAConversionConstants.STOP_CODONS_TO_PADDING_LENGTH_MAP.get(stopCodon);
        String payload = dna.substring(
            DNAConversionConstants.START_CODON.length(),
            dna.length() - (3 + paddingLength)
        );

        byte[] bytes = decodePayload(payload);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private void validate(String dna) {
        if (dna == null || dna.isBlank()) {
            throw new DNADecodingException(DNADecodingExceptionMessages.NULL_OR_EMPTY);
        }

        if (dna.length() < 9) {
            throw new DNADecodingException(DNADecodingExceptionMessages.TOO_SHORT);
        }

        if (!dna.startsWith(DNAConversionConstants.START_CODON)) {
            throw new DNADecodingException(DNADecodingExceptionMessages.MISSING_START_CODON);
        }

        if (dna.length() % 3 != 0) {
            throw new DNADecodingException(DNADecodingExceptionMessages.INVALID_LENGTH);
        }

        String stopCodon = dna.substring(dna.length() - 3);
        if (!DNAConversionConstants.STOP_CODONS.contains(stopCodon)) {
            throw new DNADecodingException(DNADecodingExceptionMessages.INVALID_STOP_CODON);
        }

        int paddingLength = DNAConversionConstants.STOP_CODONS_TO_PADDING_LENGTH_MAP.get(stopCodon);
        String payload = dna.substring(
            DNAConversionConstants.START_CODON.length(),
            dna.length() - (3 + paddingLength)
        );

        if (payload.isBlank() || payload.length() % 4 != 0) {
            throw new DNADecodingException(DNADecodingExceptionMessages.INVALID_PAYLOAD);
        }
    }

    private byte[] decodePayload(String payload) {
        int byteCount = payload.length() / 4;
        byte[] bytes = new byte[byteCount];

        for (int i = 0; i < byteCount; i++) {
            int b = 0;
            for (int j = 0; j < 4; j++) {
                char base = payload.charAt(i * 4 + j);
                Integer bits = DNAConversionConstants.BASE_TO_TWO_BITS_MAP.get(base);
                if (bits == null) {
                    throw new DNADecodingException(DNADecodingExceptionMessages.INVALID_BASE, base);
                }
                b = (b << 2) | bits;
            }
            bytes[i] = (byte) b;
        }

        return bytes;
    }
}
