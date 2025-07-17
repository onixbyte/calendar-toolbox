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

public final class Todo implements ComponentProperty {

    private final static String COMPONENT_NAME = "VTODO";

    // The following are required, but must not occur more than once.
    private final DateTimeStamp dateTimeStamp;
    private final UniqueIdentifier uniqueIdentifier;

    // The following are optional, but must not occur more than once.
    private final Classification classification;
    private final DateTimeCompleted dateTimeCompleted;
    private final DateTimeCreated dateTimeCreated;
    private final Description description;
    private final DateTimeStart dateTimeStart;
    private final GeographicPosition geographicPosition;
    private final LastModified lastModified;
    private final Location location;
    private final Organiser organiser;
    private final PercentComplete percentComplete;
    private final Priority priority;
    private final RecurrenceId recurrenceId;
    private final SequenceNumber sequenceNumber;
    private final Status status;
    private final Summary summary;
    private final UniformResourceLocator uniformResourceLocator;

    // The following is optional, but should not occur more than once.
    private final RecurrenceRule recurrenceRule;

    // Either `due` or `duration` may appear in a `to-do` component, but `due` and `duration`
    // must not occur in the same `to-do`. If `duration` appear in a `to-do` component, then
    // `dtstart` must also appear in the same `to-do` component.
    private final DateTimeDue dateTimeDue;
    private final Duration duration;

    // The following are optional, and may occur more than once.
    private final List<Attachment> attachments;
    private final List<Attendee> attendees;
    private final List<Categories> categories;
    private final List<Comment> comments;
    private final List<Contact> contacts;
    private final List<ExceptionDateTimes> exceptionDateTimes;
    private final List<RequestStatus> requestStatuses;
    private final List<RelatedTo> relatedToList;
    private final List<Resources> resources;
    private final List<RecurrenceDateTimes> recurrenceDateTimes;

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

    public static TodoBuilder builder() {
        return new TodoBuilder();
    }

    public static class TodoBuilder {
        private DateTimeStamp dateTimeStamp;
        private UniqueIdentifier uniqueIdentifier;
        private Classification classification;
        private DateTimeCompleted dateTimeCompleted;
        private DateTimeCreated dateTimeCreated;
        private Description description;
        private DateTimeStart dateTimeStart;
        private GeographicPosition geographicPosition;
        private LastModified lastModified;
        private Location location;
        private Organiser organiser;
        private PercentComplete percentComplete;
        private Priority priority;
        private RecurrenceId recurrenceId;
        private SequenceNumber sequenceNumber;
        private Status status;
        private Summary summary;
        private UniformResourceLocator uniformResourceLocator;
        private RecurrenceRule recurrenceRule;
        private DateTimeDue dateTimeDue;
        private Duration duration;
        private List<Attachment> attachments;
        private List<Attendee> attendees;
        private List<Categories> categories;
        private List<Comment> comments;
        private List<Contact> contacts;
        private List<ExceptionDateTimes> exceptionDateTimes;
        private List<RequestStatus> requestStatuses;
        private List<RelatedTo> relatedToList;
        private List<Resources> resources;
        private List<RecurrenceDateTimes> recurrenceDateTimes;

        private TodoBuilder() {
        }

        public TodoBuilder withDateTimeStamp(DateTimeStamp dateTimeStamp) {
            this.dateTimeStamp = dateTimeStamp;
            return this;
        }

        public TodoBuilder withUniqueIdentifier(UniqueIdentifier uniqueIdentifier) {
            this.uniqueIdentifier = uniqueIdentifier;
            return this;
        }

        public TodoBuilder withClassification(Classification classification) {
            this.classification = classification;
            return this;
        }

        public TodoBuilder withDateTimeCompleted(DateTimeCompleted dateTimeCompleted) {
            this.dateTimeCompleted = dateTimeCompleted;
            return this;
        }

        public TodoBuilder withDateTimeCreated(DateTimeCreated dateTimeCreated) {
            this.dateTimeCreated = dateTimeCreated;
            return this;
        }

        public TodoBuilder withDescription(Description description) {
            this.description = description;
            return this;
        }

        public TodoBuilder withDateTimeStart(DateTimeStart dateTimeStart) {
            this.dateTimeStart = dateTimeStart;
            return this;
        }

        public TodoBuilder withGeographicPosition(GeographicPosition geographicPosition) {
            this.geographicPosition = geographicPosition;
            return this;
        }

        public TodoBuilder withLastModified(LastModified lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public TodoBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }

        public TodoBuilder withOrganiser(Organiser organiser) {
            this.organiser = organiser;
            return this;
        }

        public TodoBuilder withPercentComplete(PercentComplete percentComplete) {
            this.percentComplete = percentComplete;
            return this;
        }

        public TodoBuilder withPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public TodoBuilder withRecurrenceId(RecurrenceId recurrenceId) {
            this.recurrenceId = recurrenceId;
            return this;
        }

        public TodoBuilder withSequenceNumber(SequenceNumber sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
            return this;
        }

        public TodoBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public TodoBuilder withSummary(Summary summary) {
            this.summary = summary;
            return this;
        }

        public TodoBuilder withUniformResourceLocator(UniformResourceLocator uniformResourceLocator) {
            this.uniformResourceLocator = uniformResourceLocator;
            return this;
        }

        public TodoBuilder withRecurrenceRule(RecurrenceRule recurrenceRule) {
            this.recurrenceRule = recurrenceRule;
            return this;
        }

        public TodoBuilder withDateTimeDue(DateTimeDue dateTimeDue) {
            this.dateTimeDue = dateTimeDue;
            return this;
        }

        public TodoBuilder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public TodoBuilder withAttachments(Attachment... attachments) {
            this.attachments = List.of(attachments);
            return this;
        }

        public TodoBuilder withAttendees(Attendee... attendees) {
            this.attendees = List.of(attendees);
            return this;
        }

        public TodoBuilder withCategories(Categories... categories) {
            this.categories = List.of(categories);
            return this;
        }

        public TodoBuilder withComments(Comment... comments) {
            this.comments = List.of(comments);
            return this;
        }

        public TodoBuilder withContacts(Contact... contacts) {
            this.contacts = List.of(contacts);
            return this;
        }

        public TodoBuilder withExceptionDateTimes(ExceptionDateTimes... exceptionDateTimes) {
            this.exceptionDateTimes = List.of(exceptionDateTimes);
            return this;
        }

        public TodoBuilder withRequestStatuses(RequestStatus... requestStatuses) {
            this.requestStatuses = List.of(requestStatuses);
            return this;
        }

        public TodoBuilder withRelatedToList(RelatedTo... relatedToList) {
            this.relatedToList = List.of(relatedToList);
            return this;
        }

        public TodoBuilder withResources(Resources... resources) {
            this.resources = List.of(resources);
            return this;
        }

        public TodoBuilder withRecurrenceDateTimes(RecurrenceDateTimes... recurrenceDateTimes) {
            this.recurrenceDateTimes = List.of(recurrenceDateTimes);
            return this;
        }

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

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        var propertyAppender = PropertyAppender.of(builder);

        builder.append("BEGIN").append(":").append(COMPONENT_NAME);

        // propertyAppender.append();

        builder.append("\n").append("END").append(":").append(COMPONENT_NAME);
        return "";
    }
}
