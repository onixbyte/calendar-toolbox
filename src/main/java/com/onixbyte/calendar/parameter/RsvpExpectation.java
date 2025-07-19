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

/**
 * Represents the iCalendar {@code RSVP} parameter, which indicates whether a response is expected
 * from the participant for a scheduled event. This parameter is used to specify if the calendar
 * entity requires an RSVP (response) from the attendee.
 * <p>
 * Instances of this class are immutable and can be created via the static factory method
 * {@link #of(boolean)}. The boolean value is converted to uppercase ("TRUE" or "FALSE") when
 * formatted, as per iCalendar syntax requirements.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class RsvpExpectation implements Parameter {

    /**
     * Indicates whether an RSVP is expected (true) or not (false).
     */
    private final boolean value;

    /**
     * Constructs an {@code RsvpExpectation} with the specified RSVP expectation.
     *
     * @param value whether an RSVP is expected (true) or not (false)
     */
    private RsvpExpectation(boolean value) {
        this.value = value;
    }

    /**
     * Creates an {@code RsvpExpectation} instance from a boolean value.
     *
     * @param value whether an RSVP is expected (true) or not (false)
     * @return a new instance of {@code RsvpExpectation}
     */
    public static RsvpExpectation of(boolean value) {
        return new RsvpExpectation(value);
    }

    /**
     * Returns the formatted {@code RSVP} parameter string as specified in the
     * iCalendar specification.
     * <p>
     * The boolean value is converted to uppercase ("TRUE" or "FALSE") to comply with
     * iCalendar syntax.
     *
     * @return a formatted string in the form {@code RSVP=TRUE} or {@code RSVP=FALSE} suitable for
     * inclusion in an iCalendar entity
     */
    @Override
    public String formatted() {
        return "RSVP=" + String.valueOf(value).toUpperCase();
    }
}
