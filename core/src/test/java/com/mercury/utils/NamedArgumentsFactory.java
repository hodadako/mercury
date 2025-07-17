package com.mercury.utils;

import static org.junit.jupiter.api.Named.*;

import org.junit.jupiter.params.provider.Arguments;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public final class NamedArgumentsFactory {
	private NamedArgumentsFactory() {
	}

	public static Arguments exception(String testDisplayName, byte[] input, String expectedMessage) {
		return Arguments.of(named(testDisplayName, input), expectedMessage);
	}

	public static Arguments exception(String testDisplayName, String input, String expectedMessage) {
		return Arguments.of(named(testDisplayName, input), expectedMessage);
	}
}
