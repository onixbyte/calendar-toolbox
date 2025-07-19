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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents the iCalendar {@code DELEGATED-TO} parameter.
 * <p>
 * This parameter specifies one or more calendar users who are delegated responsibility for a
 * calendar component or event.
 * <p>
 * Instances of this class are immutable and can be created from one or more URIs representing
 * the delegatees.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class Delegatees implements Parameter {

    /**
     * The list of URIs representing the delegatees.
     */
    private final List<URI> values;

    /**
     * Constructs a {@code Delegatees} instance with a list of URIs.
     *
     * @param values the list of delegatee URIs
     */
    private Delegatees(List<URI> values) {
        this.values = values;
    }

    /**
     * Creates a {@code Delegatees} instance from an array of {@link URI} objects.
     *
     * @param delegatees one or more URIs representing the delegatees
     * @return a new {@code Delegatees} instance
     */
    public static Delegatees of(URI... delegatees) {
        return new Delegatees(List.of(delegatees));
    }

    /**
     * Creates a {@code Delegatees} instance from an array of strings that represent URIs.
     * Each string is converted to a {@link URI}.
     *
     * @param delegatees one or more strings representing the delegatee URIs
     * @return a new {@code Delegatees} instance
     * @throws IllegalArgumentException if any string is not a valid URI
     */
    public static Delegatees of(String... delegatees) {
        var _delegatees = Stream.of(delegatees)
                .map((delegatee) -> {
                    try {
                        return new URI(delegatee);
                    } catch (URISyntaxException e) {
                        throw new IllegalArgumentException("Invalid URI: " + delegatee, e);
                    }
                })
                .toList();
        return new Delegatees(_delegatees);
    }

    /**
     * Returns the formatted {@code DELEGATED-TO} parameter string as specified in the
     * iCalendar specification.
     * <p>
     * Each URI is quoted and multiple values are separated by commas.
     *
     * @return a formatted string in the form {@code DELEGATED-TO="uri1","uri2",...} suitable for
     * inclusion in an iCalendar entity
     */
    @Override
    public String formatted() {
        var _delegatees = values.stream()
                .map((delegatee) -> '"' + delegatee.toASCIIString() + '"')
                .toList();

        return "DELEGATED-TO=" + String.join(",", _delegatees);
    }
}
