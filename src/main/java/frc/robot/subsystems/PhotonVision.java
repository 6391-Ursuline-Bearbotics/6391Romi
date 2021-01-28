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
      if (result.hasTargets()) {
         List<PhotonTrackedTarget> targets = result.getTargets();
         return targets.size();
      }
      return 0;
   }

   public boolean hasTarget(){
      return camera.hasTargets();
   }
   
   public double getHorizontalError(){
      // Get the latest pipeline result.
      PhotonPipelineResult result = camera.getLatestResult();

      if (result.hasTargets()) {
         return result.targets.get(0).getYaw();
      }
      return 0;
   }

   public void setPipeline(int index) {
      camera.setPipelineIndex(index);
   }
}
