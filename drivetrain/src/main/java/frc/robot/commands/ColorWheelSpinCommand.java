/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ColorWheelSubsystem;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.HashMap;

/**
 * An example command that uses an example subsystem.
 */
public class ColorWheelSpinCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorWheelSubsystem m_subsystem;
  private final ColorSensorV3 colorSensor;

  private final ColorMatch m_colorMatcher = new ColorMatch();
  private boolean greenCheck = true;

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private Color firstColor;
  private Color previousColor;

  private HashMap<String, Integer> colorCounts = new HashMap<String, Integer>();

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ColorWheelSpinCommand(ColorWheelSubsystem subsystem, ColorSensorV3 m_colorSensor) {
    this.colorSensor = m_colorSensor;
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }


  //new SequentialCommandGroup
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    firstColor = colorSensor.getColor();
    previousColor = firstColor;

    this.colorCounts.put("blue", 0);
    this.colorCounts.put("green", 0);
    this.colorCounts.put("yellow", 0);
    this.colorCounts.put("red", 0);

    ColorMatchResult matchResult = m_colorMatcher.matchClosestColor(firstColor);

    this.incrementColorCounts(matchResult.color);
  }

  public void incrementColorCounts(Color color) {
    if(color.equals(kBlueTarget)) {
      int bCount = this.colorCounts.get("blue");
      bCount++;
      this.colorCounts.put("blue", bCount);
    } else if(color.equals(kGreenTarget)) {
      int gCount = this.colorCounts.get("green");
      gCount++;
      this.colorCounts.put("green", gCount);
    } else if(color.equals(kYellowTarget)) {
      int yCount = this.colorCounts.get("yellow");
      yCount++;
      this.colorCounts.put("yellow", yCount);
    } else if(color.equals(kRedTarget)) {
      int rCount = this.colorCounts.get("red");
      rCount++;
      this.colorCounts.put("red", rCount);
    }    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.turn(-1.0);

    Color detectedColor = colorSensor.getColor();
    ColorMatchResult matchResult = m_colorMatcher.matchClosestColor(detectedColor);
    if(!matchResult.color.equals(previousColor)) { 
      this.incrementColorCounts(matchResult.color);
      previousColor = matchResult.color;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.turn(0);

    int bCount = this.colorCounts.get("blue");
    int gCount = this.colorCounts.get("green");
    int yCount = this.colorCounts.get("yellow");
    int rCount = this.colorCounts.get("red");

    System.out.println("Blue [" + bCount + "], Green [" + gCount + "], Yellow [" + yCount + "], Red [" + rCount + "]");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    int rCount = this.colorCounts.get("red");

    if(rCount > 8) {
      // more than three revolutions of red was found - stop
      return true;
    }
    System.out.println("Color Wheel Spin - isFinished " + rCount);
    return false;
  }
}
