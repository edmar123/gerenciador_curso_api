package com.edmar.gerenciador_cursos_api.miniCurso.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.edmar.gerenciador_cursos_api.realtime.Realtime;

@RestController
@RequestMapping("/api/sse")
@CrossOrigin("*")
@Component
public class RealTimecontroller {

	private final List<SseEmitter> listEmmitter = new ArrayList<>();

	@GetMapping("/data")
	public void atualizarDados() {
		Realtime realtime = new Realtime();
		realtime.setDados(LocalTime	.now().toString());

		this.listEmmitter.stream().forEach(emitter -> {

			try {
				emitter.send(realtime, MediaType.APPLICATION_JSON);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@GetMapping()
//    @Timed(millis = 30000)
	public ResponseBodyEmitter handleRequest() {

		final SseEmitter emitter = new SseEmitter(600000000000000L);

		try {
			Realtime realtime = new Realtime();

			realtime.setDados(LocalTime.now().toString());
			listEmmitter.add(emitter);
			emitter.send(realtime, MediaType.APPLICATION_JSON); 

			// Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
			emitter.completeWithError(e);
		}

		emitter.onTimeout(() -> {
			emitter.complete();
		});

		return emitter;
	}

	@Scheduled(fixedDelay = 5000L)
	public void update() {
		this.listEmmitter.stream().forEach(emitter -> {
			try {
			
				final String msg = "keep-alive";				
				System.out.println(msg);
				final MediaType textPlain = MediaType.TEXT_PLAIN;
				emitter.send(msg, textPlain);
			} catch (IOException e) { 
				e.printStackTrace();
			}
		});
	}
	
}
