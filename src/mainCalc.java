import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class mainCalc {
	
	static String taxBrackets="taxBrackets.txt";
	static String line;
	static double[] taxRate= new double[4];
	static int[] incCap= new int[4];
	//Reads and extracts tax bracket info from text file
	public static void getTaxBrackets() {
		try {
			FileReader fileReader=new FileReader(taxBrackets);
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			int i=0;
			
			while((line=bufferedReader.readLine())!=null) {
				//Tax rate and income cap is split with a space and stored in separate array variables
				String[] keypair=line.split(" ");
				taxRate[i] = Double.parseDouble(keypair[0]);
				incCap[i] = Integer.parseInt(keypair[1]);
				System.out.println(taxRate[i]);
				System.out.println(incCap[i]);
				i++;
			}   
			bufferedReader.close();
		}
		catch(IOException ex){
			System.out.println("File "+taxBrackets+" could not be read");
			System.exit(0);
		}	
	}
	
	//Calculation of income tax
	public static void main(String[] args) {
		int income;
		int taxable = 0;
		int  tax=0;
		getTaxBrackets();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter an income amount.");
		//checks if income tax is int.
		while (true)
            try {
            	income = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.print("The entered income amount is invalid. Please try again: ");
            }
	//income tax calculations done in a series of if statements
		for (int i = 0;i<=taxRate.length-1;i++){
			if((i==0)){
				if(income<=incCap[i]){
					if(income>0){
						taxable+=(income-incCap[i])*taxRate[i];
					}
				}else{
					taxable+=incCap[i]*taxRate[i];
				}
			}else if((i==taxRate.length-1)){
				
				if(income >incCap[i-1]){
					taxable+=(income-incCap[i-1])*taxRate[i];
				}		
		
			}else{
				if(((income >incCap[i-1]) && (income <=incCap[i]))||((income>=incCap[i])&&(income>incCap[0]))){
					
					if(income<=incCap[i]){
						taxable+=(income-incCap[i-1])*taxRate[i];
					}else{
						taxable+=(incCap[i]-incCap[i-1])*taxRate[i];
					}
					
				}		
			}
			
		}

		System.out.println("Your income tax is: $"+taxable);  
		sc.close();
	}
	

}
