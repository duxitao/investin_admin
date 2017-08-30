package cn.investin.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.investin.model.AdminUser;

public class LoginInterceptor implements HandlerInterceptor {

	private String excludedUrls = null;

	public String getExcludedUrls() {
		return excludedUrls;
	}

	public void setExcludedUrls(String excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getServletPath();
		if (!url.contains(excludedUrls)) {
			if (null == request.getSession().getAttribute("user")) {
				String path = request.getContextPath();
				response.sendRedirect(path + "\\login");
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
