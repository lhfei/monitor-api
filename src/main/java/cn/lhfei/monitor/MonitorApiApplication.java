package cn.lhfei.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import cn.lhfei.monitor.config.CanalConfig;

@SpringBootApplication
public class MonitorApiApplication {
	@Bean(name = "canalConfig")
    @ConfigurationProperties(prefix = "canal")
    public CanalConfig getConfiguration() {
        return new CanalConfig();
    }
	public static void main(String[] args) {
		SpringApplication.run(MonitorApiApplication.class, args);
	}
}
