
***********************************************************************
*                               GECROS                                *
*     Genotype-by-Environment interaction on CROp growth Simulator    *
*          (as linked with a simple soil simulation model)            *
*                                                                     *
*                          (FST-version 1.0)                          *
*                         Author: Xinyou YIN                          *
*                      Crop and Weed Ecology group                    *
*                Wageningen University & Research Centre              *
*               PO Box 430, 6700 AK Wageningen, Netherlands           *
*               Pythonized by agb32@cornell.edu 2008                  *
*               1126 Bradfield Hall, Cornell University               *
*                           Ithaca NY 14850                           *
***********************************************************************

******************** SUBROUTINES FOR CROP SIMULATION *******************
*----------------------------------------------------------------------*
*  SUBROUTINE TUNIT                                                    *
*  Purpose: This subroutine calculates the daily amount of thermal day *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*                                                                      *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  DS      R4  Development stage                            -       I  *
*  TMAX    R4  Daily maximum temperature                    oC      I  *
*  TMIN    R4  Daily minimum temperature                    oC      I  *
*  DIF     R4  Daytime plant-air temperature differential   oC      I  *
*  DAYL    R4  Astronomic daylength (base = 0 degrees)      h       I  *
*  TBD     R4  Base temperature for phenology               oC      I  *
*  TOD     R4  Optimum temperature for phenology            oC      I  *
*  TCD     R4  Ceiling temperature for phenology            oC      I  *
*  TSEN    R4  Curvature for temperature response           -       I  *
*  TDU     R4  Daily thermal-day unit                       -       O  *
*----------------------------------------------------------------------*
      SUBROUTINE TUNIT(DS,TMAX,TMIN,DIF,DAYL,TBD,TOD,TCD,TSEN, TDU)
Cf2py intent(out) tdu

      IMPLICIT REAL (A-Z)
      INTEGER I
      SAVE

*---timing for sunrise and sunset
      SUNRIS = 12. - 0.5*DAYL
      SUNSET = 12. + 0.5*DAYL

*---mean daily temperature
      TMEAN  = (TMAX + TMIN)/2.
      TT    = 0.

*---diurnal course of temperature
      DO 10 I = 1, 24
        IF (I.GE.SUNRIS .AND. I.LE.SUNSET) THEN
          TD = TMEAN+DIF+0.5*ABS(TMAX-TMIN)*COS(0.2618*FLOAT(I-14))
        ELSE
          TD = TMEAN    +0.5*ABS(TMAX-TMIN)*COS(0.2618*FLOAT(I-14))
        ENDIF

*---assuming development rate at supra-optimum temperatures during
*   the reproductive phase equals that at the optimum temperature
        IF (DS.GT.1.) THEN
           TD = MIN (TD,TOD)
        ELSE
           TD = TD
        ENDIF

*---instantaneous thermal unit based on bell-shaped temperature response
        IF (TD.LT.TBD .OR. TD.GT.TCD) THEN
           TU = 0.
        ELSE
           TU = (((TCD-TD)/(TCD-TOD))*((TD-TBD)/(TOD-TBD))**
     $          ((TOD-TBD)/(TCD-TOD)))**TSEN
        ENDIF

        TT = TT + TU/24.
  10  CONTINUE

*---daily thermal unit
      TDU = TT

      RETURN
      END




*----------------------------------------------------------------------*
*  SUBROUTINE PHENO                                                    *
*  Purpose: This subroutine calculates phenological development rate.  *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*                                                                      *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  DS      R4  Development stage                            -       I  *
*  SLP     R4  Crop type(1. for short-day,-1. for long-day) -       I  *
*  DDLP    R4  Daylength for photoperiodism                 h       I  *
*  SPSP    R4  DS for start of photoperiod-sensitive phase  -       I  *
*  EPSP    R4  DS for end of photoperiod-sensitive phase    -       I  *
*  PSEN    R4  Photoperiod sensitivity (+ for SD, - for LD) h-1     I  *
*  MTDV    R4  Minimum thermal days for vegetative phase    d       I  *
*  MTDR    R4  Minimum thermal days for reproductive phase  d       I  *
*  TDU     R4  Daily thermal-day unit                       -       I  *
*  DVR     R4  Development rate                             d-1     O  *
*----------------------------------------------------------------------*
      SUBROUTINE PHENO (DS,SLP,DDLP,SPSP,EPSP,PSEN,MTDV,MTDR,TDU, DVR)
Cf2py intent(out) dvr

      IMPLICIT REAL (A-Z)
      SAVE

*---determining if it is for short-day or long-day crop
      IF (SLP.LT.0.) THEN
          MOP = 18.     !minimum optimum photoperiod for long-day crop
          DLP = MIN(MOP,DDLP)
      ELSE
          MOP = 11.     !maximum optimum photoperiod for short-day crop
          DLP = MAX(MOP,DDLP)
      ENDIF

*---effect of photoperiod on development rate
      IF (DS.LT.SPSP .OR. DS.GT.EPSP) THEN
          EFP = 1.
      ELSE
          EFP = MAX(0., 1.-PSEN*(DLP-MOP))
      ENDIF

*---development rate of vegetative and reproductive phases
      IF (DS.GE.0. .AND. DS.LT.1.0) THEN
          DVR   = 1./MTDV*TDU*EFP
      ELSE
          DVR   = 1./MTDR*TDU
      ENDIF

      RETURN
      END

*----------------------------------------------------------------------*
*  SUBROUTINE RNACC                                                    *
*  Purpose: This subroutine calculates rate of N accumulation in organs*
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*                                                                      *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  FNSH    R4  Fraction of new N partitioned to shoot       -       I  *
*  NUPT    R4  Nitrogen uptake at a time step               gN/m2/d I  *
*  RWST    R4  Rate of stem weight                          g/m2/d  I  *
*  STEMNC  R4  Nitrogen concentration in stem               gN/g    I  *
*  LNCMIN  R4  Minimum N concentration in leaf              gN/g    I  *
*  RNCMIN  R4  Minimum N concentration in root              gN/g    I  *
*  LNC     R4  Nitrogen concentration in leaf               gN/g    I  *
*  RNC     R4  Nitrogen concentration in root               gN/g    I  *
*  NLV     R4  Canopy (green)leaf N content                 gN/m2   I  *
*  NRT     R4  (living)root N content                       gN/m2   I  *
*  WLV     R4  Canopy (green)leaf weight                    g/m2    I  *
*  WRT     R4  (living)Root weight                          g/m2    I  *
*  DELT    R4  Time step of simulation                      d       I  *
*  CB      R4  Factor for initial N concent. of seed-fill   -       I  *
*  CX      R4  Factor for final N concent. of seed-fill     -       I  *
*  TM      R4  DS when transition from CB to CX is fastest  -       I  *
*  DS      R4  Development stage                            -       I  *
*  SEEDNC  R4  Standard seed N concentration                gN/g    I  *
*  RWSO    R4  growth rate of seed                          g/m2/d  I  *
*  LNLV    R4  Loss rate of NLV due to senescence           gN/m2/d I  *
*  LNRT    R4  Loss rate of NRT due to senescence           gN/m2/d I  *
*  RNRT    R4  rate of N accumulation in root               gN/m2/d O  *
*  RNST    R4  rate of N accumulation in stem               gN/m2/d O  *
*  RNLV    R4  rate of N accumulation in leaf               gN/m2/d O  *
*  RTNLV   R4  Positive value of RNLV                       gN/m2/d O  *
*  RNSO    R4  rate of N accumulation in seed(storage organ)gN/m2/d O  *
*----------------------------------------------------------------------*
      SUBROUTINE RNACC (FNSH,NUPT,RWST,STEMNC,LNCMIN,RNCMIN,LNC,RNC,
     $                  NLV,NRT,WLV,WRT,DELT,CB,CX,TM,DS,SEEDNC,
     $                  RWSO,LNLV,LNRT, RNRT,RNST,RNLV,RTNLV,RNSO)
Cf2py intent(out)rnrt,rnst,rnlv,rtnlv,rnso     

      IMPLICIT REAL (A-Z)
      SAVE

*---amount of N partitioned to shoot
      NSHN   = FNSH * NUPT

*---leaf N (NLVA) or root N (NRTA) available for remobilization
      NLVA   = INSW(LNCMIN-LNC, NLV-WLV*LNCMIN, 0.) / DELT
      NRTA   = INSW(RNCMIN-RNC, NRT-WRT*RNCMIN, 0.) / DELT
      NTA    = NLVA + NRTA

*---rate of N accumulation in stem
      RNST   = RWST * INSW(-NTA,STEMNC,0.)

*---expected N dynamics during seed(storage organ) filling
      CDS    = CB+(CX-CB)*(4.-TM-DS)/(2.-TM)*(DS-1.)**(1./(2.-TM))
      ENSNC  = LIMIT(CB,CX,CDS) * SEEDNC

*---rate of N accumulation in seed
      NGS    = NSHN - RNST - ENSNC*RWSO
      NONC   = MAX(0.,INSW(NTA+NGS,(NTA+NSHN-RNST)/NOTNUL(RWSO),ENSNC))
      RNSO   = RWSO*NONC

*---rate of N accumulation in leaf
      NLVN   = INSW(NTA+NGS,-NLVA-LNLV,-NLVA/NOTNUL(NTA)*(-NGS)-LNLV)
      GNLV   = INSW(NGS, NLVN, NSHN-RNST-RNSO-LNLV)
      RNLV   = MAX (-NLV+1.E-7, GNLV)
      RTNLV  = MAX(0., RNLV)

*---rate of N accumulation in root
      NRTN   = INSW(NTA+NGS, NUPT-NSHN-NRTA-LNRT,
     $         NUPT-NSHN-NRTA/NOTNUL(NTA)*(-NGS)-LNRT)
      GNRT   = INSW(NGS, NRTN, NUPT-NSHN-LNRT)
      RNRT   = MAX (-NRT+5.E-8, GNRT)

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE RLAIC                                                    *
*  Purpose: This subroutine calculates the daily increase of leaf      *
*           area index (m2 leaf/m2 ground/day).                        *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*                                                                      *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  DS      R4  Development stage                          -         I  *
*  SLA0    R4  Specific leaf area constant                m2 g-1    I  *
*  RWLV    R4  Rate of increment in leaf weight           g m-2 d-1 I  *
*  LAI     R4  Leaf area index                            m2 m-2    I  *
*  KN      R4  Leaf nitrogen extinction coefficient       m2 m-2    I  *
*  NLV     R4  Total leaf nitrogen content in a canopy    g m-2     I  *
*  RNLV    R4  Rate of increment in NLV                   g m-2 d-1 I  *
*  SLNB    R4  Nitrogen content of bottom leaves          g m-2     I  *
*  RSLNB   R4  Rate of increment in SLNB                  g m-2 d-1 I  *
*  RLAI    R4  Rate of increment in leaf area index       m2 m-2d-1 O  *
*----------------------------------------------------------------------*
      SUBROUTINE RLAIC(DS,SLA0,RWLV,LAI,KN,NLV,RNLV,SLNB,RSLNB, RLAI)
Cf2py intent(out) rlai      
      
      IMPLICIT REAL (A-Z)
      SAVE

*---rate of LAI driven by carbon supply
      RLAI   =  INSW(RWLV, MAX(-LAI+1.E-5,SLA0*RWLV), SLA0*RWLV)

*---rate of LAI driven by nitrogen during juvenile phase
      IF ((LAI.LT.1.) .AND. (DS.LT.0.5)) THEN
        RLAI  = (SLNB*RNLV-NLV*RSLNB)/SLNB/(SLNB+KN*NLV)
      ENDIF

      RETURN
      END



*----------------------------------------------------------------------*
*  SUBROUTINE BETAF                                                    *
*  Purpose: This subroutine calculates the dynamics of expected growth *
*           of sinks, based on the beta sigmoid growth equation        *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*                                                                      *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  DVR     R4  Development rate                            d-1      I  *
*  TE      R4  Stage at which sink growth stops            -        I  *
*  TX      R4  Stage at which sink growth rate is maximal  -        I  *
*  TI      R4  Stage of a day considered                   -        I  *
*  FD      R4  Relative expected growth of a sink at a day d-1      O  *
*----------------------------------------------------------------------*
      SUBROUTINE BETAF(DVR,TE,TX,TI, FD)
Cf2py intent(out) fd      

      IMPLICIT REAL (A-Z)
      SAVE

      FD   = DVR*(2.*TE-TX)*(TE-TI)/TE/(TE-TX)**2*(TI/TE)**(TX/(TE-TX))

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE SINKG                                                    *
*  Purpose: This subroutine calculates carbon demand for sink growth.  *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*                                                                      *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  DS      R4  Development stage                           -        I  *
*  SSG     R4  Stage at which sink growth starts           -        I  *
*  TOTC    R4  Total carbon in a sink at end of its growth g C/m2   I  *
*  YG      R4  Growth efficiency                           g C/g C  I  *
*  FD      R4  Relative expected growth of a sink at a day d-1      I  *
*  DCDR    R4  Shortfall of C demand in previous days      g C/m2   I  *
*  DCS     R4  Daily C supply for sink growth              g C/m2/d I  *
*  DELT    R4  Time step of integration                    d        I  *
*  DCDC    R4  C demand of the current day                 g C/m2/d O  *
*  DCD     R4  Daily C demand for sink growth              g C/m2/d O  *
*  FLWC    R4  Flow of current assimilated C to sink       g C/m2/d O  *
*----------------------------------------------------------------------*
      SUBROUTINE SINKG(DS,SSG,TOTC,YG,FD,DCDR,DCS,DELT, DCDC,DCD,FLWC)
Cf2py intent(out) dcdc,dcd,flwc      
      
      IMPLICIT REAL (A-Z)
      SAVE

*---expected demand for C of the current time step
      DCDC   = INSW (DS-SSG, 0., TOTC/YG*FD)

*---total demand for C at the time step considered
      DCD    = DCDC + MAX(0.,DCDR)/DELT

*---flow of current assimilated carbon to sink
      FLWC   = MIN(DCD, DCS)

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE ASTRO  (from the SUCROS model)                           *
*  Purpose: This subroutine calculates astronomic daylength,           *
*           diurnal radiation characteristics such as the daily        *
*           integral of sine of solar elevation and solar constant.    *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  DOY     R4  Daynumber (Jan 1st = 1)                       -      I  *
*  LAT     R4  Latitude of the site                        degree   I  *
*  INSP    R4  Inclination of sun angle for computing DDLP degree   I  *
*  SC      R4  Solar constant                             J m-2 s-1 O  *
*  SINLD   R4  Seasonal offset of sine of solar height       -      O  *
*  COSLD   R4  Amplitude of sine of solar height             -      O  *
*  DAYL    R4  Astronomic daylength (base = 0 degrees)       h      O  *
*  DDLP    R4  Photoperiodic daylength                       h      O  *
*  DSINBE  R4  Daily total of effective solar height       s d-1    O  *
*                                                                      *
*  FATAL ERROR CHECKS (execution terminated, message)                  *
*  condition: LAT > 67, LAT < -67                                      *
*                                                                      *
*  FILE usage : none                                                   *
*----------------------------------------------------------------------*
      SUBROUTINE ASTRO (DOY,LAT,INSP, SC,SINLD,COSLD,DAYL,DDLP,
     $                  DSINBE)
Cf2py intent(out) sc,sinld,cosld,dayl,ddlp,dsinbe     
     
      IMPLICIT REAL (A-Z)
      SAVE

*---PI and conversion factor from degrees to radians
      PI    = 3.141592654
      RAD   = PI/180.
*---check on input range of parameters
      IF (LAT.GT.67.)  STOP 'ERROR IN ASTRO: LAT> 67'
      IF (LAT.LT.-67.) STOP 'ERROR IN ASTRO: LAT>-67'

*---declination of the sun as function of daynumber (DOY)
      DEC   = -ASIN (SIN (23.45*RAD)*COS (2.*PI*(DOY+10.)/365.))

*---SINLD, COSLD and AOB are intermediate variables
      SINLD = SIN (RAD*LAT)*SIN (DEC)
      COSLD = COS (RAD*LAT)*COS (DEC)
      AOB   = SINLD/COSLD

*---daylength (DAYL)
      DAYL   = 12.0*(1.+2.*ASIN (AOB)/PI)
      DDLP   = 12.0*(1.+2.*ASIN((-SIN(INSP*RAD)+SINLD)/COSLD)/PI)

      DSINB  = 3600.*(DAYL*SINLD+24.*COSLD*SQRT (1.-AOB*AOB)/PI)
      DSINBE = 3600.*(DAYL*(SINLD+0.4*(SINLD*SINLD+COSLD*COSLD*0.5))+
     &         12.0*COSLD*(2.0+3.0*0.4*SINLD)*SQRT (1.-AOB*AOB)/PI)

*---solar constant (SC)
      SC     = 1367.*(1.+0.033*COS(2.*PI*(DOY-10.)/365.))

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE TOTPT                                                    *
*  Purpose: This subroutine calculates daily total gross photosynthesis*
*           and transpiration by performing a Gaussian integration     *
*           over time. At five different times of the day, temperature *
*           and radiation are computed to determine assimilation       *
*           and transpiration whereafter integration takes place.      *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*                                                                      *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  SC      R4  Solar constant                             J m-2 s-1 I  *
*  SINLD   R4  Seasonal offset of sine of solar height    -         I  *
*  COSLD   R4  Amplitude of sine of solar height          -         I  *
*  DAYL    R4  Astronomic daylength (base = 0 degrees)    h         I  *
*  DSINBE  R4  Daily total of effective solar height      s d-1     I  *
*  DDTR    R4  Daily global radiation                     J m-2 d-1 I  *
*  TMAX    R4  Daily maximum temperature                  oC        I  *
*  TMIN    R4  Daily minimum temperature                  oC        I  *
*  DVP     R4  Vapour pressure                            kPa       I  *
*  WNM     R4  daily average wind speed (>=0.1 m/s)       m s-1     I  *
*  C3C4    R4  Crop type (=1 for C3, -1 for C4 crops)     -         I  *
*  LAI     R4  (green)Leaf area index                     m2 m-2    I  *
*  TLAI    R4  Total Leaf area index                      m2 m-2    I  *
*  HT      R4  Plant height                               m         I  *
*  LWIDTH  R4  Leaf width                                 m         I  *
*  RD      R4  Rooting depth                              cm        I  *
*  SD1     R4  Depth of evaporative upper soil layer      cm        I  *
*  RSS     R4  Soil resistance,equivalent to leaf stomata s m-1     I  *
*  BLD     R4  Leaf angle from horizontal                 degree    I  *
*  KN      R4  Leaf nitrogen extinction coefficient       m2 m-2    I  *
*  KW      R4  Windspeed extinction coefficient in canopy m2 m-2    I  *
*  SLN     R4  Average leaf nitrogen content in canopy    g m-2     I  *
*  SLNT    R4  Top-leaf nitrogen content                  g m-2     I  *
*  SLNN    R4  Value of SLNT with small plant-N increment g m-2     I  *
*  SLNMIN  R4  Minimum or base SLNT for photosynthesis    g m-2     I  *
*  DWSUP   R4  Daily water supply for evapotranspiration  mm d-1    I  *
*  CO2A    R4  Ambient CO2 concentration                  ml m-3    I  *
*  LS      R4  Lodging severity                           -         I  *
*  EAJMAX  R4  Energy of activation for Jmax              J mol-1   I  *
*  XVN     R4  Slope of linearity between Vcmax & leaf N  umol/g/s  I  *
*  XJN     R4  Slope of linearity between Jmax & leaf N   umol/g/s  I  *
*  THETA   R4  Convexity for light response of e-transport   -      I  *
*  WCUL    R4  Water content of the upper soil layer      m3 m-3    I  *
*  FVPD    R4  Slope for linear effect of VPD on Ci/Ca    (kPa)-1   I  *
*  PPCAN   R4  Potential canopy CO2 assimilation          g m-2 d-1 O  *
*  APCANS  R4  Actual standing-canopy CO2 assimilation    g m-2 d-1 O  *
*  APCANN  R4  APCANS with small plant-N increment        g m-2 d-1 O  *
*  APCAN   R4  Actual canopy CO2 assimilation             g m-2 d-1 O  *
*  PTCAN   R4  Potential canopy transpiration             mm d-1    O  *
*  ATCAN   R4  Actual canopy transpiration                mm d-1    O  *
*  PESOIL  R4  Potential soil evaporation                 mm d-1    O  *
*  AESOIL  R4  Actual soil evaporation                    mm d-1    O  *
*  DIFS    R4  Daytime average soil-air temp. difference  oC        O  *
*  DIFSU   R4  Daytime aver. sunlit leaf-air temp. diff.  oC        O  *
*  DIFSH   R4  Daytime aver. shaded leaf-air temp. diff.  oC        O  *
*  DAPAR   R4  Daily PAR absorbed by crop canopy          J m-2 d-1 O  *
*----------------------------------------------------------------------*
      SUBROUTINE TOTPT(SC,SINLD,COSLD,DAYL,DSINBE,DDTR,TMAX,TMIN,DVP,
     $               WNM,C3C4,LAI,TLAI,HT,LWIDTH,RD,SD1,RSS,BLD,KN,KW,
     $               SLN,SLNT,SLNN,SLNMIN,DWSUP,CO2A,LS,EAJMAX,
     $               XVN,XJN,THETA,WCUL,FVPD, PPCAN,APCANS,APCANN,APCAN,
     $               PTCAN,ATCAN,PESOIL,AESOIL,DIFS,DIFSU,DIFSH,DAPAR)
Cf2py intent(out) ppcan,apcans,apcann,apcan,ptcan,atcan,pesoil,aesoil,difs,difsu,difsh,dapar
     
      IMPLICIT REAL(A-Z)

      REAL XGAUSS(5), WGAUSS(5)
      INTEGER I1, IGAUSS
      SAVE

*---Gauss weights for five point Gauss integration
      DATA IGAUSS /5/
      DATA XGAUSS /0.0469101,0.2307534,0.5      ,0.7692465,0.9530899/
      DATA WGAUSS /0.1184635,0.2393144,0.2844444,0.2393144,0.1184635/

      PI   = 3.141592654

*---output-variables set to zero and five different times of a day(HOUR)
      PPCAN  = 0.
      APCANS = 0.
      APCANN = 0.
      APCAN  = 0.
      PTCAN  = 0.
      ATCAN  = 0.
      PESOIL = 0.
      AESOIL = 0.
      DIFS   = 0.
      DIFSU  = 0.
      DIFSH  = 0.
      DAPAR  = 0.

      DO 10 I1=1,IGAUSS

*---timing for sunrise
      SUNRIS = 12. - 0.5*DAYL

*---specifying the time (HOUR) of a day
      HOUR = SUNRIS + DAYL*XGAUSS(I1)

*---sine of solar elevation
      SINB  = MAX (0., SINLD+COSLD*COS(2.*PI*(HOUR-12.)/24.))

*---daytime course of radiation
      DTR   = DDTR*(SINB*SC/1367.)/DSINBE

*---daytime course of air temperature
      DAYTMP= TMIN+(TMAX-TMIN)*SIN(PI*(HOUR+DAYL/2.-12.)/(DAYL+3.))

*---daytime course of water supply
      WSUP  = DWSUP*(SINB*SC/1367.)/DSINBE
      WSUP1 = WSUP*SD1/RD
*---daytime course of wind speed
      WND   = WNM         !no diurnal fluctuation is assumed here

*---total incoming PAR and NIR
      PAR   = 0.5*DTR
      NIR   = 0.5*DTR

*---diffuse light fraction (FRDF) from atmospheric transmission (ATMTR)
      ATMTR = PAR/(0.5*SC*SINB)

      IF (ATMTR.LE.0.22) THEN
         FRDF = 1.
      ELSE IF (ATMTR.GT.0.22 .AND. ATMTR.LE.0.35) THEN
         FRDF = 1.-6.4*(ATMTR-0.22)**2
      ELSE
         FRDF = 1.47-1.66*ATMTR
      ENDIF

      FRDF = MAX (FRDF, 0.15+0.85*(1.-EXP (-0.1/SINB)))

*---incoming diffuse PAR (PARDF) and direct PAR (PARDR)
      PARDF = PAR * FRDF
      PARDR = PAR - PARDF

*---incoming diffuse NIR (NIRDF) and direct NIR (NIRDR)
      NIRDF = NIR * FRDF
      NIRDR = NIR - NIRDF

*---extinction and reflection coefficients
      BL    = BLD*PI/180.     !leaf angle, conversion to radians
      CALL KBEAM (SINB,BL, KB)

      SCPPAR = 0.2            !leaf scattering coefficient for PAR
      SCPNIR = 0.8            !leaf scattering coefficient for NIR
      CALL KDIFF (TLAI,BL,SCPPAR, KDPPAR)
      CALL KDIFF (TLAI,BL,SCPNIR, KDPNIR)

      CALL REFL (SCPPAR,KB, KBPPAR,PCBPAR)
      CALL REFL (SCPNIR,KB, KBPNIR,PCBNIR)

      PCDPAR = 0.057          !canopy diffuse PAR reflection coefficient
      PCDNIR = 0.389          !canopy diffuse NIR reflection coefficient

*---turbulence resistance for canopy (RT) and for soil (RTS)
      RT     = 0.74*(LOG((2.-0.7*HT)/(0.1*HT)))**2/(0.4**2*WND)
      RTS    = 0.74*(LOG(56.))**2/(0.4**2*WND)

*---fraction of sunlit and shaded components in canopy
      FRSU   = 1./KB/LAI*(1.-EXP(-KB*LAI))
      FRSH   = 1.-FRSU

*---boundary layer resistance for canopy, sunlit and shaded leaves
      GBHLF  = 0.01*SQRT(WND/LWIDTH)
      GBHC   = (1.-EXP(- 0.5*KW    *LAI))/(0.5*KW   )*GBHLF
      GBHSU  = (1.-EXP(-(0.5*KW+KB)*LAI))/(0.5*KW+KB)*GBHLF
      GBHSH  = GBHC - GBHSU

      RBHSU  = 1./GBHSU   !boundary layer resistance to heat,sunlit part
      RBWSU  = 0.93*RBHSU !boundary layer resistance to H2O, sunlit part
      RBHSH  = 1./GBHSH   !boundary layer resistance to heat,shaded part
      RBWSH  = 0.93*RBHSH !boundary layer resistance to H2O, shaded part

*---boundary layer resistance for soil
      RBHS   = 172.*SQRT(0.05/MAX(0.1,WND*EXP(-KW*TLAI)))
      RBWS   = 0.93*RBHS

*---photosynthetically active nitrogen for sunlit and shaded leaves
      CALL PAN (SLNT,SLNMIN,LAI,KN,KB, NPSU,NPSH)

      CALL PAN (SLNN,SLNMIN,LAI,KN,KB, NPSUN,NPSHN)

*---absorbed PAR and NIR by sunlit leaves and shaded leaves
      CALL LIGAB (SCPPAR,KB,KBPPAR,KDPPAR,PCBPAR,PCDPAR,PARDR,PARDF,LAI,
     $            APARSU,APARSH)
      CALL LIGAB (SCPNIR,KB,KBPNIR,KDPNIR,PCBNIR,PCDNIR,NIRDR,NIRDF,LAI,
     $            ANIRSU,ANIRSH)
      APAR   = APARSU+APARSH

*---absorbed total radiation (PAR+NIR) by sunlit and shaded leaves
      ATRJSU = APARSU+ANIRSU
      ATRJSH = APARSH+ANIRSH

*---absorbed total radiation (PAR+NIR) by soil
      PSPAR  = 0.1                                  !soil PAR reflection
      PSNIR  = INSW(WCUL-0.5, 0.52-0.68*WCUL, 0.18) !soil NIR reflection
      ATRJS=(1.-PSPAR)*(PARDR*EXP(-KBPPAR*TLAI)+
     $      PARDF*EXP(-KDPPAR*TLAI))+(1.-PSNIR)
     $      *(NIRDR*EXP(-KBPNIR*TLAI)+NIRDF*EXP(-KDPNIR*TLAI))

*---instantaneous potential photosynthesis and transpiration
      CALL PPHTR(FRSU,DAYTMP,DVP,CO2A,C3C4,FVPD,APARSU,NPSU,RBWSU,RBHSU,
     $           RT*FRSU,ATRJSU,ATMTR,EAJMAX,XVN,XJN,THETA, PLFSU,
     $           PTSU,RSWSU,NRADSU,SLOPSU)
      CALL PPHTR(FRSH,DAYTMP,DVP,CO2A,C3C4,FVPD,APARSH,NPSH,RBWSH,RBHSH,
     $           RT*FRSH,ATRJSH,ATMTR,EAJMAX,XVN,XJN,THETA, PLFSH,
     $           PTSH,RSWSH,NRADSH,SLOPSH)
      IPP    = PLFSU+ PLFSH
      IPT    = PTSU + PTSH
      PT1    = IPT  * SD1/RD

*---instantaneous potential soil evaporation
      CALL PEVAP (DAYTMP,DVP,RSS,RTS,RBWS,RBHS,ATRJS,ATMTR,
     $            PT1,WSUP1, IPE,NRADS)

*---instantaneous actual soil evaporation, actual canopy
*   transpiration and photosynthesis
      IAE    = MIN (IPE,IPE/(PT1+IPE)*WSUP1           )
      IAT    = MIN (IPT,PT1/(PT1+IPE)*WSUP1+WSUP-WSUP1)
      ATSU   = PTSU/IPT*IAT
      ATSH   = PTSH/IPT*IAT

      CALL DIFLA (NRADS,IAE,RBHS,RTS, ADIFS)
      CALL APHTR (DAYTMP,APARSU,DVP,CO2A,C3C4,FVPD,NRADSU,ATSU,PTSU,
     $            RT*FRSU,RBHSU,RBWSU,RSWSU,SLOPSU,NPSU,NPSUN,
     $            EAJMAX,XVN,XJN,THETA, PASSU,PANSU,ADIFSU)
      CALL APHTR (DAYTMP,APARSH,DVP,CO2A,C3C4,FVPD,NRADSH,ATSH,PTSH,
     $            RT*FRSH,RBHSH,RBWSH,RSWSH,SLOPSH,NPSH,NPSHN,
     $            EAJMAX,XVN,XJN,THETA, PASSH,PANSH,ADIFSH)
      IAPS   = PASSU + PASSH
      IAPN   = PANSU + PANSH

*---canopy photosynthesis if there is lodging
      CALL ICO2 (DAYTMP+ADIFSU,DVP,FVPD,CO2A,C3C4, ASVP,ACO2I)
      CALL PHOTO(C3C4,(1.-SCPPAR)*PAR,DAYTMP+ADIFSU,ACO2I,
     $           SLN-SLNMIN,EAJMAX,XVN,XJN,THETA, IPPL,IRDL)
      ARSWSU = (PTSU-ATSU)*(SLOPSU*(RBHSU+RT*FRSU)+.067*(RBWSU+RT*FRSU))
     $         /ATSU/.067+PTSU/ATSU*RSWSU
      IAPL   = ((1.6*RSWSU+1.3*RBWSU+RT*FRSU)/(1.6*ARSWSU+1.3*RBWSU
     $         +RT*FRSU)*(IPPL-IRDL)+IRDL)*(1.-exp(-LAI))
      IAP    = MIN(IAPS, (1.-LS)*IAPS+LS*IAPL)
      IAPNN  = MIN(IAPN, (1.-LS)*IAPN+LS*IAPL*IAPN/IAPS)

*---integration of assimilation and transpiration to a daily total
      PPCAN  = PPCAN  + IPP   * WGAUSS(I1)
      APCANS = APCANS + IAPS  * WGAUSS(I1)
      APCANN = APCANN + IAPNN * WGAUSS(I1)
      APCAN  = APCAN  + IAP   * WGAUSS(I1)
      PTCAN  = PTCAN  + IPT   * WGAUSS(I1)
      ATCAN  = ATCAN  + IAT   * WGAUSS(I1)
      PESOIL = PESOIL + IPE   * WGAUSS(I1)
      AESOIL = AESOIL + IAE   * WGAUSS(I1)
      DIFS   = DIFS   + ADIFS * WGAUSS(I1)
      DIFSU  = DIFSU  + ADIFSU* WGAUSS(I1)
      DIFSH  = DIFSH  + ADIFSH* WGAUSS(I1)
      DAPAR  = DAPAR  + APAR  * WGAUSS(I1)

10    CONTINUE

      PPCAN  = PPCAN  * DAYL * 3600.
      APCANS = APCANS * DAYL * 3600.
      APCANN = APCANN * DAYL * 3600.
      APCAN  = APCAN  * DAYL * 3600.
      PTCAN  = PTCAN  * DAYL * 3600.
      ATCAN  = ATCAN  * DAYL * 3600.
      PESOIL = PESOIL * DAYL * 3600.
      AESOIL = AESOIL * DAYL * 3600.
      DIFS   = DIFS
      DIFSU  = DIFSU
      DIFSH  = DIFSH
      DAPAR  = DAPAR  * DAYL * 3600.

      RETURN
      END


*----------------------------------------------------------------------*
* SUBROUTINE PPHTR                                                     *
* Purpose: This subroutine calculates potential leaf photosynthesis    *
*          and transpiration.                                          *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  FRAC    R4  Fraction of leaf classes (sunlit vs shaded) -        I  *
*  DAYTMP  R4  Air temperature                            oC        I  *
*  DVP     R4  Vapour pressure                            kPa       I  *
*  CO2A    R4  Ambient CO2 concentration                  ml m-3    I  *
*  C3C4    R4  Crop type (=1. for C3, -1 for C4 crops)    -         I  *
*  FVPD    R4  Slope for linear effect of VPD on Ci/Ca    (kPa)-1   I  *
*  PAR     R4  Absorbed photosynth. active radiation      J m-2 s-1 I  *
*  NP      R4  Photosynthetically active N content        g m-2     I  *
*  RBW     R4  Leaf boundary layer resistance to water    s m-1     I  *
*  RBH     R4  Leaf boundary layer resistance to heat     s m-1     I  *
*  RT      R4  Turbulence resistance                      s m-1     I  *
*  ATRJ    R4  Absorbed global radiation                  J m-2 s-1 I  *
*  ATMTR   R4  Atmospheric transmissivity                 -         I  *
*  EAJMAX  R4  Energy of activation for Jmax              J mol-1   I  *
*  XVN     R4  Slope of linearity between Vcmax & leaf N  umol/g/s  I  *
*  XJN     R4  Slope of linearity between Jmax  & leaf N  umol/g/s  I  *
*  THETA   R4  Convexity for light response of e-transport   -      I  *
*  PLF     R4  Potential leaf photosynthesis              gCO2/m2/s O  *
*  PT      R4  Potential leaf transpiration               mm s-1    O  *
*  RSW     R4  Potential stomatal resistance to water     s m-1     O  *
*  NRADC   R4  Net leaf absorbed radiation                J m-2 s-1 O  *
*  SLOPEL  R4  Slope of saturated vapour pressure curve   kPa oC-1  O  *
*----------------------------------------------------------------------*
      SUBROUTINE PPHTR(FRAC,DAYTMP,DVP,CO2A,C3C4,FVPD,PAR,NP,RBW,RBH,RT,
     $      ATRJ,ATMTR,EAJMAX,XVN,XJN,THETA, PLF,PT,RSW,NRADC,SLOPEL)
Cf2py intent(out) plf,pt,rsw,nradc,slopel     
     
      IMPLICIT REAL (A-Z)

*---first-round calculation to determine leaf temperature
      CALL ICO2 (DAYTMP,DVP,FVPD,CO2A,C3C4, SVP,FCO2I)
      CALL PHOTO(C3C4,PAR,DAYTMP,FCO2I,NP,EAJMAX,XVN,XJN,THETA, FPLF,
     $           FLRD)

      VPD    = MAX (0., SVP- DVP)
      SLOPE  = 4158.6 * SVP/(DAYTMP + 239.)**2
      CALL GCRSW(FPLF,FLRD,DAYTMP,CO2A,FCO2I,RBW,RT, FRSW)
      CALL PTRAN(FRSW,RT,RBW,RBH,ATRJ,ATMTR,FRAC,DAYTMP,DVP,
     $           SLOPE,VPD, FPT,FNRADC)

      CALL DIFLA (FNRADC,FPT,RBH,RT, FDIF)

      TLEAF  = DAYTMP + FDIF

*---second-round calculation to determine potential photosynthesis
*   and transpiration
      CALL ICO2  (TLEAF,DVP,FVPD,CO2A,C3C4, SVPL,CO2I)
      CALL PHOTO (C3C4,PAR,TLEAF,CO2I,NP,EAJMAX,XVN,XJN,THETA, PLF,LRD)

      SLOPEL = (SVPL-SVP)/NOTNUL(TLEAF-DAYTMP)

      CALL GCRSW (PLF,LRD,TLEAF,CO2A,CO2I,RBW,RT, RSW)
      CALL PTRAN (RSW,RT,RBW,RBH,ATRJ,ATMTR,FRAC,TLEAF,DVP,
     $            SLOPEL,VPD, PT,NRADC)

      RETURN
      END


*----------------------------------------------------------------------*
* SUBROUTINE PEVAP                                                     *
* Purpose: This subroutine calculates potential soil evaporation.      *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  DAYTMP  R4  Air temperature                            oC        I  *
*  DVP     R4  Vapour pressure                            kPa       I  *
*  RSS     R4  Soil resistance,equivalent to leaf stomata s m-1     I  *
*  RTS     R4  Turbulence resistance for soil             s m-1     I  *
*  RBWS    R4  Soil boundary layer resistance to water    s m-1     I  *
*  RBHS    R4  Soil boundary layer resistance to heat     s m-1     I  *
*  ATRJS   R4  Absorbed global radiation by soil          J m-2 s-1 I  *
*  ATMTR   R4  Atmospheric transmissivity                 -         I  *
*  PT1     R4  Potential leaf transpiration using water   mm s-1    I  *
*              from upper evaporative soil layer                       *
*  WSUP1   R4  Water supply from upper evaporative soil   mm s-1    I  *
*              layer for evapotranspiration                            *
*  PESOIL  R4  Potential soil evaporation                 mm s-1    O  *
*  NRADS   R4  Net soil absorbed radiation                J m-2 s-1 O  *
*----------------------------------------------------------------------*
      SUBROUTINE PEVAP (DAYTMP,DVP,RSS,RTS,RBWS,RBHS,ATRJS,ATMTR,
     $                  PT1,WSUP1, PESOIL,NRADS)
Cf2py intent(out) pesoil,nrads
     
      IMPLICIT REAL (A-Z)

*--- first-round calculation to estimate soil surface temperature (TAVS)
      SVP    = 0.611*EXP(17.4*DAYTMP/(DAYTMP+239.))
      VPD    = MAX (0., SVP-DVP)
      SLOPE  = 4158.6 * SVP/(DAYTMP + 239.)**2
      CALL PTRAN(RSS,RTS,RBWS,RBHS,ATRJS,ATMTR,1.,DAYTMP,DVP,
     $           SLOPE,VPD, FPE,FNRADS)
      FPESOL = MAX(0., FPE)
      FAESOL = MIN(FPESOL,FPESOL/(PT1+FPESOL)*WSUP1)
      CALL DIFLA (FNRADS,FAESOL,RBHS,RTS, FDIFS)
      TAVS   = DAYTMP + FDIFS

*---second-round calculation to estimate potential soil evaporation
      SVPS   = 0.611*EXP(17.4*TAVS/(TAVS+239.))
      SLOPES = (SVPS-SVP)/NOTNUL(FDIFS)

      CALL PTRAN(RSS,RTS,RBWS,RBHS,ATRJS,ATMTR,1.,TAVS,DVP,
     $           SLOPES,VPD, PE,NRADS)
      PESOIL = MAX(0., PE)

      RETURN
      END
*----------------------------------------------------------------------*
* SUBROUTINE APHTR                                                     *
* Purpose: This subroutine calculates actual leaf photosynthesis when  *
*          water stress occurs.                                        *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  DAYTMP  R4  Air temperature                            oC        I  *
*  PAR     R4  Absorbed photosynth. active radiation      J m-2 s-1 I  *
*  DVP     R4  Vapour pressure                            kPa       I  *
*  CO2A    R4  Ambient CO2 concentration                  ml m-3    I  *
*  C3C4    R4  Crop type (=1. for C3, -1 for C4 crops)    -         I  *
*  FVPD    R4  Slope for linear effect of VPD on Ci/Ca    (kPa)-1   I  *
*  NRADC   R4  Net leaf absorbed radiation                J m-2 s-1 I  *
*  AT      R4  Actual leaf transpiration                  mm s-1    I  *
*  PT      R4  Potential leaf transpiration               mm s-1    I  *
*  RT      R4  Turbulence resistance                      s m-1     I  *
*  RBH     R4  Leaf boundary layer resistance to heat     s m-1     I  *
*  RBW     R4  Leaf boundary layer resistance to water    s m-1     I  *
*  RSW     R4  Potential stomatal resistance to water     s m-1     I  *
*  SLOPEL  R4  Slope of saturated vapour pressure curve   kPa oC-1  I  *
*  NP      R4  Photosynthet. active leaf N content        g m-2     I  *
*  NPN     R4  NP with small plant-N increment            g m-2     I  *
*  EAJMAX  R4  Energy of activation for Jmax              J mol-1   I  *
*  XVN     R4  Slope of linearity between Vcmax & leaf N  umol/g/s  I  *
*  XJN     R4  Slope of linearity between Jmax  & leaf N  umol/g/s  I  *
*  THETA   R4  Convexity for light response of e-transport   -      I  *
*  PLFAS   R4  Actual leaf photosynthesis                 gCO2/m2/s O  *
*  PLFAN   R4  PLFAS with small plant-N increment         gCO2/m2/s O  *
*  ADIF    R4  Actual leaf-air temperature difference     oC        O  *
*----------------------------------------------------------------------*
      SUBROUTINE APHTR(DAYTMP,PAR,DVP,CO2A,C3C4,FVPD,NRADC,AT,PT,RT,RBH,
     $  RBW,RSW,SLOPEL,NP,NPN,EAJMAX,XVN,XJN,THETA, PLFAS,PLFAN,ADIF)
Cf2py intent(out) plfas,plfan,adif
     
      IMPLICIT REAL (A-Z)

      PSYCH  = 0.067            !psychrometric constant (kPa/oC)

*---leaf temperature if water stress occurs
      CALL DIFLA (NRADC,AT,RBH,RT, ADIF)
      ATLEAF = DAYTMP + ADIF

*---stomatal resistance to water if water stress occurs
      ARSW=(PT-AT)*(SLOPEL*(RBH+RT)+PSYCH*(RBW+RT))/AT/PSYCH+PT/AT*RSW

*---potential photosynthesis at the new leaf temperature
      CALL ICO2 (ATLEAF,DVP,FVPD,CO2A,C3C4, SVPA,ACO2I)
      CALL PHOTO(C3C4,PAR,ATLEAF,ACO2I,NPN,EAJMAX,XVN,XJN,THETA,APLFN,
     $           ARDN)
      CALL PHOTO(C3C4,PAR,ATLEAF,ACO2I,NP,EAJMAX,XVN,XJN,THETA,APLF,
     $           ARD)

*---actual photosynthesis under water stress condition
      PLFAS=(1.6*RSW+1.3*RBW+RT)/(1.6*ARSW+1.3*RBW+RT)*(APLF-ARD)+ARD
      PLFAN=(1.6*RSW+1.3*RBW+RT)/(1.6*ARSW+1.3*RBW+RT)*(APLFN-ARDN)
     $         +ARDN

      RETURN
      END


*----------------------------------------------------------------------*
* SUBROUTINE PTRAN                                                     *
* Purpose: This subroutine calculates leaf transpiration, using the    *
*          Penman-Monteith equation                                    *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  RSW     R4  Potential stomatal resistance to water     s m-1     I  *
*  RT      R4  Turbulence resistance                      s m-1     I  *
*  RBW     R4  Leaf boundary layer resistance to water    s m-1     I  *
*  RBH     R4  Leaf boundary layer resistance to heat     s m-1     I  *
*  ATRJ    R4  Absorbed global radiation                  J m-2 s-1 I  *
*  ATMTR   R4  Atmospheric transmissivity                 -         I  *
*  FRAC    R4  Fraction of leaf classes (sunlit vs shaded)-         I  *
*  TLEAF   R4  Leaf temperature                           oC        I  *
*  DVP     R4  Vapour pressure                            kPa       I  *
*  SLOPE   R4  Slope of saturated vapour pressure curve   kPa oC-1  I  *
*  VPD     R4  Saturation vapour pressure deficit of air  kPa       I  *
*  PT      R4  Potential leaf transpiration               mm s-1    O  *
*  NRADC   R4  Net leaf absorbed radiation                J m-2 s-1 O  *
*----------------------------------------------------------------------*
      SUBROUTINE PTRAN (RSW,RT,RBW,RBH,ATRJ,
     $                ATMTR,FRAC,TLEAF,DVP,SLOPE,VPD, PT,NRADC)
Cf2py intent(out) pt,nradc
     
      IMPLICIT REAL (A-Z)

*---some physical constants
      BOLTZM = 5.668E-8         !Stefan-Boltzmann constant(J/m2/s/K4)
      LHVAP  = 2.4E6            !latent heat of water vaporization(J/kg)
      VHCA   = 1200.            !volumetric heat capacity (J/m3/oC)
      PSYCH  = 0.067            !psychrometric constant (kPa/oC)

*---net absorbed radiation
      CLEAR  = MAX(0., MIN(1., (ATMTR-0.25)/0.45))    !sky clearness
      BBRAD  = BOLTZM*(TLEAF +273.)**4
      RLWN   = BBRAD*(0.56-0.079*SQRT(DVP*10.))*(0.1+0.9*CLEAR)*FRAC
      NRADC  = ATRJ - RLWN

*---intermediate variable related to resistances
      PSR    = PSYCH*(RBW+RT+RSW)/(RBH+RT)

*---radiation-determined term
      PTR    = NRADC*SLOPE        /(SLOPE+PSR)/LHVAP

*---vapour pressure-determined term
      PTD    = (VHCA*VPD/(RBH+RT))/(SLOPE+PSR)/LHVAP

*---potential evaporation or transpiration
      PT     = MAX(1.E-10,PTR+PTD)

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE DIFLA                                                    *
*  Purpose: This subroutine calculates leaf(canopy)-air temperature    *
*           differential.                                              *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  NRADC   R4  Net leaf absorbed radiation                J m-2 s-1 I  *
*  PT      R4  Potential leaf transpiration               mm s-1    I  *
*  RBH     R4  Leaf boundary layer resistance to heat     s m-1     I  *
*  RT      R4  Turbulence resistance                      s m-1     I  *
*  DIF     R4  Leaf-air temperature difference            oC        O  *
*----------------------------------------------------------------------*
      SUBROUTINE DIFLA (NRADC,PT,RBH,RT, DIF)
Cf2py intent(out) dif      
      
      IMPLICIT REAL  (A-Z)
      SAVE

      LHVAP  = 2.4E6            !latent heat of water vaporization(J/kg)
      VHCA   = 1200.            !volumetric heat capacity (J/m3/oC)

      DIF    = LIMIT (-25., 25., (NRADC-LHVAP*PT)*(RBH+RT)/VHCA)

      RETURN
      END
*----------------------------------------------------------------------*
*  SUBROUTINE ICO2                                                     *
*  Purpose: This subroutine calculates the internal CO2 concentration  *
*           as affected by vapour pressure deficit.                    *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  TLEAF   R4  Leaf temperature                           oC        I  *
*  DVP     R4  Vapour pressure                            kPa       I  *
*  FVPD    R4  Slope for linear effect of VPDL on Ci/Ca   (kPa)-1   I  *
*  CO2A    R4  Ambient CO2 concentration                  ml m-3    I  *
*  C3C4    R4  Crop type (=1. for C3, -1 for C4 crops)    -         I  *
*  SVPL    R4  Saturated vapour pressure of leaf          kPa       O  *
*  CO2I    R4  intercellular CO2 concentration            ml m-3    O  *
*----------------------------------------------------------------------*
      SUBROUTINE ICO2  (TLEAF,DVP,FVPD,CO2A,C3C4, SVPL,CO2I)
Cf2py intent(out) svpl,co2i      
      
      IMPLICIT REAL (A-Z)
      SAVE

*---air-to-leaf vapour pressure deficit
      SVPL   = 0.611 * EXP(17.4 * TLEAF / (TLEAF + 239.))
      VPDL   = MAX  (0., SVPL - DVP)

*---Michaelis-Menten const. for CO2 at 25oC (umol/mol)
      KMC25  = INSW(C3C4, 650., 404.9) !greater KMC25 for C4 than C3

*---Michaelis-Menten const. for O2 at 25oC (mmol/mol)
      KMO25  = INSW(C3C4, 450., 278.4) !greater KMO25 for C4 than C3

*---CO2 compensation point in absence of dark respiration (GAMMAX)
      O2     = 210.    !oxygen concentration(mmol/mol)
      EAVCMX = 65330.  !energy of activation for Vcmx(J/mol)
      EAKMC  = 79430.  !energy of activation for KMC (J/mol)
      EAKMO  = 36380.  !energy of activation for KMO (J/mol)
      EARD   = 46390.  !energy of activation for dark respiration(J/mol)
      RDVX25 = 0.0089  !ratio of dark respiration to Vcmax at 25oC

      KMC    = KMC25*EXP((1./298.-1./(TLEAF+273.))*EAKMC/8.314)
      KMO    = KMO25*EXP((1./298.-1./(TLEAF+273.))*EAKMO/8.314)
      GAMMAX = 0.5*EXP(-3.3801+5220./(TLEAF+273.)/8.314)*O2*KMC/KMO

*---CO2 compensation point (GAMMA)
      RDVCX =RDVX25*EXP((1./298.-1./(TLEAF+273.))*(EARD-EAVCMX)/8.314)
      GAMMA0=(GAMMAX+RDVCX*KMC*(1.+O2/KMO))/(1.-RDVCX)
      GAMMA =INSW (C3C4, GAMMA0/10., GAMMA0)

*---internal/ambient CO2 ratio, based on data of Morison & Gifford (1983)
      RCICA  = 1.-(1.-GAMMA/CO2A)*(0.14+FVPD*VPDL)

*---intercellular CO2 concentration
      CO2I   = RCICA * CO2A

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE GCRSW                                                    *
*  Purpose: This subroutine calculates overall leaf conductance        *
*           for CO2 (GC) and the stomatal resistance to water (RSW).   *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  PLEAF   R4  Gross leaf photosynthesis                  gCO2/m2/s I  *
*  RDLEAF  R4  Leaf dark respiration                      gCO2/m2/s I  *
*  TLEAF   R4  Leaf temperature                           oC        I  *
*  CO2A    R4  Ambient CO2 concentration                  ml m-3    I  *
*  CO2I    R4  Internal CO2 concentration                 ml m-3    I  *
*  RT      R4  Turbulence resistance                      s m-1     I  *
*  RBW     R4  Leaf boundary layer resistance to water    s m-1     I  *
*  RSW     R4  Potential stomatal resistance to water     s m-1     O  *
*----------------------------------------------------------------------*
      SUBROUTINE GCRSW (PLEAF,RDLEAF,TLEAF,CO2A,CO2I,RBW,RT, RSW)
Cf2py intent(out) rsw      
      
      IMPLICIT REAL (A-Z)
      SAVE

*---potential conductance for CO2
      GC  = (PLEAF-RDLEAF)*(273.+TLEAF)/0.53717/(CO2A-CO2I)

*---potential stomatal resistance to water
      RSW = MAX(1E-10, 1./GC - RBW*1.3 - RT)/1.6

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE PAN                                                      *
*  Purpose: This subroutine calculates photosynthetically active       *
*           nitrogen content for sunlit and shaded parts of canopy.    *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  SLNT    R4  Top-leaf nitrogen content                  g m-2     I  *
*  SLNMIN  R4  Minimum or base SLNT for photosynthesis    g m-2     I  *
*  LAI     R4  (green)Leaf area index                     m2 m-2    I  *
*  KN      R4  Leaf nitrogen extinction coefficient       m2 m-2    I  *
*  KB      R4  Direct beam radiation extinction coeff.    m2 m-2    I  *
*  NPSU    R4  Photosynthet. active N for sunlit leaves   g m-2     O  *
*  NPSH    R4  Photosynthet. active N for shaded leaves   g m-2     O  *
*----------------------------------------------------------------------*
      SUBROUTINE PAN(SLNT,SLNMIN,LAI,KN,KB, NPSU,NPSH)
Cf2py intent(out) npsu,npsh      
      
      IMPLICIT REAL (A-Z)
      SAVE

*---total photosynthetic nitrogen in canopy
      NPC   = SLNT*(1.-EXP(-KN*LAI))/KN-SLNMIN*LAI

*---photosynthetic nitrogen for sunlit and shaded parts of canopy
      NPSU  = SLNT*(1.-EXP(-(KN+KB)*LAI))/(KN+KB)
     $        -SLNMIN*(1.-EXP(-KB*LAI))/KB
      NPSH  = NPC-NPSU

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE PHOTO                                                    *
*  Purpose: This subroutine calculates leaf photosynthesis and dark    *
*           respiration, based on a renewed Farquhar biochemistry      *
*           (cf Yin et al.2004. Plant, Cell & Environment 27:1211-1222)*
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  C3C4    R4  Crop type (=1. for C3, -1. for C4 crops)   -         I  *
*  PAR     R4  Leaf absorbed photosynth. active radiance  J m-2 s-1 I  *
*  TLEAF   R4  Leaf temperature                           oC        I  *
*  CO2I    R4  Intercellular CO2 concentration            ml m-3    I  *
*  NP      R4  Photosynthetically active leaf N content   g m-2     I  *
*  EAJMAX  R4  Energy of activation for Jmax              J mol-1   I  *
*  XVN     R4  Slope of linearity between Vcmax & leaf N  umol/g/s  I  *
*  XJN     R4  Slope of linearity between Jmax  & leaf N  umol/g/s  I  *
*  THETA   R4  Convexity for light response of e-transport   -      I  *
*  PLEAF   R4  Gross leaf photosynthesis                  gCO2/m2/s O  *
*  RDLEAF  R4  Leaf dark respiration                      gCO2/m2/s O  *
*----------------------------------------------------------------------*
      SUBROUTINE PHOTO(C3C4,PAR,TLEAF,CO2I,NP,EAJMAX,XVN,XJN,
     $                 THETA, PLEAF,RDLEAF)
Cf2py intent(out) pleaf,rdleaf     
     
      IMPLICIT REAL (A-Z)
      SAVE

*---Michaelis-Menten constants for CO2 and O2 at 25oC
      IF (C3C4.LT.0.) THEN
        KMC25  = 650.   !greater KMC25 for C4 than C3; unit:(umol/mol)
        KMO25  = 450.   !greater KMO25 for C4 than C3; unit:(mmol/mol)
      ELSE
        KMC25  = 404.9  !unit:(umol/mol)
        KMO25  = 278.4  !unit:(mmol/mol)
      ENDIF

*---other constants related to the Farquhar-type photosynthesis model
      O2     = 210.    !oxygen concentration(mmol/mol)
      EAVCMX = 65330.  !energy of activation for Vcmx(J/mol)
      EAKMC  = 79430.  !energy of activation for KMC (J/mol)
      EAKMO  = 36380.  !energy of activation for KMO (J/mol)
      EARD   = 46390.  !energy of activation for dark respiration(J/mol)
      DEJMAX = 200000. !energy of deactivation for JMAX (J/mol)
      SJ     = 650.    !entropy term in JT equation (J/mol/K)
      PHI2M  = 0.85    !maximum electron transport efficiency of PS II
      HH     = 3.      !number of protons required to synthesise 1 ATP

*---PAR photon flux in umol/m2/s absorbed by leaf photo-systems
      UPAR   = 4.56*PAR !4.56 conversion factor in umol/J

*---Michaelis-Menten constants for CO2 and O2 respectively
      KMC    = KMC25*EXP((1./298.-1./(TLEAF+273.))*EAKMC/8.314)
      KMO    = KMO25*EXP((1./298.-1./(TLEAF+273.))*EAKMO/8.314)

*---CO2 compensation point in the absence of dark respiration
      GAMMAX = 0.5*EXP(-3.3801+5220./(TLEAF+273.)/8.314)*O2*KMC/KMO

*---Arrhenius function for the effect of temperature on carboxylation
      VCT    =    EXP((1./298.-1./(TLEAF+273.))*EAVCMX/8.314)

*---function for the effect of temperature on electron transport
      JT     =    EXP((1./298.-1./(TLEAF+273.))*EAJMAX/8.314)*
     $        (1.+EXP(SJ/8.314-DEJMAX/298./8.314))/
     $        (1.+EXP(SJ/8.314-1./(TLEAF+273.) *DEJMAX/8.314))

*---maximum rates of carboxylation(VCMX) and of electron transport(JMAX)
      VCMX   = XVN*VCT*NP
      JMAX   = XJN*JT *NP

*---CO2 concentration at carboxylation site & electron pathways and
*   their stoichiometries
      FPSEUD = 0.           !assuming no pseudocyclic e- transport
      IF (C3C4.LT.0.) THEN
        ZZ   = 0.2          !CO2 leakage from bundle-sheath to mesophyll
        CC   = 10.*CO2I     !to mimic C4 CO2 concentrating mechanism
        SF   = 2.*(CC-GAMMAX)/(1.-ZZ)
        FQ   = 1.- FPSEUD- 2.*(4.*CC+8.*GAMMAX)/HH/(SF+3.*CC+7.*GAMMAX)
        FCYC = FQ
      ELSE
        CC   = CO2I
        SF   = 0.
        FQ   = 0.
        FCYC = 1.-(FPSEUD*HH*(SF+3.*CC+7.*GAMMAX)/(4.*CC+8.*GAMMAX)+1.)/
     $                   (HH*(SF+3.*CC+7.*GAMMAX)/(4.*CC+8.*GAMMAX)-1.)
      ENDIF

*--- electron transport rate in dependence on PAR photon flux
      ALPHA2 = (1.-FCYC)/(1.+(1.-FCYC)/PHI2M)
      X      = ALPHA2*UPAR/MAX(1.E-10,JMAX)
      J2     = JMAX*(1+X-((1+X)**2-4.*X*THETA)**0.5)/2./THETA

*---rates of carboxylation limited by Rubisco and electron transport
      VC     = VCMX * CC/(CC + KMC*(O2/KMO+1.))
      VJ     =   J2 * CC*(2.+FQ-FCYC)/HH/(SF+3.*CC+7.*GAMMAX)/(1.-FCYC)

*---gross rate of leaf photosynthesis
      ALF    = (1.-GAMMAX/CC)*MIN(VC,VJ)
      PLEAF  = MAX(1.E-10, (1.E-6)*44.*ALF)

*---rate of leaf dark respiration
      RDVX25 = 0.0089      !ratio of dark respiration to Vcmax at 25oC
      RDT    = EXP((1./298.-1./(TLEAF+273.))*EARD/8.314)
      RDLEAF = (1.E-6)*44. *RDVX25*(XVN*NP) * RDT

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE REFL                                                     *
*  Purpose: This subroutine calculates reflection coefficients.        *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  SCP     R4  Leaf scattering coefficient                -         I  *
*  KB      R4  Direct beam radiation extinction coeff.    m2 m-2    I  *
*  KBP     R4  Scattered beam radiation extinction coeff. m2 m-2    O  *
*  PCB     R4  Canopy beam radiation reflection coeff.    -         O  *
*----------------------------------------------------------------------*
      SUBROUTINE REFL (SCP,KB, KBP,PCB)
Cf2py intent(out) kbp,pcb      
      
      IMPLICIT REAL (A-Z)
      SAVE

*--- scattered beam radiation extinction coefficient
      KBP    = KB*SQRT(1.-SCP)

*---canopy reflection coefficient for horizontal leaves
      PH     = (1.-SQRT(1.-SCP))/(1.+SQRT(1.-SCP))

*---Canopy beam radiation reflection coefficient
      PCB    = 1.-EXP(-2.*PH*KB/(1.+KB))

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE LIGAB                                                    *
*  Purpose: This subroutine calculates absorbed light for sunlit and   *
*           shaded leaves.                                             *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  SCP     R4  Leaf scattering coefficient                -         I  *
*  KB      R4  Direct beam radiation extinction coeff.    m2 m-2    I  *
*  KBP     R4  Scattered beam radiation extinction coeff. m2 m-2    I  *
*  KDP     R4  Diffuse radiation extinction coefficient   m2 m-2    I  *
*  PCB     R4  Canopy beam radiation reflection coeff.    -         I  *
*  PCD     R4  Canopy diffuse radiation reflection coeff. -         I  *
*  IB0     R4  Incident direct-beam radiation             J m-2 s-1 I  *
*  ID0     R4  Incident diffuse radiation                 J m-2 s-1 I  *
*  LAI     R4  (green)Leaf area index                     m2 m-2    I  *
*  ISU     R4  Absorbed radiation by sunlit leaves        J m-2 s-1 O  *
*  ISH     R4  Absorbed radiation by shaded leaves        J m-2 s-1 O  *
*----------------------------------------------------------------------*
      SUBROUTINE LIGAB (SCP,KB,KBP,KDP,PCB,PCD,IB0,ID0,LAI, ISU,ISH)
Cf2py intent(out) isu,ish      
      
      IMPLICIT REAL (A-Z)
      SAVE

*---total absorbed light by canopy
      IC     = (1.-PCB)*IB0*(1.-EXP(-KBP*LAI))+
     $         (1.-PCD)*ID0*(1.-EXP(-KDP*LAI))

*---absorbed light by sunlit and shaded fractions of canopy
      ISU    = (1.-SCP)*IB0*(1.-EXP(-KB *LAI))+(1.-PCD)*ID0/(KDP+KB)*
     $       KDP*(1.-EXP(-(KDP+KB)*LAI))+IB0*((1.-PCB)/(KBP+KB)*KBP*
     $       (1.-EXP(-(KBP+KB)*LAI))-(1.-SCP)*(1.-EXP(-2.*KB*LAI))/2.)
      ISH    = IC-ISU

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE KBEAM                                                    *
*  Purpose: This subroutine calculates extinction coefficient for      *
*           direct beam radiation.                                     *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  SINB    R4  Sine of solar elevation                    -         I  *
*  BL      R4  Leaf angle (from horizontal)               radians   I  *
*  KB      R4  Direct beam radiation extinction coeff.    m2 m-2    O  *
*----------------------------------------------------------------------*
      SUBROUTINE KBEAM (SINB,BL, KB)
Cf2py intent(out) kb      
      
      IMPLICIT REAL (A-Z)
      SAVE

*---solar elevation in radians
      B      = ASIN(SINB)

*---average projection of leaves in the direction of a solar beam
      IF (SINB.GE.SIN(BL)) THEN
          OAV = SINB*COS(BL)
      ELSE
          OAV = 2./3.141592654*(SINB*COS(BL)*ASIN(TAN(B)/TAN(BL))
     $         +((SIN(BL))**2-SINB**2)**0.5)
      ENDIF

*---beam radiation extinction coefficient
      KB     = OAV/SINB

      RETURN
      END


*----------------------------------------------------------------------*
*  SUBROUTINE KDIFF                                                    *
*  Purpose: This subroutine calculates extinction coefficient for      *
*           diffuse radiation.                                         *
*                                                                      *
*  FORMAL PARAMETERS:  (I=input,O=output,C=control,IN=init,T=time)     *
*  name   type meaning                                    units  class *
*  ----   ---- -------                                    -----  ----- *
*  LAI     R4  Total leaf area index                      m2 m-2    I  *
*  BL      R4  Leaf angle (from horizontal)               radians   I  *
*  SCP     R4  Leaf scattering coefficient                -         I  *
*  KDP     R4  Diffuse radiation extinction coefficient   m2 m-2    O  *
*----------------------------------------------------------------------*
      SUBROUTINE KDIFF (LAI,BL,SCP, KDP)
Cf2py intent(out) kdp      
      
      IMPLICIT REAL (A-Z)
      SAVE

      PI    = 3.141592654

*---extinction coefficient of beam lights from 15, 45 and 75o elevations
      CALL KBEAM (SIN(15.*PI/180.),BL, KB15)
      CALL KBEAM (SIN(45.*PI/180.),BL, KB45)
      CALL KBEAM (SIN(75.*PI/180.),BL, KB75)

*---diffuse light extinction coefficient
      KDP   = -1./LAI*LOG(0.178*EXP(-KB15*(1.-SCP)**0.5*LAI)
     $                   +0.514*EXP(-KB45*(1.-SCP)**0.5*LAI)
     $                   +0.308*EXP(-KB75*(1.-SCP)**0.5*LAI))

      RETURN
      END
      
*----------------------------------------------------------------------*
* FUNCTION INSW                                                        *
* Input switch. Y is set equal to Y1 or Y2 depending on the value of X *
*----------------------------------------------------------------------*
      REAL FUNCTION INSW(X,Y1,Y2)
      IMPLICIT REAL (A-Z)
      SAVE
      
      IF (X.LT.0.0) THEN
        INSW = Y1
      ELSE 
        INSW = Y2
      END IF
      RETURN
      END 

*----------------------------------------------------------------------*
* FUNCTION LIMIT                                                       *
* Y is equal to X but limited by Xl and XH                             *
*----------------------------------------------------------------------*
      REAL FUNCTION LIMIT(XL,XH,X)
      IMPLICIT REAL (A-Z)
      SAVE
      
      IF (X.LT.XL) THEN
        LIMIT = XL
      ELSE
        IF (X.GT.XH) THEN
          LIMIT = XH
        ELSE 
          LIMIT = X
        END IF
      END IF
      RETURN
      END 
*----------------------------------------------------------------------*
* FUNCTION NOTNUL                                                      *
*  Return 1 if X=0 otherwise return X                                  *
*----------------------------------------------------------------------*
      REAL FUNCTION NOTNUL(X)
      
      IMPLICIT REAL (A-Z)
      SAVE
      
      IF (X.EQ.0.0) THEN 
        NOTNUL=1.0
      ELSE
        NOTNUL=X
      END IF
      RETURN
      END
*----------------------------------------------------------------------*
*      FUNCTION TK(TCELSIUS)                                           *
* Converts Celsius temperature to Kelvin.                              *
*----------------------------------------------------------------------*
      FUNCTION TK(TCELSIUS)
      
      REAL ABSZERO
      PARAMETER(ABSZERO = -273.15)
      
      TK = TCELSIUS - ABSZERO
      RETURN
      END       
      
*----------------------------------------------------------------------*
* FUNCTION REAAND                                                       *
*  Return 1 if Y1>0 AND Y2>0 otherwise return 0                        *
*----------------------------------------------------------------------*
      REAL FUNCTION REAAND(Y1,Y2)
      
      IMPLICIT REAL (A-Z)
      SAVE
      
      IF ((Y1.GT.0.0) .AND. (Y2.GT.0.0)) THEN
      	  REAAND=1.0
      ELSE
          REAAND=0.0
      END IF
      RETURN
      END      

*----------------------------------------------------------------------*
* FUNCTION FCNSW                                                       *
*  Return Y1 if X<0, Y2 IF X==0, Y3 IF X>0                             *
*----------------------------------------------------------------------*
      REAL FUNCTION FCNSW(X,Y1,Y2,Y3)
      
      IMPLICIT REAL (A-Z)
      SAVE
      
      IF (X.LT.0.0) THEN 
        FCNSW=Y1
      ELSE
        IF (X.EQ.0.0) THEN
          FCNSW=Y2
        ELSE
        	IF (X.GT.0.0) THEN
        	  FCNSW=Y3
        	ENDIF
        ENDIF
      END IF
      RETURN
      END

*----------------------------------------------------------------------*
* FUNCTION REANOR                                                      *
*  Return 1 if X1<=0 AND X2<=0, RETURN 0 OTHERWISE                     *
*----------------------------------------------------------------------*
      REAL FUNCTION REANOR(X1,X2)
      
      IMPLICIT REAL (A-Z)
      SAVE
      
      IF ((X1.LE.0.0) .AND. (X2.LE.0.0)) THEN
          REANOR=1.0
      ELSE
          REANOR=0.0
      END IF
      RETURN
      END



