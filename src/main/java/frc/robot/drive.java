/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;
/**
 * Add your docs here.
 */
public class drive {
    public static CANSparkMax L1, L2, R1, R2;
    public static XboxController driveController;
    public static DoubleSolenoid rDriveShift, lDriveShift;
    public static int PCM_CAN_ID = 19;
    public static boolean driveMechShiftToggle;
    public static void initDrive(){
        L1 = new CANSparkMax(10, MotorType.kBrushless);
        L2 = new CANSparkMax(11, MotorType.kBrushless);
        R1 = new CANSparkMax(12, MotorType.kBrushless);
        R2 = new CANSparkMax(13, MotorType.kBrushless);
        L2.follow(L1);
        R2.follow(R1);
        driveController = new XboxController(1);
        rDriveShift = new DoubleSolenoid(PCM_CAN_ID, 0, 7); //these values are subject to change if wiring changes
        lDriveShift = new DoubleSolenoid(PCM_CAN_ID, 1, 6); //^^
        driveMechShiftToggle = false;
    }

    public static void arcadeDrive(double forwardPower, double turnPower, boolean powerBoost, boolean mechanicalBoost){
        
        /*
        this arcade drive function has two shiting modes one shifts a power scaler and is then applied to only forward power,
         but could easily be switched to both forward power and turn power based on driver preference. the second shifting mode is the actual gear shift.
         the final part is the math for arcade drive
         */
        if(powerBoost){  //if power boost true, give raw input to the motor output: which to default is half input the power and so by pressing and holding you double the output power to the motors from whats normal
            forwardPower = forwardPower *  1.0;
        } else {
            forwardPower = forwardPower *  0.5; //looky
        }

        if(mechanicalBoost){driveMechShiftToggle = !driveMechShiftToggle;} //this toggles a boolean. that way, to switch to high speeds(mechanically) you just press the button once to switch the gear ratios

        if(driveMechShiftToggle){  //shift gears to lower torque:higher RPM ratio
            rDriveShift.set(Value.kReverse); 
            lDriveShift.set(Value.kForward);
        } else {
            rDriveShift.set(Value.kForward); 
            lDriveShift.set(Value.kReverse);
        }

        R1.set(turnPower + forwardPower); //this is the math for arcade drive.
        L1.set(turnPower - forwardPower);
    }
}
