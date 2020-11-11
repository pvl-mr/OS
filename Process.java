package com.company;

import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Process {
    private TablePage tablePages;
    private int processId;


    public Process(int processId) {
        this.processId = processId;
    }

    public void createTablePages(int numberOfPages) {
       tablePages = new TablePage();
       for (int i = 0; i < numberOfPages; i++) {
           tablePages.addNote(i);
       }
    }

    public void printTablePages() {
        System.out.println(tablePages.toString());
    }

    public int translate(int processId, int pageId) {
        return 0;
    }

    public TablePage getTablePage() {
        return tablePages;
    }


    public int getProcessId() {
        return processId;
    }

    public void work(int j, int pageId) {
        System.out.println("Страница " + j  + " процесса " + processId + " по адресу " + pageId);
    }



}
