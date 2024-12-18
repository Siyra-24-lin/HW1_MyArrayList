import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList_NameSurname<String> arrayList = new ArrayList_NameSurname<>();
        arrayList.add("Tom");
        arrayList.add("Sam");
        arrayList.add("Jack");
        arrayList.add("Anna");
        arrayList.add("Kate");
        System.out.println(arrayList);
        System.out.println(arrayList.size());

        arrayList.add(2,"Sally");
        System.out.println(arrayList);

        arrayList.quickSort(Comparator.naturalOrder());
        System.out.println(arrayList);

        System.out.println(arrayList.get(4));
        arrayList.set(4, "Luce");
        System.out.println(arrayList);
        System.out.println(arrayList.isSorted());

        arrayList.quickSort(Comparator.naturalOrder());
        System.out.println(arrayList.isSorted());

        arrayList.split(3);
        System.out.println(arrayList);

        arrayList.remove(1);
        System.out.println(arrayList);

        arrayList.clear();
        System.out.println(arrayList);

    }
}