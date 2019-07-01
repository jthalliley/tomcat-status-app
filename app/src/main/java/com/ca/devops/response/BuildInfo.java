package com.ca.devops.response;

import java.io.Serializable;

public class BuildInfo implements Serializable {

    private String version;
    private String artifact;
    private String name;
    private String group;
    private Long   time;

    public BuildInfo(String version, String artifact, String name, String group, Long time) {
        this.version  = version;
        this.artifact = artifact;
        this.name     = name;
        this.group    = group;
        this.time     = time;
    }

    public String getVersion()  { return version;  }
    public String getArtifact() { return artifact; }
    public String getName()     { return name;     }
    public String getGroup()    { return group;    }
    public Long   getTime()     { return time;     }

}
