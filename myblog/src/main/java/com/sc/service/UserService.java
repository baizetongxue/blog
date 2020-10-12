package com.sc.service;

import com.sc.pojo.User;

/**
 * Created by BaiZe schoolmate on 2020/5/10 15:55.
 */
public interface UserService {

    User checkUser(String username, String password);
}
