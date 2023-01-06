package tr.com.swe599;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import lombok.ToString;

@ToString
public class CheckSyntaxDto {
    @JsonProperty
    @Expose
    public final String errors;

    public CheckSyntaxDto(String errors) {
        this.errors = errors;
    }
}
