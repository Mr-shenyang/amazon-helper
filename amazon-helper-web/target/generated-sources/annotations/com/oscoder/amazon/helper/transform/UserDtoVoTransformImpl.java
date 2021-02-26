package com.oscoder.amazon.helper.transform;

import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.vo.UserVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-26T22:37:45+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"
)
public class UserDtoVoTransformImpl implements UserDtoVoTransform {

    @Override
    public UserVO dto2DVo(UserDTO user) {
        if ( user == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setId( user.getId() );
        userVO.setType( user.getType() );
        userVO.setName( user.getName() );
        userVO.setCode( user.getCode() );
        userVO.setEmail( user.getEmail() );
        userVO.setTel( user.getTel() );

        return userVO;
    }
}
