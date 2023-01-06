package tr.com.swe599;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "html-syntax-checker")
@RestController
public class HtmlSyntaxCheckerController {

    private final String apiKey;
    private final HtmlSyntaxCheckerService htmlSyntaxCheckerService;

    HtmlSyntaxCheckerController(HtmlSyntaxCheckerService htmlSyntaxCheckerService,
                                ApiKey apiKey) {
        this.htmlSyntaxCheckerService = htmlSyntaxCheckerService;
        this.apiKey = apiKey.getApiKey();
    }

    @RequestMapping(value = "/check-syntax")
    public ResponseEntity<String> checkSyntax(@RequestBody String htmlPayload,
                                              @RequestHeader(value = "api_key") String receivedApiKey) {

        if (!this.apiKey.equals(receivedApiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid API key");
        }

        log.info("/check-syntax received payload: " + htmlPayload);

        CheckSyntaxDto dto = htmlSyntaxCheckerService.checkSyntax(htmlPayload);
        String responseJson = JsonHelper.toJsonString(dto);

        log.info("/check-syntax response: " + responseJson);

        return ResponseEntity.ok(JsonHelper.toJsonString(dto));
    }
}
