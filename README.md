# QRLibrarian

IntelliJ IDEA，旗舰版，不是社区版 http://www.jetbrains.com/idea/  
Wildfly，这个要装，因为是本地调试的 http://download.jboss.org/wildfly/9.0.2.Final/wildfly-9.0.2.Final.zip  
MySQL不用装，连我的就行了，但是需要下载驱动 http://dev.mysql.com/downloads/connector/j/  

__1.__  首先，配置Wildfly，也就是JBoss  
  到解压目录，运行 `bin/add-user.bat`，注册管理员和用户账户  
  运行 `bin/standalone.bat`，打开浏览器 `localhost:9990`，有界面就成功了  
  
__2.__  解压出`mysql-connector-java-5.1.38-bin.jar`  
  进入 `localhost:9990`，到`Deployments`标签，点`Add`加入jar文件  
  这步可能不成功，用Firefox浏览器就行了  
  然后切换到`Configuration`，`Subsystems -> DataSources -> Non-XA -> Add`  
  选择MySQL，连接 `211.80.59.230:3306`，连接成功即可  
  
__3.__  打开IntelliJ IDEA，配置GitHub账号  
  `Settings -> Version Control -> GitHub`  
  
__4.__  导入项目  
  
__5.__  添加应用服务器  
  `Settings -> Build,Execution,Deployment -> Application Servers`  
  添加JBoss Server即Wildfly解压目录  
  
__6.__  调试环境  
  `Run/Debug Configurations -> Add -> JBoss Server -> Local`  
  右边Deployment标签页加war包  
  
__7.__  `Open Module Settings`，确认包依赖没有问题，进入`Artifacts`标签页，把右边的包全部点上  
  
__8.__  运行调试，有时候会遇到`403 Forbidden`的情况，没有什么道理  
  一般建几个空的`Web Application`跑一跑可能会变好，也可能不会  
  
__9.__  其他问题再说……  
  
