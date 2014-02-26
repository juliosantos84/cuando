package com.everythingbiig.cuando.context;



public interface ContextAwarenessService {
	void broadcastContextUpdates();
	void registerForContextUpdates(ContextAware contextAware);
	Context getContext();
}
