//motor id's 40-49
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * cargoIntake
 */
public class cargoIntake {

    public static TalonSRX cargoWristL, cargoWristR, mecCargoIntakeL, mecCargoIntakeR;
    public static XboxController mechStick;
    
    public static void initializeCargo(){

        cargoWristL = new TalonSRX(40);
        cargoWristR = new TalonSRX(41);

        mecCargoIntakeL = new TalonSRX(42);
        mecCargoIntakeR = new TalonSRX(43);

        cargoWristL.follow(cargoWristR);
        cargoWristL.setInverted(true);

        mecCargoIntakeL.follow(mecCargoIntakeR);
        mecCargoIntakeL.setInverted(true);

    }

    public void cargoWristOut(){
        cargoWristR.set(ControlMode.PercentOutput, mechStick.getY(Hand.kLeft));
    }

    public void cargoWristIn(){
        cargoWristR.set(ControlMode.PercentOutput, -1 * mechStick.getY(Hand.kLeft));
    }

    public void mecCargoIntake(){

        if(mechStick.getBumper(Hand.kRight)){
            mecCargoIntakeL.set(ControlMode.PercentOutput, 0.75);
        }

    }

    public void mecCargoOuttake(){

        if(mechStick.getBumper(Hand.kLeft)){
            mecCargoIntakeR.set(ControlMode.PercentOutput, -0.75);
        }

    }

}




