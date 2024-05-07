import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Использование: java Task1 <файл_окружности> <файл_точек>");
            System.exit(1);
        }

        try (BufferedReader circleReader = new BufferedReader(new FileReader(args[0]));
             BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]))) {

            String[] circleLine = circleReader.readLine().split(" ");
            double centerX = Double.parseDouble(circleLine[0]);
            double centerY = Double.parseDouble(circleLine[1]);
            double radius = Double.parseDouble(circleReader.readLine());

            String line;
            while ((line = pointsReader.readLine()) != null) {
                String[] pointCoords = line.split(" ");
                double pointX = Double.parseDouble(pointCoords[0]);
                double pointY = Double.parseDouble(pointCoords[1]);

                double distanceSquared = Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2);
                if (distanceSquared == Math.pow(radius, 2)) {
                    System.out.println("0 - точка лежит на окружности");
                } else if (distanceSquared < Math.pow(radius, 2)) {
                    System.out.println("1 - точка внутри");
                } else {
                    System.out.println("2 - точка снаружи");
                }
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            System.exit(1);
        }
    }
}