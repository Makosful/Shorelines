package com.github.makosful.shoreline;

import com.github.makosful.shoreline.gui.model.Cache;
import com.github.makosful.stage.exception.IlligalIdException;
import com.github.makosful.stage.utils.StageManager;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * All this class does is initialize the application. Nothing else.
 *
 * @author Axl
 */
public class Main extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        StageManager sm = new StageManager(stage);
        Cache c = Cache.getInstance();
        c.setStageManager(sm);

        registerScenes(sm);

        sm.getStage().show();

        File file = new File("./res/logo.png");
        Image icon = new Image(file.toURI().toString());
        sm.getStage().getIcons().add(icon);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    private void registerScenes(StageManager sm) throws IOException,
                                                        IlligalIdException
    {
        sm.registerScene(0, "Shoreline",
                         getClass().getResource("gui/view/MainWindow.fxml"));
        sm.registerScene(1, "Login",
                         getClass().getResource("gui/view/Login.fxml"));
        sm.registerScene(2, "Signup",
                         getClass().getResource("gui/view/SignUpWindow.fxml"));

        sm.setActiveScene(1);
    }
}
