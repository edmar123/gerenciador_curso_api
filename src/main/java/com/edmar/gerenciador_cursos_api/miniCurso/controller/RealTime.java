package com.edmar.gerenciador_cursos_api.miniCurso.controller;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api")
public class RealTime {

	@GetMapping("/sse")
	public ResponseBodyEmitter handleRequest() {

		final SseEmitter emitter = new SseEmitter(6000L);

			try {
				emitter.send(LocalTime.now().toString(), MediaType.TEXT_PLAIN);

				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
				emitter.completeWithError(e);
				
			}
		
		emitter.complete();

		return emitter;
	}

}
