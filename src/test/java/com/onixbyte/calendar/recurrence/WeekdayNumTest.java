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

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the WeekdayNum class.
 */
class WeekdayNumTest {

    @Test
    void testWeekdayNumWithoutOrdinal() {
        WeekdayNum weekdayNum = WeekdayNum.of(DayOfWeek.MONDAY);
        
        assertNotNull(weekdayNum);
        assertEquals("MO", weekdayNum.formatted());
    }

    @Test
    void testWeekdayNumWithPositiveOrdinal() {
        WeekdayNum weekdayNum = WeekdayNum.of(1, DayOfWeek.MONDAY);
        
        assertNotNull(weekdayNum);
        assertEquals("+1MO", weekdayNum.formatted());
    }

    @Test
    void testWeekdayNumWithNegativeOrdinal() {
        WeekdayNum weekdayNum = WeekdayNum.of(-1, DayOfWeek.FRIDAY);
        
        assertNotNull(weekdayNum);
        assertEquals("-1FR", weekdayNum.formatted());
    }

    @Test
    void testWeekdayNumWithAllDaysOfWeek() {
        assertEquals("MO", WeekdayNum.of(DayOfWeek.MONDAY).formatted());
        assertEquals("TU", WeekdayNum.of(DayOfWeek.TUESDAY).formatted());
        assertEquals("WE", WeekdayNum.of(DayOfWeek.WEDNESDAY).formatted());
        assertEquals("TH", WeekdayNum.of(DayOfWeek.THURSDAY).formatted());
        assertEquals("FR", WeekdayNum.of(DayOfWeek.FRIDAY).formatted());
        assertEquals("SA", WeekdayNum.of(DayOfWeek.SATURDAY).formatted());
        assertEquals("SU", WeekdayNum.of(DayOfWeek.SUNDAY).formatted());
    }

    @Test
    void testWeekdayNumWithLargePositiveOrdinal() {
        WeekdayNum weekdayNum = WeekdayNum.of(53, DayOfWeek.SUNDAY);
        
        assertNotNull(weekdayNum);
        assertEquals("+53SU", weekdayNum.formatted());
    }

    @Test
    void testWeekdayNumWithLargeNegativeOrdinal() {
        WeekdayNum weekdayNum = WeekdayNum.of(-53, DayOfWeek.SUNDAY);
        
        assertNotNull(weekdayNum);
        assertEquals("-53SU", weekdayNum.formatted());
    }

    @Test
    void testWeekdayNumWithZeroOrdinalThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            WeekdayNum.of(0, DayOfWeek.MONDAY);
        });
    }

    @Test
    void testWeekdayNumWithOrdinalTooLargeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            WeekdayNum.of(54, DayOfWeek.MONDAY);
        });
    }

    @Test
    void testWeekdayNumWithOrdinalTooSmallThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            WeekdayNum.of(-54, DayOfWeek.MONDAY);
        });
    }

    @Test
    void testWeekdayNumBoundaryValues() {
        // Test boundary values
        assertDoesNotThrow(() -> {
            WeekdayNum.of(53, DayOfWeek.MONDAY);
            WeekdayNum.of(-53, DayOfWeek.MONDAY);
            WeekdayNum.of(1, DayOfWeek.MONDAY);
            WeekdayNum.of(-1, DayOfWeek.MONDAY);
        });
    }

    @Test
    void testWeekdayNumFormattingWithDifferentOrdinals() {
        assertEquals("+2TU", WeekdayNum.of(2, DayOfWeek.TUESDAY).formatted());
        assertEquals("-3WE", WeekdayNum.of(-3, DayOfWeek.WEDNESDAY).formatted());
        assertEquals("+10TH", WeekdayNum.of(10, DayOfWeek.THURSDAY).formatted());
        assertEquals("-25FR", WeekdayNum.of(-25, DayOfWeek.FRIDAY).formatted());
    }
}
