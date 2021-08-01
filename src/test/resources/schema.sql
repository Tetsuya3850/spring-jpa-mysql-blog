CREATE TABLE blog (
   `id` int NOT NULL AUTO_INCREMENT,
    `text` varchar(255) NOT NULL,
    `visibility` enum('PUBLIC','PRIVATE') NOT NULL DEFAULT 'PUBLIC',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `location_lat` double DEFAULT NULL,
    `location_lon` double DEFAULT NULL,
    `version` int NOT NULL,
     PRIMARY KEY (`id`)
);

CREATE TABLE comment (
    id int NOT NULL AUTO_INCREMENT,
    text varchar(255) NOT NULL,
    timestamp int NOT NULL,
    blogId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (blogId) REFERENCES blog(id)
);

CREATE TABLE genre (
    id int NOT NULL AUTO_INCREMENT,
    description varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE genre_details (
    explanation varchar(255) NOT NULL,
    genre_id int NOT NULL,
    PRIMARY KEY (genre_id),
    FOREIGN KEY (genre_id) REFERENCES genre(id)
);