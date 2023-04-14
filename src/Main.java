import java.util.ArrayList;
import java.util.List;

public class Main {//обьявляем публичный класс Главный
    public static void main(String[] args) throws InterruptedException {//обьявляем главный метод, кот.пробрасывает исключение класса ПрерванноеИсключение (из-за метода калькуляторФинишировавшихЛошадей)
        List<Horse> horses = prepareHorsesAndStart(10);//обьявляем ссыл.перем. типа интерфейса List<Horse> = методу подготовитьЛошадейИНачать(аргумент: число лошадей)
        while (calculateHorsesFinished(horses) != horses.size()){//цикл пока (метод калькуляторФинишировавшихЛошадей(аргумент: список horses) НЕ= размеру списка horses)
            System.out.println("All horses finished!");//печатаем - "Все лошади финишировали!"
        }
    }

    public static List<Horse> prepareHorsesAndStart(int horsesCount) {//обьявляем публичный статический типа интерфейса List<Horse> метод подготовитьЛошадейИНачать(параметр: число)
        List<Horse> horses = new ArrayList<>(horsesCount);//обьявляем ссыл.перем.типа интерфейс List<Horse> на новый обьект с поведением класса ArrayList<>(предварительный размер списка: перем.-параметр метода)
        String number;//обьявляем пустую переменную типа String

        for (int i = 1; i < horsesCount + 1; i++) {//обьявляем цикл размером от 1 до перем.-параметр метода + 1 (чтоб счет начинался от единицы) по возрастанию
            number = i < 10 ? ("0" + i) : "" + i;//перем. number присваиваем значение = (если i < 10 ТО == ("0" + i) ИНАЧЕ ("" + i (пустая строка + индекс цикла))
            horses.add(new Horse("Horse_" + number));//заполняем список horses (новыми обьектами класса Лошадь(аргумент для конструктора: литерал + переменная number)
        }

        System.out.println("All horses start the race!");//печатаем - литерал
        for (int i = 0; i < horsesCount; i++) {//обьявляем цикл размером от 0 до перем.-параметр метода по возрастанию
            horses.get(i).start();//ссыл.перем. на список horses.получитьЗначение(по индексу).запуститьПоток()   ------------- ТУТ ЗАПУСКАЕМ ПОТОК
        }
        return horses;//вернуть содержимое ссыл.перем. на список
    }

    public static int calculateHorsesFinished(List<Horse> horses) throws InterruptedException {//обьявляем публичный статический типа int метод калькуляторФинишировавшихЛошадей(параметр: ссыл.перем.типа интерфейс List<Horse>)
        // который пробрасывает исключение класса ПрерванноеИсключение
        int finishedCount = 0;//обьявляем переменную типа int счетчикФинишировавших = 0
        for (int i = 0; i < horses.size(); i++) {//обьявляем цикл размером от 0 до размер списка horses по возрастанию
            if (horses.get(i).isFinished()) {//если (элемент из списка(по индексу) финишировал(булевая переменная класса Лошадь), то
                finishedCount++;//переменную счетчикФинишировавших увеличиваем на 1
            } else {//иначе
                System.out.println("Waiting for " + horses.get(i).getName());//печатаем - литерал + данный элемент списка horses .получитьИмя()
                horses.get(i).join();//данный элемент списка horses.вызывает метод join(), кот.заставляет главный поток обождать пока выполниться второстепенный поток
            }
        }
        return finishedCount;//вернуть счетчикФинишировавших
    }
}
