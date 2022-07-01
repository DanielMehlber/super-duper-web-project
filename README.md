# Installation
## Using Docker
In order to use docker you'll need
* `docker` and `docker-compose` command line tools
* `maven` for build (optional)
* an internet connection
* the **source-code package** (not only the war file)

Please shut down any database or running wildfly instance to avoid port conflicts.

**On Windows** make sure that docker is running by starting "Docker Desktop".

### Option 1: Container with demo data
First download the SQL Script from https://drive.google.com/file/d/1xgm1NpjNFq4mnnyVld7DkPKZ3G2Q50l0/view?usp=sharing
Place it in the **project directory** (where the docker files and the `README.md` are located) of this project like this `./db_dump.sql`. The 
container will use this database image at startup, so it does not work without it.

In order to start the software (incl. database schema filled with demo-data) just call
```shell
# 1) compile the .war file without running unit tests (optional)
# Other option: place downloaded ROOT.war into ./target directory
mvn package -DskipTests

# 2) build containers
docker-compose build

# 3) start containers (application + db)
docker-compose up -d

# Do stuff... The application is running

# 4) stop all containers (application + db)
docker-compose down
```
* Instead of Step 1, you can also place the downloaded `ROOT.war` file into the `target` folder inside the project directory (in case you don't want to use maven). You may have to create this directory first.
* In Step 2 the container will download:
  * mysql image
  * wildfly server (wildfly-preview-26.1.0.Final)
  * mysql-connector for JDBC
  * database setup script (from google drive)

### Option 2: Containers without any data (empty schema, no demo data)
In order to start the software (incl. database, but without demo data) just call
```shell
# 1) compile the .war file without running unit tests (optional)
# Other option: place downloaded ROOT.war into ./target directory
mvn package -DskipTests

# 2) build containers
docker-compose -f docker-compose-empty.yml build

# 3) start containers (application + db)
docker-compose -f docker-compose-empty.yml up -d

# Do stuff... The application is running

# 4) stop all containers (application + db)
docker-compose -f docker-compose-empty.yml down
```

### Manual Installation

Download the following:
* ROOT.war (E-Mail)
* The SQL Setup Script [here](https://drive.google.com/file/d/1xgm1NpjNFq4mnnyVld7DkPKZ3G2Q50l0/view?usp=sharing)

1. Start your Wildfly and database
2. create a new JNDI datasource on your wildfly called `java:jboss/datasources/eSportsDS`
3. run `./src/main/resources/schema.sql` on your SQL server (to establish an empty schema)
4. run `./db_dump.sql` on your SQL server (skip this step if you want a clean installation)
5. place the `ROOT.war` file in your `deployments` folder (Wildfly)
6. Start the application


# Usage

The server is online on `http://localhost` (on port `80`). Just navigate to http://localhost in order to get to the login page.

To login as admin user use username `admin` with password `admin123`. This enables you to
* delete user
* add games
* edit games
* delete games
* delete teams