package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

public class IdController {
    private HashMap<String, Id> allIds;
    private String path = "/ids";
    ServerController sc = ServerController.getInstance();

    public String getPath() {
        return this.path;
    }

    public HashMap<String, Id> getAllIds() {
        return this.allIds;
    }

    public void addToMap() {
        allIds = new HashMap<String, Id>();
        for (Id id : this.getIds()) {
            allIds.put(id.getGithub(), id);
        }
    }

    public ArrayList<Id> getIds() {
        String jsonResult = sc.MakeURLCall(this.path, ServerController.RequestType.GET, "");

        // Read Object List from JSON Array String using Jackson
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Id> ids = mapper.readValue(jsonResult, new TypeReference<ArrayList<Id>>() {
            });
            return ids;
        } catch (IOException i) {
            System.out.println("IOException in getIds method");
            i.printStackTrace();
        }
        return null;
    }

    public String writeJsonToString(Id id) {
        try {
            ObjectMapper om = new ObjectMapper();
            return om.writeValueAsString(id);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("jackson.core.JsonProcessingException in writeJson()");
        }
        return null;
    }

    public Id writeJsonToObject(String jsonString) {
        try {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, new TypeReference<Id>() {
        });
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.out.println("jackson.core.JsonProcessingException in writeJson()");
        }
        return null;
    }

    public Id postId(Id id) {
        String idAsJson = this.writeJsonToString(id);
        String jsonResult = sc.MakeURLCall(this.path, ServerController.RequestType.POST, idAsJson);
        return this.writeJsonToObject(jsonResult);
    }

    public Id putId(Id id) {
        String idAsJson = this.writeJsonToString(id);
        String jsonResult = sc.MakeURLCall(this.path, ServerController.RequestType.PUT, idAsJson);
        return this.writeJsonToObject(jsonResult);
    }
 
}