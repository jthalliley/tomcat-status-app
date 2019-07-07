package com.jth.devops.response;

import java.io.Serializable;

public class StatusResponse implements Serializable {

    private ServerStatus[] serverStatuses;

    public StatusResponse(ServerStatus[] serverStatuses) {
        this.serverStatuses = serverStatuses;
    }

    public ServerStatus[] getServerStatuses() { return serverStatuses; }

}
