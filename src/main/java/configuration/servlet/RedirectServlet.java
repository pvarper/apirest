package configuration.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URL_PREFIX = "url=";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        String query = req.getQueryString();
        if (query.contains(URL_PREFIX)) {
            String url = query.replace(URL_PREFIX, "");
            if (!url.startsWith(req.getContextPath())) {
                url = req.getContextPath() + url;
            }

            resp.sendRedirect(url);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
