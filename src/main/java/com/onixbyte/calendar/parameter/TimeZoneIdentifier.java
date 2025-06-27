package com.onixbyte.calendar.parameter;

import java.time.ZoneId;

public record TimeZoneIdentifier(ZoneId zoneId) {

    public static TimeZoneIdentifier of(ZoneId zoneId) {
        return new TimeZoneIdentifier(zoneId);
    }

    public static TimeZoneIdentifier of(String zoneId) {
        return new TimeZoneIdentifier(ZoneId.of(zoneId));
    }

    public String formatted() {
        return "TZID=" + zoneId.getId();
    }
}
