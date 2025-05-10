# Sử dụng image openjdk 21 làm base
FROM openjdk:21-jdk-slim

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file JAR từ build Maven
COPY target/warehouse-backend-0.0.1-SNAPSHOT.jar app.jar

# Mở port 8080
EXPOSE 8080

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]