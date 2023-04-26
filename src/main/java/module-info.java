module com.example.demo1111 {
    requires javafx.controls;
    requires javafx.fxml;
            
            requires com.dlsc.formsfx;
                        
    opens com.example to javafx.fxml;
    exports com.example;
}