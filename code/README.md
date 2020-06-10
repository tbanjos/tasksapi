# Task API 

Provides an API to store tasks and assign them to people

## Requirements

- Java 11
- Gradle 6.0
- Docker and docker-compose
- Postman (optional)

## Run

To build the app run:

```
./gradlew booJar
```

The app and configured MongoDb instances can be run with docker-compose:

```
docker-compose up
```

To run the integration tests you need the mongodb instance to be up:
```
docker-compose up mongo

./gradlew integrationTest
```

## Usage

The API provides CRUD operations for task and person. 

Examples:

```
curl --location --request GET 'http://localhost:8003/tasks'
```

```
curl --location --request GET 'http://localhost:8003/persons'
```

To assign a task to a person use: 
```
curl --location --request POST 'http://localhost:8003/assignments' \
--header 'Content-Type: application/json' \
--data-raw '{
	"personId": "1",
	"taskId" : "1"
}'
```
To get tasks assigned to a person: 
```
curl --location --request GET 'http://localhost:8003/assignments?personId=1'
```

Please check the Postman collection for all endpoints.

