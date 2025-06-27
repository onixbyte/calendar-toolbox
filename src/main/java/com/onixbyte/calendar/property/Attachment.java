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

import com.onixbyte.calendar.parameter.FormatType;
import com.onixbyte.calendar.parameter.InlineEncoding;
import com.onixbyte.calendar.parameter.ValueDataType;
import com.onixbyte.calendar.util.ParamAppender;

import java.net.URI;
import java.util.Base64;
import java.util.Objects;

public class Attachment implements ComponentProperty {

    private final URI uri;
    private final InlineEncoding encoding;
    private final ValueDataType value;
    private final byte[] binary;
    private final FormatType formatType;

    private Attachment(URI uri, FormatType formatType) {
        this.uri = uri;
        this.formatType = formatType;
        this.encoding = null;
        this.value = null;
        this.binary = null;
    }

    private Attachment(byte[] binary, FormatType formatType) {
        this.value = ValueDataType.BINARY;
        this.encoding = InlineEncoding.BASE_64;
        this.binary = binary;
        this.formatType = formatType;
        this.uri = null;
    }

    public static Attachment of(URI uri, FormatType formatType) {
        return new Attachment(uri, formatType);
    }

    public static Attachment of(URI uri) {
        return of(uri, null);
    }

    public static Attachment of(String uri, FormatType formatType) {
        return of(URI.create(uri), formatType);
    }

    public static Attachment of(String uri) {
        return of(uri, null);
    }

    public static Attachment of(byte[] binary, FormatType formatType) {
        return new Attachment(binary, formatType);
    }

    public static Attachment of(byte[] binary) {
        return new Attachment(binary, null);
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("ATTACH");

        if (Objects.nonNull(binary) && binary.length > 0) {
            ParamAppender.append(builder, encoding);
            ParamAppender.append(builder, value);
            ParamAppender.append(builder, formatType);

            builder.append(":").append(Base64.getEncoder().encodeToString(binary));
        } else {
            ParamAppender.append(builder, formatType);

            builder.append(":").append(uri);
        }

        return builder.toString();
    }
}
