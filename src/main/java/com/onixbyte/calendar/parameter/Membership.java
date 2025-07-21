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
 * Represents the iCalendar MEMBER property parameter.
 * <p>
 * This parameter specifies one or more URIs that identify members associated with a calendar
 * component or event, such as members of a group or organizer's team.
 * <p>
 * Instances of this class are immutable and can be created from one or more URIs representing
 * the members.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class Membership implements Parameter {

    /**
     * The list of URIs representing the members.
     */
    private final List<URI> values;

    /**
     * Constructs a {@code Membership} instance with a list of member URIs.
     *
     * @param values the list of member URIs
     */
    private Membership(List<URI> values) {
        this.values = values;
    }

    /**
     * Creates a {@code Membership} instance from an array of {@link URI} objects.
     *
     * @param members one or more URIs representing the members
     * @return a new {@code Membership} instance
     */
    public static Membership of(URI... members) {
        return new Membership(List.of(members));
    }

    /**
     * Creates a {@code Membership} instance from an array of strings that represent URIs.
     * Each string is converted to a {@link URI}.
     *
     * @param members one or more strings representing the member URIs
     * @return a new {@code Membership} instance
     * @throws IllegalArgumentException if any string is not a valid URI
     */
    public static Membership of(String... members) {
        return new Membership(Stream.of(members)
                .map((member) -> {
                    try {
                        return new URI(member);
                    } catch (URISyntaxException e) {
                        throw new IllegalArgumentException("Invalid URI: " + member, e);
                    }
                })
                .toList());
    }

    /**
     * Returns the formatted {@code MEMBER} parameter string as specified in the
     * iCalendar specification.
     * <p>
     * Each URI is quoted and multiple values are separated by commas.
     *
     * @return a formatted string in the form {@code MEMBER="uri1","uri2",...} suitable for
     * inclusion in an iCalendar entity
     */
    @Override
    public String formatted() {
        var _members = values.stream()
                .map((member) -> '"' + member.toASCIIString() + '"')
                .toList();
        return "MEMBER=" + String.join(",", _members);
    }
}
