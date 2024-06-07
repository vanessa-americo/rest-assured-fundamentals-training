import config.VideoGameConfig;
import config.VideoGameEndpoints;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import objects.VideoGame;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class VideoGameTest extends VideoGameConfig {

    String gameBodyJson = "{\n" +
            "  \"category\": \"Platform\",\n" +
            "  \"name\": \"Mario Party\",\n" +
            "  \"rating\": \"Mature\",\n" +
            "  \"releaseDate\": \"2024-05-04\",\n" +
            "  \"reviewScore\": 89\n" +
            "}";

    @Test
    public void getAllGames() {
        given()
                .when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void createNewGameByJSON() {
        given()
                .body(gameBodyJson)
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void createNewGameByXML() {
        String gameBodyXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<VideoGameRequest>\n" +
                "\t<category>Platform</category>\n" +
                "\t<name>Mario</name>\n" +
                "\t<rating>Mature</rating>\n" +
                "\t<releaseDate>2012-05-04</releaseDate>\n" +
                "\t<reviewScore>85</reviewScore>\n" +
                "</VideoGameRequest>";

        given()
                .body(gameBodyXml)
                .contentType("application/xml")
                .accept("application/xml")
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void updateGame() {
        given()
                .body(gameBodyJson)
                .when()
                .put("videogame/3")
                .then();
    }

    @Test
    public void deleteGame() {
        given()
                .accept("text/plain")
                .when()
                .delete("videogame/8")
                .then();
    }

    @Test
    public void getSingleGame() {
        given()
                .pathParam("videoGameId", 5)
                .when()
                .get(VideoGameEndpoints.SINGLES_VIDEO_GAME)
                .then();
    }

    @Test
    public void testVideoGameSerializationByJSON() {
        VideoGame videoGame = new VideoGame("Shooter", "MyAwesomeGame", "Mature", "2018-04-04", 99);

        given()
                .body(videoGame)
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void testVideoGameXMLSchema() {
        given()
                .pathParam("videoGameId", 5)
                .contentType("application/xml")
                .accept("application/xml")
        .when()
                .get(VideoGameEndpoints.SINGLES_VIDEO_GAME)
        .then()
                .body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameXSD.xsd"));
    }

    @Test
    public void testVideoGameJSONSchema() {
        given()
                .pathParam("videoGameId", 5)
                .contentType("application/json")
                .accept("application/json")
        .when()
                .get(VideoGameEndpoints.SINGLES_VIDEO_GAME)
        .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VideoGameJSONSchema.json"));
        //json-schema-validator dependency, since it does not come with RestAssured
    }

    @Test
    public void testConvertionFromJSONToPOJO() {
        Response response =
                given()
                        .pathParam("videoGameId", 5)
                        .contentType("application/json")
                        .accept("application/json")
                .when()
                        .get(VideoGameEndpoints.SINGLES_VIDEO_GAME);

        VideoGame videoGame = response.getBody().as(VideoGame.class);
        System.out.println(videoGame);
    }

    @Test
    public void captureResponseTime() {
        long responseTime = get(VideoGameEndpoints.ALL_VIDEO_GAMES).time();
        System.out.println("Response time in MS is: " + responseTime);
    }

    @Test
    public void assertOnResponseTime() {
        get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then().time(lessThan(1000L));
    }

}