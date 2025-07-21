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

package com.onixbyte.calendar.component;

import com.onixbyte.calendar.component.property.ComponentProperty;
import com.onixbyte.calendar.util.Formatters;
import com.onixbyte.common.util.CollectionUtil;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Utility class for appending iCalendar properties to a {@code StringBuilder}.
 * <p>
 * This class provides a convenient way to append formatted property strings to iCalendar component
 * output. It handles the proper formatting of properties including line breaks, folding for
 * long lines, and collection handling.
 * <p>
 * Properties in iCalendar format are each on separate lines, and lines longer than 75 characters
 * are folded (wrapped) with a space continuation character. This utility automatically handles
 * this formatting.
 * <p>
 * Supported property types include:
 * <ul>
 *   <li>Duration properties (formatted as ISO 8601 duration strings)</li>
 *   <li>Component properties (any property implementing ComponentProperty)</li>
 *   <li>Lists of component properties</li>
 * </ul>
 * <p>
 * Usage example:
 * <pre>{@code
 * }</pre>
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.1.0
 * @since 1.0.0
 */
final class ComponentComposer {

    private final StringBuilder builder;

    private final String componentName;

    private ComponentComposer(String componentName) {
        this.builder = new StringBuilder();
        this.componentName = componentName;
    }

    public static ComponentComposer of(String componentName) {
        return new ComponentComposer(componentName);
    }

    /**
     * Start composing a component.
     * <p>
     * A header refers to the start of this component, in the pattern of
     * "{@code BEGIN:componentName}".
     *
     * @return the appender instance
     */
    public ComponentComposer start() {
        builder.append("BEGIN").append(":").append(componentName);
        return this;
    }

    /**
     * Appends the specified {@code Duration} as a formatted iCalendar property.
     * <p>
     * The duration is formatted according to ISO 8601 duration format and appended with proper line
     * folding if necessary. If the duration is null, it is ignored.
     *
     * @param duration the duration to append, may be null
     * @return the underlying {@code StringBuilder} for method chaining
     */
    public ComponentComposer append(Duration duration) {
        if (Objects.nonNull(duration)) {
            builder.append("\n").append(Formatters.folding(Formatters.formatDuration(duration)));
        }
        return this;
    }

    /**
     * Appends the specified ComponentProperty as a formatted iCalendar property.
     * <p>
     * The property is formatted using its formatted() method and appended with proper line folding
     * if necessary. If the property is null, it is ignored.
     *
     * @param componentProperty the component property to append, may be null
     * @return the underlying {@code StringBuilder} for method chaining
     */
    public ComponentComposer append(ComponentProperty componentProperty) {
        if (Objects.nonNull(componentProperty)) {
            builder.append("\n").append(Formatters.folding(componentProperty.formatted()));
        }
        return this;
    }

    /**
     * Appends the specified list of ComponentProperties as formatted iCalendar properties.
     * <p>
     * Each property in the list is formatted using its formatted() method and appended with proper
     * line folding if necessary. If the list is null or empty, it is ignored.
     *
     * @param componentProperties the list of component properties to append, may be null or empty
     * @return the underlying {@code StringBuilder} for method chaining
     */
    public ComponentComposer append(List<? extends ComponentProperty> componentProperties) {
        if (CollectionUtil.notEmpty(componentProperties)) {
            componentProperties.forEach((componentProperty) -> builder.append("\n").append(Formatters.folding(componentProperty.formatted())));
        }
        return this;
    }

    /**
     * End composing.
     *
     * @return a text block represents the component in iCalendar format.
     */
    public String end() {
        builder.append("\n").append("END").append(":").append(componentName);
        return builder.toString();
    }
}
