package cn.com.someday.fgnna.indexview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaojunjie on 17-11-8.
 */

public class IndexView extends LinearLayout implements ViewPager.OnPageChangeListener
{
    private int widthAndHeight;
    private int marge;
    private int mCount = 0;

    public IndexView(Context context)
    {
        super(context);
    }

    public IndexView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public IndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    public void setupByViewPager(ViewPager viewPager)
    {
        if(0 == viewPager.getAdapter().getCount())
        {
            return;
        }
        mCount = viewPager.getAdapter().getCount();
        nowPosition = 0;
        initIndexItem();
        viewPager.removeOnPageChangeListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    private void initIndexItem()
    {
        removeAllViews();
        mViews.clear();
        widthAndHeight = dpToPx(8,getContext());
        marge = dpToPx(3,getContext());
        for(int i=0;i < mCount;i++)
        {
            View view = new View(getContext());
            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(widthAndHeight,widthAndHeight);
            marginLayoutParams.setMargins(marge,marge,marge,marge);
            view.setLayoutParams(marginLayoutParams);
            view.setBackgroundResource(R.drawable.bg_round_8b92b8_r50);
            addView(view);
            mViews.add(view);
        }
        mViews.get(nowPosition).setBackgroundResource(R.drawable.bg_round_c7d0ff_r50);
    }

    private List<View> mViews = new ArrayList<>();

    private int nowPosition = 0;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {
        Log.d("IndexView","onPageScrolled:position="+position+" ; positionOffset="+positionOffset);
        if(0.0 == positionOffset && nowPosition != position)
        {
            for(int i =0;i<mCount;i++)
            {
                mViews.get(i).setBackgroundResource(R.drawable.bg_round_8b92b8_r50);
            }
            mViews.get(position).setBackgroundResource(R.drawable.bg_round_c7d0ff_r50);
            nowPosition = position;
        }
    }

    @Override
    public void onPageSelected(int position)
    {
        Log.d("IndexView","onPageSelected");
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {
        Log.d("IndexView","onPageScrollStateChanged");
    }

    /**
     * DP转PX
     */
    public static int dpToPx(float dp,Context context)
    {
        // Get the screen's density scale
        final float scale = context.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }

}
