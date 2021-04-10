import controllers.ServerController;
import org.junit.Test;

import static controllers.ServerController.getInstance;

public class TestServerController {

    @Test
    public void testIdGet() {
        // Given
        ServerController sc = getInstance();

        // When
        String actualResponse = sc.get("/ids");

        // Then
        System.out.println(actualResponse);
    }

    @Test
    public void testIdPost() {
        // Given
        ServerController sc = getInstance();
        String jsonString = "{\"userid\": \"-\", \"name\": \"mountainDew\", \"github\" : \"sodaIsBadAnyway\"}";

        // When
        String actualResponse = sc.post("/ids", jsonString);

        // Then
        System.out.println(actualResponse);
    }

    @Test
    public void testIdPut() {
        // Given
        ServerController sc = getInstance();
        String jsonString = "{\"userid\": \"PUTREQUEST\", \"name\": \"DrPepper\", \"github\" : \"highfructosecornsyrup\"}";

        // When
        String actualResponse = sc.put("/ids", jsonString);

        // Then
        System.out.println(actualResponse);
    }

    @Test
    public void testMakeURLCall() {
        // Given
        ServerController sc = getInstance();

        // When
        String actualResponse = sc.MakeURLCall("/ids", ServerController.RequestType.GET, "");

        // Then
        System.out.println(actualResponse);
    }
}
