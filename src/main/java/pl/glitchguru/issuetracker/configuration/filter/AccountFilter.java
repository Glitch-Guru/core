package pl.glitchguru.issuetracker.configuration.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.glitchguru.issuetracker.model.authentication.AccountContext;

import java.io.IOException;

@Component
@Order(1)
public class AccountFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final var req = (HttpServletRequest) request;
        if (req.getHeader("X-AccountID") == null) {
            AccountContext.setCurrentAccountId(-1L);
        } else {
            AccountContext.setCurrentAccountId(Long.valueOf(req.getHeader("X-AccountID")));
        }
        try {
            chain.doFilter(request, response);
        } finally {
            AccountContext.setCurrentAccountId(-1L);
        }
    }
}
