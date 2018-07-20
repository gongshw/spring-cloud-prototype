package com.gongshw.scp.base.auth.model;

import lombok.Data;

/**
 * Client Credential.
 *
 * @author gongshiwei@baidu.com
 */
@Data
public class ClientCredential {
    String username;
    String password;
}
