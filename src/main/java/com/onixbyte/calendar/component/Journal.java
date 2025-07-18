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

import java.util.List;
import java.util.Objects;

public final class Journal implements ComponentProperty {

    private final static String COMPONENT_NAME = "VJOURNAL";

    // The following are required, but must not occur more than once.
    private final DateTimeStamp dateTimeStamp;
    private final UniqueIdentifier uniqueIdentifier;

    // The following are optional, but must not occur more than once.
    private final Classification classification;
    private final DateTimeCreated dateTimeCreated;
    private final DateTimeStart dateTimeStart;
    private final LastModified lastModified;
    private final Organiser organiser;
    private final RecurrenceId recurrenceId;
    private final SequenceNumber sequenceNumber;
    private final Status status;
    private final Summary summary;
    private final UniformResourceLocator uniformResourceLocator;

    // The following are optional, and should not occur more than once.
    private final RecurrenceRule recurrenceRule;

    // The following are optional, and may occur more than once.
    private final List<Attachment> attachments;
    private final List<Attendee> attendees;
    private final List<Categories> categories;
    private final List<Comment> comments;
    private final List<Contact> contacts;
    private final List<Description> descriptions;
    private final List<ExceptionDateTimes> exceptionDateTimes;
    private final List<RelatedTo> relatedToList;
    private final List<RecurrenceDateTimes> recurrenceDate;
    private final List<RequestStatus> requestStatuses;

    private Journal(
            DateTimeStamp dateTimeStamp,
            UniqueIdentifier uniqueIdentifier,
            Classification classification,
            DateTimeCreated dateTimeCreated,
            DateTimeStart dateTimeStart,
            LastModified lastModified,
            Organiser organiser,
            RecurrenceId recurrenceId,
            SequenceNumber sequenceNumber,
            Status status,
            Summary summary,
            UniformResourceLocator uniformResourceLocator,
            RecurrenceRule recurrenceRule,
            List<Attachment> attachments,
            List<Attendee> attendees,
            List<Categories> categories,
            List<Comment> comments,
            List<Contact> contacts,
            List<Description> descriptions,
            List<ExceptionDateTimes> exceptionDateTimes,
            List<RelatedTo> relatedToList,
            List<RecurrenceDateTimes> recurrenceDate,
            List<RequestStatus> requestStatuses
    ) {
        this.dateTimeStamp = dateTimeStamp;
        this.uniqueIdentifier = uniqueIdentifier;
        this.classification = classification;
        this.dateTimeCreated = dateTimeCreated;
        this.dateTimeStart = dateTimeStart;
        this.lastModified = lastModified;
        this.organiser = organiser;
        this.recurrenceId = recurrenceId;
        this.sequenceNumber = sequenceNumber;
        this.status = status;
        this.summary = summary;
        this.uniformResourceLocator = uniformResourceLocator;
        this.recurrenceRule = recurrenceRule;
        this.attachments = attachments;
        this.attendees = attendees;
        this.categories = categories;
        this.comments = comments;
        this.contacts = contacts;
        this.descriptions = descriptions;
        this.exceptionDateTimes = exceptionDateTimes;
        this.relatedToList = relatedToList;
        this.recurrenceDate = recurrenceDate;
        this.requestStatuses = requestStatuses;
    }

    public static JournalBuilder builder() {
        return new JournalBuilder();
    }

    public static class JournalBuilder {
        private DateTimeStamp stamp;
        private UniqueIdentifier uid;
        private Classification classification;
        private DateTimeCreated created;
        private DateTimeStart start;
        private LastModified lastMod;
        private Organiser organiser;
        private RecurrenceId recurId;
        private SequenceNumber seq;
        private Status status;
        private Summary summary;
        private UniformResourceLocator url;
        private RecurrenceRule recurrenceRule;
        private List<Attachment> attachments;
        private List<Attendee> attendees;
        private List<Categories> categories;
        private List<Comment> comments;
        private List<Contact> contacts;
        private List<Description> descriptions;
        private List<ExceptionDateTimes> exceptionDateTimes;
        private List<RelatedTo> relatedToList;
        private List<RecurrenceDateTimes> recurrenceDates;
        private List<RequestStatus> requestStatuses;

        public JournalBuilder withDateTimeStamp(DateTimeStamp stamp) {
            this.stamp = stamp;
            return this;
        }

        public JournalBuilder withUniqueIdentifier(UniqueIdentifier uid) {
            this.uid = uid;
            return this;
        }

        public JournalBuilder withClassification(Classification classification) {
            this.classification = classification;
            return this;
        }

        public JournalBuilder withDateTimeCreated(DateTimeCreated created) {
            this.created = created;
            return this;
        }

        public JournalBuilder withDateTimeStart(DateTimeStart start) {
            this.start = start;
            return this;
        }

        public JournalBuilder withLastModified(LastModified lastMod) {
            this.lastMod = lastMod;
            return this;
        }

        public JournalBuilder withOrganiser(Organiser organiser) {
            this.organiser = organiser;
            return this;
        }

        public JournalBuilder withRecurrenceId(RecurrenceId recurId) {
            this.recurId = recurId;
            return this;
        }

        public JournalBuilder withSequenceNumber(SequenceNumber seq) {
            this.seq = seq;
            return this;
        }

        public JournalBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public JournalBuilder withSummary(Summary summary) {
            this.summary = summary;
            return this;
        }

        public JournalBuilder withUniformResourceLocator(UniformResourceLocator url) {
            this.url = url;
            return this;
        }

        public JournalBuilder withRecurrenceRule(RecurrenceRule recurrenceRule) {
            this.recurrenceRule = recurrenceRule;
            return this;
        }

        public JournalBuilder withAttachments(Attachment... attachments) {
            this.attachments = List.of(attachments);
            return this;
        }

        public JournalBuilder withAttendees(Attendee... attendees) {
            this.attendees = List.of(attendees);
            return this;
        }

        public JournalBuilder withCategories(Categories... categories) {
            this.categories = List.of(categories);
            return this;
        }

        public JournalBuilder withComments(Comment... comments) {
            this.comments = List.of(comments);
            return this;
        }

        public JournalBuilder withContacts(Contact... contacts) {
            this.contacts = List.of(contacts);
            return this;
        }

        public JournalBuilder withDescriptions(Description... descriptions) {
            this.descriptions = List.of(descriptions);
            return this;
        }

        public JournalBuilder withExceptionDateTimes(ExceptionDateTimes... exceptionDateTimes) {
            this.exceptionDateTimes = List.of(exceptionDateTimes);
            return this;
        }

        public JournalBuilder withRelatedToList(RelatedTo... relatedToList) {
            this.relatedToList = List.of(relatedToList);
            return this;
        }

        public JournalBuilder withRecurrenceDate(RecurrenceDateTimes... recurrenceDate) {
            this.recurrenceDates = List.of(recurrenceDate);
            return this;
        }

        public JournalBuilder withRequestStatuses(RequestStatus... requestStatuses) {
            this.requestStatuses = List.of(requestStatuses);
            return this;
        }

        public Journal build() {
            if (Objects.isNull(stamp)) {
                throw new IllegalArgumentException("`dtstamp` is required.");
            }

            if (Objects.isNull(uid)) {
                throw new IllegalArgumentException("`uid` is required.");
            }

            return new Journal(
                    stamp, uid, classification, created, start, lastMod, organiser, recurId, seq,
                    status, summary, url, recurrenceRule, attachments, attendees, categories,
                    comments, contacts, descriptions, exceptionDateTimes, relatedToList,
                    recurrenceDates, requestStatuses
            );
        }
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        var propertyAppender = PropertyAppender.of(builder);

        builder.append("BEGIN").append(":").append(COMPONENT_NAME);

        propertyAppender.append(dateTimeStamp);
        propertyAppender.append(uniqueIdentifier);
        propertyAppender.append(classification);
        propertyAppender.append(dateTimeCreated);
        propertyAppender.append(dateTimeStart);
        propertyAppender.append(lastModified);
        propertyAppender.append(organiser);
        propertyAppender.append(recurrenceId);
        propertyAppender.append(sequenceNumber);
        propertyAppender.append(status);
        propertyAppender.append(summary);
        propertyAppender.append(uniformResourceLocator);
        propertyAppender.append(recurrenceRule);
        attachments.forEach(propertyAppender::append);
        attendees.forEach(propertyAppender::append);
        categories.forEach(propertyAppender::append);
        comments.forEach(propertyAppender::append);
        contacts.forEach(propertyAppender::append);
        descriptions.forEach(propertyAppender::append);
        exceptionDateTimes.forEach(propertyAppender::append);
        relatedToList.forEach(propertyAppender::append);
        recurrenceDate.forEach(propertyAppender::append);
        requestStatuses.forEach(propertyAppender::append);

        builder.append("\n").append("END").append(":").append(COMPONENT_NAME);

        return builder.toString();
    }
}
