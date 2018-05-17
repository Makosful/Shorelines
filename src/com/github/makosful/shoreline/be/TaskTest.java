/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.be;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author Hussain
 */
public class TaskTest extends Thread
{

    private boolean bolPause;
    private Task task;

    public TaskTest()
    {
        bolPause = false;
    }

    @Override
    public void run()
    {
        try
        {
            while (bolPause)
            {
                try
                {
                    pause();
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(TaskTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            task.run();
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(TaskTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTask(Task task)
    {
        this.task = task;
    }

    public void pause() throws InterruptedException
    {
        bolPause = true;
        synchronized (this)
        {
            this.wait();
        }

    }

    public void resumeThread()
    {
        bolPause = false;
        synchronized (this)
        {
            this.notify();
        }

    }

    public Boolean getBoolean()
    {
        return bolPause;
    }

}
