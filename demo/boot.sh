mvn clean scala:compile compile package

java -cp /usr/home/pcohen/dev/java/maven/apache-maven-3.1.1/boot/plexus-classworlds-2.5.1.jar:/home/pcohen/dev/scala/scala-2.11.0-M5/lib/scala-compiler.jar:/home/pcohen/dev/scala/scala-2.11.0-M5/lib/scala-library.jar:/home/pcohen/dev/scala/scala-2.11.0-M5/lib/scala-reflect.jar:target/demo-1.0-SNAPSHOT.jar:/home/pcohen/.m2/repository/org/springframework/boot/spring-boot/0.5.0.M5/spring-boot-0.5.0.M5.jar:/home/pcohen/.m2/repository/org/springframework/boot/spring-boot-starter/0.5.0.M5/spring-boot-starter-0.5.0.M5.jar:/home/pcohen/.m2/repository/org/springframework/boot/spring-boot-actuator/0.5.0.M5/spring-boot-actuator-0.5.0.M5.jar:/home/pcohen/.m2/repository/org/springframework/spring-core/4.0.0.M3/spring-core-4.0.0.M3.jar:/home/pcohen/.m2/repository/org/springframework/spring-context/4.0.0.M3/spring-context-4.0.0.M3.jar:/home/pcohen/.m2/repository/org/springframework/spring-beans/4.0.0.M3/spring-beans-4.0.0.M3.jar:/home/pcohen/.m2/repository/org/slf4j/jcl-over-slf4j/1.7.5/jcl-over-slf4j-1.7.5.jar:/home/pcohen/.m2/repository/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar:/home/pcohen/.m2/repository/org/springframework/spring-aop/4.0.0.M3/spring-aop-4.0.0.M3.jar:/home/pcohen/.m2/repository/org/springframework/spring-expression/4.0.0.M3/spring-expression-4.0.0.M3.jar:/home/pcohen/.m2/repository/ch/qos/logback/logback-core/1.0.13/logback-core-1.0.13.jar:/home/pcohen/.m2/repository/ch/qos/logback/logback-classic/1.0.13/logback-classic-1.0.13.jar:/home/pcohen/.m2/repository/org/jboss/logging/jboss-logging/3.1.0.CR2/jboss-logging-3.1.0.CR2.jar:/home/pcohen/.m2/repository/org/springframework/boot/spring-boot-starter-actuator/0.5.0.M5/spring-boot-starter-actuator-0.5.0.M5.jar:/home/pcohen/.m2/repository/org/slf4j/jul-to-slf4j/1.7.5/jul-to-slf4j-1.7.5.jar:/home/pcohen/.m2/repository/org/springframework/boot/spring-boot-starter-web/0.5.0.M5/spring-boot-starter-web-0.5.0.M5.jar:/home/pcohen/.m2/repository/org/springframework/boot/spring-boot-starter-logging/0.5.0.M5/spring-boot-starter-logging-0.5.0.M5.jar:/home/pcohen/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/7.0.42/tomcat-embed-core-7.0.42.jar:/home/pcohen/.m2/repository/org/apache/tomcat/embed/tomcat-embed-logging-juli/7.0.42/tomcat-embed-logging-juli-7.0.42.jar:/home/pcohen/.m2/repository/org/springframework/spring-webmvc/4.0.0.M3/spring-webmvc-4.0.0.M3.jar:/home/pcohen/.m2/repository/org/springframework/spring-web/4.0.0.M3/spring-web-4.0.0.M3.jar:/home/pcohen/.m2/repository/aopalliance/aopalliance/1.0/aopalliance-1.0.jar:/home/pcohen/.m2/repository/org/hibernate/hibernate-validator/4.3.1.Final/hibernate-validator-4.3.1.Final.jar:/home/pcohen/.m2/repository/javax/validation/validation-api/1.0.0.GA/validation-api-1.0.0.GA.jar:/home/pcohen/.m2/repository/com/google/code/gson/gson/2.2.4/gson-2.2.4.jar:/home/pcohen/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/0.5.0.M5/spring-boot-autoconfigure-0.5.0.M5.jar:/home/pcohen/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.2.2/jackson-core-2.2.2.jar:/home/pcohen/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.2.2/jackson-databind-2.2.2.jar:/home/pcohen/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.2.2/jackson-annotations-2.2.2.jar test.pcohen.scaladays.demo.Application
