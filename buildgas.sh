chmod +x mvn*
mvn -N io.takari:maven:wrapper
./mvnw clean package -pl qiot-datahub-storer-gas -am -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=dockerdocker rmi quay.io/qiotcovid19/qiot-datahub-storer-gas:2.0.0-alpha
docker build -f src/main/docker/Dockerfile.native -t quay.io/qiotcovid19/qiot-datahub-storer-gas:2.0.0-alpha .
docker push quay.io/qiotcovid19/qiot-datahub-storer-gas:2.0.0-alpha
#docker run -it --rm -p 5000:5000 --net host quay.io/qiot/qiot-datahub-registration
