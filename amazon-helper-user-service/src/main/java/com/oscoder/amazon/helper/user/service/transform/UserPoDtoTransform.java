package com.oscoder.amazon.helper.user.service.transform;

import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import com.oscoder.amazon.helper.user.service.data.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author
 * @create 2021-02-21 12:20 AM
 **/
@Mapper
public interface UserPoDtoTransform {
	UserPoDtoTransform INSTANCE = Mappers.getMapper(UserPoDtoTransform.class);
	
	@Mappings({})
	UserDTO po2Dto(UserPO user);
	
	
	@Mappings({})
	UserPwdDTO po2PwdDto(UserPO user);
}
