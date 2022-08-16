CREATE DATABASE IF NOT EXISTS `springbootadvertisement`;
CREATE DATABASE IF NOT EXISTS `springbootreport`;
CREATE DATABASE IF NOT EXISTS `springbootuser`;

-- CREATE USER 'root'@'localhost' IDENTIFIED BY 'local';
-- GRANT ALL ON *.* TO 'root'@'%';

GRANT ALL ON `springbootadvertisement`.* TO 'springmicroserviceuser'@'%';
GRANT ALL ON `springbootreport`.* TO 'springmicroserviceuser'@'%';
GRANT ALL ON `springbootuser`.* TO 'springmicroserviceuser'@'%';