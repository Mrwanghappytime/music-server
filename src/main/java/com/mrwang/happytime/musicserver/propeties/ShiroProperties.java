package com.mrwang.happytime.musicserver.propeties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "music.server.shiro")
public class ShiroProperties {
    private String hashAlgorithmName;
    private Integer hashIterations;
    private String authenticationStrategyClassName;
    private LinkedHashMap<String,String> filterMap;

}
