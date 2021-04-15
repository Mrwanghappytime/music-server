# music-server
springboot+mybatis+shiro+redis 开发的一套后端服务代码，主要功能有权限控制，跨域问题的解决，文件上传，session在多服务共享问题

本项目前端请使用https://github.com/Yin-Hongwei/music-website.git 中的music-manager目录和music-client，本项目为后端服务的改进

开发环境：springboot 2.3.4.RELEASE + redis5.x + mysql8.x + jdk8

在部署之前，需要先执行数据库脚本文件tq_music.sql文件，在resources目录下，修改application.yaml文件，主要修改的内容为数据库数据源相关配置和redis相关配置和静态资源路径，服务器端口不需要改

第一次进行部署时因为数据库中密码为明文密码，所以需要打开springboot主方法下的注释代码，供修改数据库中的密码，登录时的密码为数据库脚本中插入时的密码，如果想开发的情况下，请参考接口设计文档。

部署可以直接从idea打开文件，也可以进行到music-server目录，执行mvn package命令，然后执行java -jar ./target/music-server-0.0.1-SNAPSHOT.jar命令


