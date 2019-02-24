package org.study.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// session ��ü ����
				HttpSession sessoin = request.getSession();
				if (sessoin.getAttribute("adminUserId") == null) {
					response.sendRedirect(request.getContextPath() + "/admin/login?message=nologin");
					return false; // ������ ������ �α��� ������ �̵�
				} else {
					return true; // ��� ����
				}
			}
	}
