package com.project.bikersden;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BikeSetups extends AppCompatActivity {

    private Button btnUpload;
    private GridLayout gridLayout;
    private List<Bitmap> uploadedImages;

    private ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_setups);

        btnUpload = findViewById(R.id.btnUpload);
        gridLayout = findViewById(R.id.gridLayout);
        uploadedImages = new ArrayList<>();

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            Uri selectedImageUri = data.getData();

                            try {
                                InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                                Bitmap selectedImageBitmap = BitmapFactory.decodeStream(inputStream);

                                // Resize and crop the image to fit the square size
                                Bitmap croppedImageBitmap = getCroppedBitmap(selectedImageBitmap);

                                ImageView imageView = new ImageView(BikeSetups.this);
                                imageView.setImageBitmap(croppedImageBitmap);

                                // Add the ImageView to the GridLayout
                                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                                params.width = getResources().getDisplayMetrics().widthPixels / 3; // Adjust as needed
                                params.height = getResources().getDisplayMetrics().widthPixels / 3; // Adjust as needed
                                imageView.setLayoutParams(params);
                                gridLayout.addView(imageView);

                                // Add the uploaded image to the list
                                uploadedImages.add(croppedImageBitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(intent);
            }
        });
    }

    private Bitmap getCroppedBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int size = Math.min(width, height);

        int x = (width - size) / 2;
        int y = (height - size) / 2;

        Bitmap croppedBitmap = Bitmap.createBitmap(bitmap, x, y, size, size);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(croppedBitmap, size, size, false);

        return scaledBitmap;
    }
}
