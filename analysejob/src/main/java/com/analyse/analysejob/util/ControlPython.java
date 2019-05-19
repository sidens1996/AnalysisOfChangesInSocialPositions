package com.analyse.analysejob.util;

import java.io.File;

public class ControlPython {
    public static void kill(Process pr) {
        pr.destroy();
        System.out.println("process end.");
    }

    public static Process start(String python,String path) {
        Process pr=null;
        try {
            String[] args1=new String[] {python, path};
            pr=Runtime.getRuntime().exec(args1,null,new File("C:/Yan/data/pycharmDocument/python-project/ScrapySocialPosition"));
            System.out.println("process started..");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return pr;
    }
}
