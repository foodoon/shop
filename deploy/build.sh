cd .. && git pull
tomcat_home=/usr/tomcat-shop
mvn clean package -Dmaven.test.skip=true 
mv target/shop-1.0-SNAPSHOT.war ROOT.war
rm -rf ${tomcat_home}/webapps/ROOT.war
rm -rf ${tomcat_home}/webapps/ROOT

cp ROOT.war ${tomcat_home}/webapps/

