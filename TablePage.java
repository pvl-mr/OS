package com.company;


import java.util.ArrayList;

public class TablePage {
    private ArrayList<Page> pages;
    private int size;

    public TablePage() {
        pages = new ArrayList<Page>();
    }

    public void addNote(int pageId) {
        Page page = new Page(pageId);
        page.setIsInPhysicalMemory(false);
        pages.add(page);
    }

    public Page getNote(int i) {
        if (i>-1 && i < pages.size())
            return pages.get(i);
        else
            return null;
    }
    
    @Override
    public String toString() {
        return (pages.toString());
    }

    public int getSize() {
        return pages.size();
    }

    public void replace(int pageId, Page newPage) {
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getPageId() == pageId) {
                pages.set(i, newPage);
            }
        }
    }

    public TablePage sort() {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < pages.size()-1; i++) {
                System.out.println(pages.get(i).getTimeModification() + " " + pages.get(i+1).getTimeModification());
                int a = pages.get(i).getTimeModification();
                int b = pages.get(i+1).getTimeModification();
                if (a > b) {
                    Page tmp = pages.get(i);
                    pages.set(i,  pages.get(i+1));
                    pages.set(i+1, tmp);
                    isSorted = false;
                }
            }
        }

        return new TablePage();
    }



}
