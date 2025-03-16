Sistema de Gerenciamento de Aluguel de Carros
Informações do Projeto
Nome do Projeto: Sistema de Gerenciamento de Aluguel de Carros
Artefato (artifact): car-rental-management
Nome (name): car-rental-management
Grupo (group): com.empresa.locadora
Descrição: Sistema completo para gerenciamento de uma locadora de veículos, incluindo cadastro de clientes, veículos, contratos de aluguel, pagamentos e relatórios.
Objetivo
Desenvolver uma aplicação web completa, utilizando Spring Boot, para atender as necessidades de gerenciamento de uma locadora de veículos.

Funcionalidades Gerais (MVP Inicial)
1. Gestão de Clientes (CRUD)
Cadastrar novos clientes (Nome, CPF/CNPJ, endereço, telefone, e-mail).
Listar clientes com paginação e busca.
Editar dados dos clientes.
Excluir cliente.
2. Gestão de Carros (CRUD)
Cadastrar novos carros (Marca, Modelo, Placa, Ano, Status: disponível, alugado, manutenção).
Listar carros com filtros (por status, modelo, ano, etc.).
Atualizar informações dos carros.
Remover carros (com validação se não está alugado).
3. Gestão de Contratos de Aluguel (CRUD)
Criar contrato de aluguel (cliente, carro, data início, data fim, valor total, status: ativo, finalizado, cancelado).
Atualizar contratos (ex: finalização do aluguel).
Consultar histórico de contratos.
Cancelar contratos (com justificativa).
4. Gestão de Pagamentos (CRUD)
Registrar pagamento (contrato associado, valor, data, forma de pagamento).
Listar pagamentos.
Consultar total recebido por período.
Funcionalidades Avançadas
1. Notificações e Alertas
Alertar quando:
Um contrato está próximo do vencimento (exemplo: 2 dias antes).
Um carro está com status "manutenção" (notificação para gerente).
2. Relatórios e Dashboard
Relatórios:
Quantidade de carros disponíveis, alugados, em manutenção.
Faturamento mensal, anual.
Contratos ativos e vencidos.
Dashboard resumido na Home (com gráficos simples).
3. Segurança e Autenticação (JWT)
Login e logout com Spring Security e JWT.
Perfis de usuário:
Admin: acesso total.
Atendente: cadastro de clientes, contratos, pagamentos.
Gerente: todos acessos, inclusive relatórios.
4. API Responsiva (Mobile-ready)
Backend em Spring Boot com endpoints RESTful.
Swagger/OpenAPI para documentação.