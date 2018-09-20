package com.casit.entity.PO;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 类说明:
 * 
 * @author zhouhai
 * @version 创建时间：2018年9月5日 下午12:01:15
 */
public abstract class BaeEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	public String toJsonByGson() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(this);
	}

	public String toJsonByJackson() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return mapper.writeValueAsString(this);
	}

	
	
}
