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

import com.onixbyte.calendar.parameter.CommonName;
import com.onixbyte.calendar.parameter.DirectoryEntryReference;
import com.onixbyte.calendar.parameter.Language;
import com.onixbyte.calendar.parameter.SentBy;
import com.onixbyte.calendar.util.ParamAppender;

import java.net.URI;

/**
 * Represents an iCalendar ORGANIZER property, which specifies the organiser of a
 * calendar component.
 * <p>
 * The {@code Organiser} class encapsulates information about the individual who is responsible for
 * organising a calendar event, to-do, or journal entry. This includes the organiser's contact
 * information and optional parameters as defined in RFC 5545.
 * <p>
 * The organiser is typically the person who created the calendar component and is responsible
 * for managing it, including sending invitations to attendees and handling responses.
 * <p>
 * This class follows the builder pattern through the {@link OrganiserBuilder} nested class,
 * allowing for flexible construction of organiser objects with optional parameters.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Organiser implements ComponentProperty {

    /**
     * The common name (display name) of the organiser.
     */
    private final CommonName commonName;

    /**
     * The directory entry reference for the organiser.
     */
    private final DirectoryEntryReference directoryEntryReference;

    /**
     * The "sent by" information for the organiser.
     */
    private final SentBy sentBy;

    /**
     * The language preference of the organiser.
     */
    private final Language language;

    /**
     * The URI identifying the organiser (typically a mailto: URI).
     */
    private final URI value;

    /**
     * Constructs a new Organiser instance with the specified parameters.
     *
     * @param commonName              the common name (display name) of the organiser
     * @param directoryEntryReference the directory entry reference for the organiser
     * @param sentBy                  the "sent by" information for the organiser
     * @param language                the language preference of the organiser
     * @param value                   the URI identifying the organiser (typically a mailto: URI)
     * @throws IllegalArgumentException if the URI scheme is not mailto
     */
    private Organiser(
            CommonName commonName,
            DirectoryEntryReference directoryEntryReference,
            SentBy sentBy,
            Language language,
            URI value
    ) {
        if (value.getScheme() != null && !value.getScheme().equalsIgnoreCase("mailto")) {
            throw new IllegalArgumentException("Organiser value must be a mailto URI.");
        }

        this.commonName = commonName;
        this.directoryEntryReference = directoryEntryReference;
        this.sentBy = sentBy;
        this.language = language;
        this.value = value;
    }

    /**
     * Creates a new {@code OrganiserBuilder} instance for constructing {@code Organiser} objects.
     *
     * @return a new {@code OrganiserBuilder} instance
     */
    public static OrganiserBuilder builder() {
        return new OrganiserBuilder();
    }

    /**
     * Builder class for constructing {@code Organiser} instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating {@code Organiser} objects with optional
     * parameters. All parameters are optional and can be set independently according to the
     * requirements of the organiser.
     */
    public static class OrganiserBuilder {
        /**
         * The common name (display name) of the organiser being built.
         */
        private CommonName commonName;

        /**
         * The directory entry reference for the organiser being built.
         */
        private DirectoryEntryReference directoryEntryReference;

        /**
         * The "sent by" information for the organiser being built.
         */
        private SentBy sentBy;

        /**
         * The language preference of the organiser being built.
         */
        private Language language;

        /**
         * Private constructor to enforce use through the factory method.
         */
        private OrganiserBuilder() {
        }

        /**
         * Sets the common name (display name) for the organiser.
         *
         * @param commonName the common name of the organiser
         * @return this builder instance for method chaining
         */
        public OrganiserBuilder withCommonName(CommonName commonName) {
            this.commonName = commonName;
            return this;
        }

        /**
         * Sets the directory entry reference for the organiser.
         *
         * @param directoryEntryReference the directory entry reference
         * @return this builder instance for method chaining
         */
        public OrganiserBuilder withDirectoryEntryReference(DirectoryEntryReference directoryEntryReference) {
            this.directoryEntryReference = directoryEntryReference;
            return this;
        }

        /**
         * Sets the "sent by" information for the organiser.
         *
         * @param sentBy the "sent by" information
         * @return this builder instance for method chaining
         */
        public OrganiserBuilder withSentBy(SentBy sentBy) {
            this.sentBy = sentBy;
            return this;
        }

        /**
         * Sets the language preference for the organiser.
         *
         * @param language the language preference
         * @return this builder instance for method chaining
         */
        public OrganiserBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        /**
         * Builds a new {@code Organiser} instance with the specified URI value.
         *
         * @param value the URI identifying the organiser (typically a {@code mailto:} URI)
         * @return a new Organiser instance
         * @throws IllegalArgumentException if the URI scheme is not mailto
         */
        public Organiser build(URI value) {
            return new Organiser(commonName, directoryEntryReference, sentBy, language, value);
        }

        /**
         * Builds a new Organiser instance with the specified string URI value.
         *
         * @param value the string representation of the URI identifying the organiser
         * @return a new Organiser instance
         * @throws IllegalArgumentException if the URI scheme is not mailto or if the string
         *                                  is invalid
         */
        public Organiser build(String value) {
            return new Organiser(commonName, directoryEntryReference, sentBy, language, URI.create(value));
        }
    }

    /**
     * Returns the formatted string representation of this organiser property.
     * <p>
     * The format follows the iCalendar specification for {@code ORGANIZER} properties, including
     * all configured parameters and the organiser URI value.
     *
     * @return the formatted organiser property string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("ORGANIZER");

        var paramAppender = ParamAppender.of(builder);

        paramAppender.append(commonName);
        paramAppender.append(directoryEntryReference);
        paramAppender.append(sentBy);
        paramAppender.append(language);

        builder.append(":").append(value.toString());
        return builder.toString();
    }
}
