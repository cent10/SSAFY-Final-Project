package com.activityx.allei.service;

import com.activityx.allei.dto.KakaoPayApprovalDto;
import com.activityx.allei.dto.KakaoPayBean;
import com.activityx.allei.dto.KakaoPayReadyDto;

public interface KakaoPayService {
	
	KakaoPayReadyDto kakaoPayReady(KakaoPayBean bean);
	
	KakaoPayApprovalDto kakaoPayApproval(String pg_token, KakaoPayBean bean);
}
