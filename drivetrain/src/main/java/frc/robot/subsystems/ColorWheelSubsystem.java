package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorWheelSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  WPI_VictorSPX colorWheelMotor = new WPI_VictorSPX (Constants.COLOR_WHEEL);
  
  public ColorWheelSubsystem() {
    this.colorWheelMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void turn(double cwMotor) {
    //Spin three times
    colorWheelMotor.set(cwMotor);
    //Loop that stops based on number passed in command
  }

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
}
