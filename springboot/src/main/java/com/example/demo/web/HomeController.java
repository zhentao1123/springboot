package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.ConfigInfo;
import com.example.demo.to.ConfigInfoTO;
import com.example.demo.util.mapper.BeanMapper;

@Controller
@RequestMapping("/")
public class HomeController {
	
	public static final String HOME_RESULT = "Home";
	
	@Autowired
	ConfigInfo configInfo;
	
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
}
