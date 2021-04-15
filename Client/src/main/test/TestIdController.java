import controllers.IdController;
import models.Id;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestIdController {

    @Test
    public void testGetIds() {
        // Given
        IdController ic = new IdController();

        // When
        ArrayList<Id> idList = ic.getIds();

        // Then
        System.out.println(idList.get(0));
    }

    @Test
    public void testAddToMap() {
        // Given
        IdController ic = new IdController();

        // When
        ic.addToMap();
        HashMap<String, Id> idMap = ic.getAllIds();

        // Then
        System.out.println(idMap.get("highfructosecornsyrup"));
    }

    @Test
    public void testWriteJsonToString() {
        // Given
        Id id = new Id ("TheresaId", "Theresa", "GitTheresa");
        IdController ic = new IdController();

        // When
        String jsonString = ic.writeJsonToString(id);

        // Then
        System.out.println(jsonString);
    }

    @Test
    public void testPostId() {
        // Given
        Id idGiven = new Id ("ZeusId", "Zeus", "GitZeus");
        IdController ic = new IdController();

        // When
        Id id = ic.postId(idGiven);

        // Then
        System.out.println(id);
    }

    @Test
    public void testPutId() {
        // Given
        Id idGiven = new Id ("AthenaId", "Athena", "GitAthena");
        IdController ic = new IdController();

        // When
        Id id = ic.putId(idGiven);

        // Then
        System.out.println(id);
    }
}
