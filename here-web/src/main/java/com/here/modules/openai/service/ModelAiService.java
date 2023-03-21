package com.here.modules.openai.service;

import com.here.common.api.ResultObject;
import com.here.modules.openai.vo.ChatOpenAiVO;
import com.here.modules.openai.vo.OpenaiVO;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.model.Model;

import java.util.List;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName OpenAiService.java
 * @Description TODO
 * @createTime 2023年03月19日 11:08:00
 */
public interface ModelAiService {

    List<Model> listModels();

    ResultObject<List<CompletionChoice>> completionChoice(OpenaiVO openaiVO);

    ResultObject<List<ChatCompletionChoice>> chatCompletionChoice(ChatOpenAiVO chatOpenAiVO);

}
