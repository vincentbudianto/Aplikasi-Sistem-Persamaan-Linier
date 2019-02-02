package CodeTubes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Soal2_3 {
	
	double [][] Mat = new double [10][10];
	String[] arraytitik = new String [10];
	int countpoint;
	
	public Soal2_3()
	{
		int i;
		double tester;
		double [] listx;
		double [] listy;
		String [] liststring;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Soal 2-3 : Menyelesaikan Interpolasi dengan Gauss");
		
		
		String enter = System.getProperty("line.separator");
    	
		String namaFile = "Input2_3.txt";
		String namaFileOut = "Output.txt";
	       
	    bacaFile(namaFile);
		listx = new double[countpoint];
		listy = new double[countpoint];
		liststring = new String [countpoint];
		
		for (i = 0; i <countpoint; i++)
		{
			liststring[i] = arraytitik[i];
			SplitString (liststring[i], " ", listx, listy,i);
		}
		
		MatrixClass M = new MatrixClass((countpoint),(countpoint));
		CreateMatrix (M, listx, listy);
		System.out.println();
		M.TulisMatriks();
		
		System.out.println();
		System.out.println("Applicating Gauss");
		M.Gauss();
		M.TulisMatriks();
		
		if (M.IsSolusiAda())
		{
			M.PrintConsoleInterpolasi();
			System.out.println("Ada 4 kali percobaan taksiran nilai y dari x yang diberikan");
			tulisFile("Ada 4 kali percobaan taksiran nilai y dari x yang diberikan", namaFileOut); 
			tulisFile(enter, namaFileOut); 
			for (int a = 1; a <= 4; a++)
			{
				System.out.println("Tuliskan nilai x pada sebuah fungsi ini yang ingin Anda ketahui");
				tester = input.nextDouble();
				tulisFile("Tuliskan nilai x pada sebuah fungsi ini yang ingin Anda ketahui : " + tester, namaFileOut); 
				tulisFile(enter, namaFileOut); 
				System.out.println("Hasilnya adalah : " + M.NilaiFungsi(tester));
				tulisFile("Hasilnya adalah : " + M.NilaiFungsi(tester), namaFileOut); 
				tulisFile(enter, namaFileOut); 
			}
		}
		else
		{
			System.out.println("Solusi tidak ada");
			tulisFile("Solusi tidak ada", namaFileOut); 
		}
		tulisFile(enter, namaFileOut); 
	}
	
	void CreateMatrix (MatrixClass M, double[] listx, double[] listy)
	{
		int i,j;
		
		for (i = 0; i < M.Brs; i++)
		{
			for (j = 0; j < (M.Kol - 1); j++)
			{
				M.Mat [i][j] = Math.pow(listx[i],j);
			}
		}
		
		for (i = 0; i < M.Brs; i++)
		{
			M.Mat[i][M.Kol-1] = listy[i];
		}
	}
	
	void SplitString (String str, String delimiter, double[] listx, double[] listy, int i)
	{
		System.out.println ("String = "+str);
		String[] temp;
		temp = str.split(delimiter);
		listx[i] = Double.parseDouble (temp[0]);
		listy[i] = Double.parseDouble (temp[1]);
		System.out.println(listx[i]);
		System.out.println(listy[i]);
	}

	
	// Method tulis file
    void tulisFile(String teks, String namaFile) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter(namaFile, true)));
            out.print(teks);
            out.close(); 
        } catch (IOException e) {
            System.out.print("Gagal menulis ke file " + namaFile);
            e.printStackTrace();
        }
    }
 
    // Method baca file
    void bacaFile(String namaFile) {
        BufferedReader br = null;
        
        try {
            String sCurrentLine;
            int i = 0;
            br = new BufferedReader(new FileReader(namaFile));
            while ((sCurrentLine = br.readLine()) != null) {
                arraytitik[i] = sCurrentLine;
                i = i+1;
            }
            countpoint = i;
        } catch (IOException e) {
            System.out.println("Gagal membaca file " + namaFile);
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }   
}