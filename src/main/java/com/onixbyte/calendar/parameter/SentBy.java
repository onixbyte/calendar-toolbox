package com.onixbyte.calendar.parameter;

import java.net.URI;

public record SentBy(URI sentBy) implements Parameter {

    public static SentBy of(URI sentBy) {
        return new SentBy(sentBy);
    }

    public static SentBy of(String sentBy) {
        return new SentBy(URI.create(sentBy));
    }

    @Override
    public String formatted() {
        return "SENT-BY=\"" + sentBy.toString() + '"';
    }
}
