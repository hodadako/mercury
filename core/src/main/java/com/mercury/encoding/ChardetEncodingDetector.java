package com.mercury.encoding;

import org.mozilla.universalchardet.UniversalDetector;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public class ChardetEncodingDetector implements EncodingDetector {

	@Override
	public String detectEncoding(byte[] data) {
		if (data.length == 0)
			return "";

		UniversalDetector detector = new UniversalDetector(null);
		detector.handleData(data, 0, data.length);
		detector.dataEnd();

		String encoding = detector.getDetectedCharset();
		detector.reset();

		if (encoding == null)
			return "";

		return encoding;
	}
}
