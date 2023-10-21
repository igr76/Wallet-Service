FROM eclipse-temurin:17-jdk
ADD /target/WalletService-0.0.1-SNAPSHOT.jar WalletService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","WalletService-0.0.1-SNAPSHOT.jar"]
