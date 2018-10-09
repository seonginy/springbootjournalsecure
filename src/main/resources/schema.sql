-- SECURITY: USER ACCOUNT
DROP TABLE IF EXISTS account;
create table account (
  account_name varchar(255) not null,
  password varchar(255) not null,
  id serial,
  enabled bool default true);

-- JOURNAL TABLE: ENTRY
DROP table if exists entry;
create table entry(
  id bigint(20) not null auto_increment,
  created datetime default null,
  summary varchar(255) default null,
  title varchar(255) default null,
  primary key (ID)
);