package com.example.demo.web.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",//匹配路径
initParams={
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),// 忽略资源
    @WebInitParam(name="sessionStatMaxCount",value="1000"),//缺省1000
    //@WebInitParam(name="sessionStatEnable",value="false"),//可以关闭season监控
    @WebInitParam(name="profileEnable",value="true"),//druid 0.2.7版本开始支持profile，配置profileEnable能够监控单个url调用的sql列表
})
public class DruidStatFilter extends WebStatFilter{

}
