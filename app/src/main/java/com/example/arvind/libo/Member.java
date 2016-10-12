package com.example.arvind.libo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gayathri on 10/11/2016.
 */

public class Member {
    public int real_id;
    public String id;
    public String name;
    public String email;
    public String password;
    public Date expiry_date=new Date();

    public Member()
    {
        super();
//        Calendar c = Calendar.getInstance();
//        df.format(c.getTime());

        expiry_date.setYear(expiry_date.getYear()+1);
    }

}
