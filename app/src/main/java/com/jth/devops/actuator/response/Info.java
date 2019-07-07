package com.jth.devops.actuator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *  Response from hitting /actuator/info endpoint.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {

    private GitInfo   git;
    private BuildInfo build;

    public Info() {}

    public GitInfo   getGit()   { return git;   }
    public BuildInfo getBuild() { return build; }

    public void setGit(GitInfo     git)   { this.git   = git;   }
    public void setBuild(BuildInfo build) { this.build = build; }

}
