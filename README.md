# Mutant Spring boot Application

Mutant is spring boot application to help Magneto in finding mutants to fight the x-Men. Given a dna input with n size, the application will tell if the input is from a mutant or a human. The dna will be from a mutant if the there are more than one sequence of four characters (A, G, T or C) found vertically, horizontally or diagonally. Also, it provides a rest service with stats of ratio for mutants counts over humans counts

## Tech stack used

    - Java 1.8
    - Maven 3.6.3
    - Spring Boot 2.3.1
    - MongoDB Java Driver 3.12.2
    - JUnit 5 with Mockito
    - Log4j2 Lib
    - Jacoco Reports Lib
    - AWS Cloud

## Usage

### Run application locally

Proceed to clone [GIT](https://github.com/nujovich/mutantproject) into a local directory

```bash
git clone https://github.com/nujovich/mutantproject.git
```

Generate target folder:

```bash
mvn clean package
```

If you want to see the JaCoCo reports:
```bash
mvn install
mvn clean jacoco:prepare-agent install jacoco:report
```
This will generate a site\jacoco folder under target. To see generate reports go to index.html file, right click on it, copy path and paste it in your browser. Coverage %82-85


Run the spring boot app using the mvn wrapper command:
```bash
mvnw spring-boot:run
```
Or Run the app using the conventional java way:
```bash
java -jar mutantproject-0.0.1-SNAPSHOT.jar
```

### Rest Services Exposed

#### URL of the application

Hosted in a AWS free tier
```bash
http://ec2-18-224-213-168.us-east-2.compute.amazonaws.com:8080/mutantapp/v1
```

#### /mutant

Request - Mutant ADN

```bash
curl -v POST http://ec2-18-224-213-168.us-east-2.compute.amazonaws.com:8080/mutantapp/v1/mutant -H 'Content-Type: application/json' -d '{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}'
```
Or
```bash
curl -v POST http://localhost:8080/mutantapp/v1/mutant -H 'Content-Type: application/json' -d '{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}'
```

Response
```bash
< HTTP/1.1 200
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 9
< Date: Mon, 27 Jul 2020 16:50:15 GMT
<Is Mutant
```

Request: Human DNA

```bash
curl -v POST http://ec2-18-224-213-168.us-east-2.compute.amazonaws.com:8080/mutantapp/v1/mutant -H 'Content-Type: application/json' -d '{"dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]}'
```
Or
```bash
curl -v POST http://localhost:8080/mutantapp/v1/mutant -H 'Content-Type: application/json' -d '{"dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]}'
```
Response
```bash
Response
< HTTP/1.1 403
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 27 Jul 2020 17:52:13 GMT
<
{"errorMessage":"Not a mutant","errorCode":403}
```

#### /stats

Request
```bash
curl -v GET http://ec2-18-224-213-168.us-east-2.compute.amazonaws.com:8080/mutantapp/v1/stats -H 'Content-Type: application/json'
```
Or
```bash
curl -v GET http://localhost:8080/mutantapp/v1/stats -H 'Content-Type: application/json'
```

Response
```bash
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 27 Jul 2020 16:51:02 GMT
<
{"countDnaHuman":3,"countDnaMutant":7,"ratio":2.3333333333333335}
```

## Database - MongoDB
I used a dedicated free cluster configured in [MongoDB](https://cloud.mongodb.com/) site

