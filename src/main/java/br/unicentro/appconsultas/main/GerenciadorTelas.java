package br.unicentro.appconsultas.main;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GerenciadorTelas {

    // Referência para a única janela principal da aplicação.
    private static Stage stage;

    // Recebe o Stage criado pelo JavaFX na classe Main.
    public static void setStage(Stage stagePrincipal) {
        stage = stagePrincipal;
    }

    // Carrega o FXML e troca a Scene exibida no Stage principal.
    public static void trocarTela(String arquivoFXML, String titulo) throws IOException {
        // 1. Localiza e prepara o arquivo FXML.
        FXMLLoader loader = new FXMLLoader(GerenciadorTelas.class.getResource(arquivoFXML));
        // 2. Carrega o FXML e cria uma nova Scene.
        Scene scene = new Scene(loader.load(), 1000, 650);
        // 3. Define o título da janela.
        stage.setTitle(titulo);
        // 4. Coloca a nova Scene no MESMO Stage.
        stage.setScene(scene);
        stage.show();
    }
}
 