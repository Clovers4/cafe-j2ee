package com.coffee.service;

import java.sql.SQLException;

import com.coffee.domain.*;
import com.coffee.exception.*;

/**
 * @InterfaceName: IUserService
 * @Description:Service层接口，提供用户登录，注册等服务。
 * 
 * @author: K
 */
public interface IUserService {

    /**
     * 提供注册服务
     * @param user
     * @throws UserExistException
     */
    void register(User user) throws Exception;

    /**
     * 提供登录服务
     * 
     * @param account
     * @param password
     * @return
     * @throws SQLException
     */
    User login(String account, String password) throws SQLException;
    
    /**
     * 提供更新数据(修改个人信息，密码等)服务
     * 
     * @param user
     * @return
     * @throws SQLException
     */
    void update(User user) throws SQLException;
}