package tr.com.swe599;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "html-operations")
@RestController
public class HtmlBeautifierController {
    private final String apiKey;
    private final HtmlBeautifierService htmlBeautifierService;

    HtmlBeautifierController(HtmlBeautifierService htmlBeautifierService,
                             ApiKey apiKey) {
        this.htmlBeautifierService = htmlBeautifierService;
        this.apiKey = apiKey.getApiKey();
    }

    @RequestMapping(value = "/beautify", method = RequestMethod.POST)
    public ResponseEntity<String> beautify(@RequestBody String htmlPayload,
                                           @RequestHeader(value = "api_key") String receivedApiKey) {

        if (!this.apiKey.equals(receivedApiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid API key");
        }

        log.info("/beautify received payload: " + htmlPayload);

        BeautifyDto dto = htmlBeautifierService.beautify(htmlPayload);
        String responseJson = JsonHelper.toJsonString(dto);

        log.info("/beautify response: " + responseJson);

        return ResponseEntity.ok(responseJson);
    }
}
