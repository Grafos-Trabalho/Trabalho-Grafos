package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {
    private BufferedReader buffRead;
    private BufferedWriter buffWrite;
    private String type;

    public Arquivo(String path, String file, String type) throws IOException {
        File pasta = new File(path);
        if(!pasta.exists()) {
            pasta.mkdirs();
        }

        try {
            switch (type) {
                case "save":
                    this.buffWrite = new BufferedWriter(new FileWriter(path + file + ".txt"));
                    break;
            
                case "read":
                    this.buffRead = new BufferedReader(new FileReader(path + file + ".txt"));
                    break;
                
                default:
                    throw new Exception("Type incorrect");
            }
    
            this.type = type;
        } catch (Exception e) {
            System.err.println("File not found");
        }
    }

    public boolean ready() throws IOException {
        return this.buffRead.ready();
    }

    public int numLines() throws IOException {
        int numLines = 0;
        
        while(buffRead.ready())
            numLines++;

        return numLines;
    }

    public String readLine() throws IOException {
        return this.buffRead.readLine() + "\n";
	}

    public void write(String string) throws IOException {
		this.append(string);
	}

    public void write(int inteiro) throws IOException {
		this.append(Integer.toString(inteiro));
	}

    public void close() throws IOException {
        switch (this.type) {
            case "save":
                this.buffWrite.close();
                break;
        
            case "read":
                this.buffRead.close();
                break;
        }
    }

    private void append(String string) throws IOException {
        this.buffWrite.append(string);
    }
}
