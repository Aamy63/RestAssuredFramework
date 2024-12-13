
package data.validResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Owner {

    @JsonProperty("href")
    String href;
    @JsonProperty("id")
    String id;
    @JsonProperty("type")
    String type;
    @JsonProperty("uri")
    String uri;
    @JsonProperty("display_name")
    Object displayName;
    @JsonProperty("external_urls")
    ExternalUrls__1 externalUrls;
}
