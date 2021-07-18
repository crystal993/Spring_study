package com.work.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Component("needToLogoutInterceptor") // 다중의 HandlerInterceptor 이어서 bean-name 설정
@Slf4j
public class NeedToLogoutInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean isLogin = (boolean)request.getAttribute("isLogin");
		
		log.debug("### needToLogoutInterceptor isLogin : " + isLogin);
		
		// 로그아웃 했음에도 불구하고 요청객체에 isLogin = true (로그인 되어있는 경우)
		if(isLogin) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.append("<script>");
			out.append("alert('로그아웃하시기 바랍니다.');");
			out.append("location.replace('/main');");
			out.append("</script>");
			
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
