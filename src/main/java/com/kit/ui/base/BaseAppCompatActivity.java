package com.kit.ui.base;

import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.IdRes;

import com.kit.app.ActivityManager;
import com.kit.utils.log.Zog;

/**
 * @author joeyzhao
 */
public abstract class BaseAppCompatActivity extends LifecycleKotlinCoroutineActivity implements BaseV4Fragment.OnFragmentInteractionListener, View.OnClickListener {


    private boolean isShowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        darkMode();
        setContentView(layoutResId());
        this.views = new SparseArray<>();
        getExtras();
        initWidget();
        loadData();
        initWidgetWithExtra();


        ActivityManager.getInstance().pushActivity(this);
        isShowing = true;
    }

    protected void darkMode() {
//        if (DarkMode.isDarkMode()) {
//            setTheme(R.style.main_theme_dark);
//        }else {
//            setTheme(R.style.main_theme_light);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isShowing = true;
    }

    public boolean isShowing() {
        return isShowing;
    }

    /**
     * 去网络或者本地加载数据
     */
    protected void loadData() {
    }


    protected void initWidgetWithExtra() {
    }

    /**
     * i
     * 获得上一个Activity传过来的值
     */
    protected void getExtras() {
    }

    /**
     * 初始化界面
     */
    protected void initWindow() {
    }

    /**
     * 初始化界面
     */
    protected void initWidget() {
    }

    protected abstract int layoutResId();

    public boolean isTranslucentOrFloating() {
        return false;
    }


    public TextView getTextView(@IdRes int viewId) {
        return getView(viewId);
    }

    public ImageView getImageView(@IdRes int viewId) {
        return getView(viewId);
    }

    public ImageButton getImageButton(@IdRes int viewId) {
        return getView(viewId);
    }

    public Button getButton(@IdRes int viewId) {
        return getView(viewId);
    }

    public EditText getEditText(@IdRes int viewId) {
        return getView(viewId);
    }

    public RatingBar getRatingBar(@IdRes int viewId) {
        return getView(viewId);
    }


    public ProgressBar getProgressBar(@IdRes int viewId) {
        return getView(viewId);
    }

    protected void onViewClick(View view) {

    }

    @Override
    public void onClick(View view) {
        if (view == null || view.getId() == 0) {
            return;
        }

        onViewClick(view);

    }

    protected void setOnClickListener(View... views) {
        if (views == null || views.length <= 0) {
            return;
        }

        for (View v : views) {
            if (v == null) {
                continue;
            }
            v.setOnClickListener(this);
        }
    }

    public View view(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    private SparseArray<View> views;


    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing()) {
            destroy();
        }
        isShowing = false;
    }


    @Override
    protected void onDestroy() {
        try {
            super.onDestroy();
        } catch (RuntimeException e) {
            Zog.showException(e);
        }
    }


    public void destroy() {
        ActivityManager.getInstance().popActivity(this);
        views.clear();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
//
//        return super.onOptionsItemSelected(item);
//    }
//


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
