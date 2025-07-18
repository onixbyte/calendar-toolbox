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

package com.onixbyte.calendar.recurrence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Frequency enum.
 */
class FrequencyTest {

    @Test
    void testFrequencyValues() {
        Frequency[] frequencies = Frequency.values();
        
        assertEquals(7, frequencies.length, "Should have 7 frequency values");
        
        assertEquals(Frequency.SECONDLY, frequencies[0]);
        assertEquals(Frequency.MINUTELY, frequencies[1]);
        assertEquals(Frequency.HOURLY, frequencies[2]);
        assertEquals(Frequency.DAILY, frequencies[3]);
        assertEquals(Frequency.WEEKLY, frequencies[4]);
        assertEquals(Frequency.MONTHLY, frequencies[5]);
        assertEquals(Frequency.YEARLY, frequencies[6]);
    }

    @Test
    void testFrequencyValueOf() {
        assertEquals(Frequency.SECONDLY, Frequency.valueOf("SECONDLY"));
        assertEquals(Frequency.MINUTELY, Frequency.valueOf("MINUTELY"));
        assertEquals(Frequency.HOURLY, Frequency.valueOf("HOURLY"));
        assertEquals(Frequency.DAILY, Frequency.valueOf("DAILY"));
        assertEquals(Frequency.WEEKLY, Frequency.valueOf("WEEKLY"));
        assertEquals(Frequency.MONTHLY, Frequency.valueOf("MONTHLY"));
        assertEquals(Frequency.YEARLY, Frequency.valueOf("YEARLY"));
    }

    @Test
    void testFrequencyValueOfInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Frequency.valueOf("INVALID");
        });
    }

    @Test
    void testFrequencyToString() {
        assertEquals("SECONDLY", Frequency.SECONDLY.toString());
        assertEquals("MINUTELY", Frequency.MINUTELY.toString());
        assertEquals("HOURLY", Frequency.HOURLY.toString());
        assertEquals("DAILY", Frequency.DAILY.toString());
        assertEquals("WEEKLY", Frequency.WEEKLY.toString());
        assertEquals("MONTHLY", Frequency.MONTHLY.toString());
        assertEquals("YEARLY", Frequency.YEARLY.toString());
    }

    @Test
    void testFrequencyName() {
        assertEquals("SECONDLY", Frequency.SECONDLY.name());
        assertEquals("MINUTELY", Frequency.MINUTELY.name());
        assertEquals("HOURLY", Frequency.HOURLY.name());
        assertEquals("DAILY", Frequency.DAILY.name());
        assertEquals("WEEKLY", Frequency.WEEKLY.name());
        assertEquals("MONTHLY", Frequency.MONTHLY.name());
        assertEquals("YEARLY", Frequency.YEARLY.name());
    }

    @Test
    void testFrequencyOrdinal() {
        assertEquals(0, Frequency.SECONDLY.ordinal());
        assertEquals(1, Frequency.MINUTELY.ordinal());
        assertEquals(2, Frequency.HOURLY.ordinal());
        assertEquals(3, Frequency.DAILY.ordinal());
        assertEquals(4, Frequency.WEEKLY.ordinal());
        assertEquals(5, Frequency.MONTHLY.ordinal());
        assertEquals(6, Frequency.YEARLY.ordinal());
    }
}
