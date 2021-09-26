package com.finch.god.common.modules.csrf.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "csrf")
@Slf4j
public class CsrfProperties {
    String types;
    String paramName;
    String tokenName;
    String tokenUrl;
    int tokenMaxAge;
    String referer; // Deprecated from 1.1.4，兼容
    String referers;
}
