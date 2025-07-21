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

import com.onixbyte.calendar.parameter.RelationshipType;

/**
 * Represents the {@code RELATED-TO} property in an iCalendar component.
 * <p>
 * This property is used to specify a relationship between calendar components. It contains the
 * persistent, globally unique identifier of another calendar component. The property also supports
 * a relationship type parameter to define the nature of the relationship
 * (e.g., parent, child, sibling).
 * <p>
 * This property is commonly used to create hierarchical relationships between calendar components
 * or to reference related components.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class RelatedTo implements ComponentProperty {

    /**
     * The optional relationship type parameter that defines the nature of the relationship.
     */
    private final RelationshipType relationshipType;

    /**
     * The unique identifier of the related calendar component.
     */
    private final String value;

    /**
     * Constructs a new {@code RelatedTo} instance with the specified parameters.
     *
     * @param relationshipType the optional relationship type parameter
     * @param value            the unique identifier of the related calendar component
     */
    private RelatedTo(RelationshipType relationshipType, String value) {
        this.relationshipType = relationshipType;
        this.value = value;
    }

    /**
     * Creates a new builder for constructing {@code RelatedTo} instances.
     *
     * @return a new {@code RelatedToBuilder}
     */
    public static RelatedToBuilder builder() {
        return new RelatedToBuilder();
    }

    /**
     * Builder class for creating {@code RelatedTo} instances with optional parameters.
     */
    public static class RelatedToBuilder {
        /**
         * The optional relationship type parameter.
         */
        private RelationshipType relationshipType;

        /**
         * Private constructor to enforce builder pattern usage.
         */
        private RelatedToBuilder() {
        }

        /**
         * Sets the relationship type parameter for this related-to property.
         *
         * @param relationshipType the relationship type parameter
         * @return this builder instance for method chaining
         */
        public RelatedToBuilder withRelationshipType(RelationshipType relationshipType) {
            this.relationshipType = relationshipType;
            return this;
        }

        /**
         * Builds a new {@code RelatedTo} instance with the specified related component identifier.
         *
         * @param value the unique identifier of the related calendar component
         * @return a new {@code RelatedTo} instance
         */
        public RelatedTo build(String value) {
            return new RelatedTo(relationshipType, value);
        }
    }

    /**
     * Returns the formatted string representation of this related-to property for inclusion in
     * an iCalendar.
     * <p>
     * The format follows RFC 5545 specifications and includes any specified parameters.
     *
     * @return the formatted {@code RELATED-TO} property string
     */
    @Override
    public String formatted() {
        return PropertyComposer.of("RELATED-TO")
                .append(relationshipType)
                .end(value);
    }
}
