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

class FormatTypeTest {

    @Test
    void testCreateTextPlain() {
        FormatType formatType = FormatType.of("text/plain");
        assertEquals("FMTTYPE=text/plain", formatType.formatted());
    }

    @Test
    void testCreateApplicationPdf() {
        FormatType formatType = FormatType.of("application/pdf");
        assertEquals("FMTTYPE=application/pdf", formatType.formatted());
    }

    @Test
    void testCreateImageJpeg() {
        FormatType formatType = FormatType.of("image/jpeg");
        assertEquals("FMTTYPE=image/jpeg", formatType.formatted());
    }

    @Test
    void testCreateImagePng() {
        FormatType formatType = FormatType.of("image/png");
        assertEquals("FMTTYPE=image/png", formatType.formatted());
    }

    @Test
    void testCreateApplicationJson() {
        FormatType formatType = FormatType.of("application/json");
        assertEquals("FMTTYPE=application/json", formatType.formatted());
    }

    @Test
    void testCreateTextHtml() {
        FormatType formatType = FormatType.of("text/html");
        assertEquals("FMTTYPE=text/html", formatType.formatted());
    }

    @Test
    void testCreateVideoMp4() {
        FormatType formatType = FormatType.of("video/mp4");
        assertEquals("FMTTYPE=video/mp4", formatType.formatted());
    }

    @Test
    void testCreateAudioMp3() {
        FormatType formatType = FormatType.of("audio/mp3");
        assertEquals("FMTTYPE=audio/mp3", formatType.formatted());
    }

    @Test
    void testNullValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FormatType.of(null));
    }

    @Test
    void testEmptyStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> FormatType.of(""));
    }

    @Test
    void testCustomMimeType() {
        FormatType formatType = FormatType.of("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        assertEquals("FMTTYPE=application/vnd.openxmlformats-officedocument.wordprocessingml.document", formatType.formatted());
    }

    @Test
    void testMimeTypeWithCharset() {
        FormatType formatType = FormatType.of("text/html; charset=utf-8");
        assertEquals("FMTTYPE=text/html; charset=utf-8", formatType.formatted());
    }
}
