package com.here.modules.openai.controller;

import com.here.common.aop.Log;
import com.here.common.api.ResultObject;
import com.here.modules.oauth.vo.WriteInviCodeVO;
import com.here.modules.openai.service.ModelAiService;
import com.here.modules.openai.vo.ChatOpenAiVO;
import com.here.modules.openai.vo.OpenaiVO;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.model.Model;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName OpenAiControoler.java
 * @Description TODO
 * @createTime 2023年03月19日 10:39:00
 */
@RestController
@RequestMapping("/openai")
@Api(tags = "智能导购模块-接入opnai")
public class OpenAiController {

    @Autowired
    private ModelAiService modelAiService;

    /**
     *
     * Given a prompt, the model will return one or more predicted completions,
     * and can also return the probabilities of alternative tokens at each position.
     * @return
     */
    @GetMapping("models")
    @ResponseBody
    @Log
    @ApiOperation("获取模型列表")
    public List<Model> models(){
        return  modelAiService.listModels();
    }


    /**
     *
     * Given a prompt, the model will return one or more predicted completions,
     * and can also return the probabilities of alternative tokens at each position.
     * @return
     */
    @PostMapping("completion")
    @ResponseBody
    @Log
    @ApiOperation("根据模版生成文本")
    public ResultObject<List<CompletionChoice>> completion(@RequestBody @Validated OpenaiVO openaiVO){
        return modelAiService.completionChoice(openaiVO);
    }

    /**
     * chatGPT
     * @return
     */
    @PostMapping("chat")
    public ResultObject writeCode(@RequestBody @Validated ChatOpenAiVO chatOpenAiVO){
        return modelAiService.chatCompletionChoice(chatOpenAiVO);
    }

}
