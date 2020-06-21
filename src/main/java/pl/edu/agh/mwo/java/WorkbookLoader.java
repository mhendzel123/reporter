package pl.edu.agh.mwo.java;

 

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import pl.edu.agh.mwo.java.dataObjects.Pracownik;
import pl.edu.agh.mwo.java.dataObjects.Projekt;

 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 

public class WorkbookLoader {

 

    List<Pracownik> listaPracownikow = new ArrayList<>();
    List<Projekt> listaProjektow = new ArrayList<>();

 

    public boolean sprawdzCzyPracownikIstnieje(Pracownik nowyPracownik) {
        boolean czyIstnieje = false;
        for (Pracownik obecnyPracownik : listaPracownikow) {
            if (obecnyPracownik.getNazwa().equals(nowyPracownik.getNazwa())) {
                czyIstnieje = true;
                break;
            }
        }
        return czyIstnieje;
    }
    
    

 

    public List<Pracownik> listaPracownikowZFolderu(File folder) {
        try {
            for (File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    listaPracownikowZFolderu(fileEntry);
                } else {
                    String nazwaNowegoPracownika = fileEntry.getName().split(".xls")[0];
                    Workbook nowyArkusz = WorkbookFactory.create(fileEntry);
                    Pracownik nowyPracownik = new Pracownik(nazwaNowegoPracownika, nowyArkusz);
                    if (listaPracownikow.size() == 0) {
                        listaPracownikow.add(nowyPracownik);
                    } else if (!sprawdzCzyPracownikIstnieje(nowyPracownik)) {
//                        for (Pracownik obecnyPracownik : listaPracownikow) {
//                            if (obecnyPracownik.getNazwa().equals(nowyPracownik.getNazwa())) {
//                                obecnyPracownik.updatePracownik(nowyArkusz);
//                                break;
//                            }
//                        }
//                    } else {
                        listaPracownikow.add(nowyPracownik);
                    }
                }
            }
            return listaPracownikow;
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

 

    public List<Projekt> listaProjektowZFolderu(File folder) {
        try {
            for (File fileEntry : folder.listFiles()) {
                //System.out.println(fileEntry);
                if (fileEntry.isDirectory()) {
                    listaProjektowZFolderu(fileEntry);
                } else {
                    //String nazwaNowegoPracownika = fileEntry.getName().split(".xls")[0];
                    Workbook nowyArkusz = WorkbookFactory.create(fileEntry);
                    for (Sheet sheet : nowyArkusz) {
                        Projekt newProject = new Projekt(sheet);
                        //System.out.println(newProject.getNazwa());
                    if (listaProjektow.size() == 0) {
                        listaProjektow.add(newProject);
                    } else if (!sprawdzCzyProjektIstnieje(newProject)) {
                    
                        listaProjektow.add(newProject);
                    }
                    }
                   
                }
            }
            return listaProjektow;
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private boolean sprawdzCzyProjektIstnieje(Projekt newProject) {
        boolean czyIstnieje = false;
        for (Projekt obecnyProjekt : listaProjektow) {
            if (obecnyProjekt.getNazwa().equals(newProject.getNazwa())) {
                czyIstnieje = true;
                break;
            }
        }
        return czyIstnieje;
    }

 

    

 

}