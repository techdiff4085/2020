/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorArmSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  WPI_VictorSPX colorArmMotor = new WPI_VictorSPX (Constants.COLOR_WHEEL_ARM);
  DigitalInput lsDown = new DigitalInput(Constants.COLOR_WHEEL_DOWN_LS);
  DigitalInput lsUp = new DigitalInput(Constants.COLOR_WHEEL_UP_LS);
//  DigitalInput StopDrive = new DigitalInput(Constants.Stop_Drive_LS);
  
  public ColorArmSubsystem() {

  }

  public void extendArm(String direction) {
    System.out.println("Executing extendArm - " + direction);
    if(direction.equals("Up")) {
      colorArmMotor.set(-.55);
    } else if(direction.equals("Down")) {
      colorArmMotor.set(.55);
    }
  }

  public boolean isUp() {
    System.out.println("isUp()");
    if(lsUp.get()) { 
      return true;
    } else { 
      return false;
    }
  }

  public boolean isDown() {
    System.out.println("isDown()");
    if(lsDown.get()) { 
      return true;
    } else {
      return false;
    }
  }
/*
  public boolean driveStopLimitSwitch(){
      System.out.println("isDriveStopped()");
      if(this.StopDrive.get()) {
        return true;
      } else {
        return false;
      }
  }
*/
  public void stopMotor() { 
    System.out.println("Stopping the motor");
    this.colorArmMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}