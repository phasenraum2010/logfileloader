logfileloader
-------------

Loads Logfiles from HTTP Share at Webserver und pushes them to Database.

Runtime
-------

Runs on cloud Services like cloudfoundry.com or on a local tomcat servlet container.

Runtime Dependencies
--------------------

Uses MySQL as local Database and MongoDB as Message Store Service for EAI.

Install
-------

Setup MySQL
-----------

CREATE USER 'logfileloader'@'localhost' IDENTIFIED BY 'logfileloaderpwd';

GRANT SELECT, INSERT, UPDATE, DELETE,CREATE, DROP,FILE,INDEX,ALTER, CREATE TEMPORARY TABLES, CREATE VIEW, EVENT, TRIGGER, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EXECUTE ON * . * TO 'logfileloader'@'localhost' IDENTIFIED BY 'logfileloaderpwd' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;

CREATE DATABASE IF NOT EXISTS `logfileloader` ;

GRANT ALL PRIVILEGES ON `logfileloader` . * TO 'logfileloader'@'localhost';

GRANT ALL PRIVILEGES ON `logfileloader\_%` . * TO 'logfileloader'@'localhost';

Run
---

mvn clean install tomcat7:run
