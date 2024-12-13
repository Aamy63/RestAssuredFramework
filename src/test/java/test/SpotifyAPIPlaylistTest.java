package test;

import data.invalidResponse.ErrorMain;
import data.validResponse.Playlist;
import framework.StatusCode;
import static framework.StatusCode.*;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.TestDataUtils.generateDescription;
import static utils.TestDataUtils.generateName;

public class SpotifyAPIPlaylistTest extends  BaseTest{

    @Severity(SeverityLevel.CRITICAL)
    @Link()
    @TmsLink("TC-001")
    @Description("This is our description")
    @Test(description = "Should able to create playlist in folder")
    public void shouldAbleToCreatePlaylistTest(){
        String name = generateName();
        System.out.println(name);
        String description = generateDescription();
        System.out.println(description);
        Playlist requestPayload = Playlist.builder().
                name(name).
                description(description).
                _public(false).
                build();

        Response response = APIRequest.postRequest(requestPayload);
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_201.code));
        assertThat(response.getContentType(), equalTo("application/json; charset=utf-8"));

        Playlist responsePayload = response.as(Playlist.class);
        assertThat(responsePayload.getName(), equalTo(name));
        assertThat(responsePayload.getDescription(), equalTo(description));
        assertThat(responsePayload.get_public(), equalTo(requestPayload.get_public()));
    }

    @Test
    public void shouldAbleToGetPlaylistTest(){

        Response response = APIRequest.getRequest("2jbMBFJMcLOBQWAYfgfvYi");

        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_200.code));
        assertThat(response.getContentType(), equalTo("application/json; charset=utf-8"));

        Playlist responsePayload = response.as(Playlist.class);

        assertThat(responsePayload.getName(), equalTo("Updated Playlist Name"));
        assertThat(responsePayload.getDescription(), equalTo("Updated playlist description"));
        assertThat(responsePayload.get_public(), equalTo(true));
    }

    @Test
    public void shouldAbleToUpdatePlaylistTest(){
        Playlist requestPayload = Playlist.builder().
                name("Updated Playlist Name").
                description("Updated playlist description").
                _public(false).
                build();

        Response response = APIRequest.putRequest(requestPayload, "2jbMBFJMcLOBQWAYfgfvYi");
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_200.code));
    }

    @Test
    public void shouldNotAbleToCreatePlaylistWithoutNameTest(){
        Playlist requestPayload = Playlist.builder().
                name("").
                description("New playlist description").
                _public(false).
                build();

        Response response = APIRequest.postRequest(requestPayload);

        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_400.code));
        assertThat(response.getContentType(), equalTo("application/json; charset=utf-8"));

        ErrorMain responsePayload = response.as(ErrorMain.class);

        assertThat(responsePayload.getErrorBase().getStatus(), equalTo(400));
        assertThat(responsePayload.getErrorBase().getMessage(), equalTo(StatusCode.CODE_400.msg));
    }

    @Test
    public void shouldNotAbleToCreatePlaylistWithInvalidAccessTokenTest(){
        Playlist requestPayload = Playlist.builder().
                name("New Playlist").
                description("New playlist description").
                _public(false).
                build();

        Response response = APIRequest.postRequest(requestPayload, "12345");

        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_401.code));
        assertThat(response.getContentType(), equalTo("application/json"));

        ErrorMain responsePayload = response.as(ErrorMain.class);

        assertThat(responsePayload.getErrorBase().getStatus(), equalTo(getStatusCode(CODE_401)));
        assertThat(responsePayload.getErrorBase().getMessage(), equalTo(getMessage(CODE_401)));
    }

    @Step("get status code to verify")
    public int getStatusCode(StatusCode statusCode) {
        return statusCode.code;
    }

    @Step("Get message to verify")
    public String getMessage(StatusCode statusCode) {
        return statusCode.msg;
    }
}