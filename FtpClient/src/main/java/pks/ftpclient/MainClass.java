package pks.ftpclient;

import java.util.Scanner;
import pks.ftpclient.commands.*;


public class MainClass {

    private static void printMenu() {
        String menu = "Выбор действия:\n";
        menu += "1) Создать каталог\n";
        menu += "2) Удалить каталог\n";
        menu += "3) Перейти в заданный каталог\n";
        menu += "4) Вывести содержимое текущего каталога\n";
        menu += "5) Перейти в родительский каталог\n";
        menu += "6) Копировать файл в локальный\n";
        menu += "7) Отправить файл на сервер\n";
        menu += "8) Удалить файл\n";
        menu += "9) Переименовать файл\n";
        menu += "0) Выход\n";
        System.out.print(menu);
    }

    public static void main(String[] args) {
        String server, user, pass;
        Scanner in = new Scanner(System.in);
        System.out.print("Сервер:");
        server = in.nextLine();
        FtpClient ftp = new FtpClient(server);
        ftp.connect();
        System.out.print("Пользователь:");
        user = in.nextLine();
        System.out.print("Пароль:");
        pass = in.nextLine();
        ftp.login(user, pass);
        int k;
        while (true) {
            try {
                printMenu();
                k = in.nextInt();
                switch (k) {
                    case 1 -> new MakeDirCommand().execute();
                    case 2 -> new RemoveDirCommand().execute();
                    case 3 -> new ChangeDirCommand().execute();
                    case 4 -> new ListCommand().execute();
                    case 5 -> new UpDirCommand().execute();
                    case 6 -> new GetCommand().execute();
                    case 7 -> new SendCommand().execute();
                    case 8 -> new DeleteCommand().execute();
                    case 9 -> new RenameCommand().execute();
                    case 0 -> {
                        System.out.println("Выход из программы.");
                        return;
                    }
                    default -> System.out.println("Некорректный ввод.");
                }
            } catch (MyException e) {
            }
        }
    }
}
