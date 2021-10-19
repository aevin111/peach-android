package com.example.peach.errors;

public class ServerError
{
    public static boolean success(int code)
    {
        if (code == 400 || code == 500 || code == 404)
        {
            return false;
        }

        else
        {
            return true;
        }
    }
}
