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

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class AlternateTextRepresentationTest {

    @Test
    void testOfUri() throws URISyntaxException {
        URI uri = new URI("http://example.com/alt-text");
        AlternateTextRepresentation altrep = AlternateTextRepresentation.of(uri);
        assertEquals("ALTREP=\"http://example.com/alt-text\"", altrep.formatted());
    }

    @Test
    void testOfString() {
        String uriStr = "http://example.com/description.html";
        AlternateTextRepresentation altrep = AlternateTextRepresentation.of(uriStr);
        assertEquals("ALTREP=\"http://example.com/description.html\"", altrep.formatted());
    }

    @Test
    void testOfStringWithEncodedCharacters() {
        String uriStr = "http://example.com/中文";
        AlternateTextRepresentation altrep = AlternateTextRepresentation.of(uriStr);
        assertEquals("ALTREP=\"http://example.com/%E4%B8%AD%E6%96%87\"", altrep.formatted());
    }

    @Test
    void testInvalidUriStringThrowsException() {
        String invalidUri = "ht!tp://[invalid uri]";
        assertThrows(IllegalArgumentException.class, () -> AlternateTextRepresentation.of(invalidUri));
    }
}

