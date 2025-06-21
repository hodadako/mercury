package com.mercury.encoding;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class Icu4jEncodingDetectorTest {

	Icu4jEncodingDetector detector = new Icu4jEncodingDetector();

	@Test
	void shouldDetectUtf8Encoding() {
		byte[] data = "안녕하세요 Mercury".getBytes(StandardCharsets.UTF_8);
		String encoding = detector.detectEncoding(data);
		assertThat(encoding).isEqualTo("UTF-8");
	}

	@Test
	void shouldDetectEucKrEncodingWithLongKoreanText() {
		String koreanText = "안녕하세요. 이것은 인코딩 테스트를 위한 긴 문장입니다. 다양한 문자가 포함되어 있습니다.";
		byte[] data = koreanText.getBytes(Charset.forName("EUC-KR"));
		String encoding = detector.detectEncoding(data);
		assertThat(encoding).isEqualTo("EUC-KR");
	}

	@Test
	void shouldDetectIso88591Encoding() {
		byte[] data = "Bonjour le monde".getBytes(Charset.forName("ISO-8859-1"));
		String encoding = detector.detectEncoding(data);
		assertThat(encoding).isEqualTo("ISO-8859-1");
	}

	@Test
	void shouldReturnEmptyEncodingForEmptyInput() {
		byte[] data = new byte[0];
		String encoding = detector.detectEncoding(data);
		assertThat(encoding).isEqualTo("");
	}

	@Test
	void shouldDetectUtf8EncodingWithBom() {
		byte[] bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
		byte[] text = "hello".getBytes(StandardCharsets.UTF_8);
		byte[] combined = new byte[bom.length + text.length];
		System.arraycopy(bom, 0, combined, 0, bom.length);
		System.arraycopy(text, 0, combined, bom.length, text.length);

		String encoding = detector.detectEncoding(combined);
		assertThat(encoding).isEqualTo("UTF-8");
	}

	@Test
	void shouldDetectUtf16LEEncoding() {
		byte[] data = "hello".getBytes(Charset.forName("UTF-16LE"));
		String encoding = detector.detectEncoding(data);
		assertThat(encoding).isEqualTo("UTF-16LE");
	}

	@Test
	void shouldDetectUtf16BEEncoding() {
		byte[] data = "hello".getBytes(Charset.forName("UTF-16BE"));
		String encoding = detector.detectEncoding(data);
		assertThat(encoding).isEqualTo("UTF-16BE");
	}

	@Test
	void shouldDetectUtf32LEEncoding() {
		byte[] data = "hello".getBytes(Charset.forName("UTF-32LE"));
		String encoding = detector.detectEncoding(data);
		assertThat(encoding).isEqualTo("UTF-32LE");
	}

	@Test
	void shouldDetectUtf32BEEncoding() {
		byte[] data = "hello".getBytes(Charset.forName("UTF-32BE"));
		String encoding = detector.detectEncoding(data);
		assertThat(encoding).isEqualTo("UTF-32BE");
	}
}
