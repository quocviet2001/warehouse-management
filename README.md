# Hệ thống Quản lý Kho hàng

Ứng dụng web full-stack để quản lý các hoạt động kho hàng, bao gồm quản lý tồn kho, nhập/xuất kho, danh mục sản phẩm, báo cáo và quản lí người dùng. Được xây dựng bằng Spring Boot, ReactJS, MySQL và được đóng gói bằng Docker để dễ dàng triển khai.

## Mục lục
- [Tính năng](#tính-năng)
- [Công nghệ sử dụng](#công-nghệ-sử-dụng)
- [Cấu trúc thư mục](#cấu-trúc-thư-mục)
- [Hướng dẫn Cài đặt và Chạy bằng Docker](#hướng-dẫn-cài-đặt-và-chạy-bằng-docker)

## Tính năng

- **Xác thực người dùng**: Đăng nhập an toàn với JWT.
- **Quản lý sản phẩm**: Thêm, sửa, xóa sản phẩm với bộ lọc theo mã hoặc tên sản phẩm.
- **Quản lý danh mục**: Sắp xếp các sản phẩm theo danh mục.
- **Quản lý nhập/xuất kho**: Ghi nhận giao dịch nhập/xuất sản phẩm chi tiết, rõ ràng.
- **Báo cáo**: Tạo báo cáo nhập/xuất với khả năng lọc dữ liệu theo khoảng thời gian.
- **Giao diện**: Xây dựng bằng ReactJS.
- **Triển khai container**: Backend, Frontend và MySQL được đóng gói trong Docker.

## Công nghệ sử dụng

- **Backend**: Java, Spring Boot, MySQL, JWT
- **Frontend**: ReactJS, Axios, Tailwind CSS
- **Cơ sở dữ liệu**: MySQL
- **Triển khai**: Docker, Docker Compose
- **Công cụ build**: Maven, npm

## Cấu trúc thư mục

```
warehouse-management/
├── warehouse-backend/                    # Backend Spring Boot
│   ├── src/                    # Mã nguồn
│   ├── Dockerfile              # Dockerfile cho backend
│   └── pom.xml                 # Cấu hình Maven
├── warehouse-frontend/                   # Frontend ReactJS
│   ├── src/                    # Mã nguồn
│   ├── Dockerfile              # Dockerfile cho frontend
│   ├── nginx.conf              # Cấu hình Nginx
│   ├── package.json            # Cấu hình npm
│   └── public/                 # File tĩnh
├── docker-compose.yml          # Cấu hình Docker Compose
├── .env                        # Biến môi trường
└── README.md                   # Tài liệu dự án
```

## Hướng dẫn Cài đặt và Chạy bằng Docker

### Yêu cầu

- Docker ([Hướng dẫn cài đặt](https://docs.docker.com/get-docker/))
- Docker Compose ([Hướng dẫn cài đặt](https://docs.docker.com/compose/install/))
- Git ([Hướng dẫn cài đặt](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git))

### Bước 1: Clone dự án

1. Mở terminal và clone repository từ GitHub:

   ```bash
   git clone https://github.com/quocviet2001/warehouse-management.git
   cd warehouse-management
   ```


### Bước 2: Tạo file `.env`

1. Trong thư mục gốc của dự án (`warehouse-management`), tạo file `.env` với nội dung sau:

   ```plaintext
   MYSQL_ROOT_PASSWORD=your-password
   SPRING_DATASOURCE_USERNAME=root
   SPRING_DATASOURCE_PASSWORD=your-password
   MYSQL_DATABASE=warehouse_db

   # Chuỗi bí mật dùng để ký JWT,cần dài ít nhất 64 ký tự, chứa chữ cái và số ngẫu nhiên
   JWT_SECRET=your-secret-string
   ```

### Bước 3: Build và chạy ứng dụng

1. Mở terminal trong thư mục gốc của dự án (`warehouse-management`).
2. Chạy lệnh sau để build và khởi động các container:

   ```bash
   docker-compose up --build -d
   ```
**Lưu ý**: Mở Docker trước khi chạy lệnh trên.
### Bước 4: Tạo tài khoản admin với mật khẩu tùy chỉnh

Để tạo tài khoản admin với mật khẩu của riêng bạn, thực hiện các bước sau:

1. **Tạo mật khẩu mã hóa bằng công cụ online**:
   - Truy cập [BCrypt Generator](https://bcrypt-generator.com/).
   - Nhập mật khẩu bạn muốn (ví dụ: `admin123`).
   - Nhấn "Generate" để nhận hash BCrypt (ví dụ: `$2a$10$K7L8M9N0P1Q2R3S4T5U6V7...`).
   - Sao chép hash này.
2. **Truy cập MySQL container**:
   - Chạy lệnh sau để vào MySQL:

     ```bash
     docker exec -it warehouse_mysql_1 mysql -u root -pyourpassword warehouse_db
     ```

     **Lưu ý**: `warehouse-mysql-1` là tên container, kiểm tra bằng `docker ps` và thay thế. Thay yourpassword bằng mật khẩu database đã tạo ở file .env của bạn.

3. **Chèn tài khoản admin**:
   - Trong MySQL, chạy lệnh sau, thay `YOUR_BCRYPT_HASH_HERE` bằng hash BCrypt vừa tạo:

     ```sql
     INSERT INTO users (id, username, password, role) 
     VALUES (1, 'admin', 'YOUR_BCRYPT_HASH_HERE', 'ADMIN');
     ```

### Bước 5: Truy cập ứng dụng và kiểm thử
1. **Đăng nhập**: Truy cập `http://localhost:3000/login` và sử dụng tài khoản `username/password` (Ví dụ:`admin/admin123` nếu dữ liệu tài khoản đã nhập ở bước 4).
2. **Quản lý sản phẩm**: Vào `http://localhost:3000/products` để thêm/sửa/xóa sản phẩm.
3. **Nhập/Xuất kho**: Kiểm tra `http://localhost:3000/stock-in` và `http://localhost:3000/stock-out`.
4. **Báo cáo**: Truy cập `http://localhost:3000/reports` để tạo báo cáo.

### Bước 6: Dừng ứng dụng

Để dừng và xóa các container:

```bash
docker-compose down
```

