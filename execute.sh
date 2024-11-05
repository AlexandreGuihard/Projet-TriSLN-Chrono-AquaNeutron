clear
javac -d bin --module-path /usr/share/openjfx/lib:/usr/share/java/mariadb-java-client.jar --add-modules javafx.controls,javafx.fxml src/vue/*.java
java -cp bin --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml src.vue.TriSLN