package com.lang.meeting.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lang.meeting.dao.CounterDao;

public class CounterListener implements ServletContextListener {
//	上下文对象销毁时自动调用
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext ctxt = arg0.getServletContext();
		int visitcount = Integer.parseInt(ctxt.getAttribute("visitcount").toString());
		CounterDao dao = new CounterDao();
		dao.update(visitcount);
	}
//	上下文对象创建时自动调用
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		CounterDao dao = new CounterDao();
		int visitcount = dao.select();
		ServletContext ctxt = arg0.getServletContext();
		ctxt.setAttribute("visitcount", visitcount);

	}

}
