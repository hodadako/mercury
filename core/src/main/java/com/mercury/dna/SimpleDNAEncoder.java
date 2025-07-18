package com.mercury.dna;

import java.nio.charset.Charset;

import com.mercury.encoding.EncodingDetector;
import com.mercury.encoding.Icu4jEncodingDetector;
import com.mercury.exception.DNAEncodingException;
import com.mercury.exception.DNAEncodingExceptionMessages;

/*
 * Copyright (c) 2025 mercury contributors
 * This program is made available under the terms of the Apache License.
 */
public class SimpleDNAEncoder implements DNAEncoder {
	private final EncodingDetector detector;

	public SimpleDNAEncoder() {
		detector = new Icu4jEncodingDetector();
	}

	public SimpleDNAEncoder(EncodingDetector detector) {
		this.detector = detector;
	}

	@Override
	public String encode(String data) {
		return DNAEncodingUtils.encodeToDNA(data);
	}

	@Override
	public String encode(byte[] data) {
		return encode(getOriginalString(data));
	}

	private String getOriginalString(byte[] data) {
		if (data == null || data.length == 0) {
			throw new DNAEncodingException(DNAEncodingExceptionMessages.NULL_OR_EMPTY);
		}

		String charsetName = detector.detectEncoding(data);
		if (charsetName == null || charsetName.isEmpty()) {
			throw new DNAEncodingException(DNAEncodingExceptionMessages.INVALID_CHARACTER_ENCODING);
		}

		try {
			Charset charset = Charset.forName(charsetName);
			return new String(data, charset);
		} catch (IllegalArgumentException | UnsupportedOperationException e) {
			throw new DNAEncodingException(DNAEncodingExceptionMessages.UNSUPPORTED_CHARSET);
		}
	}
}
