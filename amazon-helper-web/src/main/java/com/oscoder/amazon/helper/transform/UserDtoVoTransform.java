package com.oscoder.amazon.helper.transform;

import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author
 * @create 2021-02-20 11:24 PM
 **/
@Mapper
public interface UserDtoVoTransform {
	UserDtoVoTransform INSTANCE = Mappers.getMapper(UserDtoVoTransform.class);
	
	@Mappings({})
	UserVO dto2DVo(UserDTO user);
}
