import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int lineSize = Integer.parseInt(args[0]);
        String[] input = getInput(args);

        WordWrap ww = new WordWrap(lineSize, input);
        Integer[] lines = ww.solve();

        System.out.println("solved using DP");
        printResult(lines, input);
    }

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

    public static String[] getInput(String[] args){
        if(args.length == 2){
            File f = new File(args[1]);
            try{
                List<String> in = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(f));
                String line;
                while( (line = reader.readLine())!= null){
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
