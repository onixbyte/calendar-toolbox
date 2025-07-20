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

import java.util.Objects;

/**
 * Represents the {@code REQUEST-STATUS} property value as defined in RFC 5545.
 * <p>
 * This class encapsulates the status code, description, and optional additional data
 * for a scheduling request response, formatted as a semicolon-separated TEXT value.
 * The status code is a hierarchical numeric code (e.g., "2.0", "3.7"), the description
 * provides a brief explanation, and additional data may include further context.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class RequestStatus implements ComponentProperty {

    /**
     * The status class value (1-5) indicating the general outcome category.
     */
    private final int statusClass;
    
    /**
     * The status detail value (0-99) providing additional specificity within the class.
     */
    private final int statusDetail;
    
    /**
     * The mandatory description providing human-readable explanation of the status.
     */
    private final String description;
    
    /**
     * Optional additional data providing further context for the status.
     */
    private final String additionalData;

    /**
     * Constructs a RequestStatus instance.
     *
     * @param statusClass    the status class (1 to 5)
     * @param statusDetail   the status detail (0 to 99)
     * @param description    the mandatory description of the status
     * @param additionalData optional additional data, may be null
     * @throws IllegalArgumentException if validation fails
     */
    private RequestStatus(int statusClass, int statusDetail, String description, String additionalData) {
        // Validate status class (1 to 5 as per RFC 5545)
        if (statusClass < 1 || statusClass > 5) {
            throw new IllegalArgumentException("Status class MUST be between 1 and 5.");
        }

        // Validate status detail (0 to 9 for simplicity, extensible for future RFCs)
        if (statusDetail < 0 || statusDetail > 99) {
            throw new IllegalArgumentException("Status detail MUST be between 0 and 99.");
        }

        // Validate description
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description MUST not be null or empty.");
        }

        this.statusClass = statusClass;
        this.statusDetail = statusDetail;
        this.description = description;
        this.additionalData = additionalData;
    }

    /**
     * Creates a new builder instance for constructing RequestStatus objects.
     *
     * @return a new RequestStatusBuilder instance
     */
    public static RequestStatusBuilder builder() {
        return new RequestStatusBuilder();
    }

    /**
     * Builder class for constructing RequestStatus instances.
     * <p>
     * This builder provides factory methods for creating RequestStatus instances
     * with or without additional data.
     */
    public static class RequestStatusBuilder {
        /**
         * Private constructor to enforce use through the factory method.
         */
        private RequestStatusBuilder() {
        }

        /**
         * Creates a RequestStatus with a mandatory description and no additional data.
         *
         * @param statusClass  the status class (1 to 5)
         * @param statusDetail the status detail (0 to 9)
         * @param description  the mandatory description
         * @return a new RequestStatus instance
         * @throws IllegalArgumentException if validation fails
         */
        public RequestStatus build(int statusClass, int statusDetail, String description) {
            return new RequestStatus(statusClass, statusDetail, description, null);
        }

        /**
         * Creates a RequestStatus with both a description and additional data.
         *
         * @param statusClass    the status class (1 to 5)
         * @param statusDetail   the status detail (0 to 9)
         * @param description    the mandatory description
         * @param additionalData the optional additional data
         * @return a new RequestStatus instance
         * @throws IllegalArgumentException if validation fails
         */
        public RequestStatus build(int statusClass, int statusDetail, String description, String additionalData) {
            return new RequestStatus(statusClass, statusDetail, description, additionalData);
        }
    }

    /**
     * Returns the formatted REQUEST-STATUS value as a semicolon-separated string.
     * <p>
     * The format is: <code>class.detail;description[;additionalData]</code>
     * where additionalData is included only if non-null.
     * </p>
     *
     * @return the formatted string
     */
    @Override
    public String formatted() {
        StringBuilder builder = new StringBuilder();
        builder.append(statusClass)
                .append('.')
                .append(statusDetail)
                .append(';')
                .append(escapeText(description));
        if (additionalData != null) {
            builder.append(';')
                    .append(escapeText(additionalData));
        }
        return builder.toString();
    }

    /**
     * Escapes special characters in TEXT values as per RFC 5545.
     *
     * @param text the text to escape
     * @return the escaped text
     */
    private String escapeText(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("\\", "\\\\")
                .replace(";", "\\;")
                .replace(",", "\\,")
                .replace("\n", "\\n");
    }

    /**
     * Gets the status class.
     *
     * @return the status class (1 to 5)
     */
    public int getStatusClass() {
        return statusClass;
    }

    /**
     * Gets the status detail.
     *
     * @return the status detail (0 to 9)
     */
    public int getStatusDetail() {
        return statusDetail;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the additional data, if any.
     *
     * @return the additional data, or null if not set
     */
    public String getAdditionalData() {
        return additionalData;
    }

    /**
     * Indicates whether some other object is "equal to" this RequestStatus.
     * <p>
     * Two RequestStatus objects are considered equal if they have the same
     * status class, status detail, description, and additional data (including
     * both being null for additional data).
     * </p>
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestStatus that = (RequestStatus) o;
        return statusClass == that.statusClass &&
                statusDetail == that.statusDetail &&
                description.equals(that.description) &&
                Objects.equals(additionalData, that.additionalData);
    }

    /**
     * Returns a hash code value for this RequestStatus.
     * <p>
     * This method is supported for the benefit of hash tables such as those
     * provided by {@link java.util.HashMap}. The hash code is computed based
     * on the status class, status detail, description, and additional data.
     * </p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(statusClass, statusDetail, description, additionalData);
    }
}
