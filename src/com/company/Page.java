package com.company;

public class Page {

    private int pageId;
    private int bytes = 4;
    private boolean inPhysicalMemory;
    private boolean isModified;
    private int timeModification;
    public int numberInPhysicalMemory;

    public Page(int pageId) {
        this.pageId = pageId;
        isModified = false;
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
        isModified = isM;
    }

    public void setTimeModification(int time) {
        timeModification = time;
    }

    public int getTimeModification() {
        return timeModification;
    }

    public boolean getIsModificied() {
        return isModified;
    }

    public void setNumberInPhysicalMemory(int num) {
        numberInPhysicalMemory = num;
    }

}
