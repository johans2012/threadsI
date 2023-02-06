package directory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author macmini
 */
public class WebPageReader {

    
    protected static final String SCRIPT = "script";
   
    List<String> script;
    List<String> inputs;

    public void formatWebContent() {
        
        script = new ArrayList<String>();
        
        Scanner sc = null;
        File file = new File("/Applications/MAMP/htdocs/SiteUtils/site/index.html");

        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                
                if(data.contains(SCRIPT)){
                   script.add(data);
                }                
            }
            
            System.out.println(script);

        } catch (Exception io) {
            System.out.print("Error on load file :" + io);
        }
   
    }

    public void readPage() {

        try {
            URL url = new URL("https://www.youtube.com");
            Scanner sc = new Scanner(url.openStream());

            while (sc.hasNext()) {
                System.out.println(sc.next());
            }


        } catch (IOException ex) {

            System.out.print("read page method throw exp: " + ex);
        }

    }

    public static String readContents(URL url) {
        BufferedReader in = null;
        try {
            URLConnection con = url.openConnection();
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            // Load the contents:
            String line = in.readLine();
            /*
            You should use a StringBuilder when appending multiple times
            because it's faster then using normal concation (+=) with Strings.
             */
            StringBuilder builder = new StringBuilder();
            do {
                builder.append(line + "\n"); // Keep the line-endings (pretty print)
            } while ((line = in.readLine()) != null);
            // Return the contents:
            return builder.toString();
        } catch (IOException e) {
            // Problem reading from the URL
            e.printStackTrace();
        } finally {
            // Close the Streams to prevent memory-leaks:
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ""; // Better way to do that...
    }

    public static void writeContents(File file, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            // Write:
            out.write(content);
            /*
            Normally, you don't need to call this manually, because it is called when
            the "close()"-method is called. I like to do it anyhow...
             */
            out.flush();
        } catch (FileNotFoundException e) {
            // The given file could not be found
            e.printStackTrace();
        } catch (IOException e) {
            // There was a problem writing to the File
            e.printStackTrace();
        } finally {
            // Close the Streams to prevent memory-leaks:
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
//        // Read the contents:
//        URL url = null;
//        try {
//            url = new URL("https://www.youtube.com");
//        } catch (MalformedURLException e) {
//            // When the URL was not well-formed
//            e.printStackTrace();
//        }
//        String content = readContents(url);;
//        // Write the contents:
//        File output_file = new File("output.xml");
//        writeContents(output_file, content);
//
//        WebPageReader reader = new WebPageReader();
//        reader.readPage();
        
        WebPageReader reader = new WebPageReader();
        reader.formatWebContent();

    }
}
