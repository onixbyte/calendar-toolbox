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

import com.onixbyte.calendar.parameter.CommonName;
import com.onixbyte.calendar.parameter.DirectoryEntryReference;
import com.onixbyte.calendar.parameter.Language;
import com.onixbyte.calendar.parameter.SentBy;
import com.onixbyte.calendar.util.ParamAppender;

import java.net.URI;

public final class Organiser implements ComponentProperty {

    private final CommonName commonName;

    private final DirectoryEntryReference directoryEntryReference;

    private final SentBy sentBy;

    private final Language language;

    private final URI value;

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

    public static OrganiserBuilder builder() {
        return new OrganiserBuilder();
    }

    public static class OrganiserBuilder {
        private CommonName commonName;
        private DirectoryEntryReference directoryEntryReference;
        private SentBy sentBy;
        private Language language;

        private OrganiserBuilder() {
        }

        public OrganiserBuilder withCommonName(CommonName commonName) {
            this.commonName = commonName;
            return this;
        }

        public OrganiserBuilder withDirectoryEntryReference(DirectoryEntryReference directoryEntryReference) {
            this.directoryEntryReference = directoryEntryReference;
            return this;
        }

        public OrganiserBuilder withSentBy(SentBy sentBy) {
            this.sentBy = sentBy;
            return this;
        }

        public OrganiserBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        public Organiser build(URI value) {
            return new Organiser(commonName, directoryEntryReference, sentBy, language, value);
        }

        public Organiser build(String value) {
            return new Organiser(commonName, directoryEntryReference, sentBy, language, URI.create(value));
        }
    }

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
