package com.ruizhou.user_center.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -2005238598552966667L;
    private String userAccount;
    private String userPassword;

}
