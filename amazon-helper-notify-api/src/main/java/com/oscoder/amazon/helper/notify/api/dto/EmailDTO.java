package com.oscoder.amazon.helper.notify.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@Getter
@Setter
public class EmailDTO {
    private List<String> receivers;

    private String subject;

    private String content;
}
