/**
 * @author Yuxi Jin 
 * school: CMU
 * the date submitted: Oct 30, 2014
 */
package core;

public class Player {
	int [] a={1,2};

    private final String name;
    private final String symbol;
    private int score;
    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score=0;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setScore(int num){
    	this.score=num;
    }
    
    public int getScore(){
    	return score;
    }
}
