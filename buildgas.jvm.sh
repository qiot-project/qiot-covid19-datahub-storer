chmod +x mvn*
mvn -N io.takari:maven:wrapper
./mvnw clean package -pl qiot-datahub-storer-gas -am -Dquarkus.package.type=fast-jar
# -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=docker
docker rmi quay.io/qiotcovid19/qiot-datahub-storer-gas:2.0.0
cd qiot-datahub-storer-gas
docker build -f src/main/docker/Dockerfile.fast-jar -t quay.io/qiotcovid19/qiot-datahub-storer-gas:2.0.0 .
cd ..
docker push quay.io/qiotcovid19/qiot-datahub-storer-gas:2.0.0
