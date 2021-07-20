package com.work.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Component("needToAdminInterceptor")
@Slf4j
public class NeedToAdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean isAdmin = (boolean)request.getAttribute("isAdmin");
		boolean isLogin = (boolean)request.getAttribute("isLogin");
		
		log.debug("### needToAdminInterceptor isAdmin : " + isAdmin);
			
		if(!isAdmin) {
			// JavaScript 사용해서 경고메세지 출력후 메인페이지 이동 요청
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.append("<script>");
			out.append("alert('[접근불가] 관리자 권한 인증이 필요합니다.');");
			out.append("location.replace('/main');");
			out.append("</script>");
			
			return false;
		}

		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
