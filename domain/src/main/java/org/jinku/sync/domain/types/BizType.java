package org.jinku.sync.domain.types;

import java.util.Arrays;

public enum BizType {

    IM(1, "IM聊天");

    int value;
    String text;

    BizType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static BizType get(int value) {
        return Arrays.stream(values()).filter(type -> type.getValue() == value).findFirst().orElse(null);
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
