package com.ca.devops.response;

import java.io.Serializable;

public class GitInfo implements Serializable {

    private CommitInfo commitInfo;
    private String     branchName;

    public GitInfo(CommitInfo commitInfo, String branchName) {
        this.commitInfo = commitInfo;
        this.branchName = branchName;
    }

    public CommitInfo getCommitInfo() { return commitInfo; }
    public String     getBranchName() { return branchName; }

}
