package com.mpt.android.spannabletextview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mpt.android.stv.Slice;
import com.mpt.android.stv.SpannableTextView;
import com.mpt.android.stv.callback.OnTextClick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements OnTextClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpannableTextView stvAddress = findViewById(R.id.stvAddress);
        SpannableTextView stvMarksTop = findViewById(R.id.stvMarksTop);
        SpannableTextView stvMarksDown = findViewById(R.id.stvMarksDown);
        SpannableTextView stvFormula = findViewById(R.id.stvFormula);
        SpannableTextView stvOffer = findViewById(R.id.stvOffer);
        SpannableTextView stvImageSpan = findViewById(R.id.stvImageSpan);
        TextView spanTest = findViewById(R.id.spanTest);
        ImageView layoutBitmapBtn = findViewById(R.id.layoutBitmapBtn);

        stvAddress.addSlice(new Slice.Builder("WebMob Technologies\n1")
                .style(Typeface.BOLD)
                .build());
        stvAddress.addSlice(new Slice.Builder("st")
                .superscript()
                .build());
        stvAddress.addSlice(new Slice.Builder(" floor,111, N Market St. #300,\n")
                .textColor(Color.parseColor("#616161"))
                .build());
        stvAddress.addSlice(new Slice.Builder("San Jose, California ,\n95113, USA")
                .build());
        stvAddress.display();

        stvAddress.addSlice(new Slice.Builder("st")
                .superscript()
                .build());

        stvMarksTop.addSlice(new Slice.Builder("  9.5/10  ")
                .backgroundColor(Color.parseColor("#073680"))
                .textColor(Color.WHITE)
                .build());
        stvMarksTop.addSlice(new Slice.Builder(" excellent! ")
                .backgroundColor(Color.parseColor("#DFF1FE"))
                .textColor(Color.parseColor("#073680"))
                .style(Typeface.BOLD)
                .build());
        stvMarksTop.display();
        stvMarksDown.addSlice(new Slice.Builder("  3.5/10  ")
                .backgroundColor(Color.parseColor("#800736"))
                .textColor(Color.WHITE)
                .setCornerRadius(13)
                .build());
        stvMarksDown.addSlice(new Slice.Builder(" horrible! ")
                .textColor(Color.parseColor("#073680"))
                .style(Typeface.BOLD)
                .build());
        stvMarksDown.display();


        //***
        stvFormula.addSlice(new Slice.Builder("Area= \u03C0 r")
                .underline()
                .build());
        stvFormula.addSlice(new Slice.Builder("2")
                .superscript()
                .build());

        stvFormula.addSlice(new Slice.Builder("\n\n")
                .build());
        //*****
        stvFormula.addSlice(new Slice.Builder("Water= H")
                .build());
        stvFormula.addSlice(new Slice.Builder("2")
                .subscript()
                .build());
        stvFormula.addSlice(new Slice.Builder("O")
                .build());
        stvFormula.display();

        stvOffer.addSlice(new Slice.Builder("Price is \u20B9 ")
                .build());
        stvOffer.addSlice(new Slice.Builder("1000")
                .strike()
                .build());
        stvOffer.addSlice(new Slice.Builder(" Offer Price is \u20B9 500")
                .style(Typeface.BOLD)
                .textColor(Color.parseColor("#304ffe"))
                .build());

        stvOffer.addSlice(new Slice.Builder("\n\n")
                .build());
        stvOffer.addSlice(new Slice.Builder("http://milaptank.com")
                .setOnTextClick(this)
                .underline()
                .build());

        stvOffer.display();


        stvImageSpan.addSlice(
                new Slice.Builder("Show love by press, Show love by press  ")
                        .backgroundColor(Color.parseColor("#67c8ff"))
                        .textSize(60)
                        .textColor(Color.BLUE)
                        .build());

        stvImageSpan.addSlice(
                new Slice.Builder("star")
                        .setImageResource(R.drawable.ic_star_border)
                        .textSize(40)
                        .build());

        stvImageSpan.addSlice(
                new Slice.Builder("Show love by press.")
                        .backgroundColor(Color.parseColor("#67c8ff"))
                        .textSize(60)
                        .textColor(Color.BLUE)
                        .build());
        stvImageSpan.addSlice(
                new Slice.Builder("star")
                        .setImageBitmap(layoutToBitmap())
                        .textSize(40)
                        .build()
        );
        stvImageSpan.display();

        SpannableStringBuilder builder = new SpannableStringBuilder("我是一个儿女，我来自哪里？");
        builder.setSpan(new BackgroundColorSpan(Color.parseColor("#67c8ff")), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.BLUE), 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getBaseContext(), "show toast", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(11, 22, 66));
            }
        }, 2, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.MAGENTA), 7, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//我
        builder.setSpan(new AbsoluteSizeSpan(8, true), 8, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//lai
        builder.setSpan(new RelativeSizeSpan(2), 9, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spanTest.setText(builder);
        spanTest.setMovementMethod(LinkMovementMethod.getInstance());


        layoutBitmapBtn.setImageBitmap(layoutToBitmap());
    }

    public Drawable layoutToDrawable() {

        LayoutInflater inflator = getLayoutInflater();
        View viewRoot = inflator.inflate(R.layout.user_level_layout, null);

        TextView textView = viewRoot.findViewById(R.id.tv_user_level);
        textView.setText("21");
        Bitmap snapshot = convertViewToBitmap(viewRoot);
        return new BitmapDrawable(snapshot);
    }

    public Bitmap layoutToBitmap() {
        LayoutInflater inflator = getLayoutInflater();
        View viewRoot = inflator.inflate(R.layout.user_level_layout, null);

        TextView textView = viewRoot.findViewById(R.id.tv_user_level);
        textView.setText("21");
        Bitmap snapshot = convertViewToBitmap(viewRoot);
        return snapshot;
    }
    public Bitmap convertViewToBitmap(View view) {
        view.setDrawingCacheEnabled(false);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());  //根据字符串的长度显示view的宽度
        view.buildDrawingCache();
        view.setDrawingCacheEnabled(true);

        Bitmap bitmap = getViewBitmap(view);//contentLly是布局文件
        savePhotoToSDCard(bitmap, "/sdcard/test", "test");
        //return view.getDrawingCache(true);
    return bitmap;
    }


    private Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    public static boolean checkSDCardAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static void savePhotoToSDCard(Bitmap photoBitmap, String path, String photoName) {
        if (checkSDCardAvailable()) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File photoFile = new File(path, photoName + ".png");
            if (photoFile.exists()) {
                photoFile.delete();
            }

            FileOutputStream fileOutputStream = null;
            try {
                photoFile.createNewFile();
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                        fileOutputStream.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                e.printStackTrace();
            } catch (IOException e) {
                photoFile.delete();
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }
    public static Bitmap convertViewToBitmap2(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap= Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    @Override
    public void onTextClick(View view, Slice slice) {
        Toast.makeText(this, "Website Clicked", Toast.LENGTH_LONG).show();
    }
}
