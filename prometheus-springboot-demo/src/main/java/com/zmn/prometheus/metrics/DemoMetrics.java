package com.zmn.prometheus.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：通过io.micrometer.core.instrument.binder.MeterBinder接口实现 bind 方法，并将要采集的指标注册到 MeterRegistry。
 *
 * @author mujunlin
 * @since 2022/07/27 14:42
 */
@Component
public class DemoMetrics implements MeterBinder {

    public Counter counter;
    public Map<String, Double> map;

    // Map对象，用于对该组件进行扩展
    // demoMetrics.map.put("x", counter1)
    // demoMetrics.map.put("y", counter2)
    // demoMetrics.map.put("z", counter3)
    // DemoMetrics 根据 Map 的 Key 的名称 x\y\z 取出业务端埋点值

    DemoMetrics() {
        map = new HashMap<String, Double>();
    }


    /**
     * Counter 是一个只增不减的计数器，可以用来记录请求或者错误的总量，比如 http_requests_total 等。
     * Gauge 属于可增可减的仪表盘，更多用来反映应用当前的状态，比如主机当前空闲的内存 node_memory_MemFree 或者其他属性。
     * 以上指标在上述代码中都进行了定义以及设置了标签。标签组合可以更好地标识应用及应用中的监控项，特别是在集群当中。本节后文中的效果展示部分会展示这些指标的数据。
     */
    public void bindTo(MeterRegistry meterRegistry) {
        // 定义并注册一个名称为 prometheus.demo.counter 的计数器，标签是 name:counter1
        this.counter = Counter.builder("prometheus.demo.counter").tags("name", "counter1").description("demo counter").register(meterRegistry);
        // 从业务端传递的Map中取出与Key对应的值放入注册的Gauge仪表盘中，标签是name:gauge1
        // Gauge.builder("prometheus.demo.gauge",map,x->x.get("x")).tags("name","gauge1").description("This is Gauge").register(meterRegistry);    }
    }
}
