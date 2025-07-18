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

package com.onixbyte.calendar.util;

import com.onixbyte.calendar.property.ComponentProperty;
import com.onixbyte.common.util.CollectionUtil;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public final class PropertyAppender {

    private final StringBuilder builder;

    private PropertyAppender(StringBuilder builder) {
        this.builder = builder;
    }

    public static PropertyAppender of(StringBuilder builder) {
        return new PropertyAppender(builder);
    }

    public StringBuilder append(Duration duration) {
        if (Objects.nonNull(duration)) {
            builder.append("\n").append(Formatters.folding(Formatters.formatDuration(duration)));
        }
        return builder;
    }

    public StringBuilder append(ComponentProperty componentProperty) {
        if (Objects.nonNull(componentProperty)) {
            builder.append("\n").append(Formatters.folding(componentProperty.formatted()));
        }
        return builder;
    }

    public StringBuilder append(List<? extends ComponentProperty> componentProperties) {
        if (CollectionUtil.notEmpty(componentProperties)) {
            componentProperties.forEach((componentProperty) -> builder
                    .append("\n")
                    .append(Formatters.folding(componentProperty.formatted())));
        }
        return builder;
    }
}
