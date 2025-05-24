# Sử dụng image openjdk 21 làm base
FROM openjdk:21-jdk-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file Maven và source code
COPY pom.xml .
COPY src ./src

# Cài đặt Maven và build ứng dụng
RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests

# Expose cổng 8080
EXPOSE 8080

# Lệnh chạy ứng dụng
CMD ["java", "-jar", "target/warehouse-backend-0.0.1-SNAPSHOT.jar"]