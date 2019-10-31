package com.edmar.gerenciador_cursos_api.realtime;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.edmar.gerenciador_cursos_api.events.RealtimeEvent;
import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;
import com.edmar.gerenciador_cursos_api.miniCurso.service.MiniCursoService;

@RestController
@RequestMapping("/api/realtime")
@CrossOrigin("*")
@Component
public class RealTimecontroller implements ApplicationListener<RealtimeEvent> {

	private final List<SseEmitter> listEmmitter = new ArrayList<>();
	
	@Autowired
	private MiniCursoService minicursoService;

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
	public ResponseBodyEmitter createConnectionSse() {

		final SseEmitter emitter = new SseEmitter(600000000000000L);
		
		List<MiniCurso> minicursos = this.minicursoService.listar();
		
		List<MinicursoSSeDto> minicursosDto = minicursos.stream()
				.map(MinicursoSSeDto::convertToDto)
				.collect(Collectors.toList());

		try {
			listEmmitter.add(emitter);
			emitter.send(minicursosDto, MediaType.APPLICATION_JSON); 

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

	@Override
	public void onApplicationEvent(RealtimeEvent event) {
		
		List<MiniCurso> minicursos = this.minicursoService.listar();
		
		List<MinicursoSSeDto> minicursosDto = minicursos.stream()
				.map(MinicursoSSeDto::convertToDto)
				.collect(Collectors.toList());

		// TODO Auto-generated method stub
		listEmmitter.stream().forEach(emitter -> {
			try {
				emitter.send(minicursosDto, MediaType.APPLICATION_JSON);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
}
