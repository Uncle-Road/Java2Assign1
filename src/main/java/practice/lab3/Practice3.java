package practice.lab3;

import java.lang.*;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class Practice3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Please input the functio NO:\n" +
                    "1 - Get even numbers\n" +
                    "2 - Get odd numbers\n" +
                    "3 - Get prime numbers\n" +
                    "4 - Get prime numbers that are bigger than 5\n" +
                    "0 - Qunum");
            int no = scanner.nextInt();
            if(no == 0 ) break;
            else if(no > 4 || no < 0) continue;
            System.out.println("Input size of the list:");
            int len = scanner.nextInt();
            System.out.println("Input elements of the list:");
            int [] lst = new int[len];
            for(int i=0;i<len;i++) {
                lst[i] = scanner.nextInt();
            }

            System.out.println("Filter results:");
            List<Integer> ans = new ArrayList<>();
            if (no == 1){
                ans = Arrays.stream(lst).filter(isEven()).boxed().collect(Collectors.toList());
            }else if (no == 2){
                ans = Arrays.stream(lst).filter(isOdd()).boxed().collect(Collectors.toList());
            }else if (no == 3){
                ans = Arrays.stream(lst).filter(isPrime()).boxed().collect(Collectors.toList());
            }else {
                ans = Arrays.stream(lst).filter(isPrime().and(biggerThan5())).boxed().collect(Collectors.toList());
            }
            System.out.println(ans);
        }


    }

    private static IntPredicate isEven() {
        return num -> (num % 2 == 0);
    }

    private static IntPredicate isOdd() {
        return num -> num % 2 == 1;
    }

    private static IntPredicate isPrime() {
        return num -> {
            for (int i = 2; i <= num / 2 ; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        };
    }

    private static IntPredicate biggerThan5() {
        return num -> num > 5;
    }

}
