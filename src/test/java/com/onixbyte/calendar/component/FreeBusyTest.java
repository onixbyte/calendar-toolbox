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
import com.onixbyte.calendar.property.Contact;
import com.onixbyte.calendar.property.DateTimeStart;
import com.onixbyte.calendar.property.DateTimeEnd;
import com.onixbyte.calendar.property.Organiser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the FreeBusy class.
 */
class FreeBusyTest {

    private DateTimeStamp dateTimeStamp;
    private UniqueIdentifier uniqueIdentifier;
    private Contact contact;
    private DateTimeStart dateTimeStart;
    private DateTimeEnd dateTimeEnd;
    private Organiser organiser;

    @BeforeEach
    void setUp() {
        ZonedDateTime now = ZonedDateTime.now();
        dateTimeStamp = DateTimeStamp.builder().build(now);
        uniqueIdentifier = UniqueIdentifier.builder().build("test-freebusy-123");
        contact = Contact.builder().build("John Doe");
        dateTimeStart = DateTimeStart.builder().build(now);
        dateTimeEnd = DateTimeEnd.builder().build(now.plusHours(8));
        organiser = Organiser.builder().build("mailto:organizer@example.com");
    }

    @Test
    void testFreeBusyBuilderWithRequiredFields() {
        FreeBusy freeBusy = FreeBusy.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .build();

        assertNotNull(freeBusy);
        String formatted = freeBusy.formatted();
        assertTrue(formatted.contains("BEGIN:VFREEBUSY"));
        assertTrue(formatted.contains("END:VFREEBUSY"));
        assertTrue(formatted.contains("DTSTAMP:"));
        assertTrue(formatted.contains("UID:test-freebusy-123"));
    }

    @Test
    void testFreeBusyBuilderWithOptionalFields() {
        FreeBusy freeBusy = FreeBusy.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .withContact(contact)
                .withDateTimeStart(dateTimeStart)
                .withDateTimeEnd(dateTimeEnd)
                .withOrganiser(organiser)
                .build();

        assertNotNull(freeBusy);
        String formatted = freeBusy.formatted();
        assertTrue(formatted.contains("BEGIN:VFREEBUSY"));
        assertTrue(formatted.contains("END:VFREEBUSY"));
        assertTrue(formatted.contains("CONTACT:John Doe"));
        assertTrue(formatted.contains("ORGANIZER:mailto:organizer@example.com"));
    }

    @Test
    void testFreeBusyBuilderFluentInterface() {
        FreeBusy.FreeBusyBuilder builder = FreeBusy.builder();
        
        assertSame(builder, builder.withDateTimeStamp(dateTimeStamp));
        assertSame(builder, builder.withUniqueIdentifier(uniqueIdentifier));
        assertSame(builder, builder.withContact(contact));
        assertSame(builder, builder.withDateTimeStart(dateTimeStart));
        assertSame(builder, builder.withDateTimeEnd(dateTimeEnd));
        assertSame(builder, builder.withOrganiser(organiser));
    }

    @Test
    void testFreeBusyFormattedStructure() {
        FreeBusy freeBusy = FreeBusy.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .build();

        String formatted = freeBusy.formatted();
        String[] lines = formatted.split("\n");
        
        assertTrue(lines[0].equals("BEGIN:VFREEBUSY"));
        assertTrue(lines[lines.length - 1].equals("END:VFREEBUSY"));
        
        // Check that required properties are present
        boolean hasDtStamp = false, hasUid = false;
        for (String line : lines) {
            if (line.startsWith("DTSTAMP:")) hasDtStamp = true;
            if (line.startsWith("UID:")) hasUid = true;
        }
        
        assertTrue(hasDtStamp, "Missing DTSTAMP");
        assertTrue(hasUid, "Missing UID");
    }
}
