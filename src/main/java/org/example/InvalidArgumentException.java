package org.example;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(){
        super();
    }
    public InvalidArgumentException(String msg){ super(msg);}
}
