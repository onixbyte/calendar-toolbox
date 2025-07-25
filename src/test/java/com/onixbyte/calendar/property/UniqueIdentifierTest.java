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

import com.onixbyte.calendar.component.property.UniqueIdentifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UniqueIdentifier class.
 */
class UniqueIdentifierTest {

    @Test
    void testUniqueIdentifierCreation() {
        UniqueIdentifier uid = UniqueIdentifier.builder().build("test-uid-123");
        
        assertNotNull(uid);
    }

    @Test
    void testUniqueIdentifierFormatting() {
        UniqueIdentifier uid = UniqueIdentifier.builder().build("test-uid-123");
        
        String formatted = uid.formatted();
        assertEquals("UID:test-uid-123", formatted);
    }

    @Test
    void testUniqueIdentifierFormattingWithSpecialCharacters() {
        UniqueIdentifier uid = UniqueIdentifier.builder().build("test@example.com");
        
        String formatted = uid.formatted();
        assertEquals("UID:test@example.com", formatted);
    }

    @Test
    void testUniqueIdentifierFormattingWithEmptyString() {
        UniqueIdentifier uid = UniqueIdentifier.builder().build("");
        
        String formatted = uid.formatted();
        assertEquals("UID:", formatted);
    }

    @Test
    void testUniqueIdentifierBuilder() {
        UniqueIdentifier.UniqueIdentifierBuilder builder = UniqueIdentifier.builder();
        assertNotNull(builder);
        
        UniqueIdentifier uid = builder.build("test-uid");
        assertNotNull(uid);
    }

    @Test
    void testUniqueIdentifierBuilderReturnsNewInstance() {
        UniqueIdentifier.UniqueIdentifierBuilder builder1 = UniqueIdentifier.builder();
        UniqueIdentifier.UniqueIdentifierBuilder builder2 = UniqueIdentifier.builder();
        
        assertNotSame(builder1, builder2);
    }

    @Test
    void testUniqueIdentifierFormattingWithLongString() {
        String longUid = "very-long-unique-identifier-with-many-characters-and-numbers-123456789";
        UniqueIdentifier uid = UniqueIdentifier.builder().build(longUid);
        
        String formatted = uid.formatted();
        assertEquals("UID:" + longUid, formatted);
    }

    @Test
    void testUniqueIdentifierFormattingWithUUIDFormat() {
        String uuidString = "550e8400-e29b-41d4-a716-446655440000";
        UniqueIdentifier uid = UniqueIdentifier.builder().build(uuidString);
        
        String formatted = uid.formatted();
        assertEquals("UID:" + uuidString, formatted);
    }
}
