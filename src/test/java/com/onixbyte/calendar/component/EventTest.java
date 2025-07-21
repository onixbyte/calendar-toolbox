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

import com.onixbyte.calendar.property.DateTimeStamp;
import com.onixbyte.calendar.property.UniqueIdentifier;
import com.onixbyte.calendar.property.DateTimeStart;
import com.onixbyte.calendar.property.DateTimeEnd;
import com.onixbyte.calendar.property.Summary;
import com.onixbyte.calendar.property.Description;
import com.onixbyte.calendar.property.Location;
import com.onixbyte.calendar.property.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.ZonedDateTime;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class.
 */
class EventTest {

    private DateTimeStamp dateTimeStamp;
    private UniqueIdentifier uniqueIdentifier;
    private DateTimeStart dateTimeStart;
    private DateTimeEnd dateTimeEnd;
    private Summary summary;
    private Description description;
    private Location location;
    private Status status;

    @BeforeEach
    void setUp() {
        ZonedDateTime now = ZonedDateTime.now();
        dateTimeStamp = DateTimeStamp.builder().build(now);
        uniqueIdentifier = UniqueIdentifier.builder().build("test-uid-123");
        dateTimeStart = DateTimeStart.builder().build(now);
        dateTimeEnd = DateTimeEnd.builder().build(now.plusHours(1));
        summary = Summary.builder().build("Test Event");
        description = Description.builder().build("Test Description");
        location = Location.builder().build("Test Location");
        status = Status.CONFIRMED;
    }

    @Test
    void testEventBuilderWithRequiredFields() {
        Event event = Event.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .build();

        assertNotNull(event);
        String formatted = event.formatted();
        assertTrue(formatted.contains("BEGIN:VEVENT"));
        assertTrue(formatted.contains("END:VEVENT"));
        assertTrue(formatted.contains("DTSTAMP:"));
        assertTrue(formatted.contains("UID:test-uid-123"));
    }

    @Test
    void testEventBuilderWithOptionalFields() {
        Event event = Event.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .withDateTimeStart(dateTimeStart)
                .withDateTimeEnd(dateTimeEnd)
                .withSummary(summary)
                .withDescription(description)
                .withLocation(location)
                .withStatus(status)
                .build();

        assertNotNull(event);
        String formatted = event.formatted();
        assertTrue(formatted.contains("BEGIN:VEVENT"));
        assertTrue(formatted.contains("END:VEVENT"));
        assertTrue(formatted.contains("SUMMARY:Test Event"));
        assertTrue(formatted.contains("DESCRIPTION:Test Description"));
        assertTrue(formatted.contains("LOCATION:Test Location"));
        assertTrue(formatted.contains("STATUS:CONFIRMED"));
    }

    @Test
    void testEventBuilderThrowsExceptionWhenDateTimeStampIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Event.builder()
                    .withUniqueIdentifier(uniqueIdentifier)
                    .build();
        });
    }

    @Test
    void testEventBuilderThrowsExceptionWhenUniqueIdentifierIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Event.builder()
                    .withDateTimeStamp(dateTimeStamp)
                    .build();
        });
    }

    @Test
    void testEventBuilderThrowsExceptionWhenBothDateTimeEndAndDurationAreSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            Event.builder()
                    .withDateTimeStamp(dateTimeStamp)
                    .withUniqueIdentifier(uniqueIdentifier)
                    .withDateTimeEnd(dateTimeEnd)
                    .withDuration(Duration.ofHours(1))
                    .build();
        });
    }

    @Test
    void testEventBuilderFluentInterface() {
        Event.EventBuilder builder = Event.builder();
        
        assertSame(builder, builder.withDateTimeStamp(dateTimeStamp));
        assertSame(builder, builder.withUniqueIdentifier(uniqueIdentifier));
        assertSame(builder, builder.withDateTimeStart(dateTimeStart));
        assertSame(builder, builder.withDateTimeEnd(dateTimeEnd));
        assertSame(builder, builder.withSummary(summary));
        assertSame(builder, builder.withDescription(description));
        assertSame(builder, builder.withLocation(location));
        assertSame(builder, builder.withStatus(status));
    }

    @Test
    void testEventFormattedStructure() {
        Event event = Event.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .withSummary(summary)
                .build();

        String formatted = event.formatted();
        String[] lines = formatted.split("\n");
        
        assertTrue(lines[0].equals("BEGIN:VEVENT"));
        assertTrue(lines[lines.length - 1].equals("END:VEVENT"));
        
        // Check that required properties are present
        boolean hasDtStamp = false, hasUid = false, hasSummary = false;
        for (String line : lines) {
            if (line.startsWith("DTSTAMP:")) hasDtStamp = true;
            if (line.startsWith("UID:")) hasUid = true;
            if (line.startsWith("SUMMARY:")) hasSummary = true;
        }
        
        assertTrue(hasDtStamp, "Missing DTSTAMP");
        assertTrue(hasUid, "Missing UID");
        assertTrue(hasSummary, "Missing SUMMARY");
    }

    @Test
    void testEventWithDuration() {
        Event event = Event.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .withDateTimeStart(dateTimeStart)
                .withDuration(Duration.ofHours(2))
                .build();

        String formatted = event.formatted();
        assertTrue(formatted.contains("DURATION:PT2H"));
        assertFalse(formatted.contains("DTEND:"));
    }

    @Test
    void testEventWithDateTimeEnd() {
        Event event = Event.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .withDateTimeStart(dateTimeStart)
                .withDateTimeEnd(dateTimeEnd)
                .build();

        String formatted = event.formatted();
        assertTrue(formatted.contains("DTEND:"));
        assertFalse(formatted.contains("DURATION:"));
    }
}
