package com.mercury.dna;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleDNADecoderTest {

    @Test
    void shouldThrowExceptionOnNullInput() {
        SimpleDNADecoder decoder = new SimpleDNADecoder();
        assertThatThrownBy(() -> decoder.decode(null))
                .isInstanceOf(DNAEncodingException.class)
                .hasMessageContaining("too short or null");
    }

    @Test
    void shouldThrowExceptionOnShortInput() {
        SimpleDNADecoder decoder = new SimpleDNADecoder();
        assertThatThrownBy(() -> decoder.decode("ATG"))
                .isInstanceOf(DNAEncodingException.class)
                .hasMessageContaining("too short or null");
    }

    @Test
    void shouldThrowExceptionOnMissingStartCodon() {
        SimpleDNADecoder decoder = new SimpleDNADecoder();
        String dna = "AAAACCCCTAA";
        assertThatThrownBy(() -> decoder.decode(dna))
                .isInstanceOf(DNAEncodingException.class)
                .hasMessageContaining("missing start codon");
    }

    @Test
    void shouldThrowExceptionOnInvalidStopCodon() {
        SimpleDNADecoder decoder = new SimpleDNADecoder();
        String dna = "ATGACGTACXXX";
        assertThatThrownBy(() -> decoder.decode(dna))
                .isInstanceOf(DNAEncodingException.class)
                .hasMessageContaining("Invalid or missing stop codon");
    }

    @Test
    void shouldThrowExceptionOnInvalidPayloadLength() {
        SimpleDNADecoder decoder = new SimpleDNADecoder();
        String dna = "ATGACGTAA"; // payload length 3, should be multiple of 3 (after removing start/stop)
        assertThatThrownBy(() -> decoder.decode(dna))
                .isInstanceOf(DNAEncodingException.class)
                .hasMessageContaining("Invalid payload length or codon alignment");
    }

    @Test
    void shouldThrowExceptionOnInvalidPaddingBases() {
        SimpleDNADecoder decoder = new SimpleDNADecoder();
        String dna = "ATGACGTAG";
        assertThatThrownBy(() -> decoder.decode(dna))
                .isInstanceOf(DNAEncodingException.class)
                .hasMessageContaining("Padding bases do not match expected padding length");
    }

    @Test
    void shouldThrowExceptionOnInvalidBase() {
        SimpleDNADecoder decoder = new SimpleDNADecoder();
        String dna = "ATGXXXTAA";
        assertThatThrownBy(() -> decoder.decode(dna))
                .isInstanceOf(DNAEncodingException.class)
                .hasMessageContaining("Invalid DNA base");
    }
} 