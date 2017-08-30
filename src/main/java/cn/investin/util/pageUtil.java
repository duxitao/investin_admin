package cn.investin.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class pageUtil {

	public static int AddPagE(Model model, HttpServletRequest request, int totalRecord) {
		int pageNum = 1;
		int pageSize = Integer.parseInt(AppConfig.getInstance().getProperties().getProperty("system.pageSize"));
		if (request.getParameter("pageNum") != null && !"".equals(request.getParameter("pageNum")))
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		if (request.getParameter("pageSize") != null && !"".equals(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int totalPageNum = totalRecord / pageSize;
		if (totalRecord % pageSize > 0)
			totalPageNum++;
		if (pageNum > totalPageNum)
			pageNum = totalPageNum;
		if (pageNum < 1)
			pageNum = 1;
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("totalPageNum", totalPageNum);
		return pageNum;
	}

}
