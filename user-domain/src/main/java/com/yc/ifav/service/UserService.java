package com.yc.ifav.service;

import com.yc.ifav.entity.User;

public interface UserService {

    public void register(User user);

    public  User login(User user);

    public User emreg(User user);
}
