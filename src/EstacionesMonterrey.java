public class EstacionesMonterrey {

	//Las coordenadas geográficas se pueden obtener en google maps->boton derecho->¿Qué hay aqui?
	static AtributosEstacion Talleres = new AtributosEstacion("Talleres","San Bernabé",null,0,0,false,0,"Amarilla",25.754237, -100.365236);
	static AtributosEstacion SanBernabe = new AtributosEstacion("San Bernabé","Unidad Modelo",null,0,0,false,0,"Amarilla",25.748655, -100.361680);
	static AtributosEstacion UnidadModelo = new AtributosEstacion("San Bernabé",null,null,0,0,false,0,"Amarilla",25.748655, -100.361680);

	static AtributosEstacion[] Paradas = {Talleres,SanBernabe,UnidadModelo};

	/* Ejemplos Práctica Praga
	//-----------------Linea Verde(A)---------------------
	static AtributosEstacion Dejvicka = new AtributosEstacion("Dejvicka","Hradcanska",null,0,0,false,0,"Verde",50.100472,14.393174999999928);
	static AtributosEstacion Hradcanska = new AtributosEstacion("Hradcanska","Malostranska","Dejvicka",0,0,false,0,"Verde",50.097442,14.40316000000007);
	static AtributosEstacion Malostranska = new AtributosEstacion("Malostranska","Staromestska","Hradcanska",0,0,false,0,"Verde",50.091725,14.409213000000022);
	static AtributosEstacion Staromestska= new AtributosEstacion("Staromestska","Mustek","Malostranska",0,0,false,0,"Verde",50.088203,14.417671000000041);
	//-----------------------TRANSBORDO														(arriba)          (abajo)
	static AtributosEstacion Mustek = new AtributosEstacion("Mustek","Muzeum","Staromestska",0,0,true,3,"Namesti_Republiky","Narodni_Triada",0,0,"Verde","Amarilla",50.083531,14.42455700000005);
	//-----------------------TRANSBORDO														(abajo)     (arriba)
	static AtributosEstacion Muzeum = new AtributosEstacion("Muzeum","Namesti_Miru","Mustek",0,0,true,5,"IP_Pavlova","Hlavni_Nadrazi",0,0,"Verde","Roja",50.07946399999999,14.431292999999982);
	static AtributosEstacion Namesti_Miru = new AtributosEstacion("Namesti_Miru","Jiriho_z_Podebrad","Muzeum",0,0,false,0,"Verde",50.075312,14.439781000000039);
	static AtributosEstacion Jiriho_z_Podebrad = new AtributosEstacion("Jiriho_z_Podebrad","Flora","Namesti_Miru",0,0,false,0,"Verde",50.07772199999999,14.450750999999968);
	static AtributosEstacion Flora = new AtributosEstacion("Flora","Zeliveskeho","Jiriho_z_Podebrad",0,0,false,0,"Verde",50.078277,14.462591999999972);
	static AtributosEstacion Zeliveskeho = new AtributosEstacion("Zeliveskeho","Strasnicka","Flora",0,0,false,0,"Verde",50.078283, 14.47547899999995);
	static AtributosEstacion Strasnicka= new AtributosEstacion("Strasnicka","Skalka","Zeliveskeho",0,0,false,0,"Verde",50.073071,14.4900513999999962);
	static AtributosEstacion Skalka = new AtributosEstacion("Skalka",null,"Strasnicka",0,0,false,0,"Verde",50.06843000000001,14.507861999999932);


	static AtributosEstacion Zlicin = new AtributosEstacion("Zlicin","Stodulky",null,0,0,false,0,"Amarilla",50.053239,14.290980999999988);
	static AtributosEstacion Stodulky = new AtributosEstacion("Stodulky","Luka","Zlicin",0,0,false,0,"Amarilla",50.046701,14.30653899999993);
	static AtributosEstacion Luka = new AtributosEstacion("Luka","Luziny","Stodulky",0,0,false,0,"Amarilla",50.045672,14.321340999999961);
	static AtributosEstacion Luziny = new AtributosEstacion("Luziny","Hurka","Luka",0,0,false,0,"Amarilla",50.044497,14.330441999999948);
	static AtributosEstacion Hurka = new AtributosEstacion("Hurka","Nove_Butovice","Luziny",0,0,false,0,"Amarilla",50.050009,14.342792000000031);
	static AtributosEstacion Nove_Butovice = new AtributosEstacion("Nove_Butovice","Jinonice","Hurka",0,0,false,0,"Amarilla",50.050843,14.352149000000054);
	static AtributosEstacion Jinonice = new AtributosEstacion("Jinonice","Radlicka","Nove_Butovice",0,0,false,0,"Amarilla",50.054207,14.369912999999997);
	static AtributosEstacion Radlicka = new AtributosEstacion("Radlicka","Smichovske_nadrazi","Jinonice",0,0,false,0,"Amarilla",50.05812,14.387757999999963);
	static AtributosEstacion Smichovske_nadrazi = new AtributosEstacion("Smichovske_nadrazi","Andel","Radlicka",0,0,false,0,"Amarilla",50.061768,14.408889000000045);
	static AtributosEstacion Andel = new AtributosEstacion("Andel","Karlovo_namesti","Smichovske_nadrazi",0,0,false,0,"Amarilla",50.070011,14.404224999999997);
	static AtributosEstacion Karlovo_namesti = new AtributosEstacion("Karlovo_namesti","Narodni_trida","Andel",0,0,false,0,"Amarilla",50.074591,14.416977999999972);
	static AtributosEstacion Narodni_trida= new AtributosEstacion("Narodni_trida","Mustek","Karlovo_namesti",0,0,false,0,"Amarilla",50.0755381,14.43780049999998);
	//----------------------- deberia ir Mustek TRANSBORDO																	(abajo)          (arriba)

	static AtributosEstacion Namesti_republiky = new AtributosEstacion("Namesti_republiky","Florenc","Mustek",0,0,false,0,"Amarilla",50.088825,14.430598000000032);
	static 	AtributosEstacion Florenc = new AtributosEstacion("Florenc","Krizikova ","Namesti_republiky",0,0,true,3,"Hlavni_nadrazi","Vltavska",0,0,"Amarilla","Roja",50.090484,14.437645999999972);
	static AtributosEstacion  Krizikova= new AtributosEstacion("Krizikova","Invalidovna","Florenc",0,0,false,0,"Amarilla",50.092547,14.45133999999996);
	static AtributosEstacion Invalidovna = new AtributosEstacion("Invalidovna","Palmovka","Krizikova",0,0,false,0,"Amarilla",50.096801,14.463152000000036);
	static AtributosEstacion Palmovka = new AtributosEstacion("Palmovka","Ceskomoravska","Invalidovna",0,0,false,0,"Amarilla",50.103984,14.474794999999972);
	static AtributosEstacion Ceskomoravska = new AtributosEstacion("Ceskomoravska","Vysocanska","Palmovka",0,0,false,0,"Amarilla",50.10616400000001,14.491690000000062);
	static AtributosEstacion Vysocanska = new AtributosEstacion("Vysocanska","Kolbenova","Ceskomoravska",0,0,false,0,"Amarilla",50.110063,14.501081);
	static AtributosEstacion Kolbenova = new AtributosEstacion("Kolbenova","Hloubetin","Vysocanska",0,0,false,0,"Amarilla",50.110395,14.516397999999981);
	static AtributosEstacion Hloubetin  = new AtributosEstacion("Hloubetin","Rajska_zahrada","Kolbenova",0,0,false,0,"Amarilla",50.106688,14.536389999999983);
	static AtributosEstacion Rajska_zahrada = new AtributosEstacion("Rajska_zahrada","Cerny_most ","Hloubetin",0,0,false,0,"Amarilla",50.106848,14.56050300000004);
	static AtributosEstacion Cerny_most = new AtributosEstacion("Cerny_most",null,"Rajska_zahrada",0,0,false,0,"Amarilla",50.108967,14.576832999999965);

	//-----------------Linea Roja(C)---------------------
	static AtributosEstacion Ladvi = new AtributosEstacion("Ladvi","Kobylisy",null,0,0,false,0,"Roja",50.12666,14.469432999999981);
	static AtributosEstacion Kobylisy = new AtributosEstacion("Kobylisy","Nadrazi_Holesovice","Ladvi",0,0,false,0,"Roja",50.12412,14.45425499999999);
	static AtributosEstacion Nadrazi_Holesovice = new AtributosEstacion("Nadrazi_Holesovice","Vltavska","Kobylisy",0,0,false,0,"Roja",50.108915,14.440072999999984);
	static AtributosEstacion Vltavska = new AtributosEstacion("Vltavska","Florenc","Nadrazi_Holesovice",0,0,false,0,"Roja",50.100298,14.438491999999997);
	static AtributosEstacion Hlavni_nadrazi = new AtributosEstacion("Hlavni_nadrazi","Muzeum","Florenc",0,0,false,0,"Roja",50.083505,14.434137999999962);
	static AtributosEstacion IP_Pavlova= new AtributosEstacion("IP_Pavlova","Vysehrad","Muzeum",0,0,false,0,"Roja",50.074333,14.430309999999963);
	static AtributosEstacion Vysehrad = new AtributosEstacion("Vysehrad","Prazskeho_povstani","IP_Pavlova",0,0,false,0,"Roja",50.06314099999999,14.430520000000001);
	static AtributosEstacion Prazskeho_povstani = new AtributosEstacion("Prazskeho_povstani","Pankrac","Vysehrad",0,0,false,0,"Roja",50.056856,14.433312);
	static AtributosEstacion Pankrac = new AtributosEstacion("Pankrac","Budejovicka","Prazskeho_povstani",0,0,false,0,"Roja",50.051028,14.439683000000059);
	static AtributosEstacion Budejovicka = new AtributosEstacion("Budejovicka","Kacerov","Pankrac",0,0,false,0,"Roja",50.044411,14.448787000000038);
	static AtributosEstacion Kacerov = new AtributosEstacion("Kacerov","Roztyly","Budejovicka",0,0,false,0,"Roja",50.041838,14.459308999999962);
	static AtributosEstacion Roztyly = new AtributosEstacion("Roztyly","Chodov","Kacerov",0,0,false,0,"Roja",50.03767000000001,14.476738999999952);
	static AtributosEstacion Chodov = new AtributosEstacion("Chodov","Opatov","Roztyly",0,0,false,0,"Roja",50.031624,14.490880000000061);
	static AtributosEstacion Opatov = new AtributosEstacion("Opatov","Haje","Chodov",0,0,false,0,"Roja",50.027888,14.509190999999987);
	static AtributosEstacion Haje = new AtributosEstacion("Haje",null,"Opatov",0,0,false,0,"Roja",50.030783,14.526973999999996);



	static AtributosEstacion[] Paradas = {Andel,Budejovicka,Cerny_most,Ceskomoravska,Chodov,Dejvicka,Flora,Florenc,Haje,Hlavni_nadrazi,
		Hloubetin,Hradcanska,Hurka,Invalidovna,IP_Pavlova,Jinonice,Jiriho_z_Podebrad,Kacerov,Karlovo_namesti,Kobylisy,Kolbenova,Krizikova,
		Ladvi,Luka,Luziny,Malostranska,Mustek,Muzeum,Nadrazi_Holesovice,Namesti_Miru,Namesti_republiky,Narodni_trida,Nove_Butovice,Opatov,
		Palmovka,Pankrac,Prazskeho_povstani,Radlicka,Rajska_zahrada,Roztyly,Skalka,Smichovske_nadrazi,Staromestska,Stodulky,Strasnicka,Vltavska,
		Vysehrad,Vysocanska,Zeliveskeho,Zlicin};
	
	*/

}
