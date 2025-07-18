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

package com.onixbyte.calendar;

import com.onixbyte.calendar.component.CalendarComponent;
import com.onixbyte.calendar.property.CalendarScale;
import com.onixbyte.calendar.property.Method;
import com.onixbyte.calendar.property.ProductIdentifier;
import com.onixbyte.calendar.property.Version;

import java.util.List;

public final class Calendar {

    private final CalendarScale calendarScale;
    private final Method method;
    private final ProductIdentifier productIdentifier;
    private final Version version;
    private final List<CalendarComponent> components;

    private Calendar(
            CalendarScale calendarScale,
            Method method,
            ProductIdentifier productIdentifier,
            Version version,
            List<CalendarComponent> components
    ) {
        this.calendarScale = calendarScale;
        this.method = method;
        this.productIdentifier = productIdentifier;
        this.version = version;
        this.components = components;
    }

    public static CalendarBuilder builder() {
        return new CalendarBuilder();
    }

    public static class CalendarBuilder {
        private CalendarScale calendarScale;
        private Method method;
        private ProductIdentifier productIdentifier;
        private Version version;
        private List<CalendarComponent> components;

        private CalendarBuilder() {
        }

        public CalendarBuilder withCalendarScale(CalendarScale calendarScale) {
            this.calendarScale = calendarScale;
            return this;
        }

        public CalendarBuilder withMethod(Method method) {
            this.method = method;
            return this;
        }

        public CalendarBuilder withProductIdentifier(ProductIdentifier productIdentifier) {
            this.productIdentifier = productIdentifier;
            return this;
        }

        public CalendarBuilder withVersion(Version version) {
            this.version = version;
            return this;
        }

        public Calendar build() {
            return new Calendar(
                    calendarScale, method, productIdentifier, version, components
            );
        }
    }

    public String formatted() {
        var builder = new StringBuilder();

        builder.append("BEGIN:VCALENDAR");
        builder.append("\n").append(calendarScale.formatted());
        builder.append("\n").append(method.formatted());
        builder.append("\n").append(productIdentifier.formatted());
        builder.append("\n").append(version.formatted());
        components.forEach((component) ->
                builder.append("\n").append(component.formatted())
        );

        builder.append("\n").append("END:VCALENDAR");

        return builder.toString();
    }
}
