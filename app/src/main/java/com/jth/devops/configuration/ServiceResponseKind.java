package com.jth.devops.configuration;

public enum ServiceResponseKind {

    ACTUATOR_INFO,        // Spring Boot actuator info, health, etc. /actuator/info or /info
    HTTP_STATUS;          // Use HTTP status only

}
