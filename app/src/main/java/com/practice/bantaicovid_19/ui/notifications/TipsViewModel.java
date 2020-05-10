package com.practice.bantaicovid_19.ui.notifications;

import android.content.Context;
import android.content.res.TypedArray;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.practice.bantaicovid_19.R;
import com.practice.bantaicovid_19.dataclass.Tips;

import java.util.ArrayList;

public class TipsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Tips>> tipsList = new MutableLiveData<>();

    public void setTipsList(Context context) {
        String[] tipsTitles = context.getResources().getStringArray(R.array.tips_title);
        String[] tipsLinks = context.getResources().getStringArray(R.array.tips_link);
        String[] tipsContents = context.getResources().getStringArray(R.array.tips_content);
        TypedArray tipsPhotos = context.getResources().obtainTypedArray(R.array.tips_drawable);
        ArrayList<Tips> list = new ArrayList<>();

        for (int i=0; i<tipsTitles.length; i++) {
            Tips tips = new Tips();
            tips.setTitle(tipsTitles[i]);
            tips.setLink(tipsLinks[i]);
            tips.setContent(tipsContents[i]);
            tips.setPhoto(tipsPhotos.getResourceId(i,-1));
            list.add(tips);
        }

        tipsList.postValue(list);
    }

    public MutableLiveData<ArrayList<Tips>> getTipsList() {
        return tipsList;
    }
}
