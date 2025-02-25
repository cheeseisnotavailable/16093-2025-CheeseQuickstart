//package org.firstinspires.ftc.teamcode.actions;
//
//import com.acmerobotics.dashboard.config.Config;
//
//import org.firstinspires.ftc.teamcode.SuperStructure;
//import org.firstinspires.ftc.teamcode.actions.actioncore.MotorAction;
//
//@Config
//public class ArmAction extends MotorAction {
//    private int armTarget;
//
//    public ArmAction(SuperStructure upper, int target){
//        super(upper, target);
//        armTarget = target;
//    }
//
//    public ArmAction(SuperStructure upper, int target, int toleranceRange){
//        super(upper, target, toleranceRange);
//        armTarget = target;
//    }
//
//    public ArmAction(SuperStructure upper, int target, int toleranceRange, double power){
//        super(upper, target, toleranceRange, power);
//        armTarget = target;
//    }
//
//    public int getError() {
//        return armTarget - upper.getArmPosition();
//    }
//
//    public void actuate() {
//        upper.setArmByP(armTarget,power);
//    }
//
//    public void stop(){
//        upper.setArmPower(0);
//        toleranceRange = 10000;
//        super.stop();
//    }
//
//    //Functions not in super class
//    public void forceStop(){
//        upper.setArmPower(0);
//        toleranceRange = 10000;
//        finishRange = 10000;
//    }
//
//    public String toString() {
//        return returnType() + " Target " + this.armTarget + " Power " + this.power + " Error " + this.getError();
//    }
//
//    public String returnType(){
//        return "ArmAction";
//    }
//
//}
