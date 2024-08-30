package com.sparta.spartaeats.common.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sparta.spartaeats.common.type.ApiResultError;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;


public class ApiResult extends HashMap<String, Object>{

    private static final long serialVersionUID = 5040586336975987989L;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static final String AR_KEY_RESULT_CODE = "resultCode";
    public static final String AR_KEY_RESULT_MSG = "resultMessage";
    public static final String AR_KEY_RESULT_DATA = "resultData";
    public static final String AR_KEY_PAGEINFO = "pageInfo";
    public static final String AR_KEY_LIST = "list";
    public static final String AR_KEY_SC = "sc";

    public ApiResult(ApiResultError err) {
        this.set(err);
    }

    public ApiResult(Map<String, Object> map) {
        super(map);
    }

    public ApiResult() {
        this.set(ApiResultError.ERROR_DEFAULT);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public ApiResult set(ApiResultError err) {
        return set(err, null);
    }

    public ApiResult set(ApiResultError err, String appendMessage) {
        put(AR_KEY_RESULT_CODE, err.getCode());

        String msg = err.getMessage();
        if(StringUtils.hasText(appendMessage)) {
            msg = String.format("%s\n%s", msg, appendMessage);
        }
        put(AR_KEY_RESULT_MSG, msg);
        return this;
    }

    public ApiResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ApiResult setResultData(Object data) {
        Map<String, String> map = objectMapper.convertValue(data, new TypeReference<Map<String, String>>() {});

        super.put(AR_KEY_RESULT_DATA, map);
        return this;
    }

    public Object getResultData() {
        return (Object)super.get(AR_KEY_RESULT_DATA);
    }

    public String getResultMessage() {
        return (String)super.get(AR_KEY_RESULT_MSG);
    }

    public ApiResult setResultMessage(String message) {
        super.put(AR_KEY_RESULT_MSG, message);
        return this;
    }

    public String getResultCode() {
        return (String)super.get(AR_KEY_RESULT_CODE);
    }

    public Object getList() {
        return super.get(AR_KEY_LIST);
    }

    public ApiResult setList(Page<?> data) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<?> list = data.getContent();

        if(super.get(AR_KEY_RESULT_DATA) != null) {
            map = (HashMap<String, Object>) super.get(AR_KEY_RESULT_DATA);
        }
        map.put(AR_KEY_LIST, list);

        this.setPageInfo(data);

        super.put(AR_KEY_RESULT_DATA, map);
        return this;
    }


    public ApiResult setPageInfo(Page<?> data) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(super.get(AR_KEY_RESULT_DATA) != null) {
            map = (HashMap<String, Object>) super.get(AR_KEY_RESULT_DATA);
        }
        PageInfo pi = new PageInfo(data);
        map.put(AR_KEY_PAGEINFO, pi);

        super.put(AR_KEY_RESULT_DATA, map);
        return this;
    }

    public ApiResult setSc(Object searchCondition) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(super.get(AR_KEY_RESULT_DATA) != null) {
            map = (HashMap<String, Object>) super.get(AR_KEY_RESULT_DATA);
        }

        map.put(AR_KEY_SC, searchCondition);
        super.put(AR_KEY_RESULT_DATA, map);
        return this;
    }

    public Object getSc() {
        return super.get(AR_KEY_SC);
    }



}
