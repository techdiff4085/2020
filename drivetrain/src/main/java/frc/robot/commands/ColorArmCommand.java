/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ColorArmSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ColorArmCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorArmSubsystem m_subsystem;
  private final String extendType;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ColorArmCommand(ColorArmSubsystem subsystem, String extendType) {
    m_subsystem = subsystem;
    this.extendType = extendType;


    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {    
    System.out.println("Command Executing");
    if (extendType.equals("Out")){
      this.m_subsystem.extendArm("Up");
    }

    if (extendType.equals("In")) {
      this.m_subsystem.extendArm("Down");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_subsystem.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(this.extendType.equals("Out")) { 
      if(this.m_subsystem.isUp()) {
        System.out.println("Finished");
        return true;
      }
    } else if(this.extendType.equals("In")) { 
      if(this.m_subsystem.isDown()) { 
        System.out.println("Finished");
        return true;
      }
    }
    System.out.println("Not Finished");
    return false;
  }
}
