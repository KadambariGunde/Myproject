package myapplication.bits;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class PhotoshopActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivResult;
    private ImageButton ibGrayScale;
    private ImageButton ibBlacknWhite;
    private ImageButton ibBright;
    private ImageButton ibRed;
    private ImageButton ibBlue;
    private ImageButton ibGreen;
    private ImageButton ibDark;
    private ImageButton ibCamera;
    private ImageButton ibGallery;
    private ImageButton ibGalleryImage;
    Bitmap mOriginalImage;
    Intent incomingIntent;
    private ImageButton ibOriginal;
    private Bitmap operation;
    Uri imageUri;
    Bitmap receivedImage;
    private static final int CAMERA_IMAGE = 2;
    public static final int REQUEST_CODE_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoshop_activity_layout);

        ivResult = (ImageView) findViewById(R.id.ivResult);
        mOriginalImage = ((BitmapDrawable) ivResult.getDrawable()).getBitmap();

        ibGrayScale = (ImageButton) findViewById(R.id.ibGrayScale);
        ibGrayScale.setOnClickListener(this);

        ibBlacknWhite = (ImageButton) findViewById(R.id.ibBlacknWhite);
        ibBlacknWhite.setOnClickListener(this);

        ibOriginal = (ImageButton) findViewById(R.id.ibOriginal);
        ibOriginal.setOnClickListener(this);

        ibBright = (ImageButton) findViewById(R.id.ibBright);
        ibBright.setOnClickListener(this);

        ibRed = (ImageButton) findViewById(R.id.ibRed);
        ibRed.setOnClickListener(this);

        ibBlue = (ImageButton) findViewById(R.id.ibBlue);
        ibBlue.setOnClickListener(this);

        ibDark = (ImageButton) findViewById(R.id.ibDark);
        ibDark.setOnClickListener(this);

        ibGreen = (ImageButton) findViewById(R.id.ibGreen);
        ibGreen.setOnClickListener(this);

        applyImageEffect();

        ibCamera = (ImageButton) findViewById(R.id.ibCamera);
        ibCamera.setOnClickListener(this);

        ibGallery = (ImageButton) findViewById(R.id.ibGallery);
        ibGallery.setOnClickListener(this);

        ibGalleryImage = (ImageButton) findViewById(R.id.ibGalleryImage);
        ibGalleryImage.setOnClickListener(this);
    }

    public void applyImageEffect() {

        toGrayScale(mOriginalImage);
        toBlackAndWhite(mOriginalImage);
        toOriginal(mOriginalImage);
        bright(mOriginalImage);
        red(mOriginalImage);
        blue(mOriginalImage);
        dark(mOriginalImage);
        green(mOriginalImage);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Starting activity from navigator
        incomingIntent = getIntent();
        try {
            imageUri = (Uri) incomingIntent.getExtras().get(Intent.EXTRA_STREAM);
            ivResult.setImageURI(imageUri);
        } catch (Exception e) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK) {
            Uri uri = intent.getData();
            ivResult.setImageURI(uri);
            try {
                mOriginalImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                applyImageEffect();
                setImage(ivResult);
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(), "file doesnt exists", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == CAMERA_IMAGE && resultCode == RESULT_OK) {
            mOriginalImage = (Bitmap) intent.getExtras().get("data");
            //Assigning Image to color effect buttons
            ivResult.setImageBitmap(mOriginalImage);
            applyImageEffect();
        } else {
            Toast.makeText(getApplicationContext(), "Image not captured", Toast.LENGTH_SHORT).show();
        }
    }

    public void setImage(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ivResult.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ibCamera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_IMAGE);
                ivResult.setImageBitmap(receivedImage);
                break;

            case R.id.ibGallery:
                saveImage();
                break;

            case R.id.ibGalleryImage:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
                break;

            case R.id.ibGrayScale:
                setImage(ibGrayScale);
                break;

            case R.id.ibBright:
                setImage(ibBright);
                break;

            case R.id.ibDark:
                setImage(ibDark);
                break;

            case R.id.ibOriginal:
                setImage(ibOriginal);
                break;

            case R.id.ibRed:
                setImage(ibRed);
                break;

            case R.id.ibGreen:
                setImage(ibGreen);
                break;

            case R.id.ibBlue:
                setImage(ibBlue);
                break;

            case R.id.ibBlacknWhite:
                setImage(ibBlacknWhite);
                break;
        }
    }

    //Saving Image to Gallery in Camera Folder
    public void saveImage() {
//        ivResult =
//      copyFile(new File(sourceFile), destFile);
        Toast.makeText(getApplicationContext(), "Image saved in gallery", Toast.LENGTH_SHORT).show();
    }

    private void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }

        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }

    }

    public void toOriginal(Bitmap mOriginalImage) {
        ibOriginal.setImageBitmap(mOriginalImage);
    }

    public void toGrayScale(Bitmap mOriginalImage) {
        int width, height;
        height = mOriginalImage.getHeight();
        width = mOriginalImage.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(mOriginalImage, 0, 0, paint);
        ibGrayScale.setImageBitmap(bmpGrayscale);
    }

    public void toBlackAndWhite(Bitmap mOriginalImage) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);

        Bitmap blackAndWhiteBitmap = mOriginalImage.copy(Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        paint.setColorFilter(colorMatrixFilter);

        Canvas canvas = new Canvas(blackAndWhiteBitmap);
        canvas.drawBitmap(blackAndWhiteBitmap, 0, 0, paint);

        ibBlacknWhite.setImageBitmap(blackAndWhiteBitmap);
    }

    public void bright(Bitmap mOriginalImagge) {
        operation = Bitmap.createBitmap(mOriginalImagge.getWidth(), mOriginalImagge.getHeight(), mOriginalImagge.getConfig());

        for (int i = 0; i < mOriginalImagge.getWidth(); i++) {
            for (int j = 0; j < mOriginalImagge.getHeight(); j++) {
                int p = mOriginalImagge.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r = 100 + r;
                g = 100 + g;
                b = 100 + b;
                alpha = 100 + alpha;
                operation.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
        ibBright.setImageBitmap(operation);
    }

    public void dark(Bitmap mOriginalImage) {
        operation = Bitmap.createBitmap(mOriginalImage.getWidth(), mOriginalImage.getHeight(), mOriginalImage.getConfig());

        for (int i = 0; i < mOriginalImage.getWidth(); i++) {
            for (int j = 0; j < mOriginalImage.getHeight(); j++) {
                int p = mOriginalImage.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r = r - 50;
                g = g - 50;
                b = b - 50;
                alpha = alpha - 50;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        ibDark.setImageBitmap(operation);
    }

    public void red(Bitmap mOriginalImage) {
        operation = Bitmap.createBitmap(mOriginalImage.getWidth(), mOriginalImage.getHeight(), mOriginalImage.getConfig());

        for (int i = 0; i < mOriginalImage.getWidth(); i++) {
            for (int j = 0; j < mOriginalImage.getHeight(); j++) {
                int p = mOriginalImage.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r = r + 150;
                g = 0;
                b = 0;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }
        }
        ibRed.setImageBitmap(operation);
    }

    public void green(Bitmap mOriginalImage) {
        operation = Bitmap.createBitmap(mOriginalImage.getWidth(), mOriginalImage.getHeight(), mOriginalImage.getConfig());

        for (int i = 0; i < mOriginalImage.getWidth(); i++) {
            for (int j = 0; j < mOriginalImage.getHeight(); j++) {
                int p = mOriginalImage.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

                r = 0;
                g = g + 150;
                b = 0;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
            }

        }
        ibGreen.setImageBitmap(operation);
    }

    public void blue(Bitmap mOriginalImage) {
        operation = Bitmap.createBitmap(mOriginalImage.getWidth(), mOriginalImage.getHeight(), mOriginalImage.getConfig());

        for (int i = 0; i < mOriginalImage.getWidth(); i++) {
            for (int j = 0; j < mOriginalImage.getHeight(); j++) {
                int p = mOriginalImage.getPixel(i, j);
                int r = Color.RED;
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(b);

                r = 0;
                g = 0;
                b = b + 100;
                alpha = 0;
                operation.setPixel(i, j, Color.argb(Color.alpha(b), r, g, b));
            }
        }
        ibBlue.setImageBitmap(mOriginalImage);
    }
}
