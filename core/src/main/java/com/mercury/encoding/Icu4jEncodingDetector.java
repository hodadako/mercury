package com.mercury.encoding;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public class Icu4jEncodingDetector implements EncodingDetector {
	@Override
	public String detectEncoding(byte[] data) {
		if (data.length == 0)
			return "";

		CharsetDetector detector = new CharsetDetector();
		detector.setText(data);
		CharsetMatch match = detector.detect();
		if (match == null)
			return "";
		return match.getName();
	}
}
