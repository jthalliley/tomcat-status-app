package com.ca.devops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ca.devops.response.StatusResponse;
import com.ca.devops.core.Reacher;

@Controller
public class StatusController {

    @Autowired Reacher reacher;

    @GetMapping("/statuses")
    @ResponseBody
    public StatusResponse getStatuses() {
        return reacher.fetchStatuses();
    }

}
