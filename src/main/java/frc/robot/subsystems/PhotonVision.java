package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPipelineResult;
import org.photonvision.PhotonTrackedTarget;

public class PhotonVision {
   PhotonCamera camera;

   public PhotonVision(String cameraName) {
      // Creates a new PhotonCamera.
      camera = new PhotonCamera(cameraName);
   }

   public int numberOfTargets() {
      // Get the latest pipeline result.
      PhotonPipelineResult result = camera.getLatestResult();

      // Get a list of currently tracked targets.
      List<PhotonTrackedTarget> targets = result.getTargets();
      return targets.size();
   }

   public boolean hasTarget(){
      return camera.hasTargets();
   }
   
   public double getHorizontalError(){
      // Get the latest pipeline result.
      PhotonPipelineResult result = camera.getLatestResult();

      return result.targets.get(0).getYaw();
   }

   public void setPipeline(int index) {
      camera.setPipelineIndex(index);
   }
}
