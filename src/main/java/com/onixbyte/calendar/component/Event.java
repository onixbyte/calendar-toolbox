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

public final class Event implements CalendarComponent {
    
    private static final String COMPONENT_NAME = "VEVENT";

    // the following are required, but must not occur more than once
    private final DateTimeStamp dateTimeStamp;

    private final UniqueIdentifier uniqueIdentifier;

    // the following is required if the component appears in an iCalendar object that doesn't
    // specify the `method` property; otherwise, it is optional; in any case, it must not occur
    // more than once
    private final DateTimeStart dateTimeStart;

    // the following are optional, but must not occur more than once
    private final Classification classification;

    private final DateTimeCreated dateTimeCreated;

    private final Description description;

    private final GeographicPosition geographicPosition;

    private final LastModified lastModified;

    private final Location location;

    private final Organiser organiser;

    private final Priority priority;

    private final SequenceNumber sequenceNumber;

    private final Status status;

    private final Summary summary;

    private final TimeTransparency timeTransparency;

    private final UniformResourceLocator uniformResourceLocator;

    private final RecurrenceId recurrenceId;

    // the following is optional, but should not occur more than once
    private final RecurrenceRule recurrenceRule;

    // either `dtend` or `duration` may appear in a `eventprop`, but `dtend` and `duration` must
    // not occur in the same `event`
    private final DateTimeEnd dateTimeEnd;

    private final Duration duration;

    // the following are optional, and may occur more than once
    private final List<Attachment> attachments;

    private final List<Attendee> attendees;

    private final List<Categories> categories;

    private final List<Comment> comments;

    private final List<Contact> contacts;

    private final List<ExceptionDateTimes> exceptionDateTimes;

    private final List<RequestStatus> requestStatuses;

    private final List<RelatedTo> related;

    private final List<Resources> resources;

    private final List<RecurrenceDateTimes> recurrenceDateTimes;

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

    public static EventBuilder builder() {
        return new EventBuilder();
    }

    public static class EventBuilder {
        private DateTimeStamp dateTimeStamp;
        private UniqueIdentifier uniqueIdentifier;
        private DateTimeStart dateTimeStart;
        private Classification classification;
        private DateTimeCreated dateTimeCreated;
        private Description description;
        private GeographicPosition geographicPosition;
        private LastModified lastModified;
        private Location location;
        private Organiser organiser;
        private Priority priority;
        private SequenceNumber sequenceNumber;
        private Status status;
        private Summary summary;
        private TimeTransparency timeTransparency;
        private UniformResourceLocator uniformResourceLocator;
        private RecurrenceId recurrenceId;
        private RecurrenceRule recurrenceRule;
        private DateTimeEnd dateTimeEnd;
        private Duration duration;
        private List<Attachment> attachments;
        private List<Attendee> attendees;
        private List<Categories> categories;
        private List<Comment> comments;
        private List<Contact> contacts;
        private List<ExceptionDateTimes> exceptionDateTimes;
        private List<RequestStatus> requestStatuses;
        private List<RelatedTo> related;
        private List<Resources> resources;
        private List<RecurrenceDateTimes> recurrenceDateTimes;

        private EventBuilder() {
        }

        public static EventBuilder builder() {
            return new EventBuilder();
        }

        public EventBuilder withDateTimeStamp(DateTimeStamp dateTimeStamp) {
            this.dateTimeStamp = dateTimeStamp;
            return this;
        }

        public EventBuilder withUniqueIdentifier(UniqueIdentifier uniqueIdentifier) {
            this.uniqueIdentifier = uniqueIdentifier;
            return this;
        }

        public EventBuilder withDateTimeStart(DateTimeStart dateTimeStart) {
            this.dateTimeStart = dateTimeStart;
            return this;
        }

        public EventBuilder withClassification(Classification classification) {
            this.classification = classification;
            return this;
        }

        public EventBuilder withDateTimeCreated(DateTimeCreated dateTimeCreated) {
            this.dateTimeCreated = dateTimeCreated;
            return this;
        }

        public EventBuilder withDescription(Description description) {
            this.description = description;
            return this;
        }

        public EventBuilder withGeographicPosition(GeographicPosition geographicPosition) {
            this.geographicPosition = geographicPosition;
            return this;
        }

        public EventBuilder withLastModified(LastModified lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public EventBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }

        public EventBuilder withOrganiser(Organiser organiser) {
            this.organiser = organiser;
            return this;
        }

        public EventBuilder withPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public EventBuilder withSequenceNumber(SequenceNumber sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
            return this;
        }

        public EventBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public EventBuilder withSummary(Summary summary) {
            this.summary = summary;
            return this;
        }

        public EventBuilder withTimeTransparency(TimeTransparency timeTransparency) {
            this.timeTransparency = timeTransparency;
            return this;
        }

        public EventBuilder withUniformResourceLocator(UniformResourceLocator uniformResourceLocator) {
            this.uniformResourceLocator = uniformResourceLocator;
            return this;
        }

        public EventBuilder withRecurrenceId(RecurrenceId recurrenceId) {
            this.recurrenceId = recurrenceId;
            return this;
        }

        public EventBuilder withRecurrenceRule(RecurrenceRule recurrenceRule) {
            this.recurrenceRule = recurrenceRule;
            return this;
        }

        public EventBuilder withDateTimeEnd(DateTimeEnd dateTimeEnd) {
            this.dateTimeEnd = dateTimeEnd;
            return this;
        }

        public EventBuilder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public EventBuilder withAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        public EventBuilder withAttendees(List<Attendee> attendees) {
            this.attendees = attendees;
            return this;
        }

        public EventBuilder withCategories(List<Categories> categories) {
            this.categories = categories;
            return this;
        }

        public EventBuilder withComments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public EventBuilder withContacts(List<Contact> contacts) {
            this.contacts = contacts;
            return this;
        }

        public EventBuilder withExceptionDateTimes(List<ExceptionDateTimes> exceptionDateTimes) {
            this.exceptionDateTimes = exceptionDateTimes;
            return this;
        }

        public EventBuilder withRequestStatuses(List<RequestStatus> requestStatuses) {
            this.requestStatuses = requestStatuses;
            return this;
        }

        public EventBuilder withRelated(List<RelatedTo> related) {
            this.related = related;
            return this;
        }

        public EventBuilder withResources(List<Resources> resources) {
            this.resources = resources;
            return this;
        }

        public EventBuilder withRecurrenceDateTimes(List<RecurrenceDateTimes> recurrenceDateTimes) {
            this.recurrenceDateTimes = recurrenceDateTimes;
            return this;
        }

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

    public String formatted() {
        var builder = new StringBuilder();
        var propertyAppender = PropertyAppender.of(builder);
        
        builder.append("BEGIN").append(":").append(COMPONENT_NAME);

        propertyAppender.append(dateTimeStamp);
        propertyAppender.append(uniqueIdentifier);
        propertyAppender.append(dateTimeStart);

        propertyAppender.append(classification);
        propertyAppender.append(dateTimeCreated);
        propertyAppender.append(description);
        propertyAppender.append(geographicPosition);
        propertyAppender.append(lastModified);
        propertyAppender.append(location);
        propertyAppender.append(organiser);
        propertyAppender.append(priority);
        propertyAppender.append(sequenceNumber);
        propertyAppender.append(status);
        propertyAppender.append(summary);
        propertyAppender.append(timeTransparency);
        propertyAppender.append(uniformResourceLocator);
        propertyAppender.append(recurrenceId);
        propertyAppender.append(recurrenceRule);

        if (Objects.nonNull(dateTimeEnd)) {
            propertyAppender.append(dateTimeEnd);
        } else if (Objects.nonNull(duration)) {
            propertyAppender.append(duration);
        }

        if (Objects.nonNull(attachments) && !attachments.isEmpty()) {
            attachments.forEach(propertyAppender::append);
        }

        if (Objects.nonNull(attendees) && !attendees.isEmpty()) {
            attendees.forEach(propertyAppender::append);
        }

        if (Objects.nonNull(categories) && !categories.isEmpty()) {
            categories.forEach(propertyAppender::append);
        }

        if (Objects.nonNull(comments) && !comments.isEmpty()) {
            comments.forEach(propertyAppender::append);
        }

        if (Objects.nonNull(contacts) && !contacts.isEmpty()) {
            contacts.forEach(propertyAppender::append);
        }

        if (Objects.nonNull(exceptionDateTimes) && !exceptionDateTimes.isEmpty()) {
            exceptionDateTimes.forEach(propertyAppender::append);
        }

        if (Objects.nonNull(requestStatuses) && !requestStatuses.isEmpty()) {
            requestStatuses.forEach(propertyAppender::append);
        }

        if (Objects.nonNull(related) && !related.isEmpty()) {
            related.forEach(propertyAppender::append);
        }

        if (Objects.nonNull(resources) && !resources.isEmpty()) {
            resources.forEach(propertyAppender::append);
        }

        if (Objects.nonNull(recurrenceDateTimes) && !recurrenceDateTimes.isEmpty()) {
            recurrenceDateTimes.forEach(propertyAppender::append);
        }

        builder.append("\n").append("END").append(":").append(COMPONENT_NAME);
        return builder.toString();
    }
}
