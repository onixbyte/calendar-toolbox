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

import com.onixbyte.calendar.component.property.*;
import com.onixbyte.calendar.util.PropertyAppender;

import java.util.List;

/**
 * Represents a {@code VFREEBUSY} component in an iCalendar object.
 * <p>
 * This component provides a grouping of component properties that describe either a request for
 * free or busy time information, a reply to a request for free or busy time information, or a
 * published set of busy time information.
 * <p>
 * The free/busy time information is used by scheduling applications to determine when calendar
 * users are available for meetings or appointments.
 * <p>
 * This component follows RFC 5545 specifications for free/busy components.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class FreeBusy implements CalendarComponent {

    /**
     * The component name identifier for this free/busy component.
     */
    public static final String COMPONENT_NAME = "VFREEBUSY";

    /**
     * The date-time stamp property (required).
     */
    private final DateTimeStamp dateTimeStamp;

    /**
     * The unique identifier property (required).
     */
    private final UniqueIdentifier uniqueIdentifier;

    /**
     * The optional contact property.
     */
    private final Contact contact;

    /**
     * The optional start date-time property.
     */
    private final DateTimeStart dateTimeStart;

    /**
     * The optional end date-time property.
     */
    private final DateTimeEnd dateTimeEnd;

    /**
     * The optional organiser property.
     */
    private final Organiser organiser;

    /**
     * The optional uniform resource locator property.
     */
    private final UniformResourceLocator uniformResourceLocator;

    /**
     * The optional list of attendee properties.
     */
    private final List<Attendee> attendees;

    /**
     * The optional list of comment properties.
     */
    private final List<Comment> comments;

    /**
     * The optional list of free/busy time properties.
     */
    private final List<FreeBusyTime> freeBusyTimes;

    /**
     * The optional list of request status properties.
     */
    private final List<RequestStatus> requestStatuses;

    /**
     * Constructs a new {@code FreeBusy} component with the specified properties.
     *
     * @param dateTimeStamp          the date-time stamp property (required)
     * @param uniqueIdentifier       the unique identifier property (required)
     * @param contact                the optional contact property
     * @param dateTimeStart          the optional start date-time property
     * @param dateTimeEnd            the optional end date-time property
     * @param organiser              the optional organiser property
     * @param uniformResourceLocator the optional uniform resource locator property
     * @param attendees              the optional list of attendee properties
     * @param comments               the optional list of comment properties
     * @param freeBusyTimes          the optional list of free/busy time properties
     * @param requestStatuses        the optional list of request status properties
     */
    private FreeBusy(
            DateTimeStamp dateTimeStamp,
            UniqueIdentifier uniqueIdentifier,
            Contact contact,
            DateTimeStart dateTimeStart,
            DateTimeEnd dateTimeEnd,
            Organiser organiser,
            UniformResourceLocator uniformResourceLocator,
            List<Attendee> attendees,
            List<Comment> comments,
            List<FreeBusyTime> freeBusyTimes,
            List<RequestStatus> requestStatuses
    ) {
        this.dateTimeStamp = dateTimeStamp;
        this.uniqueIdentifier = uniqueIdentifier;
        this.contact = contact;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.organiser = organiser;
        this.uniformResourceLocator = uniformResourceLocator;
        this.attendees = attendees;
        this.comments = comments;
        this.freeBusyTimes = freeBusyTimes;
        this.requestStatuses = requestStatuses;
    }

    /**
     * Creates a new builder for constructing {@code FreeBusy} instances.
     *
     * @return a new {@code FreeBusyBuilder}
     */
    public static FreeBusyBuilder builder() {
        return new FreeBusyBuilder();
    }

    /**
     * Builder class for creating {@code FreeBusy} instances with various properties.
     * <p>
     * This builder provides a fluent interface for constructing FreeBusy components with
     * required and optional properties. The builder enforces that only valid combinations
     * of properties are used according to RFC 5545 specifications.
     * <p>
     * Example usage:
     * <pre>{@code
     * FreeBusy freeBusy = FreeBusy.builder()
     *     .withDateTimeStamp(dateTimeStamp)
     *     .withUniqueIdentifier(uniqueId)
     *     .withOrganiser(organiser)
     *     .withFreeBusyTimes(freeBusyTime1, freeBusyTime2)
     *     .build();
     * }</pre>
     */
    public static class FreeBusyBuilder {
        /**
         * The date-time stamp property for the free/busy component being built.
         */
        private DateTimeStamp dateTimeStamp;

        /**
         * The unique identifier property for the free/busy component being built.
         */
        private UniqueIdentifier uniqueIdentifier;

        /**
         * The contact property for the free/busy component being built.
         */
        private Contact contact;

        /**
         * The start date-time property for the free/busy component being built.
         */
        private DateTimeStart dateTimeStart;

        /**
         * The end date-time property for the free/busy component being built.
         */
        private DateTimeEnd dateTimeEnd;

        /**
         * The organiser property for the free/busy component being built.
         */
        private Organiser organiser;

        /**
         * The uniform resource locator property for the free/busy component being built.
         */
        private UniformResourceLocator uniformResourceLocator;

        /**
         * The list of attendee properties for the free/busy component being built.
         */
        private List<Attendee> attendees;

        /**
         * The list of comment properties for the free/busy component being built.
         */
        private List<Comment> comments;

        /**
         * The list of free/busy time properties for the free/busy component being built.
         */
        private List<FreeBusyTime> freeBusyTimes;

        /**
         * The list of request status properties for the free/busy component being built.
         */
        private List<RequestStatus> requestStatuses;

        /**
         * Private constructor to enforce use through the factory method.
         */
        private FreeBusyBuilder() {
        }

        /**
         * Sets the date-time stamp property for the free/busy component.
         *
         * @param dateTimeStamp the date-time stamp property (required)
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withDateTimeStamp(DateTimeStamp dateTimeStamp) {
            this.dateTimeStamp = dateTimeStamp;
            return this;
        }

        /**
         * Sets the unique identifier property for the free/busy component.
         *
         * @param uniqueIdentifier the unique identifier property (required)
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withUniqueIdentifier(UniqueIdentifier uniqueIdentifier) {
            this.uniqueIdentifier = uniqueIdentifier;
            return this;
        }

        /**
         * Sets the contact property for the free/busy component.
         *
         * @param contact the contact property
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        /**
         * Sets the start date-time property for the free/busy component.
         *
         * @param dateTimeStart the start date-time property
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withDateTimeStart(DateTimeStart dateTimeStart) {
            this.dateTimeStart = dateTimeStart;
            return this;
        }

        /**
         * Sets the end date-time property for the free/busy component.
         *
         * @param dateTimeEnd the end date-time property
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withDateTimeEnd(DateTimeEnd dateTimeEnd) {
            this.dateTimeEnd = dateTimeEnd;
            return this;
        }

        /**
         * Sets the organiser property for the free/busy component.
         *
         * @param organiser the organiser property
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withOrganiser(Organiser organiser) {
            this.organiser = organiser;
            return this;
        }

        /**
         * Sets the uniform resource locator property for the free/busy component.
         *
         * @param uniformResourceLocator the uniform resource locator property
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withUniformResourceLocator(UniformResourceLocator uniformResourceLocator) {
            this.uniformResourceLocator = uniformResourceLocator;
            return this;
        }

        /**
         * Sets the attendee properties for the free/busy component.
         *
         * @param attendees the attendee properties
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withAttendees(Attendee... attendees) {
            this.attendees = List.of(attendees);
            return this;
        }

        /**
         * Sets the comment properties for the free/busy component.
         *
         * @param comments the comment properties
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withComments(Comment... comments) {
            this.comments = List.of(comments);
            return this;
        }

        /**
         * Sets the free/busy time properties for the free/busy component.
         *
         * @param freeBusyTimes the free/busy time properties
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withFreeBusyTimes(FreeBusyTime... freeBusyTimes) {
            this.freeBusyTimes = List.of(freeBusyTimes);
            return this;
        }

        /**
         * Sets the request status properties for the free/busy component.
         *
         * @param requestStatuses the request status properties
         * @return this builder instance for method chaining
         */
        public FreeBusyBuilder withRequestStatuses(RequestStatus... requestStatuses) {
            this.requestStatuses = List.of(requestStatuses);
            return this;
        }

        /**
         * Builds and returns a new FreeBusy instance.
         * <p>
         * Creates a FreeBusy component with all the properties that have been set on this builder.
         * Note that dateTimeStamp and uniqueIdentifier are required properties for a valid
         * free/busy component according to RFC 5545, but this method does not perform validation.
         *
         * @return a new FreeBusy instance
         */
        public FreeBusy build() {
            return new FreeBusy(
                    dateTimeStamp, uniqueIdentifier, contact, dateTimeStart, dateTimeEnd, organiser,
                    uniformResourceLocator, attendees, comments, freeBusyTimes, requestStatuses
            );
        }
    }

    /**
     * Returns the formatted iCalendar representation of this free/busy component.
     * <p>
     * The format follows the iCalendar specification (RFC 5545) with {@code BEGIN:VFREEBUSY} and
     * {@code END:VFREEBUSY} delimiters containing all the free/busy properties. Required properties
     * are included first, followed by optional properties.
     *
     * @return the formatted iCalendar {@code VFREEBUSY} component string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        var propertyAppender = PropertyAppender.of(builder);

        builder.append("BEGIN:").append(COMPONENT_NAME);

        propertyAppender.append(dateTimeStamp);
        propertyAppender.append(uniqueIdentifier);
        propertyAppender.append(contact);
        propertyAppender.append(dateTimeStart);
        propertyAppender.append(dateTimeEnd);
        propertyAppender.append(organiser);
        propertyAppender.append(contact);
        propertyAppender.append(uniformResourceLocator);
        propertyAppender.append(attendees);
        propertyAppender.append(comments);
        propertyAppender.append(freeBusyTimes);
        propertyAppender.append(requestStatuses);

        builder.append("\n").append("END:").append(COMPONENT_NAME);

        return builder.toString();
    }
}
