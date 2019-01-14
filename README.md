# Robot
Robot that search url in database which is unknown.
Database contain table where store url, date, http status of url. 
User should enter date in format "yyyy-MM-dd". Then Robot check url with such date and later.
And write to database http status of url.

Configuration
===============
File config.properties should write to indicate DataBase driver, url, login and password.

Default values
===============
In config file already written default values. Used postgresql driver to develop and debug.

Instruments of development
============================
IntelliJ IDEA Community Edition 2018.3.2 x64
Java™ SE Development Kit 8, Update 191 (JDK 8u191)
PostgreSQL JDBC Driver JDBC 4.2
JUnit 4
Maven

Author
================================
Luiza Kazbekova
