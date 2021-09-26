package com.finch.god.common.modules.csrf.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public enum CheckTypeEnum {

    REFERER("referer"),
    TOKEN("token");

    private String val;

    CheckTypeEnum(String val) {
        this.val = val;
    }

    public static CheckTypeEnum findByVal(String val) {
        return Stream.of(values()).filter(c -> StringUtils.equals(val, c.val))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("invalid check_type"));
    }

    public String getVal() {
        return val;
    }

}
