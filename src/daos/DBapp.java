package daos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class DBapp {
    static Connection minConnection;
    static Statement stmt;
    static BufferedReader inLine;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            inLine = new BufferedReader(new InputStreamReader(System.in));
            //generel opsaetning
            //via native driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=projekt;user=sa;password=reallyStrongPwd123;");
            stmt = minConnection.createStatement();
            //Indloesning og kald af den rigtige metode
            System.out.println("Indtast  ");
            System.out.println("1 dfor at oprette et produkt  ");
            System.out.println("2 fop at se salg  ");
            String in=inLine.readLine();
            switch (in)
            {case "1"  : {laveProdukt();break;}
                case "2" : {salgPrProdPrDato();break;}
                default : System.out.println("ukendt indtastning");
            }
        }
        catch (Exception e) {
            System.out.println("fejl:  "+e.getMessage());
        }
    }

    
    public static void laveProdukt() {
    	printProduktKategori();
    	
    	try {
    		System.out.println("Intaste ID på produktkategorien");
    		
    		String pkidstring=inLine.readLine();
    		printProdukt(pkidstring);
    		System.out.println("Intast _unikt_ id paa oensker produkt");
    		String pidstring=inLine.readLine();
    		System.out.println("Intast navn paa oensker produkt");
    		String pnavnstring=inLine.readLine();
    		System.out.println("insert into Produkt values (" + pidstring + ", '" + pnavnstring + "', " + pkidstring + ")");
    		String sql ="insert into Produkt values (" + pidstring + ", '" + pnavnstring + "', " + pkidstring + ")";
    		stmt.executeQuery(sql);
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    
    public static void printProduktKategori() {
    	try {
    		String sql = "SELECT * FROM ProduktKategori";
    		ResultSet res=stmt.executeQuery(sql);
            while (res.next()) {
                String s;
                s = res.getString(1);
                System.out.println(s + " :" + res.getString(2) + "");
            }
    	}
    	catch (Exception e){
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void printProdukt(String pkstring) {
    	try {
    		System.out.println("SQL : SELECT * FROM Produkt WHERE IDProduktKategori = pkstring");
    		String sql = "SELECT * FROM Produkt WHERE IDProduktKategori =" + pkstring;
    		ResultSet res=stmt.executeQuery(sql);
            while (res.next()) {
                String s;
                s = res.getString(1);
                System.out.println(s + " :" + res.getString(2) + "");
            }
    	}
    	catch (Exception e){
    		System.out.println(e.getMessage());
    	}
    	
    }
    
    public static void salgPrProdPrDato() {
    	printProdukt();
    	try {
    		System.out.println("Vaelg id paa oensket produkt");
    		String pidstring=inLine.readLine();
    		System.out.println("Indtask dato");
    		String datostring = inLine.readLine();
    		getSalgStatist(pidstring, datostring);
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    }
    
    
    public static void printProdukt() {
    	try {
    		String sql = "SELECT * FROM Produkt";
    		ResultSet res=stmt.executeQuery(sql);
            while (res.next()) {
                String s;
                s = res.getString(1);
                System.out.println(s + " :" + res.getString(2) + "");
            }
    	}
    	catch (Exception e){
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void getSalgStatist(String produktid, String dato) {
    	try {
    		String sql = "SELECT SUM(\n" + 
    				"    		ISNULL(sl.aftaltpris, (((sp.pris - ISNULL(sp.rabat, 0)) * sl.antal))\n" + 
    				"    		    )) / count(DISTINCT sl.IDSalg) \n" + 
    				"    		    FROM Salg s\n" + 
    				"    		INNER JOIN SalgsLinie sl ON sl.IDSalg = s.id\n" + 
    				"    		INNER JOIN stedPris sp ON sp.id=sl.IDStedPris\n" + 
    				"    		WHERE sp.IDProdukt = " +produktid + " AND s.dato = '" + dato + "'";
    		ResultSet res = stmt.executeQuery(sql);
    		res.next();
    		System.out.println(res.getString(1));

    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
   


}
