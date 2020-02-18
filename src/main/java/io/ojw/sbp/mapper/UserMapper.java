package io.ojw.sbp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import io.ojw.sbp.domain.User;

public interface UserMapper {
	@Select("select name from user where id = #{id}")
	public String getName(@Param("id") String id) throws Exception;
	
	public User getLoginInfo(@Param("id") String id) throws Exception;
	
	@Select("select * from user limit 10")
	public List<User> getUsers();
}
