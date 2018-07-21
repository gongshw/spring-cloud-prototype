package io.focussource.base.auth.model;

import lombok.Data;

/**
 * Client Credential.
 *
 * @author gongshw1992@gmail.com
 */
@Data
public class ClientCredential {
    String username;
    String password;
}
