# Hexagonal architecture Spring Boot implementation

## Want to know more on Hexagonal Architecture

* [This template](https://github.com/jaguililla/hexagonal_spring)
* [Article on the Netflix Engineering blog](https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749)
* [Another insightful article](https://softengbook.org/articles/hexagonal-architecture)

## Environment & tools

* Java 21
* Gradle
* Docker & Docker compose

## How to build it?

Run

```bash
gradle build
```

## How to start it?
### Infrastructure
Start the infrastructure first

```bash
docker compose up -d
```

### Application

Run the following command

```bash
gradle bootRun
```

You can now reach the API as following:

```bash
http :8080/risks amount==9000
```
