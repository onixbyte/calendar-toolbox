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

class DelegatorsTest {

    @Test
    void testSingleUriFromUri() {
        URI uri = URI.create("mailto:manager@example.com");
        Delegators delegators = Delegators.of(uri);
        assertEquals("DELEGATED-FROM=\"mailto:manager@example.com\"", delegators.formatted());
    }

    @Test
    void testMultipleUrisFromUri() {
        Delegators delegators = Delegators.of(
                URI.create("mailto:manager1@example.com"),
                URI.create("mailto:manager2@example.com")
        );
        assertEquals("DELEGATED-FROM=\"mailto:manager1@example.com\",\"mailto:manager2@example.com\"", delegators.formatted());
    }

    @Test
    void testSingleUriFromString() {
        Delegators delegators = Delegators.of("mailto:supervisor@example.org");
        assertEquals("DELEGATED-FROM=\"mailto:supervisor@example.org\"", delegators.formatted());
    }

    @Test
    void testMultipleUrisFromString() {
        Delegators delegators = Delegators.of(
                "mailto:supervisor1@example.org",
                "mailto:supervisor2@example.org"
        );
        assertEquals("DELEGATED-FROM=\"mailto:supervisor1@example.org\",\"mailto:supervisor2@example.org\"", delegators.formatted());
    }

    @Test
    void testInvalidUriStringThrowsException() {
        String invalidUri = "http::: /invalid";
        assertThrows(IllegalArgumentException.class, () -> Delegators.of(invalidUri));
    }

    @Test
    void testEmptyInput() {
        Delegators delegators = Delegators.of(new URI[0]);
        assertEquals("DELEGATED-FROM=", delegators.formatted());
    }

    @Test
    void testMixedProtocolUris() {
        Delegators delegators = Delegators.of(
                "mailto:user@example.com",
                "https://example.com/calendar/user",
                "tel:+1234567890"
        );
        assertEquals("DELEGATED-FROM=\"mailto:user@example.com\",\"https://example.com/calendar/user\",\"tel:+1234567890\"", delegators.formatted());
    }
}
