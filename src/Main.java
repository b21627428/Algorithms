import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Person person1 = new Person(6L, "Said", "Kaya");
        Person person2 = new Person(5L, "Ahmet Kerem", "Kaya");
        Person person3 = new Person(7L, "Furkan", "Kaya");
        Person[] people = {person1, person2,person3};

//        Sort.selectionSort(people);
//        System.out.println(Arrays.toString(people));
//        Sort.shuffle(people);
//
//        Sort.insertionSort(people);
//        System.out.println(Arrays.toString(people));
//        Sort.shuffle(people);
//
//        Sort.shellSort(people);
//        System.out.println(Arrays.toString(people));
//        Sort.shuffle(people);
//
//        Sort.mergeSort(people);
//        System.out.println(Arrays.toString(people));
//        Sort.shuffle(people);
//
//        Sort.mergeSort(people,false);
//        System.out.println(Arrays.toString(people));
//        Sort.shuffle(people);
//
//        Sort.bottomUpMergeSort(people);
//        System.out.println(Arrays.toString(people));
//        Sort.shuffle(people);
//
//        Sort.quickSort(people,false);
//        System.out.println(Arrays.toString(people));
//        Sort.shuffle(people);
//
//        Sort.quickSort(people,true);
//        System.out.println(Arrays.toString(people));
//        Sort.shuffle(people);
//
//        MaxHeap<Person> personHeap = new MaxHeap<>(Arrays.asList(people));
//        personHeap.sort();
//        System.out.println(personHeap);
//
//        Sort.quickSort(people,false);
//        int resultIndex = Search.binarySearch(people, person3);
//        System.out.println(resultIndex);

        BST<Long,Person> bst = new BST<>();
        bst.put(person1.getId(),person1);
        bst.put(person2.getId(),person2);
        bst.put(person3.getId(),person3);
        System.out.println(bst.get(1L));
        System.out.println(bst.keys());
    }
}
