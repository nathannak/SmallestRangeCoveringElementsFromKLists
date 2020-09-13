import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

        List<List<Integer>> nums = new ArrayList<>();

        List<Integer> temp = new ArrayList<>();
        temp.add(4);
        temp.add(10);
        temp.add(15);
        temp.add(24);
        temp.add(26);

        nums.add(new ArrayList<>(temp));
        temp.clear();

        temp.add(0);
        temp.add(9);
        temp.add(12);
        temp.add(20);

        nums.add(new ArrayList<>(temp));
        temp.clear();

        temp.add(5);
        temp.add(18);
        temp.add(22);
        temp.add(30);

        nums.add(new ArrayList<>(temp));
        temp.clear();

        System.out.println(smallestRange(nums)[0] + " & " + smallestRange(nums)[1]);

    }

    public static int[] smallestRange(List<List<Integer>> nums) {

        //sort by value in each row and column
        PriorityQueue<int[]> q = new PriorityQueue<>( (a,b)->nums.get(a[0]).get(a[1])-nums.get(b[0]).get(b[1]) );

        // start and end are beginning and end of interval we are looking for
        int max = Integer.MIN_VALUE, start = 0, end = Integer.MAX_VALUE;

        for (int i = 0; i < nums.size(); i++) {

            // offer row and column to the pq, col starts with 0 obviously
            q.offer(new int[]{i, 0});

            // find out the max value when column is 0 in the beginning
            max = Math.max(max, nums.get(i).get(0));
        }


        while (q.size() == nums.size()) {

            int e[] = q.poll();

            int row = e[0];
            int col = e[1];

            //adjust the range
            if (end - start > max - nums.get(row).get(col)) {
                start = nums.get(row).get(col);
                end = max;
            }

            //try next column of current row at hand, since
            //we used row,col element already
            if (col + 1 < nums.get(row).size()) {
                q.offer(new int[]{row, col + 1});

                //as you are trying new element in next column of current row
                //also improve max if you can
                max = Math.max(max, nums.get(row).get(col + 1));
            }
        }
        return new int[]{start, end};
    }

}
