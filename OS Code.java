/*
    OS Group Project :
    Q) Simulate the simple Paging and Demand paging concepts by using following details:
       Memory Frames : 8
       Process A : 3 pages
       Load pages into Frames and print the page table and free frames list for simple paging and demand paging.

    Team Members :
    Ashruth - 22311A05K4
    Ishika - 22311A05M9
    Shiva Kumar - 22311A05Q4
    Karthisha - 23315A0526
 */

import java.util.*;
import java.io.*;
class Main {
    // The variables frames and pages are declared final because there should not be any modifications in their values
    static final int totalFrames = 8; // 0 - 7 
    static final int totalPages = 3; // A1, A2, A3
    static List<Integer> freeFrames; // Denotes all the free frames available
    static Random random = new Random(); // Creating an instance of random class

    public static void main(String[] args) {
        resetFreeFrames(); // Starting the CPU to implement simple paging

        System.out.println("SIMPLE PAGING");
        simplePaging(); // Implementation of Simple Paging

        resetFreeFrames(); // Restarting the CPU to implement demand paging

        System.out.println("DEMAND PAGING");
        demandPaging(); // Implementation of Demand Paging
    }

    // Implementation of Simple Paging
    static void simplePaging() {
        // We use a map to store the page and the corresponding frame
        // We use a linked hashmap to store them in the order in which they are arrived in the map
        Map<String, Integer> pageTable = new LinkedHashMap<>(); 
        // As simple paging requires all pages to be in main memory, we'll accommodate all the pages in main memory
        for (int i = 0; i < 3; i++) {
            String page = "A" + i; // Denotes the current page
            int frame = getRandomFreeFrame(); // We'll check for a free frame which is available
            pageTable.put(page, frame); // Put the page in the frame
        }
        // Print the page table stored in main memory
        printPageTable(pageTable);
        // Print the available free frames
        printFreeFrames();
    }

    // Implementation of Demand Paging
    static void demandPaging() {
        // We use a map to store the page and the corresponding frame
        // We use a linked hashmap to store them in the order in which they are arrived in the map
        Map<String, Integer> pageTable = new LinkedHashMap<>();
        // We'll only simulate referencing pages on demand
        for (int i = 0; i < 3; i++) {
            String page = "A" + i; // Denotes the current page
            System.out.println("Referencing Page " + page + "..."); // The CPU is demanding / requesting a page not available in main memory
            int frame = getRandomFreeFrame(); // We'll check for a free frame which is available
            pageTable.put(page, frame); // Put the page in the frame
        }
        // Print the page table stored in main memory
        printPageTable(pageTable);
        // Print the available free frames
        printFreeFrames();
    }
    
    static void printPageTable(Map<String, Integer> pageTable) {
        System.out.println("Page Table: ");
        // Display the page table
        for (String entry : pageTable.keySet()) {
            System.out.println("Page " + entry + " -> Frame " + pageTable.get(entry));
        }
    }

    static void printFreeFrames() {
        // The free frames are already available in our arraylist. So, just display the arraylist
        System.out.println("Free Frames: " + freeFrames);
    }

    static int getRandomFreeFrame(){
        // This function returns a random free frame which is available in main memory
        // Since our pages are less than the number of frames, there will be always free frames available
        int idx = random.nextInt(freeFrames.size()); // Returns a index in the array list
        // We'll remove that index from arraylist and return simulatenously
        return freeFrames.remove(idx);
    }

    static void resetFreeFrames(){
        freeFrames = new ArrayList<>(); // Initialsing free frames list
        for(int i = 0; i < 8; i++){
            freeFrames.add(i); // Initially, every frame is a free frame
        }
    }
}