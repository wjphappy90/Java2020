package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.User;

public interface UserService {

	List<User> findListByName(String name);

}
