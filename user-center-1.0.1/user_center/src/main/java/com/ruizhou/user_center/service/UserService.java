package com.ruizhou.user_center.service;

import com.ruizhou.user_center.common.BaseResponse;
import com.ruizhou.user_center.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author RZ
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2026-07-07 11:46:45
 */
public interface UserService extends IService<User> {

    long userRegister(String userAccount, String userPassword, String checkPassword);

    User doLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest);
}
