# Giai đoạn build
FROM node:22 AS build

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file package.json và cài dependencies
COPY package*.json ./
RUN npm install

# Sao chép mã nguồn và build
COPY . .
RUN npm run build

# Giai đoạn serve
FROM nginx:alpine

# Sao chép file build từ giai đoạn trước
COPY --from=build /app/build /usr/share/nginx/html

# Mở port 80
EXPOSE 80

# Chạy Nginx
CMD ["nginx", "-g", "daemon off;"]