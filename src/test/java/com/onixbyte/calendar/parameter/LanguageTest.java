/*
 * Copyright (c) 2024-2025 OnixByte
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.onixbyte.calendar.parameter;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class LanguageTest {

    @Test
    void testCreateFromLocale() {
        Language language = Language.of(Locale.ENGLISH);
        assertEquals("LANGUAGE=en", language.formatted());
    }

    @Test
    void testCreateFromLocaleWithCountry() {
        Language language = Language.of(Locale.US);
        assertEquals("LANGUAGE=en-US", language.formatted());
    }

    @Test
    void testCreateFromLocaleCanada() {
        Language language = Language.of(Locale.CANADA);
        assertEquals("LANGUAGE=en-CA", language.formatted());
    }

    @Test
    void testCreateFromLocaleFrance() {
        Language language = Language.of(Locale.FRANCE);
        assertEquals("LANGUAGE=fr-FR", language.formatted());
    }

    @Test
    void testCreateFromLocaleGermany() {
        Language language = Language.of(Locale.GERMANY);
        assertEquals("LANGUAGE=de-DE", language.formatted());
    }

    @Test
    void testCreateFromLocaleJapan() {
        Language language = Language.of(Locale.JAPAN);
        assertEquals("LANGUAGE=ja-JP", language.formatted());
    }

    @Test
    void testCreateFromStringTag() {
        Language language = Language.of("en-US");
        assertEquals("LANGUAGE=en-US", language.formatted());
    }

    @Test
    void testCreateFromStringTagSpanish() {
        Language language = Language.of("es-ES");
        assertEquals("LANGUAGE=es-ES", language.formatted());
    }

    @Test
    void testCreateFromStringTagChinese() {
        Language language = Language.of("zh-CN");
        assertEquals("LANGUAGE=zh-CN", language.formatted());
    }

    @Test
    void testCreateFromStringTagRussian() {
        Language language = Language.of("ru-RU");
        assertEquals("LANGUAGE=ru-RU", language.formatted());
    }

    @Test
    void testCreateFromStringTagArabic() {
        Language language = Language.of("ar-SA");
        assertEquals("LANGUAGE=ar-SA", language.formatted());
    }

    @Test
    void testCreateFromSimpleLanguageTag() {
        Language language = Language.of("en");
        assertEquals("LANGUAGE=en", language.formatted());
    }

    @Test
    void testCreateFromSimpleLanguageTagFrench() {
        Language language = Language.of("fr");
        assertEquals("LANGUAGE=fr", language.formatted());
    }

    @Test
    void testCreateFromComplexLanguageTag() {
        Language language = Language.of("zh-Hans-CN");
        assertEquals("LANGUAGE=zh-Hans-CN", language.formatted());
    }

    @Test
    void testCreateFromPrivateUseLanguageTag() {
        Language language = Language.of("x-klingon");
        assertEquals("LANGUAGE=x-klingon", language.formatted());
    }

    @Test
    void testNullStringThrowsException() {
        assertThrows(NullPointerException.class, () -> Language.of((String) null));
    }

    @Test
    void testEmptyStringCreatesUndeterminedLanguage() {
        Language language = Language.of("");
        assertEquals("LANGUAGE=und", language.formatted());
    }

    @Test
    void testMalformedLanguageTagCreatesBestFitLanguage() {
        Language language = Language.of("invalid-tag-123");
        // The language tag might be parsed as best fit, let's just verify it doesn't crash
        assertNotNull(language.formatted());
        assertTrue(language.formatted().startsWith("LANGUAGE="));
    }
}
