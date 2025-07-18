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

class RsvpExpectationTest {

    @Test
    void testRsvpTrueFormatted() {
        RsvpExpectation rsvp = RsvpExpectation.of(true);
        assertEquals("RSVP=TRUE", rsvp.formatted());
    }

    @Test
    void testRsvpFalseFormatted() {
        RsvpExpectation rsvp = RsvpExpectation.of(false);
        assertEquals("RSVP=FALSE", rsvp.formatted());
    }

    @Test
    void testMultipleInstancesWithSameValueAreEqual() {
        RsvpExpectation rsvp1 = RsvpExpectation.of(true);
        RsvpExpectation rsvp2 = RsvpExpectation.of(true);
        
        assertEquals(rsvp1.formatted(), rsvp2.formatted());
    }

    @Test
    void testMultipleInstancesWithDifferentValuesAreDifferent() {
        RsvpExpectation rsvpTrue = RsvpExpectation.of(true);
        RsvpExpectation rsvpFalse = RsvpExpectation.of(false);
        
        assertNotEquals(rsvpTrue.formatted(), rsvpFalse.formatted());
    }

    @Test
    void testIsParameter() {
        RsvpExpectation rsvp = RsvpExpectation.of(true);
        assertTrue(rsvp instanceof Parameter);
    }

    @Test
    void testFormattedIsUpperCase() {
        RsvpExpectation rsvpTrue = RsvpExpectation.of(true);
        RsvpExpectation rsvpFalse = RsvpExpectation.of(false);
        
        assertTrue(rsvpTrue.formatted().contains("TRUE"));
        assertTrue(rsvpFalse.formatted().contains("FALSE"));
        assertFalse(rsvpTrue.formatted().contains("true"));
        assertFalse(rsvpFalse.formatted().contains("false"));
    }

    @Test
    void testFormattedStartsWithRsvp() {
        RsvpExpectation rsvpTrue = RsvpExpectation.of(true);
        RsvpExpectation rsvpFalse = RsvpExpectation.of(false);
        
        assertTrue(rsvpTrue.formatted().startsWith("RSVP="));
        assertTrue(rsvpFalse.formatted().startsWith("RSVP="));
    }

    @Test
    void testConsistentBehavior() {
        RsvpExpectation rsvp = RsvpExpectation.of(true);
        
        // Multiple calls should return the same result
        for (int i = 0; i < 10; i++) {
            assertEquals("RSVP=TRUE", rsvp.formatted());
        }
    }

    @Test
    void testBooleanValueIntegrity() {
        // Test that the internal boolean value is preserved correctly
        RsvpExpectation rsvpTrue = RsvpExpectation.of(true);
        RsvpExpectation rsvpFalse = RsvpExpectation.of(false);
        
        assertEquals("RSVP=TRUE", rsvpTrue.formatted());
        assertEquals("RSVP=FALSE", rsvpFalse.formatted());
    }
}
