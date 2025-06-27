package com.onixbyte.calendar.parameter;

public enum RelationshipType implements Parameter {

    CHILD,
    PARENT,
    SIBLING,
    SNOOZE,
    CONCEPT,
    DEPENDS_ON("DEPENDS-ON"),
    FINISH_TO_FINISH("FINISHTOFINISH"),
    FINISH_TO_START("FINISHTOSTART"),
    FIRST,
    NEXT,
    REFERENCE_ID("REFID"),
    START_TO_FINISH("STARTTOFINISH"),
    START_TO_START("STARTTOSTART"),
    ;

    private final String label;

    RelationshipType() {
        this.label = name();
    }

    RelationshipType(String label) {
        this.label = label;
    }

    @Override
    public String formatted() {
        return "RELTYPE=" + label;
    }
}
