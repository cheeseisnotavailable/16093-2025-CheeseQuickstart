package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.SuperStructure;
import org.firstinspires.ftc.teamcode.drive.NewMecanumDrive;
import org.firstinspires.ftc.teamcode.references.XCYBoolean;
import org.firstinspires.ftc.teamcode.actions.actioncore.Action;

import java.util.List;

@Config
public abstract class AutoMaster extends LinearOpMode {

    private NewMecanumDrive drive;
    protected SuperStructure upper;
    protected Runnable update;
    private List<LynxModule> allHubs;

    Pose2d startPos;

    static long startTime;


    protected void initAuto(Pose2d start) throws InterruptedException{
        allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        startPos = start;
        startTime = System.currentTimeMillis();
        Action.clearActions();
        Action.setOpModeActive(()->opModeIsActive());

        telemetry.addLine("init: drive");
        telemetry.update();
        drive = new NewMecanumDrive(hardwareMap);
        drive.recalibrateOdo();
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        drive.setPoseEstimate(startPos);
        drive.setOpModeActive(()->opModeIsActive());
        drive.initialUpdate();
        drive.setSimpleMoveTolerance(2,2,Math.toRadians(10));

        telemetry.addLine("init: superstructure");
        telemetry.update();
        upper = new SuperStructure(
                this,
                () -> {
                });

        update = ()->{
            drive.update();
            upper.update();
            XCYBoolean.bulkRead();

//            double newTime = getRuntime();
//            double loopTime = newTime-oldTime;
//            double frequency = 1/loopTime;
//            oldTime = newTime;
//            telemetry.addData("REV Hub Frequency: ", frequency); //prints the control system refresh rate

//            if(drive.simpleMoveIsActivate){
//                RobotLog.d("SimpleMove at "+drive.getPoseEstimate().toString()+" SimpleMove target "+drive.getSimpleMovePosition().toString());
//            }
//            if (!Action.actions.isEmpty()) {
//                RobotLog.d("Running " + Action.showCurrentAction());
//            }

            /*
            TODO: and any other update function.
             */

            for (LynxModule module : allHubs) {
                module.clearBulkCache();
            }
        };

        drive.setUpdateRunnable(update);
        drive.setSwitchDrivePIDCondition(()->false); //TODO: something else maybe?


        /*
        TODO: reset the things that need to be reset
         */


        telemetry.addLine("init: complete");
        telemetry.update();


    }

    protected void setStartTime(){
        startTime = System.currentTimeMillis();
    }


    /*
    TODO: Literally everything else
    Example:
    protected void reset(){
        upper.setGrabPos(HardwareValues.AUTO_GRAB_CLOSED);
        Action.actions.add(new ArmAction(HardwareValues.ARM_DOWN));
        drive.moveTo(new Pose2d(0,0,0), 10);
        Action.buildSequence(update);
     */


    //This thing is in a really awkward place.
    public static  double testPIDx = 0, testPIDy = 0, testPIDheading = 90;
    public static  double targetPIDx = 0, targetPIDy = 40, targetPIDheading = 90;
    Pose2d[] poses = {new Pose2d(targetPIDx,targetPIDy,Math.toRadians(targetPIDheading)),new Pose2d(testPIDx,testPIDy,Math.toRadians(testPIDheading))};
    private Pose2d currentPose;
    private int poseCount = 0;
    protected void testAutoPID(){
        drive.setSimpleMovePower(0.5);
        if(!drive.isBusy()){
            if(poseCount < poses.length){
                currentPose = poses[poseCount];
                drive.moveTo(currentPose,200);
                poseCount++;
            }else{
                poseCount = 0;
            }
        }
    }


    protected void delay(int millisecond) {
        long end = System.currentTimeMillis() + millisecond;
        while (opModeIsActive() && end > System.currentTimeMillis() && update!=null) {
            update.run();
        }
    }
}