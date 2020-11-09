import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Xiling
 */

public class Controller implements ActionListener {
    private Model model;
    private Country attacker;
    private Country defender;

    public Controller(Model model){
        this.model = model;
        attacker = null;
        defender = null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("pass")){
            model.pass();
            attacker = null;
            defender = null;
        }else if (e.getActionCommand().equals("cancel")) {
            attacker = null;
            defender = null;
        }else if(e.getActionCommand().equals("Country")){
            CountryButton b = (CountryButton) e.getSource();
            if (attacker == null) {
                if (model.isAttacker(b.getCountry())) {
                    attacker = b.getCountry();
                }
            } else {
                if (model.canDefend(attacker, b.getCountry())) {
                    defender = b.getCountry();
                    model.attack(attacker, defender);
                    attacker = null;
                    defender = null;
                }
            }
        }
    }
}
