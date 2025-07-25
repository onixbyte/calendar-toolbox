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

import com.onixbyte.calendar.component.property.*;
import com.onixbyte.calendar.util.ComponentComposer;
import com.onixbyte.common.util.CollectionUtil;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public final class Alarm implements CalendarComponent {

    private static final String COMPONENT_NAME = "VALARM";

    private final Action action;

    private final Trigger trigger;

    private final Duration duration;

    private final List<Attachment> attachments;

    private final Description description;

    private final RepeatCount repeatCount;

    private final Summary summary;

    private final List<Attendee> attendees;

    private Alarm(
            Action action,
            Trigger trigger,
            Duration duration,
            List<Attachment> attachments,
            Description description,
            RepeatCount repeatCount,
            Summary summary,
            List<Attendee> attendees
    ) {
        this.action = action;
        this.trigger = trigger;
        this.duration = duration;
        this.attachments = attachments;
        this.description = description;
        this.repeatCount = repeatCount;
        this.summary = summary;
        this.attendees = attendees;
    }

    public static AlarmBuilder builder() {
        return new AlarmBuilder();
    }

    public static class AlarmBuilder {
        private Action action;
        private Trigger trigger;
        private Duration duration;
        private List<Attachment> attachments;
        private Description description;
        private RepeatCount repeatCount;
        private Summary summary;
        private List<Attendee> attendees;

        private AlarmBuilder() {
        }

        public AlarmBuilder withTrigger(Trigger trigger) {
            this.trigger = trigger;
            return this;
        }

        public AlarmBuilder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public AlarmBuilder withAttachments(Attachment... attachments) {
            this.attachments = List.of(attachments);
            return this;
        }

        public AlarmBuilder withDescription(Description description) {
            this.description = description;
            return this;
        }

        public AlarmBuilder withRepeatCount(RepeatCount repeatCount) {
            this.repeatCount = repeatCount;
            return this;
        }

        public AlarmBuilder withSummary(Summary summary) {
            this.summary = summary;
            return this;
        }

        public AlarmBuilder withAttendees(Attendee... attendees) {
            this.attendees = List.of(attendees);
            return this;
        }

        public Alarm buildAudio() {
            // Properties `action` and `trigger` are both required, but must not occur more than once.
            if (Objects.isNull(trigger)) {
                throw new IllegalArgumentException("Trigger is required.");
            }

            // Properties `duration` and `repeat` are both optional, and must not occur more than
            // once each; but if one occurs, so must the other.
            if ((duration == null) != (repeatCount == null)) {
                throw new IllegalArgumentException("Properties `duration` and `repeat` must be both null or both non-null");
            }

            // Property `attach` are optional, but must not occur more than once.
            if (attachments.size() > 1) {
                throw new IllegalArgumentException("Audio alarm does not allow multiple attachments.");
            }

            // No specification for property `attendee`.
            if (!attendees.isEmpty()) {
                throw new IllegalArgumentException("Audio alarm does not allow property attendee.");
            }

            return new Alarm(
                    Action.AUDIO, trigger, duration, attachments, description, repeatCount, summary,
                    attendees
            );
        }

        public Alarm buildDisplay() {
            // Properties `action`, `description` and `trigger` are both required, but must not
            // occur more than once.
            if (Objects.isNull(description)) {
                throw new IllegalArgumentException("Description is required.");
            }

            if (Objects.isNull(trigger)) {
                throw new IllegalArgumentException("Trigger is required.");
            }

            // Properties `duration` and `repeat` are both optional, and must not occur more than
            // once each; but if one occurs, so must the other.
            if ((duration == null) != (repeatCount == null)) {
                throw new IllegalArgumentException("Properties `duration` and `repeat` must be both null or both non-null");
            }

            // No specification for property `attachment`.
            if (!attachments.isEmpty()) {
                throw new IllegalArgumentException("Display alarm does not allow any attachments.");
            }

            // No specification for property `attendee`.
            if (!attendees.isEmpty()) {
                throw new IllegalArgumentException("Audio alarm does not allow property attendee.");
            }

            return new Alarm(
                    Action.DISPLAY, trigger, duration, attachments, description, repeatCount,
                    summary, attendees
            );
        }

        public Alarm buildEmail() {
            // Properties `action`, `description`, `trigger` and `summary` are both required, but
            // must not occur more than once.
            if (Objects.isNull(description)) {
                throw new IllegalArgumentException("Description is required.");
            }

            if (Objects.isNull(trigger)) {
                throw new IllegalArgumentException("Trigger is required.");
            }

            if (Objects.isNull(summary)) {
                throw new IllegalArgumentException("Summary is required.");
            }

            // Property `attendee` is required, and may occur more than once.
            if (CollectionUtil.notEmpty(attendees)) {
                throw new IllegalArgumentException("Attendee is required.");
            }

            // Properties `duration` and `repeat` are both optional, and must not occur more than
            // once each; but if one occurs, so must the other.
            if ((duration == null) != (repeatCount == null)) {
                throw new IllegalArgumentException("Properties `duration` and `repeat` must be both null or both non-null");
            }

            return new Alarm(
                    Action.EMAIL, trigger, duration, attachments, description, repeatCount, summary,
                    attendees
            );
        }
    }

    @Override
    public String formatted() {
        var composer = ComponentComposer.of(COMPONENT_NAME);

        composer.append(action)
                .append(trigger)
                .append(duration)
                .append(attachments)
                .append(description)
                .append(repeatCount)
                .append(summary)
                .append(attendees);
        return composer.end();
    }
}
