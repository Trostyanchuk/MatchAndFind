package com.matchandfind.network;

import com.matchandfind.network.wrappers.PersonsExtendedCallbackWrapper;
import com.matchandfind.network.wrappers.RefreshSuccessCallbackWrapper;

public interface INetworkManager {

    void refreshPersons(RefreshSuccessCallbackWrapper callback);

    void getPersonsJSON(PersonsExtendedCallbackWrapper callback);

    void subscribeUpdates();

    void unSubscribeUpdates();
}
