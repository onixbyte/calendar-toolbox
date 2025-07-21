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

import com.onixbyte.calendar.component.property.DateTimeStamp;
import com.onixbyte.calendar.component.property.UniqueIdentifier;
import com.onixbyte.calendar.component.property.Summary;
import com.onixbyte.calendar.component.property.Description;
import com.onixbyte.calendar.component.property.Status;
import com.onixbyte.calendar.component.property.Classification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Journal class.
 */
class JournalTest {

    private DateTimeStamp dateTimeStamp;
    private UniqueIdentifier uniqueIdentifier;
    private Summary summary;
    private Description description;
    private Status status;
    private Classification classification;

    @BeforeEach
    void setUp() {
        ZonedDateTime now = ZonedDateTime.now();
        dateTimeStamp = DateTimeStamp.builder().build(now);
        uniqueIdentifier = UniqueIdentifier.builder().build("test-journal-123");
        summary = Summary.builder().build("Test Journal Entry");
        description = Description.builder().build("This is a test journal entry");
        status = Status.DRAFT;
        classification = Classification.PUBLIC;
    }

    @Test
    void testJournalBuilderWithRequiredFields() {
        Journal journal = Journal.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .build();

        assertNotNull(journal);
        String formatted = journal.formatted();
        assertTrue(formatted.contains("BEGIN:VJOURNAL"));
        assertTrue(formatted.contains("END:VJOURNAL"));
        assertTrue(formatted.contains("DTSTAMP:"));
        assertTrue(formatted.contains("UID:test-journal-123"));
    }

    @Test
    void testJournalBuilderWithOptionalFields() {
        Journal journal = Journal.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .withSummary(summary)
                .withDescriptions(description)
                .withStatus(status)
                .withClassification(classification)
                .build();

        assertNotNull(journal);
        String formatted = journal.formatted();
        assertTrue(formatted.contains("BEGIN:VJOURNAL"));
        assertTrue(formatted.contains("END:VJOURNAL"));
        assertTrue(formatted.contains("SUMMARY:Test Journal Entry"));
        assertTrue(formatted.contains("DESCRIPTION:This is a test journal entry"));
        assertTrue(formatted.contains("STATUS:DRAFT"));
        assertTrue(formatted.contains("CLASS:PUBLIC"));
    }

    @Test
    void testJournalBuilderFluentInterface() {
        Journal.JournalBuilder builder = Journal.builder();

        assertSame(builder, builder.withDateTimeStamp(dateTimeStamp));
        assertSame(builder, builder.withUniqueIdentifier(uniqueIdentifier));
        assertSame(builder, builder.withSummary(summary));
        assertSame(builder, builder.withDescriptions(description));
        assertSame(builder, builder.withStatus(status));
        assertSame(builder, builder.withClassification(classification));
    }

    @Test
    void testJournalFormattedStructure() {
        Journal journal = Journal.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .withSummary(summary)
                .build();

        String formatted = journal.formatted();
        String[] lines = formatted.split("\n");

        assertTrue(lines[0].equals("BEGIN:VJOURNAL"));
        assertTrue(lines[lines.length - 1].equals("END:VJOURNAL"));

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
}
