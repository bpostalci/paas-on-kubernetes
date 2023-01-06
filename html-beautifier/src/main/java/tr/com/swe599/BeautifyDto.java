package tr.com.swe599;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import lombok.ToString;

@ToString

public class BeautifyDto {
    @JsonProperty
    @Expose
    public final String beautifiedHtml;

    public BeautifyDto(String beautifiedHtml) {
        this.beautifiedHtml = beautifiedHtml;
    }
}
