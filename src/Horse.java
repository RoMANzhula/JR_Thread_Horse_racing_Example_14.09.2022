public class Horse extends Thread { // оголошуємо публічний клас Horse, успадкований від класу Thread
    private boolean isFinished; // оголошуємо приватну змінну типу boolean для збереження статусу завершення

    public Horse(String name) { // оголошуємо публічний конструктор Horse (параметр: посилальна змінна типу String)
        super(name); // ініціалізуємо конструктор суперкласу
    }
    
    public boolean isFinished() { // оголошуємо метод типу boolean isFinished()
        return isFinished; // повертаємо значення змінної isFinished
    }

    @Override // переозначаємо метод інтерфейсу Runnable
    public void run() { // публічний метод run(), який не повертає значення
        String str = ""; // оголошуємо посилальну змінну типу String, яка ініціалізується порожнім рядком
        for (int i = 0; i < 1001; i++) { // оголошуємо цикл від 0 до 1000
            str += "" + i; // додаємо до змінної str поточне значення i
            if (i == 1000) { // якщо i дорівнює 1000 (максимальний розмір циклу)
                str = " has finished the race!"; // присвоюємо змінній str текст " has finished the race!"
                System.out.println(getName() + str); // виводимо на екран ім'я потоку і повідомлення про завершення
                isFinished = true; // змінній класу присвоюємо значення true
            }
        }
    }
}
