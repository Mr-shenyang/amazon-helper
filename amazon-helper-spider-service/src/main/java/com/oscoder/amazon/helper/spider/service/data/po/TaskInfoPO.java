package com.oscoder.amazon.helper.spider.service.data.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskInfoPO {
    /**
     * 任务Id
     */
    private Integer id;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 站点名称
     */
    private String site;
    /**
     * 关键字
     */
    private String keyWord;
    /**
     * 状态
     * @see com.oscoder.amazon.helper.spider.api.enums.TaskStateEnum
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 完成时间
     */
    private Date completeTime;
}
