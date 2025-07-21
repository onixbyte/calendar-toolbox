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

class DelegateesTest {

    @Test
    void testSingleUriFromUri() {
        URI uri = URI.create("mailto:john.doe@example.com");
        Delegatees delegatees = Delegatees.of(uri);
        assertEquals("DELEGATED-TO=\"mailto:john.doe@example.com\"", delegatees.formatted());
    }

    @Test
    void testMultipleUrisFromUri() {
        Delegatees delegatees = Delegatees.of(
                URI.create("mailto:john.doe@example.com"),
                URI.create("mailto:jane.doe@example.com")
        );
        assertEquals("DELEGATED-TO=\"mailto:john.doe@example.com\",\"mailto:jane.doe@example.com\"", delegatees.formatted());
    }

    @Test
    void testSingleUriFromString() {
        Delegatees delegatees = Delegatees.of("mailto:zhangsan@example.cn");
        assertEquals("DELEGATED-TO=\"mailto:zhangsan@example.cn\"", delegatees.formatted());
    }

    @Test
    void testMultipleUrisFromString() {
        Delegatees delegatees = Delegatees.of(
                "mailto:zhangsan@example.cn",
                "mailto:lisi@example.cn"
        );
        assertEquals("DELEGATED-TO=\"mailto:zhangsan@example.cn\",\"mailto:lisi@example.cn\"", delegatees.formatted());
    }

    @Test
    void testInvalidUriStringThrowsException() {
        String invalidUri = "http::: /invalid";
        assertThrows(IllegalArgumentException.class, () -> Delegatees.of(invalidUri));
    }

    @Test
    void testEmptyInput() {
        Delegatees delegatees = Delegatees.of(new URI[0]);
        assertEquals("DELEGATED-TO=", delegatees.formatted());
    }

}

