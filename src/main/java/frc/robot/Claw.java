package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * Claw
 * 
 * The code for the claw will be posted here for use on the new robot.
 * 
 * @author (Charlie Bergdall)
 * @version (0.0.4) Swenson's Claw uses Neos (Spark MAX), solenoids,
 */
public class Claw {
    //Global Variables
    public static DoubleSolenoid hatchIntakePiston;
    public static CANSparkMax claw;
    public static TalonSRX clawIntakeMotor;
    private static final double SCALER = 0.25;
    
    //This method should be run first and will initialize all important variables to make the claw work
    public static void initClaw() {
        hatchIntakePiston = new DoubleSolenoid(2, 5); //Creates hatch intake piston
        claw = new CANSparkMax(1, MotorType.kBrushless); //Creates claw | need to change the first int param to motor assignment ID
        clawIntakeMotor = new TalonSRX(2); //Creates clawIntakeMotor motor | need to change the int param to motor assingment ID
    }

    //Sets pistons to the intake mode
    public static void openPiston() {
        hatchIntakePiston.set(Value.kReverse);
    }

    //Sets pistons to the outtake mode
    public static void closePiston() {
        hatchIntakePiston.set(Value.kForward);
    }

    //Sets pistons to neutral mode | saw this in last years code so I wasn't sure if I should add it or not (ClawControl)
    public static void neutralPiston() {
        hatchIntakePiston.set(Value.kOff);
    }

    //Controls Claw rotation
    public static void clawRotation(double mechLeftTrigDemand, double mechRightTrigDemand) {
        double clawArmDemand = (mechLeftTrigDemand - mechRightTrigDemand) * SCALER;
        claw.set(clawArmDemand);
    }

    //Activates the clawIntakeMotor to intake cargo
    public static void clawIntake() {
        clawIntakeMotor.set(ControlMode.PercentOutput, .75);
    }

    //Activates the clawIntakeMotor to outtake cargo
    public static void clawOuttake() {
        clawIntakeMotor.set(ControlMode.PercentOutput, -.75);
    }

}