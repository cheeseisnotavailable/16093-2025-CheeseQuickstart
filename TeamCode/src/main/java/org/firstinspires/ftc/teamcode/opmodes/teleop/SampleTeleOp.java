package org.firstinspires.ftc.teamcode.opmodes.teleop;

public class SampleTeleOp extends TeleOpMaster{

    @Override
    public void runOpMode() throws InterruptedException {

        initTeleOp(0);

        waitForStart();

        //nothing needs to go here.

        while(opModeIsActive()){
            super.update.run();
        }

        //for single teleOp: override keybinds();
    }
}
