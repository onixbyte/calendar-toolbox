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

class RelationshipTypeTest {

    @Test
    void testChildFormatted() {
        RelationshipType type = RelationshipType.CHILD;
        assertEquals("RELTYPE=CHILD", type.formatted());
    }

    @Test
    void testParentFormatted() {
        RelationshipType type = RelationshipType.PARENT;
        assertEquals("RELTYPE=PARENT", type.formatted());
    }

    @Test
    void testSiblingFormatted() {
        RelationshipType type = RelationshipType.SIBLING;
        assertEquals("RELTYPE=SIBLING", type.formatted());
    }

    @Test
    void testSnoozeFormatted() {
        RelationshipType type = RelationshipType.SNOOZE;
        assertEquals("RELTYPE=SNOOZE", type.formatted());
    }

    @Test
    void testConceptFormatted() {
        RelationshipType type = RelationshipType.CONCEPT;
        assertEquals("RELTYPE=CONCEPT", type.formatted());
    }

    @Test
    void testDependsOnFormatted() {
        RelationshipType type = RelationshipType.DEPENDS_ON;
        assertEquals("RELTYPE=DEPENDS-ON", type.formatted());
    }

    @Test
    void testFinishToFinishFormatted() {
        RelationshipType type = RelationshipType.FINISH_TO_FINISH;
        assertEquals("RELTYPE=FINISHTOFINISH", type.formatted());
    }

    @Test
    void testFinishToStartFormatted() {
        RelationshipType type = RelationshipType.FINISH_TO_START;
        assertEquals("RELTYPE=FINISHTOSTART", type.formatted());
    }

    @Test
    void testFirstFormatted() {
        RelationshipType type = RelationshipType.FIRST;
        assertEquals("RELTYPE=FIRST", type.formatted());
    }

    @Test
    void testNextFormatted() {
        RelationshipType type = RelationshipType.NEXT;
        assertEquals("RELTYPE=NEXT", type.formatted());
    }

    @Test
    void testReferenceIdFormatted() {
        RelationshipType type = RelationshipType.REFERENCE_ID;
        assertEquals("RELTYPE=REFID", type.formatted());
    }

    @Test
    void testStartToFinishFormatted() {
        RelationshipType type = RelationshipType.START_TO_FINISH;
        assertEquals("RELTYPE=STARTTOFINISH", type.formatted());
    }

    @Test
    void testStartToStartFormatted() {
        RelationshipType type = RelationshipType.START_TO_START;
        assertEquals("RELTYPE=STARTTOSTART", type.formatted());
    }

    @Test
    void testAllValues() {
        RelationshipType[] values = RelationshipType.values();
        assertEquals(13, values.length);
        
        // Verify all enum values are present
        boolean hasChild = false, hasParent = false, hasSibling = false, hasSnooze = false, hasConcept = false;
        boolean hasDependsOn = false, hasFinishToFinish = false, hasFinishToStart = false, hasFirst = false;
        boolean hasNext = false, hasReferenceId = false, hasStartToFinish = false, hasStartToStart = false;
        
        for (RelationshipType value : values) {
            switch (value) {
                case CHILD -> hasChild = true;
                case PARENT -> hasParent = true;
                case SIBLING -> hasSibling = true;
                case SNOOZE -> hasSnooze = true;
                case CONCEPT -> hasConcept = true;
                case DEPENDS_ON -> hasDependsOn = true;
                case FINISH_TO_FINISH -> hasFinishToFinish = true;
                case FINISH_TO_START -> hasFinishToStart = true;
                case FIRST -> hasFirst = true;
                case NEXT -> hasNext = true;
                case REFERENCE_ID -> hasReferenceId = true;
                case START_TO_FINISH -> hasStartToFinish = true;
                case START_TO_START -> hasStartToStart = true;
            }
        }
        
        assertTrue(hasChild);
        assertTrue(hasParent);
        assertTrue(hasSibling);
        assertTrue(hasSnooze);
        assertTrue(hasConcept);
        assertTrue(hasDependsOn);
        assertTrue(hasFinishToFinish);
        assertTrue(hasFinishToStart);
        assertTrue(hasFirst);
        assertTrue(hasNext);
        assertTrue(hasReferenceId);
        assertTrue(hasStartToFinish);
        assertTrue(hasStartToStart);
    }

    @Test
    void testValueOf() {
        assertEquals(RelationshipType.CHILD, RelationshipType.valueOf("CHILD"));
        assertEquals(RelationshipType.PARENT, RelationshipType.valueOf("PARENT"));
        assertEquals(RelationshipType.SIBLING, RelationshipType.valueOf("SIBLING"));
        assertEquals(RelationshipType.DEPENDS_ON, RelationshipType.valueOf("DEPENDS_ON"));
        assertEquals(RelationshipType.FINISH_TO_FINISH, RelationshipType.valueOf("FINISH_TO_FINISH"));
        assertEquals(RelationshipType.FINISH_TO_START, RelationshipType.valueOf("FINISH_TO_START"));
        assertEquals(RelationshipType.REFERENCE_ID, RelationshipType.valueOf("REFERENCE_ID"));
        assertEquals(RelationshipType.START_TO_FINISH, RelationshipType.valueOf("START_TO_FINISH"));
        assertEquals(RelationshipType.START_TO_START, RelationshipType.valueOf("START_TO_START"));
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RelationshipType.valueOf("INVALID"));
    }

    @Test
    void testEnumInstancesAreParameters() {
        for (RelationshipType type : RelationshipType.values()) {
            assertTrue(type instanceof Parameter);
            assertNotNull(type.formatted());
            assertTrue(type.formatted().startsWith("RELTYPE="));
        }
    }

    @Test
    void testCustomLabels() {
        // Test that custom labels are used for specific enum values
        assertEquals("RELTYPE=DEPENDS-ON", RelationshipType.DEPENDS_ON.formatted());
        assertEquals("RELTYPE=FINISHTOFINISH", RelationshipType.FINISH_TO_FINISH.formatted());
        assertEquals("RELTYPE=FINISHTOSTART", RelationshipType.FINISH_TO_START.formatted());
        assertEquals("RELTYPE=REFID", RelationshipType.REFERENCE_ID.formatted());
        assertEquals("RELTYPE=STARTTOFINISH", RelationshipType.START_TO_FINISH.formatted());
        assertEquals("RELTYPE=STARTTOSTART", RelationshipType.START_TO_START.formatted());
    }

    @Test
    void testDefaultLabels() {
        // Test that default labels (enum name) are used for values without custom labels
        assertEquals("RELTYPE=CHILD", RelationshipType.CHILD.formatted());
        assertEquals("RELTYPE=PARENT", RelationshipType.PARENT.formatted());
        assertEquals("RELTYPE=SIBLING", RelationshipType.SIBLING.formatted());
        assertEquals("RELTYPE=SNOOZE", RelationshipType.SNOOZE.formatted());
        assertEquals("RELTYPE=CONCEPT", RelationshipType.CONCEPT.formatted());
        assertEquals("RELTYPE=FIRST", RelationshipType.FIRST.formatted());
        assertEquals("RELTYPE=NEXT", RelationshipType.NEXT.formatted());
    }
}
