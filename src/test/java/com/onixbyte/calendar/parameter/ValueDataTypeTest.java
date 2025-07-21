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

class ValueDataTypeTest {

    @Test
    void testBinaryFormatted() {
        ValueDataType dataType = ValueDataType.BINARY;
        assertEquals("VALUE=BINARY", dataType.formatted());
    }

    @Test
    void testBooleanFormatted() {
        ValueDataType dataType = ValueDataType.BOOLEAN;
        assertEquals("VALUE=BOOLEAN", dataType.formatted());
    }

    @Test
    void testCalAddressFormatted() {
        ValueDataType dataType = ValueDataType.CAL_ADDRESS;
        assertEquals("VALUE=CAL-ADDRESS", dataType.formatted());
    }

    @Test
    void testDateFormatted() {
        ValueDataType dataType = ValueDataType.DATE;
        assertEquals("VALUE=DATE", dataType.formatted());
    }

    @Test
    void testDateTimeFormatted() {
        ValueDataType dataType = ValueDataType.DATE_TIME;
        assertEquals("VALUE=DATE-TIME", dataType.formatted());
    }

    @Test
    void testDurationFormatted() {
        ValueDataType dataType = ValueDataType.DURATION;
        assertEquals("VALUE=DURATION", dataType.formatted());
    }

    @Test
    void testFloatFormatted() {
        ValueDataType dataType = ValueDataType.FLOAT;
        assertEquals("VALUE=FLOAT", dataType.formatted());
    }

    @Test
    void testIntegerFormatted() {
        ValueDataType dataType = ValueDataType.INTEGER;
        assertEquals("VALUE=INTEGER", dataType.formatted());
    }

    @Test
    void testPeriodFormatted() {
        ValueDataType dataType = ValueDataType.PERIOD;
        assertEquals("VALUE=PERIOD", dataType.formatted());
    }

    @Test
    void testRecurFormatted() {
        ValueDataType dataType = ValueDataType.RECUR;
        assertEquals("VALUE=RECUR", dataType.formatted());
    }

    @Test
    void testTextFormatted() {
        ValueDataType dataType = ValueDataType.TEXT;
        assertEquals("VALUE=TEXT", dataType.formatted());
    }

    @Test
    void testTimeFormatted() {
        ValueDataType dataType = ValueDataType.TIME;
        assertEquals("VALUE=TIME", dataType.formatted());
    }

    @Test
    void testUnknownFormatted() {
        ValueDataType dataType = ValueDataType.UNKNOWN;
        assertEquals("VALUE=UNKNOWN", dataType.formatted());
    }

    @Test
    void testUriFormatted() {
        ValueDataType dataType = ValueDataType.URI;
        assertEquals("VALUE=URI", dataType.formatted());
    }

    @Test
    void testUtcOffsetFormatted() {
        ValueDataType dataType = ValueDataType.UTC_OFFSET;
        assertEquals("VALUE=UTC-OFFSET", dataType.formatted());
    }

    @Test
    void testXmlReferenceFormatted() {
        ValueDataType dataType = ValueDataType.XML_REFERENCE;
        assertEquals("VALUE=XML-REFERENCE", dataType.formatted());
    }

    @Test
    void testUidFormatted() {
        ValueDataType dataType = ValueDataType.UID;
        assertEquals("VALUE=UID", dataType.formatted());
    }

    @Test
    void testAllValues() {
        ValueDataType[] values = ValueDataType.values();
        assertEquals(17, values.length);
        
        // Verify all enum values are present
        boolean hasBinary = false, hasBoolean = false, hasCalAddress = false, hasDate = false, hasDateTime = false;
        boolean hasDuration = false, hasFloat = false, hasInteger = false, hasPeriod = false, hasRecur = false;
        boolean hasText = false, hasTime = false, hasUnknown = false, hasUri = false, hasUtcOffset = false;
        boolean hasXmlReference = false, hasUid = false;
        
        for (ValueDataType value : values) {
            switch (value) {
                case BINARY -> hasBinary = true;
                case BOOLEAN -> hasBoolean = true;
                case CAL_ADDRESS -> hasCalAddress = true;
                case DATE -> hasDate = true;
                case DATE_TIME -> hasDateTime = true;
                case DURATION -> hasDuration = true;
                case FLOAT -> hasFloat = true;
                case INTEGER -> hasInteger = true;
                case PERIOD -> hasPeriod = true;
                case RECUR -> hasRecur = true;
                case TEXT -> hasText = true;
                case TIME -> hasTime = true;
                case UNKNOWN -> hasUnknown = true;
                case URI -> hasUri = true;
                case UTC_OFFSET -> hasUtcOffset = true;
                case XML_REFERENCE -> hasXmlReference = true;
                case UID -> hasUid = true;
            }
        }
        
        assertTrue(hasBinary);
        assertTrue(hasBoolean);
        assertTrue(hasCalAddress);
        assertTrue(hasDate);
        assertTrue(hasDateTime);
        assertTrue(hasDuration);
        assertTrue(hasFloat);
        assertTrue(hasInteger);
        assertTrue(hasPeriod);
        assertTrue(hasRecur);
        assertTrue(hasText);
        assertTrue(hasTime);
        assertTrue(hasUnknown);
        assertTrue(hasUri);
        assertTrue(hasUtcOffset);
        assertTrue(hasXmlReference);
        assertTrue(hasUid);
    }

    @Test
    void testValueOf() {
        assertEquals(ValueDataType.BINARY, ValueDataType.valueOf("BINARY"));
        assertEquals(ValueDataType.BOOLEAN, ValueDataType.valueOf("BOOLEAN"));
        assertEquals(ValueDataType.CAL_ADDRESS, ValueDataType.valueOf("CAL_ADDRESS"));
        assertEquals(ValueDataType.DATE, ValueDataType.valueOf("DATE"));
        assertEquals(ValueDataType.DATE_TIME, ValueDataType.valueOf("DATE_TIME"));
        assertEquals(ValueDataType.DURATION, ValueDataType.valueOf("DURATION"));
        assertEquals(ValueDataType.FLOAT, ValueDataType.valueOf("FLOAT"));
        assertEquals(ValueDataType.INTEGER, ValueDataType.valueOf("INTEGER"));
        assertEquals(ValueDataType.PERIOD, ValueDataType.valueOf("PERIOD"));
        assertEquals(ValueDataType.RECUR, ValueDataType.valueOf("RECUR"));
        assertEquals(ValueDataType.TEXT, ValueDataType.valueOf("TEXT"));
        assertEquals(ValueDataType.TIME, ValueDataType.valueOf("TIME"));
        assertEquals(ValueDataType.UNKNOWN, ValueDataType.valueOf("UNKNOWN"));
        assertEquals(ValueDataType.URI, ValueDataType.valueOf("URI"));
        assertEquals(ValueDataType.UTC_OFFSET, ValueDataType.valueOf("UTC_OFFSET"));
        assertEquals(ValueDataType.XML_REFERENCE, ValueDataType.valueOf("XML_REFERENCE"));
        assertEquals(ValueDataType.UID, ValueDataType.valueOf("UID"));
    }

    @Test
    void testInvalidValueOfThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ValueDataType.valueOf("INVALID"));
    }

    @Test
    void testEnumInstancesAreParameters() {
        for (ValueDataType dataType : ValueDataType.values()) {
            assertTrue(dataType instanceof Parameter);
            assertNotNull(dataType.formatted());
            assertTrue(dataType.formatted().startsWith("VALUE="));
        }
    }

    @Test
    void testFormattedUsesHyphens() {
        // Test that underscores are replaced with hyphens in formatted output
        assertEquals("VALUE=CAL-ADDRESS", ValueDataType.CAL_ADDRESS.formatted());
        assertEquals("VALUE=DATE-TIME", ValueDataType.DATE_TIME.formatted());
        assertEquals("VALUE=UTC-OFFSET", ValueDataType.UTC_OFFSET.formatted());
        assertEquals("VALUE=XML-REFERENCE", ValueDataType.XML_REFERENCE.formatted());
    }

    @Test
    void testFormattedDoesNotChangeSimpleNames() {
        // Test that simple names (without underscores) are not changed
        assertEquals("VALUE=BINARY", ValueDataType.BINARY.formatted());
        assertEquals("VALUE=BOOLEAN", ValueDataType.BOOLEAN.formatted());
        assertEquals("VALUE=DATE", ValueDataType.DATE.formatted());
        assertEquals("VALUE=DURATION", ValueDataType.DURATION.formatted());
        assertEquals("VALUE=FLOAT", ValueDataType.FLOAT.formatted());
        assertEquals("VALUE=INTEGER", ValueDataType.INTEGER.formatted());
        assertEquals("VALUE=PERIOD", ValueDataType.PERIOD.formatted());
        assertEquals("VALUE=RECUR", ValueDataType.RECUR.formatted());
        assertEquals("VALUE=TEXT", ValueDataType.TEXT.formatted());
        assertEquals("VALUE=TIME", ValueDataType.TIME.formatted());
        assertEquals("VALUE=UNKNOWN", ValueDataType.UNKNOWN.formatted());
        assertEquals("VALUE=URI", ValueDataType.URI.formatted());
        assertEquals("VALUE=UID", ValueDataType.UID.formatted());
    }
}
