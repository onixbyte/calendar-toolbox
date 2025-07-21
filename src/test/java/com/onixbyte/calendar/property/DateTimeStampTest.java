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

package com.onixbyte.calendar.property;

import com.onixbyte.calendar.component.property.DateTimeStamp;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DateTimeStamp class.
 */
class DateTimeStampTest {

    @Test
    void testDateTimeStampCreation() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeStamp dateTimeStamp = DateTimeStamp.builder().build(now);
        
        assertNotNull(dateTimeStamp);
    }

    @Test
    void testDateTimeStampFormatting() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeStamp dateTimeStamp = DateTimeStamp.builder().build(now);
        
        String formatted = dateTimeStamp.formatted();
        assertNotNull(formatted);
        assertTrue(formatted.startsWith("DTSTAMP:"));
    }

    @Test
    void testDateTimeStampFormattingWithSpecificTime() {
        ZonedDateTime specificTime = ZonedDateTime.parse("2024-01-01T10:00:00Z");
        DateTimeStamp dateTimeStamp = DateTimeStamp.builder().build(specificTime);
        
        String formatted = dateTimeStamp.formatted();
        assertEquals("DTSTAMP:20240101T100000Z", formatted);
    }

    @Test
    void testDateTimeStampBuilder() {
        DateTimeStamp.DateTimeStampBuilder builder = DateTimeStamp.builder();
        assertNotNull(builder);
        
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeStamp dateTimeStamp = builder.build(now);
        assertNotNull(dateTimeStamp);
    }

    @Test
    void testDateTimeStampBuilderReturnsNewInstance() {
        DateTimeStamp.DateTimeStampBuilder builder1 = DateTimeStamp.builder();
        DateTimeStamp.DateTimeStampBuilder builder2 = DateTimeStamp.builder();
        
        assertNotSame(builder1, builder2);
    }
}
