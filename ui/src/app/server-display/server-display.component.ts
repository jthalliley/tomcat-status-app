import { Component, OnInit } from '@angular/core';

import { StatusResponse } from '../classes/StatusResponse';
import { ServerStatus   } from '../classes/ServerStatus';
import { StatusService  } from '../services/status.service';

@Component({
  selector: 'server-display',
  templateUrl: './server-display.component.html',
  styleUrls: ['./server-display.component.scss']
})
export class ServerDisplayComponent implements OnInit {

    lastUpdatedTime: number;
    statusResponse:  StatusResponse;

    environments:    string[] = ['dev', 'test1', 'test2', 'test3', 'test4', 'test5'];


    constructor(private statusService: StatusService) { }

    ngOnInit() {
        this.statusService.change.subscribe(statusResponse => {
            this.statusResponse = this.sortStatuses(statusResponse);
        });

        this.refreshView();
    }

    public refreshView() {
        this.statusService.fetchStatus();
        this.lastUpdatedTime = Date.now();
    }

    private sortStatuses(statusResponse: StatusResponse): StatusResponse {
        let result: StatusResponse = statusResponse;

        result.serverStatuses = statusResponse.serverStatuses
            .sort( (left, right) =>
                   (left.environmentName + left.applicationName) < (right.environmentName + right.applicationName)
                   ? -1
                   : (left.environmentName + left.applicationName) === (right.environmentName + right.applicationName)
                   ? 0
                   : +1
                 )
        ;

        return result;
    }

}
