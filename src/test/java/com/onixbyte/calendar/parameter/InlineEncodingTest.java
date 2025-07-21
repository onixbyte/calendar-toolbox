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

class InlineEncodingTest {

    @Test
    void testEightBitFormatted() {
        InlineEncoding encoding = InlineEncoding.EIGHT_BIT;
        assertEquals("ENCODING=8BIT", encoding.formatted());
    }

    @Test
    void testBase64Formatted() {
        InlineEncoding encoding = InlineEncoding.BASE_64;
        assertEquals("ENCODING=BASE64", encoding.formatted());
    }

    @Test
    void testAllValues() {
        InlineEncoding[] values = InlineEncoding.values();
        assertEquals(2, values.length);
        
        // Verify all enum values are present
        boolean hasEightBit = false, hasBase64 = false;
        for (InlineEncoding value : values) {
            switch (value) {
                case EIGHT_BIT -> hasEightBit = true;
                case BASE_64 -> hasBase64 = true;
            }
        }
        
        assertTrue(hasEightBit);
        assertTrue(hasBase64);
    }

    @Test
    void testValueOf() {
        assertEquals(InlineEncoding.EIGHT_BIT, InlineEncoding.valueOf("EIGHT_BIT"));
        assertEquals(InlineEncoding.BASE_64, InlineEncoding.valueOf("BASE_64"));
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> InlineEncoding.valueOf("INVALID"));
    }

    @Test
    void testEnumInstancesAreParameters() {
        for (InlineEncoding encoding : InlineEncoding.values()) {
            assertTrue(encoding instanceof Parameter);
            assertNotNull(encoding.formatted());
            assertTrue(encoding.formatted().startsWith("ENCODING="));
        }
    }

    @Test
    void testLabelsAreCorrect() {
        assertEquals("ENCODING=8BIT", InlineEncoding.EIGHT_BIT.formatted());
        assertEquals("ENCODING=BASE64", InlineEncoding.BASE_64.formatted());
    }
}
