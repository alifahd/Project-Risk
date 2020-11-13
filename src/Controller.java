import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Xiling
 * @author Aleksandar Veselinovic
 */

public class Controller implements ActionListener {
    private Model model;
    private Country attacker;
    private Country defender;
    private Country country1;
    private Country country2;
    private int placementTroops;

    /**
     * Constructor of Controller
     *
     * @param model the model that is controlled
     */
    public Controller(Model model){
        this.model = model;
        attacker = null;
        defender = null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("pass")){//if the pass button was pressed
            model.pass();
            attacker = null;
            defender = null;
        }else if (e.getActionCommand().equals("cancel")) {//if the cancel button was pressed
            attacker = null;
            defender = null;
        }else if (e.getActionCommand().equals("fortify")) {//if the cancel button was pressed
            model.activateFortify();
        }else if(e.getActionCommand().equals("Country")){//if a country button was pressed

            CountryButton b = (CountryButton) e.getSource();//get the country button that was pressed

            if(model.isPlacementPhase()){
                if(placementTroops == 0){
                    placementTroops = model.bonusTroopCalculator();
                }
                placementTroops = model.troopPlacement(placementTroops, b.getCountry());
            }
            else if(model.isFortifyPhase()){
                if(country1 == null){
                    model.isFortifying(b.getCountry());
                    country1 = b.getCountry();
                }
                else{
                    if(model.canFortify(country1, b.getCountry())){
                        country2 = b.getCountry();
                        model.fortify(country1, country2);
                        country1 = null;
                        country2 = null;
                        attacker = null;
                        defender = null;
                    }
                }
            }else{
                if (attacker == null) {//if the attacker hasn't been selected yet
                    if (model.isAttacker(b.getCountry())) {//if the country represented by the button can attack
                        attacker = b.getCountry();//store the country represented by the button
                    }
                } else {//if the attacker has been selected
                    if (model.canDefend(attacker, b.getCountry())) {//if the country represented by the button can defend the attacking country
                        defender = b.getCountry();//store the country represented by the button
                        model.attack(attacker, defender);
                        attacker = null;
                        defender = null;
                    }
                }
            }

        }
    }
}
