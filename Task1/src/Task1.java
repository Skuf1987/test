public class Task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StringBuilder path = new StringBuilder();
        int startIndex = 0;
        while (true) {
            path.append(startIndex + 1);
            startIndex = (startIndex + m - 1) % n;
            if (startIndex == 0) {
                break;
            }
        }

        System.out.println(path.toString());
    }
}