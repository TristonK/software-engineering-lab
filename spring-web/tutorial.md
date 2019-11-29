# App Industry System

工业App分级系统

### Install

- Java 1.8
- Mysql 5.7
- 导入IDE IntelliJ IDEA, Maven进行构建

    Import Project -> 选择项目文件夹`spring-web` -> Import project from externel model & choose Maven 
    -> Next -> Next -> Finish

- 可能出现问题：

1. `Error:java: 无效的源发行版: 1.8`
   - 问题原因，Java版本不匹配
   - 解决方法：检查`.idea/misc.xml`,`.idea/compiler.xml`以及项目module setting中的语言设定均为Java 1.8；修改后，maven clean再重新运行。

### Packages

- `com.example.springweb.controller`: 处理http请求
- `com.example.springweb.service`: 接受请求后实现业务逻辑
- `com.example.springweb.mapper`: 实现Mybatis与Mysql交互映射
- `com.example.springweb.dao`: 定义实体及其属性
- `resources/templates`：存放html模版文件
- `resources/static`：存放静态文件
- `resources/application.properties`：对项目进行配置，如Mysql，Log等
- `test`：实现单元测试

### Configures

* Spring mvc
* Mysql
* Mybatis
* Thymeleaf

### Usage

* 在运行前，需要首先配置数据库以及修改对应的Mapper文件
  * 数据库连接配置在`application.properties` 中，修改相应的连接，用户名等
  * 之后可以在数据库中创建User表，里面可以有id，name，password等属性
  * 然后创建Mapper借口，用`@Mapper`注释，表明这里可以实现数据库映射
  * `@Select`后面写入sql语句，`@Results`中加入对象属性与数据库列名的映射，例如：项目中HelloUser的属性为(id, name, password)，数据库中列stringId对应id，列user_name对应name，所以需要在这里声明
* 由于SpringBoot内置了Tomcat服务器，点击运行即可通过`http://localhost:8080` 进行访问
* 实例：
  * 访问`http://localhost:8080/hello`，在`com.example.springweb.controller.HelloController` 中的hello()方法上有`@RequestMapping("/hello")` ，表示`\hello`请求会由此方法进行处理。
  * 在这个例子中，会在后台获取数据库中的User列表并打印，之后跳转到hello.html页面
  * 如果你想在前端显示后端的数据，你可以参考ThymeleafController中的实现，利用model可以实现将后端数据传递到前端，在前端利用Thymeleaf渲染后在浏览器中显示

### Guides
Other Tutorials
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

