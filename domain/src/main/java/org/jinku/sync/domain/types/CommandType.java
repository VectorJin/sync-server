package org.jinku.sync.domain.types;

import java.util.Arrays;

public enum CommandType {

    PULL_SYNC(1, "拉取Sync");

    int value;
    String text;

    CommandType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static CommandType get(int value) {
        return Arrays.stream(values()).filter(type -> type.getValue() == value).findFirst().orElse(null);
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
