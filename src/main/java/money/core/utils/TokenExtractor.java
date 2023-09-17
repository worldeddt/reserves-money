package money.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class TokenExtractor {

    public static String resolveToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");
        if (itHasToken(bearerToken)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static boolean itHasToken(String bearerToken) {
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer");
    }
}
