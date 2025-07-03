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

import java.util.Objects;

/**
 * Represents the iCalendar {@code RANGE} parameter for recurrence identifiers, used to specify the
 * scope of recurrence instances affected by a scheduling operation.
 * <p>
 * This class enforces the singleton pattern to ensure that only one instance of
 * {@code RecurrenceIdentifierRange} exists, as the specification defines a single valid value:
 * {@code THISANDFUTURE}.
 *
 * @author siujamo
 */
public final class RecurrenceIdentifierRange implements Parameter {

    /**
     * Singleton instance of RecurrenceIdentifierRange.
     */
    private static RecurrenceIdentifierRange _instance;

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private RecurrenceIdentifierRange() {}

    /**
     * Returns the singleton instance of RecurrenceIdentifierRange.
     * <p>
     * This method implements the double-checked locking mechanism to ensure
     * thread-safe lazy initialization of the instance.
     *
     * @return the singleton instance of RecurrenceIdentifierRange
     */
    public static RecurrenceIdentifierRange of() {
        if (Objects.isNull(_instance)) {
            synchronized (RecurrenceIdentifierRange.class) {
                if (Objects.isNull(_instance)) {
                    _instance = new RecurrenceIdentifierRange();
                }
            }
        }
        return _instance;
    }

    /**
     * Returns the formatted string representation of this parameter
     * as defined in the iCalendar specification.
     *
     * @return a formatted string in the form {@code RANGE=THISANDFUTURE}
     */
    @Override
    public String formatted() {
        return "RANGE=THISANDFUTURE";
    }
}
