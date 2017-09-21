package edu.wou.jmozingo12;

public class CheckWriter {
	
	private String wordLibraryForTensAndOnes[][]= {
			{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"},
			{"zero", "one", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninty"}
	};
	
	private String wordLibraryForSpecialCases[] = {
			"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
	};
	
	private String numberPlacementTemplate[][]= {
			{"", "", "", "", "", ""},
			{"hundreds", "tens", "thousands", "hundreds", "tens", "ones"}
	};
	
	private int nextAvailibleSpaceInPlacementTemplate = 5;

	
	public CheckWriter(int userInput) {
		parseUserInput(userInput);
	}
	
	/**
	 * This method will parse the int the user enters and immediately
	 * add it to the numberPlacementTemplate, beginning with the ones place
	 * and moving forward.
	 * @param userInput
	 */
	private void parseUserInput(int userInput){
		int n = userInput;
		
		while (n > 0) {
		  int d = n / 10;
		  int k = n % 10;
		  n = d;
		 
		  addParsedInputToNumberPlacementTemplate(k);
		}
	}
	
	private void addParsedInputToNumberPlacementTemplate(int numberToAdd){
		numberPlacementTemplate[0][nextAvailibleSpaceInPlacementTemplate] = Integer.toString(numberToAdd);
		
		nextAvailibleSpaceInPlacementTemplate--;
	}
	
	private void compileOutputString(){
		int indexOfNumberToBeProcessed = nextAvailibleSpaceInPlacementTemplate + 1;
		int numberToBeProcessed = Integer.decode(numberPlacementTemplate[0][indexOfNumberToBeProcessed]);
		
		String outputString ="";
		
		while(indexOfNumberToBeProcessed <= numberPlacementTemplate[0].length){
			
			outputString = outputString + 
						   getEnglishValueOfNumber(numberToBeProcessed, getNumberPlacementValue(indexOfNumberToBeProcessed));
			
			if(indexOfNumberToBeProcessed != numberPlacementTemplate[0].length-1){
				indexOfNumberToBeProcessed++;
				numberToBeProcessed = Integer.decode(numberPlacementTemplate[0][indexOfNumberToBeProcessed]);
			}
		}
	}
	
	private String getNumberPlacementValue(int indexOfNumber){
		return numberPlacementTemplate[1][indexOfNumber];
	}
	
	private String getEnglishValueOfNumber(int number, String numberPlacement){
		if(numberPlacement.equals("thousands") || numberPlacement.equals("hundreds")){
			return wordLibraryForTensAndOnes[1][number];
		}
		else if(numberPlacement.equals("ones")){
			return wordLibraryForTensAndOnes[0][number];
		}
		else if(numberPlacement.equals("tens")){
			if(number == 1){	//Special case. One in the tens place must result in a 'teen' number.
				return "%%";	//The teen number is depended upon the next number in the sequence.
			}
			return wordLibraryForTensAndOnes[1][number];
		}
		else{
			return "Error, could not find a suitable case.";
		}
		
	}
	public static void main(String [] args){
		
		CheckWriter check = new CheckWriter((int)1004);
		check.compileOutputString();
	}
}
