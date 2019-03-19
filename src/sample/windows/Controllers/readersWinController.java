package sample.windows.Controllers;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.interfaces.impl.DbReaderList;
import sample.workedClasses.User;
import java.util.ResourceBundle;

import java.sql.SQLException;

public class readersWinController {

    @FXML
    private TableColumn<User, String> surname;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> Male;

    @FXML
    private TableColumn<User, String> login;

    @FXML
    private TableColumn<User, String> nowBook;

    @FXML
    private Button out;

    @FXML
    private TableView<User> table;

    @FXML
    private Label label;

    @FXML
    void initialize()
    {
        out.setOnAction(event -> {
            Controller button = new Controller();
            button.newWinButton("/sample/windows/FXML's/personWindow.fxml", out, "/sample/cssFiles/personWindow.css",1);
        });
        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        nowBook.setCellValueFactory(new PropertyValueFactory<User, String>("nowBook"));
        Male.setCellValueFactory(new PropertyValueFactory<User, String>("Male"));

        DbReaderList dbReaderList = new DbReaderList();
        try {
            dbReaderList.addAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(dbReaderList.getReadersList());

        label.setText(label.getText()  + dbReaderList.getReadersList().size());

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PersonPageController.PersonPage();
            }
        });
    }
}
