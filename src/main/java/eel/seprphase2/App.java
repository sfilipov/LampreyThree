package eel.seprphase2;

import eel.seprphase2.Simulator.FailureModel;
import eel.seprphase2.Simulator.PhysicalModel;
import eel.seprphase2.Simulator.Reactor;
import eel.seprphase2.Simulator.Simulator;
import eel.seprphase2.TextInterface.DoNotStep;
import eel.seprphase2.TextInterface.TerminalReader;
import eel.seprphase2.TextInterface.TerminalRenderer;
import eel.seprphase2.TextInterface.TextInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App {

    /**
     * The main entry point for the application
     *
     * @param args
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                Game game = new Game();
            } catch (GameOverException e) {
                displayMushroomCloud();
                System.out.println(e.getMessage());
                (new BufferedReader(new InputStreamReader(System.in))).readLine();
            }
        }
    }

    public static void displayMushroomCloud() {
        System.out.println("                           ---   .\n" +
                           "                . ...  ... m *m++--+....\n" +
                           "           . .. .m +-+-..m.+mm+..*+m-#mm--. .\n" +
                           "           ..+%.*--%mm#-%##*#######-%+m-m+%m++.\n" +
                           "     - - -+.-.-.#*########*###*%*########%*-*-.-.\n" +
                           "   . +*-.+m #*#+*-####%#%#-#mm#-*####%*#####m*.-...\n" +
                           "  ..--+.*###m##*##+######%-#+##mm#m-#%#%*m#####+m-  .\n" +
                           "  +--%##########*#%##########*###%-#*##-*%m## ##%%..\n" +
                           " m+%####%%%*m**#%m############*##+#*####--%%###+m#- ...\n" +
                           "--#%#%## ##+m#########*#m####%####m###*#%++-*##*%#++% .\n" +
                           ".++#*##%m###%+###m*####m%*#%#+####%+#####%.-+#*#--#*# + .\n" +
                           "%-##%####.++-%#**##m#**#-+-m*++m######*#%.-m+.m#+m%-#. .-\n" +
                           "%####*#m.m-#m-%#########m-##+#m%#########%.-*m+*-m---+++--\n" +
                           "*#%####m%%.-.#######%######*#########*#m*+m%m+#%m-.+.#m. .\n" +
                           "%##mm####%%##m+%*#*+##m##*###%.#*####-#m.#%+**%..  ---#%m\n" +
                           "m##- m#+%#m+################**###########+**%+-+  -.*#-#+.-\n" +
                           "-*###*%*%+m+-#%##############*#**#m+*m%%-.%..+-.  --m%%#-.\n" +
                           "- -+.m*#####*%##############%###**%m++.#*%#--+.  . #-#*# -\n" +
                           " - + -m*-+..%m###############%######%#m*m*m##+   . # .m+\n" +
                           " . +.- #-+ m.**+##m*%m**####*##%m+#%--%--  *-     .+. .-.\n" +
                           "                   + ###########+##*+.\n" +
                           "                    +mm#####*##*+##mm\n" +
                           "                     +######%#%+%#*--\n" +
                           "                     - #######*+### +\n" +
                           "                    .%###########m--\n" +
                           "                    -m*#####m#m%-mm+\n" +
                           "                    m+%####m##m#m#- +\n" +
                           "                    --##-##-+#++.#-\n" +
                           "                    +m%#m#m*#m+-%m. .\n" +
                           "                   ++.######*m -#+-.\n" +
                           "                  .++#######*%+-m.-\n" +
                           "                .--m##########m-++..\n" +
                           "             %+.+m################m.\n" +
                           "        -+---*.mm%+##############m-..m.-.++\n" +
                           "       -m%##%######m##############m%%.*-*++-+\n" +
                           "      .m###%m#######################%+-###**.\n" +
                           "      .. --%#.+%+#m%###*+###m#*##+-..%#*m** -\n" +
                           "         m.- +-m.+%*+*+m*.m+mm*%-+- --\n");
    }
}
