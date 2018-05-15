/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.be;

import javafx.concurrent.Task;

/**
 *
 * @author Hussain
 */
public abstract class TaskString extends Task
{

    @Override
    protected abstract Object call() throws Exception;

    @Override
    public abstract String toString();
}
