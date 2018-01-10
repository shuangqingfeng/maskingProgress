package com.afeng.maskingprogress;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.afeng.maskingprogress.imageloader.GlideImageLoader;
import com.afeng.maskingprogress.imageview.MaskingProgressView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;

import static com.lzy.imagepicker.ImagePicker.REQUEST_CODE_PREVIEW;


public class MainActivity extends AppCompatActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    private static final String TAG = "MainActivity";
    private MaskingProgressView masking;
    private RecyclerView imagePickereRV;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 5;               //允许选择图片最大数
    public static final int REQUEST_CODE_SELECT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImagePicker();
        initView();

    }

    private void initView() {
        imagePickereRV = findViewById(R.id.rv_imagePicker);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        imagePickereRV.setLayoutManager(new GridLayoutManager(this, 4));
        imagePickereRV.setHasFixedSize(true);
        imagePickereRV.setAdapter(adapter);
    }

    public void selectImage(View view) {
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
        Intent intent1 = new Intent(MainActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
        startActivityForResult(intent1, REQUEST_CODE_SELECT);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }


    @Override
    public void onItemClick(View view, int position) {

    }

    ArrayList<ImageItem> images = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ImageBean obj = (ImageBean) msg.obj;
            switch (msg.what) {
                case 0:
                    Log.d(TAG, "handleMessage0: " + obj.getPercent());
                    break;
                case 1:
                    Log.d(TAG, "handleMessage1: " + obj.getPercent());
                    break;
                case 2:
                    Log.d(TAG, "handleMessage2: " + obj.getPercent());
                    break;
//                case 3:
//
//                    break;
//                case 4:
//
//                    break;
//                case 5:
//
//                    break;

                default:
            }

        }
    };

    public void pullServer(View view) {
        for (int i = 0; i < selImageList.size(); i++) {
            ImageBean imageBean = new ImageBean();
            imageBean.setTag(i + "");
            for (int j = 0; j < 11; j++) {
                imageBean.setPercent(i + 1 * 10);
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.obj = imageBean;
                handler.sendMessageDelayed(obtain, j * 100);
            }
        }
    }

}
