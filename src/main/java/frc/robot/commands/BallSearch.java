// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPipelineResult;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class BallSearch extends CommandBase {
  PIDController controller;
  PhotonCamera m_photon;
  PhotonPipelineResult result;
  Drivetrain m_drivetrain;

  /** Creates a new BallSearch. */
  public BallSearch(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // PID constants should be tuned per robot
    controller = new PIDController(.1, 0, 0);
    m_photon = new PhotonCamera("HD3000");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    result = m_photon.getLatestResult();
    if (result.hasTargets()) {
      if (result.getBestTarget().getArea() < 80) {
        // Romi can see the ball but not super close
        m_drivetrain.arcadeDrive(.2, controller.calculate(result.getBestTarget().getYaw(), 0));
      }
      else {
        // Romi is really close to the ball so stop
        m_drivetrain.arcadeDrive(0, 0);
      }
    }
    else {
      // Romi can't find any targets so slowly spin till we find one
      m_drivetrain.arcadeDrive(0, .1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
