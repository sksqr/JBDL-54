import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<Integer> list =new ArrayList<>();
        list.add(10);
        list.add(11);
        list.add(7);

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for(Integer i : list){
            System.out.println(i);
        }

        list.stream().filter(x -> x%2 == 0)
                .forEach(System.out::println);


    }
}