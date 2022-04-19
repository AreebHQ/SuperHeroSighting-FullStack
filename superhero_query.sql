DROP DATABASE IF EXISTS superhero;
CREATE DATABASE superhero;
USE superhero;

DROP TABLE IF EXISTS supermember;
CREATE TABLE supermember(
memberId INT PRIMARY KEY AUTO_INCREMENT,
name varchar(50),
description varchar(500),
superpower varchar(50)
);

INSERT INTO supermember(name,description,superpower) VALUES("SuperMan","Guy with red cape","Flying");
INSERT INTO supermember(name,description,superpower) VALUES("BatMan","Guy with Bat suit","Money");
INSERT INTO supermember(name,description,superpower) VALUES("Captian America","Guy with star shield","Sheild");
INSERT INTO supermember(name,description,superpower) VALUES("SpiderMan","Guy with spider power","Spider Webs");
SELECT * FROM supermember;
DELETE FROM supermember WHERE memberId =43;
SELECT * FROM supermember WHERE memberId = 3;

DROP TABLE IF EXISTS organization;
CREATE TABLE organization(
organizationId INT PRIMARY KEY AUTO_INCREMENT,
name varchar(50),
description varchar(500),
street varchar(50),
city varchar(50),
state varchar(50)
);

INSERT INTO organization(name,description,street,city,state) VALUES("Avengers","When Thor's evil brother, Loki gains access to the unlimited power of the energy 
cube called the Tesseract, Nick Fury, director of S.H.I.E.L.D., initiates a superhero recruitment effort to defeat the unprecedented threat to Earth.",
"890 Fifth Avenue","Manhattan","New York");
INSERT INTO organization(name,description,street,city,state) VALUES("X-Men","Realizing mutants were the next step in human evolution,
 Professor Charles Xavier gathered gifted teenagers to be his first class of students. His goal to protect and educate the next generation of homo superior,
 while pursuing a dream of harmony.",
"1407 Graymalkin Lane","Salem Center","New York");
INSERT INTO organization(name,description,street,city,state) VALUES("THE FANTASTIC FOUR","Reed Richards, Ben Grimm, Susan Storm, and her brother, Johnny Storm, were forever changed during an experimental space flight that exposed them to cosmic rays, which gave them super human powers and abilities.",
"42nd Street and Madison Avenue","Manhattan","New York");
INSERT INTO organization(name,description,street,city,state) VALUES("S.H.I.E.L.D.","S.H.I.E.L.D. is an espionage and security organization that defends Earth and its people from groups that pose advanced technological—and sometimes supernatural or extraterrestrial—threats.",
"SHIELD. facility","Washington","DC");



DROP TABLE IF EXISTS member_organization;
CREATE TABLE member_organization(
memberId INT NOT NULL,
organizationId INT NOT NULL,
foreign key  memberOrg_supermmeber_fk(memberId) references supermember(memberId),
foreign key memberOrg_organization_fk(organizationId) references organization(organizationId),
primary key(memberId,organizationId)
);
INSERT INTO member_organization(memberId,organizationId) VALUES(3,1);
INSERT INTO member_organization(memberId,organizationId) VALUES(2,2);
INSERT INTO member_organization(memberId,organizationId) VALUES(1,3);
INSERT INTO member_organization(memberId,organizationId) VALUES(4,4);
INSERT INTO member_organization(memberId,organizationId) VALUES(3,4);
INSERT INTO member_organization(memberId,organizationId) VALUES(3,2);
INSERT INTO member_organization(memberId,organizationId) VALUES(2,3);
INSERT INTO member_organization(memberId,organizationId) VALUES(1,4);

SELECT * FROM member_organization;
SELECT s.* FROM supermember s JOIN member_organization org ON org.memberId = s.memberId WHERE org.organizationId = 1;
SELECT org.* FROM organization org JOIN member_organization s ON org.organizationId = s.organizationId WHERE s.memberId = 1;



DROP TABLE IF EXISTS location;
CREATE TABLE location(
locationId INT PRIMARY KEY AUTO_INCREMENT,
name varchar(50),
description varchar(500),
street varchar(50),
city varchar(50),
state varchar(50),
coordinates varchar(100)
);
SELECT * FROM location;
INSERT INTO location(name,description,street,city,state,coordinates) VALUES("TimeSquare","Outside Taking Photos","5th Avenue","Manhattan","NewYork","40.777762, -73.959620");
INSERT INTO location(name,description,street,city,state,coordinates) VALUES("Palm Beach","Chilling on beach","Calm Avenue","Miami","Florida","25.79351946547674, -80.19993481326249");
INSERT INTO location(name,description,street,city,state,coordinates) VALUES("Montagh","Stoping flood","Beach Street","Montauk","NewYork","41.073679779464904, -71.8573797244698");
INSERT INTO location(name,description,street,city,state,coordinates) VALUES("Dead Valley","Camping in desert","Unknown","Grand Canyon","Arizona","36.10672124845257, -112.11863550135084");


DROP TABLE IF EXISTS sighting;
CREATE TABLE sighting(
sightingId INT NOT NULL auto_increment,
locationId INT NOT NULL,
memberId INT NOT NULL,
date varchar(50),
foreign key  sighting_supermmember_fk(memberId) references supermember(memberId),
foreign key  sighting_location_fk(locationId) references location(locationId),
primary key(sightingId,memberId,locationId)
);

ALTER TABLE sighting ADD COLUMN sightingId INT NOT NULL auto_increment;

DELETE FROM sighting WHERE locationId = 1 AND memberId = 3;
SELECT * FROM sighting;
SELECT s.name,sg.date FROM supermember s JOIN sighting sg ON sg.memberId = s.memberId;
SELECT l.name,sg.date FROM location l JOIN sighting sg ON l.locationId = sg.locationId;
SELECT * FROM sighting ORDER BY date DESC;

SELECT supermember.name,location.name, sighting.date FROM supermember JOIN sighting ON supermember.memberId=sighting.memberId JOIN location on location.locationId = sighting.locationId;

INSERT INTO sighting(locationId,memberId,date) VALUES(1,2,"04/03/2021");
INSERT INTO sighting(locationId,memberId,date) VALUES(1,3,"06/13/2021");
INSERT INTO sighting(locationId,memberId,date) VALUES(2,4,"07/23/2021");
INSERT INTO sighting(locationId,memberId,date) VALUES(2,1,"04/09/2021");
INSERT INTO sighting(locationId,memberId,date) VALUES(3,1,"09/14/2021");
INSERT INTO sighting(locationId,memberId,date) VALUES(3,2,"08/19/2021");
INSERT INTO sighting(locationId,memberId,date) VALUES(4,2,"04/29/2021");
INSERT INTO sighting(locationId,memberId,date) VALUES(4,3,"01/24/2021");
INSERT INTO sighting(locationId,memberId,date) VALUES(4,4,"06/13/2021");
INSERT INTO sighting(locationId,memberId,date) VALUES(2,2,"05/17/2021");
