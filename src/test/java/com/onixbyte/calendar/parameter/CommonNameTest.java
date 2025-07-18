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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonNameTest {

    @Test
    void testSimpleCommonName() {
        CommonName cn = CommonName.of("John Doe");
        assertEquals("CN=John Doe", cn.formatted());
    }

    @Test
    void testCommonNameWithComma() {
        CommonName cn = CommonName.of("Doe, Jane");
        assertEquals("CN=Doe, Jane", cn.formatted());
    }

    @Test
    void testCommonNameWithSemicolon() {
        CommonName cn = CommonName.of("Project A; Team Lead");
        assertEquals("CN=Project A; Team Lead", cn.formatted());
    }

    @Test
    void testCommonNameWithColon() {
        CommonName cn = CommonName.of("Dept: Ops");
        assertEquals("CN=Dept: Ops", cn.formatted());
    }

    @Test
    void testCommonNameEmpty() {
        CommonName cn = CommonName.of("");
        assertEquals("CN=", cn.formatted());
    }

    @Test
    void testCommonNameWithUnicode() {
        CommonName cn = CommonName.of("张三");
        assertEquals("CN=张三", cn.formatted());
    }

    @Test
    void testCommonNameNullShouldThrow() {
        assertThrows(NullPointerException.class, () -> CommonName.of(null));
    }
}

