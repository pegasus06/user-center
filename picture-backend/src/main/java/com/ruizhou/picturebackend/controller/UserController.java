package com.ruizhou.picturebackend.controller;

import com.ruizhou.picturebackend.common.BaseResponse;
import com.ruizhou.picturebackend.common.ResultUtils;
import com.ruizhou.picturebackend.exception.ErrorCode;
import com.ruizhou.picturebackend.exception.ThrowUtils;
import com.ruizhou.picturebackend.model.dto.UserRegisterRequest;
import com.ruizhou.picturebackend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }
}
