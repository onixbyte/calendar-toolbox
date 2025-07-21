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

class FreeBusyTimeTypeTest {

    @Test
    void testFreeFormatted() {
        FreeBusyTimeType freeBusyType = FreeBusyTimeType.FREE;
        assertEquals("FBTYPE=FREE", freeBusyType.formatted());
    }

    @Test
    void testBusyFormatted() {
        FreeBusyTimeType freeBusyType = FreeBusyTimeType.BUSY;
        assertEquals("FBTYPE=BUSY", freeBusyType.formatted());
    }

    @Test
    void testBusyUnavailableFormatted() {
        FreeBusyTimeType freeBusyType = FreeBusyTimeType.BUSY_UNAVAILABLE;
        assertEquals("FBTYPE=BUSY-UNAVAILABLE", freeBusyType.formatted());
    }

    @Test
    void testBusyTentativeFormatted() {
        FreeBusyTimeType freeBusyType = FreeBusyTimeType.BUSY_TENTATIVE;
        assertEquals("FBTYPE=BUSY-TENTATIVE", freeBusyType.formatted());
    }

    @Test
    void testAllValues() {
        FreeBusyTimeType[] values = FreeBusyTimeType.values();
        assertEquals(4, values.length);
        
        // Verify all enum values are present
        boolean hasFree = false, hasBusy = false, hasBusyUnavailable = false, hasBusyTentative = false;
        for (FreeBusyTimeType value : values) {
            switch (value) {
                case FREE -> hasFree = true;
                case BUSY -> hasBusy = true;
                case BUSY_UNAVAILABLE -> hasBusyUnavailable = true;
                case BUSY_TENTATIVE -> hasBusyTentative = true;
            }
        }
        
        assertTrue(hasFree);
        assertTrue(hasBusy);
        assertTrue(hasBusyUnavailable);
        assertTrue(hasBusyTentative);
    }

    @Test
    void testValueOf() {
        assertEquals(FreeBusyTimeType.FREE, FreeBusyTimeType.valueOf("FREE"));
        assertEquals(FreeBusyTimeType.BUSY, FreeBusyTimeType.valueOf("BUSY"));
        assertEquals(FreeBusyTimeType.BUSY_UNAVAILABLE, FreeBusyTimeType.valueOf("BUSY_UNAVAILABLE"));
        assertEquals(FreeBusyTimeType.BUSY_TENTATIVE, FreeBusyTimeType.valueOf("BUSY_TENTATIVE"));
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FreeBusyTimeType.valueOf("INVALID"));
    }

    @Test
    void testEnumInstancesAreParameters() {
        for (FreeBusyTimeType type : FreeBusyTimeType.values()) {
            assertTrue(type instanceof Parameter);
            assertNotNull(type.formatted());
            assertTrue(type.formatted().startsWith("FBTYPE="));
        }
    }
}
