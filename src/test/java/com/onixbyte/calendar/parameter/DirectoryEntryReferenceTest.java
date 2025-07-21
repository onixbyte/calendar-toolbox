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

class DirectoryEntryReferenceTest {

    @Test
    void testCreateFromString() {
        DirectoryEntryReference reference = DirectoryEntryReference.of("https://directory.example.com/user/123");
        assertEquals("DIR=\"https://directory.example.com/user/123\"", reference.formatted());
    }

    @Test
    void testCreateFromUri() {
        URI uri = URI.create("ldap://ldap.example.com/cn=John%20Doe,dc=example,dc=com");
        DirectoryEntryReference reference = DirectoryEntryReference.of(uri);
        assertEquals("DIR=\"ldap://ldap.example.com/cn=John%20Doe,dc=example,dc=com\"", reference.formatted());
    }

    @Test
    void testCreateFromHttpUri() {
        DirectoryEntryReference reference = DirectoryEntryReference.of("http://example.com/directory/user");
        assertEquals("DIR=\"http://example.com/directory/user\"", reference.formatted());
    }

    @Test
    void testCreateFromHttpsUri() {
        DirectoryEntryReference reference = DirectoryEntryReference.of("https://secure.example.com/user/profile");
        assertEquals("DIR=\"https://secure.example.com/user/profile\"", reference.formatted());
    }

    @Test
    void testCreateFromLdapUri() {
        DirectoryEntryReference reference = DirectoryEntryReference.of("ldap://server.example.com/ou=People,dc=example,dc=com");
        assertEquals("DIR=\"ldap://server.example.com/ou=People,dc=example,dc=com\"", reference.formatted());
    }

    @Test
    void testInvalidUriStringThrowsException() {
        String invalidUri = "http::: /invalid";
        assertThrows(IllegalArgumentException.class, () -> DirectoryEntryReference.of(invalidUri));
    }

    @Test
    void testEmptyStringCreatesValidUri() {
        DirectoryEntryReference reference = DirectoryEntryReference.of("");
        assertEquals("DIR=\"\"", reference.formatted());
    }

    @Test
    void testNullStringThrowsException() {
        assertThrows(NullPointerException.class, () -> DirectoryEntryReference.of((String) null));
    }

    @Test
    void testUriWithSpecialCharacters() {
        DirectoryEntryReference reference = DirectoryEntryReference.of("https://directory.example.com/user?id=123&name=John%20Doe");
        assertEquals("DIR=\"https://directory.example.com/user?id=123&name=John%20Doe\"", reference.formatted());
    }
}
