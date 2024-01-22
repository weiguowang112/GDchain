package com.example.rtdataassetcoord;

import com.example.rtdataassetcoord.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({StorageProperties.class})
@EnableFeignClients(basePackages={"com.example.rtdataassetcoord.FeignClient"})
@EnableScheduling
public class RtDataAssetCoordApplication {

    public static void main(String[] args) {
        SpringApplication.run(RtDataAssetCoordApplication.class, args);
    }

}
