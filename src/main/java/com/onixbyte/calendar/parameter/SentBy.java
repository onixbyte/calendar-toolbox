package com.onixbyte.calendar.parameter;

import java.net.URI;

public final class SentBy implements Parameter {

    private final URI sentBy;

    private SentBy(URI sentBy) {
        this.sentBy = sentBy;
    }

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
