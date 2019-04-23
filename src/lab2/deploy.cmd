set  CATALINA_HOME=C:\Users\chris\Downloads\apache-tomcat-9.0.16
java -cp "%CATALINA_HOME%\webapps\axis\WEB-INF\lib\axis.jar;%CATALINA_HOME%\webapps\axis\WEB-INF\lib\jaxrpc.jar;%CATALINA_HOME%\webapps\axis\WEB-INF\lib\commons-logging-1.0.4.jar;%CATALINA_HOME%\webapps\axis\WEB-INF\lib\commons-discovery-0.2.jar;%CATALINA_HOME%\webapps\axis\WEB-INF\lib\saaj.jar;%CATALINA_HOME%\common\lib\activation.jar;%CATALINA_HOME%\common\lib\mail.jar" org.apache.axis.client.AdminClient StoreService.wsdd
pause
