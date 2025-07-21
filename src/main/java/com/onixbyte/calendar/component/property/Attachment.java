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

import com.onixbyte.calendar.parameter.FormatType;
import com.onixbyte.calendar.parameter.InlineEncoding;
import com.onixbyte.calendar.parameter.ValueDataType;
import com.onixbyte.calendar.util.PropertyComposer;

import java.net.URI;
import java.util.Base64;
import java.util.Objects;

/**
 * Represents the {@code ATTACH} property in an iCalendar component.
 * <p>
 * This property provides the capability to associate a document object with a calendar component.
 * The attachment can be either a URI reference to a resource or inline binary data encoded
 * in Base64.
 * <p>
 * The property supports optional parameters for format type specification, inline encoding method,
 * and value data type.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class Attachment implements ComponentProperty {

    /**
     * The optional format type parameter for this attachment.
     */
    private final FormatType formatType;

    /**
     * The URI reference to the attachment resource, if applicable.
     */
    private final URI uri;

    /**
     * The inline encoding parameter for binary data, if applicable.
     */
    private final InlineEncoding encoding;

    /**
     * The value data type parameter for binary data, if applicable.
     */
    private final ValueDataType value;

    /**
     * The binary data for inline attachments, if applicable.
     */
    private final byte[] binary;

    /**
     * Private constructor to create an Attachment instance.
     *
     * @param formatType the optional format type parameter
     * @param uri        the URI reference to the attachment resource
     * @param encoding   the inline encoding parameter for binary data
     * @param value      the value data type parameter for binary data
     * @param binary     the binary data for inline attachments
     */
    private Attachment(
            FormatType formatType,
            URI uri,
            InlineEncoding encoding,
            ValueDataType value,
            byte[] binary
    ) {
        this.formatType = formatType;
        this.uri = uri;
        this.encoding = encoding;
        this.value = value;
        this.binary = binary;
    }

    /**
     * Creates a new AttachmentBuilder instance for constructing Attachment objects.
     *
     * @return a new AttachmentBuilder instance
     */
    public static AttachmentBuilder builder() {
        return new AttachmentBuilder();
    }

    /**
     * Builder class for constructing Attachment instances.
     * <p>
     * This builder provides a fluent interface for creating Attachment objects with optional format
     * type parameters and various build methods for different attachment types
     * (URI-based or binary data).
     */
    public static class AttachmentBuilder {
        /**
         * The optional format type parameter for the attachment being built.
         */
        private FormatType formatType;

        /**
         * Private constructor to enforce use through the factory method.
         */
        private AttachmentBuilder() {
        }

        /**
         * Sets the format type parameter for the attachment.
         *
         * @param formatType the format type parameter
         * @return this builder instance for method chaining
         */
        public AttachmentBuilder withFormatType(FormatType formatType) {
            this.formatType = formatType;
            return this;
        }

        /**
         * Builds an attachment instance with a URI reference.
         *
         * @param uri the URI reference to the attachment resource
         * @return a new attachment instance
         */
        public Attachment build(URI uri) {
            return new Attachment(formatType, uri, null, null, null);
        }

        /**
         * Builds an attachment instance with a URI reference from a string.
         *
         * @param uri the string representation of the URI reference
         * @return a new attachment instance
         */
        public Attachment build(String uri) {
            return new Attachment(formatType, URI.create(uri), null, null, null);
        }

        /**
         * Builds an attachment instance with inline binary data.
         *
         * @param binary the binary data for the inline attachment
         * @return a new attachment instance
         */
        public Attachment build(byte[] binary) {
            return new Attachment(formatType, null, InlineEncoding.BASE_64, ValueDataType.BINARY, binary);
        }
    }

    /**
     * Returns the formatted string representation of this attachment property.
     * <p>
     * The format varies depending on whether this is a URI-based attachment or
     * inline binary data. Binary attachments are Base64-encoded with appropriate
     * encoding and value type parameters.
     *
     * @return the formatted attachment property string
     */
    @Override
    public String formatted() {
        var composer = PropertyComposer.of("ATTACH");

        if (Objects.nonNull(binary) && binary.length > 0) {
            return composer
                    .append(encoding)
                    .append(value)
                    .append(formatType)
                    .end(Base64.getEncoder().encodeToString(binary));
        } else {
            return composer
                    .append(formatType)
                    .end(uri);
        }
    }
}
