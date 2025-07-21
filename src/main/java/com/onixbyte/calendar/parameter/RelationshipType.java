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
 * Enumerates the various relationship types that can exist between different entities, as defined
 * in relevant specifications. This enum implements the {@code Parameter} interface, meaning it can
 * be formatted into a standard string representation for inclusion in structured data formats.
 * <p>
 * Each relationship type has a specific semantic meaning. For example, {@code CHILD} represents a
 * hierarchical child relationship, while dependency types like {@code FINISH_TO_START} define
 * temporal dependencies between tasks.
 * <p>
 * The enum provides both default labels (derived from the enum name) and custom labels (for cases
 * where the standard requires a different format, such as {@code DEPENDS-ON}).
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public enum RelationshipType implements Parameter {

    /**
     * Indicates a hierarchical child relationship between entities.
     */
    CHILD,

    /**
     * Indicates a hierarchical parent relationship between entities.
     */
    PARENT,

    /**
     * Indicates a sibling relationship between entities.
     */
    SIBLING,

    /**
     * Indicates a snooze relationship, typically used in scheduling contexts to delay an action
     * or reminder.
     */
    SNOOZE,

    /**
     * Indicates a conceptual relationship between entities.
     */
    CONCEPT,

    /**
     * Indicates that an entity depends on another. This is a generic dependency relationship where
     * the dependent entity cannot proceed until the referenced entity has been addressed.
     */
    DEPENDS_ON("DEPENDS-ON"),

    /**
     * Indicates a finish-to-finish dependency between tasks, where the finishing of one task is
     * dependent on the finishing of another.
     */
    FINISH_TO_FINISH("FINISHTOFINISH"),

    /**
     * Indicates a finish-to-start dependency between tasks, where the start of one task is
     * dependent on the finishing of another.
     */
    FINISH_TO_START("FINISHTOSTART"),

    /**
     * Indicates the first item in a sequence or collection of entities.
     */
    FIRST,

    /**
     * Indicates the next item in a sequence or workflow of entities.
     */
    NEXT,

    /**
     * Represents a reference identifier relationship, used to link to another entity by
     * its identifier.
     */
    REFERENCE_ID("REFID"),

    /**
     * Indicates a start-to-finish dependency between tasks, where the finishing of one task is
     * dependent on the starting of another.
     */
    START_TO_FINISH("STARTTOFINISH"),

    /**
     * Indicates a start-to-start dependency between tasks, where the start of one task is dependent
     * on the starting of another.
     */
    START_TO_START("STARTTOSTART"),
    ;

    /**
     * The formatted label for the relationship type, which may differ from the enum name for
     * some entries.
     */
    private final String label;

    /**
     * Constructs a {@code RelationshipType} using the enum constant's name as the label.
     */
    RelationshipType() {
        this.label = name();
    }

    /**
     * Constructs a {@code RelationshipType} with a custom label.
     *
     * @param label the custom label to use for this relationship type
     */
    RelationshipType(String label) {
        this.label = label;
    }

    /**
     * Returns the formatted string representation of this relationship type, suitable for use in
     * structured data formats. The format is {@code RELTYPE=label}.
     *
     * @return a string formatted as {@code RELTYPE=label}
     */
    @Override
    public String formatted() {
        return "RELTYPE=" + label;
    }
}
