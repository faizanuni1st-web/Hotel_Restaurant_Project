import java.util.Scanner;
public class Hotel_Booking_And_Resturant_Billing_System{
    public static Scanner input = new Scanner (System.in);
    public static void passcheck(String pass){

    }
    public static void idcheck(String id){

    }
    public static void Admin(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|                    Log In Menu Admin                 |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Greeting User!                                        |");
        System.out.println("|Please Enter your Admin Id and Password.              |");
        System.out.println("|Your Id should be in the format\"yourname@admin.com\"|");
        System.out.println("|You have three attempts                               |");
        System.out.println("|Press (0) to navigate to previous window              |");
        System.out.println("|------------------------------------------------------|");
        input.nextLine();
        int count = 1 ;
        while (true ){
            if (count > 1 ){
                System.out.println("You exceed the Log In limit! \n Returning to main menu.");
                intialMenu();
            }

            
        
            System.out.print("Enter your Admin Id: ");
            String id = input.nextLine();
            if (id.equals("0")){
                Login();
            }
            else 
                idcheck(id);
            System.out.print("Enter your Password:");            
            String password= input.nextLine();
            
                count +=1;
            
        }


    }
    public static void User (){
        input.nextLine();

    }

    public static void exit (){
        while (true) { 
            break;
        }}
    public static void Login(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|                     Log in Menu                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("|            1. Log In as an Admin                     |");
        System.out.println("|            2. Log in as a User                       |");
        System.out.println("|            3. Back to Main Menu                      |");
        System.out.println("|------------------------------------------------------|");
        int choice = input.nextInt();
        switch(choice ){
            case (1):{
                Admin();
                ;
                break;
            }
            case (2):{
                User();
                
                break;
            }
            case (3):{
                intialMenu();

            }
            default:
                System.out.println("INVALID OUTPUT! \n Exiting to Main Menu .....");
                intialMenu();

        }
    }
    public static void intialMenu(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|                      Main Menu                       |");
        System.out.println("|       Hotel Booking and Resturant Billing System     |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Greeting User! Please Input Your choice .            |");
        System.out.println("|            1. Log-In Menu                            |");
        System.out.println("|            2. Exit                                   |");
        System.out.println("|------------------------------------------------------|");
        int choice = input.nextInt();
        switch(choice){

            case (1):{
                System.out.println("Executing your choice \nEntering Log-in Menu \nPlease wait a bit ......... ");
                Login();
                break;}
            case (2):{
                exit();}
            
        }
    }
    public static void main(String[] args) {
        intialMenu();
    }
}