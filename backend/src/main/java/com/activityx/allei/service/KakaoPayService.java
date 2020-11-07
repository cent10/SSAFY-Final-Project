package com.activityx.allei.service;

import com.activityx.allei.dto.KakaoPayApprovalDto;
import com.activityx.allei.dto.KakaoPayReadyDto;

public interface KakaoPayService {
	
	KakaoPayReadyDto kakaoPayReady();
	
	KakaoPayApprovalDto kakaoPayApproval(String pg_token);
}
