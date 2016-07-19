package com.stelmah.example.springsecurity4.dao.impl;

import com.stelmah.example.springsecurity4.dao.base.AbstractDao;
import com.stelmah.example.springsecurity4.model.User;
import com.stelmah.example.springsecurity4.dao.api.UserDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User, Integer> implements UserDao {

    @Override
    public User getByLoginPassword(String login, String password) {
        return (User) this.getSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login)).add(Restrictions.eq("password",password)).uniqueResult();
    }

    @Override
    public User getByLogin(String login) {
        return (User) this.getSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login)).uniqueResult();
    }
}
