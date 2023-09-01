package ru.hogwarts.school.exeptions;

public class AvatarNotFoundExeption extends RuntimeException{
    public AvatarNotFoundExeption() {
    }

    public AvatarNotFoundExeption(String message) {
        super(message);
    }

    public AvatarNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public AvatarNotFoundExeption(Throwable cause) {
        super(cause);
    }

    public AvatarNotFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
