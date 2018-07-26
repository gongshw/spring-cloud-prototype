package io.focussource.base.server.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Base Auth Properties.
 *
 * @author gongshw1992@gmail.com
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "base.auth")
public class BaseAuthProperties {

    private boolean enabled = true;

    private String[] excludes = new String[0];

    private String signingKey = "327ed17cb8e54ce698d4ad295f495d30";

    private String publicKey = ""
            + "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNccdNxcG6GhzmnU0nTBsdZ49Laq149jcWJIJX"
            + "USh13tud31h81gslT0cDX6e2XrCaMwsXCVXPDeseynHtTGGnJAFxiFLrY1jxVTsY0l21HyE2gOWu"
            + "qHH5pOqqLy0TtKzLWRqqRlbV1H8s61/1Mu2FLcOLE68K3iZT1pqW5+j7OQIDAQAB";
}
