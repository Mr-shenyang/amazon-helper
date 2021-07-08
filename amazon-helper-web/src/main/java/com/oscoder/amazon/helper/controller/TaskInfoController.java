package com.oscoder.amazon.helper.controller;

import com.oscoder.amazon.helper.common.dto.PageDTO;
import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.common.request.PageRequest;
import com.oscoder.amazon.helper.jwt.JWTUtil;
import com.oscoder.amazon.helper.spider.api.dto.TaskInfoDTO;
import com.oscoder.amazon.helper.spider.api.service.TaskInfoService;
import com.oscoder.amazon.helper.transform.UserDtoVoTransform;
import com.oscoder.amazon.helper.user.api.service.UserService;
import com.oscoder.amazon.helper.vo.ResponseVo;
import com.oscoder.amazon.helper.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskInfoController {
    @Autowired
    private TaskInfoService taskInfoService;

    @GetMapping("/pageTask")
    ResponseVo<PageDTO<TaskInfoDTO>> pageTaskInfo(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        //TODO
        return ResponseVo.success(taskInfoService.queryTaskPage(6, pageRequest));
    }

    @PostMapping("/create")
    ResponseVo<String> create(@RequestBody TaskInfoDTO taskInfoDTO) {
        //TODO
        taskInfoDTO.setUserId(6);
        ResponseDTO<Integer> taskInfoResp = taskInfoService.createTaskInfo(taskInfoDTO);
        if (taskInfoResp.isSuccess()) {
            return ResponseVo.success("创建成功");
        } else {
            return ResponseVo.fail(taskInfoResp.getFailMsg());
        }
    }
}
