package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class MemoryManager {

    private int memorySize;
    private int pageSize;
    private int numberOfPages;
    private ArrayList<Process> processes = new ArrayList<Process>();
    private TablePage phMemory;
    private int allPages = 0;
    private int time = 0;
    private ArrayList<Page> disk;


    public MemoryManager(int memorySize, int pageSize) {
        this.memorySize = memorySize;
        this.pageSize = pageSize;
        numberOfPages = memorySize/pageSize;
        phMemory = new TablePage();
        disk = new ArrayList<Page>();
    }

    public void modify() {
        for (int i = 0; i < phMemory.getSize(); i++) {
            int timeModification = (int)(Math.random()*10)+1;
            phMemory.getNote(i).setIsModified(true);
            phMemory.getNote(i).setTimeModification(timeModification);
        }
        phMemory.sort();
    }

    public void run() {
        int numOfProcess = 4;
        for (int i = 0; i < numOfProcess; i++) {
            int numOfPages = (int)(Math.random()*2)+1;
            Process newProcess = new Process(i);
            processes.add(newProcess);
            newProcess.createTablePages(numOfPages);
            allPages += numOfPages;
        }

        System.out.println("Процессов - " + processes.size());
        for (int i = 0; i<processes.size(); i++) {
            System.out.println(i + "  процесс - " + processes.get(i).getTablePage().getSize());
        }

        modify();

        for (int i = 0; i < processes.size(); i++) {
            for (int j = 0; j < processes.get(i).getTablePage().getSize(); j++) {
                int physicalPageId = translate(i, processes.get(i).getTablePage().getNote(j).getPageId());
                processes.get(i).work(j, physicalPageId);
            }
        }

    }

    public int getNumInPh() {
        int numInPh = -1;
        for (int numP = 0; numP < numberOfPages; numP++) {
            if (phMemory.getNote(numP) == null && phMemory.getSize() <= numberOfPages) {
                numInPh = numP;
                return numInPh;
            }
        }
        return numInPh;
    }

    public int translate(int processId, int pageId) {
        int numInPhMemory = -1;
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).getProcessId() == processId) {
                Page page = processes.get(i).getTablePage().getNote(pageId);
                if (!page.getIfInPhysicalMemory()) {
                    numInPhMemory = getNumInPh();
                    page.setIsInPhysicalMemory(true);
                    phMemory.addNote(page);
                }
                //при отоброжении закончилось место
                if (phMemory.getSize()+1>numberOfPages) {
                    phMemory.sort();
                    Page pageN = phMemory.getNote(0);
                    //если модификация страницы не производилась, страница удаляется из физической памяти
                    if (pageN.getIsModificied() == false) {
                        phMemory.deletePage(pageN);
                    } else { //если модификация страницы производилась, происходит запись выгружаемой страницы на диск
                        disk.add(pageN);
                        pageN.setIsInPhysicalMemory(false);
                        System.out.println("Страница " + pageN.getPageId() + " c временной меткой обращения " + pageN.getTimeModification() + " выгружается");
                        phMemory.deletePage(pageN);
                       // phMemory.replace(pageN.getPageId(), new Page(pageId));
                    }
                }
                //если необходмая страница выгружена  на диск
                for (int j = 0; j < disk.size(); j++) {
                    if (pageId == disk.get(j).getPageId()) {
                        phMemory.addNote(disk.get(j));
                        phMemory.getNoteId(pageId).setIsInPhysicalMemory(true);
                        for (int k = 0; k < disk.size(); k++) {
                            if (disk.get(k).getPageId() == pageId) {
                                disk.remove(k);
                            }
                        }
                    }
                }
            }
        }

        return numInPhMemory;
    }


}
