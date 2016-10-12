package com.example.arvind.libo;

import java.util.Date;

/**
 * Created by Arvind on 12-10-2016.
 */

public class Book {
    public String id;
    public String name;
    public String is_issued;
    public Date issue_date;
    public Date return_date;

    public Book(){
        super();
        is_issued = "0000";
        issue_date = new Date();
        return_date = new Date();
    }

}
