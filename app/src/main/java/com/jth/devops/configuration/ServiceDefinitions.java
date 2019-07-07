package com.jth.devops.configuration;

import java.io.IOException;
import java.net.URL;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Service;

@org.springframework.stereotype.Service
public class ServiceDefinitions {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceDefinitions.class);

    private static final String SERVICE_DEFINITIONS = "service-definitions.json";

    private Service[] services;

//    @Bean public ServiceDefinitions getServiceDefinitions() {
//        return new ServiceDefinitions();
//    }

    private ServiceDefinitions() {}

    public Service[] getServices() { return services; }

    @PostConstruct
    public void load() {
        try {
            URL url = getClass().getClassLoader().getResource(SERVICE_DEFINITIONS);

            ObjectMapper mapper = new ObjectMapper();
            Definition def = mapper.readValue(url, Definition.class);
            services = def.getServices();

        } catch (IOException e) {
            LOG.error("Could not read service definitions.", e);
        }
    }

}
