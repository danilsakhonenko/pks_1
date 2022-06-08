package pks.ftpclient;

public class MyException extends Exception {
    private final int key;
    
    public MyException(int key)
    {
        this.key=key;
        PrintException();
    }
    
    private String PrintException()
    {
        return switch (key) {
            case 1 -> "Исключение: Ошибка формата ввода!\n";
            default -> null;
        };
    }
}
