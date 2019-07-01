import { EventEmitter, Injectable, Output      } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

import { environment } from '../../environments/environment';

import { StatusResponse } from '../classes/StatusResponse';

const HTTP_OPTIONS = {
    headers: new HttpHeaders({
        'Accept': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class StatusService {

    constructor(private http: HttpClient) {}


    private statusResponse: StatusResponse;

    @Output() change: EventEmitter<StatusResponse> = new EventEmitter();


    private getStatus(): Observable<StatusResponse> {

        let url: string = `http://${environment.host}/statuses`;

        return this.http
            .get(url, HTTP_OPTIONS)
            .map(response => {
                const result: StatusResponse = new StatusResponse(response);
                return result;
            })
            .catch(response => {
                console.log("getStatus failed!!!");
                if (!environment.production) {
                    return Observable.throw(response);
                }
            });
    }

    public fetchStatus(): void {
        this.getStatus().subscribe(statusResponse => {
            this.statusResponse = statusResponse;
            this.change.emit(this.statusResponse);
        });
    }

}
