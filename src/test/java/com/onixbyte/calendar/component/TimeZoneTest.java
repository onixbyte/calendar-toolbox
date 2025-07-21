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

package com.onixbyte.calendar.component;

import com.onixbyte.calendar.component.property.TimeZoneIdentifier;
import com.onixbyte.calendar.component.property.LastModified;
import com.onixbyte.calendar.component.property.TimeZoneUrl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TimeZone class.
 */
class TimeZoneTest {

    private TimeZoneIdentifier timeZoneIdentifier;
    private LastModified lastModified;
    private TimeZoneUrl timeZoneUrl;

    @BeforeEach
    void setUp() {
        ZonedDateTime now = ZonedDateTime.now();
        timeZoneIdentifier = TimeZoneIdentifier.builder().build("America/New_York");
        lastModified = LastModified.builder().build(now);
        timeZoneUrl = TimeZoneUrl.builder().build("http://example.com/timezone");
    }

    @Test
    void testTimeZoneBuilderWithRequiredFields() {
        TimeZone timeZone = TimeZone.builder()
                .withTimeZoneIdentifier(timeZoneIdentifier)
                .build();

        assertNotNull(timeZone);
        String formatted = timeZone.formatted();
        assertTrue(formatted.contains("BEGIN:VTIMEZONE"));
        assertTrue(formatted.contains("END:VTIMEZONE"));
        assertTrue(formatted.contains("TZID:America/New_York"));
    }

    @Test
    void testTimeZoneBuilderWithOptionalFields() {
        TimeZone timeZone = TimeZone.builder()
                .withTimeZoneIdentifier(timeZoneIdentifier)
                .withLastModified(lastModified)
                .withTimeZoneUrl(timeZoneUrl)
                .build();

        assertNotNull(timeZone);
        String formatted = timeZone.formatted();
        assertTrue(formatted.contains("BEGIN:VTIMEZONE"));
        assertTrue(formatted.contains("END:VTIMEZONE"));
        assertTrue(formatted.contains("TZID:America/New_York"));
        assertTrue(formatted.contains("LAST-MODIFIED:"));
        assertTrue(formatted.contains("TZURL:http://example.com/timezone"));
    }

    @Test
    void testTimeZoneBuilderFluentInterface() {
        TimeZone.TimeZoneBuilder builder = TimeZone.builder();
        
        assertSame(builder, builder.withTimeZoneIdentifier(timeZoneIdentifier));
        assertSame(builder, builder.withLastModified(lastModified));
        assertSame(builder, builder.withTimeZoneUrl(timeZoneUrl));
    }

    @Test
    void testTimeZoneFormattedStructure() {
        TimeZone timeZone = TimeZone.builder()
                .withTimeZoneIdentifier(timeZoneIdentifier)
                .build();

        String formatted = timeZone.formatted();
        String[] lines = formatted.split("\n");
        
        assertTrue(lines[0].equals("BEGIN:VTIMEZONE"));
        assertTrue(lines[lines.length - 1].equals("END:VTIMEZONE"));
        
        // Check that required properties are present
        boolean hasTzId = false;
        for (String line : lines) {
            if (line.startsWith("TZID:")) hasTzId = true;
        }
        
        assertTrue(hasTzId, "Missing TZID");
    }
}
