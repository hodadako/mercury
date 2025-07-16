package com.mercury.dna;

import com.mercury.encoding.EncodingDetector;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleDNAEncoderTest {

    @Test
    void shouldThrowExceptionOnNullByteArray() {
        SimpleDNAEncoder encoder = new SimpleDNAEncoder();
        assertThatThrownBy(() -> encoder.encode((byte[]) null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not be null or empty");
    }

    @Test
    void shouldThrowExceptionOnEmptyByteArray() {
        SimpleDNAEncoder encoder = new SimpleDNAEncoder();
        assertThatThrownBy(() -> encoder.encode(new byte[0]))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not be null or empty");
    }

    @Test
    void shouldThrowExceptionOnUnknownCharset() {
        EncodingDetector detector = data -> null;
        SimpleDNAEncoder encoder = new SimpleDNAEncoder(detector);
        byte[] data = "test".getBytes();
        assertThatThrownBy(() -> encoder.encode(data))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Failed to detect character encoding");
    }

    @Test
    void shouldThrowExceptionOnUnsupportedCharset() {
        EncodingDetector detector = data -> "UNSUPPORTED_CHARSET";
        SimpleDNAEncoder encoder = new SimpleDNAEncoder(detector);
        byte[] data = "test".getBytes();
        assertThatThrownBy(() -> encoder.encode(data))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Detected unsupported charset");
    }

    @Test
    void shouldEncodeValidByteArray() {
        SimpleDNAEncoder encoder = new SimpleDNAEncoder();
        byte[] data = "A".getBytes();
        String dna = encoder.encode(data);
        assertThat(dna).startsWith("ATG");
        assertThat(dna).endsWith("TGA");
    }

    @Test
    void shouldEncodeValidString() {
        SimpleDNAEncoder encoder = new SimpleDNAEncoder();
        String dna = encoder.encode("A");
        assertThat(dna).startsWith("ATG");
        assertThat(dna).endsWith("TGA");
    }
} 