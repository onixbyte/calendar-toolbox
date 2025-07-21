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
import java.util.Objects;

/**
 * Represents an iCalendar VJOURNAL component, which defines a journal entry.
 * <p>
 * The Journal class encapsulates all the properties and details of a calendar journal entry,
 * including timing information, content, and other journal-specific data. This class follows the
 * iCalendar specification (RFC 5545) for {@code VJOURNAL} components.
 * <p>
 * Journal entries represent diary entries, notes, or other textual content that can be associated
 * with a specific date and time in a calendar system.
 * <p>
 * This class follows the builder pattern through the {@link JournalBuilder} nested class, allowing
 * for flexible construction of journal objects with optional properties.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Journal implements ComponentProperty {

    /**
     * The component name for {@code VJOURNAL} as defined in RFC 5545.
     */
    private final static String COMPONENT_NAME = "VJOURNAL";

    /**
     * The date and time stamp indicating when the journal entry was created. This is a
     * required property.
     */
    private final DateTimeStamp dateTimeStamp;

    /**
     * The unique identifier for this journal entry. This is a required property.
     */
    private final UniqueIdentifier uniqueIdentifier;

    /**
     * The access classification for the journal entry (e.g., PUBLIC, PRIVATE, CONFIDENTIAL). This
     * is an optional property.
     */
    private final Classification classification;

    /**
     * The date and time when the journal entry was created. This is an optional property.
     */
    private final DateTimeCreated dateTimeCreated;

    /**
     * The start date and time of the journal entry. This is an optional property.
     */
    private final DateTimeStart dateTimeStart;

    /**
     * The date and time when the journal entry was last modified. This is an optional property.
     */
    private final LastModified lastModified;

    /**
     * The organiser of the journal entry. This is an optional property.
     */
    private final Organiser organiser;

    /**
     * The recurrence identifier for the journal entry. This is an optional property.
     */
    private final RecurrenceId recurrenceId;

    /**
     * The sequence number for the journal entry (used for versioning). This is an
     * optional property.
     */
    private final SequenceNumber sequenceNumber;

    /**
     * The status of the journal entry (e.g., DRAFT, FINAL, CANCELLED). This is an
     * optional property.
     */
    private final Status status;

    /**
     * The summary or title of the journal entry. This is an optional property.
     */
    private final Summary summary;

    /**
     * The uniform resource locator (URL) associated with the journal entry. This is an
     * optional property.
     */
    private final UniformResourceLocator uniformResourceLocator;

    /**
     * The recurrence rule for the journal entry. This is an optional property that should not occur
     * more than once.
     */
    private final RecurrenceRule recurrenceRule;

    /**
     * The list of attachments associated with the journal entry. This is an optional property that
     * may occur more than once.
     */
    private final List<Attachment> attachments;

    /**
     * The list of attendees for the journal entry. This is an optional property that may occur more
     * than once.
     */
    private final List<Attendee> attendees;

    /**
     * The list of categories for the journal entry. This is an optional property that may occur
     * more than once.
     */
    private final List<Categories> categories;

    /**
     * The list of comments for the journal entry. This is an optional property that may occur more
     * than once.
     */
    private final List<Comment> comments;

    /**
     * The list of contacts for the journal entry. This is an optional property that may occur more
     * than once.
     */
    private final List<Contact> contacts;

    /**
     * The list of descriptions for the journal entry. This is an optional property that may occur
     * more than once.
     */
    private final List<Description> descriptions;

    /**
     * The list of exception date and times for the journal entry. This is an optional property that
     * may occur more than once.
     */
    private final List<ExceptionDateTimes> exceptionDateTimes;

    /**
     * The list of related journal entries. This is an optional property that may occur more
     * than once.
     */
    private final List<RelatedTo> relatedToList;

    /**
     * The list of recurrence date and times for the journal entry. This is an optional property
     * that may occur more than once.
     */
    private final List<RecurrenceDateTimes> recurrenceDate;

    /**
     * The list of request statuses for the journal entry. This is an optional property that may
     * occur more than once.
     */
    private final List<RequestStatus> requestStatuses;

    /**
     * Constructs a new Journal instance with the specified properties.
     * <p>
     * This constructor is private to enforce the use of the builder pattern for creating
     * {@code Journal} instances. Use {@link #builder()} to create new Journal objects.
     *
     * @param dateTimeStamp          the date and time stamp when the journal entry was created
     * @param uniqueIdentifier       the unique identifier for this journal entry
     * @param classification         the access classification for the journal entry
     * @param dateTimeCreated        the date and time when the journal entry was created
     * @param dateTimeStart          the start date and time of the journal entry
     * @param lastModified           the date and time when the journal entry was last modified
     * @param organiser              the organiser of the journal entry
     * @param recurrenceId           the recurrence identifier for the journal entry
     * @param sequenceNumber         the sequence number for the journal entry
     * @param status                 the status of the journal entry
     * @param summary                the summary or title of the journal entry
     * @param uniformResourceLocator the URL associated with the journal entry
     * @param recurrenceRule         the recurrence rule for the journal entry
     * @param attachments            the list of attachments associated with the journal entry
     * @param attendees              the list of attendees for the journal entry
     * @param categories             the list of categories for the journal entry
     * @param comments               the list of comments for the journal entry
     * @param contacts               the list of contacts for the journal entry
     * @param descriptions           the list of descriptions for the journal entry
     * @param exceptionDateTimes     the list of exception date and times for the journal entry
     * @param relatedToList          the list of related journal entries
     * @param recurrenceDate         the list of recurrence date and times for the journal entry
     * @param requestStatuses        the list of request statuses for the journal entry
     */
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

    /**
     * Creates a new builder instance for constructing Journal objects.
     * <p>
     * This is the preferred way to create Journal instances, as it provides a flexible and readable
     * approach to setting the various optional and required properties.
     *
     * @return a new JournalBuilder instance
     */
    public static JournalBuilder builder() {
        return new JournalBuilder();
    }

    /**
     * Builder class for constructing Journal instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating Journal objects with the required
     * {@code dateTimeStamp} and uniqueIdentifier properties, and various optional properties. The
     * builder enforces RFC 5545 constraints for journal components.
     * <p>
     * Example usage:
     * <pre>{@code
     * Journal journal = Journal.builder()
     *     .withDateTimeStamp(dateTimeStamp)
     *     .withUniqueIdentifier(uniqueId)
     *     .withSummary(summary)
     *     .withDescriptions(description)
     *     .build();
     * }</pre>
     */
    public static class JournalBuilder {
        /**
         * The date and time stamp for the journal entry being built.
         */
        private DateTimeStamp stamp;

        /**
         * The unique identifier for the journal entry being built.
         */
        private UniqueIdentifier uid;

        /**
         * The classification for the journal entry being built.
         */
        private Classification classification;

        /**
         * The creation date and time for the journal entry being built.
         */
        private DateTimeCreated created;

        /**
         * The start date and time for the journal entry being built.
         */
        private DateTimeStart start;

        /**
         * The last modified date and time for the journal entry being built.
         */
        private LastModified lastMod;

        /**
         * The organiser for the journal entry being built.
         */
        private Organiser organiser;

        /**
         * The recurrence identifier for the journal entry being built.
         */
        private RecurrenceId recurId;

        /**
         * The sequence number for the journal entry being built.
         */
        private SequenceNumber seq;

        /**
         * The status for the journal entry being built.
         */
        private Status status;

        /**
         * The summary for the journal entry being built.
         */
        private Summary summary;

        /**
         * The URL for the journal entry being built.
         */
        private UniformResourceLocator url;

        /**
         * The recurrence rule for the journal entry being built.
         */
        private RecurrenceRule recurrenceRule;

        /**
         * The list of attachments for the journal entry being built.
         */
        private List<Attachment> attachments;

        /**
         * The list of attendees for the journal entry being built.
         */
        private List<Attendee> attendees;

        /**
         * The list of categories for the journal entry being built.
         */
        private List<Categories> categories;

        /**
         * The list of comments for the journal entry being built.
         */
        private List<Comment> comments;

        /**
         * The list of contacts for the journal entry being built.
         */
        private List<Contact> contacts;

        /**
         * The list of descriptions for the journal entry being built.
         */
        private List<Description> descriptions;

        /**
         * The list of exception date and times for the journal entry being built.
         */
        private List<ExceptionDateTimes> exceptionDateTimes;

        /**
         * The list of related journal entries for the journal entry being built.
         */
        private List<RelatedTo> relatedToList;

        /**
         * The list of recurrence dates for the journal entry being built.
         */
        private List<RecurrenceDateTimes> recurrenceDates;

        /**
         * The list of request statuses for the journal entry being built.
         */
        private List<RequestStatus> requestStatuses;

        /**
         * Private constructor prevent from being instantiated from non-standard operations.
         */
        private JournalBuilder() {
        }

        /**
         * Sets the date and time stamp for the journal entry.
         *
         * @param stamp the date and time stamp when the journal entry was created
         * @return this builder instance for method chaining
         */
        public JournalBuilder withDateTimeStamp(DateTimeStamp stamp) {
            this.stamp = stamp;
            return this;
        }

        /**
         * Sets the unique identifier for the journal entry.
         *
         * @param uid the unique identifier for this journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withUniqueIdentifier(UniqueIdentifier uid) {
            this.uid = uid;
            return this;
        }

        /**
         * Sets the access classification for the journal entry.
         *
         * @param classification the access classification (e.g., PUBLIC, PRIVATE, CONFIDENTIAL)
         * @return this builder instance for method chaining
         */
        public JournalBuilder withClassification(Classification classification) {
            this.classification = classification;
            return this;
        }

        /**
         * Sets the creation date and time for the journal entry.
         *
         * @param created the date and time when the journal entry was created
         * @return this builder instance for method chaining
         */
        public JournalBuilder withDateTimeCreated(DateTimeCreated created) {
            this.created = created;
            return this;
        }

        /**
         * Sets the start date and time for the journal entry.
         *
         * @param start the start date and time of the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withDateTimeStart(DateTimeStart start) {
            this.start = start;
            return this;
        }

        /**
         * Sets the last modified date and time for the journal entry.
         *
         * @param lastMod the date and time when the journal entry was last modified
         * @return this builder instance for method chaining
         */
        public JournalBuilder withLastModified(LastModified lastMod) {
            this.lastMod = lastMod;
            return this;
        }

        /**
         * Sets the organiser for the journal entry.
         *
         * @param organiser the organiser of the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withOrganiser(Organiser organiser) {
            this.organiser = organiser;
            return this;
        }

        /**
         * Sets the recurrence identifier for the journal entry.
         *
         * @param recurId the recurrence identifier for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withRecurrenceId(RecurrenceId recurId) {
            this.recurId = recurId;
            return this;
        }

        /**
         * Sets the sequence number for the journal entry.
         *
         * @param seq the sequence number for the journal entry (used for versioning)
         * @return this builder instance for method chaining
         */
        public JournalBuilder withSequenceNumber(SequenceNumber seq) {
            this.seq = seq;
            return this;
        }

        /**
         * Sets the status for the journal entry.
         *
         * @param status the status of the journal entry (e.g., DRAFT, FINAL, CANCELLED)
         * @return this builder instance for method chaining
         */
        public JournalBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        /**
         * Sets the summary for the journal entry.
         *
         * @param summary the summary or title of the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withSummary(Summary summary) {
            this.summary = summary;
            return this;
        }

        /**
         * Sets the URL for the journal entry.
         *
         * @param url the uniform resource locator (URL) associated with the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withUniformResourceLocator(UniformResourceLocator url) {
            this.url = url;
            return this;
        }

        /**
         * Sets the recurrence rule for the journal entry.
         *
         * @param recurrenceRule the recurrence rule for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withRecurrenceRule(RecurrenceRule recurrenceRule) {
            this.recurrenceRule = recurrenceRule;
            return this;
        }

        /**
         * Sets the attachments for the journal entry.
         *
         * @param attachments the attachments associated with the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withAttachments(Attachment... attachments) {
            this.attachments = List.of(attachments);
            return this;
        }

        /**
         * Sets the attendees for the journal entry.
         *
         * @param attendees the attendees for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withAttendees(Attendee... attendees) {
            this.attendees = List.of(attendees);
            return this;
        }

        /**
         * Sets the categories for the journal entry.
         *
         * @param categories the categories for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withCategories(Categories... categories) {
            this.categories = List.of(categories);
            return this;
        }

        /**
         * Sets the comments for the journal entry.
         *
         * @param comments the comments for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withComments(Comment... comments) {
            this.comments = List.of(comments);
            return this;
        }

        /**
         * Sets the contacts for the journal entry.
         *
         * @param contacts the contacts for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withContacts(Contact... contacts) {
            this.contacts = List.of(contacts);
            return this;
        }

        /**
         * Sets the descriptions for the journal entry.
         *
         * @param descriptions the descriptions for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withDescriptions(Description... descriptions) {
            this.descriptions = List.of(descriptions);
            return this;
        }

        /**
         * Sets the exception date and times for the journal entry.
         *
         * @param exceptionDateTimes the exception date and times for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withExceptionDateTimes(ExceptionDateTimes... exceptionDateTimes) {
            this.exceptionDateTimes = List.of(exceptionDateTimes);
            return this;
        }

        /**
         * Sets the related journal entries.
         *
         * @param relatedToList the related journal entries
         * @return this builder instance for method chaining
         */
        public JournalBuilder withRelatedToList(RelatedTo... relatedToList) {
            this.relatedToList = List.of(relatedToList);
            return this;
        }

        /**
         * Sets the recurrence dates for the journal entry.
         *
         * @param recurrenceDate the recurrence date and times for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withRecurrenceDate(RecurrenceDateTimes... recurrenceDate) {
            this.recurrenceDates = List.of(recurrenceDate);
            return this;
        }

        /**
         * Sets the request statuses for the journal entry.
         *
         * @param requestStatuses the request statuses for the journal entry
         * @return this builder instance for method chaining
         */
        public JournalBuilder withRequestStatuses(RequestStatus... requestStatuses) {
            this.requestStatuses = List.of(requestStatuses);
            return this;
        }

        /**
         * Builds and returns a new Journal instance.
         * <p>
         * Validates that required properties (dateTimeStamp and uniqueIdentifier) are present.
         *
         * @return a new Journal instance
         * @throws IllegalArgumentException if required properties are missing
         */
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

    /**
     * Returns the formatted iCalendar representation of this journal component.
     * <p>
     * The format follows the iCalendar specification (RFC 5545) with {@code BEGIN:VJOURNAL} and
     * {@code END:VJOURNAL} delimiters containing all the journal properties. Required properties
     * are included first, followed by optional properties in a logical order.
     *
     * @return the formatted iCalendar {@code VJOURNAL} component string
     */
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
        propertyAppender.append(attachments);
        propertyAppender.append(attendees);
        propertyAppender.append(categories);
        propertyAppender.append(comments);
        propertyAppender.append(contacts);
        propertyAppender.append(descriptions);
        propertyAppender.append(exceptionDateTimes);
        propertyAppender.append(relatedToList);
        propertyAppender.append(recurrenceDate);
        propertyAppender.append(requestStatuses);

        builder.append("\n").append("END").append(":").append(COMPONENT_NAME);

        return builder.toString();
    }
}
