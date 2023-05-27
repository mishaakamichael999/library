package project.library;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btn_login;

    @FXML
    private ImageView imageUser;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label wrong;

//    Statement ,  прочитати про ці всі класи!!!



    public static Stage stage;
    @FXML
    public void checkLogin(ActionEvent event) throws IOException {
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            DataBase.preparedStatement = null;
            DataBase.result = null;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                        DataBase.connection = DataBase.getConnection();
                String sql = "SELECT * FROM admin WHERE login = ? and password = ?";
                DataBase.preparedStatement = DataBase.connection.prepareStatement(sql);
                DataBase.preparedStatement.setString(1, DataBase.databaseUser);
                DataBase.preparedStatement.setString(2, DataBase.databasePassword);
                DataBase.result = DataBase.preparedStatement.executeQuery();
                    if(DataBase.result.next()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Вхід виконано");
                        alert.showAndWait();
                        Node node = (Node)event.getSource();
                         this.stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("menu.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    }
            }
            catch(Exception e)
            {e.printStackTrace();}
            finally {
                if (DataBase.result != null) {
                    try {
                        DataBase.result.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (  DataBase.preparedStatement != null) {
                    try {
                        DataBase.preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Введіть логін та пароль");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Невірний логін або пароль");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(getClass().getResourceAsStream("/images/group.png"));
        imageUser.setImage(image);
    }

}
