package com.oscoder.amazon.helper.notify.api.service;

import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.notify.api.dto.EmailDTO;

public interface EmailService {
    ResponseDTO<String> notify(Integer msgType,EmailDTO emailDTO);
}
