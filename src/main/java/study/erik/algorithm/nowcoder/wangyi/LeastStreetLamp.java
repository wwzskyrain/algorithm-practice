package study.erik.algorithm.nowcoder.wangyi;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @Date 2019-07-13
 * @Created by erik
 */
public class LeastStreetLamp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();

        List<Integer> results = new LinkedList<>();
        while (scanner.hasNext()) {
            Integer streetSize = Integer.valueOf(scanner.next());
            String street = scanner.next();

            int lampCounter = 0;
            for (int i = 0; i < streetSize; ) {
                char grid = street.charAt(i);
                if (grid == '.') {
                    lampCounter++;
                    i += 3;
                } else {
                    i++;
                }
            }
            results.add(lampCounter);
        }

        for (Integer result : results) {
            System.out.println(result);
        }

    }

}
