# Library Management System Backend

## Quick Start

### 1. Prerequisites！！！
- Java JDK 1.8+
- Maven 3.6.0+

### 2. Clone Database 

First, clone the repository containing the database scripts:

```sql
git clone https://github.com/wanghanjun-code/Intelligent-library-management-system-sql.git
```

Then, in Navicat, run the following SQL command:

```sql
CREATE DATABASE books CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Click "Run SQL File", select the books.sql file, then refresh.
```



### 3. Clone Backend

```java
git clone https://github.com/wanghanjun-code/Intelligent-library-management-system-backend.git

//Open the Intelligent-library-management-system-backend folder in IntelliJ IDEA and run the project.
```



### 4. Configure Database

Edit `src/main/resources/application.yml`:
```properties
datasource:
  driver-class-name: com.mysql.cj.jdbc.Driver
  url: jdbc:mysql://localhost:3306/books?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true
  username: your_username
  password: your_password
```

### 5. Start Project
```bash
mvn spring-boot:run
```



## Troubleshooting

### Port Already in Use
```bash
lsof -i :21090
kill -9 <PID>
```

### Database Connection Failed
- Check MySQL service is running
- Confirm database exists 