package com.lms.site;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	public static String getSite(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
                System.out.println(request.getServletPath().toString());
                System.out.println(siteURL);
		return siteURL.replace(request.getServletPath(), "");
                
}
}