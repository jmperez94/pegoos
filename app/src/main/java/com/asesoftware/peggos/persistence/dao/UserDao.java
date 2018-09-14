package com.asesoftware.peggos.persistence.dao;

import com.asesoftware.peggos.persistence.dto.User;

import java.util.ArrayList;

public interface UserDao {

    public ArrayList<User> getAllUsers();
    public int updateUser(User user);
    public int deleteUser(User user);
    public long addUser(User user);

}
