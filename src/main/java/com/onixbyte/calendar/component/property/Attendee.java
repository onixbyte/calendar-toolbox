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

package com.onixbyte.calendar.component.property;

import com.onixbyte.calendar.parameter.*;
import com.onixbyte.calendar.util.PropertyComposer;

import java.net.URI;

/**
 * Represents an iCalendar ATTENDEE property, which specifies participants in calendar events.
 * <p>
 * The Attendee class encapsulates information about individuals who are invited to or
 * participating in calendar events, including their participation status, role, and
 * contact information. It supports various parameters as defined in RFC 5545.
 * <p>
 * This class follows the builder pattern through the {@link AttendeeBuilder} nested class,
 * allowing for flexible construction of attendee objects with optional parameters.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Attendee implements ComponentProperty {

    /**
     * The calendar user type of the attendee (e.g., INDIVIDUAL, GROUP).
     */
    private final CalendarUserType calendarUserType;

    /**
     * The membership information for the attendee.
     */
    private final Membership membership;

    /**
     * The participation role of the attendee (e.g., CHAIR, REQ-PARTICIPANT).
     */
    private final ParticipationRole participationRole;

    /**
     * The participation status of the attendee (e.g., ACCEPTED, DECLINED).
     */
    private final ParticipationStatus participationStatus;

    /**
     * Whether an RSVP is expected from the attendee.
     */
    private final RsvpExpectation rsvpExpectation;

    /**
     * The delegatees if the attendee has delegated participation.
     */
    private final Delegatees delegatees;

    /**
     * The delegators if the attendee is acting as a delegate.
     */
    private final Delegators delegators;

    /**
     * The "sent by" information for the attendee.
     */
    private final SentBy sentBy;

    /**
     * The common name (display name) of the attendee.
     */
    private final CommonName commonName;

    /**
     * The directory entry reference for the attendee.
     */
    private final DirectoryEntryReference directoryEntryReference;

    /**
     * The language preference of the attendee.
     */
    private final Language language;

    /**
     * The URI identifying the attendee (typically a mailto: URI).
     */
    private final URI value;

    /**
     * Constructs a new Attendee instance with the specified parameters.
     * <p>
     * This constructor is private to enforce the use of the builder pattern for creating
     * attendee instances, ensuring proper validation and construction.
     *
     * @param calendarUserType        the calendar user type of the attendee
     * @param membership              the membership information for the attendee
     * @param participationRole       the participation role of the attendee
     * @param participationStatus     the participation status of the attendee
     * @param rsvpExpectation         whether an RSVP is expected from the attendee
     * @param delegatees              the delegatees if the attendee has delegated participation
     * @param delegators              the delegators if the attendee is acting as a delegate
     * @param sentBy                  the "sent by" information for the attendee
     * @param commonName              the common name (display name) of the attendee
     * @param directoryEntryReference the directory entry reference for the attendee
     * @param language                the language preference of the attendee
     * @param value                   the URI identifying the attendee
     */
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

    /**
     * Creates a new AttendeeBuilder instance to construct an Attendee object.
     * <p>
     * This factory method provides access to the builder pattern for creating attendee instances
     * with optional parameters.
     *
     * @return a new AttendeeBuilder instance
     */
    public static AttendeeBuilder builder() {
        return new AttendeeBuilder();
    }

    /**
     * Builder class for constructing Attendee instances using the builder pattern.
     * <p>
     * This builder allows for flexible construction of Attendee objects by providing methods to set
     * optional parameters. All parameters are optional and can be set independently according to
     * the requirements of the attendee.
     */
    public static class AttendeeBuilder {

        /**
         * The calendar user type of the attendee being built.
         */
        private CalendarUserType calendarUserType;

        /**
         * The membership information for the attendee being built.
         */
        private Membership membership;

        /**
         * The participation role of the attendee being built.
         */
        private ParticipationRole participationRole;

        /**
         * The participation status of the attendee being built.
         */
        private ParticipationStatus participationStatus;

        /**
         * Whether an RSVP is expected from the attendee being built.
         */
        private RsvpExpectation rsvpExpectation;

        /**
         * The delegatees if the attendee being built has delegated participation.
         */
        private Delegatees delegatees;

        /**
         * The delegators if the attendee being built is acting as a delegate.
         */
        private Delegators delegators;

        /**
         * The "sent by" information for the attendee being built.
         */
        private SentBy sentBy;

        /**
         * The common name (display name) of the attendee being built.
         */
        private CommonName commonName;

        /**
         * The directory entry reference for the attendee being built.
         */
        private DirectoryEntryReference directoryEntryReference;

        /**
         * The language preference of the attendee being built.
         */
        private Language language;

        /**
         * Constructs a new AttendeeBuilder instance.
         * <p>
         * This constructor is private to enforce the use of the factory method
         * {@link Attendee#builder()} for creating builder instances.
         */
        private AttendeeBuilder() {
        }

        /**
         * Sets the calendar user type for the attendee being built.
         * <p>
         * The calendar user type specifies the type of calendar user, such as {@code INDIVIDUAL}
         * for a person or {@code GROUP} for a group of people.
         *
         * @param calendarUserType the calendar user type to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withUserType(CalendarUserType calendarUserType) {
            this.calendarUserType = calendarUserType;
            return this;
        }

        /**
         * Sets the membership information for the attendee being built.
         * <p>
         * The membership parameter provides information about the attendee's membership in groups
         * or mailing lists.
         *
         * @param membership the membership information to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withMembership(Membership membership) {
            this.membership = membership;
            return this;
        }

        /**
         * Sets the participation role for the attendee being built.
         * <p>
         * The participation role specifies the role of the attendee in the event, such as
         * {@code CHAIR} for the meeting chair or {@code REQ-PARTICIPANT} for a required participant.
         *
         * @param participationRole the participation role to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withParticipationRole(ParticipationRole participationRole) {
            this.participationRole = participationRole;
            return this;
        }

        /**
         * Sets the participation status for the attendee being built.
         * <p>
         * The participation status indicates the attendee's response to the event, such as
         * {@code ACCEPTED}, {@code DECLINED}, or {@code TENTATIVE}.
         *
         * @param participationStatus the participation status to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withParticipationStatus(ParticipationStatus participationStatus) {
            this.participationStatus = participationStatus;
            return this;
        }

        /**
         * Sets the RSVP expectation for the attendee being built.
         * <p>
         * The RSVP expectation indicates whether a response is expected from the attendee for the
         * calendar event.
         *
         * @param rsvpExpectation the RSVP expectation to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withRsvpExpectation(RsvpExpectation rsvpExpectation) {
            this.rsvpExpectation = rsvpExpectation;
            return this;
        }

        /**
         * Sets the delegatees for the attendee being built.
         * <p>
         * The delegatees parameter specifies the attendees to whom this attendee has
         * delegated participation.
         *
         * @param delegatees the delegatees to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withDelegatees(Delegatees delegatees) {
            this.delegatees = delegatees;
            return this;
        }

        /**
         * Sets the delegators for the attendee being built.
         * <p>
         * The delegators parameter specifies the attendees who have delegated participation to
         * this attendee.
         *
         * @param delegators the delegators to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withDelegators(Delegators delegators) {
            this.delegators = delegators;
            return this;
        }

        /**
         * Sets the "sent by" information for the attendee being built.
         * <p>
         * The sent by parameter specifies the calendar user that is acting on behalf of
         * the attendee.
         *
         * @param sentBy the "sent by" information to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withSentBy(SentBy sentBy) {
            this.sentBy = sentBy;
            return this;
        }

        /**
         * Sets the common name for the attendee being built.
         * <p>
         * The common name is the display name of the attendee, which is typically shown in
         * calendar applications.
         *
         * @param commonName the common name to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withCommonName(CommonName commonName) {
            this.commonName = commonName;
            return this;
        }

        /**
         * Sets the directory entry reference for the attendee being built.
         * <p>
         * The directory entry reference provides a reference to a directory entry that contains
         * additional information about the attendee.
         *
         * @param directoryEntryReference the directory entry reference to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withDirectoryEntryReference(DirectoryEntryReference directoryEntryReference) {
            this.directoryEntryReference = directoryEntryReference;
            return this;
        }

        /**
         * Sets the language preference for the attendee being built.
         * <p>
         * The language parameter specifies the preferred language for communication with
         * the attendee.
         *
         * @param language the language preference to set
         * @return this builder instance for method chaining
         */
        public AttendeeBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Builds and returns a new Attendee instance with the configured parameters and the
         * specified URI.
         * <p>
         * This method creates a new Attendee object using the parameters set on this builder and
         * the provided URI. The URI typically identifies the attendee's email address using the
         * {@code mailto:} scheme.
         *
         * @param uri the URI identifying the attendee
         * @return a new Attendee instance
         */
        public Attendee build(URI uri) {
            return new Attendee(
                    calendarUserType, membership, participationRole, participationStatus, rsvpExpectation,
                    delegatees, delegators, sentBy, commonName, directoryEntryReference, language,
                    uri
            );
        }

        /**
         * Builds and returns a new Attendee instance with the configured parameters and the
         * specified URI string.
         * <p>
         * This method creates a new Attendee object using the parameters set on this builder
         * and the provided URI string. The URI typically identifies the attendee's email address
         * using the mailto: scheme.
         *
         * @param uri the URI string identifying the attendee
         * @return a new Attendee instance
         * @throws IllegalArgumentException if the URI string is malformed
         */
        public Attendee build(String uri) {
            return new Attendee(
                    calendarUserType, membership, participationRole, participationStatus, rsvpExpectation,
                    delegatees, delegators, sentBy, commonName, directoryEntryReference, language,
                    URI.create(uri)
            );
        }
    }

    /**
     * Returns the formatted string representation of this attendee property.
     * <p>
     * This method generates a properly formatted ATTENDEE property string that conforms to
     * RFC 5545 specifications. The output includes all configured parameters and the
     * attendee's URI.
     *
     * @return a formatted ATTENDEE property string
     */
    @Override
    public String formatted() {
        var composer = PropertyComposer.of("ATTENDEE")
                .append(calendarUserType)
                .append(membership)
                .append(participationRole)
                .append(participationStatus)
                .append(rsvpExpectation)
                .append(delegatees)
                .append(delegators)
                .append(sentBy)
                .append(commonName)
                .append(directoryEntryReference)
                .append(language);
        return composer.end(value);
    }
}
