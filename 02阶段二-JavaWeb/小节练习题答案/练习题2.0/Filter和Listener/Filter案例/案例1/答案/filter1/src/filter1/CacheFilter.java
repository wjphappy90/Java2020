package filter1;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CacheFilter
 */
public class CacheFilter implements Filter {
	private FilterConfig config;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		int expires = 0;
		if (uri.endsWith(".png")) {
			expires = Integer.parseInt(config.getInitParameter("png"));
		} else if (uri.endsWith("css")) {
			expires = Integer.parseInt(config.getInitParameter("css"));
		} else {
			expires = Integer.parseInt(config.getInitParameter("js"));
		}
		res.setDateHeader("expires", System.currentTimeMillis() + expires * 1000 * 60);

		chain.doFilter(req, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}
