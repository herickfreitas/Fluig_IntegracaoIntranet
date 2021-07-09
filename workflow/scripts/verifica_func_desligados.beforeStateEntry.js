var SeqCompararCad = 14;

function beforeStateEntry(sequenceId){
	
	log.info("verifica_func_desligados - beforeStateEntry "+sequenceId);
	
	
    if (sequenceId == SeqCompararCad) {
    	CompararCadastros();
    } 
	
}


function CompararCadastros(){
	try 
	{	
		log.info("verifica_func_desligados - CompararCadastros()");
		
		//////////////////////////////////
		//		NRO DA SOLICITAÇÃO		//
		//////////////////////////////////

		//Recupera o numero da solicitação
		var processo = getValue("WKNumProces");
	    hAPI.setCardValue("numeroSolicitacao", processo);
		
		
		//////////////////////////////////
		//		QTD DE DESLIGADOS		//
		//////////////////////////////////
		
        // Executando chamada de dataset
        var datasetReturned = DatasetFactory.getDataset("_RM_FUNC_DEMITIDOS", null, null, null);
        
        var quantidade = 0;

        if (datasetReturned.rowsCount > 0) {
        	quantidade = datasetReturned.rowsCount;
        }
        
        log.info("verifica_func_desligados - Quantidade "+ quantidade);
        
        // Gravando retorno no formulário		
		hAPI.setCardValue("qtdColaboradores", quantidade);	
        
        
		
	}
	
	catch (e)
	{
		log.error(e);
		throw e;
	}	
}