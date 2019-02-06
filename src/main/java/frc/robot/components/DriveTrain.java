package frc.robot.components;

import utils.*;

import edu.wpi.first.wpilibj.*;
import frc.robot.RobotMap;

public class DriveTrain {
    private Spark motorRight1 = new Spark(RobotMap.DRIVE_TRAIN_MOTOR_RIGHT_1);
    private Spark motorRight2 = new Spark(RobotMap.DRIVE_TRAIN_MOTOR_RIGHT_2);
    private Spark motorLeft1 = new Spark(RobotMap.DRIVE_TRAIN_MOTOR_LEFT_1);
    private Spark motorLeft2 = new Spark(RobotMap.DRIVE_TRAIN_MOTOR_LEFT_2);

    // private Encoder encoderRight = new Encoder(0, 1);
    // private Encoder encoderLeft = new Encoder(3, 4);
    private Encoder encoderRight;
    private Encoder encoderLeft;

    public double driveSpeed = 0.4;
    public double turnSpeed = 0.2;

    private double speed;
    private double rotation;

    private Vector2 position;

    private double wheelCMCircumference = 0.0;

    public DriveTrain() {
    }

    // Set the distance of the robot from it's current position.
    public void SetDistance(double cm) {
    }

    // Set the angle or rotation of the robot.
    public void SetRotation(double angle) {
    }

    // Robot rotates and drives towards a given x and y.
    public void DriveToPoint(double x, double y) {

    }

    // Robot smoothly navigates a path.
    public void DrivePointPath(Vector2[] points) {

    }

    public void CalculateWorldPosition() {
        // TODO: Tripple check my math. It probalby isn't that good.
        double right = encoderRight.getRate() * wheelCMCircumference;
        double left = encoderLeft.getRate() * wheelCMCircumference;

        // FIXME: hypotenuse is wrong.
        // It should be heighest value ^ 2 + robot width ^ 2.
        // If one value is negative, add that to the magnitude of the side.
        double hypotenuse = Math.sqrt(right * right + left * left);

        speed = Math.abs(right - left);
        rotation = Math.sin(speed / hypotenuse);

        position.Add(RobotMath.PolarToCartesian(speed, rotation));
    }

    public void Drive(double rotation, double speed) {
        rotation *= turnSpeed;
        DriveTank(speed - rotation, -speed + rotation);
        System.out.println("Speed = " + speed + " | Rotation = " + rotation);
    }

    public void DriveTank(double right, double left) {
        right *= driveSpeed;
        left *= driveSpeed;
        motorRight1.set(right);
        motorRight2.set(right);
        motorLeft1.set(left);
        motorLeft2.set(left);
    }

    public void ToggleSafty(boolean isSafe) {
        motorRight1.setSafetyEnabled(isSafe);
        motorRight1.setSafetyEnabled(isSafe);
        motorLeft1.setSafetyEnabled(isSafe);
        motorLeft2.setSafetyEnabled(isSafe);
    }

    public void Stop() {
        motorRight1.stopMotor();
        motorRight2.stopMotor();
        motorLeft1.stopMotor();
        motorLeft2.stopMotor();
    }
}