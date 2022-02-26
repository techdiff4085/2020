package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
  
    WPI_VictorSPX VCmotor = new WPI_VictorSPX (Constants.CLIMB);
    WPI_VictorSPX HCmotor = new WPI_VictorSPX (Constants.BAR_MOVE);
    DigitalInput lsDown = new DigitalInput(Constants.CLIMBER_LS_DOWN);
    DigitalInput lsUp = new DigitalInput(Constants.CLIMBER_LS_UP);
    boolean enabled = false;

    public boolean canClimb(){
      return enabled;
    }

  public ClimbSubsystem() {
    this.VCmotor.setNeutralMode(NeutralMode.Brake);
  }

  public void horizontalClimbRight(){
    HCmotor.set(1);
  }

  public void horizontalClimbLeft(){
    HCmotor.set(-1);
  }

  public void stopHorizontalClimb(){
    HCmotor.set(0);
  }

  public void verticalClimbUp(){
    //if (!isVerticalClimbUpDone()){
      VCmotor.set(1);
    //}
  }
  public void verticalClimbDown(){
    //if (!isVerticalClimbDownDone()){
      VCmotor.set(-1);
  //  }
  }

  public void stopVerticalClimb(){
    VCmotor.set(0);
  }

  /**
   * This method will check the upper limit switch on the climbing arm, so that the motor
   * can be stopped when the climbing arm is at its highest position.
   */
  public boolean isVerticalClimbUpDone() { 
    
    if(this.lsUp.get()) { 
      //System.out.println("lsUp is " + this.lsUp.get());
      return true;
    } else { 
      //System.out.println("lsUp is " + this.lsUp.get());
      return false;
    }
  }

  /**
   * This method will check the lower limit switch on the climing arm, so that the motor can be stopped when we 
   * have finished climbing.
   */
  public boolean isVerticalClimbDownDone() { 
    if(this.lsDown.get()) { 
      //System.out.println("is vertical climb down done = true");
      //System.out.println("lsUp is " + this.lsUp.get());
      return true;
    } else  {
      //System.out.println("is vertical climb down done = false");
      //System.out.println("lsUp is " + this.lsUp.get());
      return false;
    }
  }

  /**
   * This method will unlock the climber by moving the motor for .25 seconds, and immediately stopping the rotation.
   * This will allow the locking clamp to disengage, and allow the gas pistons to push the arm up into the pre-climb
   * position.
   */
  public void unlockClimber() { 
    
    // Start the motor
    VCmotor.set(-0.45);

    // Stop the motor
  }
  public void stopUnlockClimber(){
    VCmotor.set(0);
    enabled = true;
  }

  /*@Override
  public void periodic() {
    double speed = VCmotor.get();
    
    if(isVerticalClimbUpDone()){
      if(speed > 0){
        VCmotor.set(0);
      }
    }
    if(isVerticalClimbDownDone()){
      if(speed < 0){
        VCmotor.set(0);
      }
    }
  }*/
}