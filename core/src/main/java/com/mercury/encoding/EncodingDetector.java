package com.mercury.encoding;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public interface EncodingDetector {
	String detectEncoding(byte[] data);
}
