#!/usr/bin/env python
# -*- coding: utf-8 -*-

estacion = ""
last_estacion = ""
last_conexion = ""
linea = input("Linea: ")
while True:
	
	conexiones = ""
	last_conexion = estacion;
	estacion = raw_input('Nombre estacion [{0}]: '.format(last_estacion))
	if not estacion:
		estacion = last_estacion
	elif estacion == "-1":
		break;

	c = raw_input('Conexiones [{0}]: '.format(last_conexion))
	if not c:
		c = last_conexion
	
	while c:
		if conexiones:
			conexiones += ", "
		conexiones = conexiones + "\"" + c + "\" "
		last_estacion = c
		c = raw_input('Conexiones: ')
	
	transbordo = "false" if len(conexiones)==2 else "true"
	
	if estacion and conexiones: 
		with open("estaciones.txt", "a") as myfile:
	    		myfile.write("put(\"{0}\",  new Estacion(\"{0}\",{1},new String[]{{{2}}},false,0,0,200,200));\n".format(estacion, linea, conexiones))
