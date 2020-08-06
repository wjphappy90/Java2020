create database flashsale;
use flashsale;
CREATE TABLE
    USER
    (
        ID VARCHAR(128) COLLATE utf8_general_ci NOT NULL,
        USER_NAME VARCHAR(120) COLLATE utf8_general_ci,
        PASSWORD VARCHAR(64) COLLATE utf8_general_ci,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE
    tdorder
    (
        ID VARCHAR(128) COLLATE utf8_general_ci NOT NULL,
        userid VARCHAR(128) COLLATE utf8_general_ci,
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (`ID`, `USER_NAME`, `PASSWORD`) VALUES('1','zhangsan','123456');