package com.ca.devops.response;

import java.io.Serializable;

public class ServerStatus implements Serializable {

    private String    environmentName;
    private String    serverName;
    private String    applicationName;
    private Boolean   isResponding;
    private BuildInfo buildInfo;
    private GitInfo   gitInfo;

    public ServerStatus(String environmentName, String serverName, String applicationName, Boolean isResponding,
                        BuildInfo buildInfo, GitInfo gitInfo) {
        this.environmentName = environmentName;
        this.serverName      = serverName;
        this.applicationName = applicationName;
        this.isResponding    = isResponding;
        this.buildInfo       = buildInfo;
        this.gitInfo         = gitInfo;
    }

    public String    getEnvironmentName() { return environmentName; }
    public String    getServerName()      { return serverName;      }
    public String    getApplicationName() { return applicationName; }
    public Boolean   getIsResponding()    { return isResponding;    }
    public BuildInfo getBuildInfo()       { return buildInfo;       }
    public GitInfo   getGitInfo()         { return gitInfo;         }

}
