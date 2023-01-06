package tr.com.swe599;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class ApiKey {
    @Getter
    private String apiKey;

    public ApiKey(ServerEnv serverEnv) {
        this.apiKey = Base64.getEncoder().encodeToString(serverEnv.getRawApiKey().getBytes(StandardCharsets.UTF_8));
    }
}
