package com.activityx.allei.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.activityx.allei.dao.ReservationDao;
import com.activityx.allei.dto.KakaoPayApprovalDto;
import com.activityx.allei.dto.KakaoPayBean;
import com.activityx.allei.dto.KakaoPayReadyDto;
import com.activityx.allei.dto.ReservationDto;

@Service
public class KakaoPayServiceImpl implements KakaoPayService {

	private static final String HOST = "https://kapi.kakao.com";
	
	@Autowired
	ReservationDao reservationDao;
	
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
//        params.add("approval_url", "http://k3a210.p.ssafy.io/kakaopay/success");
//        params.add("cancel_url", "http://k3a210.p.ssafy.io/");
//        params.add("fail_url", "http://k3a210.p.ssafy.io/kakaopay/fail");
      params.add("approval_url", "http://localhost:3000/kakaopay/success");
      params.add("cancel_url", "http://localhost:3000/");
      params.add("fail_url", "http://localhost:3000/kakaopay/fail");
 
         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
        	KakaoPayReadyDto kakaoPayReadyDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyDto.class);
        	//db에 tid입력
        	reservationDao.addTid(kakaoPayReadyDto.getTid(), bean.getId());
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
	public KakaoPayApprovalDto kakaoPayApproval(String pg_token, String tid) {
		
		RestTemplate restTemplate = new RestTemplate();
	
		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "f729a7395dd42c6cc5bc8b634d001cd6");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		
		// DB에서 tid로 데이터 가져오기
		ReservationDto reservationDto = reservationDao.getReservationByTid(tid);
		
		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("cid", "TC0ONETIME");
		params.add("tid", tid);
		params.add("partner_order_id", ""+reservationDto.getId());
		params.add("partner_user_id", ""+reservationDto.getShop());
		params.add("pg_token", pg_token);
		
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		try {
			KakaoPayApprovalDto kakaoPayApprovalDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalDto.class);

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
