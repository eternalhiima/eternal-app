package com.eternal.web.controller.helloworld;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	/**
	 * 疎通確認用のテストに使用する
	 * @return hello.html
	 */
	@RequestMapping("/hello")
	public String helloWorld() {
		return "/hello";
	}

	/**
	 * webサーバーとのAPI通信のテストに使用する
	 * @return 名前リストjson
	 */
	@RequestMapping(value="/hellojson", method=RequestMethod.GET)
	@ResponseBody
	public List<String> helloJson() {
		return Arrays.asList("Taro", "Jiro", "Jack");
	}
}
