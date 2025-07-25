package com.mercury.translator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
class SimpleMercuryTranslatorTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource(value = {
            "com.mercury.utils.TranslatorTestDataProvider#englishSentences",
            "com.mercury.utils.TranslatorTestDataProvider#koreanSentences",
            "com.mercury.utils.TranslatorTestDataProvider#japaneseSentences" 
        })
    void shouldEncodeAndDecodeCorrectly(String input) {
        SimpleMercuryTranslator translator = new SimpleMercuryTranslator();
        String dna = translator.encode(input);
        String decoded = translator.decode(dna);
        assertThat(input).isEqualTo(decoded);
    }
}