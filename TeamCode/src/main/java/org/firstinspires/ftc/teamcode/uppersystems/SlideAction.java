package org.firstinspires.ftc.teamcode.uppersystems;

import org.firstinspires.ftc.teamcode.references.XCYBoolean;

public class SlideAction extends Action {
    private int toleranceRange = 70;
    private SuperStructure upper;
    //Params not in super class
    private int slideTarget;
    private double power = 1;

    public SlideAction(SuperStructure upper, int slideTarget){
        this.upper = upper;
        this.slideTarget = slideTarget;
    }

    public SlideAction(SuperStructure upper, int slideTarget, int toleranceRange){
        this.upper = upper;
        this.slideTarget = slideTarget;
        this.toleranceRange = toleranceRange;
    }

    public SlideAction(SuperStructure upper, int slideTarget, double power){
        this.upper = upper;
        this.slideTarget = slideTarget;
        this.power = power;
    }

    public int getError() {
        return slideTarget - upper.getSlidesPosition();
    }

    public boolean canStartNext(){
        if((Math.abs(getError()) < toleranceRange)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isFinished(){
        if((Math.abs(getError()) < 30)){
            return true;
        }else{
            return false;
        }
    }

    public void forceStop(){
        upper.setSlidesByP(slideTarget, 0);
        toleranceRange = 100000;
    }

    public String returnType(){
        return "SlideAction";
    }

    public void actuate() {
        upper.setSlidesByP(slideTarget, power);
    }
}
