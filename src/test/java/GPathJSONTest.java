import config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

@Ignore
public class GPathJSONTest extends FootballConfig {

    @Test
    public void extractMapOfElementsWithFind() {
        Response response = get("competitions/2021/teams");

        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Manchester United FC' }");
        System.out.println("Map of team data = " + allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind() {
        Response response = get("teams/57");
        String certainPlayer = response.path("squad.find { it.id == 7800 }.name");
        System.out.println("Name of the player is = " + certainPlayer);
    }

    @Test
    public void extractListOfValuesWithFindAll() {
        Response response = get("teams/57");
        List<String> playerNames = response.path("squad.findAll { it.id >= 7800 }.name");
        System.out.println("Name of the players = " + playerNames);
    }

    @Test
    public void extractSingleValueWithHighestNumber() {
        Response response = get("teams/57");
        String playerName = response.path("squad.max { it.id }.name");
        System.out.println("Player name with the highest id = " + playerName);
    }

    @Test
    public void extractSingleValueWithLowestNumber() {
        Response response = get("teams/57");
        String playerName = response.path("squad.min { it.id }.name");
        System.out.println("Player name with the lowest id = " + playerName);
    }

    @Test
    public void extractMultiplleValuesAndSumThem() {
        Response response = get("teams/57");
        int sumOfIds = response.path("squad.collect { it.id }.sum()");
        System.out.println("Sum of all player IDs = " + sumOfIds);
    }

    @Test
    public void extractMapWithFindAndFindAllWithParameters() {
        String position = "Offence";
        String nationality = "England";

        Response response = get("teams/57");

        Map<String, ?> playerOfCertainPosition = response.path
                ("squad.findAll { it.position == '%s'}.find { it.nationality == '%s'}",
                        position, nationality
        );
        System.out.println("Details of player = " + playerOfCertainPosition);
    }

    @Test
    public void extractMapWithFindAllWithParameters() {
        String position = "Offence";
        String nationality = "England";

        Response response = get("teams/57");

        ArrayList<Map<String, ?>> allPlayerOfCertainPosition = response.path
                ("squad.findAll { it.position == '%s'}.findAll { it.nationality == '%s'}",
                        position, nationality
                );
        System.out.println("Details of player = " + allPlayerOfCertainPosition);
    }
}
