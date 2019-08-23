package com.ca.devops.actuator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *  Response from hitting /actuator/info endpoint.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildInfo {

    private String version;
    private String artifact; // artifactId
    private String name;
    private String group;    // groupId
    private String time;

    public BuildInfo() {}

    public String getVersion()  { return version;  }
    public String getArtifact() { return artifact; }
    public String getName()     { return name;     }
    public String getGroup()    { return group;    }
    public String getTime()     { return time;     }

    public void setVersion(String  version)  { this.version  = version;  }
    public void setArtifact(String artifact) { this.artifact = artifact; }
    public void setName(String     name)     { this.name     = name;     }
    public void setGroup(String    group)    { this.group    = group;    }
    public void setTime(String     time)     { this.time     = time;     }

}
