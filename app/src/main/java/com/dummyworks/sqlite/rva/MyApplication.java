package com.dummyworks.sqlite.rva;

import android.app.Application;
import android.view.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application
{
    public static List<President> presidentList = new ArrayList<>();

    public static int nextId =10;

    Menu menu;


    public MyApplication()
    {
        filletPresidentlist();
    }


    private void filletPresidentlist()
    {
        President p0 = new President(0,"George Washington",1792,"wwe",1);
        President p1 = new President(1,"John Adams",1796,"wwe",1);
        President p2 = new President(2,"Thomas Jefferson",1804,"wwe",1);
        President p3 = new President(3,"James Madison",1812,"wwe",1);
        President p4 = new President(4,"James Monroe",1817,"wwe",1);
        President p5 = new President(5,"John Quincy Adams",1825,"wwe",1);
        President p6 = new President(6,"Andrew Jackson",1829,"wwe",1);
        President p7 = new President(7,"Martin Van Buren",1837,"wwe",1);
        President p8 = new President(8,"William Henry Harrison",1841,"wwe",1);
        President p9 = new President(9,"John Tyler",1841,"wwe",1);
        President p10=new President(10,"James K. Polk",1845,"wwe",1);

        presidentList.addAll(Arrays.asList(new President[] {p0,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10}));
    }

    public static List<President> getPresidentList() {
        return presidentList;
    }

    public static void setPresidentList(List<President> presidentList) {
        MyApplication.presidentList = presidentList;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int sizeId) {
        MyApplication.nextId = sizeId;
    }
}
