package project.library;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private ImageView book;

    @FXML
    private Button delete;

    @FXML
    private Button info;

    @FXML
    private Button logout;

    @FXML
    private Button photo;

    @FXML
    private ImageView smile;

    @FXML
    private Button update;

    @FXML
    private Button bars_btn;

    @FXML
    private Button arrow_btn;




    public void logoutClick(ActionEvent event){
        try {
            if(event.getSource() == logout) {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                logout.getScene().getWindow().hide();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
//    public void sliderArrow() {
//
//        TranslateTransition slide = new TranslateTransition();
//
//        slide.setDuration(Duration.seconds(.5));
//        slide.setNode(nav_form);
//        slide.setToX(-224);
//
//        TranslateTransition slide1 = new TranslateTransition();
//
//        slide1.setDuration(Duration.seconds(.5));
//        slide1.setNode(mainCenter_form);
//        slide1.setToX(-224 + 90);
//
//        TranslateTransition slide2 = new TranslateTransition();
//        slide2.setDuration(Duration.seconds(.5));
//        slide2.setNode(halfNav_form);
//        slide2.setToX(0);
//
//        slide.setOnFinished((ActionEvent event) -> {
//
//            arrow_btn.setVisible(false);
//            bars_btn.setVisible(true);
//
//        });
//
//        slide2.play();
//        slide1.play();
//        slide.play();
//
//    }
//
//    public void sliderBars() {
//
//        TranslateTransition slide = new TranslateTransition();
//
//        slide.setDuration(Duration.seconds(.5));
//        slide.setNode(nav_form);
//        slide.setToX(0);
//
//        TranslateTransition slide1 = new TranslateTransition();
//
//        slide1.setDuration(Duration.seconds(.5));
//        slide1.setNode(mainCenter_form);
//        slide1.setToX(0);
//
//        TranslateTransition slide2 = new TranslateTransition();
//        slide2.setDuration(Duration.seconds(.5));
//        slide2.setNode(halfNav_form);
//        slide2.setToX(-77);
//
//        slide.setOnFinished((ActionEvent event) -> {
//
//            arrow_btn.setVisible(true);
//            bars_btn.setVisible(false);
//
//        });
//
//        slide2.play();
//        slide1.play();
//        slide.play();
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/library-icon-png-18.jpg"));
        Image image2 = new Image(getClass().getResourceAsStream("/images/65004-emoticon-smiley-sad-geek-nerd-emoji.png"));
        book.setImage(image1);
        smile.setImage(image2);
    }
}
