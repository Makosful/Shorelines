/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.BE;

import java.util.Date;

/**
 *
 * @author Hussain
 */
public class ExcelRow
{
    private String siteName;
    private String assetSerialNumber;
    private String orderType;
    private String workOrderId;
    private String systemStatus;
    private String userStatus;
    private String createdOn;
    private String createdBy;
    private String nameDescription;
    private String priority;
    private String status;
    // EarliestStartDate
    private String esDate;
    // LatestStartDAte
    private String lsDate;
    // LatestFinishedDate
    private String lfDate;
    //EstimatedTime
    private String esTime;

    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public String getAssetSerialNumber()
    {
        return assetSerialNumber;
    }

    public void setAssetSerialNumber(String assetSerialNumber)
    {
        this.assetSerialNumber = assetSerialNumber;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getWorkOrderId()
    {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId)
    {
        this.workOrderId = workOrderId;
    }

    public String getSystemStatus()
    {
        return systemStatus;
    }

    public void setSystemStatus(String systemStatus)
    {
        this.systemStatus = systemStatus;
    }

    public String getUserStatus()
    {
        return userStatus;
    }

    public void setUserStatus(String userStatus)
    {
        this.userStatus = userStatus;
    }

    public String getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(String createdOn)
    {
        this.createdOn = createdOn;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getNameDescription()
    {
        return nameDescription;
    }

    public void setNameDescription(String nameDescription)
    {
        this.nameDescription = nameDescription;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getEsDate()
    {
        return esDate;
    }

    public void setEsDate(String esDate)
    {
        this.esDate = esDate;
    }

    public String getLsDate()
    {
        return lsDate;
    }

    public void setLsDate(String lsDate)
    {
        this.lsDate = lsDate;
    }

    public String getLfDate()
    {
        return lfDate;
    }

    public void setLfDate(String lfDate)
    {
        this.lfDate = lfDate;
    }

    public String getEsTime()
    {
        return esTime;
    }

    public void setEsTime(String esTime)
    {
        this.esTime = esTime;
    }
}
