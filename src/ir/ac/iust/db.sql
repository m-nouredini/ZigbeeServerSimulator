CREATE TABLE IF NOT EXISTS Device(Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, IEEEAddress BIGINT);
CREATE TABLE IF NOT EXISTS Endpoint(Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, IEEEAddress BIGINT, EndpointNumber INT, Status INT);
