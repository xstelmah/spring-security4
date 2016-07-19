package com.stelmah.example.springsecurity4.service.api;

import com.stelmah.example.springsecurity4.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<User> getAll();

    public void save(User user);

    public void update(User user);

    public void delete(User user);

    public User getByLoginPassword(String login, String password);

    public User getByLogin(String login);
}
