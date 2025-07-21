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

package com.onixbyte.calendar.component;

import com.onixbyte.calendar.property.Action;
import com.onixbyte.calendar.property.Trigger;
import com.onixbyte.calendar.property.Description;
import com.onixbyte.calendar.property.Summary;
import com.onixbyte.calendar.property.Attachment;
import com.onixbyte.calendar.property.RepeatCount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Alarm class.
 */
class AlarmTest {

    private Action action;
    private Trigger trigger;
    private Description description;
    private Summary summary;
    private Attachment attachment;
    private RepeatCount repeatCount;

    @BeforeEach
    void setUp() {
        action = Action.DISPLAY;
        trigger = Trigger.builder().build(Duration.ofMinutes(-10));
        description = Description.builder().build("Test Alarm");
        summary = Summary.builder().build("Alarm Summary");
        attachment = Attachment.builder().build("http://example.com/sound.wav");
        repeatCount = RepeatCount.builder().build(3);
    }

    @Test
    void testAlarmBuilderWithDisplayAction() {
        Alarm.Display display = Alarm.displayBuilder()
                .withAction(action)
                .withTrigger(trigger)
                .withDescription(description)
                .build();

        Alarm alarm = Alarm.builder().build(display);

        assertNotNull(alarm);
        String formatted = alarm.formatted();
        assertTrue(formatted.contains("BEGIN:VALARM"));
        assertTrue(formatted.contains("END:VALARM"));
        assertTrue(formatted.contains("ACTION:DISPLAY"));
        assertTrue(formatted.contains("DESCRIPTION:Test Alarm"));
    }

    @Test
    void testAlarmBuilderWithAudioAction() {
        Alarm.Audio audio = Alarm.audioBuilder()
                .withAction(action)
                .withTrigger(trigger)
                .withAttachment(attachment)
                .withRepeatCount(repeatCount)
                .withDuration(Duration.ofMinutes(10))
                .build();

        Alarm alarm = Alarm.builder().build(audio);

        assertNotNull(alarm);
        String formatted = alarm.formatted();
        assertTrue(formatted.contains("BEGIN:VALARM"));
        assertTrue(formatted.contains("END:VALARM"));
        assertTrue(formatted.contains("ACTION:DISPLAY"));
        assertTrue(formatted.contains("ATTACH:http://example.com/sound.wav"));
        assertTrue(formatted.contains("REPEAT:3"));
    }

    @Test
    void testAlarmFormattedStructure() {
        Alarm.Display display = Alarm.displayBuilder()
                .withAction(action)
                .withTrigger(trigger)
                .withDescription(description)
                .build();

        Alarm alarm = Alarm.builder().build(display);

        String formatted = alarm.formatted();
        String[] lines = formatted.split("\n");

        assertEquals("BEGIN:VALARM", lines[0]);
        assertEquals("END:VALARM", lines[lines.length - 1]);
        
        // Check that required properties are present
        boolean hasAction = false, hasTrigger = false, hasDescription = false;
        for (String line : lines) {
            if (line.startsWith("ACTION")) hasAction = true;
            if (line.startsWith("TRIGGER")) hasTrigger = true;
            if (line.startsWith("DESCRIPTION")) hasDescription = true;
        }
        
        assertTrue(hasAction, "Missing ACTION");
        assertTrue(hasTrigger, "Missing TRIGGER");
        assertTrue(hasDescription, "Missing DESCRIPTION");
    }

    @Test
    void testAlarmBuilderPatternExists() {
        assertNotNull(Alarm.builder());
        assertNotNull(Alarm.audioBuilder());
        assertNotNull(Alarm.displayBuilder());
        assertNotNull(Alarm.emailBuilder());
    }
}
