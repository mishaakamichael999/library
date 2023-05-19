package project.library;
import com.mysql.cj.log.Log;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddingController {
    @FXML
    private TextField authorField;

    @FXML
    private Button btn_edit;

    @FXML
    private Label btn_status;

    @FXML
    private FontAwesomeIconView edit;

    @FXML
    private Button exitAndAcceptBtn;

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker publishDateField;

    @FXML
    private TextField qualityField;

    @FXML
    private AnchorPane stageAdd;


    private FileInputStream fileInputStream;

    Image image;

    @FXML
    void btnAcceptAdd(ActionEvent event) throws FileNotFoundException {

        try {
            String sql = "insert into books(name, author, publishDate, quality, regDate, status, image) values (?, ?, ?, ?, ?, ?, ?)";
            LoginController.preparedStatement = LoginController.connection.prepareStatement(sql);

            LoginController.preparedStatement.setString(1, nameField.getText());
            LoginController.preparedStatement.setString(2, authorField.getText());

            LocalDate publishDateFieldValueDate = publishDateField.getValue();
            String pattern = "yyyy-MM-dd";
            String datePattern = publishDateFieldValueDate.format(DateTimeFormatter.ofPattern(pattern));
            Date date = Date.valueOf(datePattern);
            LoginController.preparedStatement.setDate(3, date);

            LoginController.preparedStatement.setString(4, qualityField.getText());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            LoginController.preparedStatement.setString(5, dtf.format(now));

            LoginController.preparedStatement.setInt(6, 1);
            LoginController.preparedStatement.setBinaryStream(7,  (InputStream)MenuController.bookImage);
            System.out.println("done");
            LoginController.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void btn_edit_image(ActionEvent event) throws FileNotFoundException {
            FileChooser open = new FileChooser();
            open.setTitle("Image File");
            open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file", "*png", "*jpg"));
        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
            File imageFile = open.showOpenDialog(stage);
            if (imageFile != null) {
                btn_edit.setStyle("-fx-background-color: green");
                MenuController.bookImage = new FileInputStream(imageFile);
            }
    }
//    public void selectSavedBooks() {
//
//        saveBook sBook = save_tableView.getSelectionModel().getSelectedItem();
//        int num = save_tableView.getSelectionModel().getFocusedIndex();
//
//        if ((num - 1) < -1) {
//            return;
//        }
//
//        String uri = "file:" + sBook.getImage();
//
//        image = new Image(uri, 117, 148, false, true);
//        save_imageView.setImage(image);
//
//        getData.savedImage = sBook.getImage();
//        getData.savedTitle = sBook.getTitle();
//
//    }

}
