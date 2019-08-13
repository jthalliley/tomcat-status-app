package com.ca.devops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ca.devops.actuator.InternalException;
import com.ca.devops.configuration.ServiceNotFoundException;
import com.ca.devops.core.Reacher;
import com.ca.devops.response.ServerStatus;
import com.ca.devops.response.StatusResponse;

@Controller
public class StatusController {

    @Autowired Reacher reacher;

    @GetMapping("/statuses")
    @ResponseBody
    public StatusResponse getStatuses() {

        try {
            return reacher.fetchStatuses();

        } catch (ServiceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

        } catch (InternalException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping("/status/{environmentName}/{applicationName}")
    @ResponseBody
    public ServerStatus getStatus(@PathVariable String environmentName, @PathVariable String applicationName) {

        try {
            return reacher.fetchStatus(environmentName, applicationName);

        } catch (ServiceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

        } catch (InternalException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }


}
