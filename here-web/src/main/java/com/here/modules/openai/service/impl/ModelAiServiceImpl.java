package com.here.modules.openai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.here.common.api.ResultObject;
import com.here.modules.openai.service.ModelAiService;
import com.here.modules.openai.vo.ChatMessageVO;
import com.here.modules.openai.vo.ChatOpenAiVO;
import com.here.modules.openai.vo.OpenaiVO;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.model.Model;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.theokanning.openai.service.OpenAiService;
import retrofit2.Retrofit;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName OpenAiServiceImpl.java
 * @Description TODO
 * @createTime 2023年03月19日 11:08:00
 */
@Service
public class ModelAiServiceImpl implements ModelAiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelAiServiceImpl.class);


    @Value("${openai.apikey}")
    private String apikey;

    @Value("${openai.apikey}")
    private String isproxy;

    @Value("${proxy.host}")
    private String host;

    @Value("${proxy.port}")
    private String port;

    /**
     * @return
     */
    @Override
    public List<Model> listModels() {
        LOGGER.info("获取模型列表");
        ResultObject<List<Model>> resultObject = new ResultObject<>();
        OpenAiService  openAiService = createOpenAiApi();
        List<Model> list = openAiService.listModels();
        LOGGER.info("获取模型列表完成");
        return list;
    }

    /**
     * @param openaiVO
     * @return
     */
    @Override
    public ResultObject<List<CompletionChoice>> completionChoice(OpenaiVO openaiVO) {
        LOGGER.info("获取预测文本");
        ResultObject<List<CompletionChoice>> resultObject = new ResultObject<>();
        OpenAiService  openAiService = createOpenAiApi();
        CompletionRequest completionRequest = CompletionRequest
                .builder()
                .model("text-davinci-003")
                .prompt(openaiVO.getPrompt())
                .n(5)
                .maxTokens(50)
                .user(openaiVO.getUser())
                .logitBias(new HashMap<>())
                .logprobs(5)
                .build();
        CompletionResult completionResult = openAiService.createCompletion(completionRequest);
        LOGGER.info("返回结果：[{}]", JSONObject.toJSONString(completionResult));
        List<CompletionChoice> choices = completionResult.getChoices();

        resultObject.setData(choices);
        LOGGER.info("获取预测文本完成");
        return resultObject;
    }

    /**
     * @param chatOpenAiVO
     * @return
     */
    @Override
    public ResultObject<List<ChatCompletionChoice>> chatCompletionChoice(ChatOpenAiVO chatOpenAiVO) {
        LOGGER.info("chatGPT对话功能接口");
        LOGGER.info("参数模版：[{}]", JSONObject.toJSONString(chatOpenAiVO));
        ResultObject<List<ChatCompletionChoice>> resultObject = new ResultObject<>();
        List<ChatMessage> messages = new ArrayList<>();
        List<ChatMessageVO> chatMessageVO = chatOpenAiVO.getMessages();
        chatMessageVO.stream().forEach(vo -> {
            ChatMessage chatMessage = new ChatMessage();
            BeanUtil.copyProperties(vo, chatMessage, true);
            messages.add(chatMessage);
        });

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(5)
                .maxTokens(50)
                .logitBias(new HashMap<>())
                .build();

        OpenAiService  openAiService = createOpenAiApi();
        List<ChatCompletionChoice> choices = openAiService.createChatCompletion(chatCompletionRequest).getChoices();
        resultObject.setData(choices);
        return resultObject;
    }


    private OpenAiService createOpenAiApi(){
        LOGGER.info("创建OpenAiService");
        ObjectMapper mapper = OpenAiService.defaultObjectMapper();
        OkHttpClient client = null;
        if ("0".equals(isproxy)){
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, Integer.valueOf(port)));
            client = OpenAiService.defaultClient(apikey, Duration.ofSeconds(1000))
                    .newBuilder()
                    .proxy(proxy)
                    .build();
        }else{
            client = OpenAiService.defaultClient(apikey, Duration.ofSeconds(1000));
        }
        Retrofit retrofit = OpenAiService.defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        OpenAiService openAiService = new  OpenAiService(api);
        LOGGER.info("创建OpenAiService完成");
        return openAiService;
    }
}
