package com.activityx.allei.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoPayService {
	
	private static String HOST = "https://kapi.kakao.com";
	private static String ADMINKEY = "f729a7395dd42c6cc5bc8b634d001cd6";

	public String KakaoPay() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "admin key를 넣어주세요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("item_name", "갤럭시S9");
        params.add("quantity", "1");
        params.add("total_amount", "2100");
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");
 
         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
//            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
//            
//            log.info("" + kakaoPayReadyVO);
//            
//            return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
        }
		
		return null;
	}

}
