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
import com.onixbyte.calendar.property.*;

import java.util.List;

/**
 * Represents an iCalendar (RFC 5545) calendar object, which serves as a container for calendar
 * components such as events, tasks, journal entries, and free/busy information.
 * <p>
 * The Calendar class provides a structured way to build and format iCalendar data according to
 * the iCalendar specification. It includes essential calendar properties like scale, method,
 * product identifier, and version, along with a collection of calendar components.
 * <p>
 * This class follows the builder pattern through the {@link CalendarBuilder} nested class,
 * allowing for flexible and readable construction of calendar objects.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Calendar {

    private final CalendarScale calendarScale;
    private final Method method;
    private final ProductIdentifier productIdentifier;
    private final Version version;
    private final Owner owner;
    private final PrimaryCalendar primaryCalendar;
    private final PublishedTTL publishedTTL;
    private final CalendarDescription calendarDescription;
    private final CalendarName calendarName;
    private final List<CalendarComponent> components;

    private Calendar(
            CalendarScale calendarScale,
            Method method,
            ProductIdentifier productIdentifier,
            Version version,
            Owner owner,
            PrimaryCalendar primaryCalendar,
            PublishedTTL publishedTTL,
            CalendarDescription calendarDescription,
            CalendarName calendarName,
            List<CalendarComponent> components
    ) {
        this.calendarScale = calendarScale;
        this.method = method;
        this.productIdentifier = productIdentifier;
        this.version = version;
        this.owner = owner;
        this.primaryCalendar = primaryCalendar;
        this.publishedTTL = publishedTTL;
        this.calendarDescription = calendarDescription;
        this.calendarName = calendarName;
        this.components = components;
    }

    /**
     * Creates a new CalendarBuilder instance to construct a Calendar object.
     * <p>
     * This factory method provides access to the builder pattern for creating calendar instances
     * with optional properties and components.
     *
     * @return a new CalendarBuilder instance
     */
    public static CalendarBuilder builder() {
        return new CalendarBuilder();
    }

    /**
     * Builder class for constructing Calendar instances using the builder pattern.
     * <p>
     * This builder allows for flexible construction of Calendar objects by providing methods to set
     * optional properties and components. The builder ensures that the resulting Calendar object is
     * properly constructed and formatted.
     */
    public static class CalendarBuilder {
        private CalendarScale calendarScale;
        private Method method;
        private ProductIdentifier productIdentifier;
        private Version version;
        private Owner owner;
        private PrimaryCalendar primaryCalendar;
        private PublishedTTL publishedTTL;
        private CalendarDescription calendarDescription;
        private CalendarName calendarName;
        private List<CalendarComponent> components;

        private CalendarBuilder() {
        }

        /**
         * Sets the calendar scale for this calendar.
         * <p>
         * The calendar scale defines the calendar system used for interpreting dates and times.
         * In most cases, this will be the Gregorian calendar system.
         *
         * @param calendarScale the calendar scale to use
         * @return this builder instance
         */
        public CalendarBuilder withCalendarScale(CalendarScale calendarScale) {
            this.calendarScale = calendarScale;
            return this;
        }

        /**
         * Sets the method property for this calendar.
         * <p>
         * The method property defines the calendar's intended use, such as {@code PUBLISH} for
         * publishing calendar information or REQUEST for scheduling requests.
         *
         * @param method the method property to set
         * @return this builder instance
         */
        public CalendarBuilder withMethod(Method method) {
            this.method = method;
            return this;
        }

        /**
         * Sets the product identifier for this calendar.
         * <p>
         * The product identifier specifies the product that created this calendar.
         * It typically includes the product name and version information.
         *
         * @param productIdentifier the product identifier to set
         * @return this builder instance
         */
        public CalendarBuilder withProductIdentifier(ProductIdentifier productIdentifier) {
            this.productIdentifier = productIdentifier;
            return this;
        }

        /**
         * Sets the version of the iCalendar specification used.
         * <p>
         * The version property specifies which version of the iCalendar specification this calendar
         * conforms to. This is typically "{@code 2.0}" for RFC 5545 compliance.
         *
         * @param version the version to set
         * @return this builder instance
         */
        public CalendarBuilder withVersion(Version version) {
            this.version = version;
            return this;
        }

        /**
         * Sets the owner of the iCalendar.
         *
         * @param owner the owner of a primary calendar
         * @return the builder instance
         */
        public CalendarBuilder withOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        /**
         * Sets if the calendar is a primary calendar.
         *
         * @param primaryCalendar the primary calendar
         * @return the builder instance
         */
        public CalendarBuilder withPrimaryCalendar(PrimaryCalendar primaryCalendar) {
            this.primaryCalendar = primaryCalendar;
            return this;
        }

        /**
         * Sets the published TTL.
         *
         * @param publishedTTL the published TTL
         * @return the builder instance
         */
        public CalendarBuilder withPublishedTTL(PublishedTTL publishedTTL) {
            this.publishedTTL = publishedTTL;
            return this;
        }

        /**
         * Sets the calendar description.
         *
         * @param calendarDescription description of the calendar
         * @return the builder instance
         */
        public CalendarBuilder withCalendarDescription(CalendarDescription calendarDescription) {
            this.calendarDescription = calendarDescription;
            return this;
        }

        /**
         * Sets the calendar name.
         *
         * @param calendarName name of the calendar
         * @return the builder instance
         */
        public CalendarBuilder withCalendarName(CalendarName calendarName) {
            this.calendarName = calendarName;
            return this;
        }

        /**
         * Sets the components to include in this calendar.
         * <p>
         * Components are the main content of the calendar and can include events, tasks,
         * journal entries, and free/busy information.
         *
         * @param components the calendar components to include
         * @return this builder instance
         */
        public CalendarBuilder withComponents(CalendarComponent... components) {
            this.components = List.of(components);
            return this;
        }

        /**
         * Builds and returns a new Calendar instance with the configured properties.
         * <p>
         * This method creates a new Calendar object using the properties set on this builder.
         * The resulting calendar will be properly formatted according to the
         * iCalendar specification.
         *
         * @return a new Calendar instance
         */
        public Calendar build() {
            return new Calendar(
                    calendarScale, method, productIdentifier, version, owner, primaryCalendar,
                    publishedTTL, calendarDescription, calendarName, components
            );
        }
    }

    /**
     * Formats this calendar into a valid iCalendar string representation.
     * <p>
     * This method generates a properly formatted iCalendar string that conforms to RFC 5545
     * specifications. The output includes all calendar properties and components, properly wrapped
     * with {@code BEGIN:VCALENDAR} and {@code END:VCALENDAR} tags.
     *
     * @return a formatted iCalendar string representation of this calendar
     */
    public String formatted() {
        var builder = new StringBuilder();

        builder.append("BEGIN:VCALENDAR");
        builder.append("\n").append(calendarScale.formatted());
        builder.append("\n").append(method.formatted());
        builder.append("\n").append(productIdentifier.formatted());
        builder.append("\n").append(version.formatted());
        builder.append("\n").append(owner.formatted());
        builder.append("\n").append(primaryCalendar.formatted());
        builder.append("\n").append(publishedTTL.formatted());
        builder.append("\n").append(calendarDescription.formatted());
        builder.append("\n").append(calendarName.formatted());
        components.forEach((component) ->
                builder.append("\n").append(component.formatted())
        );

        builder.append("\n").append("END:VCALENDAR");

        return builder.toString();
    }
}
