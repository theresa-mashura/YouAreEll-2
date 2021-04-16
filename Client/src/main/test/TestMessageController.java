import controllers.IdController;
import controllers.MessageController;
import models.Id;
import models.Message;
import org.junit.Test;

import java.util.ArrayList;

public class TestMessageController {

    @Test
    public void testGetMessages() {
        // Given
        MessageController ic = new MessageController();

        // When
        ArrayList<Message> messageList = ic.getMessages();

        // Then
        System.out.println(messageList.get(0));
    }


}
