package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class ClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimbSubsystem climbsubsystem;
  private final Joystick shooterJoystick;
  private boolean end = false;

  public ClimbCommand(ClimbSubsystem subsystem, Joystick shooterJoystick) {
    addRequirements(subsystem);
    this.climbsubsystem = subsystem;
    this.shooterJoystick = shooterJoystick;
  }

  
  @Override
  public void initialize() {
  }


  @Override
  public void execute() {
    if (climbsubsystem.canClimb()) {
    double vertical = shooterJoystick.getY();
    //System.out.println(vertical);

    if (vertical > 0.6) {
        climbsubsystem.verticalClimbUp();
        //System.out.println("going up");
    }
    else if (vertical < -0.6) {
        climbsubsystem.verticalClimbDown();
        //System.out.println("going down");
    }
    else {
        climbsubsystem.stopVerticalClimb();
    }
    double horizontal = shooterJoystick.getX();

    if (horizontal > 0.6) {  
        climbsubsystem.horizontalClimbRight(); 
    }
    else if (horizontal < -0.6) {  
        climbsubsystem.horizontalClimbLeft();
    }
    else {  
      climbsubsystem.stopHorizontalClimb();  
    }
  }
  }

 
  @Override
  public void end(boolean interrupted) {
    climbsubsystem.stopVerticalClimb(); 
  }



  @Override
  public boolean isFinished() {
    double vertical = shooterJoystick.getY();

    if(vertical > 0.6) {
      if(this.climbsubsystem.isVerticalClimbUpDone()) {
        return true;
      }
    } else if(vertical < 0.6) { 
      if(this.climbsubsystem.isVerticalClimbDownDone()) { 
        return true;
      }
    }

    return false;
  }
}
