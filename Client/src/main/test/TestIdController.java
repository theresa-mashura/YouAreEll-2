import controllers.IdController;
import controllers.ServerController;
import models.Id;
import org.junit.Test;
import sun.jvm.hotspot.debugger.sparc.SPARCThreadContext;

import java.util.ArrayList;

public class TestIdController {

    @Test
    public void testGetIds() {
        // Given
        ServerController sc = ServerController.getInstance();
        IdController ic = new IdController();

        // When
        String jsonString = sc.get(ic.getPath());
        ArrayList<Id> idList = ic.getIds(jsonString);

        // Then
        System.out.println(idList.get(0));
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
