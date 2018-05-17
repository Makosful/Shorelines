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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.CheckListView;

/**
 * FXML Controller class
 *
 * @author Hussain
 */
public class TaskManagerWindowController implements Initializable
{

    @FXML
    private CheckListView<Task> taskListView;
    // Observable taskList
    private volatile ObservableList<Task> taskList;
    // List Task from other controller;
    private List<Task> taskOtherController;
    private MainWindowController controller;
    @FXML
    private CheckListView<Task> runningListView;
    private volatile ObservableList<Task> runningTasks;
    private Boolean pause;
    private Thread thread;
    private Boolean runningTasksChecked;
    private Boolean notRunningTasksChecked;
    @FXML
    private JFXButton convertbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        runningTasksChecked = false;
        notRunningTasksChecked = false;
        runningTasks = FXCollections.observableArrayList();
        runningListView.setItems(runningTasks);
        pause = false;
        threadInitialization();

    }
    
    

    @FXML
    private void convertSelectedTasks(ActionEvent event) throws InterruptedException
    {
        System.out.println(taskListView.getCheckModel().getItemCount() + "CONVERT");
        runningTasks.addAll(taskListView.getCheckModel().getCheckedItems());
        clearSelectedTasksToConvert();
        taskListView.getCheckModel().clearChecks();

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

    @FXML
    private void removeSelectedTasks(ActionEvent event)
    {

    }

    @FXML
    private void stopSelectedTasks(ActionEvent event)
    {
        System.out.println(runningListView.getCheckModel().getItemCount() + "STP");
        List<Task> removedTasks = new ArrayList();
        for (Task task : runningListView.getCheckModel().getCheckedItems())
        {
            taskList.add(task);
            taskOtherController.add(task);
            removedTasks.add(task);
        }
        runningTasks.removeAll(removedTasks);
        runningListView.getCheckModel().clearChecks();
    }

    private void threadInitialization()
    {
        thread = new Thread(() ->
        {
            while (true)
            {
                while (!runningTasks.isEmpty() && !pause)
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
                        if (runningTasks.get(0) != null)
                        {
                            Task get = runningTasks.get(0);
                            get.run();
                            System.out.println("running");
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

    public void getTaskList(List<Task> tasks)
    {
        taskOtherController = tasks;
        for (Task task : tasks)
        {
            taskDoneRemove(task);
        }
        taskList = FXCollections.observableArrayList(tasks);
        taskListView.setItems(taskList);
    }

    public void getMainController(MainWindowController con)
    {
        controller = con;
    }

    public void clearSelectedTasksToConvert()
    {
        taskOtherController.removeAll(taskListView.getCheckModel().getCheckedItems());
        taskList.removeAll(taskListView.getCheckModel().getCheckedItems());
    }

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
        if (pause)
        {
            pause = false;
        }
        else
        {
            pause = true;
        }
    }

    @FXML
    private void selectAll(ActionEvent event)
    {
        if(!taskList.isEmpty())
        {
        if (!notRunningTasksChecked)
        {
            
            taskListView.getCheckModel().checkAll();
            notRunningTasksChecked = true;
        }
        else
        {
            taskListView.getCheckModel().clearChecks();
            notRunningTasksChecked = false;
        }
        }
    }

    @FXML
    private void selectAllRunning(ActionEvent event)
    {
        if(!runningTasks.isEmpty())
        {
        if (!runningTasksChecked)
        {
            runningListView.getCheckModel().checkAll();
            runningTasksChecked = true;
        }
        else
        {
            runningListView.getCheckModel().clearChecks();
            runningTasksChecked = false;
        }
        }
    }

}
