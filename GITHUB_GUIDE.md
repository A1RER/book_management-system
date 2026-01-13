# GitHub 使用指南 - 从入门到精通

> 专为本科生求职准备
> 适用于图书管理系统项目
> 更新时间：2026-01-13

---

## 📖 目录
1. [GitHub是什么？为什么重要？](#1-github是什么为什么重要)
2. [基础概念](#2-基础概念)
3. [日常使用流程](#3-日常使用流程)
4. [常用命令速查](#4-常用命令速查)
5. [项目管理最佳实践](#5-项目管理最佳实践)
6. [面试加分技巧](#6-面试加分技巧)

---

## 1. GitHub是什么？为什么重要？

### 什么是GitHub？
GitHub是全球最大的代码托管平台，相当于程序员的"社交网络"。

**简单理解：**
- **云盘：** 存储你的代码
- **时光机：** 记录每次修改，可以回退到任何版本
- **简历：** 展示你的技术能力
- **协作工具：** 多人一起开发项目

### 为什么对求职重要？

在秋招中，GitHub账号相当于**第二份简历**：

| 场景 | 作用 | 重要性 |
|------|------|--------|
| 投递简历 | 附上GitHub链接，展示项目 | ⭐⭐⭐⭐⭐ |
| 技术面试 | 面试官会查看你的代码质量 | ⭐⭐⭐⭐⭐ |
| HR筛选 | GitHub活跃度是筛选标准之一 | ⭐⭐⭐⭐ |
| 薪资谈判 | 优质开源贡献可提升议价能力 | ⭐⭐⭐ |

**真实案例：**
```
小明：简历上写"熟悉Spring Boot"，但GitHub空空如也
小红：简历上附上GitHub链接，有3个完整项目，每个项目README详细

结果：小红收到面试邀请的概率是小明的3倍
```

---

## 2. 基础概念

### 2.1 核心概念解释

#### Repository（仓库）
**类比：** 一个项目的文件夹
```
你的GitHub账号
├── book_management-system  ← 这就是一个仓库
├── my-blog
└── leetcode-solutions
```

#### Commit（提交）
**类比：** 游戏存档点
```
每次commit = 保存一次项目状态
可以随时回到任何一个commit
```

**示例：**
```
commit 1: 初始版本
commit 2: 修复登录bug
commit 3: 添加图书查询功能  ← 你现在在这里
commit 4: （未来）添加Redis缓存
```

#### Branch（分支）
**类比：** 平行世界
```
main分支（主线）
    ├── feature-login （新功能：登录）
    ├── bugfix-sql    （修复：SQL注入）
    └── refactor-v2.0 （重构：Spring Boot改造）
```

**为什么需要分支？**
- 不影响主线代码
- 可以同时开发多个功能
- 出问题可以随时删除分支

#### Push/Pull（推送/拉取）
```
本地电脑 ←→ GitHub服务器

Push（推送）：本地 → GitHub
Pull（拉取）：GitHub → 本地
```

---

## 3. 日常使用流程

### 3.1 第一次使用GitHub（初始化）

#### Step 1: 安装Git
```bash
# 检查是否已安装
git --version

# 如果未安装，去官网下载：https://git-scm.com/
```

#### Step 2: 配置个人信息
```bash
# 设置用户名（会显示在commit记录中）
git config --global user.name "你的名字"

# 设置邮箱（最好用GitHub注册邮箱）
git config --global user.email "your.email@example.com"

# 验证配置
git config --list
```

#### Step 3: 创建GitHub账号
1. 访问 https://github.com
2. 注册账号（建议用常用邮箱）
3. 完善个人资料（头像、简介、个人网站）

**求职建议：**
- 用户名：简洁专业（如：ZhangSan、johnsmith）
- 头像：清晰的照片或专业头像
- 简介：写明学校、专业、求职意向

#### Step 4: 创建第一个仓库
```bash
# 方法1：在GitHub网站上创建
1. 点击右上角 "+" → "New repository"
2. 输入仓库名：book_management-system
3. 选择Public（公开）
4. 勾选"Add a README file"
5. 点击"Create repository"

# 方法2：命令行创建（使用gh CLI）
gh repo create book_management-system --public
```

---

### 3.2 日常开发流程（最重要！）

**标准工作流：**
```
1. 修改代码
2. 查看状态
3. 添加到暂存区
4. 提交到本地仓库
5. 推送到GitHub
```

#### 完整示例：今天你修复了一个bug

```bash
# ========== Step 1: 查看当前状态 ==========
git status
# 会显示哪些文件被修改了

# ========== Step 2: 查看具体改动 ==========
git diff
# 显示每个文件具体改了什么

# ========== Step 3: 添加修改到暂存区 ==========
# 方法1：添加所有修改
git add .

# 方法2：添加特定文件
git add src/com/java/dao/BookDao.java

# 方法3：交互式添加（推荐）
git add -p  # 会逐个询问是否添加

# ========== Step 4: 提交到本地仓库 ==========
git commit -m "fix: 修复图书查询SQL注入漏洞"

# 提交信息规范（重要！）：
# fix: 修复bug
# feat: 新功能
# refactor: 重构
# docs: 文档更新
# style: 代码格式
# test: 测试相关

# ========== Step 5: 推送到GitHub ==========
git push origin main  # main是分支名

# 如果是第一次推送这个分支
git push -u origin main
```

---

### 3.3 分支管理（团队协作必备）

#### 创建新分支开发功能

```bash
# ========== 查看所有分支 ==========
git branch
# * main  ← 星号表示当前所在分支

# ========== 创建新分支 ==========
git branch feature-redis-cache

# ========== 切换到新分支 ==========
git checkout feature-redis-cache

# ========== 快捷方式：创建并切换 ==========
git checkout -b feature-redis-cache

# ========== 在新分支上开发 ==========
# 修改代码...
git add .
git commit -m "feat: 添加Redis缓存功能"
git push origin feature-redis-cache

# ========== 合并回主分支 ==========
git checkout main           # 切回主分支
git merge feature-redis-cache  # 合并新功能
git push origin main        # 推送到GitHub

# ========== 删除已合并的分支 ==========
git branch -d feature-redis-cache  # 删除本地分支
git push origin --delete feature-redis-cache  # 删除远程分支
```

---

### 3.4 协作开发（多人项目）

#### Fork + Pull Request 工作流

```bash
# ========== Step 1: Fork别人的仓库 ==========
# 在GitHub网站上点击 "Fork" 按钮
# 会在你的账号下创建一个副本

# ========== Step 2: Clone到本地 ==========
git clone https://github.com/你的用户名/项目名.git
cd 项目名

# ========== Step 3: 创建分支开发 ==========
git checkout -b fix-typo

# 修改代码...
git add .
git commit -m "docs: 修复README中的拼写错误"
git push origin fix-typo

# ========== Step 4: 在GitHub上创建Pull Request ==========
# 1. 访问你Fork的仓库页面
# 2. 点击 "Pull request" 按钮
# 3. 填写PR描述，说明你做了什么
# 4. 点击 "Create pull request"

# ========== Step 5: 等待原作者审核 ==========
# 通过：你的代码被合并
# 需要修改：根据反馈修改代码，再次push
```

---

## 4. 常用命令速查

### 4.1 基础操作（必须掌握）

```bash
# ========== 配置 ==========
git config --global user.name "你的名字"
git config --global user.email "邮箱"
git config --list  # 查看配置

# ========== 初始化 ==========
git init  # 初始化本地仓库
git clone <url>  # 克隆远程仓库

# ========== 日常提交 ==========
git status  # 查看状态（最常用！）
git add .   # 添加所有修改
git commit -m "提交信息"  # 提交
git push    # 推送到远程

# ========== 查看历史 ==========
git log  # 查看提交历史
git log --oneline  # 简洁版
git log --graph  # 图形化显示

# ========== 分支操作 ==========
git branch  # 查看分支
git branch <分支名>  # 创建分支
git checkout <分支名>  # 切换分支
git checkout -b <分支名>  # 创建并切换
git merge <分支名>  # 合并分支
git branch -d <分支名>  # 删除分支

# ========== 同步远程 ==========
git pull  # 拉取远程更新
git fetch  # 获取远程更新（不合并）
git remote -v  # 查看远程仓库
```

---

### 4.2 高级操作（提升效率）

```bash
# ========== 撤销操作 ==========
git restore <文件>  # 撤销工作区修改
git restore --staged <文件>  # 取消暂存
git reset HEAD~1  # 撤销上一次commit（保留修改）
git reset --hard HEAD~1  # 撤销上一次commit（删除修改）

# ========== 查看差异 ==========
git diff  # 查看工作区改动
git diff --staged  # 查看暂存区改动
git diff main feature  # 对比两个分支

# ========== 储藏工作 ==========
git stash  # 暂存当前修改
git stash list  # 查看储藏列表
git stash pop  # 恢复最近一次储藏

# ========== 标签管理 ==========
git tag v1.0  # 创建标签
git tag  # 查看所有标签
git push origin v1.0  # 推送标签

# ========== 修改提交 ==========
git commit --amend  # 修改最后一次提交
git rebase -i HEAD~3  # 交互式修改最近3次提交
```

---

### 4.3 救命命令（出问题时用）

```bash
# ========== 代码写乱了，想回到上次commit ==========
git restore .  # 恢复所有文件

# ========== 不小心commit了，想撤销 ==========
git reset HEAD~1  # 撤销commit，保留修改
git reset --hard HEAD~1  # 撤销commit，删除修改

# ========== 合并冲突了 ==========
# 1. 手动编辑冲突文件，删除冲突标记
# 2. git add <冲突文件>
# 3. git commit

# ========== push被拒绝了 ==========
git pull --rebase  # 先拉取远程更新
git push  # 再推送

# ========== 想回到某个历史版本 ==========
git log --oneline  # 找到commit ID
git checkout <commit-id>  # 查看该版本（临时）
git reset --hard <commit-id>  # 彻底回到该版本（危险！）

# ========== 找回被删除的commit ==========
git reflog  # 查看所有操作记录
git checkout <commit-id>  # 恢复
```

---

## 5. 项目管理最佳实践

### 5.1 README.md规范（超重要！）

**README是项目的门面**，面试官第一眼看的就是它！

#### 完整模板：

```markdown
# 📚 图书管理系统

> 基于Spring Boot + Vue3的全栈项目
> 作者：张三 | 邮箱：zhangsan@example.com

## 📖 项目简介

这是一个功能完善的图书管理系统，支持图书借阅、用户管理、统计分析等功能。

**在线演示：** http://demo.example.com
**视频介绍：** https://www.bilibili.com/video/BV1xx411c7mD

## ✨ 核心功能

- 🔐 用户认证：JWT + Spring Security
- 📚 图书管理：CRUD + 多条件查询
- 📊 数据统计：ECharts可视化
- ⚡ 性能优化：Redis缓存，QPS提升300%
- 🐳 容器部署：Docker一键启动

## 🛠️ 技术栈

**后端：**
- Spring Boot 2.7
- MyBatis Plus
- Redis 6.0
- MySQL 8.0

**前端：**
- Vue 3
- Element Plus
- Axios
- ECharts

**部署：**
- Docker
- Nginx

## 🚀 快速开始

### 环境要求
- JDK 8+
- MySQL 8.0+
- Redis 6.0+
- Node.js 14+

### 安装步骤

\`\`\`bash
# 1. 克隆项目
git clone https://github.com/你的用户名/book_management-system.git
cd book_management-system

# 2. 配置数据库
# 修改 src/main/resources/application.yml
# 导入 database/init.sql

# 3. 启动后端
mvn spring-boot:run

# 4. 启动前端
cd frontend
npm install
npm run dev
\`\`\`

访问 http://localhost:8080

## 📊 项目亮点

1. **性能优化**
   - 使用Redis缓存热点数据，查询速度提升80%
   - 数据库索引优化，慢查询从200ms降至50ms

2. **安全性**
   - 密码BCrypt加密
   - JWT身份认证
   - SQL注入防护

3. **架构设计**
   - 前后端分离
   - RESTful API
   - 统一异常处理

## 📷 项目截图

![登录页面](docs/images/login.png)
![主界面](docs/images/dashboard.png)

## 📝 更新日志

### v2.0 (2026-06)
- ✨ 前后端分离改造
- ✨ 添加Redis缓存
- 🐛 修复若干bug

### v1.0 (2026-01)
- 🔒 修复SQL注入漏洞
- 🔐 添加MD5密码加密
- ⚙️ 配置文件外部化

## 📖 API文档

Swagger地址：http://localhost:8080/swagger-ui.html

## 🤝 贡献指南

欢迎提交Issue和Pull Request！

## 📄 开源协议

MIT License

## 📧 联系方式

- 邮箱：zhangsan@example.com
- 微信：zhangsan123
- 博客：https://blog.example.com
```

---

### 5.2 Commit提交规范

#### 为什么需要规范？
- 阅读历史记录更清晰
- 自动生成CHANGELOG
- 体现专业素养（面试加分）

#### 规范格式：
```
<类型>(<范围>): <简短描述>

<详细描述>（可选）

<关联Issue>（可选）
```

#### 类型说明：

| 类型 | 说明 | 示例 |
|------|------|------|
| feat | 新功能 | feat: 添加图书借阅功能 |
| fix | 修复bug | fix: 修复登录失败的问题 |
| refactor | 重构代码 | refactor: 优化数据库连接逻辑 |
| docs | 文档更新 | docs: 更新README安装步骤 |
| style | 代码格式 | style: 统一代码缩进为4空格 |
| test | 测试相关 | test: 添加用户登录单元测试 |
| chore | 构建/工具 | chore: 升级依赖版本 |

#### 优秀示例：

```bash
# ✅ 好的commit
git commit -m "feat(auth): 添加JWT身份认证功能

实现了基于JWT的用户认证，包括：
- 登录时生成token
- 请求时验证token
- token过期自动刷新

Closes #12"

# ✅ 简短commit
git commit -m "fix: 修复图书查询SQL注入漏洞"

# ❌ 不好的commit
git commit -m "修改了一些东西"
git commit -m "update"
git commit -m "代码改完了"
```

---

### 5.3 .gitignore配置

**.gitignore作用：** 告诉Git哪些文件不需要提交

#### Java项目通用配置：

```bash
# 创建.gitignore文件
cat > .gitignore << 'EOF'
# ========== IDE相关 ==========
.idea/
*.iml
.vscode/
.DS_Store

# ========== 编译输出 ==========
bin/
target/
out/
build/

# ========== 依赖库 ==========
lib/
*.jar
*.war
*.ear

# ========== 配置文件（敏感信息） ==========
application-prod.yml
application-prod.properties
db.properties

# ========== 日志文件 ==========
logs/
*.log

# ========== 临时文件 ==========
*.tmp
*.bak
*.swp
*~

# ========== 操作系统 ==========
Thumbs.db
.DS_Store

# ========== Maven ==========
.mvn/
mvnw
mvnw.cmd

# ========== Node.js前端 ==========
node_modules/
dist/
.env.local
EOF

# 提交.gitignore
git add .gitignore
git commit -m "chore: 添加.gitignore配置"
```

---

### 5.4 分支管理策略

#### Git Flow工作流（适合团队项目）

```
main（主分支）：线上运行的代码
    ├── develop（开发分支）：开发中的代码
    │   ├── feature/login：新功能-登录
    │   ├── feature/book：新功能-图书管理
    │   └── feature/redis：新功能-Redis缓存
    ├── hotfix/sql-inject：紧急修复-SQL注入
    └── release/v1.0：发布分支
```

**操作流程：**

```bash
# 1. 创建develop分支
git checkout -b develop main

# 2. 开发新功能
git checkout -b feature/redis develop
# 开发...
git add .
git commit -m "feat: 添加Redis缓存"
git checkout develop
git merge feature/redis

# 3. 准备发布
git checkout -b release/v1.0 develop
# 测试、修bug...
git checkout main
git merge release/v1.0
git tag v1.0

# 4. 紧急修复
git checkout -b hotfix/security main
# 修复...
git checkout main
git merge hotfix/security
git tag v1.0.1
```

---

## 6. 面试加分技巧

### 6.1 GitHub Profile优化

#### 完善个人主页

**访问：** `https://github.com/你的用户名`

**必填项：**
```
头像：清晰的个人照片
Name：真实姓名
Bio：大三学生 | Java后端开发 | 2027届秋招
Location：北京
Email：公开邮箱
Website：个人博客（如果有）
```

#### 创建Profile README（高级技巧）

```bash
# 1. 创建与用户名同名的仓库
# 例如用户名是zhangsan，创建仓库：zhangsan

# 2. 添加README.md
# 这个README会显示在你的个人主页

# 3. 内容示例：
```

```markdown
# 👋 Hi, 我是张三

🎓 大三在读 | 计算机科学与技术
💼 寻找2027届Java后端开发岗位
📧 zhangsan@example.com

## 🛠️ 技术栈

![Java](https://img.shields.io/badge/-Java-007396?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=flat&logo=spring)
![MySQL](https://img.shields.io/badge/-MySQL-4479A1?style=flat&logo=mysql)
![Redis](https://img.shields.io/badge/-Redis-DC382D?style=flat&logo=redis)

## 📊 GitHub统计

![Stats](https://github-readme-stats.vercel.app/api?username=zhangsan&show_icons=true)

## 🔥 最近项目

- 📚 [图书管理系统](https://github.com/zhangsan/book-system) - Spring Boot + Vue3
- 🛒 [电商秒杀系统](https://github.com/zhangsan/seckill) - 高并发解决方案

## 📝 最新博客

- [Spring Boot性能优化实践](https://blog.example.com/spring-boot-optimize)
- [Redis缓存设计模式](https://blog.example.com/redis-patterns)
```

---

### 6.2 提交频率优化

**GitHub绿色方格 = 你的活跃度**

#### 理想的提交频率：

```
每天至少1次commit（绿色方格连续）
每周至少5天有commit
不要突击式commit（一天提交100次）
```

#### 保持活跃的方法：

```bash
# 1. 每天学习后提交笔记
cd my-learning-notes
git add .
git commit -m "docs: 今天学习了Spring AOP原理"
git push

# 2. 刷算法题后提交
cd leetcode-solutions
git add .
git commit -m "feat: 完成LeetCode 146题（LRU缓存）"
git push

# 3. 项目开发每完成一个小功能就提交
git commit -m "feat(book): 实现图书分类查询"
```

---

### 6.3 项目Star策略

**Star（收藏）优质项目 = 展示你的学习方向**

#### 建议Star的项目：

```
后端框架：
- spring-boot
- mybatis-plus
- redisson

优质项目：
- mall（电商项目）
- ruoyi（管理系统）
- jeecg-boot（代码生成）

学习资源：
- JavaGuide（面试宝典）
- CS-Notes（计算机基础）
- advanced-java（分布式）
```

#### 如何找优质项目？

```bash
# 1. GitHub Trending
访问：https://github.com/trending/java

# 2. Awesome系列
搜索：awesome-java
搜索：awesome-spring-boot

# 3. 根据关键词搜索
spring boot mall
spring boot e-commerce
java interview
```

---

### 6.4 简历中的GitHub描述

#### ✅ 正确示例：

```
GitHub: https://github.com/zhangsan
- 200+ commits | 5个完整项目 | 50+ Stars
- 核心项目：图书管理系统（Spring Boot + Vue3，500+ Star）
- 活跃贡献者，连续180天保持代码提交
```

#### ❌ 错误示例：

```
GitHub: https://github.com/zhangsan
（没有任何说明）
```

---

## 7. 故障排查与常见问题

### 问题1：push被拒绝

```bash
# 错误信息：
! [rejected]        main -> main (fetch first)

# 原因：远程有新的提交，本地落后了

# 解决：
git pull --rebase
git push
```

### 问题2：忘记写commit信息

```bash
# 情况：不小心执行了 git commit 没加 -m

# 会打开vim编辑器，怎么办？
# 1. 按 i 进入编辑模式
# 2. 输入commit信息
# 3. 按 Esc 退出编辑模式
# 4. 输入 :wq 保存退出
```

### 问题3：合并冲突

```bash
# 错误信息：
CONFLICT (content): Merge conflict in xxx.java

# 解决步骤：
# 1. 打开冲突文件，会看到：
<<<<<<< HEAD
你的代码
=======
别人的代码
>>>>>>> branch-name

# 2. 手动保留需要的代码，删除标记
# 3. git add <冲突文件>
# 4. git commit
```

### 问题4：不小心提交了敏感信息

```bash
# 危险！如果提交了密码/密钥

# 方法1：修改最后一次commit
git reset HEAD~1
# 删除敏感信息
git add .
git commit -m "fix: 移除敏感信息"
git push --force  # 强制推送（慎用！）

# 方法2：使用BFG清理历史
# 下载：https://rtyley.github.io/bfg-repo-cleaner/
java -jar bfg.jar --delete-files db.properties
git push --force
```

---

## 8. 学习路线建议

### 第1周：基础操作
- [ ] 完成GitHub账号注册和配置
- [ ] 练习 add/commit/push 基础命令
- [ ] 创建第一个仓库
- [ ] 写一个完整的README

### 第2周：分支管理
- [ ] 学习分支创建和切换
- [ ] 练习分支合并
- [ ] 了解Git Flow工作流
- [ ] 尝试解决一次合并冲突

### 第3周：协作开发
- [ ] Fork一个开源项目
- [ ] 提交第一个Pull Request
- [ ] 学习Code Review流程
- [ ] Star 50个优质项目

### 第4周：高级技巧
- [ ] 学习 rebase 和 cherry-pick
- [ ] 配置Git别名提高效率
- [ ] 优化GitHub Profile
- [ ] 总结Git使用经验写博客

---

## 9. 推荐资源

### 在线教程
- **Pro Git中文版**：https://git-scm.com/book/zh/v2
- **Learn Git Branching**：https://learngitbranching.js.org/?locale=zh_CN（可视化学习）
- **GitHub Docs**：https://docs.github.com/zh

### 视频教程
- B站搜索：Git从入门到精通
- B站搜索：GitHub协作开发教程

### 实践项目
- **first-contributions**：https://github.com/firstcontributions/first-contributions
  （专门给新手练习PR的项目）

---

## 10. 快捷键与技巧

### Git命令别名

```bash
# 配置别名，提高效率
git config --global alias.st status
git config --global alias.co checkout
git config --global alias.br branch
git config --global alias.ci commit
git config --global alias.lg "log --oneline --graph --all"

# 使用别名
git st  # 等同于 git status
git co main  # 等同于 git checkout main
git lg  # 漂亮的日志显示
```

### 命令行提示优化

```bash
# 安装oh-my-zsh（Mac/Linux）
sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

# 会显示当前分支，超级方便：
~/book-system (main) $
~/book-system (feature-redis) $
```

---

## 11. 本项目实战

### 你当前的GitHub状态

```bash
# 查看仓库信息
git remote -v
# origin  https://github.com/A1RER/book_management-system

# 查看当前分支
git branch
# * claude/review-repository-docs-KYX1u

# 查看提交历史
git log --oneline -5
```

### 接下来你应该做的

#### 今天：
```bash
# 1. 访问你的GitHub仓库
https://github.com/A1RER/book_management-system

# 2. 查看刚才的提交
点击 "Commits" 查看commit d4b3a0e

# 3. 创建Pull Request（如果需要合并到main）
点击 "Pull requests" → "New pull request"
选择 claude/review-repository-docs-KYX1u → main
```

#### 本周：
```bash
# 完善项目README
编辑 README.md，参考第5.1节的模板

# 添加项目截图
mkdir docs/images
# 运行项目，截图保存到images目录
git add docs/images/*.png
git commit -m "docs: 添加项目截图"
git push

# 添加开源协议
# GitHub网站上：Settings → Options → License → Choose MIT
```

#### 下周：
```bash
# 创建develop分支
git checkout -b develop
git push -u origin develop

# 以后所有新功能都在develop上开发
git checkout -b feature/service-layer develop
# 开发...
git add .
git commit -m "refactor: 提取Service业务层"
git push origin feature/service-layer
```

---

## 总结：GitHub对求职的重要性

### 面试官看你的GitHub时关注什么？

| 维度 | 权重 | 如何优化 |
|------|------|----------|
| **项目质量** | ⭐⭐⭐⭐⭐ | 2-3个完整项目，README详细 |
| **代码规范** | ⭐⭐⭐⭐ | 遵循命名规范，注释清晰 |
| **提交频率** | ⭐⭐⭐⭐ | 每周至少5天有commit |
| **技术广度** | ⭐⭐⭐ | Star多个技术栈的项目 |
| **协作能力** | ⭐⭐⭐ | 有PR记录，参与开源 |

### 行动清单

**本周必做：**
- [ ] 完善GitHub个人资料
- [ ] 为图书管理系统写详细README
- [ ] 学习并实践Git基础命令
- [ ] 配置.gitignore文件

**本月目标：**
- [ ] GitHub连续30天保持绿色
- [ ] Star 100个优质项目
- [ ] 提交第一个Pull Request
- [ ] 写3篇技术博客关联到GitHub

**秋招前：**
- [ ] 2-3个完整项目在GitHub
- [ ] 200+ commits
- [ ] 项目总Star数 > 50
- [ ] 参与1-2个开源项目

---

**记住：GitHub是你的第二份简历，每一个commit都是你技术能力的证明！**

有问题随时问我！🚀
