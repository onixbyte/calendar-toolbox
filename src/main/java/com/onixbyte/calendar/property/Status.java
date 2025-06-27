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

import java.util.List;

public enum Status implements ComponentProperty {

    TENTATIVE,
    CONFIRMED,
    CANCELLED,
    NEEDS_ACTION,
    COMPLETED,
    IN_PROGRESS,
    DRAFT,
    FINAL
    ;

    private final static List<Status> EVENT_STATUSES = List.of(
            TENTATIVE, CONFIRMED, CANCELLED
    );

    private final static List<Status> TODO_STATUSES = List.of(
            NEEDS_ACTION, COMPLETED, IN_PROGRESS, CANCELLED
    );

    private final static List<Status> JOURNAL_STATUSES = List.of(
            DRAFT, FINAL, CANCELLED
    );

    public boolean isEventStatus() {
        return EVENT_STATUSES.contains(this);
    }

    public boolean isTodoStatus() {
        return TODO_STATUSES.contains(this);
    }

    public boolean isJournalStatus() {
        return JOURNAL_STATUSES.contains(this);
    }

    @Override
    public String formatted() {
        return "STATUS:" + name().replaceAll("_", "-");
    }
}
