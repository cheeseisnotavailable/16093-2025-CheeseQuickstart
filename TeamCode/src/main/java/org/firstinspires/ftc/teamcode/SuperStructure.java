package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.references.HardwareValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Config
public class SuperStructure {

    /*
     * TODO: The hardware devices of the superstructure go here!
     * Superstructure = anything that you want to run in parallel with the drivetrain at all times
     */

    /*
     * TODO: Add your motors and servos and crap here!
     */

    /*
     * TODO: Add your PID Coefficients and Controllers here! (If you want to use them)
     */


    private final LinearOpMode opMode;
    private Runnable updateRunnable;

    /*
     * TODO: Add temporary stores for all your hardware states here!
     * e.g. motor positions, motor modes
     * And whatever other constants.
     */


    public void setUpdateRunnable(Runnable updateRunnable) {
        this.updateRunnable = updateRunnable;
    }

    public SuperStructure(LinearOpMode opMode, Runnable updateRunnable){
        this.opMode = opMode;
        HardwareMap hardwareMap = opMode.hardwareMap;
        this.updateRunnable = updateRunnable;

        /*
         * TODO: Init your hardware here!
         */
    }


    public void update() {
        /*
         * TODO: Update all your hardware states here!
         */

        /*
         * TODO: Stick all your PID stuff here!
         */

    }


    Sequences sequence;
    Sequences previousSequence;


    // Switches the sequence to a new state and stores the previous one
    public void switchSequence(Sequences s) {
        previousSequence = sequence;
        sequence = s;
    }

    public Sequences getSequence(){
        return sequence;
    }
    public Sequences getPreviousSequence(){
        return previousSequence;
    }

    // Enum for sequence states
    public enum Sequences {
        /*
         * TODO: Add your sequences here!
         * e.g. RUN, RELEASE, INTAKE
         */
    }

    /*
     * TODO: Control your hardware devices!
     * e.g.
     *     public void setArmByP(int pos, double power){
     *         armTargetPosition = pos;
     *         armUp.setTargetPosition(pos);
     *         armDown.setTargetPosition(pos);
     *         setArmModeWrapper(DcMotor.RunMode.RUN_TO_POSITION);
     *         setArmPowerWrapper(power);
     *     }
     *
     *TODO: Don't forget the targetPostions!
     */

    /*
     * TODO: getters and setters
     */

    /*
     * TODO: WRAPPER FUNCTIONS, POSSIBLY VERY IMPORTANT FOR PROPER LOOP TIMES
     */



}