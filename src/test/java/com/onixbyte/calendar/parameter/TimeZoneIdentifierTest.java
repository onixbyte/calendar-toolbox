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

import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class TimeZoneIdentifierTest {

    @Test
    void testCreateFromZoneId() {
        ZoneId zoneId = ZoneId.of("America/New_York");
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of(zoneId);
        assertEquals("TZID=America/New_York", tzId.formatted());
    }

    @Test
    void testCreateFromString() {
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of("Europe/London");
        assertEquals("TZID=Europe/London", tzId.formatted());
    }

    @Test
    void testCreateFromUtc() {
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of("UTC");
        assertEquals("TZID=UTC", tzId.formatted());
    }

    @Test
    void testCreateFromGmt() {
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of("GMT");
        assertEquals("TZID=GMT", tzId.formatted());
    }

    @Test
    void testCreateFromOffset() {
        ZoneId zoneId = ZoneOffset.of("+05:30");
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of(zoneId);
        assertEquals("TZID=+05:30", tzId.formatted());
    }

    @Test
    void testCreateFromNegativeOffset() {
        ZoneId zoneId = ZoneOffset.of("-08:00");
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of(zoneId);
        assertEquals("TZID=-08:00", tzId.formatted());
    }

    @Test
    void testCreateFromSystemDefault() {
        ZoneId systemDefault = ZoneId.systemDefault();
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of(systemDefault);
        assertEquals("TZID=" + systemDefault.getId(), tzId.formatted());
    }

    @Test
    void testGetZoneId() {
        ZoneId originalZone = ZoneId.of("Asia/Tokyo");
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of(originalZone);
        assertEquals(originalZone, tzId.getZoneId());
    }

    @Test
    void testGetZoneIdFromString() {
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of("Australia/Sydney");
        assertEquals(ZoneId.of("Australia/Sydney"), tzId.getZoneId());
    }

    @Test
    void testCommonTimeZones() {
        String[] commonTimeZones = {
            "America/New_York",
            "America/Los_Angeles",
            "Europe/London",
            "Europe/Paris",
            "Asia/Tokyo",
            "Asia/Shanghai",
            "Australia/Sydney",
            "Africa/Cairo"
        };

        for (String timeZone : commonTimeZones) {
            TimeZoneIdentifier tzId = TimeZoneIdentifier.of(timeZone);
            assertEquals("TZID=" + timeZone, tzId.formatted());
            assertEquals(ZoneId.of(timeZone), tzId.getZoneId());
        }
    }

    @Test
    void testInvalidTimeZoneStringThrowsException() {
        assertThrows(Exception.class, () -> TimeZoneIdentifier.of("Invalid/TimeZone"));
    }

    @Test
    void testNullStringThrowsException() {
        assertThrows(NullPointerException.class, () -> TimeZoneIdentifier.of((String) null));
    }

    @Test
    void testEmptyStringThrowsException() {
        assertThrows(Exception.class, () -> TimeZoneIdentifier.of(""));
    }

    @Test
    void testIsParameter() {
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of("UTC");
        assertTrue(tzId instanceof Parameter);
    }

    @Test
    void testFormattedStartsWithTzid() {
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of("UTC");
        assertTrue(tzId.formatted().startsWith("TZID="));
    }

    @Test
    void testMultipleInstancesWithSameZone() {
        TimeZoneIdentifier tzId1 = TimeZoneIdentifier.of("UTC");
        TimeZoneIdentifier tzId2 = TimeZoneIdentifier.of("UTC");
        
        assertEquals(tzId1.formatted(), tzId2.formatted());
        assertEquals(tzId1.getZoneId(), tzId2.getZoneId());
    }

    @Test
    void testConsistentBehavior() {
        TimeZoneIdentifier tzId = TimeZoneIdentifier.of("America/New_York");
        
        // Multiple calls should return the same result
        for (int i = 0; i < 10; i++) {
            assertEquals("TZID=America/New_York", tzId.formatted());
            assertEquals(ZoneId.of("America/New_York"), tzId.getZoneId());
        }
    }

    @Test
    void testOffsetTimeZones() {
        String[] offsets = {"+01:00", "+05:30", "+09:00", "-05:00", "-08:00", "-11:00"};
        
        for (String offset : offsets) {
            TimeZoneIdentifier tzId = TimeZoneIdentifier.of(offset);
            assertEquals("TZID=" + offset, tzId.formatted());
            assertEquals(ZoneOffset.of(offset), tzId.getZoneId());
        }
        
        // Special case for +00:00 which is represented as Z
        TimeZoneIdentifier utcTzId = TimeZoneIdentifier.of("+00:00");
        assertEquals("TZID=Z", utcTzId.formatted());
        assertEquals(ZoneOffset.UTC, utcTzId.getZoneId());
    }
}
