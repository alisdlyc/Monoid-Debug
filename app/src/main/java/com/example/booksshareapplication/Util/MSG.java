package com.example.booksshareapplication.Util;

import java.util.Objects;

public class MSG {

    private int msg;

    public MSG(){
        super();
    }


    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MSG{" +
                "msg=" + msg +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MSG))
            return false;
        MSG msg1 = (MSG) o;
        return getMsg() == msg1.getMsg();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMsg());
    }

    public MSG(int msg) {
        this.msg = msg;
    }

}
