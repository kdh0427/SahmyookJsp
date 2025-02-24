package jdbc;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class OracleDriverLoader extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
