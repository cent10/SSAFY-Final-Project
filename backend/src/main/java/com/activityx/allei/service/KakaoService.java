package com.activityx.allei.service;

import com.activityx.allei.dto.KakaoPayApprovalDto;
import com.activityx.allei.dto.KakaoPayReadyDto;

public interface KakaoService {
	
	KakaoPayReadyDto kakaoPayReady();
	
	KakaoPayApprovalDto kakaoPayApproval(String pg_token);
}
