CREATE TABLE users
(
    id        int                                NOT NULL AUTO_INCREMENT,
    role      enum('ADMIN','HOMEOWNER','RENTER') NOT NULL,
    email     varchar(45)                        NOT NULL,
    password  varchar(200)                       NOT NULL,
    firstName varchar(45)                        NOT NULL,
    lastName  varchar(45)                        NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(email)
);

CREATE TABLE properties
(
    id                 int                                       NOT NULL    AUTO_INCREMENT,
    price              double                                    NOT NULL,
    propertyType       enum('APARTMENT','HOUSE','ROOM','STUDIO') NOT NULL,
    city               varchar(45)                               NOT NULL,
    street             varchar(45)                               NOT NULL,
    streetNumber       int                                       NOT NULL,
    postcode           varchar(45)                               NOT NULL,
    livingSpace        int                                       NOT NULL,
    numberOfRooms      int                                       NOT NULL,
    numberOfBedrooms   int                                       NOT NULL,
    propertyStatus     enum('ACTIVE','INACTIVE')                 NOT NULL,
    ownerId            int                                       DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (id, ownerId),
    FOREIGN KEY (ownerId) REFERENCES users(id)
);

CREATE TABLE requests
(
    idRequest      int                                    NOT NULL AUTO_INCREMENT,
    idProperty     int                                    NOT NULL,
    idHomeowner    int NOT NULL,
    status         enum('PENDING','ACCEPTED','REJECTED')  NOT NULL,
    date           datetime                               NOT NULL,
    resonRejection varchar(100)                           DEFAULT NULL,
    PRIMARY KEY (idRequest),
    FOREIGN KEY (idHomeowner) REFERENCES users (id),
    FOREIGN KEY (idProperty) REFERENCES properties (id)
);