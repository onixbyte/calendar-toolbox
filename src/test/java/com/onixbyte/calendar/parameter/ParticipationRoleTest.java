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

class ParticipationRoleTest {

    @Test
    void testChairFormatted() {
        ParticipationRole role = ParticipationRole.CHAIR;
        assertEquals("ROLE=CHAIR", role.formatted());
    }

    @Test
    void testReqParticipantFormatted() {
        ParticipationRole role = ParticipationRole.REQ_PARTICIPANT;
        assertEquals("ROLE=REQ_PARTICIPANT", role.formatted());
    }

    @Test
    void testOptParticipantFormatted() {
        ParticipationRole role = ParticipationRole.OPT_PARTICIPANT;
        assertEquals("ROLE=OPT_PARTICIPANT", role.formatted());
    }

    @Test
    void testNonParticipantFormatted() {
        ParticipationRole role = ParticipationRole.NON_PARTICIPANT;
        assertEquals("ROLE=NON_PARTICIPANT", role.formatted());
    }

    @Test
    void testAllValues() {
        ParticipationRole[] values = ParticipationRole.values();
        assertEquals(4, values.length);
        
        // Verify all enum values are present
        boolean hasChair = false, hasReqParticipant = false, hasOptParticipant = false, hasNonParticipant = false;
        for (ParticipationRole value : values) {
            switch (value) {
                case CHAIR -> hasChair = true;
                case REQ_PARTICIPANT -> hasReqParticipant = true;
                case OPT_PARTICIPANT -> hasOptParticipant = true;
                case NON_PARTICIPANT -> hasNonParticipant = true;
            }
        }
        
        assertTrue(hasChair);
        assertTrue(hasReqParticipant);
        assertTrue(hasOptParticipant);
        assertTrue(hasNonParticipant);
    }

    @Test
    void testValueOf() {
        assertEquals(ParticipationRole.CHAIR, ParticipationRole.valueOf("CHAIR"));
        assertEquals(ParticipationRole.REQ_PARTICIPANT, ParticipationRole.valueOf("REQ_PARTICIPANT"));
        assertEquals(ParticipationRole.OPT_PARTICIPANT, ParticipationRole.valueOf("OPT_PARTICIPANT"));
        assertEquals(ParticipationRole.NON_PARTICIPANT, ParticipationRole.valueOf("NON_PARTICIPANT"));
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ParticipationRole.valueOf("INVALID"));
    }

    @Test
    void testEnumInstancesAreParameters() {
        for (ParticipationRole role : ParticipationRole.values()) {
            assertTrue(role instanceof Parameter);
            assertNotNull(role.formatted());
            assertTrue(role.formatted().startsWith("ROLE="));
        }
    }

    @Test
    void testFormattedUsesUnderscores() {
        // Test that underscores are preserved in formatted output
        assertEquals("ROLE=REQ_PARTICIPANT", ParticipationRole.REQ_PARTICIPANT.formatted());
        assertEquals("ROLE=OPT_PARTICIPANT", ParticipationRole.OPT_PARTICIPANT.formatted());
        assertEquals("ROLE=NON_PARTICIPANT", ParticipationRole.NON_PARTICIPANT.formatted());
    }
}
