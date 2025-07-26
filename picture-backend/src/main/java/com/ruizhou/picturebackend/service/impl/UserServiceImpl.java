package com.ruizhou.picturebackend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruizhou.picturebackend.exception.BusinessException;
import com.ruizhou.picturebackend.exception.ErrorCode;
import com.ruizhou.picturebackend.model.entity.User;
import com.ruizhou.picturebackend.service.UserService;
import com.ruizhou.picturebackend.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @author RZ
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-02-12 13:43:24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        if (StrUtil.hasBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        //校验是否重复
        QueryWrapper<User> account = new QueryWrapper<User>().eq("user_account", userAccount);
        Long l = this.baseMapper.selectCount(account);
        if (l > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号重复");
        }
        //加密密码
        getEncryptPassword(userPassword);
        return 0;
    }

    @Override
    public String getEncryptPassword(String userPassword) {
        final String SALT="ruizhou";
        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    }
}




