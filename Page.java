package com.company;

public class Page {

    private int pageId;
    private int bytes = 4;
    private boolean inPhysicalMemory;
    private boolean isModiffied;
    private int timeModification;

    public Page(int pageId) {
        this.pageId = pageId;
        isModiffied = false;
    }

    public void setIsInPhysicalMemory(boolean allocation) {
        inPhysicalMemory = allocation;
    }
    public boolean getIfInPhysicalMemory() {
        return inPhysicalMemory;
    }

    @Override
    public String toString() {
        return "Виртуальная страница "+pageId + ". Признак размещения в физ памяти - " + inPhysicalMemory;
    }

    public int getPageId() {
        return pageId;
    }

    public void setIsModified(boolean isM) {
        isModiffied = isM;
    }

    public boolean getIsModified() {
        return isModiffied;
    }


    public void setTimeModification(int time) {
        timeModification = time;
    }

    public int getTimeModification() {
        return timeModification;
    }

}
