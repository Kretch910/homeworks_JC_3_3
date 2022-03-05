package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void openZip(String name, String dir) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(name))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                FileOutputStream fout = new FileOutputStream(dir + entry.getName());
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String name) {
        GameProgress progress = null;
        try (FileInputStream fis = new FileInputStream(name);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            progress = (GameProgress) ois.readObject();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return progress;
    }

    public static void main(String[] args) {
        openZip("H://Games/savegames/save.zip", "H://Games/savegames/");

        System.out.println(openProgress("H://Games/savegames/progress2.sav"));
    }
}
