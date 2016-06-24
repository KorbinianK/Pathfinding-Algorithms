package map;
import com.opencsv.CSVReader;

import main.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jan on 21.06.2016.
 */
public class CSVData {

    private static final String csvPath = Settings.getObstacleSrc();
    private static List<String[]> entries;
    private static CSVReader reader;

    public CSVData () throws IOException {
    	boolean alreadyExists = new File(csvPath).exists();
    	if(!alreadyExists){
    		
			MapGenerator mapGen = new MapGenerator();
    	}
        reader = new CSVReader(new FileReader(csvPath));
        entries = reader.readAll();
    }

    public List<String[]> getEntries() {        
    	return entries;
    }

    public static CSVReader getReader() {       
    	return reader;
    }
}
