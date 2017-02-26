package com.app.tennis.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AccesFilter implements Filter {

    public AccesFilter() {
    }

	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String pageConnection = "/ControlServlet?task=connexion";
		HttpServletRequest rq = (HttpServletRequest) request;
        
        String url = rq.getRequestURI().substring(rq.getContextPath().length());
        
        if (url.startsWith("/css") || url.startsWith("/js")){
        	chain.doFilter(request, response);
        	return;
        }
        
        HttpSession session = rq.getSession();
        
        if (session.getAttribute("sessionUser") == null){
        	RequestDispatcher rd = request.getRequestDispatcher(pageConnection);
        	rd.forward(request, response);
        } else {
        	chain.doFilter(request, response);
        }
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
