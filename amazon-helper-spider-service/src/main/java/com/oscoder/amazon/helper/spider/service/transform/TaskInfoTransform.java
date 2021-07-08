package com.oscoder.amazon.helper.spider.service.transform;


import com.oscoder.amazon.helper.spider.api.dto.TaskInfoDTO;
import com.oscoder.amazon.helper.spider.service.data.po.TaskInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
@Mapper
public interface TaskInfoTransform {
    TaskInfoTransform INSTANCE = Mappers.getMapper(TaskInfoTransform.class);

    @Mappings({})
    TaskInfoDTO po2Dto(TaskInfoPO taskInfoPO);

    @Mappings({})
    TaskInfoPO dtoToPo(TaskInfoDTO taskInfoDTO);
}
