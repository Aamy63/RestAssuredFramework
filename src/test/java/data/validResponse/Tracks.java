
package data.validResponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tracks {

    @JsonProperty("limit")
    Integer limit;
    @JsonProperty("next")
    Object next;
    @JsonProperty("offset")
    Integer offset;
    @JsonProperty("previous")
    Object previous;
    @JsonProperty("href")
    String href;
    @JsonProperty("total")
    Integer total;
    @JsonProperty("items")
    List<Object> items;
}
