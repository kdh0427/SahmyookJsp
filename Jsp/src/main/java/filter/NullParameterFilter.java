package filter;

import java.io.IOException;
import java.util.StringTokenizer;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class NullParameterFilter implements Filter {
	private String[] parameterNames = null;

	@Override
	public void init(FilterConfig config) throws ServletException {
		String names = config.getInitParameter("parameterNames");
		StringTokenizer st = new StringTokenizer(names, ", ");
		parameterNames = new String[st.countTokens()];

		for (int i = 0; st.hasMoreTokens(); i++) {
			parameterNames[i] = st.nextToken();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		NullParameterRequestWrapper requestWrapper =
				new NullParameterRequestWrapper((HttpServletRequest) request);
		requestWrapper.checkNull(parameterNames);

		chain.doFilter(requestWrapper, response);
	}

	@Override
	public void destroy() {
	}
}
