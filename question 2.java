package GlobalTrendProgrammingAssessment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Java program to show
// ConcurrentModificationException
public class modificationError {
    public static void main(String args[])
    {

        // Creating an object of ArrayList Object
        ArrayList<String> arr
                = new ArrayList<String>();

        arr.add("One");
        arr.add("Two");
        arr.add("Three");
        arr.add("Four");

        try {
            // Printing the elements
            System.out.println(
                    "ArrayList: ");
            Iterator<String> iter
                    = arr.iterator();

            while (iter.hasNext()) {

                System.out.print(iter.next()
                        + ", ");

                // ConcurrentModificationException
                // is raised here as an element
                // is added during the iteration
                System.out.println(
                        "\n\nTrying to add"
                                + " an element in "
                                + "between iteration\n");
                arr.add("Five");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

/*public class ConcurrentModificationDemo {
      public static void main(String[] args) {
            List<String> list = new ArrayList<>();
            list.add("A");
            list.add("B");
            list.add("C");

            // Proper way to handle modification during iteration
            // Collect elements to remove in a separate list
            List<String> toRemove = new ArrayList<>();
            for (String s : list) {
                if (s.equals("B")) {
                    toRemove.add(s);
                }
            }
            list.removeAll(toRemove);

            System.out.println("List after modification: " + list);
        }
    }*/
