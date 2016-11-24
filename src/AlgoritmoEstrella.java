public class AlgoritmoEstrella extends EstacionesMonterrey{

	private static double tiempo=0;
	public static int posSigElemLA = 0;
	public static int posSigElemLC = 0;
	public static int FinRecorrido = 0;
	public static int d =0;
	private static AtributosEstacion[] PilaAb=new AtributosEstacion[50];
	private static AtributosEstacion[] PilaCe=new AtributosEstacion[50];



	public static AtributosEstacion[] AlgoritmoAEstrella(AtributosEstacion salida, AtributosEstacion meta, int modos){

		AtributosEstacion actual;
		boolean metaEnPilaCe=false;

		AtributosEstacion antDesarrollada=salida;

		//modo si es transbordo

		//G = coste de una estacion a otra
		salida.setG(0);

		//H = coste de la distancia entre estaciones
		salida.setH(distancia(salida,meta));

		//inicializamos desde la salida (PilaAb -> Pila Abierta, PilaCe -> Pila Cerrada)
		PilaAb[0]=salida;
		actual=PilaAb[posSigElemLA];
		posSigElemLA++;

		while(!metaEnPilaCe && PilaAb[0] != null){

			if(actual.isTransbordo()){  //si es estacion con transbordo
				desarrollarConDosLinea(actual,antDesarrollada,meta);

			}else{ //si no es estacion con transbordo
				desarrollarConUnaLinea(actual,antDesarrollada,meta);
			}

			//Quitar la parada de la lista abierta.
			PilaCe[posSigElemLC]=actual;
			posSigElemLC++;
			if(PilaCe[posSigElemLC-1].getNombre()==meta.getNombre()){
				metaEnPilaCe=true;
			}
			antDesarrollada=actual;
			quitarPilaAb(actual);
			if(PilaAb[0]!=null)actual=siguienteParada();
		}

		AtributosEstacion[] camino=algoritmoRecorrido(salida,meta);
		AtributosEstacion siguiente = null;
		if(FinRecorrido>1)siguiente= camino[FinRecorrido-2];
		AtributosEstacion[] recorrido = calcularDistanciaTiempo(salida,siguiente,camino);

		return recorrido;
	}

	public static void nuevaBusqueda(){

		for(int i=0;i<posSigElemLA && PilaAb[i]!=null;i++){
			PilaAb[i].setG(0);
			PilaAb[i].setH(0);
			PilaAb[i]=null;
		}
		for(int i=0;i<posSigElemLC && PilaCe[i]!=null;i++){
			PilaCe[i].setG(0);
			PilaCe[i].setH(0);
			PilaCe[i]=null;
		}
		posSigElemLA=0;
		posSigElemLC=0;
		FinRecorrido=0;
		tiempo=0;
		d=0;
	}

	private static AtributosEstacion[] calcularDistanciaTiempo(AtributosEstacion salida,AtributosEstacion siguiente, AtributosEstacion[] pilafinal){

		AtributosEstacion[] recorrido =new AtributosEstacion[FinRecorrido+1];
		boolean t=false;
		int j=0;
		for(int i=FinRecorrido; i>=0;i--){
			recorrido[j]=pilafinal[i];
			j++;
		}
		tiempo=0;
		if(recorrido[0].getLineas()[0]!=recorrido[FinRecorrido].getLineas()[0]){
			t=true;
		}
		if (t==true){
			tiempo = 6;
		}
		//Velocidad= 30Km/h 

		for(int i =0;i<recorrido.length-1;i++){
			d = d + distancia(recorrido[i], recorrido[i+1]);
		}
		tiempo = tiempo +d*6/3000;
		return recorrido;
	}

	public static double getTiempo(){
		return tiempo;
	}

	public static int getDistancia(){
		return d;
	}

	// distancia entre dos puntos
	private static int distancia(AtributosEstacion punto1,AtributosEstacion punto2){
		double distanciaI = (punto1.getI()-punto2.getI())*111145.91;
		double distanciaJ = (punto1.getJ()-punto2.getJ())*78254.86;
		return (int) Math.sqrt(Math.pow(distanciaI, 2)+Math.pow(distanciaJ,2));
	}

	//calcular la g que tiene una parada en el recorrido
	private static int calcularG( AtributosEstacion anterior2,AtributosEstacion anterior,AtributosEstacion parada){
		int penalizacion=0;

		if( anterior.isTransbordo() && !(anterior2.getLineas()[0].equals(parada.getLineas()[0])))
			penalizacion=10000;
		return anterior.getG()+distancia(anterior,parada)+penalizacion;
	}

	//quitar de la lista el nodo pr�ximo a desarrollar
	private static AtributosEstacion siguienteParada(){
		AtributosEstacion res=PilaAb[0];

		int f=PilaAb[0].getG()+PilaAb[0].getH();

		//Bucle para quitar de la lista el elemento.
		for(int i=0; i<PilaAb.length && PilaAb[i]!=null;i++){
			if(f>PilaAb[i].getG()+PilaAb[i].getH()){
				f=PilaAb[i].getG()+PilaAb[i].getH();
				res=PilaAb[i];
			}
		}

		return res;
	}

	private static void quitarPilaAb(AtributosEstacion actual){
		boolean encontrado=false;
		int i=0;
		while(!encontrado && i<PilaAb.length-1){
			if(PilaAb[i]==actual){
				encontrado=true;
			}
			i++;
		}

		for(int j=i-1;j<PilaAb.length-1 && PilaAb[j]!=null;j++){
			PilaAb[j]=PilaAb[j+1];
		}
		if(PilaAb[PilaAb.length-1]!=null){
			PilaAb[PilaAb.length-1]=null;
		}
		if(posSigElemLA>0)posSigElemLA--;
	}

	//hallar el camino recorrido
    private static AtributosEstacion[] algoritmoRecorrido(AtributosEstacion salida, AtributosEstacion meta) {

        AtributosEstacion predecesor = meta;
        AtributosEstacion actual = meta;
        AtributosEstacion siguiente = meta;
        int aux = 1;
        int caso = 1;
        int caso2 = 0;

        AtributosEstacion[] res = new AtributosEstacion[70];
        res[0] = meta;
        while (actual.getNombre() != salida.getNombre()) {

            if (actual.getNombre() == Cuauhtemoc.getNombre()) {
                caso = 2;
                caso2 = 0;

			}
			/*else if(actual.getNombre()==Mustek.getNombre()){
				caso=2;
				caso2=1;
			}else if(actual.getNombre()==Muzeum.getNombre()){
				caso=2;
				caso2=2;
			}
            */
                switch (caso) {

                    case 2://Con dos lineas
                        boolean hayPredecesor2 = false;
                        AtributosEstacion auxiliar2 = null;
                        if (caso2 == 0) {//Florenc
                            for (int i = 0; i < posSigElemLC; i++) {
                                if (Cuauhtemoc.getNombre() == PilaCe[i].getNombre()
                                        && Cuauhtemoc.getNombre() != predecesor.getNombre()) {
                                    if (!hayPredecesor2) {
                                        auxiliar2 = PilaCe[i];
                                        hayPredecesor2 = true;
                                    } else if (PilaCe[i].getG() < auxiliar2.getG()) {
                                        auxiliar2 = PilaCe[i];
                                    }
                                }
                            }
                            res[aux] = auxiliar2;
                            FinRecorrido++;
                            predecesor = actual;
                            actual = auxiliar2;
                            aux++;
                        }/*
				if(caso2==1){//Mustek
					for(int i=0; i<posSigElemLC;i++){
						if(Namesti_republiky.getNombre()==PilaCe[i].getNombre() 
								&& Namesti_republiky.getNombre()!=predecesor.getNombre()){
							if(!hayPredecesor2){
								auxiliar2=PilaCe[i];
								hayPredecesor2=true;
							}else if(PilaCe[i].getG()<auxiliar2.getG()){
								auxiliar2=PilaCe[i];
							}
						}
						if(Narodni_trida.getNombre()==PilaCe[i].getNombre() 
								&& Narodni_trida.getNombre()!=predecesor.getNombre()){
							if(!hayPredecesor2){
								auxiliar2=PilaCe[i];
								hayPredecesor2=true;
							}else if(PilaCe[i].getG()<auxiliar2.getG()){
								auxiliar2=PilaCe[i];
							}
						}
						if(Muzeum.getNombre()==PilaCe[i].getNombre() 
								&& Muzeum.getNombre()!=predecesor.getNombre()){
							if(!hayPredecesor2){
								auxiliar2=PilaCe[i];
								hayPredecesor2=true;
							}else if(PilaCe[i].getG()<auxiliar2.getG()){
								auxiliar2=PilaCe[i];
							}
						}
						if(Staromestska.getNombre()==PilaCe[i].getNombre() 
								&& Staromestska.getNombre()!=predecesor.getNombre()){
							if(!hayPredecesor2){
								auxiliar2=PilaCe[i];
								hayPredecesor2=true;
							}else if(PilaCe[i].getG()<auxiliar2.getG()){
								auxiliar2=PilaCe[i];
							}
						}
					}
					res[aux]=auxiliar2;
					FinRecorrido++;
					predecesor=actual;
					actual=auxiliar2;
					aux++;
				}
				if(caso2==2){//Muzeum
					for(int i=0; i<posSigElemLC;i++){
						if(Namesti_Miru.getNombre()==PilaCe[i].getNombre() 
								&& Namesti_Miru.getNombre()!=predecesor.getNombre()){
							if(!hayPredecesor2){
								auxiliar2=PilaCe[i];
								hayPredecesor2=true;
							}else if(PilaCe[i].getG()<auxiliar2.getG()){
								auxiliar2=PilaCe[i];
							}
						}
						if(Mustek.getNombre()==PilaCe[i].getNombre() 
								&& Mustek.getNombre()!=predecesor.getNombre()){
							if(!hayPredecesor2){
								auxiliar2=PilaCe[i];
								hayPredecesor2=true;
							}else if(PilaCe[i].getG()<auxiliar2.getG()){
								auxiliar2=PilaCe[i];
							}
						}
						if(Hlavni_nadrazi.getNombre()==PilaCe[i].getNombre() 
								&& Hlavni_nadrazi.getNombre()!=predecesor.getNombre()){
							if(!hayPredecesor2){
								auxiliar2=PilaCe[i];
								hayPredecesor2=true;
							}else if(PilaCe[i].getG()<auxiliar2.getG()){
								auxiliar2=PilaCe[i];
							}
						}
						if(IP_Pavlova.getNombre()==PilaCe[i].getNombre() 
								&& IP_Pavlova.getNombre()!=predecesor.getNombre()){
							if(!hayPredecesor2){
								auxiliar2=PilaCe[i];
								hayPredecesor2=true;
							}else if(PilaCe[i].getG()<auxiliar2.getG()){
								auxiliar2=PilaCe[i];
							}
						}
					}
					res[aux]=auxiliar2;
					FinRecorrido++;
					predecesor=actual;
					actual=auxiliar2;
					aux++;
				}
				*/
                        break;
                    case 1://Con una sola linea
                        for (int i = 0; i < posSigElemLC; i++) {
                            if (actual.getSigEstacion()[0] == PilaCe[i].getNombre()
                                    && actual.getSigEstacion()[0] != predecesor.getNombre()) {
                                res[aux] = PilaCe[i];
                                siguiente = PilaCe[i];
                                aux++;

                            }

                            if (actual.getAntEstacion()[0] == PilaCe[i].getNombre()
                                    && actual.getAntEstacion()[0] != predecesor.getNombre()) {
                                res[aux] = PilaCe[i];
                                siguiente = PilaCe[i];
                                aux++;

                            }

                        }
                        FinRecorrido++;

                        predecesor = actual;
                        actual = siguiente;
                }

                caso = 1;
            }
            return res;
        }


	private static void desarrollarConUnaLinea(AtributosEstacion actual, AtributosEstacion antDesarrollada, AtributosEstacion meta){

		boolean encontrado=false;
		int j=0;

		//busca en la lista de paradas la siguiente estacion 
		while(!encontrado && actual.getSigEstacion()[0]!=null && j<Paradas.length){

			if(actual.getSigEstacion()[0]==Paradas[j].getNombre() && !enPilaCe(Paradas[j])
					&& !enPilaAb(Paradas[j])){
				//una vez encontrada actualiza el coste de G, de la estacion actual a la siguiente encontrada
				Paradas[j].setG(calcularG(antDesarrollada,actual,Paradas[j]));
				//actualiza el coste de H (distancia entre estacion encontrada y estacion meta)
				Paradas[j].setH(distancia(Paradas[j],meta));
				// si la estacion encontrada no esta dentro de lista cerrada ni abierta 
				PilaAb[posSigElemLA]=Paradas[j];
				posSigElemLA++;
				encontrado=true;
			}
			j++;

		}
		encontrado=false;
		j=0;
		//hace lo mismo que el otro metodo pero con la anterior estacion
		while(!encontrado && actual.getAntEstacion()[0]!=null && j<Paradas.length){
			if(actual.getAntEstacion()[0]==Paradas[j].getNombre() && !enPilaCe(Paradas[j]) && !enPilaAb(Paradas[j])){
				Paradas[j].setG(calcularG(antDesarrollada,actual,Paradas[j]));
				Paradas[j].setH(distancia(Paradas[j],meta));
				PilaAb[posSigElemLA]=Paradas[j];
				posSigElemLA++;
				encontrado=true;
			}
			j++;
		}


	}


	private static void desarrollarConDosLinea(AtributosEstacion actual, AtributosEstacion antDesarrollada, AtributosEstacion meta){
		int caso=0;
        /*
		if(actual.getNombre()==Florenc.getNombre()) caso=1;
		else if(actual.getNombre()==Mustek.getNombre()) caso=2;
		else if(actual.getNombre()==Muzeum.getNombre()) caso=3;

		switch(caso){
		case 1://Florenc
			if(!enPilaCe(Vltavska) && !enPilaAb(Vltavska)){
				Vltavska.setG(calcularG(antDesarrollada,Florenc,Vltavska));
				Vltavska.setH(distancia(Vltavska,meta));
				PilaAb[posSigElemLA]=Vltavska;
				posSigElemLA++;
			}
			if(!enPilaCe(Hlavni_nadrazi) && !enPilaAb(Hlavni_nadrazi)){
				Hlavni_nadrazi.setG(calcularG(antDesarrollada,Florenc,Hlavni_nadrazi));
				Hlavni_nadrazi.setH(distancia(Hlavni_nadrazi,meta));
				PilaAb[posSigElemLA]=Hlavni_nadrazi;
				posSigElemLA++;
			}
			if(!enPilaCe(Krizikova) && !enPilaAb(Krizikova)){
				Krizikova.setG(calcularG(antDesarrollada,Florenc,Krizikova));
				Krizikova.setH(distancia(Krizikova,meta));
				PilaAb[posSigElemLA]=Krizikova;
				posSigElemLA++;
			}
			if(!enPilaCe(Namesti_republiky) && !enPilaAb(Namesti_republiky)){
				Namesti_republiky.setG(calcularG(antDesarrollada,Florenc,Namesti_republiky));
				Namesti_republiky.setH(distancia(Namesti_republiky,meta));
				PilaAb[posSigElemLA]=Namesti_republiky;
				posSigElemLA++;
			}
			break;
		case 2://Mustek
			if(!enPilaCe(Namesti_republiky) && !enPilaAb(Namesti_republiky)){
				Namesti_republiky.setG(calcularG(antDesarrollada,Mustek,Namesti_republiky));
				Namesti_republiky.setH(distancia(Namesti_republiky,meta));
				PilaAb[posSigElemLA]=Namesti_republiky;
				posSigElemLA++;
			}
			if(!enPilaCe(Narodni_trida) && !enPilaAb(Narodni_trida)){
				Narodni_trida.setG(calcularG(antDesarrollada,Mustek,Narodni_trida));
				Narodni_trida.setH(distancia(Narodni_trida,meta));
				PilaAb[posSigElemLA]=Narodni_trida;
				posSigElemLA++;
			}
			if(!enPilaCe(Muzeum) && !enPilaAb(Muzeum)){
				Muzeum.setG(calcularG(antDesarrollada,Mustek,Muzeum));
				Muzeum.setH(distancia(Muzeum,meta));
				PilaAb[posSigElemLA]=Muzeum;
				posSigElemLA++;
			}
			if(!enPilaCe(Staromestska) && !enPilaAb(Staromestska)){
				Staromestska.setG(calcularG(antDesarrollada,Mustek,Staromestska));
				Staromestska.setH(distancia(Staromestska,meta));
				PilaAb[posSigElemLA]=Staromestska;
				posSigElemLA++;
			}
			break;
		case 3://Muzeum
			if(!enPilaCe(Namesti_Miru) && !enPilaAb(Namesti_Miru)){
				Namesti_Miru.setG(calcularG(antDesarrollada,Muzeum,Namesti_Miru));
				Namesti_Miru.setH(distancia(Namesti_Miru,meta));
				PilaAb[posSigElemLA]=Namesti_Miru;
				posSigElemLA++;
			}
			if(!enPilaCe(Mustek) && !enPilaAb(Mustek)){
				Mustek.setG(calcularG(antDesarrollada,Muzeum,Mustek));
				Mustek.setH(distancia(Mustek,meta));
				PilaAb[posSigElemLA]=Mustek;
				posSigElemLA++;
			}
			if(!enPilaCe(Hlavni_nadrazi) && !enPilaAb(Hlavni_nadrazi)){
				Hlavni_nadrazi.setG(calcularG(antDesarrollada,Muzeum,Hlavni_nadrazi));
				Hlavni_nadrazi.setH(distancia(Hlavni_nadrazi,meta));
				PilaAb[posSigElemLA]=Hlavni_nadrazi;
				posSigElemLA++;
			}
			if(!enPilaCe(IP_Pavlova) && !enPilaAb(IP_Pavlova)){
				IP_Pavlova.setG(calcularG(antDesarrollada,Muzeum,IP_Pavlova));
				IP_Pavlova.setH(distancia(IP_Pavlova,meta));
				PilaAb[posSigElemLA]=IP_Pavlova;
				posSigElemLA++;
			}

		}
    */
	}

	//Pila Abierta
	private static boolean enPilaAb(AtributosEstacion parada){
		boolean res = false;
		for(int i=0; i<posSigElemLA;i++){
			if(parada.getNombre()==PilaAb[i].getNombre()){
				res=true;
			}
		}
		return res;
	}

	//Pila Cerrada
	private static boolean enPilaCe(AtributosEstacion parada){
		boolean res = false;
		for(int i=0; i<posSigElemLC;i++){
			if(parada.getNombre().equals(PilaCe[i].getNombre())){
				res=true;
			}
		}
		return res;
	}

}
