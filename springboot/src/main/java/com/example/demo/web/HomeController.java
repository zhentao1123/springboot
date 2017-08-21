package com.example.demo.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.ConfigInfo;
import com.example.demo.config.ConfigInfo1;
import com.example.demo.to.ConfigInfoTO;
import com.example.demo.util.mapper.BeanMapper;

@Controller
@RequestMapping("/")
public class HomeController {
	
	public static final String HOME_RESULT = "Home";
	
	@Autowired
	ConfigInfo configInfo;
	
	@Resource(name="configInfo1")
	ConfigInfo1 configInfo1;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseBody
    public String home() {
        return HOME_RESULT;
    }
	
	@RequestMapping(value="/getConfigInfo", method=RequestMethod.GET)
    @ResponseBody
	public ConfigInfoTO getConfigInfo(){
		return BeanMapper.map(configInfo, ConfigInfoTO.class);
	}
	
	@RequestMapping(value="/getConfigInfo1", method=RequestMethod.GET)
    @ResponseBody
	public ConfigInfoTO getConfigInfo1(){
		return BeanMapper.map(configInfo1, ConfigInfoTO.class);
	}
}
