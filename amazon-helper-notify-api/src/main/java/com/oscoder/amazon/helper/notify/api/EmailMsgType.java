package com.oscoder.amazon.helper.notify.api;

public enum EmailMsgType {
    SIMPLE(1);
    private Integer type;

    EmailMsgType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
