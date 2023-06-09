package com.bug.error.controller.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug.error.entity.dto.CMRespDto;
import com.bug.error.entity.dto.TranslateDto;

@RestController
@RequestMapping("/api")
public class PaPagoApiController {
	
	private final String clientId = "OBMTI3va7llGJwFVpIxJ";//애플리케이션 클라이언트 아이디값";
    private final String clientSecret = "GOEDRRpixx";//애플리케이션 클라이언트 시크릿값";

    private final String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	
	@PostMapping("/translate")
	public ResponseEntity<?> translate(@RequestBody TranslateDto dto) throws ParseException {
		
		String text = "";
		
		try {
            text = URLEncoder.encode(dto.getSource(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        }
		
		Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String responseBody = post(apiURL, requestHeaders, text, dto.getSourceAndTargetNumber());
        
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(responseBody);
        
        return new ResponseEntity<>(new CMRespDto<>(1, "번역 성공", jsonObject), HttpStatus.OK);
	}
	
	private static String post(String apiUrl, Map<String, String> requestHeaders, String text, int searchAndTargetNumber){
        HttpURLConnection con = connect(apiUrl);
        
        String postParams = "";
        if (searchAndTargetNumber == 1) {
        	postParams = "source=ko&target=en&text=" + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
        } else if (searchAndTargetNumber == 2) {
        	postParams = "source=en&target=ko&text=" + text; //원본언어: 한국어 (en) -> 목적언어: 영어 (ko)
        }
        
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

}
