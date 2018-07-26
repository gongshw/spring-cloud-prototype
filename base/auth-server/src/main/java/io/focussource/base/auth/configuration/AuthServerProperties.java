package io.focussource.base.auth.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import io.focussource.base.auth.model.ClientCredential;
import io.focussource.base.auth.model.UserCredential;
import lombok.Data;

/**
 * Auth Server Properties.
 *
 * @author gongshw1992@gmail.com
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "auth-server")
public class AuthServerProperties {
    private String privateKey = ""
            + "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM1xx03FwboaHOadTSdMGx1nj0tq"
            + "rXj2NxYkgldRKHXe253fWHzWCyVPRwNfp7ZesJozCxcJVc8N6x7Kce1MYackAXGIUutjWPFVOxjS"
            + "XbUfITaA5a6ocfmk6qovLRO0rMtZGqpGVtXUfyzrX/Uy7YUtw4sTrwreJlPWmpbn6Ps5AgMBAAEC"
            + "gYEAyela8cq8HUVwxg4vN8IKnBmdsYdEuq9xcGetWle/JilDOqO5eed7yM1jg6NNV2U0uDgWXCum"
            + "k/qf7AEXexsolLJtQXaOMze1f9wvJr42ByYKpx+lgb6dYw1pi1IkBJQMNWc10obttnjaQIG6btCA"
            + "78la8mLBnp8bPmvlN/YSb8kCQQD1kBDF7d285vmHE/2wv5hKALCvj5hC8jXCz0Y8/DjN6w7OzRQc"
            + "U9De8LFjU6HrE8QSiYlzrM6CDARtnRbY6ZvnAkEA1i0w9FD3hzv4vJC80WydxCoc1UTAwrahikBd"
            + "2bHFNUZOL37zjgi4o0vxgPY3frNBe5cfc35vQ0U4n6ArsqbL3wJAGDKcFIT+BrFUAWWM6C/ZF+G9"
            + "h29E98PaXFoEd9n61v/Es8gtpST1PiLFuB3zpLCL76+x5b4vk00P2qVSLQnIHQJARgLJwOOCtost"
            + "GP67Kj42KDyX5kMuO7gVgCrgQtwwmeSchDwcg25oZMDbdguGQn14R100I73NwxCqnMtv3VtWwwJB"
            + "AJ4OFSwob6PBzHsluqNzEWxvZbYPyNC75O3PSdVqHRu8Z0dtGKUSDpQp66vP6wEmp+Xk4ZxMhjz8"
            + "o95Uo7mTY1c=";
    private List<ClientCredential> clients;
    private List<UserCredential> users;
}
