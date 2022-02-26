/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ColorWheelSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 * An example command that uses an example subsystem.
 */
public class ColorWheelFindCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorWheelSubsystem m_subsystem;
  private String gameData;
  private final ColorSensorV3 colorSensor;

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private boolean endFind = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ColorWheelFindCommand(ColorWheelSubsystem subsystem, ColorSensorV3 m_colorSensor) {
    this.colorSensor = m_colorSensor;
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Color detectedColor = colorSensor.getColor();
    //System.out.println(detectedColor.toString());
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
        m_subsystem.turn(.5);
        if (match.color == kBlueTarget) {
            m_subsystem.turn(0);
            endFind = true;
        }
          //Blue case code

          break;
        case 'G' :
          //Green case code
          m_subsystem.turn(.5);
          if (match.color == kGreenTarget) {
              m_subsystem.turn(0);
              endFind = true;
          }
          break;
        case 'R' :
          //Red case code
          m_subsystem.turn(.5);
          if (match.color == kRedTarget) {
              m_subsystem.turn(0);
              endFind = true;
          }
          break;
        case 'Y' :
          //Yellow case code
          m_subsystem.turn(.5);
          if (match.color == kYellowTarget) {
              m_subsystem.turn(0);
              endFind = true;
          }
          break;
        default :
          //This is corrupt data
          break;
      }
    } else {
      //Code for no data received yet
      
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (endFind = true) {
        return true;
    }
    return false;
  }
}
