package com.kdk.config.mvc;

import org.apache.commons.text.StringEscapeUtils;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2024. 6. 8. 김대광	최초작성
 * </pre>
 *
 *
 * @author 김대광
 */
public class HTMLCharacterEscapes extends CharacterEscapes {

	private static final long serialVersionUID = 1L;

	private final int[] asciiEscapes;

	public HTMLCharacterEscapes() {
		asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
		asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.core.io.CharacterEscapes#getEscapeCodesForAscii()
	 */
	@Override
	public int[] getEscapeCodesForAscii() {
		return asciiEscapes;
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.core.io.CharacterEscapes#getEscapeSequence(int)
	 */
	@Override
	public SerializableString getEscapeSequence(int ch) {
		return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char) ch)));
	}

}
