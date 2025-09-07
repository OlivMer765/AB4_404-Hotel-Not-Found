package org.asco_devs.reservas_nf.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        if (path.contains("login.xhtml") || path.contains("register.xhtml") || path.contains("javax.faces.resource")) {
            chain.doFilter(request, response);
            return;
        }

        Object usuario = req.getSession().getAttribute("authController");
        if (usuario == null) {
            res.sendRedirect(req.getContextPath() + "/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
