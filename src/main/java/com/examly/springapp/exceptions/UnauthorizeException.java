
package com.examly.springapp.exceptions;

public class UnauthorizeException extends RuntimeException 
{
    public UnauthorizeException(String msg)
    {
        super(msg);
    }
}
