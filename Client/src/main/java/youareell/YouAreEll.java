package youareell;

import controllers.*;
import models.Id;
import java.util.List;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public static void main(String[] args) {
         // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(new MessageController(), new IdController())
        );
        //System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        //System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String get_ids() {
        //return tt.makecall("/ids", "GET", "");
        List<Id> ids = tt.getIds();
        String idString = "LIST OF ALL IDS\n FORMAT: NAME (GIT_ID)\n---------------------------\n";
        for (Id id : ids) {
            idString += id.toString() + "\n";
        }
        return idString;
    }

    public String post_id(String name, String github) {
        return tt.postId(name, github);
    }

    public String put_id(String name, String github) {
        return tt.putId(name, github);
    }


    public String get_messages() {
        //return MakeURLCall("/messages", "GET", "");
        return null;
    }


}
