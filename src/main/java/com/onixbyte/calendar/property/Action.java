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

package com.onixbyte.calendar.property;

/**
 * Enumeration representing the iCalendar {@code ACTION} property values for alarm components.
 * <p>
 * The {@code ACTION} property defines the action to be invoked when an alarm is triggered. This
 * property is required for {@code VALARM} components and determines the type of notification or
 * action that should occur at the specified alarm time.
 * <p>
 * Each action type has different requirements for additional properties:
 * <ul>
 *   <li>{@code AUDIO} - may include an ATTACH property for the sound file</li>
 *   <li>{@code DISPLAY} - requires a DESCRIPTION property for the text to display</li>
 *   <li>{@code EMAIL} - requires DESCRIPTION and SUMMARY properties, and ATTENDEE properties</li>
 * </ul>
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Action implements ComponentProperty {

    /**
     * Audio alarm action.
     * <p>
     * Specifies that the alarm should play an audio file or generate an audible alert. This action
     * may optionally include an {@code ATTACH} property that specifies the sound file to play.
     */
    AUDIO,

    /**
     * Display alarm action.
     * <p>
     * Specifies that the alarm should display a text message to the user. This action requires a
     * {@code DESCRIPTION} property that contains the text to be displayed.
     */
    DISPLAY,

    /**
     * Email alarm action.
     * <p>
     * Specifies that the alarm should send an email message. This action requires
     * {@code DESCRIPTION} and {@code SUMMARY} properties for the email content, and one or more
     * {@code ATTENDEE} properties specifying the recipients.
     */
    EMAIL,
    ;

    /**
     * Returns the formatted iCalendar representation of this action property.
     * <p>
     * The format follows the iCalendar specification: {@code ACTION:value}
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        return "ACTION:" + name();
    }
}
