package fr.capgemini.sncf.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 * Intercept user request before the controller gets it.
	 * Can be used for authentification or maintenance.
	 * 
	 * @return true to send user request to the controller, false to stop it.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return authUser(request);
	}
	
	/**
	 * Intercept user request after the controller gets it and before sending the output to view.
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * A method to simulate authentification.
	 * 
	 * @param request
	 * @return true if user has been authenticated, false if not.
	 */
	private boolean authUser(HttpServletRequest request){
		return true;
	}
}
