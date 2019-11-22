package CambioDivisas;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CambioDivisa extends Application {
	
	//Model
	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa("Libra", 0.8873);
	private Divisa dolar = new Divisa("Dolar", 1.2007);
	private Divisa yen = new Divisa("Yen", 133.59);

	
	//vista
	
	private TextField monedaText;
	private TextField resultadoText;
	private ComboBox<String> monedaInicialCombo;
	private ComboBox<String> monedaFinalCombo;
	private Button ejecutar;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		monedaText = new TextField();
		monedaText.setPrefColumnCount(4);
		
		resultadoText = new TextField();
		resultadoText.setPrefColumnCount(4);
		resultadoText.setDisable(true);
		
		monedaInicialCombo = new ComboBox<String>();
		monedaInicialCombo.getItems().addAll(euro.getNombre(), libra.getNombre(),dolar.getNombre(),yen.getNombre());
		monedaInicialCombo.setPromptText("Origen");
		
		monedaFinalCombo = new ComboBox<String>();
		monedaFinalCombo.getItems().addAll(euro.getNombre(), libra.getNombre(),dolar.getNombre(),yen.getNombre());
		monedaFinalCombo.setPromptText("Destino");
		
		ejecutar= new Button ("Cambiar");
		ejecutar.setDefaultButton(true);
		ejecutar.setOnAction(e -> onCheckButtonAction(e));
		
		HBox monedaInicialBox =new HBox(5,monedaText,monedaInicialCombo);
		monedaInicialBox.setAlignment(Pos.CENTER);
		HBox monedaFinalBox =new HBox(5,resultadoText,monedaFinalCombo);
		monedaFinalBox.setAlignment(Pos.CENTER);
		HBox botonBox =new HBox(5,ejecutar);
		botonBox.setAlignment(Pos.CENTER);
		VBox root = new VBox(5,monedaInicialBox,monedaFinalBox,botonBox);
		root.setAlignment(Pos.CENTER);
		
        Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
		
	private void onCheckButtonAction(ActionEvent e) {
		try {
		String comboOrigen = monedaInicialCombo.getSelectionModel().getSelectedItem();
		String comboDestino = monedaFinalCombo.getSelectionModel().getSelectedItem();
		Divisa origen = yen;
		Divisa destino = dolar;
		switch (comboOrigen) {
			case "Euro":
				origen = euro;
				break;
			case "Libra":
				origen = libra;
				break;

			case "Dolar":
				origen = dolar;
				break;

			case "Yen":
				origen = yen;
				break;
		}

		switch (comboDestino) {
			case "Euro":
				destino = euro;
				break;

			case "Libra":
				destino = libra;
				break;

			case "Dolar":
				destino = dolar;
				break;

			case "Yen":
				destino = yen;
				break;
		}
		double monedaInicial=Double.valueOf(monedaText.getText());
		String resultado=""+(Divisa.fromTo(origen, destino,monedaInicial));
		resultadoText.setText(resultado);
		}catch (NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("CambioDivisaApp");
			alert.setHeaderText("Error");
			alert.setContentText("El numero introducido no es valido");
			alert.showAndWait();
		}
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
