import { BuildInfo } from './BuildInfo';
import { GitInfo   } from './GitInfo';

export class ServerStatus {

    environmentName: string;
    serverName:      string;
    applicationName: string;
    isResponding:    boolean;
    buildInfo:       BuildInfo;
    gitInfo:         GitInfo;

}
