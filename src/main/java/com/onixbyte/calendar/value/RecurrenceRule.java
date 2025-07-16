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

package com.onixbyte.calendar.value;

import com.onixbyte.calendar.recurrence.Frequency;
import com.onixbyte.calendar.recurrence.WeekdayNum;
import com.onixbyte.calendar.util.Formatters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class RecurrenceRule implements PropertyValue {

    // Required
    private final Frequency frequency;

    // Optional components
    private final Integer interval;
    private final Integer count;
    private final LocalDateTime until;
    private final List<Integer> bySecondList;
    private final List<Integer> byMinuteList;
    private final List<Integer> byHourList;
    private final List<WeekdayNum> byDayList;
    private final List<Integer> byMonthDayList;
    private final List<Integer> byYearDayList;
    private final List<Integer> byWeekNoList;
    private final List<Integer> byMonthList;
    private final List<Integer> bySetPosList;
    private final DayOfWeek weekStart;

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

    public static RecurrenceRuleBuilder builder() {
        return new RecurrenceRuleBuilder();
    }

    public static class RecurrenceRuleBuilder {
        private Frequency frequency;
        private Integer interval;
        private Integer count;
        private LocalDateTime until;
        private List<Integer> bySecondList;
        private List<Integer> byMinuteList;
        private List<Integer> byHourList;
        private List<WeekdayNum> byDayList;
        private List<Integer> byMonthDayList;
        private List<Integer> byYearDayList;
        private List<Integer> byWeekNoList;
        private List<Integer> byMonthList;
        private List<Integer> bySetPosList;
        private DayOfWeek weekStart;

        private RecurrenceRuleBuilder() {
        }

        public RecurrenceRuleBuilder withFrequency(Frequency frequency) {
            this.frequency = frequency;
            return this;
        }

        public RecurrenceRuleBuilder withInterval(Integer interval) {
            if (interval < 0) {
                throw new IllegalArgumentException("Interval cannot be less than 0.");
            }
            this.interval = interval;
            return this;
        }

        public RecurrenceRuleBuilder withCount(Integer count) {
            if (count < 0) {
                throw new IllegalArgumentException("Interval cannot be less than 0.");
            }
            this.count = count;
            return this;
        }

        public RecurrenceRuleBuilder withUntil(LocalDateTime until) {
            this.until = until;
            return this;
        }

        public RecurrenceRuleBuilder withUntil(LocalDate until) {
            this.until = until.atTime(23, 59, 59);
            return this;
        }

        public RecurrenceRuleBuilder withBySecondList(Integer... bySecondList) {
            this.bySecondList = Stream.of(bySecondList)
                    .filter((second) -> second >= 0 && second <= 60)
                    .toList();
            return this;
        }

        public RecurrenceRuleBuilder withByMinuteList(Integer... byMinuteList) {
            this.byMinuteList = Stream.of(byMinuteList)
                    .filter((minute) -> minute >= 0 && minute < 60)
                    .toList();
            return this;
        }

        public RecurrenceRuleBuilder withByHourList(Integer... byHourList) {
            this.byHourList = Stream.of(byHourList)
                    .filter((hour) -> hour >= 0 && hour < 24)
                    .toList();
            return this;
        }

        public RecurrenceRuleBuilder withByDayList(WeekdayNum... byDayList) {
            this.byDayList = Stream.of(byDayList)
                    .toList();
            return this;
        }

        public RecurrenceRuleBuilder withByMonthDayList(Integer... byMonthDayList) {
            this.byMonthDayList = Stream.of(byMonthDayList)
                    .filter((monthDay) -> monthDay >= 1 && monthDay <= 31)
                    .toList();
            return this;
        }

        public RecurrenceRuleBuilder withByYearDayList(Integer... byYearDayList) {
            this.byYearDayList = Stream.of(byYearDayList)
                    .filter((yearDay) -> yearDay >= 1 && yearDay <= 366)
                    .toList();
            return this;
        }

        public RecurrenceRuleBuilder withByWeekNoList(Integer... byWeekNoList) {
            this.byWeekNoList = Stream.of(byWeekNoList)
                    .filter((week) -> week >= 1 && week <= 53)
                    .toList();
            return this;
        }

        public RecurrenceRuleBuilder withByMonthList(Integer... byMonthList) {
            this.byMonthList = Stream.of(byMonthList)
                    .filter((month) -> month >= 1 && month <= 12)
                    .toList();
            return this;
        }

        public RecurrenceRuleBuilder withBySetPosList(Integer... bySetPosList) {
            this.bySetPosList = Stream.of(bySetPosList)
                    .filter((setPos) -> (setPos >= -366 && setPos < 0) || (setPos > 0 && setPos <= 366))
                    .toList();
            return this;
        }

        public RecurrenceRuleBuilder withWeekStart(DayOfWeek weekStart) {
            this.weekStart = weekStart;
            return this;
        }

        public RecurrenceRule build() {
            return new RecurrenceRule(frequency, interval, count, until, bySecondList, byMinuteList,
                    byHourList, byDayList, byMonthDayList, byYearDayList, byWeekNoList, byMonthList,
                    bySetPosList, weekStart);
        }
    }

    /**
     * Returns a formatted iCalendar recurrence rule string.
     *
     * @return a properly formatted RRULE string
     */
    @Override
    public String formatted() {
        StringBuilder builder = new StringBuilder();
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
