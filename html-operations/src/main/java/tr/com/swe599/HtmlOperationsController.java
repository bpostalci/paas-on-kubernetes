package tr.com.swe599;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "html-operations")
@RestController
public class HtmlOperationsController {

    private final HtmlSyntaxCheckerService htmlSyntaxCheckerService;
    private final HtmlBeautifierService htmlBeautifierService;

    HtmlOperationsController(HtmlSyntaxCheckerService htmlSyntaxCheckerService,
                             HtmlBeautifierService htmlBeautifierService) {
        this.htmlSyntaxCheckerService = htmlSyntaxCheckerService;
        this.htmlBeautifierService = htmlBeautifierService;
    }

    @RequestMapping(value = "/beautify", method = RequestMethod.POST)
    public String beautify(@RequestBody String htmlPayload) {

        log.info("/beautify received payload: " + htmlPayload);

        // TODO:
        BeautifyDto dto = htmlBeautifierService.beautify(htmlPayload);
        String responseJson = JsonHelper.toJsonString(dto);

        log.info("/beautify response: " + responseJson);

        return responseJson;
    }

    @RequestMapping(value = "/check-syntax")
    public String checkSyntax(@RequestBody String htmlPayload) {
        log.info("/check-syntax received payload: " + htmlPayload);

        CheckSyntaxDto dto = htmlSyntaxCheckerService.checkSyntax(htmlPayload);
        if (dto == null) {
            // TODO: response 500
        }
        String responseJson = JsonHelper.toJsonString(dto);

        log.info("/check-syntax response: " + responseJson);

        return JsonHelper.toJsonString(dto);
    }
}
