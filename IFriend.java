import java.util.*;
import java.time.LocalDate;
class IFriend{
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String args[]){
		while(true){
			clearConsole();	
			printHomeMenu();
			System.out.print("Enter an option number to continue -> ");
			
			switch(scan.next()){
				case "1" :
					addContacts();
					break;
				case "2" :
					updateContacts();
					break;
				case "3" :
					deleteContacts();
					break;
				case "4" :
					searchContacts();
					break;
				case "5" :
					listContacts();
					break;
				case "6" :
					return;
				default :
					System.out.print("\n\t\t\t\t   | Invalid Selection ! |");
					try{Thread.sleep(1200);}catch(Exception ex){}	
			}
		}		
	}
	
	public static String[] contactIds = new String[0];
	public static String[] contactNames= new String[0];
	public static String[] phoneNumbers= new String[0];
	public static String[] companyNames= new String[0];
	public static double[] salaries= new double[0];
	public static String[] birthdays= new String[0];
	
	public static int CurrentID = 1;
	
	public static void addContacts(){
		while(true){
			clearConsole();
			System.out.println("\n\n+=============================================================================+");
			System.out.println("|                           Add Contact to the list                           |");
			System.out.println("+=============================================================================+\n\n");
			
			while(true){
				String currentId = "C" + String.format("%04d", CurrentID);
				System.out.println(" " + currentId);
				System.out.println("=======\n");	
				

				String contactName = getName(" * Name             : ");
				if(contactName == null){printFailedToAdd(); break;}
				
				String phoneNumber = getPhonenumber(" * Phone Number     : ");
				if(phoneNumber == null){printFailedToAdd(); break;}
				
				String companyName = getName(" * Company Name     : ");
				if(companyName == null){printFailedToAdd(); break;}
				
				double salary = getSalaray(" * Salary           : ");
				if(salary == 0){printFailedToAdd(); break;}
				
				String birthDate = getBirthDate(" * B'Day(YYYY-MM-DD): ");
				if(birthDate == null){printFailedToAdd(); break;}
		
				CurrentID++;
				contactIds = addToArray(contactIds,currentId);
				contactNames = addToArray(contactNames,contactName);
				phoneNumbers = addToArray(phoneNumbers,phoneNumber);
				companyNames = addToArray(companyNames,companyName);
				salaries = addToArray(salaries,salary);
				birthdays = addToArray(birthdays,birthDate);
				
				System.out.println("\n--------------------------------------------------------");
				System.out.println("            Contact has been added sucessfully !");
				break;
			}
			System.out.println("--------------------------------------------------------");
			System.out.print("\n\n>> Do you want to add another Contact(Y/N) ? ");
			if(getYesOrNO()=='n'){
				return;
			}
		}
	}
	
	public static void printFailedToAdd(){
		System.out.println("\n--------------------------------------------------------");
		System.out.println("          Failed to Add Contact : Missing Details !");
	}
	
	public static void updateContacts(){
		while(true){
			clearConsole();
			System.out.println("\n\n+=============================================================================+");
			System.out.println("|                             UPDATE Contact                                  |");
			System.out.println("+=============================================================================+\n\n");
			
			int [] searchResultsIndexes = searchAndGetIndexes();
			int numOfResult = searchResultsIndexes.length;
			int count = numOfResult; 
			System.out.println("\n");
			result:for(int i=0;i<numOfResult;i++){
				printSearchResultDetails(searchResultsIndexes[i],i+1);
				System.out.println("\n\nWhat Do you want to Update.....\n");
				System.out.println("    [1] Name");
				System.out.println("    [2] Phone Number");
				System.out.println("    [3] Company name");
				System.out.println("    [4] Salary\n");	
				System.out.println(count==1 ?"    [5] Continue Without Updateing\n":"    [5] View Next Result\n");
				
				option:while(true){
					System.out.print("Enter an Option to Continue -> ");
					switch(scan.next()){
						case "1" :
							ClearLines(11);
							System.out.println("\n Update Name");
							System.out.println("=============\n");
							
							String newContactName = getName("Input new Name\t: ");
							if(newContactName == null){printFailedToUpdate();break result;} 
							contactNames[searchResultsIndexes[i]] = newContactName;
							break option;
						case "2" :
							ClearLines(11);
							System.out.println("\n Update Phone Number");
							System.out.println("=======================\n");
							
							String newPhoneNumber = getPhonenumber("Input new Phone Number\t : ");
							if(newPhoneNumber == null){printFailedToUpdate();break result;} 
							phoneNumbers[searchResultsIndexes[i]] = newPhoneNumber;
							break option;
						case "3" :
							ClearLines(11);
							System.out.println("\n Update Company Name");
							System.out.println("========================\n");
							
							String newCompanyName = getName("Input new Company Name\t: ");
							if(newCompanyName == null){printFailedToUpdate();break result;} 
							companyNames[searchResultsIndexes[i]] = newCompanyName;
							break option;
						case "4" :
							ClearLines(12);
							System.out.println("\n Update Salary");
							System.out.println("==================\n");
							
							double newSalary = getSalaray("Input new Salaray\t : ");
							if(newSalary == 0){printFailedToUpdate();break result;} 
							salaries[searchResultsIndexes[i]] = newSalary;
							break option;
						case "5" :
								ClearLines(22);
								count--;
								continue result;
						default :
							System.out.print("\n\t\t\t\t   | Invalid Selection ! |");
							try{Thread.sleep(1200);}catch(Exception ex){}
							ClearLines(2);			
					}
				}
				System.out.println("\n--------------------------------------------------------");
				System.out.println("            Contact has been Updated Successfully !");
				break;
			}
			System.out.println("--------------------------------------------------------");
			System.out.print("\n>> Do you want to search for another Contact to Update ? ");
			if(getYesOrNO() == 'n'){
				return;
			}	
		}
		
	}
	
	public static void printFailedToUpdate(){
		System.out.println("\n--------------------------------------------------------");
		System.out.println("          Failed to Update Contact !");
	}
	
	public static void deleteContacts(){
		while(true){
			clearConsole();
			System.out.println("\n\n+=============================================================================+");
			System.out.println("|                             DELETE Contact                                  |");
			System.out.println("+=============================================================================+\n\n");
			
			int [] searchResultsIndexes = searchAndGetIndexes();
			int numOfResult = searchResultsIndexes.length;
			System.out.println("\n");
			for(int i=0;i<numOfResult;i++){
				printSearchResultDetails(searchResultsIndexes[i],i+1);
				
				System.out.print("\n> Do you want to Delete this Contact ?");
				if(getYesOrNO() == 'y'){
					contactIds = deleteFromArray(contactIds,searchResultsIndexes[i]);
					contactNames = deleteFromArray(contactNames,searchResultsIndexes[i]);
					phoneNumbers = deleteFromArray(phoneNumbers,searchResultsIndexes[i]);
					companyNames = deleteFromArray(companyNames,searchResultsIndexes[i]);
					salaries = deleteFromArray(salaries,searchResultsIndexes[i]);
					birthdays = deleteFromArray(birthdays,searchResultsIndexes[i]);
					System.out.println("\n--------------------------------------------------------");
					System.out.println("            Contact has been deleted Successfully !!");
					break;
				}else{
					ClearLines(13);
				}
			}
			System.out.println("--------------------------------------------------------");
			System.out.print("\n>> Do you want to search for another Contact to Delete?");
			if(getYesOrNO() == 'n'){
				return;
			}	
		}
	}
	
	public static void searchContacts(){
		while(true){
			clearConsole();
			System.out.println("\n\n+=============================================================================+");
			System.out.println("|                             SEARCH Contact                                  |");
			System.out.println("+=============================================================================+\n\n");
			
			int [] searchResultsIndexes = searchAndGetIndexes();
			int numOfResult = searchResultsIndexes.length;
			int count = numOfResult;
			System.out.println("\n");
			for(int i=0;i<numOfResult;i++){
				printSearchResultDetails(searchResultsIndexes[i],i+1);
				if(count-- != 1){
					System.out.print("\n> Do you want to View Next Search Result ?");
					if(getYesOrNO() == 'n'){
						break;
					}else{
						ClearLines(13);
					}
				}
			}
			System.out.println("\n--------------------------------------------------------");
			System.out.print("\n>> Do you want to search for another Contact ?");
			if(getYesOrNO() == 'n'){
				return;
			}	
		}
	}
	
	public static void listContacts(){
		sort:while(true){
			clearConsole();
			System.out.println("\n\n+===========================================================================+");
			System.out.println("|                             SORT Contact                                  |");
			System.out.println("+===========================================================================+\n\n");
			
			System.out.println("\t[1] Sorting by Name\n");
			System.out.println("\t[2] Sorting By Salary\n");
			System.out.println("\t[3] Sorting By Birthday\n");
			
			System.out.print("\nEnter an Option to Continue -> ");
			switch(scan.next()){
				case "1" :
					sortByName();
					break;
				case "2" :
					sortBySalary();
					break;
				case "3" :
					sortByBirthday();
					break;
				default :
					System.out.print("\n\t\t\t\t   | Invalid Selection ! |");
					try{Thread.sleep(1200);}catch(Exception ex){}	
					continue;
			}
			return;
		}
		
	}
	
	public static void sortByName(){
		int numOfContacts = contactIds.length;
		for(int i=0;i<numOfContacts-1;i++){
			for(int j=0;j<numOfContacts-1-i;j++){
				boolean isSuffix = true;
				String name1 = contactNames[j].toUpperCase(),
					   name2 = contactNames[j+1].toUpperCase();
				int name1length = name1.length(),
					name2length = name2.length();
				for(int k=0, l=0; k<name1length && l< name2length ; k++,l++){
					if(name1.charAt(k) > name2.charAt(l)){
						swapFollowingValuesInAllArrays(j);
						isSuffix = false;
						break;	
					}else if(name1.charAt(k) < name2.charAt(l)){
						isSuffix = false;
						break;
					}	
				}
				if(isSuffix){
					swapFollowingValuesInAllArrays(j);
				}
					
			}
		}
		while(true){
			clearConsole();
			System.out.println("\n\n\t\t+-------------------------------------------------------------+");
			System.out.println("\t\t|                  LIST Contact by Name                       |");
			System.out.println("\t\t+-------------------------------------------------------------+");
			printTable();
			System.out.println("\n--------------------------------------------------------");
			System.out.print("\n>> Do you want to go to Home Page ?");
			if(getYesOrNO() == 'y'){
				return;
			}
		}
	}
	
	public static void sortBySalary(){
		int numOfContacts = contactIds.length;
		for(int i=0;i<numOfContacts-1;i++){
			for(int j=0;j<numOfContacts-1-i;j++){
					if(salaries[j] > salaries[j+1]){
						swapFollowingValuesInAllArrays(j);	
				}
			}
		}
		while(true){
			clearConsole();
			System.out.println("\n\n\t\t+-------------------------------------------------------------+");
			System.out.println("\t\t|                  LIST Contact by Salary                     |");
			System.out.println("\t\t+-------------------------------------------------------------+");
			printTable();
			System.out.println("\n--------------------------------------------------------");
			System.out.print("\n>> Do you want to go to Home Page ?");
			if(getYesOrNO() == 'y'){
				return;
			}
		}	
	}
	
	public static void sortByBirthday(){
		int numOfContacts = contactIds.length;
		
		for(int i=0;i<numOfContacts-1;i++){
			for(int j=0;j<numOfContacts-1-i;j++){
					if(dateisAfter(birthdays[j],birthdays[j+1])){
						swapFollowingValuesInAllArrays(j);
				}
			}
		}
		while(true){
			clearConsole();
			System.out.println("\n\n\t\t+-------------------------------------------------------------+");
			System.out.println("\t\t|                  LIST Contact by Birthday                   |");
			System.out.println("\t\t+-------------------------------------------------------------+");
			printTable();
			System.out.println("\n--------------------------------------------------------");
			System.out.print("\n>> Do you want to go to Home Page ?");
			if(getYesOrNO() == 'y'){
				return;
			}
		}
	}
	
    public static String getName(String phrase){
		while(true){
				System.out.print(phrase);
				String name = scan.next();
				if(isOnlyNumeric(name)){
					System.out.println("\n\t\t         | Invalid Name : Numeric names not Allowed ! |");
					System.out.print("\n\t\t> Do you want to add name again ?");
					if(getYesOrNO() == 'n'){
						return null;
					}else{
						ClearLines(6);
					}
				}else{
					return name;
				}
		}
	}
	
	public static boolean isOnlyNumeric(String input){
		int length = input.length();
		for (int i = 0; i <  length; i++) {
			char ch = input.charAt(i);
            if (ch<'0' || '9'<ch) {
                return false;
            }
        }
        return true;
     }
		
    public static String getPhonenumber(String phrase){
		while(true){
				System.out.print(phrase);
				String number = scan.next();
				boolean isDuplicate = searchInArray(phoneNumbers,number);
				if(isValidPhoneNumber(number) && !isDuplicate){
					return number;
				}else{
					if(isDuplicate){
						System.out.println("\n\t\t         | Number Already Exists ! |");
					}else{	
						System.out.println("\n\t\t         | Invalid Phone Number ! |");
					}
					System.out.print("\n\t\t> Do you want to add phone number again ?");
					if(getYesOrNO() == 'n'){
						return null;
					}else{
						ClearLines(6);
					}
				}
		}
	}
	
	public static boolean isValidPhoneNumber(String input){
		if (input.length() != 10) {
            return false;
        }
        if (input.charAt(0) != '0') {
            return false;
        }
        if (!isOnlyNumeric(input)){
			return false;
		}
        return true;
	}

	public static double getSalaray(String phrase){
		while(true){
			System.out.print(phrase);
			try {
				double salary = Double.parseDouble(scan.next());
				if(salary>0){
					return salary;
				}
			}catch(NumberFormatException e){}
			System.out.println("\n\t\t         | Invalid Salary ! |");
			System.out.print("\n\t\t> Do you want to add salary again ?");
			if(getYesOrNO() == 'n'){
				return 0;
			}else{
				ClearLines(6);
			}
		}
	}
	
	public static String getBirthDate(String phrase){
		while(true){
			System.out.print(phrase);
			String birthdate = scan.next();
			if(isValidBirthDate(birthdate)){
				return birthdate;
			}else{
				System.out.println("\n\t\t      | Invalid Birthday ! |");
				System.out.print("\n\t\t> Do you want to input birthday again ?");
				if(getYesOrNO() == 'n'){
					return null;
				}else{
					ClearLines(6);
				}
			}
		}
	}
	
	public static boolean isValidBirthDate(String input) {
        int length = input.length();
        if (length != 10) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char ch = input.charAt(i);
            if (i == 4 || i == 7) {
                if (ch != '-') {
                    return false; 
                }
            } else {
                if (ch<'0' || '9'<ch) {
                    return false; 
                }
            }
        }
        int year = Integer.parseInt(input.substring(0, 4));
        int month = Integer.parseInt(input.substring(5, 7));
        int day = Integer.parseInt(input.substring(8));
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > getDaysInMonth(year, month)) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        String presentDate = String.format("%04d-%02d-%02d",currentDate.getYear(),currentDate.getMonthValue(),currentDate.getDayOfMonth());
        return   dateisAfter(input,"1899-01-01") && dateisAfter(presentDate,input);
    }
    
     public static int getDaysInMonth(int year, int month) {
        if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                return 29; 
            } else {
                return 28; 
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30; 
        } else {
            return 31; 
        }
    }
    
    public static boolean dateisAfter(String date1 , String date2){
		int year1 = Integer.parseInt(date1.substring(0, 4));
        int month1 = Integer.parseInt(date1.substring(5, 7));
        int day1 = Integer.parseInt(date1.substring(8));

		int year2 = Integer.parseInt(date2.substring(0, 4));
        int month2 = Integer.parseInt(date2.substring(5, 7));
        int day2 = Integer.parseInt(date2.substring(8));
        if(year1>year2){
			return true;
		}else if(year1 == year2){
				if(month1>month2){
					return true;
				}else if(month1 == month2){
					if(day1>day2){
						return true;
					}
				}
		}
		return false;
	}
	
	//get search phrase from the user and searches in names and phoneNumbers arrays and returns array with indexes of occurance of the search
	public static int[] searchAndGetIndexes(){
		String search = "";
		while(true){
			System.out.print("* Search Contact By Name or Phone Number - ");
			search = scan.next();
			if(isOnlyNumeric(search) && !isValidPhoneNumber(search)){
				System.out.println("\n\t\t         | Invalid Phone Number ! |");
				try{Thread.sleep(1200);}catch(Exception ex){}
				ClearLines(3);
				continue;	
			}
			break;
		}	
		int[] containIndexes ;
		if(isValidPhoneNumber(search)){
			containIndexes = getContainIndexes(phoneNumbers,search);
		}else{
			containIndexes = getContainIndexes(contactNames,search);
		}
		System.out.println("\n\t" + containIndexes.length + "  Result(s)");
		return containIndexes;
	}
			 
    public static String[] addToArray(String[] ar,String element){
		int length = ar.length;
		String[] temp=new String[length+1];
		for (int i = 0; i < length; i++){
			temp[i]=ar[i];
		}
			ar=temp;
		ar[ar.length-1]=element;
		return ar;
	}
    
    public static double[] addToArray(double[] ar,double element){
		int length = ar.length;
		double[] temp=new double[length+1];
		for (int i = 0; i <length; i++){
			temp[i]=ar[i];
		}
			ar=temp;
		ar[ar.length-1]=element;
		return ar;
	}
	
	public static String[] deleteFromArray(String[] array,int index){
		String[] newArray = new String[array.length - 1];
		 int length = array.length; 
		for (int i = 0, j = 0; i < length; i++) {
			if (i == index) {
				continue;
			}
			newArray[j++] = array[i];
		}
		return newArray;
	}
	
	public static double[] deleteFromArray(double[] array,int index){
		double[] newArray = new double[array.length - 1];
		int length = array.length;
		for (int i = 0, j = 0; i < length; i++) {
			if (i == index) {
				continue;
			}
			newArray[j++] = array[i];
		}
		return newArray;
	}
	
	//check specific elements in a array and returns  array with indexes of occurence of the value
	public static int[] getContainIndexes(String[] ar,String value){
		int[] br = new int[0];
		int length = ar.length;
		for(int i=0;i<length;i++){
			if(ar[i].equalsIgnoreCase(value)){
				br = addToArray(br,i);
			}
		}
		return br;
	}
	
	public static void swapFollowingValuesInAllArrays(int index){
		swapFollowingArrayValues(contactIds,index);
		swapFollowingArrayValues(contactNames,index);
		swapFollowingArrayValues(phoneNumbers,index);
		swapFollowingArrayValues(companyNames,index);
		swapFollowingArrayValues(salaries,index);
		swapFollowingArrayValues(birthdays,index);
	}
	
	public static void swapFollowingArrayValues(String[] ar, int index){
			String temp =ar[index];
			ar[index] = ar[index+1];
			ar[index+1] = temp;
		}
	
	public static void swapFollowingArrayValues(double[] ar, int index){
			double temp =ar[index];
			ar[index] = ar[index+1];
			ar[index+1] = temp;
		}
    
    public static int[] addToArray(int[] ar,int element){
		int length = ar.length;
		int[] temp=new int[length+1];
		for (int i = 0; i <length; i++){
			temp[i]=ar[i];
		}
			ar=temp;
		ar[ar.length-1]=element;
		return ar;
	}
	
	public static boolean searchInArray(String[] ar, String key){
		for (int i = 0; i < ar.length; i++){
			if(ar[i].equals(key)){
				return true;
			}
		}
		return false;
	}
	
	public static char getYesOrNO(){
			System.out.println();
			while (true) {
				System.out.print("\t\t\t('Y' or 'N') : ");
				char reponse = scan.next().toLowerCase().charAt(0);
				if (reponse == 'y' || reponse == 'n') {
					return reponse;
				}else{
					System.out.println("\t\t\t\t\t | Invalid Response ! |");
					try{Thread.sleep(1200);}catch(Exception ex){}
					ClearLines(2);
				}
			}
		}
		
	public final static void clearConsole(){
        try {   
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c","cls").inheritIO().start().waitFor();
            }else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void ClearLines( int numOfLines){
		System.out.print("\033["+numOfLines+"A");
		System.out.print("\r\033[0J");	
	}	
    
	public static void printSearchResultDetails(int index,int resultNumber){
		System.out.println("\t       Result " + resultNumber);
		System.out.println("\t   ===============\n\n" );
		System.out.println("   * Contact ID        : " + contactIds[index]);
		System.out.println("   * Name              : " + contactNames[index]);
		System.out.println("   * Phone Number      : " + phoneNumbers[index]);
		System.out.println("   * Company Name      : " + companyNames[index]);
		System.out.println("   * Salary            : " + salaries[index]);
		System.out.println("   * B'Day(YYYY-MM-DD) : " + birthdays[index]);
	}
	
	public static void printTable(){
		if(contactIds.length != 0){
			System.out.println("\n\n+-----------------------------------------------------------------------------------------------------+");
			System.out.printf("|  %-13s |  %-13s |  %-13s |  %-13s |  %-13s |  %-13s |\n", "Contact ID", "Name", "Phone Number", "Company Name", "Salary", "Birthday");
			System.out.println("+-----------------------------------------------------------------------------------------------------+");
			int numofContacts = contactIds.length;
			for (int j = 0; j < numofContacts; j++) {
				System.out.printf("|  %-13s |  %-13s |  %-13s |  %-13s |  %-13s |  %-13s |\n", contactIds[j], contactNames[j], phoneNumbers[j], companyNames[j], salaries[j], birthdays[j]);
			}
			System.out.println("+-----------------------------------------------------------------------------------------------------+\n\n");
		}else{
			System.out.println("\n\n\t\tNo Contacts to Display");
		}
	}
		
	public static void printHomeMenu(){
		System.out.println("\n\n           /$$ /$$$$$$$$ /$$$$$$$  /$$$$$$ /$$$$$$$$ /$$   /$$ /$$$$$$$ ");
		System.out.println("           |__/| $$_____/| $$__  $$|_  $$_/| $$_____/| $$$ | $$| $$__  $$");
		System.out.println("            /$$| $$      | $$  \\ $$  | $$  | $$      | $$$$| $$| $$  \\ $$");
		System.out.println("           | $$| $$$$$   | $$$$$$$/  | $$  | $$$$$   | $$ $$ $$| $$  | $$");
		System.out.println("           | $$| $$__/   | $$__  $$  | $$  | $$__/   | $$  $$$$| $$  | $$");
		System.out.println("           | $$| $$      | $$  \\ $$  | $$  | $$      | $$\\  $$$| $$  | $$");
		System.out.println("           | $$| $$      | $$  | $$ /$$$$$$| $$$$$$$$| $$ \\  $$| $$$$$$$/");
		System.out.println("           |__/|__/      |__/  |__/|______/|________/|__/  \\__/|_______/ \n\n");
		System.out.println("   _____            _             _          ____                        _              ");
		System.out.println("  / ____|          | |           | |        / __ \\                      (_)             ");
		System.out.println(" | |     ___  _ __ | |_ __ _  ___| |_ ___  | |  | |_ __ __ _  __ _ _ __  _ _______ _ __ ");
		System.out.println(" | |    / _ \\| '_ \\| __/ _` |/ __| __/ __| | |  | | '__/ _` |/ _` | '_ \\| |_  / _ \\ '__|");
		System.out.println(" | |___| (_) | | | | || (_| | (__| |_\\__ \\ | |__| | | | (_| | (_| | | | | |/ /  __/ |   ");
		System.out.println("  \\_____\\___/|_| |_|\\__\\__,_|\\___|\\__|___/  \\____/|_|  \\__, |\\__,_|_| |_|_/___\\___|_|   ");
		System.out.println("                                                        __/ |                           ");
		System.out.println("                                                       |___/         \n");
		System.out.println("=========================================================================================\n");
		System.out.println("         [1] ADD Contacts\n");
		System.out.println("         [2] UPDATE Contacts\n");
		System.out.println("         [3] DELETE Contacts\n");
		System.out.println("         [4] SEARCH Contacts\n");
		System.out.println("         [5] LIST Contacts\n");
		System.out.println("\n         [6] Exit\n\n");
	}
}	
