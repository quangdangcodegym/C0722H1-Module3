//package com.example.helloc6.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(displayName = "AuthenticationCookies", urlPatterns = "/*")
//public class AuthenticationCookies extends HttpFilter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        Cookie[] cookies = request.getCookies();
//        String username = "";
//        String password = "";
//        for (Cookie cookie : cookies) {
//            if(cookie.getName().equals("username")){
//                username = cookie.getValue();
//            }
//            if(cookie.getName().equals("password")){
//                password = cookie.getValue();
//            }
//        }
//        request.getSession().setAttribute("username", username);
//        request.getSession().setAttribute("password", password);
//        filterChain.doFilter(request, response);
//    }
//}
