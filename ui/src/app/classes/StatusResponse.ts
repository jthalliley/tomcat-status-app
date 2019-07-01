import { ServerStatus } from './ServerStatus';

export class StatusResponse {

    serverStatuses: ServerStatus[];

    constructor(value: Object = {}) {
        Object.assign(this, value);
    }

}
