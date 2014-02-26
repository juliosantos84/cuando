package com.everythingbiig.cuando.context;


public interface ContextAware {

	String getName();
	void receiveUpdate(Context context);
	void onReceiveContext();
	boolean bindToContextAwarenessService();
}
