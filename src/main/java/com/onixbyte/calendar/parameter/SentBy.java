package com.onixbyte.calendar.parameter;

import java.net.URI;

public record SentBy(URI sentBy) {

    public static SentBy of(URI sentBy) {
        return new SentBy(sentBy);
    }

    public static SentBy of(String sentBy) {
        return new SentBy(URI.create(sentBy));
    }

    public String formatted() {
        return "SENT-BY=\"" + sentBy.toString() + '"';
    }
}
