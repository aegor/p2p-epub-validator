### Build:
```
mvn package
```
### Run
```
java -jar target/p2p-epub-validator-0.0.1.jar
```
or
```
mvn spring-boot:run
```
### Validate validator :)
```
curl -F "name=geom.epub" -F "file=@geom.epub" http://localhost:8080/validate
```
or 
```
./validate <file name>
```
