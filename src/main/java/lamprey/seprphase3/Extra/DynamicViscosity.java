/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.Extra;

import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;

/**
 * Class that provides methods for calculating the dynamic viscosity of WATER substance
 * at a given temperature & density.
 * 
 * @author will
 */
public class DynamicViscosity {
    
    // Constants for dimensionless temperature, pressure & density
    private static final double T_star = 647.096; // in K
    private static final double Rho_star = 322; // in kg/m^3
    private static final double Mu_star = 1E-6; // in Pascalseconds
    
    // Coefficients a_k for mu_0 equation.
    private static final double[] a_k = {1.67752, 2.20462, 0.6366564, -0.241605};
    // Coefficients b_ji for mu_1 equation.
    private static final double[][] b_ij = {{ 0.520094,     0.222531,   -0.281378,  0.161913,   -0.0325372,  0,           0             },
                                            { 0.0850895,    0.999115,   -0.906851,  0.257399,    0,          0,           0             },
                                            {-1.08374,      1.88797,    -0.772479,  0,           0,          0,           0             },
                                            {-0.289555,     1.26613,    -0.489837,  0,           0.0698452,  0,          -0.00435673    },
                                            { 0,            0,          -0.25704,   0,           0,          0.00872102,  0             },
                                            { 0,            0.120573,    0,         0,           0,          0,          -0.000593264   }};
   
    private static double T_bar;
    private static double Rho_bar;
    
    public static Viscosity dynamicViscosity(Temperature t, Density d) {
        // Initialise dimensionless variables.
        calcT_bar(t);
        calcRho_bar(d);
        return new Viscosity(Mu_0() * Mu_1() * Mu_star);
    }
    
    // ------ Initialisation ------
    private static void calcT_bar(Temperature t) {
        T_bar = t.inKelvin() / T_star;
    }
    
    private static void calcRho_bar(Density d) {
        Rho_bar = d.inKilogramsPerCubicMetre() / Rho_star;
    }
    
    // ------ Calculations ------
    private static double Mu_0 () {
        double numerator = 100 * Math.sqrt(T_bar);
        double denominator = 0.0;
        for (int k = 0; k < 4; k++) {
            denominator += a_k[k] / Math.pow(T_bar, k);
        }
        return numerator / denominator;
    }
    
    /* Valid in the ranges :
     * 0 MPa < P < 100 MPa,  0 C   < T < 800 C
     */
    private static double Mu_1() {
        double IJSum = 0;
        double OneOverT_barMinusOne = (1/T_bar) - 1;
        double Rho_barMinusOne = Rho_bar - 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                IJSum += b_ij[i][j] 
                         * Math.pow(OneOverT_barMinusOne,(double)i) 
                         * Math.pow(Rho_barMinusOne,(double)j);
            }
        }
        return Math.exp(Rho_bar * IJSum);
    }
   
}
