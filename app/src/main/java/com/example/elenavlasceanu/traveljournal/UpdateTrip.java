package com.example.elenavlasceanu.traveljournal;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateTrip extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int REQUEST_CODE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    private ImageView mImageViewTaken;
    private Bitmap mBitmap;
    private Button mSaveButton;
    private EditText mNume;
    private EditText mDestination;
    private SeekBar mPrice;
    private ImageButton mBookmark;
    private FirebaseFirestore mFirestore;
    //private DocumentReference ref;
    private Uri filePath;
    public String image;
    private ProgressBar mProgressBar;
    //private FirebaseStorage storage;
    private StorageReference mstorageRef;
    private DatabaseReference mDatabaseRef;
    Bitmap myBitmap;
    Uri picUri;
    private String tripFirebaseId;

    // ContactsContract.Profile profile;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip);
        mFirestore = FirebaseFirestore.getInstance();
        //storage=FirebaseStorage.getInstance();
        mstorageRef = FirebaseStorage.getInstance().getReference("trips");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("trips");

        mNume = (EditText) findViewById(R.id.edit_text_trip_name_update);
        mDestination = (EditText) findViewById(R.id.edit_text_trip_destination_update);
        mSaveButton = (Button) findViewById(R.id.save_trip_button_update);
        mPrice = (SeekBar) findViewById(R.id.trip_price_update);
        // mImageView=(ImageView) findViewById(R.id.)
        mProgressBar = (ProgressBar) findViewById(R.id.pg_update);
        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        String location = intent.getStringExtra("location");
        //String price =intent.getStringExtra("price");
        String picture = intent.getStringExtra("picture");
        String bookmark = intent.getStringExtra("bookmark");
        //int priceInt=Integer.parseInt(price);
        tripFirebaseId=intent.getStringExtra("tripFirebaseID");
        mNume.setText(name);
        mDestination.setText(location);
      //  mPrice.setProgress(priceInt);


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String tripName = mNume.getText().toString();
                String tripDestination = mDestination.getText().toString();
                double tripPrice = mPrice.getProgress();*/
                uploadImage();
                // String tripPrice=mPrice.getAlpha();
                //Map<String,String> tripMap = new HashMap<>();
                /*Trip tripMap = new Trip(tripName, tripDestination, image, tripPrice, true);
                // tripMap.put("name",tripName);
                // tripMap.put("location",tripDestination);
                //tripMap.put("price",);
                mFirestore.collection("trips").add(tripMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Manage_Trip.this, "Trip add to firestore", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(Manage_Trip.this, "Error:" + error, Toast.LENGTH_SHORT).show();
                    }
                });*/

            }
        });
    }

    public void btnDatePickerOnClick(View view) {
        DialogFragment newFragment = new CustomDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");

    }

    private void initView() {
        mImageViewTaken = findViewById(R.id.imageView_temporary_update);

        if (checkPermission()) {
            //main logic or main code
            // . write your main code to execute, It will execute if the permission is already given.
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            requestPermission();
        }
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            filePath = data.getData();
        if (getPickImageResultUri(data) != null) {
            filePath = getPickImageResultUri(data);
        }
        try {
            // recyle unused bitmaps
            if (mBitmap != null) {
                mBitmap.recycle();
            }
            stream = getContentResolver().openInputStream(data.getData());
            mBitmap = BitmapFactory.decodeStream(stream);
            // myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
            mImageViewTaken.setImageBitmap(mBitmap);
            //mImageViewTaken.setImageURI(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    private void showMessageOKCancel (String message, DialogInterface.OnClickListener okListener)
    {
        new AlertDialog.Builder(UpdateTrip.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void btnGalleryPictureOnClick (View view){
        initView();
    }

    public void btnTakePictureOnClick (View view){
        initViewTake();

    }

    ///din exemplul Magdei este aceasta functie
    private Uri getCaptureImageOutputUri () {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }
    //////////////

    private void dispatchTakePictureIntent () {
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList();
        PackageManager packageManager = getPackageManager();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (captureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(captureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void initViewTake () {
        mImageViewTaken = findViewById(R.id.imageview_picturet);

        if (checkPermissionTake()) {
            //main logic or main code
            // . write your main code to execute, It will execute if the permission is already given.
            dispatchTakePictureIntent();
        } else {
            requestPermissionTake();
        }
    }

    private boolean checkPermissionTake () {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermissionTake () {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    public Uri getPickImageResultUri (Intent data){
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }


        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }


    public String getFileExtension (Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public void uploadImage () {
        if (filePath != null) {
            final StorageReference fileReference = mstorageRef.child(System.currentTimeMillis() + "." + getFileExtension(filePath));
            Task uploadTask=fileReference.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl=uri;
                                    String b=downloadUrl.toString();
                                    Trip upload = new Trip(mNume.getText().toString(), mDestination.getText().toString(), b, mPrice.getProgress(), true);

                                   // FirebaseFirestore.getInstance().collection("trips").add(upload);
                                    FirebaseFirestore.getInstance().collection("trips").document(tripFirebaseId)
                                            .set(upload)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(UpdateTrip.this,"Trip updated", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                }
                            });



                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }


    }


}



