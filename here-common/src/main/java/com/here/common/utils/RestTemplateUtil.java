package com.here.common.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestTemplateUtil {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    public String PostRequest(JSONObject jsonObject, String url) {
        String result = "";
        try {
            HttpHeaders headers = new HttpHeaders();
            //所有的请求需要用JSON格式发送
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<Object> formEntity = new HttpEntity<>(jsonObject, headers);
            result = restTemplate.postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            logger.error("小程序post请求异常{}", url);
            e.printStackTrace();
        }
        return result;
    }

    public String getRequest(HashMap<String, Object> paramMap, String url) {
        String result = "";
        try {

            //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
            result= HttpUtil.get(url, paramMap);
        } catch (Exception e) {
            logger.error("小程序post请求异常{}", url);
            e.printStackTrace();
        }
        return result;
    }
}