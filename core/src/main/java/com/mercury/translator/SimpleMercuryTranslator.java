package com.mercury.translator;

import com.mercury.dna.DNAEncoder;
import com.mercury.dna.DNADecoder;
import com.mercury.dna.SimpleDNAEncoder;
import com.mercury.dna.SimpleDNADecoder;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public class SimpleMercuryTranslator implements MercuryTranslator {
    private final DNAEncoder encoder;
    private final DNADecoder decoder;

    public SimpleMercuryTranslator() {
        this.encoder = new SimpleDNAEncoder();
        this.decoder = new SimpleDNADecoder();
    }

    public SimpleMercuryTranslator(DNAEncoder encoder, DNADecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    @Override
    public String encode(String text) {
        return encoder.encode(text);
    }

    @Override
    public String decode(String dna) {
        return decoder.decodeToText(dna);
    }

    @Override
    public String encode(byte[] data) {
        return encoder.encode(data);
    }

    @Override
    public byte[] decodeToBytes(String dna) {
        return decoder.decodeToBytes(dna);
    }
} 