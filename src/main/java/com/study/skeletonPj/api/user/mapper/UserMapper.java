package com.study.skeletonPj.api.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.study.skeletonPj.api.user.domain.User;

@Repository
@Mapper
public interface UserMapper {
	List<User.Veo> getItemList(User.Veo user);
	
	User.Veo getItem(String userId);
	
	int createItem(User.Veo user);
	
	int deleteItem(String userId);

	int putItem(User.Veo user);
	
	int patchItem(User.Veo user);
	
}
