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

package com.onixbyte.calendar.recurrence;

/**
 * Enumeration representing the frequency values for iCalendar recurrence rules.
 * <p>
 * The Frequency enum defines the intervals at which recurrence rules repeat. These values are used
 * in the RRULE property to specify how often an event, task, or journal entry should recur.
 * <p>
 * Each frequency value corresponds to a specific time interval:
 * <ul>
 * <li>{@code SECONDLY} - Recurs every second</li>
 * <li>{@code MINUTELY} - Recurs every minute</li>
 * <li>{@code HOURLY} - Recurs every hour</li>
 * <li>{@code DAILY} - Recurs every day</li>
 * <li>{@code WEEKLY} - Recurs every week</li>
 * <li>{@code MONTHLY} - Recurs every month</li>
 * <li>{@code YEARLY} - Recurs every year</li>
 * </ul>
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Frequency {

    /**
     * Recurrence frequency of every second. The event will repeat every second.
     */
    SECONDLY,

    /**
     * Recurrence frequency of every minute. The event will repeat every minute.
     */
    MINUTELY,

    /**
     * Recurrence frequency of every hour. The event will repeat every hour.
     */
    HOURLY,

    /**
     * Recurrence frequency of every day. The event will repeat every day.
     */
    DAILY,

    /**
     * Recurrence frequency of every week. The event will repeat every week.
     */
    WEEKLY,

    /**
     * Recurrence frequency of every month. The event will repeat every month.
     */
    MONTHLY,

    /**
     * Recurrence frequency of every year. The event will repeat every year.
     */
    YEARLY;
}
