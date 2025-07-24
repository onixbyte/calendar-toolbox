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
import com.onixbyte.calendar.util.PropertyComposer;

import java.net.URI;
import java.net.URISyntaxException;

public final class Owner implements CalendarProperty {

    private final CommonName commonName;

    private final URI value;

    private Owner(CommonName commonName, URI value) {
        this.commonName = commonName;
        this.value = value;
    }

    public static OwnerBuilder builder() {
        return new OwnerBuilder();
    }

    public static class OwnerBuilder {
        private CommonName commonName;

        private OwnerBuilder() {
        }

        public OwnerBuilder withCommonName(CommonName commonName) {
            this.commonName = commonName;
            return this;
        }

        public Owner build(URI value) {
            return new Owner(commonName, value);
        }

        public Owner build(String value) {
            try {
                var _value = new URI(value);
                return new Owner(commonName, _value);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Value syntax error.");
            }
        }
    }

    @Override
    public String formatted() {
        var propertyComposer = PropertyComposer.of("X-OWNER");
        propertyComposer.append(commonName);
        return propertyComposer.end(value);
    }
}
