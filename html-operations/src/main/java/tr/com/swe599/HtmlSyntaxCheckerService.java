package tr.com.swe599;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j(topic = "html-operations")
@Service
public class HtmlSyntaxCheckerService {

    @Value("${HTML_SYNTAX_CHECKER_URL}")
    private String htmlSyntaxCheckerUrl;
    private final String apiKey;

    HtmlSyntaxCheckerService(ApiKey apiKey) {
        this.apiKey = apiKey.getApiKey();
    }

    public CheckSyntaxDto checkSyntax(String html) {

        try {
            if (htmlSyntaxCheckerUrl == null) {
                log.error("htmlSyntaxCheckerUrl is null");
            }
            String uri = "http://" + htmlSyntaxCheckerUrl + "/check-syntax";
            log.info("uri: {}", uri);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("api_key", apiKey);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("html", html);

            HttpEntity<MultiValueMap<String, String>> request =
                    new HttpEntity<>(map, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("" + response.getStatusCodeValue());
            }

            return (CheckSyntaxDto) JsonHelper.fromJsonString(response.getBody(), CheckSyntaxDto.class);
        } catch (Exception e) {
            log.error("", e);
        }

        return null;
    }
}
