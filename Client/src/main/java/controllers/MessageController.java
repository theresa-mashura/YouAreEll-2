package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

public class MessageController {

    ServerController sc = ServerController.getInstance();
    private String path = "/messages";

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    public ArrayList<Message> getMessages(){
        String jsonResult = sc.MakeURLCall(this.path, ServerController.RequestType.GET, "");

        // Read Object List from JSON Array String using Jackson
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Message> messages = mapper.readValue(jsonResult, new TypeReference<ArrayList<Message>>() {});
            return messages;
        } catch (IOException i) {
            System.out.println("IOException in getMessages method");
            i.printStackTrace();
        }
        return null;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }

    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}
