package com.jth.devops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jth.devops.response.StatusResponse;
import com.jth.devops.core.Reacher;

@Controller
public class StatusController {

    @Autowired Reacher reacher;

    @GetMapping("/statuses")
    @ResponseBody
    public StatusResponse getStatuses() {
        return reacher.fetchStatuses();
    }

}
