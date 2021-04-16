import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import org.junit.Test;
import youareell.YouAreEll;

public class TestYouAreEll {

    @Test
    public void testGetIds() {
        // Given
        YouAreEll url = new YouAreEll(new TransactionController(new MessageController(), new IdController()));

        // When
        String actualIds = url.get_ids();

        // Then
        System.out.println(actualIds);

    }
}
