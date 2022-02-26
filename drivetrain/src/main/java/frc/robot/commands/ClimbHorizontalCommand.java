package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimbSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class ClimbHorizontalCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimbSubsystem climbsubsystem;
  private final Joystick shooterJoystick;
  private boolean end = false;

  public ClimbHorizontalCommand(ClimbSubsystem subsystem, Joystick shooterJoystick) {
    addRequirements(subsystem);
    this.climbsubsystem = subsystem;
    this.shooterJoystick = shooterJoystick;
  }
  
  @Override
  public void initialize() {
  }


  @Override
  public void execute() {
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

 
  @Override
  public void end(boolean interrupted) {
    climbsubsystem.stopHorizontalClimb();  
  }



  @Override
  public boolean isFinished() {
    return false;
  }
}