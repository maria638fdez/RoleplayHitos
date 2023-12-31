package items;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Random;

public class DiceSet {
	
    enum DiceLetter {    	m,    	C,    	M,    }
    private EnumMap<DiceLetter, Integer> lastRoll = new EnumMap<DiceLetter, Integer>(DiceLetter.class);
    
    
    public void SetDiceValues(int value1, int value2, int value3) {
    	int[] values = {value1, value2, value3};
    	Arrays.sort(values);
    	lastRoll.put(DiceLetter.m, values[0]);
    	lastRoll.put(DiceLetter.C, values[1]);
    	lastRoll.put(DiceLetter.M, values[2]);
    }
    
	public EnumMap<DiceLetter, Integer> roll() {
		Random rand = new Random();
		int n1 = rand.nextInt(10) + 1;
		int n2 = rand.nextInt(10) + 1;
		int n3 = rand.nextInt(10) + 1;
		SetDiceValues(n1, n2, n3);
		return lastRoll;
	}
	
	public int m (){
		return lastRoll.get(DiceLetter.m);
	}
	
	public int C (){
		return lastRoll.get(DiceLetter.C);
	}
	
	public int M (){
		return lastRoll.get(DiceLetter.M);
	}
	
    
    private void Diceset() {
    	SetDiceValues(0, 0, 0);
	}
    
    

}
