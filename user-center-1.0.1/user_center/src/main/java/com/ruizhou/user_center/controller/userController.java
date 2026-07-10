package com.ruizhou.user_center.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruizhou.user_center.common.BaseResponse;
import com.ruizhou.user_center.common.ResultUtils;
import com.ruizhou.user_center.constant.UserConstant;
import com.ruizhou.user_center.exception.ErrorCode;
import com.ruizhou.user_center.exception.ThrowUtils;
import com.ruizhou.user_center.mapper.UserMapper;
import com.ruizhou.user_center.model.User;
import com.ruizhou.user_center.model.request.UserLoginRequest;
import com.ruizhou.user_center.model.request.UserRegisterRequest;
import com.ruizhou.user_center.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Resource
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userPassword = userRegisterRequest.getUserPassword();
        String userAccount = userRegisterRequest.getUserAccount();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, checkPassword, userPassword)) {
            return null;
        }
        long userId = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(userId);
    }

    @PostMapping("/doLogin")
    public BaseResponse<User> doLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest) {
        if (userLoginRequest == null) {
            return null;
        }
        String userPassword = userLoginRequest.getUserPassword();
        String userAccount = userLoginRequest.getUserAccount();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        User user = userService.doLogin(userAccount, userPassword, httpServletRequest);
        return ResultUtils.success(user);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(@RequestParam(required = false)String userName, HttpServletRequest httpServletRequest) {
        User attribute = (User) httpServletRequest.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        ThrowUtils.throwIf(attribute == null || attribute.getUserRole() != 1, ErrorCode.NOT_LOGIN_ERROR);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("username", userName);
        }
        List<User> users = userService.list(queryWrapper);
        return ResultUtils.success(users);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody Long userId, HttpServletRequest httpServletRequest) {
        User attribute = (User) httpServletRequest.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        ThrowUtils.throwIf(attribute == null || attribute.getUserRole() != 1 || userId <= 0, ErrorCode.NOT_LOGIN_ERROR);
        boolean b = userService.removeById(userId);
        return ResultUtils.success(b);
    }

    @GetMapping("/currentUser")
    public BaseResponse<User> getCurrentUser(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_LOGIN_ERROR);
        User currentUser = userService.getById(user.getId());
        return ResultUtils.success(currentUser);
    }
    @PostMapping("/logout")
    public BaseResponse<Boolean> logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return ResultUtils.success(true);
    }

}
