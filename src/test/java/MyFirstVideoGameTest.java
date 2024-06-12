import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class MyFirstVideoGameTest extends VideoGameConfig {

    @Test
    public void myFirstTest() {
        given()
                .log().all()
        .when()
                .get("/videogame")
        .then()
                .log().all();

    }

    @Test
    public void myFirstTestWithEndpoints() {
        get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then().log().all();
    }
}
