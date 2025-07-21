package com.mercury.utils;

import static com.mercury.utils.NamedArgumentsFactory.translator;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
@SuppressWarnings("unused")
public final class TranslatorTestDataProvider {
    private TranslatorTestDataProvider() {}

    static Stream<Arguments> englishSentences() {
        return Stream.of(
            translator("English 1", "I will be always attached to you"),
            translator("English 2", "How are our tomatoes?")
        );
    }

    static Stream<Arguments> koreanSentences() {
        return Stream.of(
            translator("Korean 1", "항상 너와 함께할게"),
            translator("Korean 2", "우리의 토마토는 잘 있니?")
        );
    }

    static Stream<Arguments> japaneseSentences() {
        return Stream.of(
            translator("Japanese 1", "いつもあなたと一緒にいます"),
            translator("Japanese 2", "私たちのトマトは元気ですか？")
        );
    }
}

