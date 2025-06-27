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

import com.onixbyte.calendar.parameter.Language;
import com.onixbyte.calendar.util.AppendUtil;

import java.util.List;

public class Categories implements ComponentProperty {

    private final Language language;

    private final List<String> categories;

    private Categories(Language language, List<String> categories) {
        this.language = language;
        this.categories = categories;
    }

    public static Categories of(Language language, String... categories) {
        return new Categories(language, List.of(categories));
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("CATEGORIES");

        AppendUtil.append(builder, language);

        builder.append(":").append(String.join(",", categories));
        return builder.toString();
    }
}
