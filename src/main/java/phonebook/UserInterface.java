package phonebook;


import java.util.HashMap;

public class UserInterface {
    private final Logic logic;


    public UserInterface(String directory, String find) {
        GetData data = new GetData(directory, find);
        logic = new Logic(data);
        start();
    }

    private void start() {
        linear();
        bubbleJump();
        quickBinary();
        hashTable();
    }

    private void linear() {
        System.out.println("Start searching (linear search)...");

        int found = logic.linearSearch();
        System.out.printf("Found %d / %d entries. ", found, logic.getNamesToFind().size());
        System.out.printf("Time taken: %d min. %d sec. %d ms.\n\n",
                logic.getMinutes(logic.getLinearSearchTime()),
                logic.getSeconds(logic.getLinearSearchTime()),
                logic.getMS(logic.getLinearSearchTime()));
    }

    private void bubbleJump() {
        System.out.println("Start searching (bubble sort + jump search)...");

        int found;
        long resultTime;
        long searchTime;
        String stopped = "";
        //if bubbleSort doesn't take too much time
        if (logic.bubbleSort()) {
            found = logic.jumpSearch(logic.getPeopleAfterBubbleSort());
            resultTime = logic.getBubbleSortTime() + logic.getJumpSearchTime();
            searchTime = logic.getJumpSearchTime();
        } else {
            long beforeLinear = System.currentTimeMillis();
            found = logic.linearSearch();
            searchTime = System.currentTimeMillis() - beforeLinear;
            resultTime = logic.getBubbleSortTime() + searchTime;
            stopped = " - STOPPED, moved to linear search";
        }

        printResults(found, logic.getNamesToFind().size(), resultTime, logic.getBubbleSortTime(), searchTime, stopped);
    }

    private void quickBinary() {
        System.out.println("Start searching (quick sort + binary search)...");


        int found;
        long totalTime;
        long searchTime;
        String stopped = "";

        long quickTimeBefore = System.currentTimeMillis();
        if (logic.quickSort(logic.getPeopleAfterQuickSort(), 0, logic.getPeopleAfterQuickSort().size() - 1,
                quickTimeBefore)) {
            logic.setQuickSortTime(System.currentTimeMillis() - quickTimeBefore);
            found = logic.binarySearch(logic.getPeopleAfterQuickSort());
            totalTime = logic.getQuickSortTime() + logic.getBinarySearchTime();
            searchTime = logic.getBinarySearchTime();
        } else {
            logic.setQuickSortTime(System.currentTimeMillis() - quickTimeBefore);
            long beforeLinear = System.currentTimeMillis();
            found = logic.linearSearch();
            searchTime = System.currentTimeMillis() - beforeLinear;
            totalTime = logic.getQuickSortTime() + searchTime;
            stopped = " - STOPPED, moved to linear search";
        }

        printResults(found, logic.getNamesToFind().size(), totalTime, logic.getQuickSortTime(), searchTime, stopped);
    }

    private void hashTable() {
        System.out.println("Start searching (hash table)...");
        long beforeCreation = System.currentTimeMillis();
        HashMap<String, Integer> phoneBook = new HashMap<>();
        for (Person person : logic.getPeopleData()) {
            phoneBook.put(person.getName(), person.getNumber());
        }
        long creatingTime = System.currentTimeMillis() - beforeCreation;

        long beforeHashing = System.currentTimeMillis();
        int found = logic.hashingSearch(phoneBook);
        long Hashing = System.currentTimeMillis() - beforeHashing;

        long totalTime = creatingTime + Hashing;

        System.out.printf("Found %d / %d entries. ", found,  logic.getNamesToFind().size());
        System.out.printf("Time taken: %d min. %d sec. %d ms. \n",
                logic.getMinutes(totalTime),  logic.getSeconds(totalTime),  logic.getMS(totalTime));

        System.out.printf("Creating time: %d min. %d sec. %d ms.\n",
                logic.getMinutes(creatingTime),  logic.getSeconds(creatingTime),  logic.getMS(creatingTime));
        System.out.printf("Searching time: %d min. %d sec. %d ms.\n",
                logic.getMinutes(Hashing),  logic.getSeconds(Hashing),  logic.getMS(Hashing));
        System.out.println("");
    }

    private void printResults(int found, int size, long resultTime, long sortingTime, long searchTime, String stop) {


        System.out.printf("Found %d / %d entries. ", found, size);

        System.out.printf("Time taken: %d min. %d sec. %d ms.\n",
                logic.getMinutes(resultTime), logic.getSeconds(resultTime), logic.getMS(resultTime));
        System.out.printf("Sorting time: %d min. %d sec. %d ms.%s\n",
                logic.getMinutes(sortingTime), logic.getSeconds(sortingTime), logic.getMS(sortingTime), stop);
        System.out.printf("Searching time: %d min. %d sec. %d ms.\n",
                logic.getMinutes(searchTime), logic.getSeconds(searchTime), logic.getMS(searchTime));
        System.out.println("");
    }

}
