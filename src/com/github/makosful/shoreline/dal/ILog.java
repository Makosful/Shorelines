/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.be.ConversionLog;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author B
 */
public interface ILog
{
    public void saveLog(ConversionLog conversionLog) throws SQLException;
    
}
