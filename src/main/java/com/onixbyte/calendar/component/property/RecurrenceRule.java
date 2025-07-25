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

package com.onixbyte.calendar.component.property;

import com.onixbyte.calendar.recurrence.Frequency;
import com.onixbyte.calendar.recurrence.WeekdayNum;
import com.onixbyte.calendar.util.Formatters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the {@code RRULE} property in an iCalendar component.
 * <p>
 * This property defines a rule or repeating pattern for recurring events, to-dos, journal entries,
 * or time zone definitions. The recurrence rule specification allows for complex recurring patterns
 * including frequency, interval, count, end date, and various by-rules for fine-grained control.
 * <p>
 * The property supports all recurrence rule components as defined in RFC 5545, including frequency
 * and various optional components for detailed recurrence pattern specification.
 *
 * @author siujamo
 * @author zihluwang
 * @version 1.0.0
 */
public final class RecurrenceRule implements ComponentProperty {

    /**
     * The frequency component of the recurrence rule.
     */
    private final Frequency frequency;

    /**
     * The interval component specifying the frequency interval. For example, an interval of 2 with
     * DAILY frequency means every 2 days.
     */
    private final Integer interval;

    /**
     * The count component specifying the number of occurrences. This limits the recurrence to a
     * specific number of instances.
     */
    private final Integer count;

    /**
     * The until component specifying the end date/time for the recurrence. The recurrence will end
     * at or before this date/time.
     */
    private final LocalDateTime until;

    /**
     * The {@code BYSECOND} rule component specifying seconds within a minute. Values range from
     * {@code 0} to {@code 60} (including leap seconds).
     */
    private final List<Integer> bySecondList;

    /**
     * The {@code BYMINUTE} rule component specifying minutes within an hour. Values range from
     * {@code 0} to {@code 59}.
     */
    private final List<Integer> byMinuteList;

    /**
     * The {@code BYHOUR} rule component specifying hours within a day. Values range from
     * {@code 0} to {@code 23}.
     */
    private final List<Integer> byHourList;

    /**
     * The {@code BYDAY} rule component specifying days of the week. Can include ordinal numbers for
     * specific occurrences (e.g., first Monday).
     */
    private final List<WeekdayNum> byDayList;

    /**
     * The {@code BYMONTHDAY} rule component specifying days of the month. Values range from
     * {@code 1} to {@code 31}.
     */
    private final List<Integer> byMonthDayList;

    /**
     * The {@code BYYEARDAY} rule component specifying days of the year. Values range from {@code 1}
     * to {@code 366}.
     */
    private final List<Integer> byYearDayList;

    /**
     * The {@code BYWEEKNO} rule component specifying weeks of the year. Values range from {@code 1}
     * to {@code 53}.
     */
    private final List<Integer> byWeekNoList;

    /**
     * The {@code BYMONTH} rule component specifying months of the year. Values range from {@code 1}
     * to {@code 12}.
     */
    private final List<Integer> byMonthList;

    /**
     * The {@code BYSETPOS} rule component for limiting recurrence instances. Values range from
     * {@code -366} to {@code 366} (excluding 0).
     */
    private final List<Integer> bySetPosList;

    /**
     * The {@code WKST} rule component specifying the start of the week. Defaults to <b>Monday</b>
     * if not specified.
     */
    private final DayOfWeek weekStart;

    /**
     * Constructs a new RecurrenceRule instance with the specified components.
     *
     * @param frequency      the frequency component
     * @param interval       the interval component
     * @param count          the count component
     * @param until          the until component
     * @param bySecondList   the {@code BYSECOND} rule component
     * @param byMinuteList   the {@code BYMINUTE} rule component
     * @param byHourList     the {@code BYHOUR} rule component
     * @param byDayList      the {@code BYDAY} rule component
     * @param byMonthDayList the {@code BYMONTHDAY} rule component
     * @param byYearDayList  the {@code BYYEARDAY} rule component
     * @param byWeekNoList   the {@code BYWEEKNO} rule component
     * @param byMonthList    the {@code BYMONTH} rule component
     * @param bySetPosList   the {@code BYSETPOS} rule component
     * @param weekStart      the {@code WKST} rule component
     */
    private RecurrenceRule(
            Frequency frequency,
            Integer interval,
            Integer count,
            LocalDateTime until,
            List<Integer> bySecondList,
            List<Integer> byMinuteList,
            List<Integer> byHourList,
            List<WeekdayNum> byDayList,
            List<Integer> byMonthDayList,
            List<Integer> byYearDayList,
            List<Integer> byWeekNoList,
            List<Integer> byMonthList,
            List<Integer> bySetPosList,
            DayOfWeek weekStart
    ) {
        this.frequency = frequency;
        this.interval = interval;
        this.count = count;
        this.until = until;
        this.bySecondList = bySecondList;
        this.byMinuteList = byMinuteList;
        this.byHourList = byHourList;
        this.byDayList = byDayList;
        this.byMonthDayList = byMonthDayList;
        this.byYearDayList = byYearDayList;
        this.byWeekNoList = byWeekNoList;
        this.byMonthList = byMonthList;
        this.bySetPosList = bySetPosList;
        this.weekStart = weekStart;
    }

    /**
     * Creates a new {@code RecurrenceRuleBuilder} instance for constructing
     * {@code RecurrenceRule} objects.
     *
     * @return a new {@code RecurrenceRuleBuilder} instance
     */
    public static RecurrenceRuleBuilder builder() {
        return new RecurrenceRuleBuilder();
    }

    /**
     * Builder class for constructing {@code RecurrenceRule} instances using the builder pattern.
     * <p>
     * This builder provides a fluent interface for creating {@code RecurrenceRule} objects with the
     * required frequency component and various optional rule components. The builder validates
     * input values to ensure they conform to RFC 5545 specifications.
     * <p>
     * Example usage:
     * <pre>{@code
     * RecurrenceRule rule = RecurrenceRule.builder()
     *     .withFrequency(Frequency.WEEKLY)
     *     .withInterval(2)
     *     .withByDayList(WeekdayNum.of(DayOfWeek.MONDAY))
     *     .withCount(10)
     *     .build();
     * }</pre>
     */
    public static class RecurrenceRuleBuilder {
        /**
         * The frequency component of the recurrence rule being built.
         */
        private Frequency frequency;

        /**
         * The interval component of the recurrence rule being built.
         */
        private Integer interval;

        /**
         * The count component of the recurrence rule being built.
         */
        private Integer count;

        /**
         * The until component of the recurrence rule being built.
         */
        private LocalDateTime until;

        /**
         * The {@code BYSECOND} rule component of the recurrence rule being built.
         */
        private List<Integer> bySecond;

        /**
         * The {@code BYMINUTE} rule component of the recurrence rule being built.
         */
        private List<Integer> byMinute;

        /**
         * The {@code BYHOUR} rule component of the recurrence rule being built.
         */
        private List<Integer> byHour;

        /**
         * The {@code BYDAY} rule component of the recurrence rule being built.
         */
        private List<WeekdayNum> byDay;

        /**
         * The {@code BYMONTHDAY} rule component of the recurrence rule being built.
         */
        private List<Integer> byMonthDay;

        /**
         * The {@code BYYEARDAY} rule component of the recurrence rule being built.
         */
        private List<Integer> byYearDay;

        /**
         * The {@code BYWEEKNO} rule component of the recurrence rule being built.
         */
        private List<Integer> byWeekNo;

        /**
         * The {@code BYMONTH} rule component of the recurrence rule being built.
         */
        private List<Integer> byMonth;

        /**
         * The {@code BYSETPOS} rule component of the recurrence rule being built.
         */
        private List<Integer> bySetPos;

        /**
         * The {@code WKST} rule component of the recurrence rule being built.
         */
        private DayOfWeek weekStart;

        /**
         * Private constructor to enforce use through the factory method.
         */
        private RecurrenceRuleBuilder() {
        }

        /**
         * Sets the frequency component for the recurrence rule.
         *
         * @param frequency the frequency component
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withFrequency(Frequency frequency) {
            this.frequency = frequency;
            return this;
        }

        /**
         * Sets the interval component for the recurrence rule.
         *
         * @param interval the interval component (must be non-negative)
         * @return this builder instance for method chaining
         * @throws IllegalArgumentException if interval is negative
         */
        public RecurrenceRuleBuilder withInterval(Integer interval) {
            if (interval < 0) {
                throw new IllegalArgumentException("Interval cannot be less than 0.");
            }
            this.interval = interval;
            return this;
        }

        /**
         * Sets the count component for the recurrence rule.
         *
         * @param count the count component (must be non-negative)
         * @return this builder instance for method chaining
         * @throws IllegalArgumentException if count is negative
         */
        public RecurrenceRuleBuilder withCount(Integer count) {
            if (count < 0) {
                throw new IllegalArgumentException("Interval cannot be less than 0.");
            }
            this.count = count;
            return this;
        }

        /**
         * Sets the until component for the recurrence rule using a LocalDateTime.
         *
         * @param until the until component as a LocalDateTime
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withUntil(LocalDateTime until) {
            this.until = until;
            return this;
        }

        /**
         * Sets the until component for the recurrence rule using a LocalDate.
         * The time will be set to 23:59:59 on the specified date.
         *
         * @param until the until component as a LocalDate
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withUntil(LocalDate until) {
            this.until = until.atTime(23, 59, 59);
            return this;
        }

        /**
         * Sets the bySecond component for the recurrence rule.
         *
         * @param bySecond the bySecond component as a list of second values
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withBySecond(List<Integer> bySecond) {
            this.bySecond = bySecond;
            return this;
        }

        /**
         * Sets the byMinute component for the recurrence rule.
         *
         * @param byMinute the byMinute component as a list of minute values
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withByMinute(List<Integer> byMinute) {
            this.byMinute = byMinute;
            return this;
        }

        /**
         * Sets the byHour component for the recurrence rule.
         *
         * @param byHour the byHour component as a list of hour values
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withByHour(List<Integer> byHour) {
            this.byHour = byHour;
            return this;
        }

        /**
         * Sets the byDay component for the recurrence rule.
         *
         * @param byDay the byDay component as a list of weekday specifications
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withByDay(List<WeekdayNum> byDay) {
            this.byDay = byDay;
            return this;
        }

        /**
         * Sets the byMonthDay component for the recurrence rule.
         *
         * @param byMonthDay the byMonthDay component as a list of month day values
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withByMonthDay(List<Integer> byMonthDay) {
            this.byMonthDay = byMonthDay;
            return this;
        }

        /**
         * Sets the byYearDay component for the recurrence rule.
         *
         * @param byYearDay the byYearDay component as a list of year day values
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withByYearDay(List<Integer> byYearDay) {
            this.byYearDay = byYearDay;
            return this;
        }

        /**
         * Sets the byWeekNo component for the recurrence rule.
         *
         * @param byWeekNo the byWeekNo component as a list of week number values
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withByWeekNo(List<Integer> byWeekNo) {
            this.byWeekNo = byWeekNo;
            return this;
        }

        /**
         * Sets the byMonth component for the recurrence rule.
         *
         * @param byMonth the byMonth component as a list of month values
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withByMonth(List<Integer> byMonth) {
            this.byMonth = byMonth;
            return this;
        }

        /**
         * Sets the bySetPos component for the recurrence rule.
         *
         * @param bySetPos the bySetPos component as a list of set position values
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withBySetPos(List<Integer> bySetPos) {
            this.bySetPos = bySetPos;
            return this;
        }

        /**
         * Sets the workWeekStart component for the recurrence rule.
         *
         * @param workWeekStart the workWeekStart component
         * @return this builder instance for method chaining
         */
        public RecurrenceRuleBuilder withWorkWeekStart(DayOfWeek workWeekStart) {
            this.weekStart = workWeekStart;
            return this;
        }

        /**
         * Builds a new RecurrenceRule instance with the configured values.
         *
         * @return a new RecurrenceRule instance
         * @throws IllegalStateException if frequency is not set
         */
        public RecurrenceRule build() {
            if (frequency == null) {
                throw new IllegalStateException("Frequency is required to build a valid RecurrenceRule.");
            }
            return new RecurrenceRule(
                    frequency, interval, count, until, bySecond, byMinute, byHour, byDay,
                    byMonthDay, byYearDay, byWeekNo, byMonth, bySetPos, weekStart
            );
        }
    }

    /**
     * Returns the formatted string representation of this recurrence rule
     * as per RFC 5545 specification.
     *
     * @return the formatted recurrence rule string in RRULE format
     */
    @Override
    public String formatted() {
        var builder = new StringBuilder();
        builder.append("FREQ=").append(frequency.name());

        if (interval != null) {
            builder.append(";INTERVAL=").append(interval);
        }

        if (count != null) {
            builder.append(";COUNT=").append(count);
        }

        if (until != null) {
            var endTime = LocalTime.of(23, 59, 59);
            var untilTime = until.toLocalTime();
            var formatter = !untilTime.isAfter(endTime) && !untilTime.isBefore(endTime) ?
                    Formatters.ICALENDAR_DATE_FORMATTER :
                    Formatters.ICALENDAR_UTC_TIMESTAMP_FORMATTER;
            builder.append(";UNTIL=").append(until.format(formatter));
        }

        appendListParameter(builder, "BYSECOND", bySecondList);
        appendListParameter(builder, "BYMINUTE", byMinuteList);
        appendListParameter(builder, "BYHOUR", byHourList);

        if (byDayList != null && !byDayList.isEmpty()) {
            builder.append(";BYDAY=").append(
                    byDayList.stream()
                            .map(WeekdayNum::formatted)
                            .collect(Collectors.joining(","))
            );
        }

        appendListParameter(builder, "BYMONTHDAY", byMonthDayList);
        appendListParameter(builder, "BYYEARDAY", byYearDayList);
        appendListParameter(builder, "BYWEEKNO", byWeekNoList);
        appendListParameter(builder, "BYMONTH", byMonthList);
        appendListParameter(builder, "BYSETPOS", bySetPosList);

        if (weekStart != null) {
            builder.append(";WKST=").append(weekStart.name());
        }

        return builder.toString();
    }

    /**
     * Appends a list parameter to the recurrence rule string builder if the list is not null
     * or empty.
     * <p>
     * This helper method formats integer list parameters according to RFC 5545 specification,
     * converting each integer to a string and joining them with commas. The parameter is only
     * appended if the list contains at least one value.
     * <p>
     * The resulting format is: {@code ;PARAMNAME=value1,value2,value3}
     *
     * @param builder   the StringBuilder to append the parameter to
     * @param paramName the name of the parameter (e.g., "{@code BYSECOND}", "{@code BYMINUTE}")
     * @param values    the list of integer values to append, may be null or empty
     */
    private void appendListParameter(StringBuilder builder, String paramName, List<Integer> values) {
        if (values != null && !values.isEmpty()) {
            builder.append(";").append(paramName).append("=").append(
                    values.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(","))
            );
        }
    }
}
