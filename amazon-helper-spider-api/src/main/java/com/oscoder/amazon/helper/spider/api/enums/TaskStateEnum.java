package com.oscoder.amazon.helper.spider.api.enums;

public enum TaskStateEnum {
    Init(1,"初始态"),
    RUNNING(2,"运行态"),
    BLOCK(3,"中断态"),
    COMPLETE(4,"完成态")
    ;
    private Integer state;
    private String desc;

    TaskStateEnum(Integer state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public Integer getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }

    public static TaskStateEnum stateOf(Integer state){
        for (TaskStateEnum value : values()) {
            if (value.getState().equals(state)) {
                return value;
            }
        }
        return null;
    }
}
