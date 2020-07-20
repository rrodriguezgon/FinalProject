package com.ironhack.Server;

import com.netflix.appinfo.AmazonInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

@EnableEurekaServer
@SpringBootApplication
public class ServerApplication {

	@Value("${server.port:8761}")
	private int port;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils) {
		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");

		// Don't use spring cloud's hostname here.
		// See comment below by Michal
		config.setHostname(
				info.get(AmazonInfo.MetaDataKey.publicHostname));

		config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
		config.setNonSecurePort(port);
		config.setDataCenterInfo(info);
		return config;
	}
}
