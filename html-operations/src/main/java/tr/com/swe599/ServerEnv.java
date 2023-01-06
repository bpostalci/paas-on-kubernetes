package tr.com.swe599;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ServerEnv {
    @Value("${API_KEY}")
    private String rawApiKey;

}
