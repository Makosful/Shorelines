/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.be;

import java.util.List;

/**
 *
 * @author B
 */
public class Config
{

    private String name;
    private List<Integer> chosenColums;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Integer> getChosenColums()
    {
        return chosenColums;
    }

    public void setChosenColums(List<Integer> chosenColums)
    {
        this.chosenColums = chosenColums;
    }
}
