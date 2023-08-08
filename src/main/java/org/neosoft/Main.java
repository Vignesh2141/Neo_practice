package org.neosoft;

import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String st="good";
        String reversed=revrse(st, st.length()-1);



    }

    private static String revrse(String st,int length) {
        String st2=new String(st);
        if(length==0){
            return st2;
        }
        else{
          return   st2=(st.charAt(st.charAt(length))+""+revrse(st,length-1));
        }
    }


}