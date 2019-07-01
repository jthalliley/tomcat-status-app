package com.ca.devops.configuration;

public enum ServiceResponseKind {

    PING,            // CA-invented PING page
    ACTUATOR_INFO,   // Spring Boot actuator info, health, etc.
    SERVER_STATUS,   // CA-invented server status page
    HTTP_STATUS;     // Use HTTP status only

}
