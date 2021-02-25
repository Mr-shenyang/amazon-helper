package com.oscoder.amazon.helper.user.service.data.mapper;

import com.oscoder.amazon.helper.user.service.data.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 * @create 2020-04-22 11:28
 **/
@Mapper
public interface UserMapper {
	/**
	 * 插入
	 * @param user
	 * @return
	 */
	int insert(@Param("user") UserPO user);
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	int update(@Param("user") UserPO user);
	
	/**
	 * 根据id获取全量用户
	 * @param id
	 * @return
	 */
	UserPO getFullUserById(@Param("id") Integer id);
	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return
	 */
	UserPO getUserById(@Param("id") Integer id);
	
	
	/**
	 * 根据id获取用户信息
	 * @param loginType,
	 * @param loginName
	 * @return
	 */
	UserPO getUserPwd(@Param("loginType") Integer loginType,@Param("loginName") String loginName);
	
	/**
	 * 注销用户
	 * @param id
	 * @return
	 */
	int remove(@Param("id") Integer id);
}
