package com.exc.applibrary.main.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.exc.applibrary.R;
import com.exc.applibrary.databinding.FragmentProjectEditBinding;
import com.exc.applibrary.main.adapter.ImgAdapter;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.show.SwitchShowActivity;
import com.exc.applibrary.main.ui.dialog.SelectPicDialog;
import com.exc.applibrary.main.utils.OrderPic;
import com.exc.applibrary.main.utils.ToastUtils;
import com.exc.applibrary.main.utils.UpLoadUtil;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.progress.loading.IMessageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import lombok.SneakyThrows;
import zuo.biao.library.base.BaseActivity;

public class ProjectionInfoActivity extends BaseActivity implements View.OnClickListener {

    private FragmentProjectEditBinding binding;
    private ArrayList<OrderPic> showPicList = new ArrayList<>();
    private ImgAdapter imgAdapter;
    private SelectPicDialog selectPicDialog;
    private CustomDialog customDialog;

    public static final int TAKE_CAMERA_PERMISSION_REQUEST_CODE = 100;
    public static final int TAKE_GALLERY_PERMISSION_REQUEST_CODE = 101;
    public static final int TAKE_CAMERA_PIC_FILE_REQUEST_CODE = 200;
    public static final int TAKE_GALLERY_PIC_FILE_REQUEST_CODE = 201;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentProjectEditBinding.inflate(inflater);
        initView();
        setContentView(binding.getRoot());
    }

    @Override
    public void onClick(View v) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {
        customDialog = new CustomDialog(getActivity());

        initImgList();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    /////////////////////添加照片///////////////////////
    private void initImgList() {
        if (showPicList.size() < 6) { //最多有6张，不足6张时最后1张部位成添加按钮
            OrderPic tempOrderPic = new OrderPic();
            tempOrderPic.setVirtual(true);
            showPicList.add(tempOrderPic);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        binding.imageRecyclerview.setLayoutManager(layoutManager);
        binding.imageRecyclerview.setNestedScrollingEnabled(false);
        imgAdapter = new ImgAdapter();
        binding.imageRecyclerview.setAdapter(imgAdapter);
        imgAdapter.setList(showPicList);


        imgAdapter.setImgChildOnClickListener((clickViewId, position) -> {
            if (clickViewId == R.id.iv) {
                if (showPicList.get(position).getFileType() == 0) {
                    return;
                }
            } else if (clickViewId == R.id.ll_add) {
                selectPicDialog = new SelectPicDialog(getActivity(), btnId -> {
                    if (btnId == R.id.iv_camera) {
                        takePicFromCamera();
                    } else if (btnId == R.id.iv_gallery) {
                        takePicFromGallery();
                    }
                });
                selectPicDialog.show();
            } else if (clickViewId == R.id.iv_close) {
                new MaterialDialog.Builder(getActivity())
                        .content("您确定要删除此项吗？")
                        .positiveText("是")
                        .positiveColorRes(R.color.common_blue)
                        .negativeText("否")
                        .negativeColorRes(R.color.common_blue)
                        .onPositive((dialog, which) -> {

                            showPicList.remove(position);
                            imgAdapter.setList(showPicList);

                        })
                        .show();
            }
        });

    }

    private void takePicFromCamera() {
        //相机的话 相机权限和存储权限都需要
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                )
        ) {
            String[] permissions = new String[2];
            permissions[0] = Manifest.permission.CAMERA;
            permissions[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(permissions, TAKE_CAMERA_PERMISSION_REQUEST_CODE);
            }
            return;
        }
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_CAMERA_PIC_FILE_REQUEST_CODE);

    }

    private void takePicFromGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {

            String[] permissions = new String[2];
            permissions[0] = Manifest.permission.READ_EXTERNAL_STORAGE;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(permissions, TAKE_GALLERY_PERMISSION_REQUEST_CODE);
            }
            return;
        }

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ArrayList<String> mimeTypes = new ArrayList();
            mimeTypes.add("image/jpeg");
            mimeTypes.add("image/png");
            mimeTypes.add("image/jpg");
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        }
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "选择图片"
                ), TAKE_GALLERY_PIC_FILE_REQUEST_CODE
        );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_CAMERA_PIC_FILE_REQUEST_CODE:
                    Uri uriImageData;
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    if (null != data.getData()) {
                        uriImageData = data.getData();
                    } else {
                        uriImageData = Uri.parse(

                                MediaStore.Images.Media.insertImage(
                                        getActivity().getContentResolver(),
                                        bitmap,
                                        null,
                                        null
                                )
                        );
                    }
                    UpLoadPic(uriImageData);

                    break;
                case TAKE_GALLERY_PIC_FILE_REQUEST_CODE:
                    Uri gallerySelectUrl = data.getData();
                    UpLoadPic(gallerySelectUrl);
                    break;
                default:


                    break;
            }
        }
    }

    @SneakyThrows
    private void UpLoadPic(Uri gallerySelectUrl) {
        customDialog.show();
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = getActivity().managedQuery(gallerySelectUrl, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        File file = new File(img_path);
        UpLoadUtil.uploadImage(getActivity(), img_path, new UpLoadUtil.UpLoadImgListener() {
            @Override
            public void upLoadSuccess(int imgId) {

                OrderPic orderPic = new OrderPic();
                orderPic.setFilename(file.getPath());
                orderPic.setVirtual(false);
                orderPic.setFileType(1);
                orderPic.setId(imgId);
                orderPic.setXC(true);
                showPicList.add(showPicList.size() - 1, orderPic);
                if (showPicList.size() > 6) {//最多6张
                    showPicList.remove(showPicList.size() - 1);
                }
                getActivity().runOnUiThread(() -> {
                    imgAdapter.setList(showPicList);
                    customDialog.dismiss();
                    selectPicDialog.dismiss();

                });

            }

            @Override
            public void upLoadFail() {
                customDialog.dismiss();
                ToastUtils.showErrorToast(getActivity(),"上传失败，请重试");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case TAKE_CAMERA_PERMISSION_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    takePicFromCamera();
                    return;
                }
                //拒绝了
                ToastUtils.showNormalToast(getActivity(),"请点权限，并允许授权相机和存储权限。");
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent i = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        String pkg = "com.android.settings";
                        String cls = "com.android.settings.applications.InstalledAppDetails";
                        i.setComponent(new ComponentName(pkg, cls));
                        i.setData(Uri.parse("package:" + getActivity().getPackageName()));
                        startActivity(i);
                    }
                }, 1000);
                break;
            case TAKE_GALLERY_PERMISSION_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicFromGallery();
                    return;
                }
                //拒绝了
                ToastUtils.showNormalToast(getActivity(),"请点权限，并允许授权存储权限。");
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent i = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        String pkg = "com.android.settings";
                        String cls = "com.android.settings.applications.InstalledAppDetails";
                        i.setComponent(new ComponentName(pkg, cls));
                        i.setData(Uri.parse("package:" + getActivity().getPackageName()));
                        startActivity(i);
                    }
                }, 1000);
                break;
        }
    }

}
