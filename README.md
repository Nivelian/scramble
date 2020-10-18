# Scramblies challenge

## Description

Project consists of 2 directories:
- **fe** - Frontend part. Written using **shadow-cljs**  + **reagent**.  
Run `npm run release` to build static files to ../be/resources/public folder.
Server will serve them from that place.
- **be** - Backend part. Written using **ring** + **compojure**.  
Run `lein test` to apply tests for scramble function.  
Run `lein uberjar` to build backend jar file and `java -jar target/uberjar/be-0.1.0-SNAPSHOT-standalone.jar`
to start web server. Then navigate to <http://localhost:8080>