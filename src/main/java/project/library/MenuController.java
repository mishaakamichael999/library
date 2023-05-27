package project.library;

import com.mysql.cj.jdbc.Blob;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Collection;
import java.util.ResourceBundle;



public class MenuController implements Initializable {
    @FXML
    private Button add;

    @FXML
    private Button arrow_btn;

    @FXML
    private Button bars_btn;

    @FXML
    private ImageView book;

    @FXML
    private Circle circle_image;

    @FXML
    private Button delete;

    @FXML
    private Button edit_btn;

    @FXML
    private FontAwesomeIconView edit_icon;

    @FXML
    private Button info;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane mainCenter_form;
    @FXML
    private TableView<Books> table;
    @FXML
    private TableColumn<Books, Integer> idColm;
    @FXML
    private TableColumn<Books, String> nameColm;
    @FXML
    private TableColumn<Books, String> authorColm;
    @FXML
    private TableColumn<Books, Date> publishColm;
    @FXML
    private TableColumn<Books, String> qualityColm;
    @FXML
    private TableColumn<Books, Date> regColm;
    @FXML
    private TableColumn<Books, String> statusColm;
    @FXML
    private TableColumn<Books, Blob> imageColm;

    @FXML
    private AnchorPane nav_form;

    @FXML
    private ImageView smile;
    @FXML
    private ImageView coverOfBook;

    @FXML
    private Button update;

    private Image image;
    public static FileInputStream bookImage;
    public static DatePicker publishDatePicker;

    ObservableList<Books> tableData = FXCollections.observableArrayList();





    public void logoutClick(ActionEvent event){
        try {
            if(event.getSource() == logout) {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

                Stage stage = new Stage();
                stage.setResizable(false);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                scene.getRoot().requestFocus();
                stage.show();


                logout.getScene().getWindow().hide();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sliderArrow() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(.5));
        slide.setNode(nav_form);
        slide.setToX(-188);

        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(.5));
        slide1.setNode(mainCenter_form);
        slide1.setToX(-188 + 5);

        slide.setOnFinished((ActionEvent event) -> {
            arrow_btn.setVisible(false);
            bars_btn.setVisible(true);
        });

        slide1.play();
        slide.play();

    }

    public void sliderBars() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(.5));
        slide.setNode(nav_form);
        slide.setToX(0);

        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(.5));
        slide1.setNode(mainCenter_form);
        slide1.setToX(0);

        slide.setOnFinished((ActionEvent event) -> {
            arrow_btn.setVisible(true);
            bars_btn.setVisible(false);
        });

        slide1.play();
        slide.play();
    }
    public void insertImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file", "*png", "*jpg"));
        Stage stage = (Stage) nav_form.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {
            image = new Image(file.toURI().toString(), 87, 73, false, true);
            circle_image.setFill(new ImagePattern(image));
//            getData.path = file.getAbsolutePath();

//            changeProfile();

        }
    }

    public void designInserImage() {

        edit_btn.setVisible(false);

        circle_image.setOnMouseEntered((MouseEvent event) -> {

            edit_btn.setVisible(true);

        });

        circle_image.setOnMouseExited((MouseEvent event) -> {

            edit_btn.setVisible(false);

        });

        edit_btn.setOnMouseEntered((MouseEvent event) -> {

            edit_btn.setVisible(true);
            edit_icon.setFill(Color.valueOf("#fff"));

        });

        edit_btn.setOnMousePressed((MouseEvent event) -> {

            edit_btn.setVisible(true);
            edit_icon.setFill(Color.RED);

        });

        edit_btn.setOnMouseExited((MouseEvent event) -> {

            edit_btn.setVisible(false);

        });

    }

    public void deleteRowFromTable (ActionEvent event){
        table.getItems().removeAll(table.getSelectionModel().getSelectedItems());

    }

    @FXML
    void btn_info(ActionEvent event) {
        DataBase.preparedStatement = null;
        DataBase.result = null;
        try{
                String sql = "SELECT * FROM books";

            DataBase.preparedStatement = DataBase.connection.prepareStatement(sql);
            DataBase.result = DataBase.preparedStatement.executeQuery();



                while (DataBase.result.next()){
                    boolean isActive = DataBase.result.getBoolean("status");
                    String rowAsString;
                    if (isActive) {
                        rowAsString = "Доступно";
                    } else {
                      rowAsString = "Недоступно";
                    }
                    tableData.add(new Books(
                            DataBase.result.getInt("id"),
                            DataBase.result.getString("name"),
                            DataBase.result.getString("author"),
                            DataBase.result.getDate("publishDate"),
                            DataBase.result.getString("quality"),
                            DataBase.result.getDate("regDate"),
                            rowAsString,
                            DataBase.result.getBlob("image")
                    ));
                    table.setItems(tableData);
                }
            DataBase.preparedStatement.close();
            DataBase.result.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
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



    @FXML
    public void adding(ActionEvent event) throws IOException {
        Stage stageNew = new Stage();
        stageNew.initOwner(LoginController.stage);
        stageNew.setResizable(false);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("adding.fxml")));
        stageNew.setScene(scene);
        stageNew.show();
    }



    private void setCellTable(){
        idColm.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColm.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColm.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishColm.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        qualityColm.setCellValueFactory(new PropertyValueFactory<>("quality"));
        regColm.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        statusColm.setCellValueFactory(new PropertyValueFactory<>("status"));
        imageColm.setCellValueFactory(new PropertyValueFactory<>("image"));
    }
//    @FXML
//    void choosingRow(MouseEvent event) {
//        DataBase.preparedStatement = null;
//        DataBase.result = null;
//        try {
//           String sql = "select id,image from books";
//           DataBase.preparedStatement = DataBase.connection.prepareStatement(sql);
//           DataBase.result = DataBase.preparedStatement.executeQuery(sql);
//
//            VBox imageContainer = new VBox();
//
//           while (DataBase.result.next()){
//               InputStream is = DataBase.result.getBinaryStream("image");
//               int bookId = DataBase.result.getInt("id");
//               String fileName = "img_" + bookId + ".jpg";
//               OutputStream os = new FileOutputStream(new File(fileName));
//               byte [] content= new byte[1024];
//               int size=0;
//               while ((size=is.read(content))!=-1){
//                   os.write(content, 0, size);
//               }
//               is.close();
//               os.close();
//               javafx.scene.image.Image image = new Image("file:" + fileName);
//               ImageView imageView = new ImageView(image);
//               imageView.setPreserveRatio(true);
//
//               // Add the ImageView instance to the imageContainer
//               imageContainer.getChildren().add(imageView);
//           }
//            coverOfBook.
//
//
//        } catch (SQLException | java.io.IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            if (DataBase.result != null) {
//                try {
//                    DataBase.result.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (  DataBase.preparedStatement != null) {
//                try {
//                    DataBase.preparedStatement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @FXML
    void choosingRow(){
        table.getSelectionModel().getSelectedItem();
        int num = table.getSelectionModel().getFocusedIndex();
        if((num-1) <-1) return;
        String url = "file:" +  table.getSelectionModel().getSelectedItem().getImage();
        Image image1 = new Image(url);
        coverOfBook.setImage(image1);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        designInserImage();
        setCellTable();
        Image image1 = new Image(getClass().getResourceAsStream("/images/library-icon-png-18.jpg"));
        Image image2 = new Image(getClass().getResourceAsStream("/images/65004-emoticon-smiley-sad-geek-nerd-emoji.png"));
        book.setImage(image1);
        smile.setImage(image2);
    }

}
