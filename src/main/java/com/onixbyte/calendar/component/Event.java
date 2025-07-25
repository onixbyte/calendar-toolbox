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
import com.onixbyte.calendar.util.ComponentComposer;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Represents an iCalendar {@code VEVENT} component, which defines a calendar event.
 * <p>
 * The Event class encapsulates all the properties and details of a calendar event, including timing
 * information, attendees, location, and other event-specific data. This class follows the iCalendar
 * specification (RFC 5545) for {@code VEVENT} components.
 * <p>
 * Events can represent meetings, appointments, anniversaries, or any other time-based activity that
 * needs to be recorded in a calendar system.
 * <p>
 * This class follows the builder pattern through the {@link EventBuilder} nested class,
 * allowing for flexible construction of event objects with optional properties.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Event implements CalendarComponent {

    /**
     * The component name for VEVENT as defined in RFC 5545.
     */
    private static final String COMPONENT_NAME = "VEVENT";

    /**
     * The date and time stamp indicating when the event was created. This is a required property.
     */
    private final DateTimeStamp dateTimeStamp;

    /**
     * The unique identifier for this event. This is a required property.
     */
    private final UniqueIdentifier uniqueIdentifier;

    /**
     * The start date and time of the event. This is required if the component appears in an
     * iCalendar object that doesn't specify the method property; otherwise, it is optional.
     */
    private final DateTimeStart dateTimeStart;

    /**
     * The access classification for the event (e.g., PUBLIC, PRIVATE, CONFIDENTIAL). This is an
     * optional property.
     */
    private final Classification classification;

    /**
     * The date and time when the event was created. This is an optional property.
     */
    private final DateTimeCreated dateTimeCreated;

    /**
     * The description or notes for the event. This is an optional property.
     */
    private final Description description;

    /**
     * The geographic position (latitude and longitude) of the event. This is an optional property.
     */
    private final GeographicPosition geographicPosition;

    /**
     * The date and time when the event was last modified. This is an optional property.
     */
    private final LastModified lastModified;

    /**
     * The location where the event takes place. This is an optional property.
     */
    private final Location location;

    /**
     * The organiser of the event. This is an optional property.
     */
    private final Organiser organiser;

    /**
     * The priority level of the event. This is an optional property.
     */
    private final Priority priority;

    /**
     * The sequence number for the event (used for versioning). This is an optional property.
     */
    private final SequenceNumber sequenceNumber;

    /**
     * The status of the event (e.g., TENTATIVE, CONFIRMED, CANCELLED). This is an
     * optional property.
     */
    private final Status status;

    /**
     * The summary or title of the event. This is an optional property.
     */
    private final Summary summary;

    /**
     * The time transparency for the event (e.g., OPAQUE, TRANSPARENT). This is an
     * optional property.
     */
    private final TimeTransparency timeTransparency;

    /**
     * The uniform resource locator (URL) associated with the event. This is an optional property.
     */
    private final UniformResourceLocator uniformResourceLocator;

    /**
     * The recurrence identifier for the event. This is an optional property.
     */
    private final RecurrenceId recurrenceId;

    /**
     * The recurrence rule for the event. This is an optional property that should not occur more
     * than once.
     */
    private final RecurrenceRule recurrenceRule;

    /**
     * The end date and time of the event. Either this or duration may appear in an event, but
     * not both. This is an optional property.
     */
    private final DateTimeEnd dateTimeEnd;

    /**
     * The duration of the event. Either this or dateTimeEnd may appear in an event, but not both.
     * This is an optional property.
     */
    private final Duration duration;

    /**
     * The list of attachments associated with the event. This is an optional property that may
     * occur more than once.
     */
    private final List<Attachment> attachments;

    /**
     * The list of attendees for the event. This is an optional property that may occur more
     * than once.
     */
    private final List<Attendee> attendees;

    /**
     * The list of categories for the event. This is an optional property that may occur more
     * than once.
     */
    private final List<Categories> categories;

    /**
     * The list of comments for the event. This is an optional property that may occur more
     * than once.
     */
    private final List<Comment> comments;

    /**
     * The list of contacts for the event. This is an optional property that may occur more
     * than once.
     */
    private final List<Contact> contacts;

    /**
     * The list of exception date and times for the event. This is an optional property that may
     * occur more than once.
     */
    private final List<ExceptionDateTimes> exceptionDateTimes;

    /**
     * The list of request statuses for the event. This is an optional property that may occur more
     * than once.
     */
    private final List<RequestStatus> requestStatuses;

    /**
     * The list of related events. This is an optional property that may occur more than once.
     */
    private final List<RelatedTo> related;

    /**
     * The list of resources for the event. This is an optional property that may occur more
     * than once.
     */
    private final List<Resources> resources;

    /**
     * The list of recurrence date and times for the event. This is an optional property that may
     * occur more than once.
     */
    private final List<RecurrenceDateTimes> recurrenceDateTimes;

    /**
     * Constructs a new Event instance with the specified properties.
     * <p>
     * This constructor is private to enforce the use of the builder pattern for creating
     * Event instances. Use {@link #builder()} to create new Event objects.
     *
     * @param dateTimeStamp          the date and time stamp when the event was created (required)
     * @param uniqueIdentifier       the unique identifier for this event (required)
     * @param dateTimeStart          the start date and time of the event
     * @param classification         the access classification for the event
     * @param dateTimeCreated        the date and time when the event was created
     * @param description            the description or notes for the event
     * @param geographicPosition     the geographic position of the event
     * @param lastModified           the date and time when the event was last modified
     * @param location               the location where the event takes place
     * @param organiser              the organiser of the event
     * @param priority               the priority level of the event
     * @param sequenceNumber         the sequence number for the event
     * @param status                 the status of the event
     * @param summary                the summary or title of the event
     * @param timeTransparency       the time transparency for the event
     * @param uniformResourceLocator the URL associated with the event
     * @param recurrenceId           the recurrence identifier for the event
     * @param recurrenceRule         the recurrence rule for the event
     * @param dateTimeEnd            the end date and time of the event
     * @param duration               the duration of the event
     * @param attachments            the list of attachments associated with the event
     * @param attendees              the list of attendees for the event
     * @param categories             the list of categories for the event
     * @param comments               the list of comments for the event
     * @param contacts               the list of contacts for the event
     * @param exceptionDateTimes     the list of exception date and times for the event
     * @param requestStatuses        the list of request statuses for the event
     * @param related                the list of related events
     * @param resources              the list of resources for the event
     * @param recurrenceDateTimes    the list of recurrence date and times for the event
     */
    private Event(
            DateTimeStamp dateTimeStamp,
            UniqueIdentifier uniqueIdentifier,
            DateTimeStart dateTimeStart,
            Classification classification,
            DateTimeCreated dateTimeCreated,
            Description description,
            GeographicPosition geographicPosition,
            LastModified lastModified,
            Location location,
            Organiser organiser,
            Priority priority,
            SequenceNumber sequenceNumber,
            Status status,
            Summary summary,
            TimeTransparency timeTransparency,
            UniformResourceLocator uniformResourceLocator,
            RecurrenceId recurrenceId,
            RecurrenceRule recurrenceRule,
            DateTimeEnd dateTimeEnd,
            Duration duration,
            List<Attachment> attachments,
            List<Attendee> attendees,
            List<Categories> categories,
            List<Comment> comments,
            List<Contact> contacts,
            List<ExceptionDateTimes> exceptionDateTimes,
            List<RequestStatus> requestStatuses,
            List<RelatedTo> related,
            List<Resources> resources,
            List<RecurrenceDateTimes> recurrenceDateTimes
    ) {
        this.dateTimeStamp = dateTimeStamp;
        this.uniqueIdentifier = uniqueIdentifier;
        this.dateTimeStart = dateTimeStart;
        this.classification = classification;
        this.dateTimeCreated = dateTimeCreated;
        this.description = description;
        this.geographicPosition = geographicPosition;
        this.lastModified = lastModified;
        this.location = location;
        this.organiser = organiser;
        this.priority = priority;
        this.sequenceNumber = sequenceNumber;
        this.status = status;
        this.summary = summary;
        this.timeTransparency = timeTransparency;
        this.uniformResourceLocator = uniformResourceLocator;
        this.recurrenceId = recurrenceId;
        this.recurrenceRule = recurrenceRule;
        this.dateTimeEnd = dateTimeEnd;
        this.duration = duration;
        this.attachments = attachments;
        this.attendees = attendees;
        this.categories = categories;
        this.comments = comments;
        this.contacts = contacts;
        this.exceptionDateTimes = exceptionDateTimes;
        this.requestStatuses = requestStatuses;
        this.related = related;
        this.resources = resources;
        this.recurrenceDateTimes = recurrenceDateTimes;
    }

    /**
     * Creates a new builder instance for constructing Event objects.
     * <p>
     * This is the preferred way to create Event instances, as it provides a flexible
     * and readable approach to setting the various optional and required properties.
     *
     * @return a new EventBuilder instance
     */
    public static EventBuilder builder() {
        return new EventBuilder();
    }

    /**
     * Builder class for constructing Event instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating Event objects with the required
     * dateTimeStamp and uniqueIdentifier properties, and various optional properties.
     * The builder enforces RFC 5545 constraints, such as ensuring that dateTimeEnd and
     * duration are mutually exclusive.
     * <p>
     * Example usage:
     * <pre>{@code
     * Event event = Event.builder()
     *     .withDateTimeStamp(dateTimeStamp)
     *     .withUniqueIdentifier(uniqueId)
     *     .withSummary(summary)
     *     .withLocation(location)
     *     .build();
     * }</pre>
     */
    public static class EventBuilder {
        /**
         * The date and time stamp for the event being built.
         */
        private DateTimeStamp dateTimeStamp;

        /**
         * The unique identifier for the event being built.
         */
        private UniqueIdentifier uniqueIdentifier;

        /**
         * The start date and time for the event being built.
         */
        private DateTimeStart dateTimeStart;

        /**
         * The classification for the event being built.
         */
        private Classification classification;

        /**
         * The creation date and time for the event being built.
         */
        private DateTimeCreated dateTimeCreated;

        /**
         * The description for the event being built.
         */
        private Description description;

        /**
         * The geographic position for the event being built.
         */
        private GeographicPosition geographicPosition;

        /**
         * The last modified date and time for the event being built.
         */
        private LastModified lastModified;

        /**
         * The location for the event being built.
         */
        private Location location;

        /**
         * The organiser for the event being built.
         */
        private Organiser organiser;

        /**
         * The priority for the event being built.
         */
        private Priority priority;

        /**
         * The sequence number for the event being built.
         */
        private SequenceNumber sequenceNumber;

        /**
         * The status for the event being built.
         */
        private Status status;

        /**
         * The summary for the event being built.
         */
        private Summary summary;

        /**
         * The time transparency for the event being built.
         */
        private TimeTransparency timeTransparency;

        /**
         * The URL for the event being built.
         */
        private UniformResourceLocator uniformResourceLocator;

        /**
         * The recurrence identifier for the event being built.
         */
        private RecurrenceId recurrenceId;

        /**
         * The recurrence rule for the event being built.
         */
        private RecurrenceRule recurrenceRule;

        /**
         * The end date and time for the event being built.
         */
        private DateTimeEnd dateTimeEnd;

        /**
         * The duration for the event being built.
         */
        private Duration duration;

        /**
         * The list of attachments for the event being built.
         */
        private List<Attachment> attachments;

        /**
         * The list of attendees for the event being built.
         */
        private List<Attendee> attendees;

        /**
         * The list of categories for the event being built.
         */
        private List<Categories> categories;

        /**
         * The list of comments for the event being built.
         */
        private List<Comment> comments;

        /**
         * The list of contacts for the event being built.
         */
        private List<Contact> contacts;

        /**
         * The list of exception date and times for the event being built.
         */
        private List<ExceptionDateTimes> exceptionDateTimes;

        /**
         * The list of request statuses for the event being built.
         */
        private List<RequestStatus> requestStatuses;

        /**
         * The list of related events for the event being built.
         */
        private List<RelatedTo> related;

        /**
         * The list of resources for the event being built.
         */
        private List<Resources> resources;

        /**
         * The list of recurrence date and times for the event being built.
         */
        private List<RecurrenceDateTimes> recurrenceDateTimes;

        /**
         * Private constructor to enforce use through the factory method.
         */
        private EventBuilder() {
        }

        /**
         * Sets the date and time stamp for the event.
         *
         * @param dateTimeStamp the date and time stamp when the event was created (required)
         * @return this builder instance for method chaining
         */
        public EventBuilder withDateTimeStamp(DateTimeStamp dateTimeStamp) {
            this.dateTimeStamp = dateTimeStamp;
            return this;
        }

        /**
         * Sets the unique identifier for the event.
         *
         * @param uniqueIdentifier the unique identifier for this event (required)
         * @return this builder instance for method chaining
         */
        public EventBuilder withUniqueIdentifier(UniqueIdentifier uniqueIdentifier) {
            this.uniqueIdentifier = uniqueIdentifier;
            return this;
        }

        /**
         * Sets the start date and time for the event.
         *
         * @param dateTimeStart the start date and time of the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withDateTimeStart(DateTimeStart dateTimeStart) {
            this.dateTimeStart = dateTimeStart;
            return this;
        }

        /**
         * Sets the access classification for the event.
         *
         * @param classification the access classification (e.g., PUBLIC, PRIVATE, CONFIDENTIAL)
         * @return this builder instance for method chaining
         */
        public EventBuilder withClassification(Classification classification) {
            this.classification = classification;
            return this;
        }

        /**
         * Sets the creation date and time for the event.
         *
         * @param dateTimeCreated the date and time when the event was created
         * @return this builder instance for method chaining
         */
        public EventBuilder withDateTimeCreated(DateTimeCreated dateTimeCreated) {
            this.dateTimeCreated = dateTimeCreated;
            return this;
        }

        /**
         * Sets the description for the event.
         *
         * @param description the description or notes for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withDescription(Description description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the geographic position for the event.
         *
         * @param geographicPosition the geographic position (latitude and longitude) of the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withGeographicPosition(GeographicPosition geographicPosition) {
            this.geographicPosition = geographicPosition;
            return this;
        }

        /**
         * Sets the last modified date and time for the event.
         *
         * @param lastModified the date and time when the event was last modified
         * @return this builder instance for method chaining
         */
        public EventBuilder withLastModified(LastModified lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        /**
         * Sets the location for the event.
         *
         * @param location the location where the event takes place
         * @return this builder instance for method chaining
         */
        public EventBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }

        /**
         * Sets the organiser for the event.
         *
         * @param organiser the organiser of the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withOrganiser(Organiser organiser) {
            this.organiser = organiser;
            return this;
        }

        /**
         * Sets the priority level for the event.
         *
         * @param priority the priority level of the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        /**
         * Sets the sequence number for the event.
         *
         * @param sequenceNumber the sequence number for the event (used for versioning)
         * @return this builder instance for method chaining
         */
        public EventBuilder withSequenceNumber(SequenceNumber sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
            return this;
        }

        /**
         * Sets the status for the event.
         *
         * @param status the status of the event (e.g., TENTATIVE, CONFIRMED, CANCELLED)
         * @return this builder instance for method chaining
         */
        public EventBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        /**
         * Sets the summary for the event.
         *
         * @param summary the summary or title of the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withSummary(Summary summary) {
            this.summary = summary;
            return this;
        }

        /**
         * Sets the time transparency for the event.
         *
         * @param timeTransparency the time transparency for the event (e.g., OPAQUE, TRANSPARENT)
         * @return this builder instance for method chaining
         */
        public EventBuilder withTimeTransparency(TimeTransparency timeTransparency) {
            this.timeTransparency = timeTransparency;
            return this;
        }

        /**
         * Sets the URL for the event.
         *
         * @param uniformResourceLocator the uniform resource locator (URL) associated with the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withUniformResourceLocator(UniformResourceLocator uniformResourceLocator) {
            this.uniformResourceLocator = uniformResourceLocator;
            return this;
        }

        /**
         * Sets the recurrence identifier for the event.
         *
         * @param recurrenceId the recurrence identifier for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withRecurrenceId(RecurrenceId recurrenceId) {
            this.recurrenceId = recurrenceId;
            return this;
        }

        /**
         * Sets the recurrence rule for the event.
         *
         * @param recurrenceRule the recurrence rule for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withRecurrenceRule(RecurrenceRule recurrenceRule) {
            this.recurrenceRule = recurrenceRule;
            return this;
        }

        /**
         * Sets the end date and time for the event.
         * Cannot be used together with {@link #withDuration(Duration)}.
         *
         * @param dateTimeEnd the end date and time of the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withDateTimeEnd(DateTimeEnd dateTimeEnd) {
            this.dateTimeEnd = dateTimeEnd;
            return this;
        }

        /**
         * Sets the duration for the event.
         * Cannot be used together with {@link #withDateTimeEnd(DateTimeEnd)}.
         *
         * @param duration the duration of the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Sets the attachments for the event.
         *
         * @param attachments the list of attachments associated with the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        /**
         * Sets the attendees for the event.
         *
         * @param attendees the list of attendees for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withAttendees(List<Attendee> attendees) {
            this.attendees = attendees;
            return this;
        }

        /**
         * Sets the categories for the event.
         *
         * @param categories the list of categories for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withCategories(List<Categories> categories) {
            this.categories = categories;
            return this;
        }

        /**
         * Sets the comments for the event.
         *
         * @param comments the list of comments for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withComments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        /**
         * Sets the contacts for the event.
         *
         * @param contacts the list of contacts for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withContacts(List<Contact> contacts) {
            this.contacts = contacts;
            return this;
        }

        /**
         * Sets the exception date and times for the event.
         *
         * @param exceptionDateTimes the list of exception date and times for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withExceptionDateTimes(List<ExceptionDateTimes> exceptionDateTimes) {
            this.exceptionDateTimes = exceptionDateTimes;
            return this;
        }

        /**
         * Sets the request statuses for the event.
         *
         * @param requestStatuses the list of request statuses for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withRequestStatuses(List<RequestStatus> requestStatuses) {
            this.requestStatuses = requestStatuses;
            return this;
        }

        /**
         * Sets the related events.
         *
         * @param related the list of related events
         * @return this builder instance for method chaining
         */
        public EventBuilder withRelated(List<RelatedTo> related) {
            this.related = related;
            return this;
        }

        /**
         * Sets the resources for the event.
         *
         * @param resources the list of resources for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withResources(List<Resources> resources) {
            this.resources = resources;
            return this;
        }

        /**
         * Sets the recurrence date and times for the event.
         *
         * @param recurrenceDateTimes the list of recurrence date and times for the event
         * @return this builder instance for method chaining
         */
        public EventBuilder withRecurrenceDateTimes(List<RecurrenceDateTimes> recurrenceDateTimes) {
            this.recurrenceDateTimes = recurrenceDateTimes;
            return this;
        }

        /**
         * Builds and returns a new Event instance.
         * <p>
         * Validates that required properties (dateTimeStamp and uniqueIdentifier) are present and
         * that mutually exclusive properties (dateTimeEnd and duration) are not both set.
         *
         * @return a new Event instance
         * @throws IllegalArgumentException if required properties are missing or if
         *                                  both dateTimeEnd and duration are set
         */
        public Event build() {
            if (Objects.isNull(dateTimeStamp)) {
                throw new IllegalArgumentException("The `dtstamp` property is required and must not be null.");
            }
            if (Objects.isNull(uniqueIdentifier)) {
                throw new IllegalArgumentException("The `uid` property is required and must not be null.");
            }
            if (Objects.nonNull(dateTimeEnd) && Objects.nonNull(duration)) {
                throw new IllegalArgumentException("The `dtend` and `duration` properties must not both be set.");
            }

            return new Event(
                    dateTimeStamp, uniqueIdentifier, dateTimeStart, classification, dateTimeCreated,
                    description, geographicPosition, lastModified, location, organiser, priority,
                    sequenceNumber, status, summary, timeTransparency, uniformResourceLocator,
                    recurrenceId, recurrenceRule, dateTimeEnd, duration, attachments, attendees,
                    categories, comments, contacts, exceptionDateTimes, requestStatuses, related,
                    resources, recurrenceDateTimes
            );
        }
    }

    /**
     * Returns the formatted iCalendar representation of this event component.
     * <p>
     * The format follows the iCalendar specification (RFC 5545) with {@code BEGIN:VEVENT} and
     * {@code END:VEVENT} delimiters containing all the event properties. Required properties
     * are included first, followed by optional properties, with special handling for mutually
     * exclusive properties like dateTimeEnd and duration.
     *
     * @return the formatted iCalendar {@code VEVENT} component string
     */
    public String formatted() {
        var composer = ComponentComposer.of(COMPONENT_NAME);

        composer.start()
                .append(dateTimeStamp)
                .append(uniqueIdentifier)
                .append(dateTimeStart)
                .append(classification)
                .append(dateTimeCreated)
                .append(description)
                .append(geographicPosition)
                .append(lastModified)
                .append(location)
                .append(organiser)
                .append(priority)
                .append(sequenceNumber)
                .append(status)
                .append(summary)
                .append(timeTransparency)
                .append(uniformResourceLocator)
                .append(recurrenceId)
                .append(recurrenceRule);

        if (Objects.nonNull(dateTimeEnd)) {
            composer.append(dateTimeEnd);
        } else if (Objects.nonNull(duration)) {
            composer.append(duration);
        }

        composer.append(attachments)
                .append(attendees)
                .append(categories)
                .append(comments)
                .append(contacts)
                .append(exceptionDateTimes)
                .append(requestStatuses)
                .append(related)
                .append(resources)
                .append(recurrenceDateTimes);

        return composer.end();
    }
}
