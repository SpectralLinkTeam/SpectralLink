package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.Model;

@Entity
public class Faktura extends Model {
	
	@Column
	public int brojFakture;
	
	@Column
	public Date datumFakture;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean IsDeleted;

	@Column
	public Date datumValute;
	
	@Column
	public double osnovica;
	
	@Column
	public double ukupanPDV;
	
	@Column
	public double iznosZaPlacanje;
	
	@Column(columnDefinition="tinyint default 0")
	public boolean fakturaIzdata;
	
	@ManyToOne
	public BusinessPartner poslovniPartneri;
	
	@ManyToOne
	public BusinessYear poslovnaGodina;
	
	@ManyToOne
	public Company preduzece;

	@OneToMany(mappedBy="faktura", cascade = CascadeType.ALL)
	//@JoinColumn(name = "stavkeFakture_id")
	public List<StavkaFakture> stavkeFakture;

	public Faktura(Date datumFakture, Date datumValute,
			double osnovica, double ukupanPDV, double iznosZaPlacanje,
			boolean fakturaIzdata) {
		super();
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.osnovica = osnovica;
		this.ukupanPDV = ukupanPDV;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.fakturaIzdata = fakturaIzdata;
		
	}

	public Faktura() {
		super();
	}
	
	
	private static  String[] imebr = new String[] { "nula", "jedan", "dva", "tri", "četiri", "pet", "šest", "sedam", "osam", "devet" };
    public static String Slovima(double Value)
    {
        Value = Math.round(Value);
        if(Value == (double)0) return "nula dinara";
        boolean NegativnaVrednost = (Value < 0);
        Value = Math.abs(Value);
        String S = "";
        int celi = (int)Value;
        int dec = (int)(Math.round(Value - celi) * 100);
        String cbroj = String.format("%015d", celi);
        int I = 1;
        System.out.println("cbroj:" + cbroj);
        while(I < 15) {
            String tric = cbroj.substring(I - 1, I-1+3);
            System.out.println("tric:" + tric);
            int trojka = Integer.parseInt(tric);
            if(tric != "000") {
                String sl1 = "";

                int cs = Integer.parseInt(tric.substring(0, 1));
                int cd = Integer.parseInt(tric.substring(1, 2));
                int cj = Integer.parseInt(tric.substring(2, 3));

                if(cs == 2) S += "dve";
                else if(cs > 2) S += imebr[cs];

                if(cs == 1) S += "stotinu";
                else if(cs == 2 || cs == 3 || cs == 4) S += "stotine";
                else if(cs > 4) S += "stotina";

                if(cj == 0) sl1 = ""; else sl1 = imebr[cj];

                if(cd == 4) S += "četr";
                else if(cd == 6) S += "šez";
                else if(cd == 5) S += "pe";
                else if(cd == 9) S += "deve";
                else if(cd == 2 || cd == 3 || cd == 7 || cd == 8) S += imebr[cd];
                else if(cd == 1) {
                    sl1 = "";
                    if(cj == 0) S += "deset";
                    else if(cj == 1) S += "jeda";
                    else if(cj == 4) S += "četr";
                    else S += imebr[cj];
                    if(cj > 0) S += "naest";
                }

                if(cd > 1) S += "deset";

                if((I == 4 || I == 10) && cd != 1) {
                    if(cj == 1) sl1 = "jedna";
                    else if(cj == 2) sl1 = "dve";
                }

                S += sl1;

                if(I == 1) {
                	if(!S.equals("")){
                		S += "bilion";
                        if(cj > 1 || cd == 1) S += "a";
                	}
                } else if(I == 4) {
                	if(!S.equals("")){
	                    S += "milijard";
	                    if((trojka % 100) > 11 && (trojka % 100) < 19) S += "i";
	                    else if(cj == 1) S += "a";
	                    else if(cj > 4 || cj == 0) S += "i";
	                    else if(cj > 1) S += "e";
                	}
                } else if(I == 7) {
                	if(!S.equals("")){
	                    S += "milion";
	                    if(((trojka % 100) > 11 && (trojka % 100) < 19) || cj != 1) S += "a";
                	}
                } else if(I == 10) {
                	if(!S.equals("")){
	                	S += "hiljad";
	                    if(((trojka % 100) > 11 && (trojka % 100) < 19) || cj == 1) S += "a";
	                    else if(trojka == 1) S += "u";
	                    else if(cj > 4 || cj == 0) S += "a";
	                    else if(cj > 1) S += "e";
                	}
                }
            }
            I += 3;
        }
        S += " dinara";
        if(dec > 0) return S += " i " + String.valueOf(dec) + "/100";
        return S;
    }
	
	

}
