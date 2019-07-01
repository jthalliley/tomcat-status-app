package com.ca.devops.actuator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *  Response from hitting /actuator/info endpoint.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitInfo {

    private Long time; // millis since epoch of this commit
    private String id; // commit hash

    public CommitInfo() {}

    public Long   getTime() { return time; }
    public String getId()   { return id;   }

    public void setTime(Long time) { this.time = time; }
    public void setId(String id)   { this.id   = id;   }

}
