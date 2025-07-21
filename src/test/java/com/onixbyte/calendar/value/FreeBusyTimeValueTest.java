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

package com.onixbyte.calendar.value;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the FreeBusyTimeValue class.
 */
class FreeBusyTimeValueTest {

    private ZonedDateTime startTime;
    private Duration duration;

    @BeforeEach
    void setUp() {
        startTime = ZonedDateTime.of(2024, 1, 1, 10, 0, 0, 0, ZoneId.of("UTC"));
        duration = Duration.ofHours(1);
    }

    @Test
    void testFreeBusyTimeValueCreation() {
        FreeBusyTimeValue value = FreeBusyTimeValue.of(startTime, duration);
        
        assertNotNull(value);
    }

    @Test
    void testFreeBusyTimeValueFormatting() {
        FreeBusyTimeValue value = FreeBusyTimeValue.of(startTime, duration);
        
        String formatted = value.formatted();
        assertNotNull(formatted);
        assertTrue(formatted.contains("/"), "FreeBusyTimeValue should contain '/' separator");
        assertTrue(formatted.contains("20240101T100000Z"), "FreeBusyTimeValue should contain start time");
        assertTrue(formatted.contains("PT1H"), "FreeBusyTimeValue should contain duration");
    }

    @Test
    void testFreeBusyTimeValueFormattingStructure() {
        FreeBusyTimeValue value = FreeBusyTimeValue.of(startTime, duration);
        
        String formatted = value.formatted();
        String[] parts = formatted.split("/");
        
        assertEquals(2, parts.length, "FreeBusyTimeValue should have exactly 2 parts separated by '/'");
        assertEquals("20240101T100000Z", parts[0], "First part should be start time");
        assertEquals("PT1H", parts[1], "Second part should be duration");
    }

    @Test
    void testFreeBusyTimeValueWithDifferentDuration() {
        Duration twoHours = Duration.ofHours(2);
        FreeBusyTimeValue value = FreeBusyTimeValue.of(startTime, twoHours);
        
        String formatted = value.formatted();
        assertTrue(formatted.contains("PT2H"), "FreeBusyTimeValue should contain 2 hour duration");
    }

    @Test
    void testFreeBusyTimeValueWithMinutes() {
        Duration thirtyMinutes = Duration.ofMinutes(30);
        FreeBusyTimeValue value = FreeBusyTimeValue.of(startTime, thirtyMinutes);
        
        String formatted = value.formatted();
        assertTrue(formatted.contains("PT30M"), "FreeBusyTimeValue should contain 30 minute duration");
    }

    @Test
    void testFreeBusyTimeValueWithDifferentTimeZone() {
        ZonedDateTime estTime = ZonedDateTime.of(2024, 1, 1, 5, 0, 0, 0, ZoneId.of("America/New_York"));
        FreeBusyTimeValue value = FreeBusyTimeValue.of(estTime, duration);
        
        String formatted = value.formatted();
        assertNotNull(formatted);
        assertTrue(formatted.contains("/"), "FreeBusyTimeValue should contain '/' separator");
        assertTrue(formatted.contains("PT1H"), "FreeBusyTimeValue should contain duration");
    }

    @Test
    void testFreeBusyTimeValueWithComplexDuration() {
        Duration complex = Duration.ofHours(1).plusMinutes(30);
        FreeBusyTimeValue value = FreeBusyTimeValue.of(startTime, complex);
        
        String formatted = value.formatted();
        assertTrue(formatted.contains("PT1H30M"), "FreeBusyTimeValue should contain complex duration");
    }
}
