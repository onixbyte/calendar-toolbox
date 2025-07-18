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

package com.onixbyte.calendar.property;

import com.onixbyte.calendar.parameter.*;
import com.onixbyte.calendar.util.ParamAppender;

import java.net.URI;

public final class Attendee implements ComponentProperty {

    private final CalendarUserType calendarUserType;

    private final Membership membership;

    private final ParticipationRole participationRole;

    private final ParticipationStatus participationStatus;

    private final RsvpExpectation rsvpExpectation;

    private final Delegatees delegatees;

    private final Delegators delegators;

    private final SentBy sentBy;

    private final CommonName commonName;

    private final DirectoryEntryReference directoryEntryReference;

    private final Language language;

    private final URI value;

    private Attendee(
            CalendarUserType calendarUserType,
            Membership membership,
            ParticipationRole participationRole,
            ParticipationStatus participationStatus,
            RsvpExpectation rsvpExpectation,
            Delegatees delegatees,
            Delegators delegators,
            SentBy sentBy,
            CommonName commonName,
            DirectoryEntryReference directoryEntryReference,
            Language language,
            URI value
    ) {
        this.calendarUserType = calendarUserType;
        this.membership = membership;
        this.participationRole = participationRole;
        this.participationStatus = participationStatus;
        this.rsvpExpectation = rsvpExpectation;
        this.delegatees = delegatees;
        this.delegators = delegators;
        this.sentBy = sentBy;
        this.commonName = commonName;
        this.directoryEntryReference = directoryEntryReference;
        this.language = language;
        this.value = value;
    }

    public static AttendeeBuilder builder() {
        return new AttendeeBuilder();
    }

    public static class AttendeeBuilder {
        private CalendarUserType calendarUserType;
        private Membership membership;
        private ParticipationRole participationRole;
        private ParticipationStatus participationStatus;
        private RsvpExpectation rsvpExpectation;
        private Delegatees delegatees;
        private Delegators delegators;
        private SentBy sentBy;
        private CommonName commonName;
        private DirectoryEntryReference directoryEntryReference;
        private Language language;

        private AttendeeBuilder() {
        }

        public AttendeeBuilder withUserType(CalendarUserType calendarUserType) {
            this.calendarUserType = calendarUserType;
            return this;
        }

        public AttendeeBuilder withMembership(Membership membership) {
            this.membership = membership;
            return this;
        }

        public AttendeeBuilder withParticipationRole(ParticipationRole participationRole) {
            this.participationRole = participationRole;
            return this;
        }

        public AttendeeBuilder withParticipationStatus(ParticipationStatus participationStatus) {
            this.participationStatus = participationStatus;
            return this;
        }

        public AttendeeBuilder withRsvpExpectation(RsvpExpectation rsvpExpectation) {
            this.rsvpExpectation = rsvpExpectation;
            return this;
        }

        public AttendeeBuilder withDelegatees(Delegatees delegatees) {
            this.delegatees = delegatees;
            return this;
        }

        public AttendeeBuilder withDelegators(Delegators delegators) {
            this.delegators = delegators;
            return this;
        }

        public AttendeeBuilder withSentBy(SentBy sentBy) {
            this.sentBy = sentBy;
            return this;
        }

        public AttendeeBuilder withCommonName(CommonName commonName) {
            this.commonName = commonName;
            return this;
        }

        public AttendeeBuilder withDirectoryEntryReference(DirectoryEntryReference directoryEntryReference) {
            this.directoryEntryReference = directoryEntryReference;
            return this;
        }

        public AttendeeBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        public Attendee build(URI uri) {
            return new Attendee(
                    calendarUserType, membership, participationRole, participationStatus, rsvpExpectation,
                    delegatees, delegators, sentBy, commonName, directoryEntryReference, language,
                    uri
            );
        }

        public Attendee build(String uri) {
            return new Attendee(
                    calendarUserType, membership, participationRole, participationStatus, rsvpExpectation,
                    delegatees, delegators, sentBy, commonName, directoryEntryReference, language,
                    URI.create(uri)
            );
        }
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("ATTENDEE");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(calendarUserType);
        paramAppender.append(membership);
        paramAppender.append(participationRole);
        paramAppender.append(participationStatus);
        paramAppender.append(rsvpExpectation);
        paramAppender.append(delegatees);
        paramAppender.append(delegators);
        paramAppender.append(sentBy);
        paramAppender.append(commonName);
        paramAppender.append(directoryEntryReference);
        paramAppender.append(language);

        builder.append(":").append(value.toString());
        return builder.toString();
    }
}
