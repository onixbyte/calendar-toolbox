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

public final class FreeBusy implements CalendarComponent {

    public static final String COMPONENT_NAME = "VFREEBUSY";

    // The following are REQUIRED, but MUST NOT occur more than once.
    private final DateTimeStamp dateTimeStamp;

    private final UniqueIdentifier uniqueIdentifier;

    // The following are OPTIONAL, but MUST NOT occur more than once.
    private final Contact contact;

    private final DateTimeStart dateTimeStart;

    private final DateTimeEnd dateTimeEnd;

    private final Organiser organiser;

    private final UniformResourceLocator uniformResourceLocator;

    // The following are OPTIONAL, and MAY occur more than once.
    private final List<Attendee> attendees;

    private final List<Comment> comments;

    private final List<FreeBusyTime> freeBusyTimes;

    private final List<RequestStatus> requestStatuses;

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

    public static FreeBusyBuilder builder() {
        return new FreeBusyBuilder();
    }

    public static class FreeBusyBuilder {
        private DateTimeStamp dateTimeStamp;
        private UniqueIdentifier uniqueIdentifier;
        private Contact contact;
        private DateTimeStart dateTimeStart;
        private DateTimeEnd dateTimeEnd;
        private Organiser organiser;
        private UniformResourceLocator uniformResourceLocator;
        private List<Attendee> attendees;
        private List<Comment> comments;
        private List<FreeBusyTime> freeBusyTimes;
        private List<RequestStatus> requestStatuses;

        private FreeBusyBuilder() {
        }

        public FreeBusyBuilder withDateTimeStamp(DateTimeStamp dateTimeStamp) {
            this.dateTimeStamp = dateTimeStamp;
            return this;
        }

        public FreeBusyBuilder withUniqueIdentifier(UniqueIdentifier uniqueIdentifier) {
            this.uniqueIdentifier = uniqueIdentifier;
            return this;
        }

        public FreeBusyBuilder withContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public FreeBusyBuilder withDateTimeStart(DateTimeStart dateTimeStart) {
            this.dateTimeStart = dateTimeStart;
            return this;
        }

        public FreeBusyBuilder withDateTimeEnd(DateTimeEnd dateTimeEnd) {
            this.dateTimeEnd = dateTimeEnd;
            return this;
        }

        public FreeBusyBuilder withOrganiser(Organiser organiser) {
            this.organiser = organiser;
            return this;
        }

        public FreeBusyBuilder withUniformResourceLocator(UniformResourceLocator uniformResourceLocator) {
            this.uniformResourceLocator = uniformResourceLocator;
            return this;
        }

        public FreeBusyBuilder withAttendees(Attendee... attendees) {
            this.attendees = List.of(attendees);
            return this;
        }

        public FreeBusyBuilder withComments(Comment... comments) {
            this.comments = List.of(comments);
            return this;
        }

        public FreeBusyBuilder withFreeBusyTimes(FreeBusyTime... freeBusyTimes) {
            this.freeBusyTimes = List.of(freeBusyTimes);
            return this;
        }

        public FreeBusyBuilder withRequestStatuses(RequestStatus... requestStatuses) {
            this.requestStatuses = List.of(requestStatuses);
            return this;
        }

        public FreeBusy build() {
            return new FreeBusy(
                    dateTimeStamp, uniqueIdentifier, contact, dateTimeStart, dateTimeEnd, organiser,
                    uniformResourceLocator, attendees, comments, freeBusyTimes, requestStatuses
            );
        }
    }

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

        attendees.forEach(propertyAppender::append);
        comments.forEach(propertyAppender::append);
        freeBusyTimes.forEach(propertyAppender::append);
        requestStatuses.forEach(propertyAppender::append);

        builder.append("\n").append("END:").append(COMPONENT_NAME);

        return builder.toString();
    }
}
