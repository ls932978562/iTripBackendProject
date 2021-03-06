package cn.ekgc.itrip.base.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <b>爱旅行-基础控制器</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class BaseController extends HttpServlet {
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpSession session;
}
