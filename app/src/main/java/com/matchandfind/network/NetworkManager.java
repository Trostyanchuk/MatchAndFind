package com.matchandfind.network;

import com.matchandfind.network.wrappers.PersonsExtendedCallbackWrapper;
import com.matchandfind.network.wrappers.RefreshSuccessCallbackWrapper;

import org.testpackage.test_sdk.android.testlib.API;
import org.testpackage.test_sdk.android.testlib.services.UpdateService;

public class NetworkManager implements INetworkManager {

    @Override
    public void getPersonsJSON(PersonsExtendedCallbackWrapper callbackWrapper) {
        API.INSTANCE.getPersons(0, callbackWrapper);
    }

    @Override
    public void getPersonsJSON(int page, PersonsExtendedCallbackWrapper callbackWrapper) {
        API.INSTANCE.getPersons(page, callbackWrapper);
    }

    @Override
    public void refreshPersons(RefreshSuccessCallbackWrapper callbackWrapper) {
        API.INSTANCE.refreshPersons(callbackWrapper);
    }

    @Override
    public void subscribeUpdates(UpdateService.UpdateServiceListener listener) {
        API.INSTANCE.subscribeUpdates(listener);
    }

    @Override
    public void unSubscribeUpdates() {
        API.INSTANCE.unSubscribeUpdates();
    }
}
