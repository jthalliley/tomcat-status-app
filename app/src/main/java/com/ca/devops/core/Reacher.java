package com.ca.devops.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.devops.response.StatusResponse;
import com.ca.devops.response.ServerStatus;
import com.ca.devops.response.BuildInfo;
import com.ca.devops.response.GitInfo;
import com.ca.devops.response.CommitInfo;

import com.ca.devops.actuator.Endpoint;
import com.ca.devops.actuator.response.Info;

import com.ca.devops.configuration.Service;
import com.ca.devops.configuration.ServiceDefinitions;
import com.ca.devops.configuration.ServiceResponseKind;

@org.springframework.stereotype.Service
public class Reacher {

    private static final Logger LOG = LoggerFactory.getLogger(Reacher.class);

    @Autowired private ServiceDefinitions serviceDefinitions;


    public StatusResponse fetchStatuses() {
        List<ServerStatus> statusList = new ArrayList<>();

        if (serviceDefinitions == null) {
            LOG.error("Service definitions were not injected.");

        } else {
            for (Service service : serviceDefinitions.getServices()) {
                ServerStatus thisStatus = convertToServerStatus(service, hitEndpoint(service));
                statusList.add(thisStatus);
            }
        }

        return new StatusResponse(statusList.toArray(new ServerStatus[statusList.size()]));
    }

    private Info hitEndpoint(Service service) {
        Endpoint endpoint = new Endpoint();
        Info     info     = null;

        switch (service.getKind()) {
        case PING:
            info = endpoint.hitAcceptPing(service.getUrl());
            break;
        case ACTUATOR_INFO:
            info = endpoint.hitAcceptJson(service.getUrl());
            break;
        case SERVER_STATUS:
            info = endpoint.hitAcceptServerStatus(service.getUrl());
            break;
        case HTTP_STATUS:
            info = endpoint.hitForHttpStatus(service.getUrl());
            break;
        default:
        }

        return info;
    }

    private ServerStatus convertToServerStatus(Service service, Info info) {
        ServerStatus result = null;

        if (info != null) {
            BuildInfo buildInfo = info.getBuild() != null
                ? new BuildInfo(info.getBuild().getVersion(),
                                info.getBuild().getArtifact(),
                                info.getBuild().getName(),
                                info.getBuild().getGroup(),
                                info.getBuild().getTime())
                : null;

            CommitInfo commitInfo = info.getGit() != null && info.getGit().getCommit() != null
                ? new CommitInfo(info.getGit().getCommit().getTime(),
                                 info.getGit().getCommit().getId())
                : null;

            GitInfo gitInfo = info.getGit() != null
                ? new GitInfo(commitInfo, info.getGit().getBranch())
                : null;

            result = new ServerStatus(service.getEnvironmentName(),
                                      service.getServerName(),
                                      service.getApplicationName(),
                                      true, buildInfo, gitInfo);
        } else {
            result = new ServerStatus(service.getEnvironmentName(),
                                      service.getServerName(),
                                      service.getApplicationName(),
                                      false, null, null);
        }

        return result;
    }

}
