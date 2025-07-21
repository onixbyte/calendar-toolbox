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
import com.onixbyte.common.util.CollectionUtil;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Represents an iCalendar {@code VALARM} component that specifies alarm or reminder information for
 * calendar components.
 * <p>
 * Alarms are used to trigger notifications or actions at a specified time relative to the start or
 * end of an event, or at an absolute time. The {@code VALARM} component supports several types of
 * alarms including audio, display, and email alarms.
 * <p>
 * This class provides factory methods for creating different types of alarms:
 * <ul>
 *   <li>{@link #audioBuilder()} - for audio alarms that play sounds</li>
 *   <li>{@link #displayBuilder()} - for display alarms that show text</li>
 *   <li>{@link #emailBuilder()} - for email alarms that send messages</li>
 * </ul>
 * <p>
 * All alarms require an action property that specifies the type of alarm and a trigger property
 * that defines when the alarm should be activated.
 * <p>
 * Instances of this class are immutable and can be created using the builder pattern via
 * {@link #builder()} or the type-specific builders.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Alarm implements CalendarComponent {

    /**
     * The standard component name for VALARM components in iCalendar format.
     */
    private static final String COMPONENT_NAME = "VALARM";

    /**
     * The alarm properties that define the behaviour and characteristics of this alarm.
     * This encapsulates all the required and optional properties for the specific alarm type.
     */
    private final AlarmProp alarmProp;

    /**
     * Constructs a new Alarm instance with the specified alarm properties.
     *
     * @param alarmProp the alarm properties defining this alarm's behaviour
     */
    private Alarm(AlarmProp alarmProp) {
        this.alarmProp = alarmProp;
    }

    /**
     * Returns the formatted iCalendar representation of this alarm component.
     * <p>
     * The format follows the iCalendar specification with {@code BEGIN:VALARM} and
     * {@code END:VALARM} delimiters containing all the alarm properties.
     *
     * @return the formatted iCalendar {@code VALARM} component string
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        var propertyAppender = of(builder);

        builder.append("BEGIN").append(":").append(COMPONENT_NAME);

        propertyAppender.append(alarmProp);

        builder.append("\n").append("END").append(":").append(COMPONENT_NAME);

        return builder.toString();
    }

    /**
     * Creates a new builder instance for constructing a generic Alarm.
     * <p>
     * For specific alarm types, consider using the type-specific builders: {@link #audioBuilder()},
     * {@link #displayBuilder()}, or {@link #emailBuilder()}.
     *
     * @return a new AlarmBuilder instance
     */
    public static AlarmBuilder builder() {
        return new AlarmBuilder();
    }

    /**
     * Creates a new builder instance specifically for constructing audio alarms.
     * <p>
     * Audio alarms play a sound file or generate an audible alert when triggered.
     *
     * @return a new Audio.AudioBuilder instance
     */
    public static Audio.AudioBuilder audioBuilder() {
        return new Audio.AudioBuilder();
    }

    /**
     * Creates a new builder instance specifically for constructing display alarms.
     * <p>
     * Display alarms show a text message when triggered.
     *
     * @return a new Display.DisplayBuilder instance
     */
    public static Display.DisplayBuilder displayBuilder() {
        return new Display.DisplayBuilder();
    }

    /**
     * Creates a new builder instance specifically for constructing email alarms.
     * <p>
     * Email alarms send an email message to specified recipients when triggered.
     *
     * @return a new Email.EmailBuilder instance
     */
    public static Email.EmailBuilder emailBuilder() {
        return new Email.EmailBuilder();
    }

    /**
     * Builder class for constructing generic Alarm instances.
     * <p>
     * This builder is used when you already have an AlarmProp instance to wrap in an
     * Alarm component. For most use cases, consider using the type-specific builders instead.
     */
    public static class AlarmBuilder {
        /**
         * Private constructor to enforce use of the factory method.
         */
        private AlarmBuilder() {
        }

        /**
         * Creates a new Alarm instance with the specified alarm properties.
         *
         * @param alarmProp the alarm properties defining the alarm's behaviour
         * @return a new Alarm instance
         */
        public Alarm build(AlarmProp alarmProp) {
            return new Alarm(alarmProp);
        }
    }

    /**
     * Base interface for all alarm property implementations.
     * <p>
     * This interface extends ComponentProperty and serves as a marker for classes that represent
     * different types of alarm configurations (Audio, Display, Email). Each implementation defines
     * the specific properties required for that alarm type.
     */
    public interface AlarmProp extends ComponentProperty {
    }

    /**
     * Represents an audio alarm implementation that plays a sound when triggered.
     * <p>
     * Audio alarms are used to generate audible notifications by playing a sound file or tone.
     * The alarm requires an action and trigger property, and optionally supports duration and
     * repeat count for recurring audio alerts. An attachment can be specified to define the
     * audio file to be played.
     * <p>
     * According to RFC 5545, if duration and repeat count are specified, they must both be
     * present together to define how often and for how long the audio alarm should repeat.
     */
    public static class Audio implements AlarmProp {
        /**
         * The action property specifying this is an audio alarm (required).
         */
        private final Action action;
        
        /**
         * The trigger property defining when the alarm should be activated (required).
         */
        private final Trigger trigger;

        /**
         * The duration property specifying how long the alarm should last (optional).
         * Must be specified together with repeatCount if used.
         */
        private final Duration duration;
        
        /**
         * The repeat count property specifying how many times the alarm should repeat (optional).
         * Must be specified together with duration if used.
         */
        private final RepeatCount repeatCount;

        /**
         * The attachment property specifying the audio file to be played (optional).
         */
        private final Attachment attachment;

        /**
         * Constructs a new Audio alarm with the specified properties.
         *
         * @param action the action property defining this as an audio alarm
         * @param trigger the trigger property defining when to activate the alarm
         * @param duration the duration property for how long the alarm should last (may be null)
         * @param repeatCount the repeat count for how many times to repeat (may be null)
         * @param attachment the attachment specifying the audio file to play (may be null)
         */
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

        /**
         * Builder class for constructing Audio alarm instances.
         * <p>
         * This builder provides a fluent interface for creating audio alarms with the required
         * action and trigger properties, and optional duration, repeat count, and attachment
         * properties. The builder enforces the RFC 5545 requirement that duration and repeat
         * count must both be present or both be absent.
         */
        public static class AudioBuilder {
            /**
             * The action property for the audio alarm being built.
             */
            private Action action;
            
            /**
             * The trigger property for the audio alarm being built.
             */
            private Trigger trigger;
            
            /**
             * The duration property for the audio alarm being built.
             */
            private Duration duration;
            
            /**
             * The repeat count property for the audio alarm being built.
             */
            private RepeatCount repeatCount;
            
            /**
             * The attachment property for the audio alarm being built.
             */
            private Attachment attachment;

            /**
             * Private constructor to enforce use through the factory method.
             */
            private AudioBuilder() {
            }

            /**
             * Sets the action property for the audio alarm.
             *
             * @param action the action property (required)
             * @return this builder instance for method chaining
             */
            public AudioBuilder withAction(Action action) {
                this.action = action;
                return this;
            }

            /**
             * Sets the trigger property for the audio alarm.
             *
             * @param trigger the trigger property defining when to activate (required)
             * @return this builder instance for method chaining
             */
            public AudioBuilder withTrigger(Trigger trigger) {
                this.trigger = trigger;
                return this;
            }

            /**
             * Sets the duration property for the audio alarm.
             * Must be used together with {@link #withRepeatCount(RepeatCount)}.
             *
             * @param duration the duration property for how long the alarm should last
             * @return this builder instance for method chaining
             */
            public AudioBuilder withDuration(Duration duration) {
                this.duration = duration;
                return this;
            }

            /**
             * Sets the repeat count property for the audio alarm.
             * Must be used together with {@link #withDuration(Duration)}.
             *
             * @param repeatCount the repeat count property for how many times to repeat
             * @return this builder instance for method chaining
             */
            public AudioBuilder withRepeatCount(RepeatCount repeatCount) {
                this.repeatCount = repeatCount;
                return this;
            }

            /**
             * Sets the attachment property for the audio alarm.
             *
             * @param attachment the attachment specifying the audio file to play
             * @return this builder instance for method chaining
             */
            public AudioBuilder withAttachment(Attachment attachment) {
                this.attachment = attachment;
                return this;
            }

            /**
             * Builds and returns a new Audio alarm instance.
             * <p>
             * Validates that required properties (action and trigger) are present and that
             * duration and repeat count are either both present or both absent.
             *
             * @return a new Audio alarm instance
             * @throws IllegalArgumentException if required properties are missing or if
             *                                  duration and repeat count are not both present or both absent
             */
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

        /**
         * Returns the formatted iCalendar representation of this audio alarm.
         * <p>
         * The format includes all the audio alarm properties in the correct iCalendar syntax,
         * starting with the action property followed by trigger, duration (if present),
         * repeat count (if present), and attachment (if present).
         *
         * @return the formatted iCalendar audio alarm properties as a string
         */
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

    /**
     * Represents a display alarm implementation that shows a text message when triggered.
     * <p>
     * Display alarms are used to present visual notifications by showing descriptive text
     * to the user. The alarm requires action, description, and trigger properties, and
     * optionally supports duration and repeat count for recurring display alerts.
     * <p>
     * According to RFC 5545, if duration and repeat count are specified, they must both be
     * present together to define how often and for how long the display alarm should repeat.
     */
    public static class Display implements AlarmProp {
        /**
         * The action property specifying this is a display alarm (required).
         */
        private final Action action;
        
        /**
         * The description property containing the text to display (required).
         */
        private final Description description;
        
        /**
         * The trigger property defining when the alarm should be activated (required).
         */
        private final Trigger trigger;

        /**
         * The duration property specifying how long the alarm should last (optional).
         * Must be specified together with repeatCount if used.
         */
        private final Duration duration;
        
        /**
         * The repeat count property specifying how many times the alarm should repeat (optional).
         * Must be specified together with duration if used.
         */
        private final RepeatCount repeatCount;

        /**
         * Constructs a new Display alarm with the specified properties.
         *
         * @param action the action property defining this as a display alarm
         * @param description the description property containing the text to display
         * @param trigger the trigger property defining when to activate the alarm
         * @param duration the duration property for how long the alarm should last (may be null)
         * @param repeatCount the repeat count for how many times to repeat (may be null)
         */
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

        /**
         * Builder class for constructing Display alarm instances.
         * <p>
         * This builder provides a fluent interface for creating display alarms with the required
         * action, description, and trigger properties, and optional duration and repeat count
         * properties. The builder enforces the RFC 5545 requirement that duration and repeat
         * count must both be present or both be absent.
         */
        public static class DisplayBuilder {
            /**
             * Private constructor to enforce use through the factory method.
             */
            private DisplayBuilder() {
            }

            /**
             * The action property for the display alarm being built.
             */
            private Action action;
            
            /**
             * The description property for the display alarm being built.
             */
            private Description description;
            
            /**
             * The trigger property for the display alarm being built.
             */
            private Trigger trigger;
            
            /**
             * The duration property for the display alarm being built.
             */
            private Duration duration;
            
            /**
             * The repeat count property for the display alarm being built.
             */
            private RepeatCount repeatCount;

            /**
             * Sets the action property for the display alarm.
             *
             * @param action the action property (required)
             * @return this builder instance for method chaining
             */
            public DisplayBuilder withAction(Action action) {
                this.action = action;
                return this;
            }

            /**
             * Sets the description property for the display alarm.
             *
             * @param description the description property containing the text to display (required)
             * @return this builder instance for method chaining
             */
            public DisplayBuilder withDescription(Description description) {
                this.description = description;
                return this;
            }

            /**
             * Sets the trigger property for the display alarm.
             *
             * @param trigger the trigger property defining when to activate (required)
             * @return this builder instance for method chaining
             */
            public DisplayBuilder withTrigger(Trigger trigger) {
                this.trigger = trigger;
                return this;
            }

            /**
             * Sets the duration property for the display alarm.
             * Must be used together with {@link #withRepeatCount(RepeatCount)}.
             *
             * @param duration the duration property for how long the alarm should last
             * @return this builder instance for method chaining
             */
            public DisplayBuilder withDuration(Duration duration) {
                this.duration = duration;
                return this;
            }

            /**
             * Sets the repeat count property for the display alarm.
             * Must be used together with {@link #withDuration(Duration)}.
             *
             * @param repeatCount the repeat count property for how many times to repeat
             * @return this builder instance for method chaining
             */
            public DisplayBuilder withRepeatCount(RepeatCount repeatCount) {
                this.repeatCount = repeatCount;
                return this;
            }

            /**
             * Builds and returns a new Display alarm instance.
             * <p>
             * Validates that required properties (action, description, and trigger) are present
             * and that duration and repeat count are either both present or both absent.
             *
             * @return a new Display alarm instance
             * @throws IllegalArgumentException if required properties are missing or if
             *                                  duration and repeat count are not both present or both absent
             */
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

        /**
         * Returns the formatted iCalendar representation of this display alarm.
         * <p>
         * The format includes all the display alarm properties in the correct iCalendar syntax,
         * starting with the action property followed by description, trigger, duration (if present),
         * and repeat count (if present).
         *
         * @return the formatted iCalendar display alarm properties as a string
         */
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

    /**
     * Represents an email alarm implementation that sends an email message when triggered.
     * <p>
     * Email alarms are used to send email notifications to specified attendees when the alarm
     * is triggered. The alarm requires action, description, trigger, summary, and at least one
     * attendee, and optionally supports duration, repeat count, and multiple attachments.
     * <p>
     * According to RFC 5545, if duration and repeat count are specified, they must both be
     * present together to define how often and for how long the email alarm should repeat.
     */
    public static class Email implements AlarmProp {
        /**
         * The action property specifying this is an email alarm (required).
         */
        private final Action action;
        
        /**
         * The description property containing the email message body (required).
         */
        private final Description description;
        
        /**
         * The trigger property defining when the alarm should be activated (required).
         */
        private final Trigger trigger;
        
        /**
         * The summary property containing the email subject line (required).
         */
        private final Summary summary;

        /**
         * The list of attendees who will receive the email (required, at least one).
         */
        private final List<Attendee> attendees;

        /**
         * The duration property specifying how long the alarm should last (optional).
         * Must be specified together with repeatCount if used.
         */
        private final Duration duration;
        
        /**
         * The repeat count property specifying how many times the alarm should repeat (optional).
         * Must be specified together with duration if used.
         */
        private final RepeatCount repeatCount;

        /**
         * The list of attachments to include with the email (optional).
         */
        private final List<Attachment> attachments;

        /**
         * Constructs a new Email alarm with the specified properties.
         *
         * @param action the action property defining this as an email alarm
         * @param description the description property containing the email message body
         * @param trigger the trigger property defining when to activate the alarm
         * @param summary the summary property containing the email subject line
         * @param attendees the list of attendees who will receive the email
         * @param duration the duration property for how long the alarm should last (may be null)
         * @param repeatCount the repeat count for how many times to repeat (may be null)
         * @param attachments the list of attachments to include (may be null)
         */
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

        /**
         * Builder class for constructing Email alarm instances.
         * <p>
         * This builder provides a fluent interface for creating email alarms with the required
         * action, description, trigger, summary, and attendee properties, and optional duration,
         * repeat count, and attachment properties. The builder enforces the RFC 5545 requirement
         * that duration and repeat count must both be present or both be absent, and that at
         * least one attendee must be specified.
         */
        public static class EmailBuilder {
            /**
             * Private constructor to enforce use through the factory method.
             */
            private EmailBuilder() {
            }

            /**
             * The action property for the email alarm being built.
             */
            private Action action;
            
            /**
             * The description property for the email alarm being built.
             */
            private Description description;
            
            /**
             * The trigger property for the email alarm being built.
             */
            private Trigger trigger;
            
            /**
             * The summary property for the email alarm being built.
             */
            private Summary summary;
            
            /**
             * The list of attendees for the email alarm being built.
             */
            private List<Attendee> attendees;
            
            /**
             * The duration property for the email alarm being built.
             */
            private Duration duration;
            
            /**
             * The repeat count property for the email alarm being built.
             */
            private RepeatCount repeatCount;
            
            /**
             * The list of attachments for the email alarm being built.
             */
            private List<Attachment> attachments;

            /**
             * Sets the action property for the email alarm.
             *
             * @param action the action property (required)
             * @return this builder instance for method chaining
             */
            public EmailBuilder withAction(Action action) {
                this.action = action;
                return this;
            }

            /**
             * Sets the description property for the email alarm.
             *
             * @param description the description property containing the email message body (required)
             * @return this builder instance for method chaining
             */
            public EmailBuilder withDescription(Description description) {
                this.description = description;
                return this;
            }

            /**
             * Sets the trigger property for the email alarm.
             *
             * @param trigger the trigger property defining when to activate (required)
             * @return this builder instance for method chaining
             */
            public EmailBuilder withTrigger(Trigger trigger) {
                this.trigger = trigger;
                return this;
            }

            /**
             * Sets the summary property for the email alarm.
             *
             * @param summary the summary property containing the email subject line (required)
             * @return this builder instance for method chaining
             */
            public EmailBuilder withSummary(Summary summary) {
                this.summary = summary;
                return this;
            }

            /**
             * Sets the attendees for the email alarm.
             *
             * @param attendees the attendees who will receive the email (at least one required)
             * @return this builder instance for method chaining
             */
            public EmailBuilder withAttendees(Attendee... attendees) {
                this.attendees = List.of(attendees);
                return this;
            }

            /**
             * Sets the duration property for the email alarm.
             * Must be used together with {@link #withRepeatCount(RepeatCount)}.
             *
             * @param duration the duration property for how long the alarm should last
             * @return this builder instance for method chaining
             */
            public EmailBuilder withDuration(Duration duration) {
                this.duration = duration;
                return this;
            }

            /**
             * Sets the repeat count property for the email alarm.
             * Must be used together with {@link #withDuration(Duration)}.
             *
             * @param repeatCount the repeat count property for how many times to repeat
             * @return this builder instance for method chaining
             */
            public EmailBuilder withRepeatCount(RepeatCount repeatCount) {
                this.repeatCount = repeatCount;
                return this;
            }

            /**
             * Sets the attachments for the email alarm.
             *
             * @param attachments the attachments to include with the email
             * @return this builder instance for method chaining
             */
            public EmailBuilder withAttachments(Attachment... attachments) {
                this.attachments = List.of(attachments);
                return this;
            }

            /**
             * Builds and returns a new Email alarm instance.
             * <p>
             * Validates that required properties (action, description, trigger, summary, and
             * at least one attendee) are present and that duration and repeat count are either
             * both present or both absent.
             *
             * @return a new Email alarm instance
             * @throws IllegalArgumentException if required properties are missing, if no attendees
             *                                  are specified, or if duration and repeat count are
             *                                  not both present or both absent
             */
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

        /**
         * Returns the formatted iCalendar representation of this email alarm.
         * <p>
         * The format includes all the email alarm properties in the correct iCalendar syntax,
         * starting with the action property followed by description, trigger, summary, all
         * attendees, duration (if present), repeat count (if present), and attachments (if present).
         *
         * @return the formatted iCalendar email alarm properties as a string
         */
        @Override
        public String formatted() {
            var builder = new StringBuilder();
            var propertyAppender = PropertyAppender.of(builder);

            builder.append(action.formatted());
            propertyAppender.append(description);
            propertyAppender.append(trigger);
            propertyAppender.append(summary);

            if (CollectionUtil.notEmpty(attendees)) {
                attendees.forEach(propertyAppender::append);
            }
            propertyAppender.append(duration);
            propertyAppender.append(repeatCount);
            propertyAppender.append(attachments);
            return builder.toString();
        }
    }

    /**
     * Creates a new AlarmPropAppender instance for the given StringBuilder.
     *
     * @param builder the StringBuilder to append formatted alarm properties to
     * @return a new AlarmPropAppender instance
     */
    private static AlarmPropAppender of(StringBuilder builder) {
        return new AlarmPropAppender(builder);
    }

    /**
     * A helper record for appending formatted alarm properties to a StringBuilder.
     * <p>
     * This record encapsulates the logic for safely appending alarm property content
     * to a string builder, handling null checks and proper formatting with newlines.
     *
     * @param builder the StringBuilder to append formatted content to
     */
    private record AlarmPropAppender(StringBuilder builder) {

        /**
         * Appends the formatted representation of an alarm property to the builder.
         * <p>
         * If the alarm property is non-null, its formatted representation is appended
         * with a preceding newline. If the property is null, no content is appended.
         *
         * @param alarmProp the alarm property to append (may be null)
         * @return the StringBuilder instance for method chaining
         */
        public StringBuilder append(AlarmProp alarmProp) {
            if (Objects.nonNull(alarmProp)) {
                builder.append("\n").append(alarmProp.formatted());
            }
            return builder;
        }
    }
}
