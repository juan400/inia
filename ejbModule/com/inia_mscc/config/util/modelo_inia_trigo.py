
#***********************************************************************
#*                               GECROS                                *
#*     Genotype-by-Environment interaction on Crop growth Simulator    *
#*          (as linked with a simple soil simulation model)            *
#*                                                                     *
#*                          (FST-version 1.0)                          *
#*                         Author: Xinyou YIN                          *
#*                      Crop and Weed Ecology group                    *
#*                Wageningen University & Research Centre              *
#*               PO Box 430, 6700 AK Wageningen, Netherlands           * 
#***********************************************************************
#*               Pythonized by agb32@cornell.edu 2008                  *
#*               1126 Bradfield Hall, Cornell University               *
#*                           Ithaca NY 14850                           *
#***********************************************************************
import math,sys
import gecros_for
import numpy

class gecros():
    """
    PLANT
    =====
    CLVI  -Initial value, amount of carbon in the living leaves               gCm-2
    CRTI  -Initial value, amount of carbon in living roots                    gCm-2
    NRTI  -Initial value, nitrogen in living roots (Ng)                       gNm-2
    NLVI  -Initial value, nitrogen in living leaves(Nlv)                      gNm-2
    SLNBI -SLN in bottom leaves of the canopy (nbot)                          gNm-2 
    LAII  -Initial value, of green LAI                                        m2m-2
    HTI   -Initial value, of plant height                                     m
    RDI   -Initial value, of rooting depth to the soil                        cm
    
    SOIL
    ====
    TSOILI-Initial value, of soil temperature                                 C
    WULI  -Initial value, water content in the upper soil layer               mm
    WLLI  -Initial value, water content in the lower soil layer               mm
    DPMI  -Initial value, decomposable plant material                         gCm-2
    RPMI  -Initial value, resistant plant material (difficult to decompose)   gCm-2
    BIOI  -Initial value, microbial biomass in the soil                       gCm-2
    HUMI  -Initial value, humidified organic matter in the soil               gCm-2
    DPNI  -Initial value, organic N in decomposable plant material            gNm-2
    RPNI  -Initial value, organic N in resistant plant material               gNm-2
    NAULI -Initial value, amonium N in the upper soil layer                   gNm-2
    NALLI -Initial value, amonium N in the lower soil layer                   gNm-2
    NNULI -Initial value, nitrate N in the upper soil layer                   gNm-2
    NNLLI -Initial value, nitrate N in the lower soil layer                   gNm-2                
    """

    def __init__(self):

        pass
    
    def run(self):
        def afgen(F,X):
            for i in range(len(F)-1):
                if F[i+1][0]>X:break
            if F[i][0]>X:return X
            return F[i][1]+((X-F[i][0])*(F[i+1][1]-F[i][1]))/(F[i+1][0]-F[i][0])

        """#********* SELF-DEFINED WATER AND NITROGEN SUPPLY TO THE CROP **********
        #* User-defined daily water and (NH4+ and NO3-)nitrogen availability
        #* WSWI or NSWI = 1. for using simulated soil water or nitrogen supply;
        #* WSWI or NSWI =-1. for using user self-defined soil water supply (i.e.
        #* WINPUT) or nitrogen supply (NINPA & NINPN). In this case, crop model
        #* is de-coupled from the example soil model; in other words, simulated
        #* soil water and N availabilities are no longer affecting crop growth."""
    
        #PARAM  
        WSWI = -1.; NSWI = -1.
        WINPUT = 15. #User-defined water supply to crop     mm d- 1
        NINPA  = 0. #User-defined amonium-N supply to crop g N m-2 d- 1
        NINPN = 0.65 #User-defined nitrate-N supply to crop gNm-2 d- 1
    
    
    
        """#***************************** MODEL INPUTS ****************************
        #*** Crop parameters for pea (Pisum sativum L.)
        #PARAM 
        
        #* LEGUME = 1. for leguminous crops;   = -1. for non-leguminous crops.
        #* C3C4   = 1. for C3 crops;           = -1. for C4 crops.
        #* DETER  = 1. for determinate crops;  = -1. for indeterminate crops.
        #* SLP    = 1. for short-day crops;    = -1. for long-day crops.
        #* LODGE  = 1. for cases with lodging; = -1. for cases without lodging."""
        LEGUME = 1.; C3C4 = 1.; DETER = -1.; SLP = -1.; LODGE = 1.
    
        #PARAM 
        EG=0.35       #Efficiency of germination (epsilon g)                                                gg-1
        CFV=0.473     #Carbon fraction in the vegetative organs  (Fcv)                                      gCg-1
        YGV=0.80      #Growth efficiency for vegetative organs (i.e leaves, stem roots) (Ygv)               gCg-1C
        FFAT=0.02     #Fraction of fat in the storage organs (Flip)                                         gfat g-1
        FLIG=0.06     #Fraction of lignin in the storage organs (Flig)                                      glig g-1
        FOAC=0.04     #Fraction of organic acids in the storage organs (Foac)                               goac g-1
        FMIN=0.03     #Fraction of minerals in the storage organs (Fmin)                                    gmin g-1
        LNCI=0.0055   #0.055 #Initial value of N concentration in living leaves (ncri0)                            gNg-1
        TBD=0.        #Base temperature for phenology (Tb)                                                  C
        TOD=27.6      #Optimum temperature for phenology (To)                                               C
        TCD=36.       #Ceiling temperature for phenology (Tc)                                               C
        TSEN=0.409    #Curvature for temperature response (ct)                                              --
        SPSP=0.2      #Development stage (DS) for start of fotoperiod sensitive phase (v1)                  --
        EPSP=0.7      #Development stage (DS) for end of photoperiod sensitive phase  (v2)                  --
        INSP=-2.      #Inclination of sun angle for computing daylenght for photoperiodism(alpha)           degrees
        LWIDTH=0.025  #Leaf width (w)                                                                       m
        RDMX=100.     #Maximum rooting depth (Dmax)                                                         cm
        CDMHT=345.    #Stem dry weight per unit of plant height (rho)                                       gm-2m-1
        PMEH=0.8      #Fraction of sigmoid curve inflection in entire plant height growth period            --
        ESDI=1.35     #Development stage for end of seed number determining period for indeterminate crops  --
        PMES=0.5      #Fraction of sigmoid curve inflection in entire seed growth period                    --
        CCFIX=6.      #Carbon cost of symbiotic nitrogen fixation                                           g C g-1 N

        NUPTX=0.65    #Maximum crop nitrogen uptake                                                         gN m-2 d-1

        SLA0=0.0333   #Specific leaf area constant                                                         m2 leaf g-1
        SLNMIN=0.5   #Minimum or base SLN for photosynthesis                                           gNm-2
        RNCMIN=0.005  #Minimum N concentration in the roots                                                gN g-1
        STEMNC=0.015  #Nitrogen concentration in the stem                                                  gNg-1
        WRB=0.25      #Critical root weight density                                                        g m-2 cm-1 depth
        EAJMAX=48041.88 #Energy of activation for JMAX                                                     J mol-1
        XVN=62.       #Slope of linear relationship between VCMX and leaf nitrogen                         umol CO 2 s-1 g-1 N
        XJN=124.      #Slope of linear relationship between JMAX and leaf                                  umol e- S-1 g-1 N
        THETA=0.7     #Convexity for light response of electron transport (J2) in photosynthesis           --

        
        #*** Genotype-specific parameters for cv. Solara
        #PARAM
        SEEDW=0.21480; SEEDNC=0.04625
        BLD=50.       #Leaf angle (from horizontal)                                                        degree
        HTMX=0.7054
        MTDV=34.7627  #Minimum thermal days for vegetative growth phase                                    d
        MTDR=23.0889  #Minimum thermal days for reproductive (seed fill)phase                              d
        PSEN=-0.0     #Photoperiod sensitivity of phenological development                                 h-1
    
        #*** Soil parameters
        #PARAM
        PNLS=1.
        CLAY=23.4; WCMIN=0.05; WCFC=0.25; WCMAX=0.35
        DRPM=1.44; DPMR0=10.; RPMR0=0.3; BIOR=0.66; HUMR=0.02
        TOC=7193.; BHC=3500.; FBIOC=0.03; RN=1.; RA=1.
        RSS=100.; SD1=5.; TCT=4.; TCP=1.; MULTF=1.
    
        #*** Sensitivity-analysis options
        #PARAM 
        CO2A=350.; COEFR=1.; COEFV=1.; COEFT=5.
        FCRSH=0.5; FNRSH=0.63
        PNPRE=0.7; CB=0.75; CX=1.; TM=1.5
    
        #*** Management actions
        #TIMER 
        STTIME=110#start time
        #PARAM 
        NPL   = 60.     #Plant density     plants m-2
        IR1T  =  5.; IR1A = 0.
        IR2T  = 15.; IR2A = 0.
        IR3T  = 25.; IR3A = 0.
        IR4T  = 35.; IR4A = 0.
        IR5T  = 45.; IR5A = 0.
        IR6T  = 55.; IR6A = 0.
        IR7T  = 65.; IR7A = 0.
        IR8T  = 75.; IR8A = 0.
        IR9T  = 85.; IR9A = 0.
        IR10T = 95.; IR10A= 0.
    
        FNA1T =  5.; FNA1 = 0.
        FNA2T = 15.; FNA2 = 0.
        FNA3T = 25.; FNA3 = 0.
        FNA4T = 35.; FNA4 = 0.
        FNA5T = 45.; FNA5 = 0.
        FNA6T = 55.; FNA6 = 0.
        FNA7T = 65.; FNA7 = 0.
        FNA8T = 75.; FNA8 = 0.
    
        FNN1T =  5.; FNN1 = 0.
        FNN2T = 15.; FNN2 = 0.
        FNN3T = 25.; FNN3 = 0.
        FNN4T = 35.; FNN4 = 0.
        FNN5T = 45.; FNN5 = 0.
        FNN6T = 55.; FNN6 = 0.
        FNN7T = 65.; FNN7 = 0.
        FNN8T = 75.; FNN8 = 0.
        
        #************************* RUN CONTROL *********************************
    
        #FINISH DS > 2. #if reached physiological stage DS>2 then finsh
        #TIMER  
        #PESOIL     #Potential soil evaporation  mmd-1
        
        FINTIM=187#time to finish run
        DELT=1.#time step in days
        PRDEL=1.#time step for output in days  

        ## create time series variables
        self.d={}
        
        #************************ INITIAL CONDITIONS ***************************
        #<INITIAL>
        ZERO   = 0.
    
        #*** Initial conditions for crop model
    
        FPRO  = 6.25*SEEDNC
        FCAR  = 1.-FPRO-FFAT-FLIG-FOAC-FMIN
        CFO   = 0.444*FCAR+0.531*FPRO+0.774*FFAT+0.667*FLIG+0.368*FOAC
        YGO   = CFO/(1.275*FCAR+1.887*FPRO+3.189*FFAT+2.231*FLIG+ \
                0.954*FOAC)*30./12.
    
        CLVI   = NPL * SEEDW * CFO * EG * FCRSH
        CRTI   = NPL * SEEDW * CFO * EG * (1.-FCRSH)
        
        NLVI   = LNCI* CLVI/CFV
        NRTI   = NPL * SEEDW * EG * LNCI * FCRSH/FNRSH - NLVI
    
        LNCMIN = SLA0*SLNMIN
        LAII   = CLVI/CFV*SLA0
        SLNBI  = NLVI/LAII
    
        RDI    = max(2., SD1)
        HTI    = HTMX/1000.
    
        #*** Initial conditions for the example soil model
    
        TSOILI = 15.
        WCI    = WCFC * MULTF
        WULI   = 10.*(WCI-WCMIN)*RDI
        WLLI   = 10.*(WCI-WCMIN)*(150.-RDI)
    
        DPMI   = ZERO
        RPMI   = TOC - BHC - DPMI
        BIOI   = FBIOC * TOC
        HUMI   = BHC - BIOI
    
        DPNI   = 1./ 40.*DPMI
        RPNI   = 1./100.*RPMI
        
        NAI    = 2.
        NNI    = 2.
        NAULI  = (1.-math.exp(-0.065*RDI))*NAI +     RDI/150. *RA
        NALLI  =     math.exp(-0.065*RDI) *NAI + (1.-RDI/150.)*RA
        NNULI  = (1.-math.exp(-0.065*RDI))*NNI +     RDI/150. *RN
        NNLLI  =     math.exp(-0.065*RDI) *NNI + (1.-RDI/150.)*RN
    
        
        DS=ZERO
        CTDU=ZERO  #Cummulative thermal day unit                   d
        
        CLV=CLVI   #Amount of carbon in the living leaves          gCm-2
        CLVD=ZERO  #Amount of carbon in the dead leaves            gCm-2
        CSST=ZERO  #Amount of carbon in structural stems           gCm-2
        CSO=ZERO   #Amount of carbon in storage organs             gCm-2
        CSRT=CRTI  #Amount of carbon in structural roots           gCm-2
        CRTD=ZERO  #Amount of carbon in dead roots                 gCm-2
        CLVDS=ZERO #Amount of carbon in the dead leaves that had become litters in the soil                    gCm-2
        NRT=NRTI   #Nitrogen in living roots                       g N m- 2
        NST=ZERO   #Nitrogen content in stems (including structural stem and reserves) gNm-2
        NKV=NLVI   #Nitrogen in living leaves                      g N m-2
        NSO=ZERO   #Nitrogen content in storage organs               gNm-2
        TNLV=NLVI  #Total leaf nitrogen (including N in senesced leaves) g N m-2
        NLVD=ZERO  #Nitrogen in dead leaves                           g N m- 2
        NRTD=ZERO  #Nitrogen in dead roots                            g N m-2
    
        NLV=NLVI   #Nitrogen in living leaves                        gNm-2
        
        CRVS=ZERO  #Amount of carbon in stems reserves               gCm-2
        CRVR=ZERO  #Amount of carbon in root reserves                gCm-2
        NREOE=ZERO
        NREOF=ZERO
        DCDSR=ZERO
        DCDTR=ZERO
        SLNB=SLNBI
        LAIC=LAII  #Carbon determined LAI  m2leafm-2                          m2leafm-2
        RMUL=ZERO
        NDEMP=ZERO
        NSUPP=ZERO
        NFIXT=ZERO
        NFIXR=ZERO
        DCDTP=ZERO
        HT=HTI #Plant height     m
        RD=RDI #Rooting depth to the soil     cm
        TDAPAR=ZERO
        TPCAN=ZERO
        TRESP=ZERO
        TTCAN=ZERO
        TNUPT=ZERO
        LITNT=ZERO
        TSOIL=TSOILI
        WUL=WULI
        WLL=WLLI
        DPM=DPMI
        RPM=RPMI
        BIO=BIOI
        HUM=HUMI
        DPN=DPNI
        RPN=RPNI
        
        TNLEA=ZERO
        NAUL=NAULI
        NALL=NALLI
        NNUL=NNULI
        NNLL=NNLLI
        SFERNA=ZERO 

        #    TRANSLATION_GENERAL DRIVER='EUDRIV' #simple rectangular (euler) integration
        #print DFS,DS,CCHK,NCHK,HI,WSO,WSH,PSO,TNUPT,APCAN,PPCAN,ATCAN,NUPT,TSN,ONC,FCSH,FNSH,LAI
        #initialization of variables not mentioned before
        DIFS=0.
        WSO=0.      #Dry weight of storage organs                                     gm-2
        WSHH=0.     #Dry weight of shoot organs (excludding shedded leaves)           gm-2
        WLVD=0.     #Dry weight of dead leaves                                        gm-2
        RCSST=0.;RCRVS=0.;RCSO=0.
        RCLV=0.     #Rate of change in CLV    gCm-2d-1
        RCSRT=0.;RCRVR=0.;LCLV=0.;LCRT=0.;LVDS=0.
        APCAN=1.e-5 #???#Actual gross canopy photosynthesis     gCO2m-2d-1
        ASSA=APCAN/10.  #Assimilates available from current photosynthesis for growth     gCO2m-2d-1
        FCSH=0.     #Fraction of new carbon partitioned to shoot     gCg-1C
        FCLV=0.      #Fraction of new shoot carbon partitioned to leaves  gCg-1C
        FCSST=0.;FCRVR=0.;FCSO=0.;CREMS=0.;CREMR=0.
        NCR=0.      #Intermediate variable (nu)        gNg-1C
        DERI=0.     #First order derivative of SHSA with respect to crop N/C ratio    gCg-1Nd-1
        SHSA=1.     #Relative shoot activity             gCg-1Cd-1
        LAIN=0.;ESD=0.;FLWCT=0.
        DCSS=APCAN/20  #Daily carbon supply from current photosynthesis for shoot growth     gCm-2d-1
        FLWCS=0.
        CSRTN=0.    #Nitrogen determined CSRT            gCm-2
        RNRT=0.;RNST=0.;RNLV=0.;RNSO=0.;RTNLV=0.;LNLV=0.;LNRT=0.
        FNSH=0.     #Fraction of newly absorbed N partitioned to the shoot            gNg-1N
        NUPT=0.;LNC=0.;RNC=0.
        SLNT=0.;NDEMA=0.;APCANS=0.;RM=0.;RX=0.
        KCRN=0.5 #? #Extinction coefficient of root nitrogen    m2g-1C
        DCDS=0.;CREMSI=0.;DCSR=0.;CREMRI=0.;GAP=0.
        RNREOE=0.;RNREOF=0.;RNREOF=0.;RNREOE=0.;RNREOF=0.;RNREOF=0.;RDCDTR=0.;RNRES=0.;RDCDSR=0.;IFSH=0.
        LAI=LAII #??#Green leaf area index    m2leafm-2
        KN=0.5   #??0.5#Leaf nitrogen extinction coefficient in the canopy    m2m-2leaf
        RSLNB=0.
        TLAI=LAII    #Total leaf area index (green and seneced) m2leafm-2
        #RLAI=0.0     #?Rate of change in LAIC     m2leafm-2d-1
        NUPTN=0.;NUPTA=0.;RRMUL=0.
        NFIX=0.;RNDEMP=0.;NDEM=0.
        NSUP=0.;RNSUPP=0.;RNFIXR=0.;APCANN=0.;RMN=0.;NSUPA=0.;NSUPA=0.;NSUPN=0.;RDCDTP=0.
        RHT=0.;RRD=0.;KR=0.
        DWSUP=15.   #Daily water supply for evapotranspiration    mm d-1
        WCUL=0.     #Soil water content in the upper soil layer     m3m-3
        TAVSS=0.;RTSOIL=0.;RDPM=0.;RRPM=0.;RBIO=0.;RHUM=0.
        DECDPM=0.;DECRPM=0.;CBH=0.;DECBIO=0.;DECHUM=0.;FT=0.;FM=0.;DPMR=0.;RPMR=0.;CNDRPM=0.;DPMRC=0.;RPMRC=0.
        RDPN=0.;RRPN=0.;DECDPN=0.;DECRPN=0.;LEALL=0.
        NA=0.;NN=0.;RNAUL=0.;RNALL=0.;RNNUL=0.;RNNLL=0.;FERNA=0.;MINAUL=0.;LAYNA=0.;NITRUL=0.;VOLA=0.
        MINALL=0.;NITRLL=0.;FERNN=0.;MINNUL=0.;LAYNN=0.;DENIUL=0.;LEAUL=0.;MINNLL=0.;DENILL=0.
        MDNUL=0.;MDNUL=0.;MDNLL=0.;FMUL=0.;NSUPAS=0.;NSUPNS=0.;FWS=0.;RSFNA=0.;FMLL=0.;HI=0.;k=0.
        
        #load weather data
        weather=numpy.fromfile(file='weather_data.txt',sep=' ')
        weather=weather.reshape((numpy.shape(weather)[0]/9,9)).T
        #</INITIAL>
        for TIME in range(STTIME,FINTIM+1):
            print '*************** DAY %s ********************'%(TIME)
#            print 'Line 344--FCSH %2.4f ASSA %2.4f APCAN %2.4f'%(FCSH,ASSA,APCAN)
            #DYNAMIC
            
            #********************* ENVIRONMENTAL DATA ******************************
        
            #WEATHER CNTR='NLD'; ISTN=1; WTRDIR ='D:\weather\'; IYEAR=2003
            #*     RDD    Daily global radiation in     J/m2/d
            #*     TMMN   Daily minimum temperature in  degree C
            #*     TMMX   Daily maximum temperature in  degree C
            #*     VP     Vapour pressure in            kPa
            #*     WN     Wind speed in                 m/s
            #*     RAIN   Precipitation in              mm
            #*     LAT    Latitude of the side          degree
            #*     DOY    Day of year                   d
            #weather file rows:
            #    0 station number
            #    1 year
            #    2 day
            #    3 irradiation (kJ m-2 d-1)
            #    4 minimum temperature (degrees Celsius)
            #    5 maximum temperature (degrees Celsius)
            #    6 vapour pressure (kPa)
            #    7 mean wind speed (m s-1)
            #    8 precipitation (mm d-1)
            for i in range(len(weather[2])):
                if weather[2][i]==TIME:
                    RDD=weather[3][i]*1000.
                    TMMN=weather[4][i]
                    TMMX=weather[5][i]
                    VP=weather[6][i]
                    WN=weather[7][i]
                    RAIN=weather[8][i]
                    LAT=51.58
                    DOY=TIME
                    print'GOT WEATHER !!'
            
        
            DFS    = TIME - STTIME + 1.
        
            TMAX   = TMMX + gecros_for.insw (DS-0., 0., COEFT)
            TMIN   = TMMN + gecros_for.insw (DS-0., 0., COEFT)
            DAVTMP = 0.29*TMIN + 0.71*TMAX
            NAVTMP = 0.71*TMIN + 0.29*TMAX
        
            DDTR   = RDD * gecros_for.insw(DS-0., 0., COEFR)
            DVP    = VP  * gecros_for.insw(DS-0., 0., COEFV)
            WNM    = max (0.1, WN)
        
            #********************** THE PART OF CROP DYNAMICS **********************
        
            #*** Photoperiod, solar constant and daily extraterrestrial radiation
        
            #CALL 
            SC,SINLD,COSLD,DAYL,DDLP,DSINBE=gecros_for.astro(DOY,LAT,INSP)
        
            #*** Developmental stage (DS) & cumulative thermal units (CTDU)
        
            TDU=gecros_for.tunit(DS,TMAX,TMIN,max(0.,DIFS),DAYL,TBD,TOD,TCD,TSEN)
            DVR=gecros_for.pheno(DS,SLP,DDLP,SPSP,EPSP,PSEN,MTDV,MTDR,TDU)
            if TIME!=STTIME:
                DS     += DVR#intgrl (ZERO, DVR,DS)
                CTDU   += TDU#intgrl (ZERO, TDU,CTDU)
        
            #*** Biomass formation
            
            if TIME!=STTIME:
                HI     = WSO  / WSHH
            
            WLV    = CLV  / CFV
            WST    = CSST / CFV + CRVS/0.444
            WSO    = CSO  / CFO
            WRT    = CSRT / CFV + CRVR/0.444
            WSH    = WLV  + WST + WSO
            WSHH   = WSH  + (WLVD-CLVDS/CFV)
            WTOT   = WSH  + WRT
       
            RWST   = RCSST/ CFV + RCRVS/0.444
            RWSO   = RCSO / CFO
            RWLV   = RCLV / CFV     #Rate of change in dry weight of living leaves gm-2d-1
            RWRT   = RCSRT/ CFV + RCRVR/0.444
        
            WLVD   = CLVD / CFV
            WRTD   = CRTD / CFV
        
            #*** Carbon accumulation
            if TIME!=STTIME:
                CLV    += RCLV#intgrl (CLVI, RCLV ,CLV)
                CLVD   += LCLV#intgrl (ZERO, LCLV ,CLVD)
                CSST   += RCSST#intgrl (ZERO, RCSST,CSST)
                CSO    += RCSO#intgrl (ZERO, RCSO ,CSO)
                CSRT   += RCSRT#intgrl (CRTI, RCSRT,CSRT)
                CRTD   += LCRT#intgrl (ZERO, LCRT ,CRTD)
                CLVDS  += LVDS#intgrl (ZERO, LVDS ,CLVDS)
        
            CSH    = CLV + CSST+CRVS + CSO #Amount of carbon in living shoot organs (including stem reserves) gCm-2
            CRT    = CSRT+ CRVR #Amount of carbon in living roots organs (including root reserves) gCm-2
            CTOT   = CSH + CRT #Total amount of carbon in living shoots and roots gCm-2
        
            #*** Carbon production rate
#            print 'Line 430--ASSA %2.4f FCSH %2.4f FCLV %2.4f YGV %2.4f'%(ASSA,FCSH,FCLV,YGV)
            RCLV   = 12./44.*ASSA*    FCSH *    FCLV  *YGV - LCLV
            RCSST  = 12./44.*ASSA*    FCSH *    FCSST *YGV
            RCSRT  = 12./44.*ASSA*(1.-FCSH)*(1.-FCRVR)*YGV - LCRT
            RCSO   = 12./44.*ASSA*FCSH*FCSO*YGO + 0.94*(CREMS+CREMR)*YGO
        
            #*** Carbon partitioning among organs and reserve pools
        
            FCSH   = 1./(1.+NCR*DERI/SHSA)
        
            FCLV   = gecros_for.reaand(LAIN-LAIC,ESD-DS)*(1.-FCSO-FCSST)
            FCSST  = gecros_for.insw(DS-(ESD+0.2), FLWCT/DCSS, 0.)
            FCSO   = FLWCS/DCSS
        
            FCRVS  = 1. - FCLV - FCSO - FCSST
            FCRVR  = gecros_for.insw(CSRTN-CSRT, 1., 0.)
        
            #*** Nitrogen accumulation
            if TIME!=STTIME:
                NRT    += RNRT#intgrl (NRTI, RNRT ,NRT)
                NST    += RNST#intgrl (ZERO, RNST ,NST)
                NLV    += RNLV#intgrl (NLVI, RNLV ,NLV)
                NSO    += RNSO#intgrl (ZERO, RNSO ,NSO)
                TNLV   += RTNLV#intgrl (NLVI, RTNLV,TNLV)
                NLVD   += LNLV#intgrl (ZERO, LNLV ,NLVD)
                NRTD   += LNRT#intgrl (ZERO, LNRT ,NRTD)
        
            NSH    = NST + NLV + NSO
            NSHH   = NSH +(WLVD-CLVDS/CFV)*LNCMIN
            NTOT   = NSH + NRT
            
            #CALL 
            RNRT,RNST,RNLV,RTNLV,RNSO=gecros_for.rnacc(FNSH,NUPT,RWST,STEMNC,LNCMIN,RNCMIN,LNC,RNC,NLV,     \
                  NRT,WLV,WRT,DELT,CB,CX,TM,DS,SEEDNC,RWSO,LNLV,LNRT)
        
            #*** Nitrogen partitioning between shoots and roots

            ##print NCR,DERI,SHSA,CSH,CRT,NRT,NSH
            FNSH   = 1./(1.+NCR*DERI/SHSA*CSH/CRT*NRT/NSH)
            if TIME!=STTIME:
                NCR    = gecros_for.insw(SLNT-SLNMIN,0.,min(NUPTX,NDEMA))/(YGV* \
                   (APCANS-RM-RX)*12./44.)
        
            #*** Leaf senescence
        
            LWLVM  = (LAIC-min(LAIC,LAIN))/SLA0/DELT
            LWLV   = min(WLV-1.e-5, LWLVM+gecros_for.reanor(ESD-DS,LWLVM)*0.03*WLV)
            LCLV   = LWLV*CFV
            LNLV   = min(LWLV,LWLVM)*LNCMIN + (LWLV-min(LWLV,LWLVM))*LNC
        
            #*** Root senescence
            
            CSRTN  = 1./KCRN*math.log(1.+KCRN*max(0.,(NRT*CFV-CRVR*RNCMIN))/RNCMIN)
            KCRN   = -math.log(0.05)/6.3424/CFV/WRB/RDMX
            LCRT   = max(min(CSRT-1.e-4,CSRT-min(CSRTN,CSRT)),0.)/DELT
            LWRT   = LCRT/CFV
            LNRT   = LWRT*RNCMIN
        
            #*** Dynamics of carbon-reserve pool in stems and roots
            if TIME!=STTIME:
                CRVS   += RCRVS#intgrl (ZERO, RCRVS,CRVS)
            RCRVS  = FCRVS*DCSS - CREMS
            CREMS  = gecros_for.insw(DCDS-DCSS, 0., CREMSI)
            if TIME!=STTIME:
                CRVR   += RCRVR#intgrl (ZERO, RCRVR,CRVR)
            RCRVR  = FCRVR*DCSR - CREMR
            CREMR  = gecros_for.insw(DCDS-DCSS, 0., CREMRI)
        
            CREMSI = min(0.94*CRVS, CRVS/gecros_for.notnul(CRVS+CRVR)*GAP)/0.94
            CREMRI = min(0.94*CRVR, CRVR/gecros_for.notnul(CRVS+CRVR)*GAP)/0.94
            GAP    = max(0., DCDS-DCSS)
        
            #*** Carbon supply from current photo-assimilates for shoot & root growth
        
            DCSS   = 12./44.*    FCSH *ASSA
            DCSR   = 12./44.*(1.-FCSH)*ASSA
        
            #*** Estimation of total seed number, and 1000-seed weight
            if TIME!=STTIME:
                NREOE  += RNREOE#intgrl (ZERO, RNREOE,NREOE)
                NREOF  += RNREOF#intgrl (ZERO, RNREOF,NREOF)
            RNREOE = gecros_for.insw (DS-ESD, RNRES, 0.)
            RNREOF = gecros_for.insw (DS-1.0, RNRES, 0.)
            RNRES  = NUPT-(LNCMIN*(RCLV+LCLV)+RNCMIN*(RCSRT+LCRT)+STEMNC*RCSST)/CFV
        
            ESD    = gecros_for.insw(DETER, ESDI, 1.)
            NRES   = NREOF + (NREOE-NREOF)*(ESD-1.)/gecros_for.notnul(min(DS,ESD)-1.)
        
            TSN    = NRES/PNPRE/SEEDNC/SEEDW
            TSW    = WSO/gecros_for.notnul(TSN)*1000.
        
            #*** Daily carbon flow for seed filling
        
            #CALL
            FDS=gecros_for.betaf(DVR,1.,PMES*1.,gecros_for.limit(1.,2.,DS)-1.)
            DCDSC,DCDS,FLWCS=gecros_for.sinkg(DS,1.,TSN*SEEDW*CFO,YGO,FDS,DCDSR,DCSS,DELT)
            if TIME!=STTIME:
                DCDSR  += RDCDSR#intgrl(ZERO, RDCDSR,DCDSR)
            RDCDSR = max(0., (DCDSC-RCSO/YGO))-(FLWCS-min(DCDSC,DCSS))
        
            #*** Daily carbon flow for structural stem growth
        
            DCST   = DCSS - FLWCS
            #CALL
            FDH=gecros_for.betaf(DVR,(1.+ESD)/2.,PMEH*(1.+ESD)/2.,min((1.+ESD)/2.,DS))
            DCDTC,DCDT,FLWCT=gecros_for.sinkg(DS,0.,CDMHT*HTMX*CFV,YGV,FDH*IFSH,DCDTR,DCST,DELT)
            if TIME!=STTIME:
                DCDTR  += RDCDTR#intgrl(ZERO, RDCDTR,DCDTR)
            RDCDTR = max(0., (DCDTC-RCSST/YGV))-(FLWCT-min(DCDTC,DCST))
            #*** Nitrogen concentration of biomass
        
            LNC    = NLV / WLV
            RNC    = NRT / WRT
            HNC    = NSH / WSH
            PNC    = NTOT/ WTOT
            ONC    = gecros_for.insw(-WSO, NSO/gecros_for.notnul(WSO), 0.)
        
            #*** Amount of seed protein
        
            PSO    = 6.25*WSO*ONC
        
            #*** Specific leaf nitrogen and its profile in the canopy

            SLN    = NLV/LAI
            SLNT   = NLV*KN                  /(1.-math.exp(-KN*LAI))
            SLNBC  = NLV*KN*math.exp(-KN*LAI)/(1.-math.exp(-KN*LAI))
            SLNNT  = (NLV+0.001*NLV)*KN /(1.-math.exp(-KN*LAI))
            if TIME!=STTIME:
                SLNB   += RSLNB#intgrl (SLNBI, RSLNB,SLNB)
            RSLNB  = (SLNBC-SLNB)/DELT
        
            #*** Extinction coefficient of nitrogen and wind
        
            #CALL
            KL=gecros_for.kdiff(TLAI,BLD*3.141592654/180.,0.2)
        
            KLN    = KL*(TNLV-SLNMIN*TLAI)
            NBK    = SLNMIN*(1.-math.exp(-KL*TLAI))
            #print 'KLN{0},NBK{1},KL{2},TLAI{3}'.format(KLN,NBK,KL,TLAI)
            KN     = 1./TLAI*math.log((KLN+NBK)/(KLN*math.exp(-KL*TLAI)+NBK))
            KW     = KL
        
            #*** Leaf area development
            LAIN   = math.log(1.+KN*max(0.,NLV)/SLNMIN)/KN
            if TIME!=STTIME:
                LAIC += RLAI#intgrl(LAII, RLAI,LAIC)
            #CALL
            RLAI=gecros_for.rlaic(DS,SLA0,RWLV,LAIC,KN,NLV,RNLV,SLNB,RSLNB)
#            print 'Line 578--RLAI %2.4f,DS %2.4f,SLA0 %2.4f,RWLV %2.4f,LAIC %2.4f,KN %2.4f,NLV %2.4f,RNLV %2.4f,SLNB %2.4f,RSLNB %2.4f'\
#            %(RLAI,DS,SLA0,RWLV,LAIC,KN,NLV,RNLV,SLNB,RSLNB)
            LAI    = min(LAIN, LAIC)
            SLA    = LAI /WLV
        
            DLAI   = (CLVD-CLVDS)/CFV*SLA0
            TLAI   = LAIC + CLVD /CFV*SLA0
        
            #*** Maintenance and total respiration (g CO2 m-2 d-1)
            
            RMUN   = 44./12.*2.05*NUPTN
            RMUA   = 44./12.*0.17*NUPTA
            RMUS   = 0.06* 0.05/0.454*YGV*ASSA
            RMLD   = 0.06*(1.-FCSH)*ASSA
            if TIME!=STTIME:
                RMUL   += RRMUL#intgrl(ZERO, RRMUL,RMUL)
            RRMUL  = (RMUN+RMUA+RMUS+RMLD - RMUL)/DELT
        
            RMRE   = max(min(44./12.*0.218*(NTOT-WSH*LNCMIN-WRT*RNCMIN),  \
                           APCAN-1.e-5-RMUL), 0.)
            RM     = max(0., min(APCAN-1.e-5,RMUL) + RMRE)
            RX     = 44./12.*(CCFIX*NFIX)
            RG     = 44./12.*((1.-YGV)/YGV*(RCLV+RCSST+RCSRT+LCLV+LCRT)+  \
                                (1.-YGO)/YGO* RCSO)
        
            RESTOT = RM+RX+RG + 44./12.*0.06*(CREMS+CREMR)
        
            #*** Current photo-assimilates (g CO2 m-2 d-1) for growth, and R/P ratio
            
            ASSA   = APCAN - RM - RX
            if APCAN!=0:
                RRP    = RESTOT / APCAN
        
            #*** Nitrogen fixation (g N m-2 d-1)
            if TIME!=STTIME:
                NDEMP  += RNDEMP#intgrl (ZERO, RNDEMP,NDEMP)
            RNDEMP = (NDEM - NDEMP)/DELT
            if TIME!=STTIME:
                NSUPP  += RNSUPP#intgrl (ZERO, RNSUPP,NSUPP)
            RNSUPP = (NSUP - NSUPP)/DELT
        
            NFIXE  = max(0., APCAN-1.e-5-RM)/CCFIX*12./44.
            NFIXD  = max(0., NDEMP - NSUPP)
        
            NFIX   = gecros_for.insw (LEGUME, 0., min(NFIXE, NFIXD))
            if TIME!=STTIME:
                NFIXT  += NFIX#intgrl (ZERO, NFIX,NFIXT)
        
            if TIME!=STTIME:
                NFIXR  += RNFIXR#intgrl (ZERO, RNFIXR,NFIXR)
            RNFIXR = NFIX - min(NDEM,NFIXR/TCP)
        
            #*** Crop nitrogen demand and uptake (g N m-2 d-1)
        
            SHSA   = 12./44. * YGV*(APCAN -RM -RX)/ CSH
            SHSAN  = 12./44. * YGV*(APCANN-RMN-RX)/ CSH
        
            RMN    = max(0., min(APCAN-1.e-5,RMUL) + max(min(44./12.*0.218*  \
                      (1.001*NTOT-WSH*LNCMIN-WRT*RNCMIN),APCAN-1.e-5-RMUL), 0.))
        
            DERI   = max(0.,(SHSAN - SHSA)/(0.001*NTOT/CTOT))
            NDEMA  = CRT * SHSA**2/gecros_for.notnul(DERI)
        
            HNCCR  = LNCI*math.exp(-0.4*DS)
            
            NDEMD  = gecros_for.insw(DS-1., WSH*(HNCCR-HNC)*(1.+NRT/NSH)/DELT, 0.)
        
            NDEMAD = gecros_for.insw(LNC-1.5*LNCI, max(NDEMA, NDEMD), 0.)
            NDEM   = gecros_for.insw(SLNMIN-SLN+1.e-5, min(NUPTX,NDEMAD), 0.)
        
            NUPTA  = min(NSUPA, NSUPA/gecros_for.notnul(NSUP)*max(0.,NDEM-NFIXR/TCP))
            NUPTN  = min(NSUPN, NSUPN/gecros_for.notnul(NSUP)*max(0.,NDEM-NFIXR/TCP))
            NUPT   = max(0., NUPTA + NUPTN + min(NDEM, NFIXR/TCP))
        
            #*** Plant height or stem length (m)
        
            if TIME!=STTIME:
                DCDTP  += RDCDTP#intgrl(ZERO, RDCDTP,DCDTP)
            RDCDTP = (DCDTC-DCDTP)/DELT
            IFSH   = gecros_for.limit(0.,1.,DCST/gecros_for.notnul(DCDTP))
        
            if TIME!=STTIME:
                HT     += RHT#intgrl (HTI, RHT,HT)
            RHT    = min(HTMX-HT, FDH*HTMX*IFSH)
            
            #*** Rooting depth (cm)
        
            if TIME!=STTIME:
                RD     += RRD#intgrl (RDI, RRD,RD)
            RRD    = gecros_for.insw(RD-RDMX, min((RDMX-RD)/DELT,(RWRT+LWRT)/(WRB+KR* \
                       (WRT+WRTD))), 0.)
            KR     = -math.log(0.05)/RDMX
        
            #*** Canopy photosynthesis, transpiration and soil evaporation
            VLS  = [(0.,0.),(2.5,0.)]
            LS     = gecros_for.insw(LODGE, 0., afgen(VLS,DS))
            #FUNCTION VLS  = 0.,0., 2.5,0.
        
            FVPD   = gecros_for.insw (C3C4, 0.195127, 0.116214)
            #CALL 
            PPCAN,APCANS,APCANN,APCAN,PTCAN,ATCAN,PESOIL,AESOIL,\
            DIFS,DIFSU,DIFSH,DAPAR=gecros_for.totpt(SC,SINLD,COSLD,DAYL,DSINBE,DDTR,TMAX,TMIN,DVP, \
                         WNM,C3C4,LAIC,TLAI,HT,LWIDTH,RD,SD1,RSS,BLD,KN,    \
                         KW,SLN,SLNT,SLNNT,SLNMIN,DWSUP,CO2A,LS,EAJMAX,     \
                         XVN,XJN,THETA,WCUL,FVPD) 
#            print """Line 682-----
#            *  SC      %2.4f  Solar constant                             J m-2 s-1 I  *
#            *  SINLD   %2.4f  Seasonal offset of sine of solar height    -         I  *
#            *  COSLD   %2.4f  Amplitude of sine of solar height          -         I  *
#            *  DAYL    %2.4f  Astronomic daylength (base = 0 degrees)    h         I  *
#            *  DSINBE  %2.4f  Daily total of effective solar height      s d-1     I  *
#            *  DDTR    %2.4f  Daily global radiation                     J m-2 d-1 I  *
#            *  TMAX    %2.4f  Daily maximum temperature                  oC        I  *
#            *  TMIN    %2.4f  Daily minimum temperature                  oC        I  *
#            *  DVP     %2.4f  Vapour pressure                            kPa       I  *
#            *  WNM     %2.4f  daily average wind speed (>=0.1 m/s)       m s-1     I  *
#            *  C3C4    %2.4f  Crop type (=1 for C3, -1 for C4 crops)     -         I  *
#            *  LAI     %2.4f  (green)Leaf area index                     m2 m-2    I  *
#            *  TLAI    %2.4f  Total Leaf area index                      m2 m-2    I  *
#            *  HT      %2.4f  Plant height                               m         I  *
#            *  LWIDTH  %2.4f  Leaf width                                 m         I  *
#            *  RD      %2.4f  Rooting depth                              cm        I  *
#            *  SD1     %2.4f  Depth of evaporative upper soil layer      cm        I  *
#            *  RSS     %2.4f  Soil resistance,equivalent to leaf stomata s m-1     I  *
#            *  BLD     %2.4f  Leaf angle from horizontal                 degree    I  *
#            *  KN      %2.4f  Leaf nitrogen extinction coefficient       m2 m-2    I  *
#            *  KW      %2.4f  Windspeed extinction coefficient in canopy m2 m-2    I  *
#            *  SLN     %2.4f  Average leaf nitrogen content in canopy    g m-2     I  *
#            *  SLNT    %2.4f  Top-leaf nitrogen content                  g m-2     I  *
#            *  SLNN    %2.4f  Value of SLNT with small plant-N increment g m-2     I  *
#            *  SLNMIN  %2.4f  Minimum or base SLNT for photosynthesis    g m-2     I  *
#            *  DWSUP   %2.4f  Daily water supply for evapotranspiration  mm d-1    I  *
#            *  CO2A    %2.4f  Ambient CO2 concentration                  ml m-3    I  *
#            *  LS      %2.4f  Lodging severity                           -         I  *
#            *  EAJMAX  %2.4f  Energy of activation for Jmax              J mol-1   I  *
#            *  XVN     %2.4f  Slope of linearity between Vcmax & leaf N  umol/g/s  I  *
#            *  XJN     %2.4f  Slope of linearity between Jmax & leaf N   umol/g/s  I  *
#            *  THETA   %2.4f  Convexity for light response of e-transport   -      I  *
#            *  WCUL    %2.4f  Water content of the upper soil layer      m3 m-3    I  *
#            *  FVPD    %2.4f  Slope for linear effect of VPD on Ci/Ca    (kPa)-1   I  *"""%\
#            (SC,SINLD,COSLD,DAYL,DSINBE,DDTR,TMAX,TMIN,DVP, \
#                         WNM,C3C4,LAIC,TLAI,HT,LWIDTH,RD,SD1,RSS,BLD,KN,    \
#                         KW,SLN,SLNT,SLNNT,SLNMIN,DWSUP,CO2A,LS,EAJMAX,     \
#                         XVN,XJN,THETA,WCUL,FVPD)
            #*  PPCAN   R4  Potential canopy CO2 assimilation          g m-2 d-1 O  *
            #*  APCANS  R4  Actual standing-canopy CO2 assimilation    g m-2 d-1 O  *
            #*  APCANN  R4  APCANS with small plant-N increment        g m-2 d-1 O  *
            #*  APCAN   R4  Actual canopy CO2 assimilation             g m-2 d-1 O  *
            #*  PTCAN   R4  Potential canopy transpiration             mm d-1    O  *
            #*  ATCAN   R4  Actual canopy transpiration                mm d-1    O  *
            #*  PESOIL  R4  Potential soil evaporation                 mm d-1    O  *
            #*  AESOIL  R4  Actual soil evaporation                    mm d-1    O  *
            #*  DIFS    R4  Daytime average soil-air temp. difference  oC        O  *
            #*  DIFSU   R4  Daytime aver. sunlit leaf-air temp. diff.  oC        O  *
            #*  DIFSH   R4  Daytime aver. shaded leaf-air temp. diff.  oC        O  *
            #*  DAPAR   R4  Daily PAR absorbed by crop canopy          J m-2 d-1 O  *
#            print 'Line 733 --PPCAN %2.4f,APCANS %2.4f,APCANN %2.4f,APCAN %2.4f,PTCAN %2.4f,ATCAN %2.4f,PESOIL %2.4f,AESOIL %2.4f\
#            DIFS %2.4f,DIFSU %2.4f,DIFSH %2.4f,DAPAR%2.4f '%(PPCAN,APCANS,APCANN,APCAN,PTCAN,ATCAN,PESOIL,AESOIL,\
#            DIFS,DIFSU,DIFSH,DAPAR)        
            #*** Cumulative absorbed-PAR, photosynthesis, respiration,
            #*   transpiration, and N-uptake during the growing season
        
            if TIME!=STTIME:
                TDAPAR += DAPAR#intgrl (ZERO, DAPAR ,TDAPAR)
                TPCAN  += APCAN#intgrl (ZERO, APCAN ,TPCAN)
                TRESP  += RESTOT#intgrl (ZERO, RESTOT,TRESP)
                TTCAN  += ATCAN#intgrl (ZERO, ATCAN ,TTCAN)
                TNUPT  += NUPT#intgrl (ZERO, NUPT  ,TNUPT)
        
            #*** Crop carbon balance check
        
            CCHKIN = CTOT + CLVD+CRTD -CLVI-CRTI
            CCHK   = (CCHKIN-(TPCAN-TRESP)*12./44.)/gecros_for.notnul(CCHKIN)*100.
        
            #*** Crop nitrogen balance check
        
            NCHKIN = NTOT + NLVD+NRTD -NLVI-NRTI
            NCHK   = (NCHKIN-TNUPT)/gecros_for.notnul(TNUPT)*100.
            print 'NTOT + NLVD+NRTD -NLVI-NRTI',NTOT , NLVD,NRTD,NLVI,NRTI
            #*** Daily and total C and N returns from crop to soil
        
            LVDS   = (CLVD-CLVDS)/10.*(TAVSS-TBD)/(TOD-TBD)
        
            LITC   =  LCRT + LVDS
            LITN   =  LNRT + LVDS/CFV *LNCMIN*PNLS
        
            if TIME!=STTIME:
                LITNT  += LITN#intgrl(ZERO, LITN,LITNT)
            NRETS  = LITNT+gecros_for.insw(DS-2.,0.,NLV+NST+NRT+NFIXR+       \
                       (CLVD-CLVDS)/CFV*LNCMIN*(1.+PNLS)/2.)
        
        
            #********************** THE EXAMPLE SOIL MODEL *************************
        
            #*** Soil temperature
        
            if TIME!=STTIME:
                TSOIL  += RTSOIL#intgrl (TSOILI, RTSOIL,TSOIL)
            RTSOIL = (TAVSS - TSOIL)/TCT
            TAVSS  = ((DAVTMP+DIFS)+NAVTMP)/2.
        
            #*** Soil water availability and water balance
        
            IRRI   =   gecros_for.fcnsw(IR1T-DFS,0.,IR1A,0.)+gecros_for.fcnsw(IR2T-DFS,0.,IR2A,0.)+  \
                       gecros_for.fcnsw(IR3T-DFS,0.,IR3A,0.)+gecros_for.fcnsw(IR4T-DFS,0.,IR4A,0.)+  \
                       gecros_for.fcnsw(IR5T-DFS,0.,IR5A,0.)+gecros_for.fcnsw(IR6T-DFS,0.,IR6A,0.)+  \
                       gecros_for.fcnsw(IR7T-DFS,0.,IR7A,0.)+gecros_for.fcnsw(IR8T-DFS,0.,IR8A,0.)+  \
                       gecros_for.fcnsw(IR9T-DFS,0.,IR9A,0.)+gecros_for.fcnsw(IR10T-DFS,0.,IR10A,0.)
        
            RFIR   = RAIN + IRRI
        
            WCUL   = (WUL+WCMIN*10.*RD)/10./RD
            WCLL   = min(WCMAX, (WLL+WCMIN*10.*(150.-RD))/10./(150.-RD))
        
            RRUL   = min(10.*(WCMAX-WCUL)*      RD /TCP, RFIR)
            RRLL   = min(10.*(WCMAX-WCLL)*(150.-RD)/TCP, RFIR-RRUL)
        
            RWUL   = RRUL+10.*(WCLL-WCMIN)*RRD-gecros_for.insw(WSWI,0.,ATCAN+AESOIL)+.1
            RWLL   = RRLL-10.*(WCLL-WCMIN)*RRD
            RWUG   = max (0., RFIR-RRUL-RRLL)
        
            if TIME!=STTIME:
                WUL    += RWUL#intgrl (WULI, RWUL,WUL)
                WLL    += RWLL#intgrl (WLLI, RWLL,WLL)
            DWSUP  = gecros_for.insw(WSWI, WINPUT, max(0.1,WUL/TCP+0.1))
        
            #*** Soil organic carbon
            if TIME!=STTIME:
                DPM    += RDPM#intgrl (DPMI, RDPM,DPM)
                RPM    += RRPM#intgrl (RPMI, RRPM,RPM)
                BIO    += RBIO#intgrl (BIOI, RBIO,BIO)
                HUM    += RHUM#intgrl (HUMI, RHUM,HUM)
        
            RDPM   = LITC*DRPM/(1.+DRPM) - DECDPM
            RRPM   = LITC*1.  /(1.+DRPM) - DECRPM
            RBIO   = 0.46/(1.+CBH)*(DECDPM+DECRPM+DECBIO+DECHUM) - DECBIO
            RHUM   = 0.54/(1.+CBH)*(DECDPM+DECRPM+DECBIO+DECHUM) - DECHUM
        
            DECDPM = DPM*(1.-math.exp(-FT*FM*DPMR/365.))/TCP
            DECRPM = RPM*(1.-math.exp(-FT*FM*RPMR/365.))/TCP
            DECBIO = BIO*(1.-math.exp(-FT*FM*BIOR/365.))/TCP
            DECHUM = HUM*(1.-math.exp(-FT*FM*HUMR/365.))/TCP
        
            DPMR   = gecros_for.insw(1./gecros_for.notnul(CNDRPM)-1./8.5/(1.+CBH), DPMRC, DPMR0)
            RPMR   = gecros_for.insw(1./gecros_for.notnul(CNDRPM)-1./8.5/(1.+CBH), RPMRC, RPMR0)
            DPMRC  = gecros_for.insw(NNUL+NAUL+NNLL+NALL-RA-RN, 0., DPMR0)
            RPMRC  = gecros_for.insw(NNUL+NAUL+NNLL+NALL-RA-RN, 0., RPMR0)
        
            RESCO2 = CBH /(1.+CBH)*(DECDPM+DECRPM+DECBIO+DECHUM)
        
            CBH    = 1.67*(1.85+1.60*math.exp(-0.0786*CLAY))
            FT     = 47.9/(1.  +math.exp(106./(TSOIL+18.3)))
            FM     = gecros_for.limit(0.2, 1.0, 0.2+0.8*(WUL+WLL)/10./150./(WCFC-WCMIN))
        
            #*** Soil organic nitrogen
            if TIME!=STTIME:
                DPN    += RDPN#intgrl (DPNI, RDPN,DPN)
                RPN    += RRPN#intgrl (RPNI, RRPN,RPN)
        
            RDPN   = LITN/(1.+ 40./DRPM/100.) - DECDPN
            RRPN   = LITN/(1.+100.*DRPM/40. ) - DECRPN
        
            DECDPN = DPN*(1.-math.exp(-FT*FM*DPMR/365.))/TCP
            DECRPN = RPN*(1.-math.exp(-FT*FM*RPMR/365.))/TCP
        
            CNDRPM = (DPM+RPM)/gecros_for.notnul(DPN+RPN)
        
            #*** Soil mineral nitrogen
        
            if TIME!=STTIME:
                TNLEA  += LEALL#intgrl (ZERO, LEALL,TNLEA)
            NMINER = NA   + NN
            NA     = NAUL + NALL
            NN     = NNUL + NNLL
            if TIME!=STTIME:
                NAUL   += RNAUL#intgrl (NAULI, RNAUL,NAUL)
                NALL   += RNALL#intgrl (NALLI, RNALL,NALL)
                NNUL   += RNNUL#intgrl (NNULI, RNNUL,NNUL)
                NNLL   += RNNLL#intgrl (NNLLI, RNNLL,NNLL)
        
            RNAUL  =FERNA+MINAUL       +LAYNA-gecros_for.insw(NSWI,0.,NUPTA)-NITRUL-VOLA
            RNALL  =      MINALL       -LAYNA                    -NITRLL
            RNNUL  =FERNN+MINNUL+NITRUL+LAYNN-gecros_for.insw(NSWI,0.,NUPTN)-DENIUL-LEAUL
            RNNLL  =LEAUL+MINNLL+NITRLL-LAYNN                    -DENILL-LEALL
        
            MDN    = 1./8.5*(DECBIO+DECHUM)+ DECDPN+DECRPN - \
                       1./8.5/(1.+CBH)*(DECDPM+DECRPM+DECBIO+DECHUM)
        
            MINAUL = gecros_for.insw(MDN,-min((NAUL-      RD /150.*RA)/TCP,-MDNUL),MDNUL)
            MINALL = gecros_for.insw(MDN,-min((NALL-(150.-RD)/150.*RA)/TCP,-MDNLL),MDNLL)
            MINNUL = gecros_for.insw(MDN,-min(NNUL/TCP,-MDNUL+MINAUL), 0.)
            MINNLL = gecros_for.insw(MDN,-min(NNLL/TCP,-MDNLL+MINALL), 0.)
            MDNUL  = (1.-math.exp(-0.065*RD))*MDN
            MDNLL  =     math.exp(-0.065*RD) *MDN
        
            NITRUL = max(0.,(NAUL+MINAUL*TCP-      RD /150.*RA))*  \
                       (1.-math.exp(-FT*FMUL*0.6/7.))/TCP
            NITRLL = max(0.,(NALL+MINALL*TCP-(150.-RD)/150.*RA))*  \
                       (1.-math.exp(-FT*FMLL*0.6/7.))/TCP
            FMUL   = gecros_for.limit(0.2, 1.0, 0.2+0.8*WUL/10./      RD /(WCFC-WCMIN))
            FMLL   = gecros_for.limit(0.2, 1.0, 0.2+0.8*WLL/10./(150.-RD)/(WCFC-WCMIN))
        
            DENIUL = .0005*max(0.,NNUL+MINNUL*TCP-      RD /150.*RN)*  \
                       RESCO2*(1.-math.exp(-0.065*RD))
            DENILL = .0005*max(0.,NNLL+MINNLL*TCP-(150.-RD)/150.*RN)*  \
                       RESCO2*    math.exp(-0.065*RD)
            NSUP   = NSUPA + NSUPN
            NSUPA  = gecros_for.insw(NSWI, NINPA, NSUPAS)
            NSUPN  = gecros_for.insw(NSWI, NINPN, NSUPNS)
            NSUPAS = max (0., NAUL+(MINAUL-NITRUL)*TCP-RD/150.*RA)/TCP
            NSUPNS = max (0., NNUL+(MINNUL-DENIUL)*TCP-RD/150.*RN)/TCP*FWS
        
            FWS    = min(1., WUL/(RD*10.*(WCFC-WCMIN)))
        
            LEAUL  = max(0.,(NSUPN-NUPTN)*TCP             -RD /150.*RN)\
                      *min((RFIR-RRUL)/WCMAX/RD/10.,1.)
            LEALL  = max(0.,NNLL+(MINNLL-DENIUL)*TCP-(150.-RD)/150.*RN)\
                      *min(RWUG/WCMAX/(150.-RD)/10.,1.)
        
            VOLA   = gecros_for.insw (RAIN-1., 0.15, 0.) * SFERNA
            if TIME!=STTIME:
                SFERNA += RSFNA#intgrl (ZERO, RSFNA,SFERNA)
            RSFNA  = FERNA - SFERNA/3.
        
            LAYNA  = RRD/(150.-RD)*NALL
            LAYNN  = RRD/(150.-RD)*NNLL
        
            FERNA  =  gecros_for.fcnsw(FNA1T-DFS,0.,FNA1,0.)+gecros_for.fcnsw(FNA2T-DFS,0.,FNA2,0.) \
                     +gecros_for.fcnsw(FNA3T-DFS,0.,FNA3,0.)+gecros_for.fcnsw(FNA4T-DFS,0.,FNA4,0.) \
                     +gecros_for.fcnsw(FNA5T-DFS,0.,FNA5,0.)+gecros_for.fcnsw(FNA6T-DFS,0.,FNA6,0.) \
                     +gecros_for.fcnsw(FNA7T-DFS,0.,FNA7,0.)+gecros_for.fcnsw(FNA8T-DFS,0.,FNA8,0.)
            FERNN  =  gecros_for.fcnsw(FNN1T-DFS,0.,FNN1,0.)+gecros_for.fcnsw(FNN2T-DFS,0.,FNN2,0.) \
                     +gecros_for.fcnsw(FNN3T-DFS,0.,FNN3,0.)+gecros_for.fcnsw(FNN4T-DFS,0.,FNN4,0.) \
                     +gecros_for.fcnsw(FNN5T-DFS,0.,FNN5,0.)+gecros_for.fcnsw(FNN6T-DFS,0.,FNN6,0.) \
                     +gecros_for.fcnsw(FNN7T-DFS,0.,FNN7,0.)+gecros_for.fcnsw(FNN8T-DFS,0.,FNN8,0.)
            
            print ('DFS {0},DS {1},CCHK {2},NCHK {3},HI {4},WSO {5},WSH {6},PSO {7},TNUPT {8},APCAN {9},PPCAN{10} ,ATCAN {11},NUPT {12},TSN {13},ONC {14},FCSH {15},FNSH {16},LAI {17}'.format(DFS,DS,CCHK,NCHK,HI,WSO,WSH,PSO,TNUPT,APCAN,PPCAN,ATCAN,NUPT,TSN,ONC,FCSH,FNSH,LAI))
            #Accumulate daily values in recorders         
            keys=dir(sc);keys.remove('d')
            values=[repr(eval('sc.{0}'.format(i))) for i in keys]
            for i in range(len(keys)):
                if '_' in keys[i] or 'run' in keys[i] :continue
                k=float(values[i])
                if keys[i] not in self.d.keys():
                    self.d[keys[i]]=[k]
                else:self.d[keys[i]].append(k)
            dict2=locals()
            for i in dict2.keys():
                if i=='values' or i=='keys' or i=='dict2' or i=='self' or i=='run' or i=='weather' or i=='afgen' or i=='VLS':continue 
                k=float(dict2[i])
                if i not in self.d.keys():
                    self.d[i]=[k]
                else:self.d[i].append(k)
        #END
        #STOP
        #write output files 
        self.keys=numpy.array(self.d.keys())
        self.d=numpy.array(self.d.values(),'f').T
        fid=open('output_modelo_trigo.txt','w')
        self.keys.tofile(fid, sep=" ") 
        fid.write('\n')
        numpy.savetxt(fid, self.d, fmt="%5.5f")  
        fid.close()


if __name__ == '__main__' :
    if len(sys.argv)==2 : 
        scenarioName = sys.argv[1]
        print 'Running scenario %s' %(scenarioName)
        sc=gecros()
        sc.run()
    else :
        print 'Please re-enter your request.'
        print 'enter, for example'
        print 'python modelo_inia_trigo.py scenario_name'



