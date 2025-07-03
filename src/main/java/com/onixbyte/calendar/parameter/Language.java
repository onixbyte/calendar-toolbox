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

import java.util.Locale;

/**
 * Represents the iCalendar {@code LANGUAGE} parameter, which specifies the language used in the
 * property value. This parameter is used to indicate the natural language of text-based properties
 * such as summaries, descriptions, or comments.
 * <p>
 * Instances of this class are immutable and can be created via the static factory methods
 * {@link #of(Locale)} and {@link #of(String)}. The language tag is formatted according to the
 * IETF BCP 47 standard using {@link Locale#toLanguageTag()}.
 *
 * @author siujamo
 */
public final class Language implements Parameter {

    /**
     * The locale representing the language.
     */
    private final Locale value;

    /**
     * Constructs a {@code Language} with the specified locale.
     *
     * @param language the locale representing the language
     */
    private Language(Locale language) {
        this.value = language;
    }

    /**
     * Creates a {@code Language} instance from a {@link Locale}.
     *
     * @param locale the locale representing the language
     * @return a new instance of {@code Language}
     */
    public static Language of(Locale locale) {
        return new Language(locale);
    }

    /**
     * Creates a {@code Language} instance from a language tag string.
     * <p>
     * The language tag must be in a format compatible with {@link Locale#forLanguageTag(String)}.
     *
     * @param locale the language tag string
     * @return a new instance of {@code Language}
     * @throws IllegalArgumentException if the given string cannot be parsed as a valid language tag
     */
    public static Language of(String locale) {
        return new Language(Locale.forLanguageTag(locale));
    }

    /**
     * Returns the formatted {@code LANGUAGE} parameter string as specified in the
     * iCalendar specification.
     *
     * @return a formatted string in the form {@code LANGUAGE=language-tag} suitable for inclusion
     * in an iCalendar entity
     */
    @Override
    public String formatted() {
        return "LANGUAGE=" + value.toLanguageTag();
    }
}
