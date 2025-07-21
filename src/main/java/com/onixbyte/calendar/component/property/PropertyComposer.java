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

package com.onixbyte.calendar.component.property;

import com.onixbyte.calendar.parameter.Parameter;
import com.onixbyte.common.util.CollectionUtil;

import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

final class PropertyComposer {

    private final StringBuilder builder;

    private final String propertyName;

    private PropertyComposer(String propertyName) {
        this.builder = new StringBuilder();
        this.propertyName = propertyName;
    }

    public static PropertyComposer of(String componentName) {
        return new PropertyComposer(componentName);
    }

    public PropertyComposer start() {
        builder.append(propertyName);
        return this;
    }

    /**
     * Appends the specified parameter to the {@code StringBuilder}.
     * <p>
     * If the parameter is not null, it is formatted and appended to the builder with a semicolon
     * separator. If the parameter is null, it is ignored.
     *
     * @param param the parameter to append, may be null
     * @return the underlying StringBuilder for method chaining
     */
    public PropertyComposer append(Parameter param) {
        if (Objects.nonNull(param)) {
            builder.append(";").append(param.formatted());
        }

        return this;
    }

    public String end(String value) {
        if (Objects.nonNull(value) && !value.isEmpty()) {
            builder.append(":").append(value);
        }

        return builder.toString();
    }

    public String end(URI value) {
        if (Objects.nonNull(value)) {
            builder.append(":").append(value.toASCIIString());
        }

        return builder.toString();
    }

    public String end(List<String> values) {
        if (CollectionUtil.notEmpty(values)) {
            builder.append(":").append(String.join(",", values));
        }

        return builder.toString();
    }

    public String end(Duration value) {
        if (Objects.nonNull(value)) {
            builder.append(":").append(value);
        }

        return builder.toString();
    }
}
