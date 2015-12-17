# QRLibrarian

IntelliJ IDEA，旗舰版，不是社区版 http://www.jetbrains.com/idea/  
Wildfly http://download.jboss.org/wildfly/9.0.2.Final/wildfly-9.0.2.Final.zip  
MySQL不用装，连我的就行了，但是需要下载驱动 http://dev.mysql.com/downloads/connector/j/  

1.首先，配置Wildfly，也就是JBoss 
  到解压目录，运行 bin/add-user.bat，注册管理员和用户账户  
  运行 bin/standalone.bat，打开浏览器 localhost:9990，有界面就成功了  
  
2.解压出mysql-connector-java-5.1.38-bin.jar  
  进入 localhost:9990，到Deployments标签，点Add加入jar文件  
  这步可能不成功，用Firefox浏览器就行了  
  然后切换到Configuration，Subsystems -> DataSources -> Non-XA -> Add  
  选择MySQL，连接 211.80.59.230:3306，连接成功即可  
  
3.打开IntelliJ IDEA，配置GitHub账号  
  Settings -> Version Control -> GitHub  
  
4.导入项目  
  
5.添加应用服务器  
  Settings -> Build,Execution,Deployment -> Application Servers  
  添加JBoss Server即Wildfly解压目录  
  
6.调试环境  
  Run/Debug Configurations -> Add -> JBoss Server -> Local  
  右边Deployment标签页加war包  
  
7.其他问题再说……
  
