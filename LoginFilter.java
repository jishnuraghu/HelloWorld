package com.aig.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter{

	private static final String APP_NAME = "Login App | ";

	/**
	 * Default constructor. 
	 */
	public LoginFilter(){
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy(){
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException{
		System.out.println( APP_NAME + "inside the login app filter" );
		clearAllCookies( (HttpServletRequest) request, (HttpServletResponse) response );
		// pass the request along the filter chain
		chain.doFilter( request, response );
	}

	/**
	 * @Author jraghu on Sep 30, 2015
	 * @param request
	 */
	private void clearAllCookies( HttpServletRequest request, HttpServletResponse response ){
		if( null == request.getCookies() || request.getCookies().length == 0 ) return;

		System.out.println( APP_NAME + "About to clear the cookies" );
		
		for( Cookie cookie : request.getCookies() ){
			cookie.setValue( null );
			cookie.setMaxAge( cookie.getMaxAge() );
			cookie.setPath( "/" );
			response.addCookie( cookie );
		}
		
		System.out.println(APP_NAME+ "-----Printing the new cookie values ----");
		for( Cookie cookie : request.getCookies() ){
			System.out.println( APP_NAME + cookie.getName() + " " + cookie.getValue() );
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init( FilterConfig fConfig ) throws ServletException{
		// TODO Auto-generated method stub
	}

}
