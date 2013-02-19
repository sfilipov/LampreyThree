/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import eel.seprphase2.Simulator.CannotRepairException;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.KeyNotFoundException;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import eel.seprphase2.Utilities.Percentage;
import lamprey.seprphase3.GUI.Screens.GameplayScreen;

/**
 *
 * @author Simeon
 */
public class GameplayListeners {
    private final static Percentage STEP = new Percentage(5d);
    
    GameplayScreen screen;
    PlantController controller;
    PlantStatus status;
    GameManager manager;
    
    public GameplayListeners(GameplayScreen screen, PlantController controller, PlantStatus status, GameManager manager) {
        this.screen = screen;
        this.controller = controller;
        this.status = status;
        this.manager = manager;
    }
    
    public ClickListener getCondenserListener() {
        return new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.moveMechanicTo(630f);
                try {
                    
                    controller.repairCondenser();
                }
                catch(CannotRepairException e) {
                    //Stop repairing
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        };
    }      
    
    public ClickListener getTurbineListener() {
        return new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.moveMechanicTo(445f);
                try {
                    controller.repairTurbine();
                }
                catch(CannotRepairException e) {
                    //Do something
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        };
    }    
    
    public ClickListener getReactorListener() {
        return new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.moveMechanicTo(30f);
                try {
                    
                    controller.repairCondenser();
                }
                catch(CannotRepairException e) {
                    //Stop repairing
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        };
    }
    
    public ClickListener getPump1Listener() {
        return new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.moveMechanicTo(380f);
                try {
                    controller.repairPump(1);
                }
                catch(CannotRepairException e) {
                    //Do something
                }
                catch(KeyNotFoundException e) {
                    //Do something
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        };
    }
    
    public ClickListener getPump2Listener() {
        return new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.moveMechanicTo(750f);
                try {
                    controller.repairPump(2);
                }
                catch(CannotRepairException e) {
                    //Do something
                }
                catch(KeyNotFoundException e) {
                    //Do something
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        };
    }
    
//   public ClickListener getCoolerListener() {
//        return new ClickListener() {
//            @Override
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                screen.moveMechanicTo(750f);
//                return super.touchDown(event, x, y, pointer, button);
//            }
//        };
//    }
    
    public ClickListener getConrolRodsUpListener() {
        return new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                Percentage crPosition = status.controlRodPosition();
                try {
                    crPosition = crPosition.plus(STEP);
                }
                catch(IllegalArgumentException e) {
                    crPosition = new Percentage(100d);
                }
                controller.moveControlRods(crPosition);
            }
        };
    }
    
    public ClickListener getConrolRodsDownListener() {
        return new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                Percentage crPosition = status.controlRodPosition();
                try {
                    crPosition = crPosition.minus(STEP);
                }
                catch(IllegalArgumentException e) {
                    crPosition = new Percentage(0d);
                }
                controller.moveControlRods(crPosition);
            }
        };
    }    
    
    public ClickListener getValve1Listener() {
        return new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                try {
                    controller.flipValveState(1);
                }
                catch(Exception e) {
                }
            }
        };
    }
    
    public ClickListener getValve2Listener() {
        return new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                try {
                    controller.flipValveState(2);
                }
                catch(Exception e) {
                }
            }
        };
    }
    
    public ClickListener getPump1ButtonListener() {
        return new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                try {
                    controller.flipPumpState(1);
                }
                catch(Exception e) {
                }
            }
        };
    }
    
    public ClickListener getPump2ButtonListener() {
        return new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                try {
                    controller.flipPumpState(2);
                }
                catch(Exception e) {
                }
            }
        };
    }
    
        //DOESN'T Detect key presses
    public ClickListener getPauseListener() {
        return new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.getGame().setScreen(screen.getGame().getPauseScreen());
                return true;
            }
            
//            @Override
//            public boolean keyDown(InputEvent event, int keycode) {
//                if(keycode == Keys.A) {
//                    game.setScreen(game.getPauseScreen());
//                    return true;
//                }
//                return false;
//            }
        };
    }
}
