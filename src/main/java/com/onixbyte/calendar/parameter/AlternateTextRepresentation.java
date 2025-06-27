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

package com.onixbyte.calendar.parameter;

import java.net.URI;

public class AlternateTextRepresentation implements Parameter {

    private final URI alternateTextRepresentation;

    private AlternateTextRepresentation(URI alternateTextRepresentation) {
        this.alternateTextRepresentation = alternateTextRepresentation;
    }

    public static AlternateTextRepresentation of(URI alternateTextRepresentation) {
        return new AlternateTextRepresentation(alternateTextRepresentation);
    }

    public static AlternateTextRepresentation of(String alternateTextRepresentation) {
        return new AlternateTextRepresentation(URI.create(alternateTextRepresentation));
    }

    @Override
    public String formatted() {
        return "ALTREP=\"" + alternateTextRepresentation.toString() + '"';
    }
}
