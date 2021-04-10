package models;

/* 
 * POJO for an Id object
 */
public class Id {
    private String userid = "";
    private String name = "";
    private String github = "";

    public Id () {

    }

    public Id (String userid, String name, String github) {
        this.userid = userid;
        this.name = name;
        this.github = github;
    }

    public Id (String name, String githubId) {
        this.name = name;
        this.github = githubId;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {this.userid = userid;
    }

//    public void setUid(String uid) {
//        this.uid = uid;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.github + ") ";
    }
}