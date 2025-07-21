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

package com.onixbyte.calendar.value;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PeriodOfTime class.
 */
class PeriodOfTimeTest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;

    @BeforeEach
    void setUp() {
        startTime = LocalDateTime.of(2024, 1, 1, 10, 0, 0);
        endTime = LocalDateTime.of(2024, 1, 1, 11, 0, 0);
        duration = Duration.ofHours(1);
    }

    @Test
    void testPeriodOfTimeExplicit() {
        PeriodOfTime period = PeriodOfTime.ofExplicit(startTime, endTime);
        
        assertNotNull(period);
        String formatted = period.formatted();
        assertNotNull(formatted);
        assertTrue(formatted.contains("/"), "Period should contain '/' separator");
        assertTrue(formatted.contains("20240101T100000Z"), "Period should contain start time");
        assertTrue(formatted.contains("20240101T110000Z"), "Period should contain end time");
    }

    @Test
    void testPeriodOfTimeStart() {
        PeriodOfTime period = PeriodOfTime.ofStart(startTime, duration);
        
        assertNotNull(period);
        String formatted = period.formatted();
        assertNotNull(formatted);
        assertTrue(formatted.contains("/"), "Period should contain '/' separator");
        assertTrue(formatted.contains("20240101T100000"), "Period should contain start time");
        assertTrue(formatted.contains("PT1H"), "Period should contain duration");
    }

    @Test
    void testPeriodOfTimeExplicitFormatting() {
        PeriodOfTime period = PeriodOfTime.ofExplicit(startTime, endTime);
        
        String formatted = period.formatted();
        String[] parts = formatted.split("/");
        
        assertEquals(2, parts.length, "Period should have exactly 2 parts separated by '/'");
        assertEquals("20240101T100000Z", parts[0], "First part should be start time");
        assertEquals("20240101T110000Z", parts[1], "Second part should be end time");
    }

    @Test
    void testPeriodOfTimeStartFormatting() {
        PeriodOfTime period = PeriodOfTime.ofStart(startTime, duration);
        
        String formatted = period.formatted();
        String[] parts = formatted.split("/");
        
        assertEquals(2, parts.length, "Period should have exactly 2 parts separated by '/'");
        assertEquals("20240101T100000Z", parts[0], "First part should be start time");
        assertEquals("PT1H", parts[1], "Second part should be duration");
    }

    @Test
    void testPeriodOfTimeWithDifferentDuration() {
        Duration twoHours = Duration.ofHours(2);
        PeriodOfTime period = PeriodOfTime.ofStart(startTime, twoHours);
        
        String formatted = period.formatted();
        assertTrue(formatted.contains("PT2H"), "Period should contain 2 hour duration");
    }

    @Test
    void testPeriodOfTimeWithMinutes() {
        Duration thirtyMinutes = Duration.ofMinutes(30);
        PeriodOfTime period = PeriodOfTime.ofStart(startTime, thirtyMinutes);
        
        String formatted = period.formatted();
        assertTrue(formatted.contains("PT30M"), "Period should contain 30 minute duration");
    }

    @Test
    void testPeriodOfTimeExplicitWithDifferentTimes() {
        LocalDateTime newEndTime = LocalDateTime.of(2024, 1, 1, 14, 30, 0);
        PeriodOfTime period = PeriodOfTime.ofExplicit(startTime, newEndTime);
        
        String formatted = period.formatted();
        assertTrue(formatted.contains("20240101T100000Z"), "Period should contain start time");
        assertTrue(formatted.contains("20240101T143000Z"), "Period should contain end time");
    }
}
