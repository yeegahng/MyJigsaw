package com.y4games;

public class SetImage {
	// �̹��� ������¡
	Drawable resize( String path )
	{
		/// ��Ʈ�� ������ ������ �ϳ� �����Ѵ�.
	    Bitmap bmp ;
	    /// �̹��� �ҷ��ö� ����� �ɼ��� �����.
	    BitmapFactory.Options options2 =  new BitmapFactory.Options( );
	    /// �̹��� �����ö� ���ø��� �ɼ�
	    /// ���� Ŭ���� �۰� �����´�.
	    options2.inSampleSize = 4 ;
	    
	    /// �ش� �ɼ��� ����Ͽ� �̹����� �����´�.
	    /// �Ʒ� �ٿ��� ���� �̹����� �޸𸮿� ���� �ε������� �ʴ´�.
	    bmp = BitmapFactory.decodeFile( path, options2 ) ;
	    
	    /// ������ �̹����� Bitmap(��Ʈ��)�̶� Drawable�� �ٲ�����Ѵ�.
	    /// Drawable������ Bitmap�� �ٲ۴�.
	    /// ���� �޸𸮿� �̹����� �ε��Ѵ�.
	    BitmapDrawable dbmp = new BitmapDrawable( bmp );
	    /// Drawable ���·� ����ȯ �ؼ� ��ȯ�Ѵ�.
	    return (Drawable)dbmp ;
	}
	
	/*
	 * setCameraImageBackground, setAlbumImageBackground
	 * �� �ΰ��� �̹������� ����� �� ���� �����Ѵ�.
	 * 
	 * setCameraImageDrawable, setAlbumImageDrawble
	 * �� �ΰ��� �̹����� ������ ���� �����Ѵ�.
	 * �� ImageView�� android:scaleType="fitStart" �ɼ��� �����ϴ°� ��������
	 */
	
	// ī�޶�� ���� ������ �̹����信 ����
	public void setCameraImageBackground( Bitmap bm, ImageView iv, String tempPictuePath )
	{
		BitmapDrawable bmd = new BitmapDrawable( bm ) ;
		
		// tempPictuePath�� ���� ���� ��θ� �ٲ� ���� ī�޶�� ������ ����� �� ������ ����̴�.
		File copyFile = new File( tempPictuePath ) ;
	    try 
	    {
	    	copyFile.createNewFile( ) ;
			OutputStream out = null;
			out = new FileOutputStream(copyFile);
			bm.compress(CompressFormat.JPEG, 70, out) ;
			out.close( ) ;
			
			if( copyFile.exists( ) && copyFile.length( ) > 0 ) /// ���������������� ����Ǿ� ������
			{
				DrawbleResize resize = new DrawbleResize( ) ;
				Drawable dr = resize.resize( tempPictuePath ) ;
				iv.setBackgroundDrawable( dr ) ;
			}
			else
			{
				
			}
		} 
	    catch( Exception e ) 
	    {
			e.printStackTrace();
		}
	}
	
	// ī�޶�� ���� ������ �̹����信 ����
	public void setCameraImageDrawable( Bitmap bm, ImageView iv, String tempPictuePath )
	{
		BitmapDrawable bmd = new BitmapDrawable( bm ) ;
		
		// tempPictuePath�� ���� ���� ��θ� �ٲ� ���� ī�޶�� ������ ����� �� ������ ����̴�.
		File copyFile = new File( tempPictuePath ) ;
	    try 
	    {
	    	copyFile.createNewFile( ) ;
			OutputStream out = null;
			out = new FileOutputStream(copyFile);
			bm.compress(CompressFormat.JPEG, 70, out) ;
			out.close( ) ;
			
			if( copyFile.exists( ) && copyFile.length( ) > 0 ) /// ���������������� ����Ǿ� ������
			{
				DrawbleResize resize = new DrawbleResize( ) ;
				Drawable dr = resize.resize( tempPictuePath ) ;
				iv.setImageDrawable( dr ) ;
				//iv.setBackgroundDrawable( dr ) ;
			}
			else
			{
				
			}
		} 
	    catch( Exception e ) 
	    {
			e.printStackTrace();
		}
	}
	
	// �ٹ����� ������ ������ �̹����信 ����
	public void setAlbumImageBackground( String path, ImageView iv )
	{
        BitmapFactory.Options options = new BitmapFactory.Options( ) ; 	// ��Ʈ�� �̹����� �ɼ� ���� ���� ����
        options.inJustDecodeBounds = true;  							// ��Ʈ���� �ٷ� �ε����� ���� �ɼǸ� �޾ƿ���� ����
        BitmapFactory.decodeFile( path, options ) ; 					// ��Ʈ���� �дµ� ���ٿ� ���� �ɼǸ� �޾ƿ��� ��Ʈ�� �� ������ ����

		/// �̹����� ���̸� ����
        int fscale = options.outHeight ;
        if( options.outWidth > options.outHeight )	// �̹����� ���̺��� ���̰� Ŭ ���
        {
        	fscale = options.outWidth ;				// �̹����� ���̸� �����Ͽ� ����
        }

        Bitmap bmp ;	// ���� �̹����� ������ ����
        
		/// �̹����� ���̰� 800���� ũ��
        if( 800 < fscale )
        {
			/// �̹����� ����� 800���� ���� ��ŭ ������¡ �ҰŴ�
        	fscale = fscale / 800 ;
			/// �� ��Ʈ�� �ɼ� ����
	        BitmapFactory.Options options2 = new BitmapFactory.Options();
	        options2.inSampleSize = fscale ;	/// ������¡�� ���� ����
	        bmp = BitmapFactory.decodeFile( path, options2 ) ;	/// ������ ��Ʈ���� �о�´�.
        }
        else
        {	/// ����� ���� �ϸ� �׳� �д´�.
        	bmp = BitmapFactory.decodeFile( path ) ;
        }
		/// ���� ��Ʈ���� ����ȯ�ؼ� ���� ����
        BitmapDrawable dbmp = new BitmapDrawable( bmp );
		Drawable dr = (Drawable)dbmp ;	/// �װ� �ٽ� ����ȯ
		iv.setBackgroundDrawable( dr ) ; /// �� ��ü�� ��׶���� ����
	}
	
	public void setAlbumImageDrawble( String path, ImageView iv )
	{
        BitmapFactory.Options options = new BitmapFactory.Options( ) ; 	// ��Ʈ�� �̹����� �ɼ� ���� ���� ����
        options.inJustDecodeBounds = true;  							// ��Ʈ���� �ٷ� �ε����� ���� �ɼǸ� �޾ƿ���� ����
        BitmapFactory.decodeFile( path, options ) ; 					// ��Ʈ���� �дµ� ���ٿ� ���� �ɼǸ� �޾ƿ��� ��Ʈ�� �� ������ ����

		/// �̹����� ���̸� ����
        int fscale = options.outHeight ;
        if( options.outWidth > options.outHeight )	// �̹����� ���̺��� ���̰� Ŭ ���
        {
        	fscale = options.outWidth ;				// �̹����� ���̸� �����Ͽ� ����
        }

        Bitmap bmp ;	// ���� �̹����� ������ ����
        
		/// �̹����� ���̰� 800���� ũ��
        if( 800 < fscale )
        {
			/// �̹����� ����� 800���� ���� ��ŭ ������¡ �ҰŴ�
        	fscale = fscale / 800 ;
			/// �� ��Ʈ�� �ɼ� ����
	        BitmapFactory.Options options2 = new BitmapFactory.Options();
	        options2.inSampleSize = fscale ;	/// ������¡�� ���� ����
	        bmp = BitmapFactory.decodeFile( path, options2 ) ;	/// ������ ��Ʈ���� �о�´�.
        }
        else
        {	/// ����� ���� �ϸ� �׳� �д´�.
        	bmp = BitmapFactory.decodeFile( path ) ;
        }
		/// ���� ��Ʈ���� ����ȯ�ؼ� ���� ����
        BitmapDrawable dbmp = new BitmapDrawable( bmp );
		Drawable dr = (Drawable)dbmp ;	/// �װ� �ٽ� ����ȯ
		iv.setImageDrawable( dr ) ; /// �� ��ü�� ��׶���� ����
		//iv.setBackgroundDrawable( dr ) ; /// �� ��ü�� ��׶���� ����
	}
}
