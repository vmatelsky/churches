package com.churches.by.data.model.receivers;

import com.churches.by.data.model.Church;

import java.util.List;

import rx.functions.Action1;

public abstract class ChurchesReceiver implements Action1<List<Church>> {
}
