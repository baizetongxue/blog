package com.sc.service.impl;

import com.sc.dao.UserRepository;
import com.sc.pojo.User;
import com.sc.service.UserService;
import com.sc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BaiZe schoolmate on 2020/5/10 15:56.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user =
                userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
