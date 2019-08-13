package com.ca.devops.response;

import java.io.Serializable;

public class CommitInfo implements Serializable {

    private String time; // in Milliseconds since Epoch
    private String id;   // commit hash

    public CommitInfo(String time, String id) {
        this.time = time;
        this.id   = id;
    }


    public String getTime() { return time; }
    public String getId()   { return id;   }

}
