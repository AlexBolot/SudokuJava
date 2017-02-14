import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/*..................................................................................................
 . Copyright (c)
 .
 . The AccueilController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 14/02/17 08:37
 .
 . Contact : bolotalex06@gmail.com
 .................................................................................................*/

public class AccueilController {

    @FXML
    private Button btnPlay;

    @FXML
    void btnPlay_onClick(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Grille.fxml"));
        Stage stage = (Stage) btnPlay.getScene().getWindow();
        Scene scene = new Scene(root, 295, 340);
        stage.setScene(scene);
        stage.setTitle("Grille");
        stage.show();
    }

}
