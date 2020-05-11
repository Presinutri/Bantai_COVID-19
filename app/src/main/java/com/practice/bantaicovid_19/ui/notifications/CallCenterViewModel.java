package com.practice.bantaicovid_19.ui.notifications;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.practice.bantaicovid_19.R;
import com.practice.bantaicovid_19.dataclass.CallCenter;

import java.util.ArrayList;

public class CallCenterViewModel extends ViewModel {
    private MutableLiveData<ArrayList<CallCenter>> callCenterList = new MutableLiveData<>();

    public void setCallCenterList(Context context) {
        String[] provinsi = context.getResources().getStringArray(R.array.provinsi);
        String[] callCenter = context.getResources().getStringArray(R.array.call_center);
        ArrayList<CallCenter> list = new ArrayList<>();

        for (int i=0; i<provinsi.length; i++){
            CallCenter callCenter1 = new CallCenter();
            callCenter1.setProvinsi(provinsi[i]);
            callCenter1.setCallCenter(callCenter[i]);
            list.add(callCenter1);
        }
        callCenterList.postValue(list);
    }
    public MutableLiveData<ArrayList<CallCenter>> getCallCenter() {
        return callCenterList;
    }
}
