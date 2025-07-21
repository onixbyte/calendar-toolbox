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

class ParticipationStatusTest {

    @Test
    void testNeedsActionFormatted() {
        ParticipationStatus status = ParticipationStatus.NEEDS_ACTION;
        assertEquals("PARTSTAT=NEEDS-ACTION", status.formatted());
    }

    @Test
    void testAcceptedFormatted() {
        ParticipationStatus status = ParticipationStatus.ACCEPTED;
        assertEquals("PARTSTAT=ACCEPTED", status.formatted());
    }

    @Test
    void testDeclinedFormatted() {
        ParticipationStatus status = ParticipationStatus.DECLINED;
        assertEquals("PARTSTAT=DECLINED", status.formatted());
    }

    @Test
    void testTentativeFormatted() {
        ParticipationStatus status = ParticipationStatus.TENTATIVE;
        assertEquals("PARTSTAT=TENTATIVE", status.formatted());
    }

    @Test
    void testDelegatedFormatted() {
        ParticipationStatus status = ParticipationStatus.DELEGATED;
        assertEquals("PARTSTAT=DELEGATED", status.formatted());
    }

    @Test
    void testCompletedFormatted() {
        ParticipationStatus status = ParticipationStatus.COMPLETED;
        assertEquals("PARTSTAT=COMPLETED", status.formatted());
    }

    @Test
    void testInProgressFormatted() {
        ParticipationStatus status = ParticipationStatus.IN_PROGRESS;
        assertEquals("PARTSTAT=IN-PROGRESS", status.formatted());
    }

    @Test
    void testEventParticipationStatusValidation() {
        assertTrue(ParticipationStatus.NEEDS_ACTION.isEventParticipationStatus());
        assertTrue(ParticipationStatus.ACCEPTED.isEventParticipationStatus());
        assertTrue(ParticipationStatus.DECLINED.isEventParticipationStatus());
        assertTrue(ParticipationStatus.TENTATIVE.isEventParticipationStatus());
        assertTrue(ParticipationStatus.DELEGATED.isEventParticipationStatus());
        
        assertFalse(ParticipationStatus.COMPLETED.isEventParticipationStatus());
        assertFalse(ParticipationStatus.IN_PROGRESS.isEventParticipationStatus());
    }

    @Test
    void testTodoParticipationStatusValidation() {
        assertTrue(ParticipationStatus.NEEDS_ACTION.isTodoParticipationStatus());
        assertTrue(ParticipationStatus.ACCEPTED.isTodoParticipationStatus());
        assertTrue(ParticipationStatus.DECLINED.isTodoParticipationStatus());
        assertTrue(ParticipationStatus.TENTATIVE.isTodoParticipationStatus());
        assertTrue(ParticipationStatus.DELEGATED.isTodoParticipationStatus());
        assertTrue(ParticipationStatus.COMPLETED.isTodoParticipationStatus());
        assertTrue(ParticipationStatus.IN_PROGRESS.isTodoParticipationStatus());
    }

    @Test
    void testJournalParticipationStatusValidation() {
        assertTrue(ParticipationStatus.NEEDS_ACTION.isJournalParticipationStatus());
        assertTrue(ParticipationStatus.ACCEPTED.isJournalParticipationStatus());
        assertTrue(ParticipationStatus.DECLINED.isJournalParticipationStatus());
        
        assertFalse(ParticipationStatus.TENTATIVE.isJournalParticipationStatus());
        assertFalse(ParticipationStatus.DELEGATED.isJournalParticipationStatus());
        assertFalse(ParticipationStatus.COMPLETED.isJournalParticipationStatus());
        assertFalse(ParticipationStatus.IN_PROGRESS.isJournalParticipationStatus());
    }

    @Test
    void testAllValues() {
        ParticipationStatus[] values = ParticipationStatus.values();
        assertEquals(7, values.length);
        
        // Verify all enum values are present
        boolean hasNeedsAction = false, hasAccepted = false, hasDeclined = false, hasTentative = false;
        boolean hasDelegated = false, hasCompleted = false, hasInProgress = false;
        
        for (ParticipationStatus value : values) {
            switch (value) {
                case NEEDS_ACTION -> hasNeedsAction = true;
                case ACCEPTED -> hasAccepted = true;
                case DECLINED -> hasDeclined = true;
                case TENTATIVE -> hasTentative = true;
                case DELEGATED -> hasDelegated = true;
                case COMPLETED -> hasCompleted = true;
                case IN_PROGRESS -> hasInProgress = true;
            }
        }
        
        assertTrue(hasNeedsAction);
        assertTrue(hasAccepted);
        assertTrue(hasDeclined);
        assertTrue(hasTentative);
        assertTrue(hasDelegated);
        assertTrue(hasCompleted);
        assertTrue(hasInProgress);
    }

    @Test
    void testValueOf() {
        assertEquals(ParticipationStatus.NEEDS_ACTION, ParticipationStatus.valueOf("NEEDS_ACTION"));
        assertEquals(ParticipationStatus.ACCEPTED, ParticipationStatus.valueOf("ACCEPTED"));
        assertEquals(ParticipationStatus.DECLINED, ParticipationStatus.valueOf("DECLINED"));
        assertEquals(ParticipationStatus.TENTATIVE, ParticipationStatus.valueOf("TENTATIVE"));
        assertEquals(ParticipationStatus.DELEGATED, ParticipationStatus.valueOf("DELEGATED"));
        assertEquals(ParticipationStatus.COMPLETED, ParticipationStatus.valueOf("COMPLETED"));
        assertEquals(ParticipationStatus.IN_PROGRESS, ParticipationStatus.valueOf("IN_PROGRESS"));
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ParticipationStatus.valueOf("INVALID"));
    }

    @Test
    void testEnumInstancesAreParameters() {
        for (ParticipationStatus status : ParticipationStatus.values()) {
            assertTrue(status instanceof Parameter);
            assertNotNull(status.formatted());
            assertTrue(status.formatted().startsWith("PARTSTAT="));
        }
    }

    @Test
    void testFormattedUsesHyphens() {
        // Test that underscores are replaced with hyphens in formatted output
        assertEquals("PARTSTAT=NEEDS-ACTION", ParticipationStatus.NEEDS_ACTION.formatted());
        assertEquals("PARTSTAT=IN-PROGRESS", ParticipationStatus.IN_PROGRESS.formatted());
    }
}
