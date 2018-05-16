/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private ObservableList<Task> taskList;
    // List Task from other controller;
    private List<Task> taskOtherController;
    private MainWindowController controller;
    @FXML
    private CheckListView<Task> runningListView;
    private ObservableList<Task> runningTasks;
    
    Task task;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        runningTasks = FXCollections.observableArrayList();
        runningListView.setItems(runningTasks);
    }

    @FXML
    private void convertSelectedTasks(ActionEvent event) throws InterruptedException
    {
        ExecutorService exService = Executors.newFixedThreadPool(4);

        for (Task run : taskListView.getCheckModel().getCheckedItems())
        {
            task = run;
            exService.execute(run);
            runningTasks.add(run);
            clearTasks(run);
        }
        task.setOnFailed((even1t) ->
        {
          task.getException().printStackTrace();
        });
        exService.shutdown();
        if (exService.isShutdown())
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Convertion Info");
            alert.setContentText("You successfully converted the files to JSON");
            alert.setHeaderText("Info");
            alert.show();

            controller.setLog("No errors occured, conversion successful", "Conversion");
            controller.saveLog();

        }
    }

    @FXML
    private void removeSelectedTasks(ActionEvent event)
    {
    }

    @FXML
    private void stopSelectedTasks(ActionEvent event)
    {
    }

    private void runningTaskListener()
    {

    }

    public void getTaskList(List<Task> tasks)
    {
        taskOtherController = tasks;
        taskList = FXCollections.observableArrayList(tasks);
        taskListView.setItems(taskList);
    }

    public void getMainController(MainWindowController con)
    {
        controller = con;
    }

    public void clearTasks(Task task)
    {
        taskOtherController.remove(task);
        taskList.remove(task);
    }

    @FXML
    private void pauseResumeSelectedTasks(ActionEvent event) throws InterruptedException
    {
        for (int i = 0; i < runningListView.getCheckModel().getItemCount(); i++)
        {
            runningListView.getCheckModel().getItem(i).wait();
        }
    }
}
