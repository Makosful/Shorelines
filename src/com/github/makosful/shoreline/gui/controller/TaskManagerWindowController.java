/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * FXML Controller class
 *
 * @author Hussain
 */
public class TaskManagerWindowController implements Initializable
{

    // ListView
    @FXML
    private ListView<Task> taskListView;
    // Observable taskList
    private volatile ObservableList<Task> taskList;
    // List Task from other controller;
    private List<Task> taskOtherController;
    private MainWindowController controller;
    // ListView
    @FXML
    private ListView<Task> runningListView;
    // Observable running tasks.
    private volatile ObservableList<Task> runningTasks;
    private BooleanProperty pause;
    private Thread thread;
    private Boolean allRunningTasksSelected;
    private Boolean allTasksSelected;
    @FXML
    private JFXButton stopSelectedTasks;
    @FXML
    private JFXButton btnPauseResume;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        listInitialization();
        booleanInitialization();
        threadInitialization();
        listViewsListeners();
    }

    /**
     * Listeners for the listview, once the listview is empty, it changes
     * boolean
     * to false regarding "selectall"
     */
    public void listViewsListeners()
    {
        taskListView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Task>()
        {
            @Override
            public void onChanged(Change<? extends Task> c)
            {
                if (taskList.isEmpty())
                {
                    allTasksSelected = false;
                }
            }
        });

        runningListView.getItems().addListener(new ListChangeListener<Task>()
        {
            @Override
            public void onChanged(Change<? extends Task> c)
            {
                lockStopBtn();
                pauseButtonAndPause();
                if (runningTasks.isEmpty())
                {
                    allRunningTasksSelected = false;
                }
            }
        });

    }

    /**
     * Adding observable list to running tasks and making both listviews
     * able to select more than one element.
     */
    public void listInitialization()
    {
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        runningListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        runningTasks = FXCollections.observableArrayList();
        runningListView.setItems(runningTasks);
    }

    /**
     * Starting boolean values.
     */
    public void booleanInitialization()
    {
        allRunningTasksSelected = false;
        allTasksSelected = false;
        pause = new SimpleBooleanProperty(false);
        pause.addListener((observable) ->
        {
            lockStopBtn();
        });
    }

    @FXML
    private void convertSelectedTasks(ActionEvent event) throws InterruptedException
    {
        // Rækkefølgen er vigtig.
        runningTasks.addAll(getSelectedTasks());
        clearSelectedTasksToConvert();
        pause.setValue(false);

        if (!thread.isAlive())
        {
            thread.setDaemon(true);
            thread.start();
        }
        else
        {
            System.out.println("it is not dead");
        }

//        exService.shutdown();
//        if (exService.isShutdown())
//        {
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Convertion Info");
//            alert.setContentText("You successfully converted the files to JSON");
//            alert.setHeaderText("Info");
//            alert.show();
//
//            controller.setLog("No errors occured, conversion successful", "Conversion");
//            controller.saveLog();
//
//        }
    }
    int i = 0;

    @FXML
    private void removeSelectedTasks(ActionEvent event)
    {
        clearSelectedTasksToConvert();
    }

    /**
     * Handling the tasks the user stops.
     *
     * @param event
     */
    @FXML
    private void stopSelectedTasks(ActionEvent event)
    {
        List<Task> removedTasks = new ArrayList();
        for (Task task : getSelectedRunningTasks())
        {
            addingStoppedTasks(task);
            removedTasks.add(task);
        }
        runningTasks.removeAll(removedTasks);
    }

    private void threadInitialization()
    {
        thread = new Thread(() ->
        {
            while (true)
            {
                while (!runningTasks.isEmpty() && !pause.getValue())
                {
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(TaskManagerWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try
                    {
                        if (!runningTasks.isEmpty())
                        {

                            Task get = runningTasks.get(0);
                            get.run();
                        }

                    }
                    catch (IndexOutOfBoundsException ex)
                    {
                        ex.printStackTrace();
                    }

                }
            }

        });
    }

    /**
     * Getting the user added tasks from the mainwindow.
     * And making our listview hold on an observablelist.
     *
     * @param tasks
     */
    public void getTaskList(List<Task> tasks)
    {
        for (Task task : tasks)
        {
            taskDoneRemove(task);
        }
        taskOtherController = tasks;
        taskList = FXCollections.observableArrayList(tasks);
        taskListView.setItems(taskList);
    }

    /**
     * Getting the main controller, for methods like savelog etc.
     *
     * @param con
     */
    public void getMainController(MainWindowController con)
    {
        controller = con;
    }

    /**
     * Clearing tasks from both windows, the task handling and the main window.
     */
    public void clearSelectedTasksToConvert()
    {
        taskOtherController.removeAll(getSelectedTasks());
        taskList.removeAll(getSelectedTasks());
    }

    /**
     * Once user stops tasks, the tasks are added to the mainwindow (So that if
     * the user closes this window, he will see the tasks again, once he opens
     * it again) And ofc. added to the primary list for this window.
     *
     * @param task
     */
    public void addingStoppedTasks(Task task)
    {
        taskList.add(task);
        taskOtherController.add(task);
    }

    /**
     * Clearing tasks from the "running" listview as soon as the task has been
     * succeeded.
     *
     * @param task
     */
    private void taskDoneRemove(Task task)
    {
        if (task.getOnSucceeded() == null)
        {
            task.setOnSucceeded(new EventHandler()
            {
                @Override
                public void handle(Event event)
                {
                    runningTasks.remove(task);
                }
            });
        }
    }

    @FXML
    private void pauseResumeSelectedTasks(ActionEvent event) throws InterruptedException
    {
        if (!runningTasks.isEmpty())
        {
            if (pause.getValue())
            {
                pause.setValue(false);
            }
            else
            {
                pause.setValue(true);
            }
        }
    }

    @FXML
    private void selectAll(ActionEvent event)
    {
        if (!taskList.isEmpty())
        {
            if (!allTasksSelected)
            {
                taskListView.getSelectionModel().selectAll();

                allTasksSelected = true;
            }
            else
            {
                taskListView.getSelectionModel().clearSelection();
                allTasksSelected = false;
            }
        }
    }

    @FXML
    private void selectAllRunning(ActionEvent event)
    {
        if (!runningTasks.isEmpty())
        {
            if (!allRunningTasksSelected)
            {
                runningListView.getSelectionModel().selectAll();
                allRunningTasksSelected = true;
            }
            else
            {
                runningListView.getSelectionModel().clearSelection();
                allRunningTasksSelected = false;
            }
        }
    }

    /**
     * Setting stopButton not disabled if running task list is not empty, and
     * pause
     * is true. Else they get disabled.
     */
    public void lockStopBtn()
    {

        if (!pause.getValue() || runningTasks.isEmpty())
        {
            stopSelectedTasks.setDisable(true);
            btnPauseResume.setText("Pause");

        }
        else
        {
            stopSelectedTasks.setDisable(false);
            btnPauseResume.setText("Resume");
        }
    }

    public void pauseButtonAndPause()
    {
        if (runningTasks.isEmpty())
        {
            pause.setValue(true);
            btnPauseResume.setDisable(true);
        }
        else
        {
            btnPauseResume.setDisable(false);
        }
    }

    /**
     * Getting running tasks, from the listview.
     *
     * @return
     */
    public List<Task> getSelectedRunningTasks()
    {
        return runningListView.getSelectionModel().getSelectedItems();
    }

    /**
     * Getting the tasks that are not running, from their listview.
     *
     * @return
     */
    public List<Task> getSelectedTasks()
    {
        return taskListView.getSelectionModel().getSelectedItems();
    }

}
