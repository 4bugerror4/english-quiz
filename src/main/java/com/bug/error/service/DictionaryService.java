package com.bug.error.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
	
	private static final String URL = "https://dic.daum.net/search.do?q=";
	
	public List<String> getWord(String query) {
		
		List<String> list = new ArrayList<String>();
		
		Document document = null;
		
		try {
			document = Jsoup.connect(URL + query).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements elements = document.select(".cleanword_type .list_search li");
		
		for (Element element : elements) {
			list.add(element.text());
		}
		
		return list;
	}
	

}
