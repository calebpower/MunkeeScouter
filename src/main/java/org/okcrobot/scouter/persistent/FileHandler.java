package org.okcrobot.scouter.persistent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

/**
 * Handles all files on the disk.
 * 
 * @author Caleb L. Power
 */
public class FileHandler {
  
  /**
   * Saves a serialized JSON object to the disk.
   * 
   * @param file the disk to which the file should be saved
   * @param data the data that should be saved to the file
   * @return <code>true</code> if the file was saved properly or
   *         <code>false</code> if the file was not saved properly
   */
  public boolean save(File file, JSONObject data) {
    try {
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(data.toString(2));
      fileWriter.close();
      return true;
    } catch(IOException e) { }
    
    return false;
  }
  
}
