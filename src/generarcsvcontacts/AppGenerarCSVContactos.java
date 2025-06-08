// Clase principal

package generarcsvcontacts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppGenerarCSVContactos extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/generarcsvcontacts/graficosCSVContactos.fxml"));
        Parent root = loader.load();
        
        ControladorEventosCSVContactos controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Generador CSV para Google Contacts");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
