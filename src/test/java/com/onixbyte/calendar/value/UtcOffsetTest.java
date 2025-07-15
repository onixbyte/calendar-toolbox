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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtcOffsetTest {

    @Test
    public void testPositiveUtcOffsetWithoutSeconds() {
        UtcOffset offset = UtcOffset.ofPositive(5, 30);
        assertEquals("+0530", offset.formatted());
    }

    @Test
    public void testPositiveUtcOffsetWithSecondsLessThan10() {
        UtcOffset offset = UtcOffset.ofPositive(5, 30, 5);
        assertEquals("+053005", offset.formatted());
    }

    @Test
    public void testPositiveUtcOffsetWithSeconds() {
        UtcOffset offset = UtcOffset.ofPositive(5, 30, 15);
        assertEquals("+053015", offset.formatted());
    }

    @Test
    public void testNegativeUtcOffsetWithoutSeconds() {
        UtcOffset offset = UtcOffset.ofNegative(2, 0);
        assertEquals("-0200", offset.formatted());
    }

    @Test
    public void testNegativeUtcOffsetWithSeconds() {
        UtcOffset offset = UtcOffset.ofNegative(2, 0, 45);
        assertEquals("-020045", offset.formatted());
    }

    @Test
    public void testInvalidNegativeZeroOffsetThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                UtcOffset.ofNegative(0, 0, 0));
        assertEquals("The value of \"-0000\" and \"-000000\" are not allowed.", ex.getMessage());
    }

    @Test
    public void testInvalidHourTooHighThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                UtcOffset.ofPositive(13, 0));
        assertEquals("Hour MUST between 0 and 12.", ex.getMessage());
    }

    @Test
    public void testInvalidHourNegativeThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                UtcOffset.ofPositive(-1, 0));
        assertEquals("Hour MUST between 0 and 12.", ex.getMessage());
    }

    @Test
    public void testInvalidMinuteTooHighThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                UtcOffset.ofPositive(1, 60));
        assertEquals("Minute MUST between 0 and 59.", ex.getMessage());
    }

    @Test
    public void testInvalidMinuteNegativeThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                UtcOffset.ofPositive(1, -1));
        assertEquals("Minute MUST between 0 and 59.", ex.getMessage());
    }

    @Test
    public void testInvalidSecondTooHighThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                UtcOffset.ofPositive(1, 1, 60));
        assertEquals("Second MUST between 0 and 59.", ex.getMessage());
    }

    @Test
    public void testInvalidSecondNegativeThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                UtcOffset.ofPositive(1, 1, -1));
        assertEquals("Second MUST between 0 and 59.", ex.getMessage());
    }

    @Test
    public void testUtcOffsetWithNullSecond() {
        UtcOffset offset = UtcOffset.ofPositive(6, 15, null);
        assertEquals("+0615", offset.formatted());
    }
}
