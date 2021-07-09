package ExemplosColleagueService;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.foundation.ws.ColleagueDto;
import com.totvs.technology.ecm.foundation.ws.ColleagueDtoArray;
import com.totvs.technology.ecm.foundation.ws.ColleagueService;
import com.totvs.technology.ecm.foundation.ws.ECMColleagueServiceService;
import com.totvs.technology.ecm.foundation.ws.GroupDto;
import com.totvs.technology.ecm.foundation.ws.GroupDtoArray;
import com.totvs.technology.ecm.foundation.ws.WorkflowRoleDto;
import com.totvs.technology.ecm.foundation.ws.WorkflowRoleDtoArray;

/*
 * Classe que utiliza todos os m�todos de ColleagueService.
 * Com essa classe, pode-se criar, alterar, excluir e pesquisar colaboradores, alem de realizar outras atividades relacionadas a colaboradores.
 * No m�todo setParameters, pode-se setar algumas das vari�veis que s�o mais utilizadas como par�metros nos m�todos desta classe.
 * No m�todo changeMethod, pode-se escolher qual m�todo ser� executado.
 */

public class ColleagueServiceClient {

    private static final String url = "http://minhahomolog.cnc.org.br/webdesk/ECMColleagueService?wsdl";
    private final String loginColaborador = "integracao"; // login do usu�rio administrador
    private final String senhaColaborador = "!2018@Minha!"; // senha do usu�rio administrador
    private final int codigoEmpresa = 1; // c�digo da empresa
    private final String codigoGrupo = "adm"; // grupo do colaborador
    private final String descricaoGrupo = "Fluig"; // descri��o do grupo do colaborador
    private final String matriculaColaborador = "bbb"; // matr�cula do colaborador
    private final String codigoPapelWorkflow = "admin"; // papel do colaborador
    private final String descricaoPapelWorkflow = "admin"; // descri��o do papel do colaborador
    private final String email = "bbb@fluig.com"; // email do colaborador
    private final String nomeColaborador = "Colaborador Webservice"; // nome do colaborador
    private final String loginColaboradorNovo = "bbb2"; // login do novo colaborador
    private final String language = "PT_BR"; // idioma padr�o (vers�es antigas aceitavam "pt")

    // Dto.
    private ColleagueDto colleagueDto = new ColleagueDto();
    private GroupDto groupDto = new GroupDto();
    private WorkflowRoleDto workflowRoleDto = new WorkflowRoleDto();

    // Array
    private ColleagueDtoArray colleagueDtoArray = new ColleagueDtoArray();
    private GroupDtoArray groupDtoArray = new GroupDtoArray();
    private WorkflowRoleDtoArray workflowRoleDtoArray = new WorkflowRoleDtoArray();

    // Instancia ColleagueServiceService.
    private ECMColleagueServiceService colleagueService = new ECMColleagueServiceService();
    private ColleagueService service = colleagueService.getColleagueServicePort();

    // Inicia execu��o da classe.
    public static void main (String args[]) {
        System.out.println("\nClasse ColleagueService");

        // Instancia classe ColleagueServiceClient.
        ColleagueServiceClient csc = new ColleagueServiceClient();

        // Configura acesso ao WebServices.
        BindingProvider bp = (BindingProvider) csc.service;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

        try {
            // Chama m�todo que � respons�vel por executar os m�todos da classe.
            csc.changeMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Escolhe m�todo.
     * Nesse m�todo, pode-se escolher qual m�todo da classe ser� executado.
     */
    
    public void changeMethod() throws Exception {
        // Chama m�todo getColleagues.
        //this.getColleagues();

        // Chama m�todo getColleaguesCompressedData();
        //this.getColleaguesCompressedData();


        // Chama m�todo getColleague.
        //this.getColleague();

        // Chama m�todo getGroups.
        //this.getGroups();

        // Chama m�todo createColleague.
        this.createColleague();

        // Chama m�todo createColleaguewithDependencies.
        //this.createColleaguewithDependencies();

        // Chama m�todo updateColleague.
        //this.updateColleague();

        // Chama m�todo updateColleaguewithDependencies.
        //this.updateColleaguewithDependencies();

        // Chama m�todo removeColleague.
        //this.removeColleague();

        // Chama m�todo getSummaryColleagues.
        //this.getSummaryColleagues();

        // Chama m�todo validateColleagueLogin.
        //this.validateColleagueLogin();

        // Chama m�todo getColleaguesMail.
        //this.getColleaguesMail();
    }

    /*
     * Criptografa senha
     */
    public String criptografaSenha (String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));

        String s = hash.toString(16);

        if (s.length() %2 != 0) {
            s = "0" + s;
        }

        return s;
    }

    /*
     * Retorna todos os colaboradores ativos.
     *
     * M�todo: getColleagues.
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa.
     */
    
    
    public void getColleagues() throws Exception {
        System.out.println("\nM�todo getColleagues\n");

        // Retorna todos os colaboradores ativos.
        this.colleagueDtoArray = service.getColleagues(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa);

        // Mostra resultado.
        if (this.colleagueDtoArray != null) {

            for (int i = 0; i < this.colleagueDtoArray.getItem().size(); i++) {
                System.out.println("Nome do colaborador: " + this.colleagueDtoArray.getItem().get(i).getColleagueName());
            }
        } else {
            System.out.println("Nenhum colaborador foi encontrado!");
        }
    }

    /*
     * Retorna todos os colaboradores ativos em lista compactada.
     *
     * M�todo: getColleaguesCompressedData()
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa.
     */
    
    public void getColleaguesCompressedData() throws java.lang.Exception {
        System.out.println("\nM�todo getColleaguesCompressedData\n");

        // Retorna todos os colaboradores ativos.
        byte[] data = service.getColleaguesCompressedData(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa);

        ByteArrayInputStream fis = new ByteArrayInputStream(data);
        GZIPInputStream gs = new GZIPInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(gs);


        boolean endfile = false;
        while (endfile == false) {
            try {
                @SuppressWarnings("rawtypes")
                Map colleague = (Map) ois.readObject();

                System.out.println("Colaborador id " + colleague.get("colleagueId") + " nome "
                        + colleague.get("colleagueName"));

            } catch (EOFException eofe) {
                endfile = true;
            }
        }


        ois.close();
        fis.close();

    }

    /*
     * Retorna um colaborador.
     *
     * M�todo: getColleague.
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa;
     * - Matr�cula do colaborador.
     */
    
    public void getColleague() throws Exception {
        System.out.println("\nM�todo getColleague\n");

        // Retorna um colaborador.
        this.colleagueDtoArray = service.getColleague(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador);

        // Mostra resultado.
        if (this.colleagueDtoArray.getItem().get(0) != null) {

            for (int i = 0; i < this.colleagueDtoArray.getItem().size(); i++) {
                System.out.println("Nome do colaborador: " + this.colleagueDtoArray.getItem().get(i).getColleagueName());
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador + " n�o foi encontrado!");
        }
    }

    /*
     * Retorna os grupos de usu�rios que o colaborador participa.
     *
     * M�todo: getGroups.
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa;
     * - Matr�cula do colaborador.
     */
    
    public void getGroups() throws Exception {
        System.out.println("\nM�todo getGroups\n");

        // Retorna os grupos de usu�rios que o colaborador participa.
        this.groupDtoArray = service.getGroups(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador);

        // Mostra resultado.
        if (!this.groupDtoArray.getItem().isEmpty()) {

            for (GroupDto group : this.groupDtoArray.getItem()) {
                System.out.println("Grupo de usu�rios: " + group.getGroupDescription());
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador
                    + " n�o participa de nenhum grupo de usu�rios!");
        }
    }

    /*
     * Cria um colaborador.
     *
     * M�todo: createColleague.
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa;
     * - Array de colaboradores.
     */
    
    public void createColleague() throws Exception {
        System.out.println("\nM�todo createColleague\n");

        // Cria colaborador.
        this.colleagueDto.setActive(true);
        this.colleagueDto.setAdminUser(true);
        this.colleagueDto.setArea1Id(0);
        this.colleagueDto.setArea2Id(0);
        this.colleagueDto.setArea3Id(0);
        this.colleagueDto.setArea4Id(0);
        this.colleagueDto.setArea5Id(0);
        this.colleagueDto.setColleaguebackground("");
        this.colleagueDto.setColleagueId(this.loginColaboradorNovo);
        this.colleagueDto.setColleagueName(this.loginColaboradorNovo);
        this.colleagueDto.setCompanyId(this.codigoEmpresa);
        this.colleagueDto.setCurrentProject("Webservices");
        this.colleagueDto.setDefaultLanguage(this.language);
        this.colleagueDto.setDialectId(this.language);
        this.colleagueDto.setEmailHtml(true);
        this.colleagueDto.setEspecializationArea("Webservices");
        this.colleagueDto.setExtensionNr("1234");
        this.colleagueDto.setGedUser(true);
        this.colleagueDto.setGroupId("");
        this.colleagueDto.setGuestUser(false);
        this.colleagueDto.setHomePage("");
        this.colleagueDto.setLogin(this.loginColaboradorNovo);
        this.colleagueDto.setMail(this.email);
        this.colleagueDto.setMenuConfig(0);
        this.colleagueDto.setNominalUser(false);
        this.colleagueDto.setPasswd(this.criptografaSenha(this.senhaColaborador));
        this.colleagueDto.setPhotoPath("");
        this.colleagueDto.setSessionId("");
        this.colleagueDto.setVolumeId("Default");

        // Adiciona colaborador no array de colaboradores.
        this.colleagueDtoArray.getItem().add(this.colleagueDto);

        // Cria um colaborador.
        String result = service.createColleague(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.colleagueDtoArray);

        // Mostra resultado.
        if (result.equals("ok")) {
            System.out.println("Colaborador " + this.matriculaColaborador + " foi criado!");
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador
                    + " n�o foi criado!\nVerifique os par�metros inseridos e as permiss�es configuradas no Fluig.");
            System.out.println("\nObs.: "+result);
        }
    }

    /*
     * Cria um colaborador com grupos de seguran�a e pap�is workflow.
     *
     * M�todo: createColleaguewithDependencies.
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa;
     * - Array de colaboradores;
     * - Array de grupos;
     * - Array de papel workflow.
     */
    
    public void createColleaguewithDependencies() throws Exception {
        System.out.println("\nM�todo createColleaguewithDependencies\n");

        // Cria colaborador.
        this.colleagueDto.setActive(true);
        this.colleagueDto.setAdminUser(true);
        this.colleagueDto.setArea1Id(0);
        this.colleagueDto.setArea2Id(0);
        this.colleagueDto.setArea3Id(0);
        this.colleagueDto.setArea4Id(0);
        this.colleagueDto.setArea5Id(0);
        this.colleagueDto.setColleaguebackground("");
        this.colleagueDto.setColleagueId(this.matriculaColaborador);
        this.colleagueDto.setColleagueName(this.nomeColaborador);
        this.colleagueDto.setCompanyId(this.codigoEmpresa);
        this.colleagueDto.setCurrentProject("Webservices");
        this.colleagueDto.setDefaultLanguage(this.language);
        this.colleagueDto.setDialectId(this.language);
        this.colleagueDto.setEmailHtml(true);
        this.colleagueDto.setEspecializationArea("Webservices");
        this.colleagueDto.setExtensionNr("1234");
        this.colleagueDto.setGedUser(true);
        this.colleagueDto.setGroupId("");
        this.colleagueDto.setGuestUser(false);
        this.colleagueDto.setHomePage("");
        this.colleagueDto.setLogin(this.loginColaboradorNovo);
        this.colleagueDto.setMail(this.email);
        this.colleagueDto.setMenuConfig(0);
        this.colleagueDto.setNominalUser(false);
        this.colleagueDto.setPasswd(this.criptografaSenha(this.senhaColaborador));
        this.colleagueDto.setPhotoPath("");
        this.colleagueDto.setSessionId("");
        this.colleagueDto.setVolumeId("Default");

        // Adiciona colaborador no array de colaboradores.
        this.colleagueDtoArray.getItem().add(this.colleagueDto);

        // Cria grupos de seguran�a.
        this.groupDto.setCompanyId(this.codigoEmpresa);
        this.groupDto.setGroupId(this.codigoGrupo);
        this.groupDto.setGroupDescription(this.descricaoGrupo);

        // Adiciona grupos de seguran�a no array de grupos de seguran�a.
        this.groupDtoArray.getItem().add(this.groupDto);

        // Cria pap�is workflow.
        this.workflowRoleDto.setCompanyId(this.codigoEmpresa);
        this.workflowRoleDto.setRoleId(this.codigoPapelWorkflow);
        this.workflowRoleDto.setRoleDescription(descricaoPapelWorkflow);

        // Adiciona papel workflow no array de pap�is workflow.
        this.workflowRoleDtoArray.getItem().add(this.workflowRoleDto);

        // Cria um colaborador com grupos de seguran�a e pap�is workflow.
        String result = service.createColleaguewithDependencies(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.colleagueDtoArray, this.groupDtoArray, this.workflowRoleDtoArray);

        // Mostra resultado.
        if (result.equals("ok")) {
            System.out.println("Colaborador " + this.matriculaColaborador + " foi criado!");
        } else {
            System.out.println(result);
        }
    }

    /*
     * Altera um colaborador.
     *
     * M�todo: updateColleague.
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa;
     * - Array de colaboradores.
     */
    
    public void updateColleague() throws Exception {
        System.out.println("\nM�todo updateColleague\n");

        // Verifica se colaborador existe.
        this.colleagueDtoArray = service.getColleague(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador);

        // Mostra resultado.
        if (this.colleagueDtoArray.getItem().get(0) != null) {

            // Limpa array
            this.colleagueDtoArray.getItem().clear();

            // Cria colaborador.
            this.colleagueDto.setActive(true);
            this.colleagueDto.setAdminUser(true);
            this.colleagueDto.setArea1Id(0);
            this.colleagueDto.setArea2Id(0);
            this.colleagueDto.setArea3Id(0);
            this.colleagueDto.setArea4Id(0);
            this.colleagueDto.setArea5Id(0);
            this.colleagueDto.setColleaguebackground("");
            this.colleagueDto.setColleagueId(this.matriculaColaborador);
            this.colleagueDto.setColleagueName(this.nomeColaborador);
            this.colleagueDto.setCompanyId(this.codigoEmpresa);
            this.colleagueDto.setCurrentProject("Webservices");
            this.colleagueDto.setDefaultLanguage(this.language);
            this.colleagueDto.setDialectId(this.language);
            this.colleagueDto.setEmailHtml(true);
            this.colleagueDto.setEspecializationArea("Webservices");
            this.colleagueDto.setExtensionNr("1234");
            this.colleagueDto.setGedUser(true);
            this.colleagueDto.setGroupId("");
            this.colleagueDto.setGuestUser(false);
            this.colleagueDto.setHomePage("");
            this.colleagueDto.setLogin(this.loginColaboradorNovo);
            this.colleagueDto.setMail(this.email);
            this.colleagueDto.setMenuConfig(0);
            this.colleagueDto.setNominalUser(false);
            this.colleagueDto.setPasswd(this.criptografaSenha(this.senhaColaborador));
            this.colleagueDto.setPhotoPath("");
            this.colleagueDto.setSessionId("");
            this.colleagueDto.setVolumeId("Default");

            // Adiciona colaborador no array de colaboradores.
            this.colleagueDtoArray.getItem().add(this.colleagueDto);

            // Altera um colaborador.
            String result = service.updateColleague(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.colleagueDtoArray);

            // Mostra resultado.
            if (result.equals("ok")) {
                System.out.println("Colaborador " + this.matriculaColaborador + " foi alterado!");
            } else {
                System.out
                .println("Colaborador "
                        + this.matriculaColaborador
                        + " n�o foi alterado!\nVerifique os par�metros inseridos e as permiss�es configuradas no Fluig.");
                System.out.println("\nObs.: "+result);
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador + " n�o foi encontrado!");
        }
    }

    /*
     * Altera um colaborador com grupos de seguran�a e pap�is workflow.
     *
     * M�todo: updateColleaguewithDependencies.
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa;
     * - Array de colaboradores;
     * - Array de grupos;
     * - Array de papel workflow.
     */
    
    public void updateColleaguewithDependencies() throws Exception {
        System.out.println("\nM�todo updateColleaguewithDependencies\n");

        // Verifica se colaborador existe.
        this.colleagueDtoArray = service.getColleague(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador);

        // Mostra resultado.
        if (this.colleagueDtoArray.getItem().get(0) != null) {

            // Limpa array
            this.colleagueDtoArray.getItem().clear();

            // Cria colaborador.
            this.colleagueDto.setActive(true);
            this.colleagueDto.setAdminUser(true);
            this.colleagueDto.setArea1Id(0);
            this.colleagueDto.setArea2Id(0);
            this.colleagueDto.setArea3Id(0);
            this.colleagueDto.setArea4Id(0);
            this.colleagueDto.setArea5Id(0);
            this.colleagueDto.setColleaguebackground("");
            this.colleagueDto.setColleagueId(this.matriculaColaborador);
            this.colleagueDto.setColleagueName(this.nomeColaborador);
            this.colleagueDto.setCompanyId(this.codigoEmpresa);
            this.colleagueDto.setCurrentProject("Webservices");
            this.colleagueDto.setDefaultLanguage(this.language);
            this.colleagueDto.setDialectId(this.language);
            this.colleagueDto.setEmailHtml(true);
            this.colleagueDto.setEspecializationArea("Webservices");
            this.colleagueDto.setExtensionNr("1234");
            this.colleagueDto.setGedUser(true);
            this.colleagueDto.setGroupId("");
            this.colleagueDto.setGuestUser(false);
            this.colleagueDto.setHomePage("");
            this.colleagueDto.setLogin(this.loginColaboradorNovo);
            this.colleagueDto.setMail(this.email);
            this.colleagueDto.setMenuConfig(0);
            this.colleagueDto.setNominalUser(false);
            this.colleagueDto.setPasswd(this.criptografaSenha(this.senhaColaborador));
            this.colleagueDto.setPhotoPath("");
            this.colleagueDto.setSessionId("");
            this.colleagueDto.setVolumeId("Default");

            // Adiciona colaborador no array de colaboradores.
            this.colleagueDtoArray.getItem().add(this.colleagueDto);

            // Cria grupos de seguran�a.
            this.groupDto.setCompanyId(this.codigoEmpresa);
            this.groupDto.setGroupId(this.codigoGrupo);

            // Adiciona grupos de seguran�a no array de grupos de seguran�a.
            this.groupDtoArray.getItem().add(this.groupDto);

            // Cria pap�is workflow.
            this.workflowRoleDto.setCompanyId(this.codigoEmpresa);
            this.workflowRoleDto.setRoleId(this.codigoPapelWorkflow);

            // Adiciona papel workflow no array de pap�is workflow.
            this.workflowRoleDtoArray.getItem().add(this.workflowRoleDto);

            // Altera um colaborador com grupos de seguran�a e pap�is workflow.
            String result = service.updateColleaguewithDependencies(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.colleagueDtoArray, this.groupDtoArray, this.workflowRoleDtoArray);

            // Mostra resultado.
            if (result.equals("ok")) {
                System.out.println("Colaborador " + this.matriculaColaborador + " foi alterado!");
            } else {
                System.out.println("Colaborador " + this.matriculaColaborador + " n�o foi alterado!");
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador + " n�o foi encontrado!");
        }
    }

    /*
     * Desativa um colaborador.
     *
     * M�todo: removeColleague.
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa;
     * - Matr�cula do colaborador.
     */
    
    public void removeColleague() throws Exception {
        System.out.println("\nM�todo removeColleague\n");

        // Verifica se colaborador existe.
        this.colleagueDtoArray = service.getColleague(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador);

        // Mostra resultado.
        if (this.colleagueDtoArray.getItem().get(0) != null) {

            // Exclui um colaborador.
            String result = service.removeColleague(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador);

            // Mostra resultado.
            if (result.equals("ok")) {
                System.out.println("Colaborador " + this.matriculaColaborador + " foi desativado!");
            } else {
                System.out
                .println("Colaborador "
                        + this.matriculaColaborador
                        + " n�o foi desativado!\nVerifique os par�metros inseridos e as permiss�es configuradas no Fluig.");
                System.out.println("\nObs.: "+result);
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador + " n�o foi encontrado!");
        }
    }

    /*
     * Retorna todos os colaboradores ativos.
     *
     * M�todo: getSummaryColleagues.
     *
     * Par�metros:
     * - C�digo da empresa.
     */
    
    public void getSummaryColleagues() throws Exception {
        System.out.println("\nM�todo getSummaryColleagues\n");

        // Retorna todos os colaboradores ativos.
        this.colleagueDtoArray = service.getSummaryColleagues(this.codigoEmpresa);

        // Mostra resultado.
        if (this.colleagueDtoArray != null) {

            for (ColleagueDto colleague : this.colleagueDtoArray.getItem()) {
                System.out.println("Colaborador: " + colleague.getColleagueName());
            }
        } else {
            System.out.println("Nenhum colaborador foi encontrado!");
        }
    }

    /*
     * Valida o acesso de um colaborador no produto.
     *
     * M�todo: validateColleagueLogin.
     *
     * Par�metros:
     * - C�digo da empresa;
     * - Matr�cula do colaborador;
     * - Senha do colaborador.
     */
    
    public void validateColleagueLogin() throws Exception {
        System.out.println("\nM�todo validateColleagueLogin\n");

        // Valida o acesso de um colaborador no produto.
        String result = service.validateColleagueLogin(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador);

        // Mostra resultado.
        if (result.equals("ok")) {
            System.out.println("Acesso do colaborador " + this.matriculaColaborador + " foi validado!");
        } else {
            System.out.println("Acesso do colaborador " + this.matriculaColaborador + " n�o foi validado!");
        }
    }

    /*
     * Retorna um colaborador utilizando um e-mail.
     *
     * M�todo: getColleaguesMail.
     *
     * Par�metros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - C�digo da empresa;
     * - E-mail do colaborador.
     */
    
    public void getColleaguesMail() throws Exception {
        System.out.println("\nM�todo getColleaguesMail\n");

        // Retorna um colaborador utilizando um e-mail.
        this.colleagueDtoArray = service.getColleaguesMail(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.email);

        // Mostra resultado.
        if (!this.colleagueDtoArray.getItem().isEmpty()) {

            for (ColleagueDto colleague : this.colleagueDtoArray.getItem()) {
                System.out.println("Colaborador: " + colleague.getColleagueName());
            }
        } else {
            System.out.println("N�o foi encontrado nenhum colaborador com e-mail " + this.email);
        }
    }
}