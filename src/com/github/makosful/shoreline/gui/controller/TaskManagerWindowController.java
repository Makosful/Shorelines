/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.be.TaskTest;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
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
    private ObservableList<Task> taskList;
    // List Task from other controller;
    private List<Task> taskOtherController;
    private MainWindowController controller;
    @FXML
    private CheckListView<Task> runningListView;
    private ObservableList<Task> runningTasks;
    private boolean pause = false;
    private ExecutorService exService;
    List<TaskTest> threads = new ArrayList();
    List<Thread> threadsToPause = new ArrayList();
    private TaskTest thread;
    @FXML
    private JFXButton convertbtn;

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

//        exService = Executors.newFixedThreadPool(4);
        for (Task task : taskListView.getCheckModel().getCheckedItems())
        {
            TaskTest thread = new TaskTest();
            thread.setTask(task);

            threads.add(thread);
            thread.setDaemon(true);
            thread.start();
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

        Thread th = new Thread(() ->
        {

            threads.get(0).resumeThread();

        });
        th.setDaemon(true);
        th.start();
    }

    @FXML
    private void stopSelectedTasks(ActionEvent event)
    {
        for (int i = 0; i < taskList.size(); i++)
        {
            taskListView.getCheckModel().checkAll();
        }
    }

    private void taskDoneRemove(Task task)
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
        Thread th = new Thread(() ->
        {
            try
            {
                threads.get(0).pause();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(TaskManagerWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        th.setDaemon(true);
        th.start();

    }

}
