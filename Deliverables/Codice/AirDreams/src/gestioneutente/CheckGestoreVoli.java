package gestioneutente;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet Filter implementation class CheckGestoreVoli
 */
@WebFilter(filterName = "CheckGestoreVoli",urlPatterns = {"/gestoreVoli/*"})
public class CheckGestoreVoli implements Filter {

    /**
     * Default constructor. 
     */
    public CheckGestoreVoli() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        Account user = (Account) ((HttpServletRequest) request).getSession().getAttribute("account");

        if (user != null && user.getRuolo().equals(Ruolo.gestoreVoli)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("../login.jsp");
        }
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
