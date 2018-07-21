package io.focussource.service.demo1.controller;

import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.focussource.base.server.security.SecurityHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * Apis Protected by Spring Security.
 *
 * @author gongshw1992@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityController {
    @GetMapping("/name")
    public String name() {
        return SecurityHelper.getUsername();
    }

    @GetMapping("/roles")
    public Set<String> roles() {
        return SecurityHelper.getRoles();
    }

    @GetMapping("/checkAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String checkAdmin() {
        return "Yes! I am an admin!";
    }
}
