package com.y4games;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SpinTileActivity extends Activity {

	private int mTileDimension;
	private ImageView mWholeImage;
	
	private static final int LOAD_FROM_GALLERY = 0;
	private static final int LOAD_FROM_CAMERA = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_spin_tile);
		
		
		
		super.onCreate(savedInstanceState);
	}

	private void messTiles() {
		
	}

	private void segmentToTiles(ImageView image) {
		
	}

	private void confirmImageEligibility(ImageView image) {
		
	}
	

	private void setSampleImage() {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),  R.drawable.sample_image_jpg);
        mWholeImage.setImageBitmap(bitmap);
	}

	public void onClickLoadImageFromGallery(View v) {
		// Open Gallery
		Intent intent = new Intent(Intent.ACTION_PICK) ;
		intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE) ;
		startActivityForResult(intent, LOAD_FROM_GALLERY) ;
	}
	
	public void onClickLoadImageFromCamera(View v) {
		// Run camera
		Intent intent = new Intent() ;

		// Designate temporary path to store camera shot
		File file = new File(Environment.getExternalStorageDirectory(), "/MyJigsaw/" + "camera_shot") ;
		String tempPictuePath = file.getAbsolutePath() ;
		intent.putExtra(MediaStore.EXTRA_OUTPUT, tempPictuePath) ;
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE) ; // 모든 단말에서 안될 수 있기 때문에 수정해야 함
		startActivityForResult(intent, LOAD_FROM_CAMERA) ;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		SetImage setImage = new SetImage( ) ;
		if( resultCode == RESULT_OK )
		{
			if( requestCode == LOAD_FROM_CAMERA )
			{
				if( data == null) return ;
				// show image
				Bitmap bm = (Bitmap) data.getExtras().get( "data" );
				setImage.setCameraImage( bm, ivCardImage, tempPictuePath ) ;
			}
			else if( requestCode == LOAD_FROM_GALLERY )
			{
				Uri currImageURI = data.getData( ) ;
				String path = getRealPathFromURI( currImageURI ) ;
				tempPictuePath = path ;
				// show image
				setImage.setAlbumImage( path, ivCardImage ) ;
			}
		}
		else
		{
			System.out.println( "camera return error" ) ;
			return ;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public void onClickStartGame(View v) {
		//check: image loading
		if(mWholeImage == null) {
			Toast.makeText(this, "Load image before start.", Toast.LENGTH_SHORT);
			return;
		}
		
		//check: tile dimension
		EditText edittext = (EditText)findViewById(R.id.tile_dimen);
		if(edittext == null) {
			Toast.makeText(this, "Enter tile dimension\nbefore start.", Toast.LENGTH_SHORT);
			return;
		}
		mTileDimension = Integer.valueOf(edittext.getText().toString());
		
		
		// TODO check and make rectangle image
		confirmImageEligibility(mWholeImage);
		
		// TODO segment image into tiles
		segmentToTiles(mWholeImage);
		
		// TODO mess up directions of tiles
		messTiles();
		
		// TODO draw playboard in layout
	}

	
	@Override
	protected void onDestroy() {
		// ask if current status needs to be saved.

		super.onDestroy();
	}
	
	private class Tile extends ImageView implements android.view.View.OnClickListener {

		android.graphics.Matrix mMatrix;
		
		public Tile(Context context) {
			super(context);
			
			mMatrix = new android.graphics.Matrix();
			setScaleType(ImageView.ScaleType.MATRIX);
		}

		@Override
		public void onClick(View v) {
			//TODO rotate tile right-wise
			
		}
		
		private void rotateRight() {
			mMatrix.postRotate(90f);
			setImageMatrix(mMatrix);
		}
		
	}

}
