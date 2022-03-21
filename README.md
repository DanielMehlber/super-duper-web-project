<h1 align="center">Super-Duper-Web-Projekt</h1>

# Project Description
This is our super-duper-web-project we're going to develop for our university task.

# Installation

## Using Docker (recommended, fast lane)

### Requirements

You will need
* JDK 11
* docker (cli)
* docker-compose (cli)
* maven
* network connection (for docker image pulls)

If you're using a debian-based Linux disto, these can be installed using
```shell
sudo apt install maven docker docker-compose openjdk-11-jdk
```
**...Well, isn't that convenient?** Greetings to all Windows users out there :)

### Deployment

```shell
# 1) build and package .war files
mvn package

# 2) build docker container (containing .war file deployed to wildfly)
docker-compose build

# 3) fire up containers (including database and database setup)
docker-compose up -d

# ...Application is now fully deployed and running...

# 4) shut down containers and application
docker-compose down
```

## Manual deployment (if you insist...)

### 0. Requirements

You will need
* JDK 11
* maven
* installed and running wildfly server
* installed and running MySQL DBMS

### 1. Setup database
In order to work with this application's database schema, you'll have to create it first.

`src/main/resources/schema.sql` will create this empty schema, run it.

### 2. Build war file
In order to compile, test and package the project execute
```shell
mvn package
```

The output's war-file will be located in the `target` directory.

### 3. Deploy war file
Rename the war file to `ROOT.war` and place it into `{wildfly-location}/standalone/deployments/ROOT.war`.
