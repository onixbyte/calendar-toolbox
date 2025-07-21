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
 * Enumerates the different participation roles an entity can assume within a scheduling context, as
 * defined in relevant specifications. This enum implements the {@code Parameter} interface,
 * allowing its values to be formatted into standard string representations for inclusion in
 * structured data formats.
 * <p>
 * Each role defines a specific level of involvement and responsibility. For example, {@code CHAIR}
 * indicates the entity is the meeting chair, while {@code REQ_PARTICIPANT} denotes a
 * required participant.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public enum ParticipationRole implements Parameter {

    /**
     * Indicates the entity acts as the chair of the scheduling entity, such as a meeting.
     */
    CHAIR,

    /**
     * Indicates the entity is a required participant in the scheduling entity.
     */
    REQ_PARTICIPANT,

    /**
     * Indicates the entity is an optional participant in the scheduling entity.
     */
    OPT_PARTICIPANT,

    /**
     * Indicates the entity is not a participant but has been copied for information purposes.
     */
    NON_PARTICIPANT,
    ;

    /**
     * Returns the formatted string representation of this participation role, suitable for use in
     * structured data formats. The format is {@code ROLE=ENUM_NAME}.
     *
     * @return a string formatted as {@code ROLE=ENUM_NAME}
     */
    @Override
    public String formatted() {
        return "ROLE=" + name();
    }
}
