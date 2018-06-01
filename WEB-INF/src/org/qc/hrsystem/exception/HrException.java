package org.qc.hrsystem.exception;
import java.lang.RuntimeException;
public class HrException extends RuntimeException
{
    public HrException(){}
    public HrException(String msg)
    {
        super(msg);
    }
}
