# cinema-booking-platform

## Install
postgres (includes pgadmin)
create database 
```yaml
    url: jdbc:postgresql://localhost:5432/cinema
    username: postgres
    password: password
```

## Liquibase
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-liquibase</artifactId>
		</dependency>
```

/resources/db/changelog/db.changelog-master.yaml
/resources/db/changelog/changes/001-create-tables.yaml

## Movies data

psql tool in pgadmin to load data from csv file to movies table

```psql
\copy public.movies (movie_id,poster_link,title,released_year,certificate,runtime_minutes,genre,imdb_rating,overview,meta_rating,director,star1,star2,star3,star4,no_of_votes,gross) FROM 'C:/Users/sakus/Downloads/movies.csv' WITH (FORMAT csv, HEADER true, DELIMITER ',', QUOTE '"', NULL '', ENCODING 'UTF8');
```

