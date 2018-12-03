package br.com.projeto.reuniao.domain.repository;

import java.io.ByteArrayOutputStream;

public interface IReuniaoReportRepository {
	
	/**
	 * 
	 * @param reuniaoId
	 * @return
	 */
	ByteArrayOutputStream exportAtaReuniaoToPDF( long reuniaoId );
	
}
