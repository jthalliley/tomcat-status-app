import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from "ngx-spinner";

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

    environments: string[] = ['dev', 'test1', 'test2', 'test3', 'test4', 'test5'];
    statuses:     Map<string, ServerStatus[]>;

    activeEnvironment: string = this.environments[0];

    constructor(private statusService: StatusService,
                private spinner:       NgxSpinnerService) { }

    ngOnInit() {
        this.statusService.change.subscribe(statusResponse => {
            this.statusResponse = this.sortStatuses(statusResponse);

            this.splitStatuses();
            this.spinner.hide();
        });

        this.refreshView();
    }

    public refreshView() {
        this.spinner.show();
        this.statusService.fetchStatus();
        this.lastUpdatedTime = Date.now();
    }

    public onTabClick(env: string) {
        console.log(`clicked on ${env}`);
        this.activeEnvironment = env;
    }

    public onRestartClick(serverStatus: ServerStatus) {
        console.log(`clicked on ${serverStatus.environmentName} ${serverStatus.applicationName}`);
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

    private splitStatuses(): void {
        this.statuses = new Map();
        for (let env of this.environments) {
            let statusList = this.statusResponse.serverStatuses
                .filter( status => { return status.environmentName === env; } )
            ;
            this.statuses.set(env, statusList);
        }
    }

}
