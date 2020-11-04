package com.activityx.allei.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activityx.allei.service.KakaoAPI;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class LoginTestController {

	@Autowired
	private KakaoAPI kakao;

	@PostMapping(value = "/login")
	public HashMap<String, Object> login(@RequestParam("code") String code) {
		String access_Token = kakao.getAccessToken(code);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
		System.out.println("login Controller : " + userInfo);

		// 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
		// if (userInfo.get("email") != null) {
		// 	session.setAttribute("userId", userInfo.get("email"));
		// 	session.setAttribute("access_Token", access_Token);
		// }
		return userInfo;
	}

	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		kakao.kakaoLogout((String) session.getAttribute("access_Token"));
		session.removeAttribute("access_Token");
		session.removeAttribute("userId");
		return "logout";
	}

}
