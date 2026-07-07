package com.ruizhou.user_center.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class UserRegisterRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 7751132251854747213L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;

}
