
package data.validResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Playlist {

    @JsonProperty("collaborative")
    Boolean collaborative;
    @JsonProperty("description")
    String description;
    @JsonProperty("external_urls")
    ExternalUrls externalUrls;
    @JsonProperty("followers")
    Followers followers;
    @JsonProperty("href")
    String href;
    @JsonProperty("id")
    String id;
    @JsonProperty("images")
    List<Object> images;
    @JsonProperty("primary_color")
    Object primaryColor;
    @JsonProperty("name")
    String name;
    @JsonProperty("type")
    String type;
    @JsonProperty("uri")
    String uri;
    @JsonProperty("owner")
    Owner owner;
    @JsonProperty("public")
    Boolean _public;
    @JsonProperty("snapshot_id")
    String snapshotId;
    @JsonProperty("tracks")
    Tracks tracks;
}