package groupIceCream;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerController implements EventHandler<ActionEvent> {

	protected AnchorPane view;

	protected Customer customer;

	protected Parent root;

	@FXML
	protected CustomerController customerController;

	@FXML
	protected MenuController menuController;

	@FXML
	protected OrderController orderController;

	@FXML
	protected TextField firstNameTextField;

	@FXML
	protected TextField lastNameTextField;

	@FXML
	protected Button continueBtn;

	public void handle(ActionEvent event) {
		
		continueBtn.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CustomerController.class.getResource("Menu.fxml"));
			if(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") ) {
				  JOptionPane.showMessageDialog(null, "Error! Enter a first name or last name!");
				           }
			else {
			
			
			try {
				customer = new Customer(firstNameTextField.getText(), lastNameTextField.getText());
				root = (Parent) loader.load();
				MenuController menuController = (MenuController) loader.getController();
				menuController.setCustomer(customer);

				closeCurrentWindow(continueBtn);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			view = (AnchorPane) root;
			Scene scene = new Scene(view);
			Stage stage = new Stage();
			stage.setTitle("Customer Information");
			stage.setScene(scene);
			stage.show();
			}
		});
	}

	protected void closeCurrentWindow(Button btn) {
		Stage stage = (Stage) btn.getScene().getWindow();
		stage.close();
	}
}