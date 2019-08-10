/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tax_calculator;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rasheed
 */
public class Reader {
    
    public String path;
    private Scanner scanner;
    public ArrayList<String> lines;

    public Reader() {
        lines = new ArrayList<>();
    }

    public void read() {
        String line;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            lines.add(line);
            // process the line
        }
    }
    
    public void read(String p) {
        String line;
        try {
            scanner = new Scanner(new File(p));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            lines.add(line);
            // process the line
        }
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public void printArrayList(){
        for(int i = 0; i < lines.size(); i++){
            System.out.println(lines.get(i));
        }
    }
    
    public void drawArray(int x, int y, Graphics g){
        g.drawString("Directions", x, y);
        for(int i = 0; i < lines.size(); i++){
            //System.out.println(commands.get(i));
            g.drawString(lines.get(i), x, y+((i+1)*20));
        }
        
    }
    
}
