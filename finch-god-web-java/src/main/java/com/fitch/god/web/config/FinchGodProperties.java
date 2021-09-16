package com.fitch.god.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class FinchGodProperties {

    private String domain;
    private String staticUri;
    private String staticVersion;

    private String appCode;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStaticUri() {
        return staticUri;
    }

    public void setStaticUri(String staticUri) {
        this.staticUri = staticUri;
    }

    public String getStaticVersion() {
        return staticVersion;
    }

    public void setStaticVersion(String staticVersion) {
        this.staticVersion = staticVersion;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
