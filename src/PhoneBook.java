import javafx.application.Application;
import static javafx.application.Application.launch;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;

public class PhoneBook extends Application {
	Label lblName;
	Label lblNumber;
	Label lblEmail;

	TextField txtName;
	TextField txtNumber;
	TextField txtEmail;

	Button btnNext;
	Button btnPrevious;
	Button btnSave;
	Button btnUpdate;
	Button btnDelete;
	Button btnPrint;

	ArrayList<Contact> contacts = new ArrayList<Contact>();
	Scanner scan = new Scanner(System.in);
	int position;
	String number;

	// add contacts to the contact list
	public void save() {
		String name = txtName.getText();
		String number = txtNumber.getText();
		String email = txtEmail.getText();
		Contact contact = new Contact(name, number, email);

		if (isEmpty()) {// check if all the fields are empty
			showAlert("No field can be empty!");// show alert if any of the fields is empty
		} else if (!isNumeric(number)) {// check if the phone number field is empty
			showAlert("The phone number field must be number!");// show alert if it is empty
		} else {
			contacts.add(contact);

			txtNumber.setText("");
			txtName.setText("");
			txtEmail.setText("");
		}
	}

//print all the contacts in the list
	public void printContact() {

//Use for each loop to print the contacts in the list
		for (Contact c : contacts) {
			System.out.println(c + " ");
		}
	}

	// update the information for the contact list
	public void update() {
		String name = txtName.getText();
		String number = txtNumber.getText();
		String email = txtEmail.getText();
		Contact contact = new Contact(name, number, email);
/// check if any of the fields is empty
		if (isEmpty()) {
			showAlert("No field can be empty!");
			// check to make sure that all the input in the phone number field is number
		} else if (!isNumeric(number)) {
			showAlert("The phone number field must be number!");
		} else {
// add contact to the contact list
			contacts.add(contact);

			txtNumber.setText("");
			txtName.setText("");
			txtEmail.setText("");
			System.out.println("\n\nThis is the updated contact list!");
			printContact();
		}

	}

	// delete the contact from the list
	public boolean delete() {
		System.out.println("\n\n The contact list: ");
		printContact();// print out the list so users can see what they need to delete
		System.out.print("Please enter the contact that you want to delete: ");
		String name = scan.nextLine();

		boolean deleted = false;
		if (this.contacts.size() > 0) {// check if the contact list empty or not
			for (Contact con : this.contacts) {
				if (con.getName().equalsIgnoreCase(name)) {// check if the input from user match with one of the element
															// in the list that user need to delete
					this.contacts.remove(con);// delete the contact that user need to delete
					deleted = true;
					break;
				}
			}
		}
		printContact();// print the list after deleted
		return deleted;
	}

	//set the information back in the text field base on the position
	public void setContacts(String contact) {

		txtName.setText(contacts.get(position).getName());
		txtNumber.setText(contacts.get(position).getNumber());
		txtEmail.setText(contacts.get(position).getEmail());
	}

// show the contact base on the position of the 
	public void showContacts() {
		setContacts("" + contacts.get(position));
	}

	// previous contact in the list
	public void previous() {
//check if position of the contact is not the first one
		if (position > 0) {
			position--;

			showContacts();

		} else {
			showAlert("You are at the first contact in the list!");
		}
	}

	// next contact in the list
	public void next() {
//check if position of the contact is not the last one
		if (position < contacts.size() - 1) {

			position++;

			showContacts();
		} else {
			showAlert("You are at the last contact in the list!");
		}

	}

//validate the phone number field
	public boolean isNumeric(String number) {
		try {
			Double.parseDouble(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Validate if the fields are empty
	public boolean isEmpty() {

		return (((txtName.getText().equals(""))) || (txtNumber.getText().equals(""))
				|| (txtEmail.getText().equals("")));

	}

	// show alert to users
	public void showAlert(String msg) {
		Alert a = new Alert(Alert.AlertType.WARNING);
		a.setTitle("Contact warning! ");
		a.setHeaderText("Invalid input!");
		a.setContentText(msg);
		a.show();
	}

	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Contact");
			GridPane pane = new GridPane();

			lblName = new Label("Name");
			pane.add(lblName, 0, 0);

			lblNumber = new Label("Number");
			pane.add(lblNumber, 0, 1);

			lblEmail = new Label("Email");
			pane.add(lblEmail, 0, 2);

			txtName = new TextField();
			pane.add(txtName, 1, 0, 3, 1);

			txtNumber = new TextField();
			pane.add(txtNumber, 1, 1, 3, 1);

			txtEmail = new TextField();
			pane.add(txtEmail, 1, 2, 3, 1);

			btnNext = new Button("Next");
			pane.add(btnNext, 4, 0, 5, 1);

			btnPrevious = new Button("Previous");
			pane.add(btnPrevious, 4, 1, 3, 1);

			btnSave = new Button("Save");
			pane.add(btnSave, 0, 4, 2, 1);

			btnUpdate = new Button("Update");
			pane.add(btnUpdate, 2, 4, 3, 1);

			btnDelete = new Button("Delete");
			pane.add(btnDelete, 4, 4, 2, 1);

			btnPrint = new Button("Print");
			pane.add(btnPrint, 6, 4, 2, 1);

			pane.setHgap(5);
			pane.setVgap(5);
			pane.setPadding(new Insets(10, 10, 10, 10));
			btnSave.setOnAction(e -> save());
			btnPrint.setOnAction(e -> printContact());
			btnUpdate.setOnAction(e -> update());
			btnDelete.setOnAction(e -> delete());
			btnPrevious.setOnAction(e -> previous());
			btnNext.setOnAction(e -> next());

			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("Error occured " + e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
