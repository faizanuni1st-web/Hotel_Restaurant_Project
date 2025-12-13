import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Hotel_Booking_And_Resturant_Billing_System_Github {
   
    public static Scanner sc = new Scanner(System.in);
    public static Scanner input = new Scanner(System.in);
    
    // ==================== RESTAURANT METHODS ====================
    
    public static void change_price() {
        try {
            System.out.println("\n========== CHANGE ITEM PRICE ==========");
            
            // First show the menu
            view_menu();
            
            System.out.print("\nEnter the Item ID you want to change price of: ");
            String itemId = sc.nextLine().trim();
            
            if (itemId.isEmpty()) {
                System.out.println("Error: Item ID cannot be empty!");
                return;
            }
            
            System.out.print("Enter new price: ");
            String newPriceStr = sc.nextLine().trim();
            
            // Validate new price
            int newPrice;
            try {
                newPrice = Integer.parseInt(newPriceStr);
                if (newPrice <= 0) {
                    System.out.println("Error: Price must be a positive number!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Price must be a valid number!");
                return;
            }
            
            // Read the entire menu file
            File menuFile = new File("RestaurantMenu.txt");
            if (!menuFile.exists()) {
                System.out.println("Error: RestaurantMenu.txt file not found!");
                return;
            }
            
            ArrayList<String> menuItems = new ArrayList<>();
            boolean found = false;
            
            Scanner reader = new Scanner(menuFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");
                
                if (parts.length >= 3 && parts[0].trim().equals(itemId)) {
                    // Found the item - update the price
                    String updatedItem = parts[0].trim() + ", " + parts[1].trim() + ", " + newPrice;
                    menuItems.add(updatedItem);
                    found = true;
                    System.out.println("Updated: " + line + " → " + updatedItem);
                } else {
                    // Keep other items unchanged
                    menuItems.add(line);
                }
            }
            reader.close();
            
            if (!found) {
                System.out.println("Error: Item ID '" + itemId + "' not found in menu!");
                return;
            }
            
            // Write updated menu back to file
            PrintWriter writer = new PrintWriter(menuFile);
            for (String item : menuItems) {
                writer.println(item);
            }
            writer.close();
            
            System.out.println("\n✓ SUCCESS: Price updated successfully!");
            
            // Show updated menu
            System.out.println("\n--- Updated Menu ---");
            Scanner showReader = new Scanner(menuFile);
            while (showReader.hasNextLine()) {
                System.out.println(showReader.nextLine());
            }
            showReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: RestaurantMenu.txt file not found!");
        } catch (SecurityException e) {
            System.out.println("Error: Permission denied to access file!");
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }
    
    public static void restaurant_generate_bill() {
        try {
            System.out.print("Enter room number: ");
            String roomNo = sc.nextLine().trim();
            
            if (roomNo.isEmpty()) {
                System.out.println("Error: Room number cannot be empty!");
                return;
            }
            
            File orderFile = new File("RestaurantOrders.txt");
            if (!orderFile.exists()) {
                System.out.println("No orders found for any room.");
                return;
            }
            
            Scanner read = new Scanner(orderFile);
            int total = 0;
            boolean found = false;
            
            System.out.println("\n----- Restaurant Order Details -----");
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] p = line.split(",");
                
                if (p.length >= 5 && p[0].trim().equals(roomNo)) {
                    int price = Integer.parseInt(p[3].trim());
                    int qty = Integer.parseInt(p[4].trim());
                    total += price * qty;
                    found = true;
                    System.out.println("Item: " + p[2].trim() + ", Qty: " + qty + ", Price: " + (price * qty));
                }
            }
            read.close();
            
            if (!found) {
                System.out.println("No orders found for room " + roomNo);
                return;
            }
            
            PrintWriter bill = new PrintWriter(new FileWriter("RestaurantBills.txt", true));
            bill.println(roomNo + ", " + total);
            bill.close();
            
            System.out.println("------------------------------");
            System.out.println("Room No: " + roomNo);
            System.out.println("Total Bill: " + total);
            System.out.println("------------------------------");
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Orders file not found!");
        } catch (IOException e) {
            System.out.println("Error writing to bill file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in data!");
        } catch (Exception e) {
            System.out.println("Error generating bill: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    public static void place_order() {
        try {
            System.out.print("Enter room number: ");
            String roomNo = sc.nextLine().trim();
            
            if (roomNo.isEmpty()) {
                System.out.println("Error: Room number cannot be empty!");
                return;
            }
            
            File cartFile = new File("RestaurantCart.txt");
            if (!cartFile.exists()) {
                System.out.println("Cart is empty.");
                return;
            }
            
            Scanner read = new Scanner(cartFile);
            ArrayList<String> newCart = new ArrayList<>();
            ArrayList<String> orderItems = new ArrayList<>();
            
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] p = line.split(",");
                
                if (p.length >= 5 && p[0].trim().equals(roomNo)) {
                    orderItems.add(line);
                } else {
                    newCart.add(line);
                }
            }
            read.close();
            
            if (orderItems.isEmpty()) {
                System.out.println("Cart is empty for room " + roomNo);
                return;
            }
            
            // Move items to RestaurantOrders.txt
            PrintWriter ord = new PrintWriter(new FileWriter("RestaurantOrders.txt", true));
            PrintWriter hist = new PrintWriter(new FileWriter("RestaurantHistory.txt", true));
            
            System.out.println("\n----- Placed Orders -----");
            for (String s : orderItems) {
                ord.println(s);
                String[] itemDetails = s.split(",");
                if (itemDetails.length >= 5) {
                    hist.println("Room " + roomNo + " ordered: " + itemDetails[2].trim() + 
                                " (Qty: " + itemDetails[4].trim() + ")");
                    System.out.println(itemDetails[2].trim() + " x " + itemDetails[4].trim());
                }
            }
            
            ord.close();
            hist.close();
            
            // Overwrite new cart
            PrintWriter writer = new PrintWriter("RestaurantCart.txt");
            for (String s : newCart) {
                writer.println(s);
            }
            writer.close();
            
            System.out.println("\nOrder placed successfully!");
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cart file not found!");
        } catch (IOException e) {
            System.out.println("Error writing to order file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error placing order: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    public static void update_order() {
        try {
            System.out.print("Enter room number: ");
            String roomNo = sc.nextLine().trim();
            
            System.out.print("Enter item ID to update: ");
            String itemID = sc.nextLine().trim();
            
            if (roomNo.isEmpty() || itemID.isEmpty()) {
                System.out.println("Error: Room number and Item ID cannot be empty!");
                return;
            }
            
            File file = new File("RestaurantCart.txt");
            if (!file.exists()) {
                System.out.println("Cart is empty.");
                return;
            }
            
            Scanner read = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            boolean updated = false;
            
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] p = line.split(",");
                
                if (p.length >= 5 && p[0].trim().equals(roomNo) && p[1].trim().equals(itemID)) {
                    System.out.print("Enter new quantity: ");
                    try {
                        int newQty = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        
                        if (newQty <= 0) {
                            System.out.println("Error: Quantity must be positive!");
                            list.add(line); // Keep original
                        } else {
                            list.add(roomNo + ", " + itemID + ", " + p[2].trim() + ", " + p[3].trim() + ", " + newQty);
                            updated = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Quantity must be a number!");
                        sc.nextLine(); // Clear invalid input
                        list.add(line); // Keep original
                    }
                } else {
                    list.add(line);
                }
            }
            read.close();
            
            if (!updated) {
                System.out.println("Order not found for room " + roomNo + " with item ID " + itemID);
                return;
            }
            
            PrintWriter writer = new PrintWriter("RestaurantCart.txt");
            for (String s : list) {
                writer.println(s);
            }
            writer.close();
            
            System.out.println("\nOrder updated successfully!");
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cart file not found!");
        } catch (IOException e) {
            System.out.println("Error writing to cart file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error updating order: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    public static void add_to_cart() {
        try {
            // Read menu
            File menuFile = new File("RestaurantMenu.txt");
            if (!menuFile.exists()) {
                System.out.println("Error: Menu file not found!");
                return;
            }
            
            Scanner read = new Scanner(menuFile);
            ArrayList<String> menuList = new ArrayList<>();
            while (read.hasNextLine()) {
                menuList.add(read.nextLine());
            }
            read.close();
            
            // Input user details
            System.out.print("Enter your room number: ");
            String roomNo = sc.nextLine().trim();
            
            System.out.print("Enter item ID: ");
            String itemID = sc.nextLine().trim();
            
            if (roomNo.isEmpty() || itemID.isEmpty()) {
                System.out.println("Error: Room number and Item ID cannot be empty!");
                return;
            }
            
            boolean found = false;
            String itemName = "";
            int price = 0;
            
            // Find item in menu
            for (String line : menuList) {
                String[] p = line.split(",");
                if (p.length >= 3 && p[0].trim().equals(itemID)) {
                    itemName = p[1].trim();
                    price = Integer.parseInt(p[2].trim());
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("Item ID '" + itemID + "' does not exist in menu.");
                return;
            }
            
            System.out.print("Enter quantity: ");
            try {
                int qty = sc.nextInt();
                sc.nextLine(); // Consume newline
                
                if (qty <= 0) {
                    System.out.println("Error: Quantity must be positive!");
                    return;
                }
                
                // Add to cart
                PrintWriter writer = new PrintWriter(new FileWriter("RestaurantCart.txt", true));
                writer.println(roomNo + ", " + itemID + ", " + itemName + ", " + price + ", " + qty);
                writer.close();
                
                System.out.println("\nItem added to cart successfully!");
                System.out.println(itemName + " x " + qty + " = " + (price * qty));
                
            } catch (InputMismatchException e) {
                System.out.println("Error: Quantity must be a number!");
                sc.nextLine(); // Clear invalid input
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Menu file not found!");
        } catch (IOException e) {
            System.out.println("Error writing to cart file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid price format in menu!");
        } catch (Exception e) {
            System.out.println("Error adding to cart: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    public static void search_menu() {
        try {
            File file = new File("RestaurantMenu.txt");
            if (!file.exists()) {
                System.out.println("Error: Menu file not found!");
                return;
            }
            
            Scanner read = new Scanner(file);
            
            System.out.print("\nEnter item name to search: ");
            String search = sc.nextLine().toLowerCase().trim();
            
            if (search.isEmpty()) {
                System.out.println("Error: Search term cannot be empty!");
                read.close();
                return;
            }
            
            boolean found = false;
            
            System.out.println("\nSearch Results:");
            System.out.println("--------------------------------");
            
            while (read.hasNextLine()) {
                String line = read.nextLine();
                if (line.toLowerCase().contains(search)) {
                    System.out.println(line);
                    found = true;
                }
            }
            read.close();
            
            if (!found) {
                System.out.println("No item found containing: '" + search + "'");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Menu file not found!");
        } catch (Exception e) {
            System.out.println("Error searching menu: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    public static void view_menu() {
        try {
            File file = new File("RestaurantMenu.txt");
            if (!file.exists()) {
                System.out.println("Error: Menu file not found!");
                return;
            }
            
            Scanner read = new Scanner(file);
            
            System.out.println("\n----- Restaurant Menu -----");
            System.out.println("ID, Item Name, Price");
            System.out.println("-------------------------");
            
            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
            read.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Menu file not found!");
        } catch (Exception e) {
            System.out.println("Error reading menu: " + e.getMessage());
        }
    }

    // ==================== HOTEL METHODS ====================
    
    public static void add_room() {
        try {
            System.out.println("\n--- Add New Room to Hotel ---");
            
            System.out.print("Enter Room Number: ");
            String roomNo = sc.nextLine().trim();
            
            System.out.print("Enter Room Type: ");
            String roomType = sc.nextLine().trim();
            
            System.out.print("Enter Price per day: ");
            String priceStr = sc.nextLine().trim();
            
            if (roomNo.isEmpty() || roomType.isEmpty() || priceStr.isEmpty()) {
                System.out.println("Error: All fields are required!");
                return;
            }
            
            int price;
            try {
                price = Integer.parseInt(priceStr);
                if (price <= 0) {
                    System.out.println("Error: Price must be a positive number!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Price must be a valid number!");
                return;
            }
            
            // Default status for new room
            String status = "Available";
            
            // Read existing rooms
            ArrayList<String> rooms = new ArrayList<>();
            File roomFile = new File("Room.txt");
            
            if (roomFile.exists()) {
                Scanner reader = new Scanner(roomFile);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    rooms.add(line);
                    
                    // Check for duplicate room number
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].trim().equals(roomNo)) {
                        System.out.println("Error: Room number '" + roomNo + "' already exists!");
                        reader.close();
                        return;
                    }
                }
                reader.close();
            }
            
            // Create new room entry
            String newRoom = roomNo + ", " + roomType + ", " + price + ", " + status;
            
            // Add to list
            rooms.add(newRoom);
            
            // Write back to file
            PrintWriter writer = new PrintWriter(roomFile);
            for (String room : rooms) {
                writer.println(room);
            }
            writer.close();
            
            System.out.println("\nRoom added successfully!");
            System.out.println("New room: " + newRoom);
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Room file not found!");
        } catch (IOException e) {
            System.out.println("Error writing to room file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding room: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    public static void check_guest_status() {
        try {
            File file = new File("Guest.txt");
            if (!file.exists()) {
                System.out.println("No guest records found.");
                return;
            }
            
            Scanner read = new Scanner(file);
            
            System.out.println("\n----- Guest Status -----");
            System.out.println("Room, Guest Name, Days");
            System.out.println("-----------------------");
            
            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
            read.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Guest file not found!");
        } catch (Exception e) {
            System.out.println("Error reading guest status: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    public static void user_generate_bill() {
        try {
            System.out.print("\nEnter room number for bill: ");
            String roomNo = sc.nextLine().trim();
            
            if (roomNo.isEmpty()) {
                System.out.println("Error: Room number cannot be empty!");
                return;
            }
            
            String guestName = "";
            int days = 0;
            
            // Find guest in Guest.txt
            File guestfile = new File("Guest.txt");
            if (!guestfile.exists()) {
                System.out.println("No guest records found!");
                return;
            }
            
            Scanner gr = new Scanner(guestfile);
            boolean guestFound = false;
            
            while (gr.hasNextLine()) {
                String[] g = gr.nextLine().split(",");
                if (g.length >= 3 && g[0].trim().equals(roomNo)) {
                    guestName = g[1].trim();
                    days = Integer.parseInt(g[2].trim());
                    guestFound = true;
                    break;
                }
            }
            gr.close();
            
            if (!guestFound) {
                System.out.println("No guest found in room " + roomNo + "!");
                return;
            }
            
            // Get price from Room.txt
            File roomfile = new File("Room.txt");
            if (!roomfile.exists()) {
                System.out.println("Error: Room file not found!");
                return;
            }
            
            Scanner rr = new Scanner(roomfile);
            int price = 0;
            boolean priceFound = false;
            
            while (rr.hasNextLine()) {
                String[] r = rr.nextLine().split(",");
                if (r.length >= 3 && r[0].trim().equals(roomNo)) {
                    price = Integer.parseInt(r[2].trim());
                    priceFound = true;
                    break;
                }
            }
            rr.close();
            
            if (!priceFound) {
                System.out.println("Error: Room " + roomNo + " not found in records!");
                return;
            }
            
            int totalBill = price * days;
            
            // Save to Bill.txt
            PrintWriter bw = new PrintWriter(new FileWriter("Bill.txt", true));
            bw.println(roomNo + ", " + guestName + ", " + totalBill);
            bw.close();
            
            System.out.println("\n----- BILL -----");
            System.out.println("Guest: " + guestName);
            System.out.println("Room No: " + roomNo);
            System.out.println("Days: " + days);
            System.out.println("Price per day: " + price);
            System.out.println("Total Bill = " + totalBill);
            System.out.println("----------------");
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Required file not found!");
        } catch (IOException e) {
            System.out.println("Error writing to bill file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in data!");
        } catch (Exception e) {
            System.out.println("Error generating bill: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    public static void book_room() {
        try {
            // Show available rooms
            System.out.println("\n----- Available Rooms -----");
            File roomfile = new File("Room.txt");
            if (!roomfile.exists()) {
                System.out.println("Error: Room file not found!");
                return;
            }
            
            Scanner rf = new Scanner(roomfile);
            ArrayList<String> roomList = new ArrayList<>();
            boolean availableRooms = false;
            
            System.out.println("Room No, Type, Price, Status");
            System.out.println("----------------------------");
            
            while (rf.hasNextLine()) {
                String room = rf.nextLine();
                roomList.add(room);
                if (room.contains("Available")) {
                    System.out.println(room);
                    availableRooms = true;
                }
            }
            rf.close();
            
            if (!availableRooms) {
                System.out.println("No rooms available for booking!");
                return;
            }
            
            // Room selection
            System.out.print("\nEnter room number to book: ");
            String roomNo = sc.nextLine().trim();
            
            System.out.print("Enter guest name: ");
            String guestName = sc.nextLine().trim();
            
            System.out.print("Enter number of days: ");
            String daysStr = sc.nextLine().trim();
            
            if (roomNo.isEmpty() || guestName.isEmpty() || daysStr.isEmpty()) {
                System.out.println("Error: All fields are required!");
                return;
            }
            
            int days;
            try {
                days = Integer.parseInt(daysStr);
                if (days <= 0) {
                    System.out.println("Error: Days must be a positive number!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Days must be a valid number!");
                return;
            }
            
            boolean roomFound = false;
            
            // Update Room.txt
            PrintWriter writer = new PrintWriter("RoomTemp.txt");
            
            for (String r : roomList) {
                if (r.startsWith(roomNo + ",")) {
                    roomFound = true;
                    String[] parts = r.split(",");
                    if (parts.length >= 4) {
                        String type = parts[1].trim();
                        String price = parts[2].trim();
                        
                        if (r.contains("Booked")) {
                            System.out.println("Error: Room " + roomNo + " is already booked!");
                            writer.close();
                            new File("RoomTemp.txt").delete();
                            return;
                        }
                        
                        writer.println(roomNo + ", " + type + ", " + price + ", Booked");
                    }
                } else {
                    writer.println(r);
                }
            }
            
            writer.close();
            
            if (!roomFound) {
                System.out.println("Error: Room " + roomNo + " not found!");
                new File("RoomTemp.txt").delete();
                return;
            }
            
            // Replace old file with temp file
            new File("Room.txt").delete();
            new File("RoomTemp.txt").renameTo(new File("Room.txt"));
            
            // Add guest record
            PrintWriter gw = new PrintWriter(new FileWriter("Guest.txt", true));
            gw.println(roomNo + ", " + guestName + ", " + days);
            gw.close();
            
            // Add booking history
            PrintWriter hw = new PrintWriter(new FileWriter("BookingHistory.txt", true));
            hw.println(guestName + " booked room " + roomNo + " for " + days + " days.");
            hw.close();
            
            System.out.println("\nRoom booked successfully!");
            System.out.println("Guest: " + guestName);
            System.out.println("Room: " + roomNo);
            System.out.println("Days: " + days);
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Required file not found!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format!");
        } catch (Exception e) {
            System.out.println("Error booking room: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    public static void check_all_rooms() {
        try {
            File file = new File("Room.txt");
            if (!file.exists()) {
                System.out.println("No room records found.");
                return;
            }
            
            Scanner read = new Scanner(file);
            
            System.out.println("\n----- All Rooms -----");
            System.out.println("Room No, Type, Price, Status");
            System.out.println("---------------------------");
            
            while (read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
            read.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Room file not found!");
        } catch (Exception e) {
            System.out.println("Error reading rooms: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }

    // ==================== USER PORTALS ====================
    
    public static void hotel_user(String name) {
        while (true) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("| Welcome " + name + "                                    |");
                System.out.println("|                   Hotel User Portal                  |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("| Press (0-4) to navigate to your option               |");
                System.out.println("| 1. Check All Rooms                                   |");
                System.out.println("| 2. Book Room                                         |");
                System.out.println("| 3. Generate Bill                                     |");
                System.out.println("| 4. Check Guest Status                                |");
                System.out.println("| 0. Return to previous menu                           |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your choice: ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number!");
                    sc.nextLine(); // Clear invalid input
                    continue;
                }
                
                switch (choice) {
                    case 1:
                        check_all_rooms();
                        break;
                    case 2:
                        book_room();
                        break;
                    case 3:
                        user_generate_bill();
                        break;
                    case 4:
                        check_guest_status();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (IllegalStateException e) {
                System.out.println("Error: Scanner is closed!");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }
    
    public static void Resturant_user(String name) {
        while (true) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("| Welcome " + name + "                                  |");
                System.out.println("|                Restaurant User Portal                |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("| 1. View Menu                                         |");
                System.out.println("| 2. Search Menu                                       |");
                System.out.println("| 3. Add to Cart                                       |");
                System.out.println("| 4. Update Order                                      |");
                System.out.println("| 5. Place Order                                       |");
                System.out.println("| 6. Generate Bill                                     |");
                System.out.println("| 0. Back to previous menu                             |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your choice: ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number!");
                    sc.nextLine(); // Clear invalid input
                    continue;
                }
                
                switch (choice) {
                    case 1:
                        view_menu();
                        break;
                    case 2:
                        search_menu();
                        break;
                    case 3:
                        add_to_cart();
                        break;
                    case 4:
                        update_order();
                        break;
                    case 5:
                        place_order();
                        break;
                    case 6:
                        restaurant_generate_bill();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (IllegalStateException e) {
                System.out.println("Error: Scanner is closed!");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }
    
    public static void user_menu(String name) {
        while (true) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("|       Welcome " + name + " to User Portal            |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("| 1. View Hotel                                        |");
                System.out.println("| 2. View Restaurant                                   |");
                System.out.println("| 3. Back to previous menu                             |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your choice: ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number!");
                    sc.nextLine(); // Clear invalid input
                    continue;
                }
                
                switch (choice) {
                    case 1:
                        hotel_user(name);
                        break;
                    case 2:
                        Resturant_user(name);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (IllegalStateException e) {
                System.out.println("Error: Scanner is closed!");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }

    // ==================== ADMIN PORTAL METHODS ====================
    
    public static void chkRoom() {
        check_all_rooms(); // Reusing existing method
    }
    
    public static void checkroom_status() {
        try {
            System.out.println("\n--------------- Check Room Status --------------");
            System.out.print("Enter Room No: ");
            String roomNo = input.nextLine().trim();
            
            if (roomNo.isEmpty()) {
                System.out.println("Error: Room number cannot be empty!");
                return;
            }
            
            File roomfile = new File("Room.txt");
            if (!roomfile.exists()) {
                System.out.println("Error: Room file not found!");
                return;
            }
            
            Scanner rf = new Scanner(roomfile);
            boolean found = false;
            
            while (rf.hasNextLine()) {
                String roomdata = rf.nextLine();
                if (roomdata.startsWith(roomNo + ",")) {
                    System.out.println("\nRoom Details:");
                    System.out.println(roomdata);
                    found = true;
                    break;
                }
            }
            rf.close();
            
            if (!found) {
                System.out.println("Room " + roomNo + " not found!");
            }
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Room file not found!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                input.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }
    
    public static void genrateroombill() {
        user_generate_bill(); // Reusing existing method
    }
    
    public static void check_menu() {
        view_menu(); // Reusing existing method
    }
    
    public static void additem() {
        try {
            System.out.println("\n========== ADD NEW ITEM TO MENU ==========");
            
            // Step 1: Take item details
            System.out.print("Enter Item ID: ");
            String itemId = sc.nextLine().trim();
            
            System.out.print("Enter Item Name: ");
            String itemName = sc.nextLine().trim();
            
            System.out.print("Enter Item Price: ");
            String itemPrice = sc.nextLine().trim();
            
            // Validate inputs
            if (itemId.isEmpty() || itemName.isEmpty() || itemPrice.isEmpty()) {
                System.out.println("Error: All fields are required!");
                return;
            }
            
            // Validate price is a positive number
            int price;
            try {
                price = Integer.parseInt(itemPrice);
                if (price <= 0) {
                    System.out.println("Error: Price must be a positive number!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Price must be a valid number!");
                return;
            }
            
            // Step 2: Create new item string
            String newItem = itemId + ", " + itemName + ", " + price;
            
            // Step 3: Read existing RestaurantMenu.txt file
            File menuFile = new File("RestaurantMenu.txt");
            ArrayList<String> menuItems = new ArrayList<>();
            
            if (menuFile.exists()) {
                Scanner fileScanner = new Scanner(menuFile);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    menuItems.add(line);
                    
                    // Check if item ID already exists
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].trim().equals(itemId)) {
                        System.out.println("Error: Item ID '" + itemId + "' already exists!");
                        fileScanner.close();
                        return;
                    }
                }
                fileScanner.close();
            } else {
                System.out.println("Creating new RestaurantMenu.txt file...");
            }
            
            // Step 4: Add new item to the list
            menuItems.add(newItem);
            
            // Step 5: Create temp file and write all items
            File tempFile = new File("RestaurantMenu_temp.txt");
            PrintWriter writer = new PrintWriter(tempFile);
            
            for (String item : menuItems) {
                writer.println(item);
            }
            writer.close();
            
            // Step 6: Delete original and rename temp file
            if (menuFile.exists()) {
                if (!menuFile.delete()) {
                    System.out.println("Error: Could not delete RestaurantMenu.txt!");
                    return;
                }
            }
            
            if (!tempFile.renameTo(menuFile)) {
                System.out.println("Error: Could not rename temp file to RestaurantMenu.txt!");
                return;
            }
            
            // Success message
            System.out.println("\n✓ SUCCESS: Item added to menu!");
            System.out.println("New Item: " + newItem);
            
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Menu file not found!");
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Error: Permission denied to access file!");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("\nPress Enter to continue...");
            try {
                sc.nextLine();
            } catch (Exception e) {
                // Ignore exception in finally block
            }
        }
    }
    
    public static void searchroom() {
        checkroom_status(); // Reusing existing method
    }
    
    public static void searchitem() {
        search_menu(); // Reusing existing method
    }

    // ==================== ADMIN PORTAL NAVIGATION ====================
    
    public static void hotel(String user) {
        while (true) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("| Welcome    " + user + "                               |");
                System.out.println("|                    Hotel Portal                      |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("| Press (0-6) to navigate to your option               |");
                System.out.println("| 1. Check All Rooms                                    |");
                System.out.println("| 2. Book Room for Guest                                |");
                System.out.println("| 3. Check Room Status/Update Room status               |");
                System.out.println("| 4. Generate Bill                                      |");
                System.out.println("| 5. Search Room/Guest                                  |");
                System.out.println("| 6. Add Room to System                                 |");
                System.out.println("| 0. Previous Menu                                      |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your choice: ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number!");
                    sc.nextLine(); // Clear invalid input
                    continue;
                }
                
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        chkRoom();
                        break;
                    case 2:
                        book_room();
                        break;
                    case 3:
                        checkroom_status();
                        break;
                    case 4:
                        genrateroombill();
                        break;
                    case 5:
                        searchroom();
                        break;
                    case 6:
                        add_room();
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (IllegalStateException e) {
                System.out.println("Error: Scanner is closed!");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }
    
    public static void Resturant(String user) {
        while (true) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("| Welcome " + user + "                                 |");
                System.out.println("|                Restaurant Portal                      |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("| Press (0-6) to navigate to your option               |");
                System.out.println("| 1. Check Menu                                         |");
                System.out.println("| 2. Add Item to Menu                                   |");
                System.out.println("| 3. Change Price                                       |");
                System.out.println("| 4. Generate Bill                                      |");
                System.out.println("| 5. Search Item                                        |");
                System.out.println("| 0. Previous Menu                                      |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your choice: ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number!");
                    sc.nextLine(); // Clear invalid input
                    continue;
                }
                
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        check_menu();
                        break;
                    case 2:
                        additem();
                        break;
                    case 3:
                        change_price();
                        break;
                    case 4:
                        restaurant_generate_bill();
                        break;
                    case 5:
                        searchitem();
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (IllegalStateException e) {
                System.out.println("Error: Scanner is closed!");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }
    
    public static void admin_menu(String user) {
        while (true) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("|       Welcome " + user + " to Admin  Portal               |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("|            1. Manage Hotel                           |");
                System.out.println("|            2. Manage Restaurant                      |");
                System.out.println("|            3. Back to previous Menu                  |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your choice: ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number!");
                    sc.nextLine(); // Clear invalid input
                    continue;
                }
                
                switch (choice) {
                    case 1:
                        hotel(user);
                        break;
                    case 2:
                        Resturant(user);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (IllegalStateException e) {
                System.out.println("Error: Scanner is closed!");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }

    // ==================== AUTHENTICATION METHODS ====================
    
    public static boolean checkUserCredentials(String id, String password) {
        try {
            // Validate email format
            if (!id.contains("@") || !id.endsWith("@user.com")) {
                System.out.println("Invalid email format. Must be: username@user.com");
                return false;
            }
            
            String username = id.substring(0, id.indexOf("@"));
            
            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Error: Username and password cannot be empty!");
                return false;
            }
            
            File userfile = new File("userlog.txt");
            
            // Create file if it doesn't exist
            if (!userfile.exists()) {
                userfile.createNewFile();
                PrintWriter writer = new PrintWriter(userfile);
                writer.println("name: user pass: user123");
                writer.close();
                System.out.println("Default user created: user@user.com / user123");
                // Check against default credentials
                return username.equals("user") && password.equals("user123");
            }
            
            // Read and check credentials
            Scanner reader = new Scanner(userfile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                
                if (line.startsWith("name:") && line.contains("pass:")) {
                    int passIndex = line.indexOf(" pass:");
                    
                    if (passIndex != -1) {
                        String storedUsername = line.substring(5, passIndex).trim();
                        String storedPassword = line.substring(passIndex + 6).trim();
                        
                        if (storedUsername.equalsIgnoreCase(username) && 
                            storedPassword.equals(password)) {
                            reader.close();
                            return true;
                        }
                    }
                }
            }
            reader.close();
            return false;
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: User credentials file not found!");
            return false;
        } catch (IOException e) {
            System.out.println("Error accessing credentials file: " + e.getMessage());
            return false;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid email format!");
            return false;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean checkAdminCredentials(String id, String password) {
        try {
            // Validate email format
            if (!id.contains("@") || !id.endsWith("@admin.com")) {
                System.out.println("Invalid email format. Must be: username@admin.com");
                return false;
            }
            
            String username = id.substring(0, id.indexOf("@"));
            
            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Error: Username and password cannot be empty!");
                return false;
            }
            
            File adminfile = new File("adminlog.txt");
            
            // Create file if it doesn't exist
            if (!adminfile.exists()) {
                adminfile.createNewFile();
                PrintWriter writer = new PrintWriter(adminfile);
                writer.println("name: admin pass: admin123");
                writer.close();
                System.out.println("Default admin created: admin@admin.com / admin123");
                // Check against default credentials
                return username.equals("admin") && password.equals("admin123");
            }
            
            // Read and check credentials
            Scanner reader = new Scanner(adminfile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                
                if (line.startsWith("name:") && line.contains("pass:")) {
                    int passIndex = line.indexOf(" pass:");
                    
                    if (passIndex != -1) {
                        String storedUsername = line.substring(5, passIndex).trim();
                        String storedPassword = line.substring(passIndex + 6).trim();
                        
                        if (storedUsername.equalsIgnoreCase(username) && 
                            storedPassword.equals(password)) {
                            reader.close();
                            return true;
                        }
                    }
                }
            }
            reader.close();
            return false;
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Admin credentials file not found!");
            return false;
        } catch (IOException e) {
            System.out.println("Error accessing credentials file: " + e.getMessage());
            return false;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid email format!");
            return false;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    // ==================== LOGIN MENUS ====================
    
    public static void Admin() {
        int count = 1;
        
        while (count <= 3) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("|                    Log In Menu Admin                 |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("|Greeting User!                                        |");
                System.out.println("|Please Enter your Admin Id and Password.              |");
                System.out.println("|Your Id should be in the format\"yourname@admin.com\" |");
                System.out.println("|You have three attempts                               |");
                System.out.println("|Press (0) to navigate to previous window              |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your Admin Id: ");
                String id = input.nextLine().trim();
                
                if (id.equals("0")) {
                    return;
                }
                
                System.out.print("Enter your Password: ");
                String password = input.nextLine().trim();
                
                if (checkAdminCredentials(id, password)) {
                    String name = id.substring(0, id.indexOf("@"));
                    admin_menu(name);
                    return;
                } else {
                    System.out.println("\nInvalid credentials! Attempt " + count + " of 3 failed.");
                    if (count < 3) {
                        System.out.println("Please try again.\n");
                    }
                    count++;
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input format!");
                input.nextLine(); // Clear invalid input
                System.out.println("\nAttempt " + count + " of 3 failed.");
                if (count < 3) {
                    System.out.println("Please try again.\n");
                }
                count++;
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("\nAttempt " + count + " of 3 failed.");
                if (count < 3) {
                    System.out.println("Please try again.\n");
                }
                count++;
            }
        }
        
        if (count > 3) {
            System.out.println("\n======================================================");
            System.out.println("   You have exceeded the maximum login attempts!");
            System.out.println("   Program will now terminate.");
            System.out.println("======================================================");
            exit();
        }
    }
    
    public static void User() {
        int count = 1;
        
        while (count <= 3) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("|                    Log In Menu User                  |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("|Greeting User!                                        |");
                System.out.println("|Please Enter your User Id and Password.               |");
                System.out.println("|Your Id should be in the format\"yourname@user.com\"  |");
                System.out.println("|You have three attempts                               |");
                System.out.println("|Press (0) to navigate to previous window              |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your User Id: ");
                String id = input.nextLine().trim();
                
                if (id.equals("0")) {
                    return;
                }
                
                System.out.print("Enter your Password: ");
                String password = input.nextLine().trim();
                
                if (checkUserCredentials(id, password)) {
                    String name = id.substring(0, id.indexOf("@"));
                    user_menu(name);
                    return;
                } else {
                    System.out.println("\nInvalid credentials! Attempt " + count + " of 3 failed.");
                    if (count < 3) {
                        System.out.println("Please try again.\n");
                    }
                    count++;
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input format!");
                input.nextLine(); // Clear invalid input
                System.out.println("\nAttempt " + count + " of 3 failed.");
                if (count < 3) {
                    System.out.println("Please try again.\n");
                }
                count++;
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("\nAttempt " + count + " of 3 failed.");
                if (count < 3) {
                    System.out.println("Please try again.\n");
                }
                count++;
            }
        }
        
        if (count > 3) {
            System.out.println("\n======================================================");
            System.out.println("   You have exceeded the maximum login attempts!");
            System.out.println("   Program will now terminate.");
            System.out.println("======================================================");
            exit();
        }
    }
    
    public static void Login() {
        while (true) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("|                     Log in Menu                      |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("|            1. Log In as an Admin                     |");
                System.out.println("|            2. Log in as a User                       |");
                System.out.println("|            3. Back to Main Menu                      |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your choice: ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number!");
                    sc.nextLine(); // Clear invalid input
                    continue;
                }
                
                switch (choice) {
                    case 1:
                        Admin();
                        return; // Return to main menu after Admin() finishes
                    case 2:
                        User();
                        return; // Return to main menu after User() finishes
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (IllegalStateException e) {
                System.out.println("Error: Scanner is closed!");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }

    // ==================== MAIN MENU AND INITIALIZATION ====================
    
    public static void exit() {
        System.out.println("\nExiting program! Good Bye!");
        try {
            if (sc != null) sc.close();
            if (input != null) input.close();
        } catch (Exception e) {
            // Ignore exceptions during shutdown
        }
        System.exit(0);
    }
    
    public static void intialMenu() {
        while (true) {
            try {
                System.out.println("\n|------------------------------------------------------|");
                System.out.println("|                      Main Menu                       |");
                System.out.println("|       Hotel Booking and Restaurant Billing System    |");
                System.out.println("|------------------------------------------------------|");
                System.out.println("| Greeting User! Please Input Your choice .            |");
                System.out.println("|            1. Log-In Menu                            |");
                System.out.println("|            2. Exit                                   |");
                System.out.println("|------------------------------------------------------|");
                
                System.out.print("Enter your choice: ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number!");
                    sc.nextLine(); // Clear invalid input
                    continue;
                }
                
                switch (choice) {
                    case 1:
                        System.out.println("Entering Log-in Menu... Please wait.");
                        Login();
                        break;
                    case 2:
                        exit();
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: Input stream closed!");
                break;
            } catch (IllegalStateException e) {
                System.out.println("Error: Scanner is closed!");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }
    
    public static void initializeFiles() {
        try {
            System.out.println("Initializing system files...");
            
            // Room file
            File roomfile = new File("Room.txt");
            if (!roomfile.exists()) {
                PrintWriter writer = new PrintWriter(roomfile);
                writer.println("101, Single, 27000, Available");
                writer.println("102, Single, 27000, Available");
                writer.println("103, Double, 35000, Booked");
                writer.println("104, Luxury, 55000, Booked");
                writer.println("105, Double, 35000, Available");
                writer.println("106, Luxury, 55000, Available");
                writer.println("107, VIP Suite, 85000, Booked");
                writer.println("108, VIP Suite, 85000, Available");
                writer.println("109, Single, 27000, Available");
                writer.println("110, Family Suite, 65000, Available");
                writer.close();
                System.out.println("✓ Room.txt created.");
            }
            
            // Guest file
            File guestfile = new File("Guest.txt");
            if (!guestfile.exists()) {
                PrintWriter writer = new PrintWriter(guestfile);
                writer.println("103, Ali Hassan, 3");
                writer.println("104, Sara Khan, 2");
                writer.println("107, John Peter, 5");
                writer.close();
                System.out.println("✓ Guest.txt created.");
            }
            
            // Bill file
            File billfile = new File("Bill.txt");
            if (!billfile.exists()) {
                PrintWriter writer = new PrintWriter(billfile);
                writer.println("103, Ali Hassan, 105000");
                writer.println("104, Sara Khan, 110000");
                writer.println("107, John Peter, 425000");
                writer.close();
                System.out.println("✓ Bill.txt created.");
            }
            
            // Restaurant Menu file
            File menuFile = new File("RestaurantMenu.txt");
            if (!menuFile.exists()) {
                PrintWriter writer = new PrintWriter(menuFile);
                writer.println("1, Burger, 300");
                writer.println("2, Pizza, 1800");
                writer.println("3, Biryani, 300");
                writer.println("4, Chicken Karahi, 1500");
                writer.println("5, Fries, 200");
                writer.println("6, Cold Drink, 150");
                writer.println("7, Ice Cream, 250");
                writer.println("8, Sandwich, 350");
                writer.println("9, Nihari, 150");
                writer.println("10, Shwarma, 250");
                writer.close();
                System.out.println("✓ RestaurantMenu.txt created.");
            }
            
            // Create empty files if they don't exist
            String[] filesToCreate = {
                "RestaurantCart.txt",
                "RestaurantOrders.txt",
                "RestaurantBills.txt",
                "RestaurantHistory.txt",
                "BookingHistory.txt",
                "adminlog.txt",
                "userlog.txt"
            };
            
            for (String fileName : filesToCreate) {
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                    System.out.println("✓ " + fileName + " created.");
                }
            }
            
            System.out.println("\nSystem initialization complete!");
            System.out.println("Press Enter to continue...");
            sc.nextLine();
            
        } catch (IOException e) {
            System.out.println("Error initializing files: " + e.getMessage());
            System.out.println("Trying to continue with existing files...");
        } catch (SecurityException e) {
            System.out.println("Error: Permission denied to create files!");
            System.out.println("Trying to continue with existing files...");
        } catch (Exception e) {
            System.out.println("Unexpected error during initialization: " + e.getMessage());
            System.out.println("Trying to continue with existing files...");
        }
    }
    
    public static void main(String[] args) {
        try {
            initializeFiles();
            intialMenu();
        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
            System.out.println("Program terminated unexpectedly.");
        }
    }
}
