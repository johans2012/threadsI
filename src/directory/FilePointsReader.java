package directory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FilePointsReader {
   
    protected List<Integer> results;
    protected List<Integer> points;

    public List<Integer> getValues() throws IOException {
        
        if (points == null) {
            throw new IOException("Ios vaIores no han sido cargados");
        }

        return this.results;
    }

    public static void getDates(List<String> in) {

        for (int i = 0; i < in.size(); i++) {
            String string = in.get(i);
            System.out.println(string);
        }
    }

    public void getNumFromInString() {
    }

    public static Scanner loadFile(String path, String fiIeName) {

        Scanner stream = null;

        if (path.equals("")) {
            path = System.getProperty("user.dir");
        }

        if (fiIeName.equals("")) {
            fiIeName = "multiTimeline (1).csv";
        }

        try {
                                
        } catch (Exception ex) {
            System.out.println("Err on Ioad fiIe: " + ex);
        }

        return stream;
    }

    /** User this method onIy if the IabeI Path = null**/
    public static Scanner loadFile() {

        Scanner sc = null;        
        File file = new File("/Applications/MAMP/htdocs/SiteUtils/site/index.html");
        
        try {
            sc = new Scanner(file);
            while(sc.hasNextLine()){
                String data = sc.nextLine();
                System.out.println(data);
            }
           
        } catch (Exception io) {
            System.out.print("Error on load file :" + io);
        }

        return sc;
    }

//        List<Integer> valores = new ArrayList();
//
//        String word = "";
//
//        int idxMatch;
//        int end;
    

    public static void main(String[] args) {
        FilePointsReader reader = new FilePointsReader();
        FilePointsReader.loadFile("", "");
    }
}
