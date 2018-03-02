package mobi.idappthat.snapchat_clone_android.fragments;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import mobi.idappthat.snapchat_clone_android.R;
import mobi.idappthat.snapchat_clone_android.Views.CameraPreview;

/**
 * Created by sarthakmajithia on 16/02/18.
 */

public class CameraFragment extends Fragment {

    /**
     * ID of the Camera that needs to be accessed.
     */
    private static final int CAMERA_ID = 1;

    private CameraPreview mPreview;
    private Camera mCamera;
    private Activity mActivity;
    private ImageView imageCaptured;

    public CameraFragment() {

    }

    /**
     * Returns a new instance of this fragment
     * Can be useful when you want to send arguments from MainActivity to the fragment
     */
    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cameraView = inflater.inflate(R.layout.fragment_main, container, false);
        return cameraView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCamera = getCameraInstance(CAMERA_ID);
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(CAMERA_ID, cameraInfo);

        if (mCamera == null || cameraInfo == null) {
            Toast.makeText(mActivity, "Camera info unavailable.", Toast.LENGTH_SHORT).show();
        } else {

            final int displayRotation = mActivity.getWindowManager().getDefaultDisplay().getRotation();

            mPreview = new CameraPreview(mActivity, mCamera, cameraInfo, displayRotation);

            FrameLayout preview = view.findViewById(R.id.camera_preview);
            preview.addView(mPreview);

            imageCaptured = view.findViewById(R.id.iv_captured);

        }

    }

    private Camera getCameraInstance(int cameraId) {
        Camera c = null;
        try {
            c = Camera.open(cameraId);
        } catch (Exception e) {
//            Toast.makeText(mActivity, "Camera " + cameraId + " is unavailable: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return c;
    }

    public void takePicture() {
        mCamera.takePicture(null, null, pictureCallback);

    }

    Camera.PictureCallback pictureCallback=new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {

            try {
                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + "/snap-clone");
                File file = new File (myDir, "test.jpeg");
                Ion.with(mActivity.getApplicationContext())
                        .load("http://localhost:8080/upload")
                        .setMultipartParameter("name", "source")
                        .setMultipartFile("image", "image/jpeg", file)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                //do stuff with result
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    };
}
