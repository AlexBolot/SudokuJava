import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*..................................................................................................
 . Copyright (c)
 .
 . The Main	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 14/02/17 17:38
 .
 . Contact : bolotalex06@gmail.com
 .................................................................................................*/

/**
 Méthode de résolution :
 <p>
 Case x.y.b :
 -> Check Ligne   y pour val 1 à 9
 -> Check Colonne x pour val 1 à 9
 -> Check Block   b pour val 1 à 9
 <p>
 Pour chaque boucle de Check, si val trouvée, val retirée liste possibilités.
 <p>
 Une fois tous les Checks effectués, 1ere val de liste des possibilités et affecté à la case.
 <p>
 On passe à la case suivante.
 <p>
 Si case impossible à résoudre, la case précédente prends la 2eme val de liste des possibilités.
 */

public class Main extends Application
{
    public static void main (String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start (Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root, 295, 340));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}