mvn clean scala:compile compile install


java -cp lib/scala-reflect.jar:lib/scala-library.jar:lib/scala-compiler.jar:target/demo-1.0-SNAPSHOT.jar org.springframework.boot.loader.JarLauncher

