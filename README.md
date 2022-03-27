1) npm run build   <- it will build angular client and put bundle to the resources/static
2) run locally with param -Xmx150m to limit max memory allocation    
3) open localhost:8080/site and click "Start infinite loop"
4) see console, there will be a counter
5) open jConcole and check memory usage
6) click to "perform GC", it can call alert in Dynatrace monitoring with "Long garbage-collection time"

----

PROD:
1) a deployed app https://dynatrace-demo.herokuapp.com/site
   health check: curl https://dynatrace-demo.herokuapp.com/health-check
   
2) docker pull dynatrace/oneagent
   // help: https://hub.docker.com/r/dynatrace/oneagent
   
command to run from docker image: 
   docker run -d
   --restart=unless-stopped
   --privileged=true
   --pid=host
   --net=host
   -v /:/mnt/root
   -e ONEAGENT_INSTALLER_SCRIPT_URL="https://fyj02592.live.dynatrace.com/api/v1/deployment/installer/agent/unix/default/latest?arch=x86&flavor=default"
   -e ONEAGENT_INSTALLER_DOWNLOAD_TOKEN=<token>
   dynatrace/oneagent <INSTALLER_PARAMETERS>