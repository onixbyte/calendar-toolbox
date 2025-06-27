package com.onixbyte.calendar.parameter;

import java.time.ZoneId;

public record TimeZoneIdentifier(ZoneId zoneId) implements Parameter {

    public static TimeZoneIdentifier of(ZoneId zoneId) {
        return new TimeZoneIdentifier(zoneId);
    }

    public static TimeZoneIdentifier of(String zoneId) {
        return new TimeZoneIdentifier(ZoneId.of(zoneId));
    }

    @Override
    public String formatted() {
        return "TZID=" + zoneId.getId();
    }
}
