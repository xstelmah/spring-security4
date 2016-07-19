package com.stelmah.example.springsecurity4.dao.api;

import com.stelmah.example.springsecurity4.dao.base.GenericDao;
import com.stelmah.example.springsecurity4.model.User;

public interface UserDao extends GenericDao<User, Integer> {

    public User getByLoginPassword(String login, String password);

    public User getByLogin(String login);
}
