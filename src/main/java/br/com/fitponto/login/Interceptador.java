package br.com.fitponto.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptador extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {

		String uri = request.getRequestURI();
		if(uri.endsWith("login") || uri.endsWith("loginWrong") || uri.endsWith("doLogin") || uri.contains("resources")){
			return true;
		}

		if(request.getSession().getAttribute("sessionPerfil") != null) {
			return true;
		}

		response.sendRedirect("login");
		return false;
	}
}