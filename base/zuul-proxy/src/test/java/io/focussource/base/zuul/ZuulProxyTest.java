package io.focussource.base.zuul;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import io.focussource.base.server.test.CommonServiceTest;
import lombok.extern.slf4j.Slf4j;

/**
 * Test Bootstrap.
 *
 * @author gongshw1992@gmail.com
 */
@Slf4j
@CommonServiceTest
@RunWith(SpringRunner.class)
public class ZuulProxyTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testBootstrap() {
        log.info("application {} started!", applicationContext.getApplicationName());
    }
}
