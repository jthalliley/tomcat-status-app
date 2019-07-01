package com.ca.devops.response;

import java.io.Serializable;

public class CommitInfo implements Serializable {

    private Long   time; // in Milliseconds since Epoch
    private String id;   // commit hash

    public CommitInfo(Long time, String id) {
        this.time = time;
        this.id   = id;
    }


    public Long   getTime() { return time; }
    public String getId()   { return id;   }

}
