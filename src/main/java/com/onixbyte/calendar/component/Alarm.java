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

import com.onixbyte.calendar.property.*;
import com.onixbyte.calendar.util.PropertyAppender;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public final class Alarm implements CalendarComponent {

    private static final String COMPONENT_NAME = "VALARM";

    private final AlarmProp alarmProp;

    private Alarm(AlarmProp alarmProp) {
        this.alarmProp = alarmProp;
    }

    @Override
    public String formatted() {
        var builder = new StringBuilder();
        var propertyAppender = of(builder);

        builder.append("BEGIN").append(":").append(COMPONENT_NAME);

        propertyAppender.append(alarmProp);

        builder.append("\n").append("END").append(":").append(COMPONENT_NAME);

        return builder.toString();
    }

    public static AlarmBuilder builder() {
        return new AlarmBuilder();
    }

    public static Audio.AudioBuilder audioBuilder() {
        return new Audio.AudioBuilder();
    }

    public static Display.DisplayBuilder displayBuilder() {
        return new Display.DisplayBuilder();
    }

    public static Email.EmailBuilder emailBuilder() {
        return new Email.EmailBuilder();
    }

    public static class AlarmBuilder {
        private AlarmBuilder() {
        }

        public Alarm build(AlarmProp alarmProp) {
            return new Alarm(alarmProp);
        }
    }

    public interface AlarmProp extends ComponentProperty {
    }

    public static class Audio implements AlarmProp {
        // `action` and `trigger` are both required, but must not occur more than once
        private final Action action;
        private final Trigger trigger;

        // `duration` and `repeat` are both optional, and must not occur more than once each;
        // but if one occurs, so must the other
        private final Duration duration;
        private final RepeatCount repeatCount;

        // the following is optional, but must not occur more than once
        private final Attachment attachment;

        private Audio(
                Action action,
                Trigger trigger,
                Duration duration,
                RepeatCount repeatCount,
                Attachment attachment
        ) {
            this.action = action;
            this.trigger = trigger;
            this.duration = duration;
            this.repeatCount = repeatCount;
            this.attachment = attachment;
        }

        public static class AudioBuilder {
            private Action action;
            private Trigger trigger;
            private Duration duration;
            private RepeatCount repeatCount;
            private Attachment attachment;

            private AudioBuilder() {
            }

            public AudioBuilder withAction(Action action) {
                this.action = action;
                return this;
            }

            public AudioBuilder withTrigger(Trigger trigger) {
                this.trigger = trigger;
                return this;
            }

            public AudioBuilder withDuration(Duration duration) {
                this.duration = duration;
                return this;
            }

            public AudioBuilder withRepeatCount(RepeatCount repeatCount) {
                this.repeatCount = repeatCount;
                return this;
            }

            public AudioBuilder withAttachment(Attachment attachment) {
                this.attachment = attachment;
                return this;
            }

            public Audio build() {
                if (Objects.isNull(action)) {
                    throw new IllegalArgumentException("Action is required.");
                }

                if (Objects.isNull(trigger)) {
                    throw new IllegalArgumentException("Trigger is required.");
                }

                var durationIsNull = Objects.isNull(duration);
                var repeatCountIsNull = Objects.isNull(repeatCount);

                // if one of duration and repeat is null, throw exception.
                if (durationIsNull ^ repeatCountIsNull) {
                    throw new IllegalArgumentException("Duration and repeatCount must be both null or both non-null");
                }

                return new Audio(action, trigger, duration, repeatCount, attachment);
            }
        }

        @Override
        public String formatted() {
            var builder = new StringBuilder();
            var propertyAppender = PropertyAppender.of(builder);

            builder.append(action.formatted());
            propertyAppender.append(trigger);
            propertyAppender.append(duration);
            propertyAppender.append(repeatCount);
            propertyAppender.append(attachment);
            return builder.toString();
        }
    }

    public static class Display implements AlarmProp {
        // the following are required, but must not occur more than once
        private final Action action;
        private final Description description;
        private final Trigger trigger;

        // `duration` and `repeat` are both optional, and must not occur more than once each;
        // but if one occurs, so must the other
        private final Duration duration;
        private final RepeatCount repeatCount;

        private Display(
                Action action,
                Description description,
                Trigger trigger,
                Duration duration,
                RepeatCount repeatCount
        ) {
            this.action = action;
            this.description = description;
            this.trigger = trigger;
            this.duration = duration;
            this.repeatCount = repeatCount;
        }

        public static class DisplayBuilder {
            private DisplayBuilder() {
            }

            private Action action;
            private Description description;
            private Trigger trigger;
            private Duration duration;
            private RepeatCount repeatCount;

            public DisplayBuilder withAction(Action action) {
                this.action = action;
                return this;
            }

            public DisplayBuilder withDescription(Description description) {
                this.description = description;
                return this;
            }

            public DisplayBuilder withTrigger(Trigger trigger) {
                this.trigger = trigger;
                return this;
            }

            public DisplayBuilder withDuration(Duration duration) {
                this.duration = duration;
                return this;
            }

            public DisplayBuilder withRepeatCount(RepeatCount repeatCount) {
                this.repeatCount = repeatCount;
                return this;
            }

            public Display build() {
                if (Objects.isNull(action)) {
                    throw new IllegalArgumentException("Action is required.");
                }

                if (Objects.isNull(description)) {
                    throw new IllegalArgumentException("Description is required.");
                }

                if (Objects.isNull(trigger)) {
                    throw new IllegalArgumentException("Trigger is required.");
                }

                var durationIsNull = Objects.isNull(duration);
                var repeatCountIsNull = Objects.isNull(repeatCount);

                // if one of duration and repeat is null, throw exception.
                if (durationIsNull ^ repeatCountIsNull) {
                    throw new IllegalArgumentException("Duration and repeatCount must be both null or both non-null");
                }

                return new Display(action, description, trigger, duration, repeatCount);
            }
        }

        @Override
        public String formatted() {
            var builder = new StringBuilder();
            var propertyAppender = PropertyAppender.of(builder);

            builder.append(action.formatted());
            propertyAppender.append(description);
            propertyAppender.append(trigger);
            propertyAppender.append(duration);
            propertyAppender.append(repeatCount);
            return builder.toString();
        }
    }

    public static class Email implements AlarmProp {
        // the following are all required, but must not occur more than once
        private final Action action;
        private final Description description;
        private final Trigger trigger;
        private final Summary summary;

        // the following is required, and may occur more than once
        private final List<Attendee> attendees;

        // `duration` and `repeat` are both optional, and must not occur more than once each;
        // but if one occurs, so must the other
        private final Duration duration;
        private final RepeatCount repeatCount;

        // the following is optional, and may occur more than once
        private final List<Attachment> attachments;

        private Email(
                Action action,
                Description description,
                Trigger trigger,
                Summary summary,
                List<Attendee> attendees,
                Duration duration,
                RepeatCount repeatCount,
                List<Attachment> attachments
        ) {
            this.action = action;
            this.description = description;
            this.trigger = trigger;
            this.summary = summary;
            this.attendees = attendees;
            this.duration = duration;
            this.repeatCount = repeatCount;
            this.attachments = attachments;
        }

        public static class EmailBuilder {
            private EmailBuilder() {
            }

            private Action action;
            private Description description;
            private Trigger trigger;
            private Summary summary;
            private List<Attendee> attendees;
            private Duration duration;
            private RepeatCount repeatCount;
            private List<Attachment> attachments;

            public EmailBuilder withAction(Action action) {
                this.action = action;
                return this;
            }

            public EmailBuilder withDescription(Description description) {
                this.description = description;
                return this;
            }

            public EmailBuilder withTrigger(Trigger trigger) {
                this.trigger = trigger;
                return this;
            }

            public EmailBuilder withSummary(Summary summary) {
                this.summary = summary;
                return this;
            }

            public EmailBuilder withAttendees(Attendee... attendees) {
                this.attendees = List.of(attendees);
                return this;
            }

            public EmailBuilder withDuration(Duration duration) {
                this.duration = duration;
                return this;
            }

            public EmailBuilder withRepeatCount(RepeatCount repeatCount) {
                this.repeatCount = repeatCount;
                return this;
            }

            public EmailBuilder withAttachments(Attachment... attachments) {
                this.attachments = List.of(attachments);
                return this;
            }

            public Email build() {
                if (Objects.isNull(action)) {
                    throw new IllegalArgumentException("Action is required.");
                }

                if (Objects.isNull(description)) {
                    throw new IllegalArgumentException("Description is required.");
                }

                if (Objects.isNull(trigger)) {
                    throw new IllegalArgumentException("Trigger is required.");
                }

                if (Objects.isNull(summary)) {
                    throw new IllegalArgumentException("Summary is required.");
                }

                if (Objects.isNull(attendees) || attendees.isEmpty()) {
                    throw new IllegalArgumentException("At least 1 attendee is required.");
                }

                var durationIsNull = Objects.isNull(duration);
                var repeatCountIsNull = Objects.isNull(repeatCount);

                // if one of duration and repeat is null, throw exception.
                if (durationIsNull ^ repeatCountIsNull) {
                    throw new IllegalArgumentException("Duration and repeatCount must be both null or both non-null");
                }

                return new Email(action, description, trigger, summary, attendees, duration,
                        repeatCount, attachments);
            }
        }

        @Override
        public String formatted() {
            var builder = new StringBuilder();
            var propertyAppender = PropertyAppender.of(builder);

            builder.append(action.formatted());
            propertyAppender.append(description);
            propertyAppender.append(trigger);
            propertyAppender.append(summary);
            attendees.forEach(propertyAppender::append);
            propertyAppender.append(duration);
            propertyAppender.append(repeatCount);
            attachments.forEach(propertyAppender::append);
            return builder.toString();
        }
    }

    private static AlarmPropAppender of(StringBuilder builder) {
        return new AlarmPropAppender(builder);
    }

    private record AlarmPropAppender(StringBuilder builder) {

        public void append(AlarmProp alarmProp) {
            builder.append(alarmProp.formatted());
        }
    }
}
