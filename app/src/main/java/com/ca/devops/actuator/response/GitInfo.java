package com.ca.devops.actuator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *  Response from hitting /actuator/info endpoint.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitInfo {

    private CommitInfo commit;
    private String branch; // branchName

    public GitInfo() {}

    public CommitInfo getCommit() { return commit; }
    public String     getBranch() { return branch; }

    public void setCommit(CommitInfo commit)  { this.commit = commit; }
    public void setBranch(String     branch)  { this.branch = branch; }

}
