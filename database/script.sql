-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;


-- ************************************** `user`
CREATE DATABASE IF NOT EXISTS eventsDb;
USE eventsDb;

CREATE TABLE `user`
(
 `id`   integer NOT NULL AUTO_INCREMENT ,
 `nume` varchar(45) NOT NULL ,
 PRIMARY KEY (`id`)
);


-- ************************************** `event`

CREATE TABLE `event`
(
 `ID`          varchar(45) NOT NULL ,
 `location`      varchar(45) NOT NULL ,
 `artist` varchar(45) NOT NULL ,
 `beginh`  integer NOT NULL ,
 `begind`  integer NOT NULL ,
 `duration`        integer NOT NULL ,
 `seats`       integer NOT NULL ,
 PRIMARY KEY (`ID`)
);


-- ************************************** `ticket`

CREATE TABLE `ticket`
(
 `id`   integer NOT NULL AUTO_INCREMENT ,
 `user` integer NOT NULL ,
 PRIMARY KEY (`id`),
 KEY `fkIdx_27` (`user`),
 CONSTRAINT `FK_27` FOREIGN KEY `fkIdx_27` (`user`) REFERENCES `user` (`id`)
);


-- ************************************** `reservation`

CREATE TABLE `reservation`
(
 `id`     integer NOT NULL AUTO_INCREMENT ,
 `user`   integer NOT NULL ,
 `events` varchar(45) NOT NULL ,
 PRIMARY KEY (`id`)
);

-- ************** `hasRes`

CREATE TABLE `hasRes`
(
 `id`          integer NOT NULL AUTO_INCREMENT ,
 `ticket`      integer NOT NULL ,
 `reservation` integer NOT NULL ,
 PRIMARY KEY (`id`),
 KEY `fkIdx_36` (`ticket`),
 CONSTRAINT `FK_36` FOREIGN KEY `fkIdx_36` (`ticket`) REFERENCES `ticket` (`id`),
 KEY `fkIdx_39` (`reservation`),
 CONSTRAINT `FK_39` FOREIGN KEY `fkIdx_39` (`reservation`) REFERENCES `reservation` (`id`)
);