package com.example.noahkim.bakingtime.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Noah on 5/24/2017.
 */

public class BakingWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetRemoteViewsFactory(this.getApplicationContext());
    }
}
