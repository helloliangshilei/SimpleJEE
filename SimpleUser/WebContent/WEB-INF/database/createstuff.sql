SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `simpleapp` ;
CREATE SCHEMA IF NOT EXISTS `simpleapp` DEFAULT CHARACTER SET utf8 ;
USE `simpleapp` ;

-- -----------------------------------------------------
-- Table `simpleapp`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simpleapp`.`user` ;

CREATE  TABLE IF NOT EXISTS `simpleapp`.`user` (
  `username` VARCHAR(100) NOT NULL ,
  `firstname` VARCHAR(100) NULL ,
  `lastname` VARCHAR(100) NULL ,
  `password` VARCHAR(100) NULL ,
  PRIMARY KEY (`username`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simpleapp`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simpleapp`.`role` ;

CREATE  TABLE IF NOT EXISTS `simpleapp`.`role` (
  `role` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(100) NULL ,
  PRIMARY KEY (`role`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simpleapp`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simpleapp`.`user_role` ;

CREATE  TABLE IF NOT EXISTS `simpleapp`.`user_role` (
  `user_username` VARCHAR(100) NOT NULL ,
  `role_role` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`user_username`, `role_role`) ,
  CONSTRAINT `fk_user_has_role_user`
    FOREIGN KEY (`user_username` )
    REFERENCES `simpleapp`.`user` (`username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_role` )
    REFERENCES `simpleapp`.`role` (`role` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_user_has_role_role1_idx` ON `simpleapp`.`user_role` (`role_role` ASC) ;

CREATE INDEX `fk_user_has_role_user_idx` ON `simpleapp`.`user_role` (`user_username` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into user (username, firstname, lastname, password) values ('mckerrj', 'Jason', 'Mckerr', 'mckerrj314');
insert into user (username, firstname, lastname, password) values ('gordond', 'Dexter', 'Gordon', 'gordond314');
insert into user (username, firstname, lastname, password) values ('test1', 'testfirst', 'testlast', 'passtest1');
commit;
insert into role (role, description) values ('administrator', 'this person administrates..er..administrators..admmins...administers stuff');
insert into role (role, description) values ('developer', 'this person develops stuff');
insert into role (role, description) values ('saxplayer', 'this person plays a mean sax');
commit;
insert into user_role(user_username, role_role) values ('mckerrj', 'administrator');
insert into user_role(user_username, role_role) values ('mckerrj', 'developer');
insert into user_role(user_username, role_role) values ('gordond', 'administrator');
insert into user_role(user_username, role_role) values ('gordond', 'saxplayer');
commit;

