# Spring Boot Metrics with Prometheus

Simple example for exporting metrics to [Prometheus](https://prometheus.io/) from a Spring Boot app.
We use the Prometheus [java_client](https://github.com/prometheus/client_java). 

The configuration is contained with the [MonitoringConfig](https://github.com/thomasdarimont/spring-boot-prometheus-example/blob/master/src/main/java/de/tdlabs/training/MonitoringConfig.java) class:
```java
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.spring.boot.SpringBootMetricsCollector;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
class MonitoringConfig {

    @Bean
    SpringBootMetricsCollector springBootMetricsCollector(Collection<PublicMetrics> publicMetrics) {

        SpringBootMetricsCollector springBootMetricsCollector = new SpringBootMetricsCollector(publicMetrics);
        springBootMetricsCollector.register();

        return springBootMetricsCollector;
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        DefaultExports.initialize();
        return new ServletRegistrationBean(new MetricsServlet(), "/prometheus");
    }
}
```

## Build
```
mvn clean package
```

## Run
```
mvn spring-boot:run
```

## Collect metrics 
```
curl localhost:8080/prometheus
```

## Example output
```
# HELP jvm_memory_bytes_used Used bytes of a given JVM memory area.
# TYPE jvm_memory_bytes_used gauge
jvm_memory_bytes_used{area="heap",} 1.47710352E8
jvm_memory_bytes_used{area="nonheap",} 5.1258008E7
# HELP jvm_memory_bytes_committed Committed (bytes) of a given JVM memory area.
# TYPE jvm_memory_bytes_committed gauge
jvm_memory_bytes_committed{area="heap",} 3.28204288E8
jvm_memory_bytes_committed{area="nonheap",} 5.2166656E7
# HELP jvm_memory_bytes_max Maximum (bytes) of a given JVM memory area.
# TYPE jvm_memory_bytes_max gauge
jvm_memory_bytes_max{area="heap",} 3.71720192E9
jvm_memory_bytes_max{area="nonheap",} -1.0
# HELP jvm_memory_pool_bytes_used Used bytes of a given JVM memory pool.
# TYPE jvm_memory_pool_bytes_used gauge
jvm_memory_pool_bytes_used{pool="Code Cache",} 1.456608E7
jvm_memory_pool_bytes_used{pool="Metaspace",} 3.2659288E7
jvm_memory_pool_bytes_used{pool="Compressed Class Space",} 4041560.0
jvm_memory_pool_bytes_used{pool="PS Eden Space",} 1.2226136E8
jvm_memory_pool_bytes_used{pool="PS Survivor Space",} 1.0473704E7
jvm_memory_pool_bytes_used{pool="PS Old Gen",} 1.4975288E7
# HELP jvm_memory_pool_bytes_committed Limit (bytes) of a given JVM memory pool.
# TYPE jvm_memory_pool_bytes_committed gauge
jvm_memory_pool_bytes_committed{pool="Code Cache",} 1.4680064E7
jvm_memory_pool_bytes_committed{pool="Metaspace",} 3.3292288E7
jvm_memory_pool_bytes_committed{pool="Compressed Class Space",} 4194304.0
jvm_memory_pool_bytes_committed{pool="PS Eden Space",} 2.08142336E8
jvm_memory_pool_bytes_committed{pool="PS Survivor Space",} 1.048576E7
jvm_memory_pool_bytes_committed{pool="PS Old Gen",} 1.09576192E8
# HELP jvm_memory_pool_bytes_max Max (bytes) of a given JVM memory pool.
# TYPE jvm_memory_pool_bytes_max gauge
jvm_memory_pool_bytes_max{pool="Code Cache",} 2.5165824E8
jvm_memory_pool_bytes_max{pool="Metaspace",} -1.0
jvm_memory_pool_bytes_max{pool="Compressed Class Space",} 1.073741824E9
jvm_memory_pool_bytes_max{pool="PS Eden Space",} 1.372585984E9
jvm_memory_pool_bytes_max{pool="PS Survivor Space",} 1.048576E7
jvm_memory_pool_bytes_max{pool="PS Old Gen",} 2.788163584E9
# HELP httpsessions_max httpsessions_max
# TYPE httpsessions_max gauge
httpsessions_max -1.0
# HELP httpsessions_active httpsessions_active
# TYPE httpsessions_active gauge
httpsessions_active 0.0
# HELP mem mem
# TYPE mem gauge
mem 370583.0
# HELP mem_free mem_free
# TYPE mem_free gauge
mem_free 176263.0
# HELP processors processors
# TYPE processors gauge
processors 8.0
# HELP instance_uptime instance_uptime
# TYPE instance_uptime gauge
instance_uptime 9113.0
# HELP uptime uptime
# TYPE uptime gauge
uptime 11884.0
# HELP systemload_average systemload_average
# TYPE systemload_average gauge
systemload_average 1.47
# HELP heap_committed heap_committed
# TYPE heap_committed gauge
heap_committed 320512.0
# HELP heap_init heap_init
# TYPE heap_init gauge
heap_init 256000.0
# HELP heap_used heap_used
# TYPE heap_used gauge
heap_used 144248.0
# HELP heap heap
# TYPE heap gauge
heap 3630080.0
# HELP nonheap_committed nonheap_committed
# TYPE nonheap_committed gauge
nonheap_committed 50944.0
# HELP nonheap_init nonheap_init
# TYPE nonheap_init gauge
nonheap_init 2496.0
# HELP nonheap_used nonheap_used
# TYPE nonheap_used gauge
nonheap_used 50072.0
# HELP nonheap nonheap
# TYPE nonheap gauge
nonheap 0.0
# HELP threads_peak threads_peak
# TYPE threads_peak gauge
threads_peak 17.0
# HELP threads_daemon threads_daemon
# TYPE threads_daemon gauge
threads_daemon 14.0
# HELP threads_totalStarted threads_totalStarted
# TYPE threads_totalStarted gauge
threads_totalStarted 21.0
# HELP threads threads
# TYPE threads gauge
threads 16.0
# HELP classes classes
# TYPE classes gauge
classes 5964.0
# HELP classes_loaded classes_loaded
# TYPE classes_loaded gauge
classes_loaded 5964.0
# HELP classes_unloaded classes_unloaded
# TYPE classes_unloaded gauge
classes_unloaded 0.0
# HELP gc_ps_scavenge_count gc_ps_scavenge_count
# TYPE gc_ps_scavenge_count gauge
gc_ps_scavenge_count 4.0
# HELP gc_ps_scavenge_time gc_ps_scavenge_time
# TYPE gc_ps_scavenge_time gauge
gc_ps_scavenge_time 77.0
# HELP gc_ps_marksweep_count gc_ps_marksweep_count
# TYPE gc_ps_marksweep_count gauge
gc_ps_marksweep_count 1.0
# HELP gc_ps_marksweep_time gc_ps_marksweep_time
# TYPE gc_ps_marksweep_time gauge
gc_ps_marksweep_time 38.0
# HELP jvm_gc_collection_seconds Time spent in a given JVM garbage collector in seconds.
# TYPE jvm_gc_collection_seconds summary
jvm_gc_collection_seconds_sum{gc="PS Scavenge",} 0.077
jvm_gc_collection_seconds_count{gc="PS Scavenge",} 4.0
jvm_gc_collection_seconds_sum{gc="PS MarkSweep",} 0.038
jvm_gc_collection_seconds_count{gc="PS MarkSweep",} 1.0
# HELP jvm_threads_current Current thread count of a JVM
# TYPE jvm_threads_current gauge
jvm_threads_current 16.0
# HELP jvm_threads_daemon Daemon thread count of a JVM
# TYPE jvm_threads_daemon gauge
jvm_threads_daemon 14.0
# HELP jvm_peak_threads Peak thread count of a JVM
# TYPE jvm_peak_threads gauge
jvm_threads_peak 17.0
# HELP jvm_threads_started_total Started thread count of a JVM
# TYPE jvm_threads_started_total counter
jvm_threads_started_total 21.0
# HELP process_cpu_seconds_total Total user and system CPU time spent in seconds.
# TYPE process_cpu_seconds_total counter
process_cpu_seconds_total 17.01
# HELP process_start_time_seconds Start time of the process since unix epoch in seconds.
# TYPE process_start_time_seconds gauge
process_start_time_seconds 1.476213245759E9
# HELP process_open_fds Number of open file descriptors.
# TYPE process_open_fds gauge
process_open_fds 66.0
# HELP process_max_fds Maximum number of open file descriptors.
# TYPE process_max_fds gauge
process_max_fds 65536.0
# HELP process_virtual_memory_bytes Virtual memory size in bytes.
# TYPE process_virtual_memory_bytes gauge
process_virtual_memory_bytes 7.97188096E9
# HELP process_resident_memory_bytes Resident memory size in bytes.
# TYPE process_resident_memory_bytes gauge
process_resident_memory_bytes 3.35212544E8
```

# Prometheus & Grafana Docker container

## Prometheus configuration

You can find the configuration for Prometheus in the `/prometheus/prometheus.yml` file. 
There I set up a monitoring target for our Spring Boot app in the `scrape_configs` config section. 

## Start Prometheus & Grafana Containers
```
cd prometheus-grafana
docker-compose up
```

You should now see the Prometheus & Grafana Containers booting:
```
Creating network "prometheusgrafana_default" with the default driver
Creating prometheusgrafana_prometheus_1
Creating prometheusgrafana_grafana_1
Attaching to prometheusgrafana_prometheus_1, prometheusgrafana_grafana_1
prometheus_1 | time="2016-10-11T19:53:12Z" level=info msg="Starting prometheus (version=1.2.1, branch=master, revision=dd66f2e94b2b662804b9aa1b6a50587b990ba8b7)" source="main.go:75" 
prometheus_1 | time="2016-10-11T19:53:12Z" level=info msg="Build context (go=go1.7.1, user=root@fd9b0daff6bd, date=20161010-15:58:23)" source="main.go:76" 
prometheus_1 | time="2016-10-11T19:53:12Z" level=info msg="Loading configuration file /etc/prometheus/prometheus.yml" source="main.go:247" 
prometheus_1 | time="2016-10-11T19:53:12Z" level=info msg="Loading series map and head chunks..." source="storage.go:354" 
prometheus_1 | time="2016-10-11T19:53:12Z" level=info msg="0 series loaded." source="storage.go:359" 
prometheus_1 | time="2016-10-11T19:53:12Z" level=info msg="Listening on :9090" source="web.go:240" 
prometheus_1 | time="2016-10-11T19:53:12Z" level=info msg="Starting target manager..." source="targetmanager.go:76" 
prometheus_1 | time="2016-10-11T19:53:12Z" level=warning msg="No AlertManagers configured, not dispatching any alerts" source="notifier.go:176" 
grafana_1    | t=2016-10-11T19:53:12+0000 lvl=info msg="Starting Grafana" logger=main version=3.1.1 commit=a4d2708 compiled=2016-08-01T10:20:16+0000
grafana_1    | t=2016-10-11T19:53:12+0000 lvl=info msg="Config loaded from" logger=settings file=/usr/share/grafana/conf/defaults.ini
grafana_1    | t=2016-10-11T19:53:12+0000 lvl=info msg="Config loaded from" logger=settings file=/etc/grafana/grafana.ini
grafana_1    | t=2016-10-11T19:53:12+0000 lvl=info msg="Config overriden from command line" logger=settings arg="default.paths.data=/var/lib/grafana"
grafana_1    | t=2016-10-11T19:53:12+0000 lvl=info msg="Config overriden from command line" logger=settings arg="default.paths.logs=/var/log/grafana"
...
```

## Open Prometheus

Point your browser to http://prometheus.local:9090/targets and look for
the list of target hosts. In this demo we configured prometheus itself 
as well as the spring boot app as targets.

### Spring-Boot-App is marked as down?

Is the IP of your target host configured in `/prometheus/prometheus.yml` 
file visible for the promotheus container?

You might need to create a container network or configure your firewall 
to allow the prometheus docker container to access your docker host.
In case of the latter you could try

To allow traffic from interface docker0 to access the local dockerhost
create the following IPTables rule:
```
sudo iptables -A INPUT -i docker0 -j ACCEPT
```

To remove the IPTables rule run this: 
```
sudo iptables -D INPUT -i docker0 -j ACCEPT
```

## Shutting down the contains
```
docker-compose down
```