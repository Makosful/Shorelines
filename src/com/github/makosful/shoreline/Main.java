package com.github.makosful.shoreline;

import com.github.makosful.shoreline.gui.model.Cache;
import com.github.makosful.stage.exception.IlligalIdException;
import com.github.makosful.stage.utils.StageManager;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Cache cache = Cache.getInstance();
        cache.setStageManager(sm);

        registerScenes(sm);

        sm.getStage().show();

        File file = new File("./res/logoT.png");
        Image icon = new Image(file.toURI().toString());
        sm.getStage().getIcons().add(icon);
    }

    private void registerScenes(StageManager sm)
    {
        try
        {
            sm.registerScene(0, "Shoreline",
                             getClass().getResource("gui/view/MainWindow.fxml"));
            sm.setActiveScene(0);
        }
        catch (IlligalIdException | IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
