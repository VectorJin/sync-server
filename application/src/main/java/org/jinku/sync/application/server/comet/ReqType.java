package org.jinku.sync.application.server.comet;

public enum ReqType {

    SIGN_IN(1, "签入"),
    PULL_SYNC(2, "拉取Sync");

    private final int type;
    private final String text;

    ReqType(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public static ReqType get(int type) {
        for (ReqType reqType : ReqType.values()) {
            if (reqType.type == type) {
                return reqType;
            }
        }
        return null;
     }
}
