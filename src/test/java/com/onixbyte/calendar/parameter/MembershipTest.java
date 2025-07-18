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

class MembershipTest {

    @Test
    void testSingleUriFromUri() {
        URI uri = URI.create("mailto:team@example.com");
        Membership membership = Membership.of(uri);
        assertEquals("MEMBER=\"mailto:team@example.com\"", membership.formatted());
    }

    @Test
    void testMultipleUrisFromUri() {
        Membership membership = Membership.of(
                URI.create("mailto:team1@example.com"),
                URI.create("mailto:team2@example.com")
        );
        assertEquals("MEMBER=\"mailto:team1@example.com\",\"mailto:team2@example.com\"", membership.formatted());
    }

    @Test
    void testSingleUriFromString() {
        Membership membership = Membership.of("mailto:group@example.org");
        assertEquals("MEMBER=\"mailto:group@example.org\"", membership.formatted());
    }

    @Test
    void testMultipleUrisFromString() {
        Membership membership = Membership.of(
                "mailto:group1@example.org",
                "mailto:group2@example.org"
        );
        assertEquals("MEMBER=\"mailto:group1@example.org\",\"mailto:group2@example.org\"", membership.formatted());
    }

    @Test
    void testInvalidUriStringThrowsException() {
        String invalidUri = "http::: /invalid";
        assertThrows(IllegalArgumentException.class, () -> Membership.of(invalidUri));
    }

    @Test
    void testEmptyInput() {
        Membership membership = Membership.of(new URI[0]);
        assertEquals("MEMBER=", membership.formatted());
    }

    @Test
    void testMixedProtocolUris() {
        Membership membership = Membership.of(
                "mailto:user@example.com",
                "https://example.com/calendar/group",
                "ldap://ldap.example.com/cn=group,ou=groups,dc=example,dc=com"
        );
        assertEquals("MEMBER=\"mailto:user@example.com\",\"https://example.com/calendar/group\",\"ldap://ldap.example.com/cn=group,ou=groups,dc=example,dc=com\"", membership.formatted());
    }

    @Test
    void testGroupMembershipUris() {
        Membership membership = Membership.of(
                "mailto:marketing@example.com",
                "mailto:sales@example.com",
                "mailto:support@example.com"
        );
        assertEquals("MEMBER=\"mailto:marketing@example.com\",\"mailto:sales@example.com\",\"mailto:support@example.com\"", membership.formatted());
    }

    @Test
    void testSingleEmptyArrayFromString() {
        Membership membership = Membership.of(new String[0]);
        assertEquals("MEMBER=", membership.formatted());
    }

    @Test
    void testUriWithSpecialCharacters() {
        Membership membership = Membership.of("mailto:user+tag@example.com");
        assertEquals("MEMBER=\"mailto:user+tag@example.com\"", membership.formatted());
    }
}
