### 超级社团 ###
------------
本科毕业设计项目，分为移动Android端和服务器端。现在开源供大家交流与学习。本项目是服务器端的代码，Android端请查看这里。
所使用的技术有：

* Java Web技术
* MySQL
* Struts2
* ...

部署步骤：

1. 安装`MySQL`，并执行SQL脚本`service.sql`;
2. 修改`SuperAssociationServer/src/com/sa/db/DBManager.java`文件下的数据库用户名和密码以及地址;
3. 修改`SuperAssociationServer/src/com/sa/tools/GloblePath.java`文件下的图片文件存放路径;
4. 使用`Tomcat`启动该项目。

>注：生产上不要使用硬编码将地址等信息写死到代码中。