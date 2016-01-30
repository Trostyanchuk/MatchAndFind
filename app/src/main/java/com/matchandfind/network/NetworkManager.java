package com.matchandfind.network;

import com.matchandfind.network.wrappers.PersonsExtendedCallbackWrapper;
import com.matchandfind.network.wrappers.RefreshSuccessCallbackWrapper;

import org.testpackage.test_sdk.android.testlib.API;

public class NetworkManager implements INetworkManager {


    @Override
    public void getPersonsJSON(PersonsExtendedCallbackWrapper callbackWrapper) {
        API.INSTANCE.getPersons(0, callbackWrapper);
    }

    @Override
    public void refreshPersons(RefreshSuccessCallbackWrapper callbackWrapper) {
        API.INSTANCE.refreshPersons(callbackWrapper);
    }

    @Override
    public void subscribeUpdates() {

    }

    @Override
    public void unSubscribeUpdates() {

    }
}
