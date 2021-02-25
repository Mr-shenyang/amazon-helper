package com.oscoder.amazon.helper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author
 * @create 2021-02-21 9:01 PM
 **/
@Controller
public class IndexController {
	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
