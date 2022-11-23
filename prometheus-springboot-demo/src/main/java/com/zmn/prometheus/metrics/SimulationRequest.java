package com.zmn.prometheus.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 类描述：
 *
 * @author mujunlin
 * @since 2022/07/27 14:58
 */
@Component
public class SimulationRequest {

    private Integer count1 = 0;
    @Autowired
    private DemoMetrics demoMetrics;

    @Async("One")
    @Scheduled(fixedDelay = 1000)
    public void increment1() {
        count1++;
        demoMetrics.counter.increment();
        demoMetrics.map.put("x", Double.valueOf(count1));
        // 将 counte1 的值放入 Gauge 中，反映应用的当前指标，比如主机当前空闲的内存大小 (node_memory_MemFree)
        System.out.println("increment 1 count:" + count1);
    }

}
