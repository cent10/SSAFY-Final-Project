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

import com.activityx.allei.dto.KakaoPayApprovalDto;
import com.activityx.allei.dto.KakaoPayBean;
import com.activityx.allei.dto.KakaoPayReadyDto;

@Service
public class KakaoPayServiceImpl implements KakaoPayService {

	private static final String HOST = "https://kapi.kakao.com";
	
	private KakaoPayReadyDto kakaoPayReadyDto;
	private KakaoPayApprovalDto kakaoPayApprovalDto;
    
	public KakaoPayReadyDto kakaoPayReady(KakaoPayBean bean) {
 
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "f729a7395dd42c6cc5bc8b634d001cd6");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", ""+bean.getId());	//주문번호
        params.add("partner_user_id", ""+bean.getShop());	//업체번호
        params.add("item_name", bean.getName());			//상품명
        params.add("quantity", ""+bean.getQuantity());		//수량
        params.add("total_amount", ""+bean.getAmount());	//결제 가격
        params.add("tax_free_amount", "100");				//비과세
        params.add("approval_url", "http://localhost:8080/pay/kakao/success");
        params.add("cancel_url", "http://localhost:8080/pay/kakao/cancel");
        params.add("fail_url", "http://localhost:8080/pay/kakao/fail");
 
         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
        	kakaoPayReadyDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyDto.class);
            return kakaoPayReadyDto;
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
        
    }

	@Override
	public KakaoPayApprovalDto kakaoPayApproval(String pg_token, KakaoPayBean bean) {
		
		RestTemplate restTemplate = new RestTemplate();
	
		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "f729a7395dd42c6cc5bc8b634d001cd6");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		
		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReadyDto.getTid());
		params.add("partner_order_id", ""+bean.getId());
		params.add("partner_user_id", ""+bean.getShop());
		params.add("pg_token", pg_token);
		params.add("total_amount", ""+bean.getAmount());
		
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		try {
			kakaoPayApprovalDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalDto.class);

			return kakaoPayApprovalDto;
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
