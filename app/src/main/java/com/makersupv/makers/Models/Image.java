/*
 * Created by Iván José Martín García
 * Copyright © 2016 makers. All rights reserved.
 *
 */

package com.makersupv.makers.Models;

import com.parse.*;

@ParseClassName("Image")
public class Image extends ParseObject{

    public Image(){
        //Default constructor... Not used, just for Parse
    }

    public String getObjectIdParse(){
        return getString("objectId");
    }

    public ParseFile getParseImage(){
        return getParseFile("image");
    }

    public byte[] getParseByte(){
        return getBytes("image");
    }

    public void setParseImage(ParseFile file){
        put("image", file);
    }

}
