package com.bruce.poster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.ArrayList;
import androidx.core.content.ContextCompat;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/***
 * 小程序菊花码合成工具类
 */
public class MGMImageMergeUtils {
    Context context;
    Disposable disposable = null;

    // 注 下方单位都为 pix
    private int totalWidth = 1080;// 需要生成的图片总宽度
    private int totalHeight = 1580;// 需要生成的图片总高度
    private int imgPadding = 28;// 大图片与边框间距
    private int logoPaddingTop = 86;// logo 图片与大图的间距
    private int logoHeight = 85;// logo 高度 
    private int logoPaddingLeft = 47;// logo 与边框左边距
    private int logoWidth = 402;// logo 宽度 
    private int qrRightPadding = 47;// 菊花码与右边距
    private int qrWidth = 240;// 菊花码宽度
    private int qrPaddingBigImage = 88;// 菊花码宽度
    private int textDesSize = 45;// 说明文字的大小
    private int textDesWidth = 560;// 说明文字的宽度
    private int textQrSize = 30;// 识别二维码文字大小
    
    MergeListener merGegeListener;
//    LocalConfig localConfig;
    private String shareText="";
    private String qrRequestPath="http://img0.imgtn.bdimg.com/it/u=3288432052,636858092&fm=26&gp=0.jpg"; // 获取小程序二维码url

//    protected CustomDialog mLoadingDialog;

    public MGMImageMergeUtils(Context context){
        this.context = context;
//        this.localConfig = new LocalConfig(context);
//        initDialog(context);
    }
    
    public MGMImageMergeUtils setMerGegeListener(MergeListener merGegeListener) {
        this.merGegeListener = merGegeListener;
        return this;
    }
    public MGMImageMergeUtils setShareText(String shareText) {
        this.shareText = shareText;
        return this;
    }
    public interface MergeListener {
        void getPicSuccess(Bitmap path);
        void getPicErr(String errMsg);
    }
    
    /***
     * 根据业务下载并合成图片
     * @param bigUrl 大图 url
     * @param qrPath 小程序 path
     */
    public void downLoadPics(String bigUrl, String qrPath,String scene) {
        bigUrl = "http://img0.imgtn.bdimg.com/it/u=3288432052,636858092&fm=26&gp=0.jpg";

        String finalBigUrl = bigUrl;
        Observable bigPicDownObservable = Observable.create((ObservableEmitter<File> e) -> {
            e.onNext(Glide.with(context)
                    .load(finalBigUrl)
                    .downloadOnly(100, 100)
                    .get());
            e.onComplete();
        }).subscribeOn(Schedulers.io());

        String qrUrl = "http://img0.imgtn.bdimg.com/it/u=3288432052,636858092&fm=26&gp=0.jpg";
        
        Observable qrPicDownObservable = Observable.create((ObservableEmitter<File> e) -> {
            e.onNext(Glide.with(context)
                    .load(qrUrl)
                    .downloadOnly(100, 100)
                    .get());
            e.onComplete();
        }).subscribeOn(Schedulers.io());
        
        disposable = Observable.zip(bigPicDownObservable, qrPicDownObservable, (File file, File file2) -> {
            ArrayList<File> files = new ArrayList<>();
            files.add(file);
            files.add(file2);
            return files;
        }).subscribe((Consumer<ArrayList<File>>) files -> {
            Bitmap bitmap = mergeMGMPhoto(context, BitmapFactory.decodeFile(files.get(0).getPath()), BitmapFactory.decodeFile(files.get(1).getPath()));
//            mLoadingDialog.dismiss();
            if (merGegeListener != null) {
                merGegeListener.getPicSuccess(bitmap);
            }
            disposable.dispose();
        }, throwable -> {
//            mLoadingDialog.dismiss();
            if (merGegeListener != null) {
                merGegeListener.getPicErr("获取图片失败，请重试");
            }
            disposable.dispose();
        });

    }

    /***
     *
     * @param mContext
     * @param bigImage 大图
     * @param qrBitmap 菊花码图片
     * @return
     */
    public Bitmap mergeMGMPhoto(Context mContext, Bitmap bigImage, Bitmap qrBitmap) {
        int width = bigImage.getWidth();//宽度需要使用原始图片宽度，否则华为曲面计算会有问题
        int height = bigImage.getHeight();

        // 如果不是正方形原图,或者是正方形，但是不是1080px的图片，裁剪成宽高为1080px的正方形、
        Bitmap bitmap;
        int minSide = width > height ? height : width; // 取短边

        if (width != height) {
            bitmap = centerSquareScaleBitmap(bigImage, minSide);
        } else {
            bitmap = bigImage;// 如果图片满足条件，不进行裁剪
        }

        //对图片进行组装
        Bitmap bitmapLogo = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.sephora_text_logo);//logo 图片

        Bitmap resultBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawColor(ContextCompat.getColor(mContext, R.color.colorAccent));

        // 画正方形图
        Rect bigImageSrc = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF bigImageDst = new RectF(imgPadding, imgPadding, totalWidth - imgPadding, totalWidth - imgPadding);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, bigImageSrc, bigImageDst, paint);

        // 丝芙兰logo 图
        Rect bitmapLogoSrc = new Rect(0, 0, bitmapLogo.getWidth(), bitmapLogo.getHeight());
        RectF bitmapLogoDst = new RectF(logoPaddingLeft, totalWidth + logoPaddingTop, logoPaddingLeft + logoWidth, totalWidth + logoPaddingTop + logoHeight);
        canvas.drawBitmap(bitmapLogo, bitmapLogoSrc, bitmapLogoDst, paint);

        // 丝芙兰菊花码 图
        Rect qrBitmapSrc = new Rect(0, 0, qrBitmap.getWidth(), qrBitmap.getHeight());
        RectF qrBitmapDst = new RectF(totalWidth - qrRightPadding - qrWidth, totalWidth + qrPaddingBigImage, totalWidth - qrRightPadding, totalWidth + qrPaddingBigImage + qrWidth);
        canvas.drawBitmap(qrBitmap, qrBitmapSrc, qrBitmapDst, paint);

        //文字绘制 长按识别小程序 
        Paint qrTextPaint = new Paint();
        qrTextPaint.setTextSize(textQrSize);//设置字体大小
        qrTextPaint.setColor(Color.BLACK);
        qrTextPaint.setTextAlign(Paint.Align.CENTER);
        String text2 = "长按识别小程序";
        // x，y 为文本中心点
        canvas.drawText(text2, totalWidth - qrRightPadding - qrWidth / 2, totalWidth + qrPaddingBigImage + qrWidth + 47, qrTextPaint);//底部文字

        //绘制文字  送你108超大红包，拿去买买买！丝芙兰冬日大牌满减折扣享不停！
        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.parseColor("#333333"));
        textPaint.setFakeBoldText(true);
        textPaint.setTextSize(textDesSize);
        textPaint.setAntiAlias(true);
        StaticLayout.Builder builder = StaticLayout.Builder.obtain(shareText, 0, shareText.length(), textPaint, textDesWidth)
                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                .setLineSpacing(0.0f, 1.2f )
                .setIncludePad(false)
                .setEllipsize(TextUtils.TruncateAt.END)
                .setMaxLines(3);

        canvas.save();
        canvas.translate(logoPaddingLeft, totalWidth + logoPaddingTop + logoHeight + 20);
        builder.build().draw(canvas);
        canvas.restore();
        
        
        return resultBitmap;
    }


    /**
     * @param bitmap     原图
     * @param edgeLength 希望得到的正方形部分的边长
     * @return 缩放截取正中部分后的位图。
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if (null == bitmap || edgeLength <= 0) {
            return null;
        }

        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if (widthOrg >= edgeLength && heightOrg >= edgeLength) {
            //压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = (edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;

            try {
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            } catch (Exception e) {
                return null;
            }

            //从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            } catch (Exception e) {
                return null;
            }
        }

        return result;
    }
    
//    private void initDialog(Context context){
//        if(mLoadingDialog==null){
//            CustomDialog.Builder builder = new CustomDialog.Builder(context);
//            builder.setStyle(CustomDialog.STYLE_PROGRESS);
//            builder.setMessage(context.getString(R.string.loading));
//            mLoadingDialog = builder.build();
//            mLoadingDialog.setCanceledOnTouchOutside(false);
//            mLoadingDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
//        }
//
//    }
}
