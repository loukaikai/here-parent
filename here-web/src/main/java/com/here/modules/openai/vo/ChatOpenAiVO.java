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

    String model;

    String prompt;

    String user;

    List<ChatMessageVO> messages;

}
