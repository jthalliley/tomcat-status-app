package com.jth.devops.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import com.jth.devops.actuator.response.Info;
import com.jth.devops.actuator.response.BuildInfo;
import com.jth.devops.actuator.response.GitInfo;

public class Endpoint {

    private static final Logger LOG = LoggerFactory.getLogger(Endpoint.class);

    public Info hitAcceptJson(String url) {
        Info info = null;

        RestTemplate restTemplate = null;

        try {
            restTemplate = new RestTemplate();
            info = restTemplate.getForObject(url, Info.class);

            return info;

        } catch (RestClientException e) {
            LOG.error("Could not hit url " + url + " : " + e.getMessage());
        }

        return info;
    }

    public Info hitForHttpStatus(String url) {

        RestTemplate restTemplate = null;

        try {
            restTemplate = new RestTemplate();
            String textResult = restTemplate.getForObject(url, String.class);

            return new Info();

        } catch (RestClientException e) {
            LOG.error("Could not hit url " + url + " : " + e.getMessage());
        }

        return null;
    }


}
