package money.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class TokenConfiguration {
    private String secret;
}
