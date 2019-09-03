package com.edmar.gerenciador_cursos_api.miniCurso;

import java.time.LocalTime;

public class Teste {

	public static void main(String[] args) {
		MiniCurso miniCurso = new MiniCurso();
		
		miniCurso.setHoraInicio(LocalTime.parse("13:35"));
		miniCurso.setHoraFim(LocalTime.parse("15:00"));
		
//		LocalTime horaInicio = LocalTime.parse("10:30");
//		LocalTime horaFim = LocalTime.parse("12:00");
//
//		Duration diferenca = Duration.between(horaInicio, horaFim);
//		LocalTime localTimeDiferenca = LocalTime.ofNanoOfDay(diferenca.toNanos());
		miniCurso.calCularDuracaoCurso();
		System.out.println(miniCurso.getDuracaoCurso());
	}

}
