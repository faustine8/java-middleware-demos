package com.zmn.prometheus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 类描述：
 *
 * @author mujunlin
 * @since 2022/07/27 14:42
 */
@SpringBootApplication
// 该注解用于引入定时功能，方便下面要介绍的业务模拟请求代码
// SimulationRequest.java进行@Scheduled操作
@EnableScheduling
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}