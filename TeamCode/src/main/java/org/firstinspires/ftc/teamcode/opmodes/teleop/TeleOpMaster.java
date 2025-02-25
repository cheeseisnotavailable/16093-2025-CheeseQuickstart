package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SuperStructure;
import org.firstinspires.ftc.teamcode.actions.actioncore.Action;
import org.firstinspires.ftc.teamcode.drive.NewMecanumDrive;
import org.firstinspires.ftc.teamcode.references.XCYBoolean;

import java.util.List;

//@Photon
public abstract class TeleOpMaster extends LinearOpMode {
    NewMecanumDrive drive;
    SuperStructure upper;
    Runnable update;
    private List<LynxModule> allHubs;
    int count = 0;
    double oldTime = 0;
    int driveMode = 0;

    long startTime = Integer.MAX_VALUE;
    private final Telemetry telemetry_M = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    /*
    TODO: declare XCYBooleans for input
     */
    XCYBoolean forceStop, timerPast20, timerPast30;


    protected void initTeleOp(double startingHeading){
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        // Initialize SuperStructure with periodic functions for logic and drive control
        upper = new SuperStructure(
                this,
                () -> {
                });

        //  =====button assignments=====
        // Gamepad 1 button assignments

        keybinds();
        timerPast20 = new XCYBoolean(()->System.currentTimeMillis() - startTime > 100000);
        timerPast30 = new XCYBoolean(()->System.currentTimeMillis() - startTime > 110000);


        drive = new NewMecanumDrive(hardwareMap);
        drive.setOpModeActive(()->opModeIsActive());
        Action.setOpModeActive(()->opModeIsActive());

        update = () -> {
            logic_period();
            drive_period();
            upper.update();
            gamepad_inputs();


            if (forceStop.toTrue()) {
                Action.stopBuilding = true;
            }
            if (forceStop.toFalse()) {
                Action.stopBuilding = false;
            }


            if(startTime == Integer.MAX_VALUE){
                startTime = System.currentTimeMillis();
            }else if(timerPast30.toTrue()){
                gamepad1.rumble(100);
                gamepad2.rumble(100);
            }else if(timerPast20.toTrue()){
                gamepad1.rumble(100);
                gamepad2.rumble(100);
            }


            if(drive.simpleMoveIsActivate){
                drive.update();
            }else{
                drive.updatePoseEstimate();
            }

        };

        // Initialize and set up mecanum drive, starting position at (0,0,0)
        drive.setUpdateRunnable(update);
        drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(startingHeading)));
        drive.initialUpdate();


        drive.storeCurrentPos();
        drive.recalibrateOdo();

        Action.clearActions();
    }

    protected void keybinds(){
        /*
        TODO: keybinds.
         */
    }

    protected void gamepad_inputs() {
        /*
        TODO: operations handling.
         */

        if (Action.actions.isEmpty()) {
            /*
            example: if(release.toTrue){
                        Action.actions.add(new ArmAction(HardwareValues.ARM_UP));
                     }
             */
        }
    }

    private void drive_period() {
        if (upper != null) {
            if (driveMode == 0) {
                drive.setFieldCentric(gamepad1.left_stick_x, gamepad1.left_stick_y, -gamepad1.right_stick_x, upper.getSequence());
            }else if (driveMode == 1){
                drive.setBotCentric(gamepad1.left_stick_x, gamepad1.left_stick_y, -gamepad1.right_stick_x, upper.getSequence());
            }
            drive.updateOdo();
        }
    }

    private void toggleDriveMode(int mode) {
        if (!drive.simpleMoveIsActivate) {
            driveMode = mode;
        }
    }


    // Logic updates with telemetry
    protected void logic_period() {
//        double newTime = getRuntime();
//        double loopTime = newTime-oldTime;
//        double frequency = 1/loopTime;
//        oldTime = newTime;
        XCYBoolean.bulkRead();

//        telemetry.addData("REV Hub Frequency: ", frequency); //prints the control system refresh rate

//        telemetry.addData("Current Pos", drive.getCurrentPoseAsString());
//        telemetry.addData("DriveMode: ", driveMode);

//        telemetry.addLine(Action.showCurrentAction());

//        telemetry_M.update();
        for (LynxModule module : allHubs) {
            module.clearBulkCache();
        }
    }
}