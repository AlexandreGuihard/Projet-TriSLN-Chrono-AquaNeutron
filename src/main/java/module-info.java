module com.trisln.aquaneutron.trislnaquaneutron {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.mail;
    requires mysql.connector.j;

    exports com.trisln.aquaneutron.bd;
    exports com.trisln.aquaneutron.controleurs;
    exports com.trisln.aquaneutron.modele;
    exports com.trisln.aquaneutron.vue;

    opens com.trisln.aquaneutron.controleurs to javafx.fxml;
    opens com.trisln.aquaneutron.vue to javafx.fxml;
}