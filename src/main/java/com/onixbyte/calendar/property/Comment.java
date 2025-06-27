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

import com.onixbyte.calendar.parameter.AlternateTextRepresentation;
import com.onixbyte.calendar.parameter.Language;
import com.onixbyte.calendar.util.ParamAppender;

public class Comment implements ComponentProperty {

    private final AlternateTextRepresentation alternateTextRepresentation;

    private final Language language;

    private final String comment;

    private Comment(AlternateTextRepresentation alternateTextRepresentation, Language language, String comment) {
        this.alternateTextRepresentation = alternateTextRepresentation;
        this.language = language;
        this.comment = comment;
    }

    public static Comment of(AlternateTextRepresentation alternateTextRepresentation, Language language, String comment) {
        return new Comment(alternateTextRepresentation, language, comment);
    }

    public static Comment of(Language language, String comment) {
        return new Comment(null, language, comment);
    }

    public static Comment of(String comment) {
        return new Comment(null, null, comment);
    }

    public String formatted() {
        var builder = new StringBuilder();
        builder.append("COMMENT");

        ParamAppender.append(builder, alternateTextRepresentation);
        ParamAppender.append(builder, language);

        builder.append(":").append(comment);
        return builder.toString();
    }
}