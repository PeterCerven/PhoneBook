package phonebook;

import java.util.ArrayList;
import java.util.HashMap;

public class Logic {
    private final ArrayList<String> namesToFind;
    private ArrayList<phonebook.Person> peopleData;
    private ArrayList<phonebook.Person> peopleAfterBubbleSort;
    private ArrayList<phonebook.Person> peopleAfterQuickSort;
    private long linearSearchTime;
    private long jumpSearchTime;
    private long bubbleSortTime;
    private long quickSortTime;
    private long BinarySearchTime;

    public Logic(GetData data) {
        namesToFind = data.getNames();
        peopleData = data.getPairs();
        peopleAfterBubbleSort = new ArrayList<>(peopleData);
        peopleAfterQuickSort = new ArrayList<>(peopleData);
    }


    public ArrayList<String> getNamesToFind() {
        return namesToFind;
    }

    public ArrayList<phonebook.Person> getPeopleData() {
        return peopleData;
    }

    public ArrayList<phonebook.Person> getPeopleAfterBubbleSort() {
        return peopleAfterBubbleSort;
    }

    public ArrayList<phonebook.Person> getPeopleAfterQuickSort() {
        return peopleAfterQuickSort;
    }

    public long getLinearSearchTime() {
        return linearSearchTime;
    }

    public long getJumpSearchTime() {
        return jumpSearchTime;
    }

    public long getBubbleSortTime() {
        return bubbleSortTime;
    }

    public long getQuickSortTime() {
        return quickSortTime;
    }

    public long getBinarySearchTime() {
        return BinarySearchTime;
    }

    public void setQuickSortTime(long quickSortTime) {
        this.quickSortTime = quickSortTime;
    }

    public int linearSearch() {
        int found = 0;
        long timeBefore = System.currentTimeMillis();
        for (String name : namesToFind) {
            for (phonebook.Person person : peopleData) {
                if (name.equals(person.getName())) {
                    found++;
                }
            }
        }

        linearSearchTime = System.currentTimeMillis() - timeBefore;
        return found;
    }


    public int hashingSearch(HashMap<String, Integer> phoneBook) {
        int counter = 0;
        for (String name : namesToFind) {
            if (phoneBook.containsKey(name)) {
                counter++;
            }
        }
        return counter;
    }


    public boolean quickSort(ArrayList<phonebook.Person> arrayToSort, int start, int end, long time) {
        if (System.currentTimeMillis() - time > linearSearchTime * 10) {
            return false;
        }
        if (end - start < 1) {
            return true;
        }
        phonebook.Person person = arrayToSort.get(end);
        int index = start - 1;
        for (int i = start; i <= end; i++) {
            if (person.compareTo(arrayToSort.get(i)) >= 0) {
                index++;
                swap(arrayToSort, index, i);
            }
        }
        quickSort(arrayToSort, start, index - 1, time);
        quickSort(arrayToSort, index + 1, end, time);

        return !(System.currentTimeMillis() - time > linearSearchTime * 10);

    }

    private void swap(ArrayList<phonebook.Person> arrayToSort, int j, int i) {
        phonebook.Person tmp = arrayToSort.get(i);
        arrayToSort.set(i, arrayToSort.get(j));
        arrayToSort.set(j, tmp);
    }


    public int binarySearch(ArrayList<phonebook.Person> search) {
        long binaryTimeBefore = System.currentTimeMillis();
        int found = 0;
        for (String name : namesToFind) {
            int start = 0;
            int end = search.size() - 1;
            while (start <= end) {
                int middle = (start + end) / 2;


                if (name.compareTo(search.get(middle).getName()) == 0) {
                    found++;
                    break;
                }
                if (name.compareTo(search.get(middle).getName()) > 0) {
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }
        }
        this.BinarySearchTime = System.currentTimeMillis() - binaryTimeBefore;
        return found;
    }


    public int jumpSearch(ArrayList<phonebook.Person> people) {
        long jumpTimeBefore = System.currentTimeMillis();
        int counter = 0;
        for (String name : namesToFind) {
            int step = (int) Math.floor(Math.sqrt(people.size()));
            int curr = 0;
            int prev = 0;
            while (people.get(curr).getName().compareTo(name) < 0) {
                if ((curr + step >= people.size() - 1)) {
                    prev = curr;
                    curr = people.size() - 1;
                    break;
                } else {
                    prev = curr;
                    curr += step;
                }
            }

            while (people.get(curr).getName().compareTo(name) > 0 && curr >= prev) {
                curr--;
            }

            if (people.get(curr).getName().compareTo(name) == 0) {
                counter++;
            }
        }
        long jumpTimeAfter = System.currentTimeMillis();
        jumpSearchTime = jumpTimeAfter - jumpTimeBefore;
        return counter;
    }


    public boolean bubbleSort() {
        long bubbleTimeBefore = System.currentTimeMillis();
        long bubbleTimeAfter = System.currentTimeMillis();
        int j = peopleAfterBubbleSort.size() - 1;
        for (int k = 0; k < peopleAfterBubbleSort.size() - 1; k++) {
            bubbleTimeAfter = System.currentTimeMillis();
            if (bubbleTimeAfter - bubbleTimeBefore > linearSearchTime * 10) {
                bubbleSortTime = bubbleTimeAfter - bubbleTimeBefore;
                return false;
            }
            for (int i = 0; i < j; i++) {
                if ((peopleAfterBubbleSort.get(i).compareTo(peopleAfterBubbleSort.get(i + 1))) > 0) {
                    phonebook.Person tpm = new phonebook.Person(peopleAfterBubbleSort.get(i).getNumber(), peopleAfterBubbleSort.get(i).getName());
                    peopleAfterBubbleSort.set(i, peopleAfterBubbleSort.get(i + 1));
                    peopleAfterBubbleSort.set(i + 1, tpm);
                }

            }
            j--;
        }
        bubbleSortTime = bubbleTimeAfter - bubbleTimeBefore;
        return true;
    }

    public long getMS(long time) {
        return time % 1000;
    }

    public long getMinutes(long time) {
        return time / 1000 / 60;
    }

    public long getSeconds(long time) {
        return time / 1000 % 60;
    }

    public void printArray(ArrayList<phonebook.Person> array) {
        for (phonebook.Person person : array) {
            System.out.println(person);
        }
    }
}
