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

import com.onixbyte.calendar.component.property.ComponentProperty;
import com.onixbyte.calendar.parameter.Parameter;
import com.onixbyte.calendar.util.PropertyComposer;

import java.util.List;

public final class CustomCalendarProperty implements ComponentProperty {

    private final String propertyName;

    private final String value;

    private final List<Parameter> parameters;

    private CustomCalendarProperty(String propertyName, String value, List<Parameter> parameters) {
        this.propertyName = propertyName;
        this.value = value;
        this.parameters = parameters;
    }

    public static CustomCalendarPropertyBuilder builder() {
        return new CustomCalendarPropertyBuilder();
    }

    public static class CustomCalendarPropertyBuilder {
        private List<Parameter> parameters;

        private CustomCalendarPropertyBuilder() {
        }

        private CustomCalendarPropertyBuilder withParameters(Parameter... parameters) {
            this.parameters = List.of(parameters);
            return this;
        }

        public CustomCalendarProperty build(String propertyName, String propertyValue) {
            return new CustomCalendarProperty(propertyName, propertyValue, parameters);
        }
    }

    @Override
    public String formatted() {
        var composer = PropertyComposer.of(propertyName);
        parameters.forEach(composer::append);
        return composer.end(value);
    }
}
