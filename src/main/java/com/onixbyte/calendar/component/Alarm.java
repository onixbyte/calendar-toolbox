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

/**
 * This component provides a grouping of component properties that define an alarm.
 *
 * @author zihluwang
 * @author siujamo
 */
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

    /**
     * Create a builder for <code>Alarm</code>.
     *
     * @return builder instance for <code>Alarm</code>
     */
    public static AlarmBuilder builder() {
        return new AlarmBuilder();
    }

    /**
     * Builder for <code>Alarm</code>.
     */
    public static class AlarmBuilder {
        private Trigger trigger;
        private Duration duration;
        private List<Attachment> attachments;
        private Description description;
        private RepeatCount repeatCount;
        private Summary summary;
        private List<Attendee> attendees;

        private AlarmBuilder() {
        }

        /**
         * Set the trigger for an <code>Alarm</code>.
         *
         * @param trigger trigger of the <code>Alarm</code>
         * @return builder instance
         */
        public AlarmBuilder withTrigger(Trigger trigger) {
            this.trigger = trigger;
            return this;
        }

        /**
         * Set the duration for an <code>Alarm</code>.
         *
         * @param duration duration of the <code>Alarm</code>
         * @return builder instance
         */
        public AlarmBuilder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Set the attachments for an <code>Alarm</code>.
         *
         * @param attachments attachments of the <code>Alarm</code>
         * @return builder instance
         */
        public AlarmBuilder withAttachments(Attachment... attachments) {
            this.attachments = List.of(attachments);
            return this;
        }

        /**
         * Set the description for an <code>Alarm</code>.
         *
         * @param description description of the <code>Alarm</code>
         * @return builder instance
         */
        public AlarmBuilder withDescription(Description description) {
            this.description = description;
            return this;
        }

        /**
         * Set the repeat count for an <code>Alarm</code>.
         *
         * @param repeatCount repeat count of the <code>Alarm</code>
         * @return builder instance
         */
        public AlarmBuilder withRepeatCount(RepeatCount repeatCount) {
            this.repeatCount = repeatCount;
            return this;
        }

        /**
         * Set the summary for an <code>Alarm</code>.
         *
         * @param summary summary of the <code>Alarm</code>
         * @return builder instance
         */
        public AlarmBuilder withSummary(Summary summary) {
            this.summary = summary;
            return this;
        }

        /**
         * Set the attendees for an <code>Alarm</code>.
         *
         * @param attendees attendees of the <code>Alarm</code>
         * @return builder instance
         */
        public AlarmBuilder withAttendees(Attendee... attendees) {
            this.attendees = List.of(attendees);
            return this;
        }

        /**
         * Builds an audio type {@code Alarm}.
         *
         * @return a new immutable audio alarm instance
         * @throws IllegalArgumentException if any of the above constraints are violated
         */
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

        /**
         * Builds a display type {@code Alarm}.
         *
         * @return newly constructed immutable display alarm instance
         * @throws IllegalArgumentException if any of the above rules fail validation
         */
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

        /**
         * Builds an email type {@code Alarm}.
         *
         * @return newly constructed immutable display alarm instance
         * @throws IllegalArgumentException if any of the above rules fail validation
         */
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

    /**
     * Output this property in <code>ics</code> format.
     *
     * @return <code>ics</code>-formatted string
     */
    @Override
    public String formatted() {
        return ComponentComposer.of(COMPONENT_NAME)
                .append(action)
                .append(trigger)
                .append(duration)
                .append(attachments)
                .append(description)
                .append(repeatCount)
                .append(summary)
                .append(attendees)
                .end();
    }
}
