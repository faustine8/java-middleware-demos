# 普通 Java 应用接入 Prometheus

## IDEA 添加 lib 方式

报错：java.lang.NoClassDefFoundError: io/prometheus/client/exemplars/ExemplarSampler

## 启动参数添加 -D

```shell
-Djava.ext.dirs=/opt/zmn/software/micrometer-lib/
```

报错：java.lang.NoClassDefFoundError: io/prometheus/client/CollectorRegistry

## 打包 jar 包命令行参数添加 

```shell
java -Dloader.path=/opt/zmn/software/micrometer-lib/ -Dzmn_logging_root_dir=/Users/faustine/Code/logs -jar zmn-owl-admin.jar
```

java.lang.NoClassDefFoundError: io/prometheus/client/exemplars/HistogramExemplarSampler

## 尝试

尝试删除了可能不需要的 jar ( micrometer-jvm-extras-0.2.2.jar) 效果仍然同上。

## 打包 jar 包更改启动方式

```shell
java -jar 
```

## 正常可用方案

```xml
<dependencies>
    <!--桥接Prometheus-->
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
        <version>1.9.0</version>
    </dependency>
    <!--micrometer核心包，按需引入，使用Meter注解或手动埋点时需要-->
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-core</artifactId>
        <version>1.9.0</version>
    </dependency>
    <!--micrometer获取JVM相关信息，并展示在Grafana上-->
    <dependency>
        <groupId>io.github.mweirauch</groupId>
        <artifactId>micrometer-jvm-extras</artifactId>
        <version>0.2.2</version>
    </dependency>
</dependencies>
```

数据正常

数据暴露：<http://local-owl.xiujiadian.com:19513/owl/actuator/>

Prometheus 数据路径：<http://local-owl.xiujiadian.com:19513/owl/actuator/prometheus>

