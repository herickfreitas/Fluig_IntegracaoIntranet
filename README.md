# Integrações

[![Maintainability](https://api.codeclimate.com/v1/badges/820ae2a57cfe89002f51/maintainability)](https://codeclimate.com/github/digtetecnologia/integracoes/maintainability)

Este repositório é dedicado aos processos de integração entre os RPs e os formulários de carga de dados do Intranet by Digte

## Processos disponíveis
1. Sincronização de Cargos: sincroniza_cargos
2. Sincronização de Empresas: sincroniza_empresas
3. Sincronização de Unidades: sincroniza_unidades
4. Sincronização de Departamentos: sincroniza_departamentos
5. Sincronização de Dados Adicionais do Usuário: sincroniza_dados_adicionais
5. Workflow que faz uma compara��o entre os cadastros de usu�rios do Fluig e RM, quando divergente o papel Admin ser� acionado: sincroniza_cadastro_usuarios

## Configuração
1. Faça um fork deste repositório
2. Clone o novo repositório para sua máquina local
3. Abra o conteúdo clonado, o qual corresponde a um projeto fluig, no Eclipse (estamos considerando que você já possui o fluig studio instalado - [Fluig Studio](https://tdn.totvs.com/display/public/fluig/Fluig+Studio))
4. Abra o diagrama do processo desejado e configure o mesmo para apontar para seu ambiente fluig alvo
5. Configure o diagrama do processo como desejar, se adequando a realidade da sua organização. Apenas mantenha a atividade "Executar sincronização" intacta
6. Acesse o script associado à atividade "Executar sincronização" e realize as edições necessárias, conforme indicado em comentários explicativos deixados no código
