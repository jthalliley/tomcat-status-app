# UI RESTful Services

This app provides RESTful services to the UI.  All of the pinging of
services happens in this app, and the result of which is provided to
the UI.

## Endpoints

There are 2 endpoints provided here:

1. Used by the UI, ```/statuses``` fetches the status for all
   apps/services listed in the Services Definition (see below) at
   once, and returns such to the UI.

2. ```/status/{environment}/{applicationName}``` fetches the status
   for the one environment/application name provided, and returns that
   status to the caller.  Currently, this is used by Jenkins deploy
   jobs to display up/down status of apps just deployed.

## Services Definition

Currently, there's a ```server-definitions.json``` file that defines
all of the services to be checked.  This may or may not be a good
idea, as it's currently built into the app.  If we can get it off of
the classpath, sitting on disk next to the app, this would be
preferable.

### Works with various kinds of Services

At CA, our services produce different kinds of outputs, as we've not
created an up-to-date standard to follow (nor have older apps been
upgraded).

Here's what is supported by this app:

| ServiceResponseKind | Description                             |
|---------------------|-----------------------------------------|
| PING                | CA-invented ping page                   |
| ACTUATOR_INFO       | Spring Boot actuator info, health, etc. |
| SERVER_STATUS       | CA-invented server status page          |
| SERVER_INFO         | CA-invented server info page            |
| HTTP_STATUS         | Use HTTP status code only               |

#### PING Response Format

```
PING!<br><br>version.built-by: devops<br><br>version.built-os: Linux<br><br>version.build-date: 2019-05-31T16:24:08-0400<br><br>version.scm-revision: contractInterface-1.1.76<br><br>version.scm-branch: deploy_next<br><br>JMS Connection: Not Applicable<br><br>DB Connection: Passed
```

#### ACTUATOR_INFO Response Format

```
{"build":{"version":"4.5.8","artifact":"VehicleValueGateway","name":"VehicleValueGateway","group":"com.cac.originations","time":"2019-08-08T13:47:39.266Z"},"camel.name":"camel-1","camel.version":"2.23.0","camel.uptime":"1 hour 41 minutes","camel.uptimeMillis":6082983,"camel.status":"Started"}
```

#### SERVER_STATUS Response Format

```
Server Name :tu-mtorig001-01.cac.com<br>
Server No :0<br>
Server Port :80<br>
Logs URL :tu-mtorig001-01.cac.com:7777/logs<br><br>
<HTML>
Manifest-Version: 1.0<br>
Archiver-Version: Plexus Archiver<br>
Created-By: Apache Maven<br>
Built-By: devops<br>
Build-Jdk: 1.7.0_80<br>
Implementation-Title: econtractclient-war<br>
Implementation-Version: 4.22.53<br>
Implementation-Vendor-Id: com.cac.originations<br>
Implementation-Build: steps-econtract_08-06-2019_11:47:42<br>
Implemented-By: devops<br>
</HTML>
```

#### SERVER_INFO Response Format

Seems to be unused.


#### HTTP_STATUS Response Format

Here we interpret a 200 to be ``up``, and anything else to be ``down``.


## To build this app

```bash
./build.sh
```

## To run this app

```bash
java -jar target/*.war
```
