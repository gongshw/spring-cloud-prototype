package com.gongshw.scp.base.auth.model;

import java.util.Set;

import lombok.Data;

/**
 * User Credential.
 *
 * @author gongshiwei@baidu.com
 */
@Data
public class UserCredential {
    String username;
    String password;
    Set<String> roles;
}
