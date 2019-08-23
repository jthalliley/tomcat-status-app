package com.ca.devops.configuration;

public class Service {

    private String              environmentName;
    private String              projectId;
    private String              applicationName;
    private String              url;
    private ServiceResponseKind kind;

    public Service() {}

    public String              getEnvironmentName() { return environmentName; }
    public String              getProjectId()       { return projectId;       }
    public String              getApplicationName() { return applicationName; }
    public String              getUrl()             { return url;             }
    public ServiceResponseKind getKind()            { return kind;            }

    public void setEnvironmentName(String   environmentName) { this.environmentName = environmentName; }
    public void setProjectId(String         projectId)       { this.projectId       = projectId;       }
    public void setApplicationName(String   applicationName) { this.applicationName = applicationName; }
    public void setUrl(String               url)             { this.url             = url;             }
    public void setKind(ServiceResponseKind kind)            { this.kind            = kind;            }

}
