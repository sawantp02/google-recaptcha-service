package com.sample.recaptcha.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.recaptcha.service.RecaptchaService;

@RestController
@RequestMapping("/recaptcha")
public class GoogleRecaptchaController {

	@Autowired RecaptchaService captchaService;
	private static final String UNKNOWN = "unknown";
	@GetMapping("/verify")
	public ResponseEntity<Map<String, Object>> verifyGoogleRecaptcha(@RequestParam(name="g-recaptcha-response") String recaptchaResponse, HttpServletRequest request) {
		String remoteIP = request.getHeader("x-forwarded-for");  
		if(StringUtils.equalsIgnoreCase(UNKNOWN, remoteIP)) {      
			remoteIP = request.getHeader("Proxy-Client-IP");      
		}      
		if(StringUtils.equalsIgnoreCase(UNKNOWN, remoteIP)) {      
			remoteIP = request.getHeader("WL-Proxy-Client-IP");      
		}      
		if(StringUtils.equalsIgnoreCase(UNKNOWN, remoteIP)) {      
	       remoteIP = request.getRemoteAddr();      
		} 
		String captchaVerifyMessage = 
			captchaService.verifyRecaptcha(remoteIP, recaptchaResponse);
		
		if ( captchaVerifyMessage != null && captchaVerifyMessage.length() > 0) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", captchaVerifyMessage);
			return ResponseEntity.badRequest()
					.body(response);
		}
		return ResponseEntity.ok().build();
	}
}
