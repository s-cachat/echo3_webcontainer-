package nextapp.echo.webcontainer;

import java.io.IOException;
import java.util.logging.Logger;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

/**
 * filtre pour le forcage du samesite sur les cookie
 *
 * @author scachat
 */
@WebFilter(urlPatterns = "/app")
public class SameSiteCookieFilter implements Filter {

    private final static Logger logger = Logger.getLogger(SameSiteCookieFilter.class.getSimpleName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper((HttpServletResponse) response) {
            @Override
            public void addHeader(String name, String value) {
                if ("Set-Cookie".equalsIgnoreCase(name) && value != null && value.contains("JSESSIONID")
                        && !value.toLowerCase().contains("samesite=")) {
                    value += "; SameSite=Lax"; // ou Strict/None
                }
                super.addHeader(name, value);
            }
            // Mêmes surcharges nécessaires pour setHeader
        };
        chain.doFilter(request, wrappedResponse);
    }

}
