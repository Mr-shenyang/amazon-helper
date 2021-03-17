package com.oscoder.amazon.helper.user.service.transform;

import com.oscoder.amazon.helper.user.api.dto.UserDTO;
import com.oscoder.amazon.helper.user.api.dto.UserInitDTO;
import com.oscoder.amazon.helper.user.api.dto.UserPwdDTO;
import com.oscoder.amazon.helper.user.service.data.po.UserPO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-17T02:29:19+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"
)
public class UserPoDtoTransformImpl implements UserPoDtoTransform {

    @Override
    public UserDTO po2Dto(UserPO user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setType( user.getType() );
        userDTO.setName( user.getName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setTel( user.getTel() );

        return userDTO;
    }

    @Override
    public UserPwdDTO po2PwdDto(UserPO user) {
        if ( user == null ) {
            return null;
        }

        UserPwdDTO userPwdDTO = new UserPwdDTO();

        userPwdDTO.setId( user.getId() );
        userPwdDTO.setName( user.getName() );
        userPwdDTO.setPassword( user.getPassword() );

        return userPwdDTO;
    }

    @Override
    public UserPO initDto2Po(UserInitDTO initDTO) {
        if ( initDTO == null ) {
            return null;
        }

        UserPO userPO = new UserPO();

        userPO.setType( initDTO.getType() );
        userPO.setName( initDTO.getName() );
        userPO.setEmail( initDTO.getEmail() );
        userPO.setTel( initDTO.getTel() );
        userPO.setPassword( initDTO.getPassword() );

        return userPO;
    }
}
