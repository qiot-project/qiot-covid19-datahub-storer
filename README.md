# QIoT Datahub Storer

## Choose one of:

### To build and create container image

$ ./mvnw clean package

### To build, create container image and push it to registry

$ ./mvnw -Dhttps.protocols=TLSv1.2 clean package -Dquarkus.container-image.push=true

### To build native and create container image

$ ./mvnw clean package -Pnative

### To build native, create container image and push it to registry

$ ./mvnw -Dhttps.protocols=TLSv1.2 clean package -Pnative -Dquarkus.container-image.push=true