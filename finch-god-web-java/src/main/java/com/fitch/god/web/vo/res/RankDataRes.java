package com.fitch.god.web.vo.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankDataRes {

    /**
     * 选手id
     */
    private Integer playId;

    /**
     * 玩家姓名
     */
    private String playName;

    /**
     * 玩家签名
     */
    private String signature;

    /**
     * 玩家分数
     */
    private Integer score;

}
