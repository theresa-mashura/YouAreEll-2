package controllers;

import models.Id;

import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {}

    public List<Id> getIds() {
        return idCtrl.getIds();
    }
    public String postId(String idToRegister, String gitHubName) {
        Id tid = new Id(idToRegister, gitHubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }
}
