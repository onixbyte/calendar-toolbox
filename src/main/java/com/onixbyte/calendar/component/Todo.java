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

import com.onixbyte.calendar.property.*;
import com.onixbyte.calendar.util.PropertyAppender;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Represents an iCalendar {@code VTODO} component, which defines a to-do or task.
 * <p>
 * The To-do class encapsulates all the properties and details of a calendar to-do item, including
 * timing information, completion status, priority, and other task-specific data. This class follows
 * the iCalendar specification (RFC 5545) for {@code VTODO} components.
 * <p>
 * To-dos represent tasks that need to be accomplished, such as assignments, reminders, or action
 * items that can be tracked in a calendar system.
 * <p>
 * This class follows the builder pattern through the {@link TodoBuilder} nested class,
 * allowing for flexible construction of to-do objects with optional properties.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Todo implements ComponentProperty {

    /**
     * The component name for {@code VTODO} as defined in RFC 5545.
     */
    private final static String COMPONENT_NAME = "VTODO";

    /**
     * The date and time stamp indicating when the to-do was created. This is a required property.
     */
    private final DateTimeStamp dateTimeStamp;

    /**
     * The unique identifier for this to-do. This is a required property.
     */
    private final UniqueIdentifier uniqueIdentifier;

    /**
     * The access classification for the to-do (e.g., PUBLIC, PRIVATE, CONFIDENTIAL). This is an
     * optional property.
     */
    private final Classification classification;

    /**
     * The date and time when the to-do was completed. This is an optional property.
     */
    private final DateTimeCompleted dateTimeCompleted;

    /**
     * The date and time when the to-do was created. This is an optional property.
     */
    private final DateTimeCreated dateTimeCreated;

    /**
     * The description or notes for the to-do. This is an optional property.
     */
    private final Description description;

    /**
     * The start date and time of the to-do. This is an optional property.
     */
    private final DateTimeStart dateTimeStart;

    /**
     * The geographic position (latitude and longitude) of the to-do. This is an optional property.
     */
    private final GeographicPosition geographicPosition;

    /**
     * The date and time when the to-do was last modified. This is an optional property.
     */
    private final LastModified lastModified;

    /**
     * The location where the to-do takes place. This is an optional property.
     */
    private final Location location;

    /**
     * The organiser of the to-do. This is an optional property.
     */
    private final Organiser organiser;

    /**
     * The percentage completion of the to-do. This is an optional property.
     */
    private final PercentComplete percentComplete;

    /**
     * The priority level of the to-do. This is an optional property.
     */
    private final Priority priority;

    /**
     * The recurrence identifier for the to-do. This is an optional property.
     */
    private final RecurrenceId recurrenceId;

    /**
     * The sequence number for the to-do (used for versioning). This is an optional property.
     */
    private final SequenceNumber sequenceNumber;

    /**
     * The status of the to-do (e.g., NEEDS-ACTION, COMPLETED, IN-PROGRESS). This is an
     * optional property.
     */
    private final Status status;

    /**
     * The summary or title of the to-do. This is an optional property.
     */
    private final Summary summary;

    /**
     * The uniform resource locator (URL) associated with the to-do. This is an optional property.
     */
    private final UniformResourceLocator uniformResourceLocator;

    /**
     * The recurrence rule for the to-do. This is an optional property that should not occur more
     * than once.
     */
    private final RecurrenceRule recurrenceRule;

    /**
     * The due date and time of the to-do. Either this or duration may appear in a to-do, but
     * not both. This is an optional property.
     */
    private final DateTimeDue dateTimeDue;

    /**
     * The duration of the to-do. Either this or dateTimeDue may appear in a to-do, but not both. If
     * duration appears, then dateTimeStart must also appear. This is an optional property.
     */
    private final Duration duration;

    /**
     * The list of attachments associated with the to-do. This is an optional property that may
     * occur more than once.
     */
    private final List<Attachment> attachments;

    /**
     * The list of attendees for the to-do. This is an optional property that may occur more
     * than once.
     */
    private final List<Attendee> attendees;

    /**
     * The list of categories for the to-do. This is an optional property that may occur more
     * than once.
     */
    private final List<Categories> categories;

    /**
     * The list of comments for the to-do. This is an optional property that may occur more
     * than once.
     */
    private final List<Comment> comments;

    /**
     * The list of contacts for the to-do. This is an optional property that may occur more
     * than once.
     */
    private final List<Contact> contacts;

    /**
     * The list of exception date and times for the to-do. This is an optional property that may
     * occur more than once.
     */
    private final List<ExceptionDateTimes> exceptionDateTimes;

    /**
     * The list of request statuses for the to-do. This is an optional property that may occur more
     * than once.
     */
    private final List<RequestStatus> requestStatuses;

    /**
     * The list of related to-dos. This is an optional property that may occur more than once.
     */
    private final List<RelatedTo> relatedToList;

    /**
     * The list of resources for the to-do. This is an optional property that may occur more
     * than once.
     */
    private final List<Resources> resources;

    /**
     * The list of recurrence date and times for the to-do. This is an optional property that may
     * occur more than once.
     */
    private final List<RecurrenceDateTimes> recurrenceDateTimes;

    /**
     * Constructs a new To-do instance with the specified properties.
     * <p>
     * This constructor is private to enforce the use of the builder pattern for creating
     * {@code To-do} instances. Use {@link #builder()} to create new To-do objects.
     *
     * @param dateTimeStamp          the date and time stamp when the to-do was created
     * @param uniqueIdentifier       the unique identifier for this to-do
     * @param classification         the access classification for the to-do
     * @param dateTimeCompleted      the date and time when the to-do was completed
     * @param dateTimeCreated        the date and time when the to-do was created
     * @param description            the description or notes for the to-do
     * @param dateTimeStart          the start date and time of the to-do
     * @param geographicPosition     the geographic position of the to-do
     * @param lastModified           the date and time when the to-do was last modified
     * @param location               the location where the to-do takes place
     * @param organiser              the organiser of the to-do
     * @param percentComplete        the percentage completion of the to-do
     * @param priority               the priority level of the to-do
     * @param recurrenceId           the recurrence identifier for the to-do
     * @param sequenceNumber         the sequence number for the to-do
     * @param status                 the status of the to-do
     * @param summary                the summary or title of the to-do
     * @param uniformResourceLocator the URL associated with the to-do
     * @param recurrenceRule         the recurrence rule for the to-do
     * @param dateTimeDue            the due date and time of the to-do
     * @param duration               the duration of the to-do
     * @param attachments            the list of attachments associated with the to-do
     * @param attendees              the list of attendees for the to-do
     * @param categories             the list of categories for the to-do
     * @param comments               the list of comments for the to-do
     * @param contacts               the list of contacts for the to-do
     * @param exceptionDateTimes     the list of exception date and times for the to-do
     * @param requestStatuses        the list of request statuses for the to-do
     * @param relatedToList          the list of related to-dos
     * @param resources              the list of resources for the to-do
     * @param recurrenceDateTimes    the list of recurrence date and times for the to-do
     */
    private Todo(
            DateTimeStamp dateTimeStamp,
            UniqueIdentifier uniqueIdentifier,
            Classification classification,
            DateTimeCompleted dateTimeCompleted,
            DateTimeCreated dateTimeCreated,
            Description description,
            DateTimeStart dateTimeStart,
            GeographicPosition geographicPosition,
            LastModified lastModified,
            Location location,
            Organiser organiser,
            PercentComplete percentComplete,
            Priority priority,
            RecurrenceId recurrenceId,
            SequenceNumber sequenceNumber,
            Status status,
            Summary summary,
            UniformResourceLocator uniformResourceLocator,
            RecurrenceRule recurrenceRule,
            DateTimeDue dateTimeDue,
            Duration duration,
            List<Attachment> attachments,
            List<Attendee> attendees,
            List<Categories> categories,
            List<Comment> comments,
            List<Contact> contacts,
            List<ExceptionDateTimes> exceptionDateTimes,
            List<RequestStatus> requestStatuses,
            List<RelatedTo> relatedToList,
            List<Resources> resources,
            List<RecurrenceDateTimes> recurrenceDateTimes
    ) {
        this.dateTimeStamp = dateTimeStamp;
        this.uniqueIdentifier = uniqueIdentifier;
        this.classification = classification;
        this.dateTimeCompleted = dateTimeCompleted;
        this.dateTimeCreated = dateTimeCreated;
        this.description = description;
        this.dateTimeStart = dateTimeStart;
        this.geographicPosition = geographicPosition;
        this.lastModified = lastModified;
        this.location = location;
        this.organiser = organiser;
        this.percentComplete = percentComplete;
        this.priority = priority;
        this.recurrenceId = recurrenceId;
        this.sequenceNumber = sequenceNumber;
        this.status = status;
        this.summary = summary;
        this.uniformResourceLocator = uniformResourceLocator;
        this.recurrenceRule = recurrenceRule;
        this.dateTimeDue = dateTimeDue;
        this.duration = duration;
        this.attachments = attachments;
        this.attendees = attendees;
        this.categories = categories;
        this.comments = comments;
        this.contacts = contacts;
        this.exceptionDateTimes = exceptionDateTimes;
        this.requestStatuses = requestStatuses;
        this.relatedToList = relatedToList;
        this.resources = resources;
        this.recurrenceDateTimes = recurrenceDateTimes;
    }

    /**
     * Creates a new builder instance for constructing To-do objects.
     * <p>
     * This is the preferred way to create To-do instances, as it provides a flexible and readable
     * approach to setting the various optional and required properties.
     *
     * @return a new TodoBuilder instance
     */
    public static TodoBuilder builder() {
        return new TodoBuilder();
    }

    /**
     * Builder class for constructing {@code To-do} instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating {@code To-do} objects with the required
     * {@code dateTimeStamp} and uniqueIdentifier properties, and various optional properties. The
     * builder enforces RFC 5545 constraints, such as ensuring that dateTimeDue and duration are
     * mutually exclusive.
     * <p>
     * Example usage:
     * <pre>{@code
     * Todo todo = Todo.builder()
     *     .withDateTimeStamp(dateTimeStamp)
     *     .withUniqueIdentifier(uniqueId)
     *     .withSummary(summary)
     *     .withDateTimeDue(dueDate)
     *     .withPriority(priority)
     *     .build();
     * }</pre>
     */
    public static class TodoBuilder {
        /**
         * The date and time stamp for the to-do being built.
         */
        private DateTimeStamp dateTimeStamp;

        /**
         * The unique identifier for the to-do being built.
         */
        private UniqueIdentifier uniqueIdentifier;

        /**
         * The classification for the to-do being built.
         */
        private Classification classification;

        /**
         * The completion date and time for the to-do being built.
         */
        private DateTimeCompleted dateTimeCompleted;

        /**
         * The creation date and time for the to-do being built.
         */
        private DateTimeCreated dateTimeCreated;

        /**
         * The description for the to-do being built.
         */
        private Description description;

        /**
         * The start date and time for the to-do being built.
         */
        private DateTimeStart dateTimeStart;

        /**
         * The geographic position for the to-do being built.
         */
        private GeographicPosition geographicPosition;

        /**
         * The last modified date and time for the to-do being built.
         */
        private LastModified lastModified;

        /**
         * The location for the to-do being built.
         */
        private Location location;

        /**
         * The organiser for the to-do being built.
         */
        private Organiser organiser;

        /**
         * The percentage completion for the to-do being built.
         */
        private PercentComplete percentComplete;

        /**
         * The priority for the to-do being built.
         */
        private Priority priority;

        /**
         * The recurrence identifier for the to-do being built.
         */
        private RecurrenceId recurrenceId;

        /**
         * The sequence number for the to-do being built.
         */
        private SequenceNumber sequenceNumber;

        /**
         * The status for the to-do being built.
         */
        private Status status;

        /**
         * The summary for the to-do being built.
         */
        private Summary summary;

        /**
         * The URL for the to-do being built.
         */
        private UniformResourceLocator uniformResourceLocator;

        /**
         * The recurrence rule for the to-do being built.
         */
        private RecurrenceRule recurrenceRule;

        /**
         * The due date and time for the to-do being built.
         */
        private DateTimeDue dateTimeDue;

        /**
         * The duration for the to-do being built.
         */
        private Duration duration;

        /**
         * The list of attachments for the to-do being built.
         */
        private List<Attachment> attachments;

        /**
         * The list of attendees for the to-do being built.
         */
        private List<Attendee> attendees;

        /**
         * The list of categories for the to-do being built.
         */
        private List<Categories> categories;

        /**
         * The list of comments for the to-do being built.
         */
        private List<Comment> comments;

        /**
         * The list of contacts for the to-do being built.
         */
        private List<Contact> contacts;

        /**
         * The list of exception date and times for the to-do being built.
         */
        private List<ExceptionDateTimes> exceptionDateTimes;

        /**
         * The list of request statuses for the to-do being built.
         */
        private List<RequestStatus> requestStatuses;

        /**
         * The list of related to-dos for the to-do being built.
         */
        private List<RelatedTo> relatedToList;

        /**
         * The list of resources for the to-do being built.
         */
        private List<Resources> resources;

        /**
         * The list of recurrence date and times for the to-do being built.
         */
        private List<RecurrenceDateTimes> recurrenceDateTimes;

        /**
         * Private constructor to enforce use through the factory method.
         */
        private TodoBuilder() {
        }

        /**
         * Sets the date and time stamp for the to-do.
         *
         * @param dateTimeStamp the date and time stamp when the to-do was created
         * @return this builder instance for method chaining
         */
        public TodoBuilder withDateTimeStamp(DateTimeStamp dateTimeStamp) {
            this.dateTimeStamp = dateTimeStamp;
            return this;
        }

        /**
         * Sets the unique identifier for the to-do.
         *
         * @param uniqueIdentifier the unique identifier for this to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withUniqueIdentifier(UniqueIdentifier uniqueIdentifier) {
            this.uniqueIdentifier = uniqueIdentifier;
            return this;
        }

        /**
         * Sets the access classification for the to-do.
         *
         * @param classification the access classification (e.g., PUBLIC, PRIVATE, CONFIDENTIAL)
         * @return this builder instance for method chaining
         */
        public TodoBuilder withClassification(Classification classification) {
            this.classification = classification;
            return this;
        }

        /**
         * Sets the completion date and time for the to-do.
         *
         * @param dateTimeCompleted the date and time when the to-do was completed
         * @return this builder instance for method chaining
         */
        public TodoBuilder withDateTimeCompleted(DateTimeCompleted dateTimeCompleted) {
            this.dateTimeCompleted = dateTimeCompleted;
            return this;
        }

        /**
         * Sets the creation date and time for the to-do.
         *
         * @param dateTimeCreated the date and time when the to-do was created
         * @return this builder instance for method chaining
         */
        public TodoBuilder withDateTimeCreated(DateTimeCreated dateTimeCreated) {
            this.dateTimeCreated = dateTimeCreated;
            return this;
        }

        /**
         * Sets the description for the to-do.
         *
         * @param description the description or notes for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withDescription(Description description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the start date and time for the to-do.
         *
         * @param dateTimeStart the start date and time of the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withDateTimeStart(DateTimeStart dateTimeStart) {
            this.dateTimeStart = dateTimeStart;
            return this;
        }

        /**
         * Sets the geographic position for the to-do.
         *
         * @param geographicPosition the geographic position (latitude and longitude) of the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withGeographicPosition(GeographicPosition geographicPosition) {
            this.geographicPosition = geographicPosition;
            return this;
        }

        /**
         * Sets the last modified date and time for the to-do.
         *
         * @param lastModified the date and time when the to-do was last modified
         * @return this builder instance for method chaining
         */
        public TodoBuilder withLastModified(LastModified lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        /**
         * Sets the location for the to-do.
         *
         * @param location the location where the to-do takes place
         * @return this builder instance for method chaining
         */
        public TodoBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }

        /**
         * Sets the organiser for the to-do.
         *
         * @param organiser the organiser of the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withOrganiser(Organiser organiser) {
            this.organiser = organiser;
            return this;
        }

        /**
         * Sets the percentage completion for the to-do.
         *
         * @param percentComplete the percentage completion of the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withPercentComplete(PercentComplete percentComplete) {
            this.percentComplete = percentComplete;
            return this;
        }

        /**
         * Sets the priority level for the to-do.
         *
         * @param priority the priority level of the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        /**
         * Sets the recurrence identifier for the to-do.
         *
         * @param recurrenceId the recurrence identifier for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withRecurrenceId(RecurrenceId recurrenceId) {
            this.recurrenceId = recurrenceId;
            return this;
        }

        /**
         * Sets the sequence number for the to-do.
         *
         * @param sequenceNumber the sequence number for the to-do (used for versioning)
         * @return this builder instance for method chaining
         */
        public TodoBuilder withSequenceNumber(SequenceNumber sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
            return this;
        }

        /**
         * Sets the status for the to-do.
         *
         * @param status the status of the to-do (e.g., NEEDS-ACTION, COMPLETED, IN-PROGRESS)
         * @return this builder instance for method chaining
         */
        public TodoBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        /**
         * Sets the summary for the to-do.
         *
         * @param summary the summary or title of the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withSummary(Summary summary) {
            this.summary = summary;
            return this;
        }

        /**
         * Sets the URL for the to-do.
         *
         * @param uniformResourceLocator the uniform resource locator (URL) associated with
         *                               the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withUniformResourceLocator(UniformResourceLocator uniformResourceLocator) {
            this.uniformResourceLocator = uniformResourceLocator;
            return this;
        }

        /**
         * Sets the recurrence rule for the to-do.
         *
         * @param recurrenceRule the recurrence rule for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withRecurrenceRule(RecurrenceRule recurrenceRule) {
            this.recurrenceRule = recurrenceRule;
            return this;
        }

        /**
         * Sets the due date and time for the to-do. Cannot be used together
         * with {@link #withDuration(Duration)}.
         *
         * @param dateTimeDue the due date and time of the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withDateTimeDue(DateTimeDue dateTimeDue) {
            this.dateTimeDue = dateTimeDue;
            return this;
        }

        /**
         * Sets the duration for the to-do. Cannot be used together
         * with {@link #withDateTimeDue(DateTimeDue)}. If duration is set, dateTimeStart should also
         * be set.
         *
         * @param duration the duration of the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Sets the list of attachments for the to-do.
         *
         * @param attachments the attachments to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withAttachments(Attachment... attachments) {
            this.attachments = List.of(attachments);
            return this;
        }

        /**
         * Sets the list of attendees for the to-do.
         *
         * @param attendees the attendees to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withAttendees(Attendee... attendees) {
            this.attendees = List.of(attendees);
            return this;
        }

        /**
         * Sets the list of categories for the to-do.
         *
         * @param categories the categories to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withCategories(Categories... categories) {
            this.categories = List.of(categories);
            return this;
        }

        /**
         * Sets the list of comments for the to-do.
         *
         * @param comments the comments to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withComments(Comment... comments) {
            this.comments = List.of(comments);
            return this;
        }

        /**
         * Sets the list of contacts for the to-do.
         *
         * @param contacts the contacts to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withContacts(Contact... contacts) {
            this.contacts = List.of(contacts);
            return this;
        }

        /**
         * Sets the list of exception date times for the to-do.
         *
         * @param exceptionDateTimes the exception date times to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withExceptionDateTimes(ExceptionDateTimes... exceptionDateTimes) {
            this.exceptionDateTimes = List.of(exceptionDateTimes);
            return this;
        }

        /**
         * Sets the list of request statuses for the to-do.
         *
         * @param requestStatuses the request statuses to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withRequestStatuses(RequestStatus... requestStatuses) {
            this.requestStatuses = List.of(requestStatuses);
            return this;
        }

        /**
         * Sets the list of related-to references for the to-do.
         *
         * @param relatedToList the related-to references to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withRelatedToList(RelatedTo... relatedToList) {
            this.relatedToList = List.of(relatedToList);
            return this;
        }

        /**
         * Sets the list of resources for the to-do.
         *
         * @param resources the resources to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withResources(Resources... resources) {
            this.resources = List.of(resources);
            return this;
        }

        /**
         * Sets the list of recurrence date times for the to-do.
         *
         * @param recurrenceDateTimes the recurrence date times to set for the to-do
         * @return this builder instance for method chaining
         */
        public TodoBuilder withRecurrenceDateTimes(RecurrenceDateTimes... recurrenceDateTimes) {
            this.recurrenceDateTimes = List.of(recurrenceDateTimes);
            return this;
        }

        /**
         * Builds a new to-do instance with the configured properties.
         * <p>
         * This method validates that all required properties are set and that mutually exclusive
         * properties (such as {@code dateTimeDue} and {@code duration}) are not both specified.
         *
         * @return a new to-do instance
         * @throws IllegalArgumentException if required fields (dateTimeStamp, uniqueIdentifier) are
         *                                  not set
         * @throws IllegalStateException    if both dateTimeDue and duration are set simultaneously
         */
        public Todo build() {
            // Check required fields
            if (Objects.isNull(dateTimeStamp)) {
                throw new IllegalArgumentException("Date-Time Stamp is required.");
            }

            if (Objects.isNull(uniqueIdentifier)) {
                throw new IllegalArgumentException("Unique Identifier is required.");
            }

            // Mutually exclusive properties: due and duration
            if (dateTimeDue != null && duration != null) {
                throw new IllegalStateException("Both dateTimeDue and duration cannot be set simultaneously");
            }

            return new Todo(
                    dateTimeStamp, uniqueIdentifier, classification, dateTimeCompleted,
                    dateTimeCreated, description, dateTimeStart, geographicPosition, lastModified,
                    location, organiser, percentComplete, priority, recurrenceId, sequenceNumber,
                    status, summary, uniformResourceLocator, recurrenceRule, dateTimeDue, duration,
                    attachments, attendees, categories, comments, contacts, exceptionDateTimes,
                    requestStatuses, relatedToList, resources, recurrenceDateTimes
            );
        }
    }

    /**
     * Formats this to-do component as an iCalendar string representation.
     * <p>
     * This method generates the iCalendar text format for the to-do component, including all its
     * properties formatted according to RFC 5545 specification. The output includes the
     * {@code BEGIN:VTODO} and {@code END:VTODO} delimiters and all configured properties in the
     * proper format.
     *
     * @return the iCalendar string representation of this to-do
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        var propertyAppender = PropertyAppender.of(builder);

        builder.append("BEGIN").append(":").append(COMPONENT_NAME);

        propertyAppender.append(dateTimeStamp);
        propertyAppender.append(uniqueIdentifier);
        propertyAppender.append(classification);
        propertyAppender.append(dateTimeCompleted);
        propertyAppender.append(dateTimeCreated);
        propertyAppender.append(description);
        propertyAppender.append(dateTimeStart);
        propertyAppender.append(geographicPosition);
        propertyAppender.append(lastModified);
        propertyAppender.append(location);
        propertyAppender.append(organiser);
        propertyAppender.append(percentComplete);
        propertyAppender.append(priority);
        propertyAppender.append(recurrenceId);
        propertyAppender.append(sequenceNumber);
        propertyAppender.append(status);
        propertyAppender.append(summary);
        propertyAppender.append(uniformResourceLocator);
        propertyAppender.append(recurrenceRule);
        propertyAppender.append(dateTimeDue);
        propertyAppender.append(duration);
        propertyAppender.append(attachments);
        propertyAppender.append(attendees);
        propertyAppender.append(categories);
        propertyAppender.append(comments);
        propertyAppender.append(contacts);
        propertyAppender.append(exceptionDateTimes);
        propertyAppender.append(requestStatuses);
        propertyAppender.append(relatedToList);
        propertyAppender.append(resources);
        propertyAppender.append(recurrenceDateTimes);

        builder.append("\n").append("END").append(":").append(COMPONENT_NAME);
        return builder.toString();
    }
}
