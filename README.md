```
CREATE TABLE blog (
    id int NOT NULL AUTO_INCREMENT,
    text varchar(255) NOT NULL,
    timestamp int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE comment (
    id int NOT NULL AUTO_INCREMENT,
    text varchar(255) NOT NULL,
    timestamp int NOT NULL,
    blogId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (blogId) REFERENCES blog(id)
);
```
