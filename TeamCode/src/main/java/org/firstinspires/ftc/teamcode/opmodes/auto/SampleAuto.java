package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.opmodes.teleop.TeleOpMaster;

public class SampleAuto extends AutoMaster {

    @Override
    public void runOpMode() throws InterruptedException {

        initAuto(new Pose2d(0,0,0));

        waitForStart();

        //functions!
        //functions!

        while(opModeIsActive()){
            super.update.run();
        }
    }
}
