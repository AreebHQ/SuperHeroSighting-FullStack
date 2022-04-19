DROP DATABASE IF EXISTS superheroTest;
CREATE DATABASE superheroTest;
USE superheroTest;


select * from supermember;
select * from location;
select * from sighting;
select * from organization;



DROP TABLE IF EXISTS supermember;
CREATE TABLE supermember(
memberId INT PRIMARY KEY AUTO_INCREMENT,
name varchar(50),
description varchar(500),
superpower varchar(50)
);
select * from supermember;

DROP TABLE IF EXISTS organization;
CREATE TABLE organization(
organizationId INT PRIMARY KEY AUTO_INCREMENT,
name varchar(50),
description varchar(500),
street varchar(50),
city varchar(50),
state varchar(50)
);


DROP TABLE IF EXISTS member_organization;
CREATE TABLE member_organization(
memberId INT NOT NULL,
organizationId INT NOT NULL,
foreign key  memberOrg_supermmeber_fk(memberId) references supermember(memberId),
foreign key memberOrg_organization_fk(organizationId) references organization(organizationId),
primary key(memberId,organizationId)
);


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

select * from supermember;