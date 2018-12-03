package br.com.projeto.reuniao.domain.repository.impl;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.projeto.reuniao.domain.repository.IReuniaoReportRepository;
import br.com.projeto.reuniao.infraestruture.report.IReportManager;

@Component
public class ReuniaoReportRepositoryImpl implements IReuniaoReportRepository {

	private static final String ATA_REUNIAO_REPORT_PATH = "/report/ata/ata-reuniao.jasper";
	
	@Autowired
	private IReportManager reportManager; 
	
	@Override
	public ByteArrayOutputStream exportAtaReuniaoToPDF( long reuniaoId ) {
	    
		HashMap<String, Object> params = new HashMap<>(); 
		
		params.put("reuniaoId", reuniaoId);
			
		return this.reportManager.exportToPDF(params, ATA_REUNIAO_REPORT_PATH); 
	}
	
}
