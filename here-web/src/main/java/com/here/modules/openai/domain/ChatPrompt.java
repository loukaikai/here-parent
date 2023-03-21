package com.here.modules.openai.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName ChatPrompt.java
 * @Description 主要针对chatGPT来配置聊天模板
 * @createTime 2023年03月21日 09:25:00
 */
public class ChatPrompt {
    String id;

    /**
     * 模板名称
     * **/
    String promptName;

    /**
     * message
     * [
     *         {"role": "system", "content": "You are a helpful assistant."},
     *         {"role": "user", "content": "Who won the world series in 2020?"},
     *         {"role": "assistant", "content": "The Los Angeles Dodgers won the World Series in 2020."},
     *         {"role": "user", "content": "Where was it played?"}
     *     ]
     *  可以保存为json字符串
     * **/
    String message;


    /**********************以下字段皆为预留，
     * model 默认gpt-3.5-turbo
     * temperature
     *
     * **********************/
    String modelId;
}
