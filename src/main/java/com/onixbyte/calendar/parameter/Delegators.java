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
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents the iCalendar {@code DELEGATED-FROM} parameter.
 * <p>
 * This parameter specifies one or more calendar users who have delegated responsibility for a
 * calendar component or event to another user.
 * <p>
 * Instances of this class are immutable and can be created from one or more URIs representing
 * the delegators.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class Delegators implements Parameter {

    /**
     * The list of URIs representing the delegators.
     */
    private final List<URI> values;

    /**
     * Constructs a {@code Delegators} instance with a list of URIs.
     *
     * @param values the list of delegator URIs
     */
    private Delegators(List<URI> values) {
        this.values = values;
    }

    /**
     * Creates a {@code Delegators} instance from an array of {@link URI} objects.
     *
     * @param delegators one or more URIs representing the delegators
     * @return a new {@code Delegators} instance
     */
    public static Delegators of(URI... delegators) {
        return new Delegators(List.of(delegators));
    }

    /**
     * Creates a {@code Delegators} instance from an array of strings that represent URIs. Each
     * string is converted to a {@link URI}.
     *
     * @param delegators one or more strings representing the delegator URIs
     * @return a new {@code Delegators} instance
     * @throws IllegalArgumentException if any string is not a valid URI
     */
    public static Delegators of(String... delegators) {
        var _delegators = Stream.of(delegators)
                .map((delegator) -> {
                    try {
                        return new URI(delegator);
                    } catch (URISyntaxException e) {
                        throw new IllegalArgumentException("Invalid URI: " + delegator, e);
                    }
                })
                .toList();
        return new Delegators(_delegators);
    }

    /**
     * Returns the formatted DELEGATED-FROM parameter string as specified in the
     * iCalendar specification.
     * <p>
     * Each URI is quoted and multiple values are separated by commas.
     *
     * @return a formatted string in the form {@code DELEGATED-FROM="uri1","uri2",...} suitable for
     * inclusion in an iCalendar entity
     */
    @Override
    public String formatted() {
        var _delegators = values.stream()
                .map((delegator) -> '"' + delegator.toASCIIString() + '"')
                .toList();

        return "DELEGATED-FROM=" + String.join(",", _delegators);
    }
}
