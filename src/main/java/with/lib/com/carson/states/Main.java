package with.lib.com.carson.states;


import lib.com.carson.Database;
import lib.com.carson.Pair;
import lib.com.carson.Path;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

import static with.lib.com.carson.states.State.*;
import static lib.com.carson.AdvancedManipulator.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Database<State> db = genDB();
        Path<State> path = Path.goFrom(db,VA,CA);
        printNormalMap(db,path,"../map","final");
    }

    private static Database<State> genDB(){
        return new Database<State>(State.class) {
            @Override
            public void initializeCords() {
                cords.put(State.AL, new Pair<>(1292, 842));
                cords.put(State.AK, new Pair<>(39,64));
                cords.put(State.AZ, new Pair<>(363, 749));
                cords.put(State.AR, new Pair<>(1074, 779));
                cords.put(State.CA, new Pair<>(105, 575));
                cords.put(State.CO, new Pair<>(608, 570));
                cords.put(State.CT, new Pair<>(1722, 375));
                cords.put(State.DE, new Pair<>(1659, 523));
                cords.put(State.DC, new Pair<>(1590, 535));//1597, 529
                cords.put(State.FL, new Pair<>(1499, 988));
                cords.put(State.GA, new Pair<>(1423, 845));
                cords.put(State.HI, new Pair<>(61,904));
                cords.put(State.ID, new Pair<>(353, 316));
                cords.put(State.IL, new Pair<>(1182, 535));
                cords.put(State.IN, new Pair<>(1285, 524));
                cords.put(State.IA, new Pair<>(1033, 442));
                cords.put(State.KS, new Pair<>(861, 609));
                cords.put(State.KY, new Pair<>(1339, 632));
                cords.put(State.LA, new Pair<>(1082, 940));
                cords.put(State.ME, new Pair<>(1789, 185));
                cords.put(State.MD, new Pair<>(1605, 506));
                cords.put(State.MA, new Pair<>(1743, 339));
                cords.put(State.MI, new Pair<>(1318, 374));
                cords.put(State.MN, new Pair<>(996, 276));
                cords.put(State.MS, new Pair<>(1189, 865));
                cords.put(State.MO,new Pair<>(1070,622));
                cords.put(State.MT,new Pair<>(536,189));
                cords.put(State.NE,new Pair<>(823,467));
                cords.put(State.NV,new Pair<>(239,494));
                cords.put(State.NH,new Pair<>(1733,282));
                cords.put(State.NJ,new Pair<>(1680,456));
                cords.put(State.NM,new Pair<>(578,774));
                cords.put(State.NY,new Pair<>(1631,345));
                cords.put(State.NC,new Pair<>(1564,686));
                cords.put(State.ND,new Pair<>(814,206));
                cords.put(State.OH,new Pair<>(1398,508));
                cords.put(State.OK,new Pair<>(905,750));
                cords.put(State.OR,new Pair<>(169,268));
                cords.put(State.PA,new Pair<>(1567,448));
                cords.put(State.RI,new Pair<>(1759,365));
                cords.put(State.SC,new Pair<>(1518,776));
                cords.put(State.SD,new Pair<>(806,334));
                cords.put(State.TN,new Pair<>(1301,712));
                cords.put(State.TX,new Pair<>(849,944));
                cords.put(State.UT,new Pair<>(409,545));
                cords.put(State.VT,new Pair<>(1695,268));
                cords.put(State.VA,new Pair<>(1584,591));
                cords.put(State.WA,new Pair<>(213,118));
                cords.put(State.WV,new Pair<>(1480,568));
                cords.put(State.WI,new Pair<>(1144,332));
                cords.put(State.WY,new Pair<>(579,380));

                //tweaks
                for(State t : cords.keySet()){
                    Pair<Integer,Integer> cord = cords.get(t);
                    cord = new Pair<>(cord.a,cord.b-30);
                    cords.replace(t,cord);
                }
            }


            @Override
            public void initializeConnections() {
                //all border connections between states

                //WA
                addConnection(WA,OR,273);
                addConnection(WA,ID,384);

                //OR
                addConnection(OR,ID,290);
                addConnection(OR,CA,489);
                addConnection(OR,NV,407);

                //ID
                addConnection(ID,MT,287);
                addConnection(ID,WY,379);
                addConnection(ID,UT,378);
                addConnection(ID,NV,374);

                //CA
                addConnection(CA,NV,215);
                addConnection(CA,AZ,505);

                //NV
                addConnection(NV,UT,288);
                addConnection(NV,AZ,442);

                //MT
                addConnection(MT,WY,302);
                addConnection(MT,ND,441);
                addConnection(MT,SD,545);


                //WY
                addConnection(WY,UT,326);
                addConnection(WY,NE,393);
                addConnection(WY,CO,256);
                addConnection(WY,SD, 375);


                //UT
                addConnection(UT,AZ,364);
                addConnection(UT,CO,284);
                addConnection(UT,NM, 439);

                //AZ
                addConnection(AZ,NM,300);


                //ND
                addConnection(ND,SD,253);
                addConnection(ND,MN,302);

                //SD
                addConnection(SD,MN,317);
                addConnection(SD,IA,373);
                addConnection(SD,NE,171);

                //NM
                addConnection(NM,TX,387);
                addConnection(NM,OK,579);
                addConnection(NM,CO,347);

                //CO
                addConnection(CO,OK,569);
                addConnection(CO,NE,337);
                addConnection(CO,KS,392);


                //OK
                addConnection(OK,TX,400);
                addConnection(OK,AR,253);
                addConnection(OK,KS,209);
                addConnection(OK,MO,240);



                //extra stuff
                addConnection(MN,WI,351);
                addConnection(MN,IL,497);
                addConnection(IL,IN,173);
                addConnection(IN,MI,529);
                addConnection(IN,OH,515);
                addConnection(OH,PA,303);
                addConnection(PA,NY,169);
                addConnection(AR,LA,410);
                addConnection(AR,MS,241);
                addConnection(MS,AL,145);
                addConnection(AL,GA,234);
                addConnection(GA,FL,322);
                addConnection(GA,SC,153);
                addConnection(SC,NC,180);
                addConnection(NC,VA,119);
                addConnection(VA,WV,126);
                addConnection(NC,TN,425);
                addConnection(TN,KY,205);
                addConnection(VA,DC,134);
                addConnection(VA,MD,156);
                addConnection(PA,NJ,166);
                addConnection(MD,DE,60);
                addConnection(NY,CT,77);
                addConnection(CT,RI,83);
                addConnection(CT,MA,103);
                addConnection(MA,NH,55);
                addConnection(NH,VT,106);
                addConnection(NH,ME,177);
                addConnection(MD,PA,152);



                //to offshore states
                addConnection(HI,CA,2300);
                addConnection(AK,WA,1565);
            }

            @Override
            public void initializeAdjustments() {
                adjust(OK, 0, -35);
                adjust(TN, -50, 0);
                adjust(MS, 0, -20);
                adjust(GA, -20,-10);
                adjust(SC, 10, 0);
                adjust(OH, 0, -20);
                adjust(PA, -40, 0);
                adjust(ME, -30, -5);
                adjust(VT, -30, 0);
                adjust(NH, 0, 20);
                adjust(MA, 0, 10);
                adjust(RI,0,15);
                adjust(CT,0,30);
                adjust(SD,0,-10);
                adjust(CO,0,-20);
                adjust(UT,20,0);
                adjust(NV,20,5);
                adjust(CA,-60,15);
                adjust(ID,-15,-10);
                adjust(MI,-25,0);
                adjust(DC,0,20);
            }
        };
    }

}
