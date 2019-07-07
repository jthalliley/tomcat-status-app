package com.jth.devops.configuration;

public class Service {

    private String              environmentName;
    private String              serverName;
    private String              applicationName;
    private String              url;
    private ServiceResponseKind kind;

    public Service() {}

    public String              getEnvironmentName() { return environmentName; }
    public String              getServerName()      { return serverName;      }
    public String              getApplicationName() { return applicationName; }
    public String              getUrl()             { return url;             }
    public ServiceResponseKind getKind()            { return kind;            }

    public void setEnvironmentName(String   environmentName) { this.environmentName = environmentName; }
    public void setServerName(String        serverName)      { this.serverName      = serverName;      }
    public void setApplicationName(String   applicationName) { this.applicationName = applicationName; }
    public void setUrl(String               url)             { this.url             = url;             }
    public void setKind(ServiceResponseKind kind)            { this.kind            = kind;            }

}
