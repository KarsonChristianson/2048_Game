module edu.umn.d.chri5410._2048_correct {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens edu.umn.d.chri5410._2048_correct to javafx.fxml;
    exports edu.umn.d.chri5410._2048_correct;
}