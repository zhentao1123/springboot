package com.example.demo.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;

@Configuration
public class JsonConfig {

	@Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
       FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
       
       FastJsonConfig fastJsonConfig = new FastJsonConfig();
       fastJsonConfig.setSerializerFeatures(
    		   SerializerFeature.PrettyFormat, 
    		   SerializerFeature.QuoteFieldNames, 
    		   SerializerFeature.WriteDateUseDateFormat,
    		   SerializerFeature.WriteNullListAsEmpty,
    		   SerializerFeature.DisableCircularReferenceDetect);
       fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
       
       //fastJsonConfig.setParserConfig(parserConfig);
       fastConverter.setFastJsonConfig(fastJsonConfig);
       
       fastConverter.setSupportedMediaTypes(MediaType.parseMediaTypes(Lists.newArrayList(
    		   MediaType.APPLICATION_JSON_UTF8_VALUE, 
    		   MediaType.TEXT_HTML_VALUE+";charset=UTF-8"
    		   )));
       
       HttpMessageConverter<?> converter = fastConverter;
       return new HttpMessageConverters(converter);
    }
}
