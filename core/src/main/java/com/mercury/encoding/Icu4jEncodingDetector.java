package com.mercury.encoding;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;


public class Icu4jEncodingDetector implements EncodingDetector {
	@Override
	public String detectEncoding(byte[] data) {
		if (data.length == 0) return "";

		CharsetDetector detector = new CharsetDetector();
		detector.setText(data);
		CharsetMatch match = detector.detect();
		if (match == null) return "";
		return match.getName();
	}
}
