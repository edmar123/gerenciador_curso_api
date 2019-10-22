package com.edmar.gerenciador_cursos_api.events;

import org.springframework.context.ApplicationEvent;

public class RealtimeEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 1L;

	public RealtimeEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
