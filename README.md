# TomcatStatusApp

This is a simple SpringBoot app,  where all services are pinged from this server app, and results are then made available to the UI.

## ui directory

[ui directory](./ui/README.md)

## app directory

[app directory](./app/README.md)

# To build the Production app

```bash
./ui/build.sh
./app/build.sh
```

# To run the Production app

```bash
./run.sh
```

and go to http://localhost:3000 in your browser.

# To monitor this app

Go to http://localhost:3000/actuator/info or http://localhost:3000/actuator/health in your browser.


# TO DO List

1. Change API to something that hits the URLs asynchronously, and returns results asynchronously.

   * Could do this by having 1 call to get list of apps to be displayed (minimal data), then calling back end with each app individually and asynchronously to get results back asynchronously.

2. Make boxes smaller (narrower) to fit all on screen at once.
   * Could do by columns per environment.
