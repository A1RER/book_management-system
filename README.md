# 图书管理系统 (Book Management System)

一个基于 Java Swing 和 MySQL 的桌面图书管理系统，支持管理员和读者两种角色。

## 功能特性

### 管理员功能
- 管理员登录
- 添加书籍
- 删除书籍
- 查询书籍（按书名、作者、出版社）
- 查询读者
- 删除读者

### 读者功能
- 读者注册 / 登录
- 查询书籍（按书名、作者、出版社）

## 技术栈

| 类别 | 技术 |
|------|------|
| 开发语言 | Java SE 8 |
| GUI 框架 | Java Swing |
| 数据库 | MySQL |
| 数据库驱动 | MySQL Connector/Java 8.0.15 |
| 开发工具 | Eclipse / IntelliJ IDEA |

## 项目结构

```
book_management-system/
├── src/
│   └── com/java/
│       ├── dao/           # 数据访问层
│       │   ├── Main.java              # 程序入口
│       │   ├── AdminDao.java          # 管理员数据访问
│       │   ├── BookDao.java           # 书籍数据访问
│       │   ├── BookInformationDao.java
│       │   └── UserDao.java           # 读者数据访问
│       ├── frame/         # 图形界面
│       │   ├── EnterInterface.java    # 登录界面
│       │   ├── AdminMainInterface.java    # 管理员主界面
│       │   ├── ReaderMainInterface.java   # 读者主界面
│       │   ├── RegisterInterface.java     # 注册界面
│       │   ├── AddBookInterface.java      # 添加书籍界面
│       │   ├── DeleteBookInterface.java   # 删除书籍界面
│       │   ├── DeleteReaderInterface.java # 删除读者界面
│       │   ├── QueryBookInterface.java    # 查询书籍界面
│       │   └── QueryReaderInterface.java  # 查询读者界面
│       ├── model/         # 数据模型
│       │   ├── Admin.java
│       │   ├── Book.java
│       │   ├── BookInformation.java
│       │   └── Reader.java
│       └── util/          # 工具类
│           ├── Connect.java       # 数据库连接
│           └── StringNull.java    # 字符串校验
├── lib/
│   └── mysql-connector-java-8.0.15.jar
└── bin/                   # 编译输出目录
```

## 环境要求

- JDK 8 或以上
- MySQL 5.7 或以上
- Eclipse 或 IntelliJ IDEA

## 快速开始

### 1. 克隆项目

```bash
git clone <repository-url>
cd book_management-system
```

### 2. 创建数据库

在 MySQL 中执行以下 SQL：

```sql
CREATE DATABASE book_admin CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE book_admin;

CREATE TABLE admin (
    admin_id    INT PRIMARY KEY AUTO_INCREMENT,
    admin_name  VARCHAR(50) NOT NULL,
    admin_phone VARCHAR(20),
    admin_password VARCHAR(50) NOT NULL
);

CREATE TABLE reader (
    reader_id       INT PRIMARY KEY AUTO_INCREMENT,
    reader_name     VARCHAR(50) NOT NULL,
    reader_phone    VARCHAR(20),
    reader_password VARCHAR(50) NOT NULL
);

CREATE TABLE book (
    book_id      INT PRIMARY KEY AUTO_INCREMENT,
    book_name    VARCHAR(100) NOT NULL,
    book_writer  VARCHAR(50),
    book_publish VARCHAR(100),
    book_status  VARCHAR(20) DEFAULT '在馆'
);

-- 插入默认管理员账号
INSERT INTO admin (admin_name, admin_phone, admin_password) VALUES ('admin', '12345678900', '123456');
```

### 3. 配置数据库连接

编辑 `src/com/java/util/Connect.java`，修改数据库连接信息：

```java
con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/book_admin?serverTimezone=UTC",
    "root",    // 替换为你的 MySQL 用户名
    "123456"   // 替换为你的 MySQL 密码
);
```

### 4. 编译并运行

**使用 IDE：**

1. 用 Eclipse 或 IntelliJ IDEA 打开项目
2. 将 `lib/mysql-connector-java-8.0.15.jar` 添加到项目 classpath
3. 运行 `com.java.dao.Main` 类

**使用命令行（Linux / macOS）：**

```bash
javac -cp lib/mysql-connector-java-8.0.15.jar:src -d bin \
    $(find src -name "*.java")

java -cp lib/mysql-connector-java-8.0.15.jar:bin com.java.dao.Main
```

**使用命令行（Windows）：**

```bat
javac -cp "lib\mysql-connector-java-8.0.15.jar;src" -d bin ^
    src\com\java\dao\Main.java

java -cp "lib\mysql-connector-java-8.0.15.jar;bin" com.java.dao.Main
```

## 使用说明

1. 启动程序后进入登录界面
2. **管理员**：使用管理员账号登录，可进行书籍和读者的增删查操作
3. **读者**：注册账号后登录，可查询书籍信息

## 注意事项

- 数据库连接信息（用户名、密码）硬编码在 `Connect.java` 中，生产环境中请使用配置文件管理敏感信息
- 确保 MySQL 服务已启动并可访问
- 默认连接地址为 `localhost:3306`，如有变更请同步修改 `Connect.java`

## 许可证

本项目仅供学习交流使用。
