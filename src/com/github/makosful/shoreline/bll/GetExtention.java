package com.github.makosful.shoreline.bll;

/**
 *
 * @author B
 */
public class GetExtention {

    public String extension;
    private final String fileName;
    
    public GetExtention(String fileName)
    {
        this.fileName = fileName;
        
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }
    }
    
    

}
