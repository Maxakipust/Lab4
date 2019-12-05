public class WordWrap {
    final int lineLength;
    final String[] words;
    int[][] cost;
    Integer[] lineNums;

    /**
     * set up the word wrap problem
     * @param lineLength the length of a line in number of chars
     * @param words the words to include in the problem.
     */
    public WordWrap(int lineLength, String[] words){
        this.lineLength = lineLength;
        this.words = words;
        cost = new int[words.length][words.length];
        lineNums = new Integer[words.length];
    }

    /**
     * calculates formatting for the words that reduces the amount of extra spaceing while keeping the lines neat
     * @return an array that contains the number of words on each line
     */
    public Integer[] solve(){
        //calulate the cost of having words i to j in a line
        Integer extraSpace[][] = new Integer[words.length][words.length];
        for(int i = 0; i< words.length; i++){
            extraSpace[i][i] = lineLength - words[i].length();
            for(int j = i+1; j<words.length; j++){
                extraSpace[i][j] = extraSpace[i][j-1] - words[j].length()-1;
            }
        }

        //calculate the square of the cost of the line
        for(int i =0; i< extraSpace.length; i++){
            for(int j = i; j<extraSpace[i].length; j++){
                extraSpace[i][j] = cleanSquareNum(extraSpace[i][j], j);
            }
        }

        Integer totalCost[] = new Integer[words.length+1];
        Integer solution[] = new Integer[words.length+1];
        //find the minimum cost for puting words on each line
        totalCost[0] = 0;
        for (int j = 1; j <= words.length; j++) {
            totalCost[j] = Integer.MAX_VALUE;
            for (int i = 1; i <= j; i++) {
                if (totalCost[i-1] != Integer.MAX_VALUE && extraSpace[i-1][j-1] != Integer.MAX_VALUE && (totalCost[i-1] + extraSpace[i-1][j-1] < totalCost[j])){
                    totalCost[j] = totalCost[i-1] + extraSpace[i-1][j-1];
                    solution[j] = i;
                }
            }
        }

        getSolution(solution, words.length);
        return lineNums;
    }

    /**
     * calulates the cost of the line by squaring it
     * @param num the remaining spaces in the line
     * @param line the line number
     * @return the cost of the line
     */
    public Integer cleanSquareNum(int num, int line){
        if(num < 0){
            //if we cant fit the number on the line then we return the max
            return Integer.MAX_VALUE;
        }
        if(line == words.length-1){
            //if the word is on the last line then we dont give it a cost since we can ignore the extra spaces on the line
            return 0;
        }
        //otherwise square the number
        return num*num;
    }

    /**
     * converts the solution to a list of line numbers.
     * It will set the actual solution to lineNums. this is done to save me a ton of pain.
     * @param solution the solution we get from the dp solution
     * @param size the size that we are looking at
     * @return the new size
     */
    int getSolution (Integer solution[], int size) {
        //line number is size-1
        //words from solution[size] to size
        int k;
        if (solution[size] == 1)
            k = 1;
        else
            k = getSolution(solution, solution[size]-1) + 1;
        for(int i = solution[size]-1; i< size; i++){
            lineNums[k] = size - solution[size];
        }
        return k;
    }
}
