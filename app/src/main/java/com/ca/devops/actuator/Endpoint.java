package com.ca.devops.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import com.ca.devops.actuator.response.Info;
import com.ca.devops.actuator.response.BuildInfo;
import com.ca.devops.actuator.response.GitInfo;

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

    public Info hitAcceptPing(String url) {

        RestTemplate restTemplate = null;

        try {
            restTemplate = new RestTemplate();
            String pingResult = restTemplate.getForObject(url, String.class);

            return infoFromPing(pingResult);

        } catch (RestClientException e) {
            LOG.error("Could not hit url " + url + " : " + e.getMessage());
        }

        return null;
    }

    public Info hitAcceptServerStatus(String url) {

        RestTemplate restTemplate = null;

        try {
            restTemplate = new RestTemplate();
            String serverStatusResult = restTemplate.getForObject(url, String.class);

            return infoFromServerStatus(serverStatusResult);

        } catch (RestClientException e) {
            LOG.error("Could not hit url " + url + " : " + e.getMessage());
        }

        return null;
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


    /** Looks like this:
     * Server Name :tu-mtorig002-01.cac.com
     * Server No :0
     * Server Port :80
     * Logs URL :tu-mtorig002-01.cac.com:7777/logs
     *
     * Manifest-Version: 1.0
     * Archiver-Version: Plexus Archiver
     * Created-By: Apache Maven
     * Built-By: devops
     * Build-Jdk: 1.8.0_202
     * Implementation-Title: EContractClientWar
     * Implementation-Version: 1.2.431-SNAPSHOT
     * Implementation-Vendor-Id: com.cac.originations.caps
     * Implementation-Build: steps-econtract_05-02-2019_14:56:52
     * Implemented-By: devops
     */
    private Info infoFromServerStatus(String serverStatusResult) {

        Info      result    = new Info();
        BuildInfo buildInfo = new BuildInfo();

        for (String line : serverStatusResult.split("\n")) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String value = stripHtml(parts[1]);
                if ("Implementation-Title".equals(parts[0])) {
                    buildInfo.setArtifact(value);
                    buildInfo.setName(value);

                } else if ("Implementation-Version".equals(parts[0])) {
                    buildInfo.setVersion(value);

                } else if ("Implementation-Vendor-Id".equals(parts[0])) {
                    buildInfo.setGroup(value);
                }
            }
        }

        result.setBuild(buildInfo);
        return result;
    }


    private Info infoFromPing(String pingResult) {

        Info    result  = new Info();
        GitInfo gitInfo = new GitInfo();

        for (String line : pingResult.split("\\<br\\>")) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String value = stripHtml(parts[1]);
                if ("version.scm-branch".equals(parts[0])) {
                    gitInfo.setBranch(value);
                }
            }
        }

        result.setGit(gitInfo);
        return result;
    }

    private String stripHtml(String str) {
        return str.replaceAll("<br>", "");
    }

}
