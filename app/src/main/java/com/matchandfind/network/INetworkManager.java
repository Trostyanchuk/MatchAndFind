package com.matchandfind.network;

import com.matchandfind.network.wrappers.PersonsExtendedCallbackWrapper;
import com.matchandfind.network.wrappers.RefreshSuccessCallbackWrapper;

import org.testpackage.test_sdk.android.testlib.services.UpdateService;

public interface INetworkManager {

    void refreshPersons(RefreshSuccessCallbackWrapper callback);

    void getPersonsJSON(PersonsExtendedCallbackWrapper callback);

    void getPersonsJSON(int page, PersonsExtendedCallbackWrapper callback);

    void subscribeUpdates(UpdateService.UpdateServiceListener listener);

    void unSubscribeUpdates();
}
