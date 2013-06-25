package be.demo.bad.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: massoo
 */
public class CleanseFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(CleanseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest wrappedRequest = new CharacterSetRequestFilter((HttpServletRequest) request);
        LOG.info("Request URL: {}",wrappedRequest.getRequestURL().toString());
        chain.doFilter(wrappedRequest,response);
    }

    @Override
    public void destroy() {
       // nothing
    }

}
