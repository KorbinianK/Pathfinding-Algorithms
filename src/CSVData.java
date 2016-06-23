import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jan on 21.06.2016.
 */
public class CSVData {

    private static final String csvPath = "obstacle_map.csv";
    private static List entries;
    private static CSVReader reader;

    public CSVData () throws IOException {
        reader = new CSVReader(new FileReader(csvPath));
        entries = reader.readAll();
    }

    public static List<String[]> getEntries() {        
    	return entries;
    }

    public static CSVReader getReader() {       
    	return reader;
    }
}
