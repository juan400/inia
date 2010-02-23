	# -*- coding: utf-8 -*-
# ----------------------
# Diccionario de input
#-----------------------

input = {
			'Key_de_corrida' :
			{
				'FSirmbra': (2009,6,1 ), #anio,mes,dia
				'EstacionClimatica': 'LE',
				'Cultivar':'DonAlberto'
				'FertilizacionSiembra':[ 
					{
						'fecha': (2009,6,1),
						'Fuente': '18-46-0', 
						'rate': 100
					},
				'Refertilizacion1': [ 
					{
						'fecha': (2009,7,1),
						'Fuente': '18-46-0', 
						'rate': 100
					},
				'Refertilizacion2': [ 
					{
						'fecha': (2009,8,1),
						'Fuente': '18-46-0', 
						'rate': 100
					},
				'NombreSuelo': '10.1', #nombre coneat
				'ProfundidadA':0.2
				'ProfundidadB':0.3
				'DensidadPlantas': 200     # plantas m-2
				'WULI':50 #  -Initial value, water content in the upper soil layer  mm
				'WLLI':50 #  -Initial value, water content in the lower soil layer  mm
				'DPMI':200 #  -Initial value, decomposable plant material gCm-2
				'RPMI':100 #  -Initial value, resistant plant material (difficult to decompose)   gCm-2
				'HUMI':2.0 #  -Initial value, humidified organic matter in the soil gCm-2
				'NAULI':10 # -Initial value, amonium N in the upper soil layer gNm-2
				'NALLI':10# -Initial value, amonium N in the lower soil layer gNm-2
				'NNULI':20# -Initial value, nitrate N in the upper soil layer   gNm-2
				'NNLLI':20 # -Initial value, nitrate N in the lower soil layer  gNm-2    
			},

