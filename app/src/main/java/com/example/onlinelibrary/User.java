package com.example.onlinelibrary;

public class User {

    String bookname;
    String authorname;

    public User(String bookname, String authorname) {
        this.bookname = bookname;
        this.authorname = authorname;
    }

    public String getName1() {
        return bookname;
    }

    public String getID1() {
        return authorname;
    }
}

//
//public class User {
//
//    String name1;
//    String ID1;
//
//    public User(String name1, String ID1) {
//        this.name1 = name1;
//        this.ID1 = ID1;
//    }
//
//    public String getName() {
//        return name1;
//    }
//
//    public String getID() {
//        return ID1;
//    }
//}
