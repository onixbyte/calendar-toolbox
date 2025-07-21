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

import com.onixbyte.calendar.parameter.Language;
import com.onixbyte.calendar.util.ParamAppender;

import java.util.List;

/**
 * Represents the iCalendar {@code CATEGORIES} property, which specifies categories or
 * subtypes for a calendar component.
 * <p>
 * This property is used to categorise or classify calendar components, making it easier to organise
 * and filter calendar data. Categories can be used to group related events, tasks, or journal
 * entries together.
 * <p>
 * Examples of categories might include:
 * <ul>
 *   <li>APPOINTMENT</li>
 *   <li>BUSINESS</li>
 *   <li>EDUCATION</li>
 *   <li>HOLIDAY</li>
 *   <li>MEETING</li>
 *   <li>MISCELLANEOUS</li>
 *   <li>PERSONAL</li>
 *   <li>PHONE CALL</li>
 *   <li>SICK DAY</li>
 *   <li>SPECIAL OCCASION</li>
 *   <li>TRAVEL</li>
 *   <li>VACATION</li>
 * </ul>
 * <p>
 * Multiple categories can be specified for a single component, and the property supports optional
 * language specification for internationalisation.
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern
 * via {@link #builder()}.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Categories implements ComponentProperty {

    /**
     * Optional parameter specifying the language of the category text. This helps with
     * internationalisation and localisation of calendar data.
     */
    private final Language language;

    /**
     * The list of category names for this component. Each category is a text string that classifies
     * the component.
     */
    private final List<String> categories;

    /**
     * Constructs a new Categories instance with the specified parameters.
     *
     * @param language   optional language parameter
     * @param categories the list of category names
     */
    private Categories(Language language, List<String> categories) {
        this.language = language;
        this.categories = categories;
    }

    /**
     * Creates a new builder instance for constructing a Categories property.
     *
     * @return a new CategoriesBuilder instance
     */
    public static CategoriesBuilder builder() {
        return new CategoriesBuilder();
    }

    /**
     * Builder class for constructing Categories instances.
     * <p>
     * This builder allows for optional configuration of language parameters before creating the
     * final Categories instance with one or more category names.
     */
    public static class CategoriesBuilder {
        /**
         * Optional language parameter.
         */
        private Language language;

        /**
         * Private constructor to enforce use of the factory method.
         */
        private CategoriesBuilder() {
        }

        /**
         * Sets the language parameter for the categories.
         *
         * @param language the language parameter
         * @return this builder instance for method chaining
         */
        public CategoriesBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Creates a new {@code Categories} instance with the specified category names.
         *
         * @param categories one or more category names
         * @return a new Categories instance
         */
        public Categories build(String... categories) {
            return new Categories(language, List.of(categories));
        }
    }

    /**
     * Returns the formatted iCalendar representation of this categories property.
     * <p>
     * The format follows the iCalendar specification:
     * {@code CATEGORIES[;parameters]:value1,value2,...} where multiple categories are separated by
     * commas and parameters may include language if specified.
     *
     * @return the formatted iCalendar property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("CATEGORIES");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(language);

        builder.append(":").append(String.join(",", categories));
        return builder.toString();
    }
}
