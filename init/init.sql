CREATE DATABASE IF NOT EXISTS `springbootadvertisement`;
CREATE DATABASE IF NOT EXISTS `springbootreport`;
CREATE DATABASE IF NOT EXISTS `springbootuser`;

-- CREATE USER 'root'@'localhost' IDENTIFIED BY 'local';
-- GRANT ALL ON *.* TO 'root'@'%';

-- GRANT ALL PRIVILEGES ON `springbootadvertisement`.* TO 'springmicroserviceuser'@'%' WITH GRANT OPTION;
-- GRANT ALL PRIVILEGES ON `springbootreport`.* TO 'springmicroserviceuser'@'%' WITH GRANT OPTION;
-- GRANT ALL PRIVILEGES ON `springbootuser`.* TO 'springmicroserviceuser'@'%' WITH GRANT OPTION;

GRANT ALL ON `springbootadvertisement`.* TO 'springmicroserviceuser'@'%';
GRANT ALL ON `springbootreport`.* TO 'springmicroserviceuser'@'%';
GRANT ALL ON `springbootuser`.* TO 'springmicroserviceuser'@'%';