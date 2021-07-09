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
 * Classe que utiliza todos os métodos de ColleagueService.
 * Com essa classe, pode-se criar, alterar, excluir e pesquisar colaboradores, alem de realizar outras atividades relacionadas a colaboradores.
 * No método setParameters, pode-se setar algumas das variáveis que são mais utilizadas como parâmetros nos métodos desta classe.
 * No método changeMethod, pode-se escolher qual método será executado.
 */

public class ColleagueServiceClient {

    private static final String url = "http://minhahomolog.cnc.org.br/webdesk/ECMColleagueService?wsdl";
    private final String loginColaborador = "integracao"; // login do usuário administrador
    private final String senhaColaborador = "!2018@Minha!"; // senha do usuário administrador
    private final int codigoEmpresa = 1; // código da empresa
    private final String codigoGrupo = "adm"; // grupo do colaborador
    private final String descricaoGrupo = "Fluig"; // descrição do grupo do colaborador
    private final String matriculaColaborador = "bbb"; // matrícula do colaborador
    private final String codigoPapelWorkflow = "admin"; // papel do colaborador
    private final String descricaoPapelWorkflow = "admin"; // descrição do papel do colaborador
    private final String email = "bbb@fluig.com"; // email do colaborador
    private final String nomeColaborador = "Colaborador Webservice"; // nome do colaborador
    private final String loginColaboradorNovo = "bbb2"; // login do novo colaborador
    private final String language = "PT_BR"; // idioma padrão (versões antigas aceitavam "pt")

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

    // Inicia execução da classe.
    public static void main (String args[]) {
        System.out.println("\nClasse ColleagueService");

        // Instancia classe ColleagueServiceClient.
        ColleagueServiceClient csc = new ColleagueServiceClient();

        // Configura acesso ao WebServices.
        BindingProvider bp = (BindingProvider) csc.service;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

        try {
            // Chama método que é responsável por executar os métodos da classe.
            csc.changeMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Escolhe método.
     * Nesse método, pode-se escolher qual método da classe será executado.
     */
    
    public void changeMethod() throws Exception {
        // Chama método getColleagues.
        //this.getColleagues();

        // Chama método getColleaguesCompressedData();
        //this.getColleaguesCompressedData();


        // Chama método getColleague.
        //this.getColleague();

        // Chama método getGroups.
        //this.getGroups();

        // Chama método createColleague.
        this.createColleague();

        // Chama método createColleaguewithDependencies.
        //this.createColleaguewithDependencies();

        // Chama método updateColleague.
        //this.updateColleague();

        // Chama método updateColleaguewithDependencies.
        //this.updateColleaguewithDependencies();

        // Chama método removeColleague.
        //this.removeColleague();

        // Chama método getSummaryColleagues.
        //this.getSummaryColleagues();

        // Chama método validateColleagueLogin.
        //this.validateColleagueLogin();

        // Chama método getColleaguesMail.
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
     * Método: getColleagues.
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa.
     */
    
    
    public void getColleagues() throws Exception {
        System.out.println("\nMétodo getColleagues\n");

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
     * Método: getColleaguesCompressedData()
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa.
     */
    
    public void getColleaguesCompressedData() throws java.lang.Exception {
        System.out.println("\nMétodo getColleaguesCompressedData\n");

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
     * Método: getColleague.
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa;
     * - Matrícula do colaborador.
     */
    
    public void getColleague() throws Exception {
        System.out.println("\nMétodo getColleague\n");

        // Retorna um colaborador.
        this.colleagueDtoArray = service.getColleague(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador);

        // Mostra resultado.
        if (this.colleagueDtoArray.getItem().get(0) != null) {

            for (int i = 0; i < this.colleagueDtoArray.getItem().size(); i++) {
                System.out.println("Nome do colaborador: " + this.colleagueDtoArray.getItem().get(i).getColleagueName());
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador + " não foi encontrado!");
        }
    }

    /*
     * Retorna os grupos de usuários que o colaborador participa.
     *
     * Método: getGroups.
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa;
     * - Matrícula do colaborador.
     */
    
    public void getGroups() throws Exception {
        System.out.println("\nMétodo getGroups\n");

        // Retorna os grupos de usuários que o colaborador participa.
        this.groupDtoArray = service.getGroups(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador);

        // Mostra resultado.
        if (!this.groupDtoArray.getItem().isEmpty()) {

            for (GroupDto group : this.groupDtoArray.getItem()) {
                System.out.println("Grupo de usuários: " + group.getGroupDescription());
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador
                    + " não participa de nenhum grupo de usuários!");
        }
    }

    /*
     * Cria um colaborador.
     *
     * Método: createColleague.
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa;
     * - Array de colaboradores.
     */
    
    public void createColleague() throws Exception {
        System.out.println("\nMétodo createColleague\n");

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
                    + " não foi criado!\nVerifique os parâmetros inseridos e as permissões configuradas no Fluig.");
            System.out.println("\nObs.: "+result);
        }
    }

    /*
     * Cria um colaborador com grupos de segurança e papéis workflow.
     *
     * Método: createColleaguewithDependencies.
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa;
     * - Array de colaboradores;
     * - Array de grupos;
     * - Array de papel workflow.
     */
    
    public void createColleaguewithDependencies() throws Exception {
        System.out.println("\nMétodo createColleaguewithDependencies\n");

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

        // Cria grupos de segurança.
        this.groupDto.setCompanyId(this.codigoEmpresa);
        this.groupDto.setGroupId(this.codigoGrupo);
        this.groupDto.setGroupDescription(this.descricaoGrupo);

        // Adiciona grupos de segurança no array de grupos de segurança.
        this.groupDtoArray.getItem().add(this.groupDto);

        // Cria papéis workflow.
        this.workflowRoleDto.setCompanyId(this.codigoEmpresa);
        this.workflowRoleDto.setRoleId(this.codigoPapelWorkflow);
        this.workflowRoleDto.setRoleDescription(descricaoPapelWorkflow);

        // Adiciona papel workflow no array de papéis workflow.
        this.workflowRoleDtoArray.getItem().add(this.workflowRoleDto);

        // Cria um colaborador com grupos de segurança e papéis workflow.
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
     * Método: updateColleague.
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa;
     * - Array de colaboradores.
     */
    
    public void updateColleague() throws Exception {
        System.out.println("\nMétodo updateColleague\n");

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
                        + " não foi alterado!\nVerifique os parâmetros inseridos e as permissões configuradas no Fluig.");
                System.out.println("\nObs.: "+result);
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador + " não foi encontrado!");
        }
    }

    /*
     * Altera um colaborador com grupos de segurança e papéis workflow.
     *
     * Método: updateColleaguewithDependencies.
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa;
     * - Array de colaboradores;
     * - Array de grupos;
     * - Array de papel workflow.
     */
    
    public void updateColleaguewithDependencies() throws Exception {
        System.out.println("\nMétodo updateColleaguewithDependencies\n");

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

            // Cria grupos de segurança.
            this.groupDto.setCompanyId(this.codigoEmpresa);
            this.groupDto.setGroupId(this.codigoGrupo);

            // Adiciona grupos de segurança no array de grupos de segurança.
            this.groupDtoArray.getItem().add(this.groupDto);

            // Cria papéis workflow.
            this.workflowRoleDto.setCompanyId(this.codigoEmpresa);
            this.workflowRoleDto.setRoleId(this.codigoPapelWorkflow);

            // Adiciona papel workflow no array de papéis workflow.
            this.workflowRoleDtoArray.getItem().add(this.workflowRoleDto);

            // Altera um colaborador com grupos de segurança e papéis workflow.
            String result = service.updateColleaguewithDependencies(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.colleagueDtoArray, this.groupDtoArray, this.workflowRoleDtoArray);

            // Mostra resultado.
            if (result.equals("ok")) {
                System.out.println("Colaborador " + this.matriculaColaborador + " foi alterado!");
            } else {
                System.out.println("Colaborador " + this.matriculaColaborador + " não foi alterado!");
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador + " não foi encontrado!");
        }
    }

    /*
     * Desativa um colaborador.
     *
     * Método: removeColleague.
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa;
     * - Matrícula do colaborador.
     */
    
    public void removeColleague() throws Exception {
        System.out.println("\nMétodo removeColleague\n");

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
                        + " não foi desativado!\nVerifique os parâmetros inseridos e as permissões configuradas no Fluig.");
                System.out.println("\nObs.: "+result);
            }
        } else {
            System.out.println("Colaborador " + this.matriculaColaborador + " não foi encontrado!");
        }
    }

    /*
     * Retorna todos os colaboradores ativos.
     *
     * Método: getSummaryColleagues.
     *
     * Parâmetros:
     * - Código da empresa.
     */
    
    public void getSummaryColleagues() throws Exception {
        System.out.println("\nMétodo getSummaryColleagues\n");

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
     * Método: validateColleagueLogin.
     *
     * Parâmetros:
     * - Código da empresa;
     * - Matrícula do colaborador;
     * - Senha do colaborador.
     */
    
    public void validateColleagueLogin() throws Exception {
        System.out.println("\nMétodo validateColleagueLogin\n");

        // Valida o acesso de um colaborador no produto.
        String result = service.validateColleagueLogin(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador);

        // Mostra resultado.
        if (result.equals("ok")) {
            System.out.println("Acesso do colaborador " + this.matriculaColaborador + " foi validado!");
        } else {
            System.out.println("Acesso do colaborador " + this.matriculaColaborador + " não foi validado!");
        }
    }

    /*
     * Retorna um colaborador utilizando um e-mail.
     *
     * Método: getColleaguesMail.
     *
     * Parâmetros:
     * - Login do colaborador;
     * - Senha do colaborador;
     * - Código da empresa;
     * - E-mail do colaborador.
     */
    
    public void getColleaguesMail() throws Exception {
        System.out.println("\nMétodo getColleaguesMail\n");

        // Retorna um colaborador utilizando um e-mail.
        this.colleagueDtoArray = service.getColleaguesMail(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.email);

        // Mostra resultado.
        if (!this.colleagueDtoArray.getItem().isEmpty()) {

            for (ColleagueDto colleague : this.colleagueDtoArray.getItem()) {
                System.out.println("Colaborador: " + colleague.getColleagueName());
            }
        } else {
            System.out.println("Não foi encontrado nenhum colaborador com e-mail " + this.email);
        }
    }
}