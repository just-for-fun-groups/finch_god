package com.finch.god.common.modules.csrf.config;

import com.finch.god.common.config.Constants;
import com.finch.god.common.modules.csrf.enums.CheckTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@ConditionalOnProperty(name = "csrf.types")
@EnableConfigurationProperties(CsrfProperties.class)
@ComponentScan("com.finch.god.common.modules.csrf.config")
@Slf4j
public class CsrfConfiguration {

    @Resource
    private CsrfProperties csrfProperties;

    private List<String> refererHosts = new ArrayList<>();
    private List<CheckTypeEnum> checkTypes;

    @PostConstruct
    public void init() {
        try {
            initRefererHosts();
            initCheckTypes();
            validate();
        } catch (Exception e) {
            log.error("error when init.", e);
            illegal("invalid config");
        }
    }

    public String paramName() {
        return this.csrfProperties.getParamName();
    }

    public String tokenName() {
        return this.csrfProperties.getTokenName();
    }

    public int tokenMaxAge() {
        return this.csrfProperties.tokenMaxAge == 0 ? Constants.EXPIRE_DEFAULT_TOKEN : this.csrfProperties.tokenMaxAge;
    }

    public List<String> refererHosts() {
        return this.refererHosts;
    }

    public List<CheckTypeEnum> checkTypes() {
        return this.checkTypes;
    }

    private void initRefererHosts() {
        if (StringUtils.isNotBlank(this.csrfProperties.referers)) {
            Stream.of(StringUtils.split(this.csrfProperties.referers, Constants.SR_REFERERS))
                    .map(this::host).forEach(this.refererHosts::add);
        }
    }

    private void initCheckTypes() {
        if (StringUtils.isBlank(this.csrfProperties.types)) {
            return;
        }

        this.checkTypes = Stream.of(StringUtils.split(this.csrfProperties.types, Constants.SR_TYPES))
                .map(CheckTypeEnum::findByVal).collect(Collectors.toList());
    }

    private void validate() {
        if (CollectionUtils.isEmpty(this.checkTypes)) {
            illegal("no check_types config");
        }

        if (this.checkTypes.contains(CheckTypeEnum.REFERER)) {
            validateRefererCheckType();
        }

        if (this.checkTypes.contains(CheckTypeEnum.TOKEN)) {
            validateTokenCheckType();
        }
    }

    private void validateRefererCheckType() {
        if (CollectionUtils.isEmpty(this.refererHosts)) {
            illegal("no referer config");
        }
    }

    private void validateTokenCheckType() {
        if (StringUtils.isBlank(this.csrfProperties.paramName)) {
            illegal("no param_name config");
        }
        if (StringUtils.isBlank(this.csrfProperties.tokenName)) {
            illegal("no token_name config");
        }
        if (StringUtils.isBlank(this.csrfProperties.tokenUrl)) {
            illegal("no token_url config");
        }
    }

    private void illegal(String msg) {
        throw new IllegalArgumentException(msg);
    }

    private String host(String referer) {
        try {
            return new URI(referer).getHost();
        } catch (Exception e) {
            log.error("error when getting host, referer:{}", referer, e);
            throw new IllegalArgumentException(String.format("invalid referer: %s", referer));
        }
    }

}
