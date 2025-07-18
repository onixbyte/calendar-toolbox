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
import com.onixbyte.calendar.property.Summary;
import com.onixbyte.calendar.property.Description;
import com.onixbyte.calendar.property.Status;
import com.onixbyte.calendar.property.Priority;
import com.onixbyte.calendar.property.PercentComplete;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Todo class.
 */
class TodoTest {

    private DateTimeStamp dateTimeStamp;
    private UniqueIdentifier uniqueIdentifier;
    private Summary summary;
    private Description description;
    private Status status;
    private Priority priority;
    private PercentComplete percentComplete;

    @BeforeEach
    void setUp() {
        ZonedDateTime now = ZonedDateTime.now();
        dateTimeStamp = DateTimeStamp.builder().build(now);
        uniqueIdentifier = UniqueIdentifier.builder().build("test-todo-123");
        summary = Summary.builder().build("Test Todo");
        description = Description.builder().build("Test Todo Description");
        status = Status.NEEDS_ACTION;
        priority = Priority.builder().build(1);
        percentComplete = PercentComplete.builder().build(50);
    }

    @Test
    void testTodoBuilderWithRequiredFields() {
        Todo todo = Todo.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .build();

        assertNotNull(todo);
        String formatted = todo.formatted();
        assertTrue(formatted.contains("BEGIN:VTODO"));
        assertTrue(formatted.contains("END:VTODO"));
        assertTrue(formatted.contains("DTSTAMP:"));
        assertTrue(formatted.contains("UID:test-todo-123"));
    }

    @Test
    void testTodoBuilderWithOptionalFields() {
        Todo todo = Todo.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .withSummary(summary)
                .withDescription(description)
                .withStatus(status)
                .withPriority(priority)
                .withPercentComplete(percentComplete)
                .build();

        assertNotNull(todo);
        String formatted = todo.formatted();
        assertTrue(formatted.contains("BEGIN:VTODO"));
        assertTrue(formatted.contains("END:VTODO"));
        assertTrue(formatted.contains("SUMMARY:Test Todo"));
        assertTrue(formatted.contains("DESCRIPTION:Test Todo Description"));
        assertTrue(formatted.contains("STATUS:NEEDS-ACTION"));
        assertTrue(formatted.contains("PRIORITY:1"));
        assertTrue(formatted.contains("PERCENT-COMPLETE:50"));
    }

    @Test
    void testTodoBuilderThrowsExceptionWhenDateTimeStampIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Todo.builder()
                    .withUniqueIdentifier(uniqueIdentifier)
                    .build();
        });
    }

    @Test
    void testTodoBuilderThrowsExceptionWhenUniqueIdentifierIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Todo.builder()
                    .withDateTimeStamp(dateTimeStamp)
                    .build();
        });
    }

    @Test
    void testTodoBuilderFluentInterface() {
        Todo.TodoBuilder builder = Todo.builder();
        
        assertSame(builder, builder.withDateTimeStamp(dateTimeStamp));
        assertSame(builder, builder.withUniqueIdentifier(uniqueIdentifier));
        assertSame(builder, builder.withSummary(summary));
        assertSame(builder, builder.withDescription(description));
        assertSame(builder, builder.withStatus(status));
        assertSame(builder, builder.withPriority(priority));
        assertSame(builder, builder.withPercentComplete(percentComplete));
    }

    @Test
    void testTodoFormattedStructure() {
        Todo todo = Todo.builder()
                .withDateTimeStamp(dateTimeStamp)
                .withUniqueIdentifier(uniqueIdentifier)
                .withSummary(summary)
                .build();

        String formatted = todo.formatted();
        String[] lines = formatted.split("\n");

        assertEquals("BEGIN:VTODO", lines[0]);
        assertEquals("END:VTODO", lines[lines.length - 1]);
        
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
