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

import static org.junit.jupiter.api.Assertions.*;

class RecurrenceIdentifierRangeTest {

    @Test
    void testSingletonInstance() {
        RecurrenceIdentifierRange range1 = RecurrenceIdentifierRange.of();
        RecurrenceIdentifierRange range2 = RecurrenceIdentifierRange.of();
        
        assertSame(range1, range2);
    }

    @Test
    void testFormattedOutput() {
        RecurrenceIdentifierRange range = RecurrenceIdentifierRange.of();
        assertEquals("RANGE=THISANDFUTURE", range.formatted());
    }

    @Test
    void testIsParameter() {
        RecurrenceIdentifierRange range = RecurrenceIdentifierRange.of();
        assertTrue(range instanceof Parameter);
    }

    @Test
    void testMultipleCallsReturnSameInstance() {
        RecurrenceIdentifierRange range1 = RecurrenceIdentifierRange.of();
        RecurrenceIdentifierRange range2 = RecurrenceIdentifierRange.of();
        RecurrenceIdentifierRange range3 = RecurrenceIdentifierRange.of();
        
        assertSame(range1, range2);
        assertSame(range2, range3);
        assertSame(range1, range3);
    }

    @Test
    void testFormattedIsConsistent() {
        RecurrenceIdentifierRange range1 = RecurrenceIdentifierRange.of();
        RecurrenceIdentifierRange range2 = RecurrenceIdentifierRange.of();
        
        assertEquals(range1.formatted(), range2.formatted());
        assertEquals("RANGE=THISANDFUTURE", range1.formatted());
        assertEquals("RANGE=THISANDFUTURE", range2.formatted());
    }

    @Test
    void testThreadSafety() throws InterruptedException {
        RecurrenceIdentifierRange[] results = new RecurrenceIdentifierRange[100];
        Thread[] threads = new Thread[100];
        
        for (int i = 0; i < 100; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                results[index] = RecurrenceIdentifierRange.of();
            });
        }
        
        for (Thread thread : threads) {
            thread.start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        // All results should be the same instance
        RecurrenceIdentifierRange first = results[0];
        for (int i = 1; i < results.length; i++) {
            assertSame(first, results[i]);
        }
    }

    @Test
    void testConstantValue() {
        RecurrenceIdentifierRange range = RecurrenceIdentifierRange.of();
        
        // Test that the formatted value is always the same
        for (int i = 0; i < 10; i++) {
            assertEquals("RANGE=THISANDFUTURE", range.formatted());
        }
    }
}
