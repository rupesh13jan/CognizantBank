package com.journaldev.spring.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleApi {

	//JSONArray obj=new JSONArray(Arrays.asList(new Topic(1, "spring", "standlone application")));
		@RequestMapping("/topics")
		public List<Topic> getTopics() {
			return Arrays.asList(new Topic(1, "spring", "standlone application"),new Topic(2, "javasript", "UI validation"));
//return "Hi";
		}

	

}
