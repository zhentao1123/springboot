package com.example.demo.util.jackson;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SuppressWarnings("serial")
public class CustomerObjectMapper extends ObjectMapper {
	public CustomerObjectMapper(){
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
}
