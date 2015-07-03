package strategies;

import data.Constants;
import main.LordPlanking;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Npc;

public class Planking implements Strategy{
    Npc[] MILLS = Npcs.getNearest(4250);

    @Override
    public boolean activate() {
        return Inventory.isFull() && Inventory.getCount(LordPlanking.LOG_ID) >= 28 && Inventory.getCount(LordPlanking.PLANK_ID) < 2 && Players.getMyPlayer().getAnimation() <= 1;
    }

    @Override
    public void execute() {
        Logger.addMessage("Making Planks", true);
        if (MILLS != null){
            MILLS[0].interact(Npcs.Option.THIRD);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Game.getOpenInterfaceId() == 39000;
                }
            }, 2500);
            if (LordPlanking.LOG_ID == Constants.LOGS) {
                Menu.clickButton(39007);
            } else if (LordPlanking.LOG_ID == Constants.OAKLOGS) {
                Menu.clickButton(39008);
            } else if (LordPlanking.LOG_ID == Constants.TEAKLOGS) {
                Menu.clickButton(39009);
            } else if (LordPlanking.LOG_ID == Constants.MAHOGANYLOGS) {
                Menu.clickButton(39010);
            }
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getCount(LordPlanking.PLANK_ID) > 26;
                }
            },2500);
            LordPlanking.PLANKS_MADE += 28;
        }
    }
}
