-- noinspection SqlNoDataSourceInspectionForFile

create table Rating (
    RATING_ID INTEGER not null,
    MOVIE_ID  VARCHAR not null,
    RATING    INTEGER not null,
    USER_ID   VARCHAR not null
);
insert into Rating(RATING_ID, MOVIE_ID, RATING, USER_ID) values  (1, '111', 8, '8888');
insert into Rating(RATING_ID, MOVIE_ID, RATING, USER_ID) values  (2, '222', 10, '8888');
insert into Rating(RATING_ID, MOVIE_ID, RATING, USER_ID) values  (3, '333', 10, '8888');
insert into Rating(RATING_ID, MOVIE_ID, RATING, USER_ID) values  (4, '444', 9, '8888');
insert into Rating(RATING_ID, MOVIE_ID, RATING, USER_ID) values  (5, '111', 10, '9999');
insert into Rating(RATING_ID, MOVIE_ID, RATING, USER_ID) values  (6, '333', 9, '9999');
