chmod +x mvn*
mvn -N io.takari:maven:wrapper
./mvnw clean package -pl qiot-datahub-storer-gas -am
# -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=docker
docker rmi quay.io/qiotcovid19/qiot-datahub-storer-gas:2.0.0-alpha
cd qiot-datahub-storer-gas
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/qiotcovid19/qiot-datahub-storer-gas:2.0.0-alpha .
cd ..
docker push quay.io/qiotcovid19/qiot-datahub-storer-gas:2.0.0-alpha
