package com.here.modules.openai.vo;

import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.Data;

import java.util.List;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName ChatOpenAiVO.java
 * @Description TODO
 * @createTime 2023年03月20日 19:54:00
 */
@Data
public class ChatOpenAiVO {

    /** 使用的模型**/
    String model;

    /**
     * 用户使用的模版id
     * **/
    String prompt;

    /**
     * 用户id：我需要根据这个来查询用户的历史记录，历史记录缓存在redis
     * **/
    String user;

    /**
     * 用户提问问题
     * **/
    String messages;

}
