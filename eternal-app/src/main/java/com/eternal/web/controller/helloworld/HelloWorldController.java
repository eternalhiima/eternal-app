package com.eternal.web.controller.helloworld;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	/**
	 * 疎通確認用のテストに使用する
	 * @return
	 */
	@RequestMapping("/hello")
	public String helloWorld() {
		return "/hello";
	}

	/**
	 * webサーバーとのAPI通信のテストに使用する
	 * @return
	 */
	@RequestMapping(value="/hellojson", method=RequestMethod.GET)
	@ResponseBody
	public List<String> helloJson() {
		List<String> nameList = new ArrayList<>();
		nameList.add("Taro");
		nameList.add("Jiro");
		nameList.add("Jack");
		return nameList;
	}
}
