package io.focussource.base.auth.model;

import java.util.Set;

import lombok.Data;

/**
 * User Credential.
 *
 * @author gongshw1992@gmail.com
 */
@Data
public class UserCredential {
    String username;
    String password;
    Set<String> roles;
}
