package map;
import com.opencsv.CSVReader;

import main.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
/**
 * <h1> CSV Reader Class for the Thymio project</h1>
 * <h3> Course: Informationssysteme (SS 2016) Universitaet Regensburg</h3>
 * 
 * <div>Dozent: Prof. Dr. Bernd Ludwig</div>
 *   
 * Gruppe 6:
 * Bauer Louisa, Durry Jan, Kasberger Korbinian, Mykyttschak Lina 
 * 
 * Generates a new CSV File if necessary and starts the Map Generator
 * Has some Getter for the Nodes
 * 
 * @version 1.0
 * @author Jan Durry: jan.durry@stud.uni-regensburg.de
 */
public class CSVData {

    private static final String csvPath = Settings.getObstacleSrc();
    private static List<String[]> entries;
    private static CSVReader reader;

    /**
     * Constructor
     * 
     * Deletes CSV File if it's set to Overwrite in Settings Class
     * 
     * @throws IOException
     */
    public CSVData () throws IOException {
    	boolean alreadyExists = new File(csvPath).exists();
    	if(!alreadyExists){
    		
			@SuppressWarnings("unused")
			MapGenerator mapGen = new MapGenerator();
    	}else if(Settings.isOverwrite()){
			File f = new File(csvPath);
			f.delete();
			@SuppressWarnings("unused")
			MapGenerator mapGen = new MapGenerator();
		}
        reader = new CSVReader(new FileReader(csvPath));
        entries = reader.readAll();
    }

    /**
     * Returns the entries of the CSV file
     * @return List<String[]>
     */
    public List<String[]> getEntries() {        
    	return entries;
    }

    /**
     *  Returns the reader
     * @return CSVreader
     */
    public static CSVReader getReader() {       
    	return reader;
    }
}
