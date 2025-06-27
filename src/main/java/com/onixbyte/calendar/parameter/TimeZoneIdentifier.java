package com.onixbyte.calendar.parameter;

import java.time.ZoneId;

public final class TimeZoneIdentifier implements Parameter {

    private final ZoneId zoneId;

    private TimeZoneIdentifier(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public static TimeZoneIdentifier of(ZoneId zoneId) {
        return new TimeZoneIdentifier(zoneId);
    }

    public static TimeZoneIdentifier of(String zoneId) {
        return new TimeZoneIdentifier(ZoneId.of(zoneId));
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    @Override
    public String formatted() {
        return "TZID=" + zoneId.getId();
    }
}
