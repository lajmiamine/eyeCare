package com.example.hamza.imageupload;


/**
 * Created by hamza on 4/20/2016.
 */
public class Config {
    // File upload url (replace the ip with your server address)
    public static final String FILE_UPLOAD_URL = "http://f85bb8bc.ngrok.io/AndroidFileUpload/fileUpload.php?username="+
            MainActivity.name+"&lastname="+MainActivity.lastname;

    // Directory name to store captured images and videos
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
}

