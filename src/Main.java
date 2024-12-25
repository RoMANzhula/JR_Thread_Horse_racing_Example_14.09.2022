import java.util.ArrayList;
import java.util.List;

public class Main { // оголошуємо публічний головний клас
    public static void main(String[] args) throws InterruptedException { // оголошуємо головний метод, який пробрасыває виняток InterruptedException (через метод calculateHorsesFinished)
        List<Horse> horses = prepareHorsesAndStart(10); // оголошуємо змінну типу інтерфейс List<Horse> і викликаємо метод prepareHorsesAndStart з параметром: кількість коней
        while (calculateHorsesFinished(horses) != horses.size()) { // цикл поки (метод calculateHorsesFinished з аргументом: список horses) не дорівнює розміру списку horses
            System.out.println("All horses finished!"); // виводимо "Усі коні фінішували!"
        }
    }

    public static List<Horse> prepareHorsesAndStart(int horsesCount) { // оголошуємо публічний статичний метод типу List<Horse> підготовитиКонейІЗапустити з параметром: число
        List<Horse> horses = new ArrayList<>(horsesCount); // оголошуємо змінну типу List<Horse> як новий об'єкт з класу ArrayList з попереднім розміром списку
        String number; // оголошуємо змінну типу String

        for (int i = 1; i < horsesCount + 1; i++) { // цикл від 1 до параметра методу + 1 (щоб індексація починалася з 1)
            number = i < 10 ? ("0" + i) : "" + i; // змінній number присвоюємо значення: якщо i < 10, то "0" + i, інакше просто i
            horses.add(new Horse("Horse_" + number)); // додаємо новий об'єкт класу Horse в список horses
        }

        System.out.println("All horses start the race!"); // виводимо "Усі коні стартують в гонці!"
        for (int i = 0; i < horsesCount; i++) { // цикл від 0 до параметра методу по збільшенню
            horses.get(i).start(); // запускаємо потік для кожного коня
        }
        return horses; // повертаємо список коней
    }

    public static int calculateHorsesFinished(List<Horse> horses) throws InterruptedException { // публічний статичний метод для обчислення кількості коней, які фінішували
        int finishedCount = 0; // оголошуємо змінну для підрахунку кількості фінішованих коней
        for (int i = 0; i < horses.size(); i++) { // цикл від 0 до розміру списку horses
            if (horses.get(i).isFinished()) { // якщо кінь фінішував
                finishedCount++; // збільшуємо лічильник фінішованих
            } else { // інакше
                System.out.println("Waiting for " + horses.get(i).getName()); // виводимо: "Очікуємо на " + ім'я коня
                horses.get(i).join(); // чекаємо завершення потоку цього коня
            }
        }
        return finishedCount; // повертаємо кількість фінішованих коней
    }
}
