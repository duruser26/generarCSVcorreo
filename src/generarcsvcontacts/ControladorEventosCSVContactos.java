// Este archivo es parte del proyecto GenerarCSVContactos, que permite generar un archivo CSV a partir de un archivo CSV de entrada.
// El controlador de eventos maneja la interacción del usuario con la interfaz gráfica para seleccionar archivos y generar el CSV.
// El proyecto está diseñado para ser utilizado con JavaFX y se espera que se implemente la funcionalidad de selección de archivos XLSX en el futuro.
// El código está organizado en un controlador de eventos que maneja la lógica de la interfaz gráfica y la generación del archivo CSV.
// Este controlador es responsable de manejar los eventos de los botones, seleccionar archivos y directorios, y generar el archivo CSV final.
// El controlador utiliza clases auxiliares como DAOHuesped y GenerarCSVContactos para interactuar con los datos de los huéspedes y generar el contenido del CSV.

package generarcsvcontacts;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class ControladorEventosCSVContactos {

    @FXML private TextField csvPathField;
    @FXML private TextField csvOutputPathField;
    @FXML private TextField fileNameField;
    @FXML private Button xlsxButton;
    @FXML private Button csvPathButton;
    @FXML private Button generarCsvButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Este método se llama al inicializar el controlador
    // Aquí se configuran los eventos de los botones
    @FXML
    private void initialize() {
        xlsxButton.setOnAction(e -> seleccionarCSV()); // <- Cambiar el nombre del botón a seleccionarXLSX a futuro cuando se implemente la selección de archivos XLSX
        csvPathButton.setOnAction(e -> seleccionarDirectorioSalida());
        generarCsvButton.setOnAction(e -> generarCSV());
    }

    // Métodos para elegir el archivo CSV y el directorio de salida
    // Estos métodos se pueden cambiar a seleccionarXLSX y seleccionarDirectorioSalidaXLSX a futuro cuando se implemente la selección de archivos XLSX
    private void seleccionarCSV() { // <- Cambiar el nombre a seleccionarXLSX a futuro cuando se implemente la selección de archivos XLSX
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo CSV"); // <- Cambiar el nombre a Seleccionar archivo XLSX a futuro cuando se implemente la selección de archivos XLSX
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV", "*.csv")); // <- Cambiar las extensiones a CSV y XSLX a futuro cuando se implemente la selección de archivos XLSX
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            csvPathField.setText(file.getAbsolutePath());
        }
    }

    // Este método permite seleccionar un directorio de salida para guardar el archivo CSV generado
    private void seleccionarDirectorioSalida() {
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Seleccionar directorio de salida");
        File dir = dirChooser.showDialog(stage);
        if (dir != null) {
            csvOutputPathField.setText(dir.getAbsolutePath());
        }
    }

    // Este método genera el archivo CSV a partir del archivo CSV (por ahora) seleccionado
    // En el futuro, se cambiará a generarCSVDesdeXLSX cuando se implemente la selección de archivos XLSX
    private void generarCSV() {
        try {
            String xlsxPath = csvPathField.getText();
            String outputPath = csvOutputPathField.getText();
            String fileName = fileNameField.getText();

            if (xlsxPath.isEmpty() || outputPath.isEmpty() || fileName.isEmpty()) {
                mostrarAlerta("Error", "Por favor completa todos los campos.");
                return;
            }

            File archivoEntrada = new File(xlsxPath);
            DAOHuesped dao = new DAOHuesped(archivoEntrada);
            GenerarCSVContactos generador = new GenerarCSVContactos(dao);

            String csvContent = generador.generarCSV();
            File archivoSalida = new File(outputPath, fileName + ".csv");

            try (OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(archivoSalida), StandardCharsets.UTF_8)) {
                writer.write('\uFEFF'); // Escribir BOM para UTF-8
                writer.write(csvContent);
            }

            mostrarAlerta("Éxito", "CSV generado en: " + archivoSalida.getAbsolutePath());

        } catch (ErrorDAO | IOException e) {
            mostrarAlerta("Error", "No se pudo generar el CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Este método muestra una alerta con el título y mensaje proporcionados
    // Se utiliza para informar al usuario sobre el éxito o error en la generación del CSV
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}