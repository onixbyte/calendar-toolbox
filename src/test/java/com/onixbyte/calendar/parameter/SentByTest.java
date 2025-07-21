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

import static org.junit.jupiter.api.Assertions.*;

class SentByTest {

    @Test
    void testCreateFromUri() {
        URI uri = URI.create("mailto:assistant@example.com");
        SentBy sentBy = SentBy.of(uri);
        assertEquals("SENT-BY=\"mailto:assistant@example.com\"", sentBy.formatted());
    }

    @Test
    void testCreateFromString() {
        SentBy sentBy = SentBy.of("mailto:secretary@example.com");
        assertEquals("SENT-BY=\"mailto:secretary@example.com\"", sentBy.formatted());
    }

    @Test
    void testCreateFromHttpUri() {
        SentBy sentBy = SentBy.of("https://example.com/calendar/proxy");
        assertEquals("SENT-BY=\"https://example.com/calendar/proxy\"", sentBy.formatted());
    }

    @Test
    void testCreateFromHttpsUri() {
        SentBy sentBy = SentBy.of("https://secure.example.com/user/proxy");
        assertEquals("SENT-BY=\"https://secure.example.com/user/proxy\"", sentBy.formatted());
    }

    @Test
    void testCreateFromTelUri() {
        SentBy sentBy = SentBy.of("tel:+1234567890");
        assertEquals("SENT-BY=\"tel:+1234567890\"", sentBy.formatted());
    }

    @Test
    void testCreateFromSipUri() {
        SentBy sentBy = SentBy.of("sip:proxy@example.com");
        assertEquals("SENT-BY=\"sip:proxy@example.com\"", sentBy.formatted());
    }

    @Test
    void testInvalidUriStringThrowsException() {
        String invalidUri = "http::: /invalid";
        assertThrows(IllegalArgumentException.class, () -> SentBy.of(invalidUri));
    }

    @Test
    void testNullStringThrowsException() {
        assertThrows(NullPointerException.class, () -> SentBy.of((String) null));
    }

    @Test
    void testEmptyStringCreatesValidUri() {
        SentBy sentBy = SentBy.of("");
        assertEquals("SENT-BY=\"\"", sentBy.formatted());
    }

    @Test
    void testUriWithSpecialCharacters() {
        SentBy sentBy = SentBy.of("mailto:user+tag@example.com");
        assertEquals("SENT-BY=\"mailto:user+tag@example.com\"", sentBy.formatted());
    }

    @Test
    void testUriWithQueryParameters() {
        SentBy sentBy = SentBy.of("https://example.com/proxy?user=123&role=assistant");
        assertEquals("SENT-BY=\"https://example.com/proxy?user=123&role=assistant\"", sentBy.formatted());
    }

    @Test
    void testFormattedIsQuoted() {
        SentBy sentBy = SentBy.of("mailto:proxy@example.com");
        String formatted = sentBy.formatted();
        
        assertTrue(formatted.contains("\"mailto:proxy@example.com\""));
        assertTrue(formatted.startsWith("SENT-BY=\""));
        assertTrue(formatted.endsWith("\""));
    }

    @Test
    void testIsParameter() {
        SentBy sentBy = SentBy.of("mailto:proxy@example.com");
        assertTrue(sentBy instanceof Parameter);
    }

    @Test
    void testMultipleInstancesWithSameUri() {
        SentBy sentBy1 = SentBy.of("mailto:proxy@example.com");
        SentBy sentBy2 = SentBy.of("mailto:proxy@example.com");
        
        assertEquals(sentBy1.formatted(), sentBy2.formatted());
    }

    @Test
    void testConsistentBehavior() {
        SentBy sentBy = SentBy.of("mailto:proxy@example.com");
        
        // Multiple calls should return the same result
        for (int i = 0; i < 10; i++) {
            assertEquals("SENT-BY=\"mailto:proxy@example.com\"", sentBy.formatted());
        }
    }
}
