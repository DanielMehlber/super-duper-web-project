<h1 align="center">Super-Duper-Web-Projekt</h1>

# Project Description
This is our super-duper-web-project we're going to develop for our university task.
 **Main Goal** Outmatch Matthias Raimann and his team by any means

# Installation

## Using Docker (recommended, fast lane)

### What is Docker?
**Docker** is a tool used to create and run containers (isolated and easy-to-manage boxes
containing a software/service/server). Containers are running in their own virtual machine
and therefore each container has
* its own file system
* its own processes
* its own ports
* its own environment variables
* and more

In a nutshell: They don't "touch" your private system and allow a "clean" and convenient 
solution for quick startup, deployment, shutdown. More advantages are:
* higher security (e.g. ports must be exposed by developer)
* comfortability for developers (e.g. single command start, stop, install, delete, ...)
* better "waste management": containers can be discarded/uninstalled easily
* containers can be orchestrated (connected and managed as a collective)

BUT: Docker can only manage 1 Container at once. If you want to manage an entire system
of containers (e.g. application server + application + database) you'll need **docker-compose**

Docker Compose is used to fire-up or shut-down multiple containers at once in order to 
"unfold" an entire interconnected system of containers with a single command.

In our project it is used for deployment (starting a fresh server and database with a single command)
and testing purposes.

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
# 0) make sure you are in the project root folder
cd [...]/super-duper-web-development

# 1) compile, build and package .war file
mvn package

# 2) build docker containers (containing .war file deployed in wildfly)
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

# Testing (Unit & Integration)
## How it normally works
In order to test our functionality we have to use unit testing:
```shell
# this command runs all unit tests of this project using maven
mvn test
```

**BUT**: We are working with databases and we need them in order to test the functionality.

**PROBLEM**: How do we setup our databases not just locally, but also on our remote repository on 
GitHub (for CI/CD) in an automated and convenient manner?

There are multiple solutions to this problem (we only use one of them):
1) ~~Install database locally~~ (manual solution without unified database configuration)
2) ~~Mock database and its data~~, a.k.a _not using a database at all_ (requires more testing-logic, quick and dirty)
3) **Start containerized database with automatic configuration and setup** (clean and automated solution)

## The solution we use
In order to test our processes, we need our environment containers (databases, etc) up and running.
For this purpose there is a separate `docker-compose-environment.yml`.

The `docker-compose-environment.yml` sets up all environment containers 
(the `docker-compose.yml` file sets up everything).

```shell
# 1) start test database and setup schema
docker-compose -f docker-compose-environment.yml up -d

# 2) run all unit tests of this project
mvn test 

# 3) shut down test database
docker-compose -f docker-compose-environment.yml down
```

# Configuration
## Wildfly configuration
In order to configure wildfly you can edit `wildfly-config/standalone.xml` (e.g. datasources).

This configuration file will replace the default configuration file of wildfly in the docker container.
