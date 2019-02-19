import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Soal2_1 {
	
	public Soal2_1()
	{
		int i;
		int N;
		double tester;
		double [] listx;
		double [] listy;
		String [] liststring;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Soal 2-1 : Menyelesaikan Interpolasi dengan Gauss");
		
		System.out.print("Masukkan nilai N (jumlah titik - 1) : ");
		N = input.nextInt ();
		
		listx = new double[N+1];
		listy = new double[N+1];
		liststring = new String [N+1];
		
		for (i = 0; i <= N; i++)
		{
			System.out.print("Masukkan nilai (x"+ i + ",y" + i + ") : ");
			if (i == 0) {
				input.nextLine();
				liststring[i] = input.nextLine();
			}
			else {
				liststring[i] = input.nextLine();
			}
			SplitString (liststring[i], ",", listx, listy,i);
		}
		
		MatrixClass M = new MatrixClass((N+1),(N+1));
		CreateMatrix (M, listx, listy);
		
		System.out.println();
		M.TulisMatriks();
		System.out.println();
		
		System.out.println("Applicating Gauss");
		M.Gauss();
		M.TulisMatriks();
		
		String enter = System.getProperty("line.separator");
		String namaFileOut = "Output.txt";
		
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
	
	public static void SplitString (String str, String delimiter, double[] listx, double[] listy, int i)
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
}
