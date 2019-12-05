import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    /**
     * main entry point to the program
     * @param args a list of args for the program. See readme for more info
     */
    public static void main(String[] args) {
        int lineSize = Integer.parseInt(args[0]);
        String[] input = getInput(args);

        WordWrap ww = new WordWrap(lineSize, input);
        Long start = System.currentTimeMillis();
        Integer[] lines = ww.solve();
        Long end = System.currentTimeMillis();

        System.out.println("solved in "+((end-start)/1000.0) +" seconds");
        printResult(lines, input);
    }

    /**
     * prints the result of the Word Wrap
     * @param lines the list of how many words go on each line gotten from the word wrap
     * @param input the list of words gotten as input
     */
    public static void printResult(Integer[] lines, String[] input){
        int word = 0;
        for(int line = 1; line < lines.length && lines[line]!=null; line++){
            int words = lines[line];
            for(int i = word; i<=word+words; i++){
                System.out.print(input[i]+" ");
            }
            word = word+words+1;
            System.out.println();
        }
    }

    /**
     * gets the input from the user. it checks if the input is a file and uses that if it is, if its not it uses the raw input as input
     * @param args the program args
     * @return a list of words to use
     */
    public static String[] getInput(String[] args){
        if(args.length == 2){
            File f = new File(args[1]);
            try{
                List<String> in = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(f));
                String line;
                while( (line = reader.readLine())!= null){
                    line.replaceAll("\n","");
                    line.replaceAll("\r\n", "");
                    line.trim();
                    String[] words = line.split(" ");
                    in.addAll(Arrays.asList(words));
                }
                String[] ret = new String[in.size()];
                in.toArray(ret);
                return ret;
            }catch (Exception ex){
            }
        }
        List<String> argList = new ArrayList<>(Arrays.asList(args));
        argList.remove(0);
        args = new String[argList.size()];
        argList.toArray(args);
        return args;
    }
}
