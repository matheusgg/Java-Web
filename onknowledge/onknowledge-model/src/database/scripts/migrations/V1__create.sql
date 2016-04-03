SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `onknowledge` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `onknowledge` ;

-- -----------------------------------------------------
-- Table `onknowledge`.`tb_perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_perfil` (
  `id_perfil` INT NOT NULL AUTO_INCREMENT,
  `ds_perfil` VARCHAR(100) NOT NULL,
  `fl_ativo` TINYINT(1) NOT NULL COMMENT 'true - Ativo\nfalse - Inativo\n',
  PRIMARY KEY (`id_perfil`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_pergunta_seguranca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_pergunta_seguranca` (
  `id_pergunta` INT NOT NULL AUTO_INCREMENT,
  `ds_pergunta` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id_pergunta`),
  UNIQUE INDEX `ds_pergunta_UNIQUE` (`ds_pergunta` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_anexo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_anexo` (
  `id_anexo` INT NOT NULL AUTO_INCREMENT,
  `nome_arquivo` VARCHAR(200) NOT NULL,
  `extensao_arquivo` VARCHAR(10) NULL,
  `caminho_arquivo` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id_anexo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `login` VARCHAR(100) NOT NULL,
  `senha` BLOB NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `sobrenome` VARCHAR(300) NOT NULL,
  `dt_nascimento` DATE NOT NULL,
  `tp_sexo` INT NOT NULL COMMENT '0 - Masculino\n1 - Feminino\n',
  `apelido` VARCHAR(100) NULL,
  `dt_cadastro` DATE NOT NULL,
  `fl_ativo` TINYINT(1) NOT NULL COMMENT 'true - Ativo\nfalse - Inativo\n',
  `fl_recebe_notificacoes_por_email` TINYINT(1) NOT NULL COMMENT 'true - Receber notificações\nfalse - Não receber notificações',
  `id_foto` INT NULL,
  `id_perfil` INT NOT NULL,
  `id_pergunta_seguranca` INT NOT NULL,
  `resposta_pergunta_seguranca` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  INDEX `fk_perfil_usuario_idx` (`id_perfil` ASC),
  INDEX `fk_pergunta_usuario_idx` (`id_pergunta_seguranca` ASC),
  INDEX `idx_login` (`login`(50) ASC),
  INDEX `fk_anexo_foto_usuario_idx` (`id_foto` ASC),
  CONSTRAINT `fk_usuario1`
    FOREIGN KEY (`id_perfil`)
    REFERENCES `onknowledge`.`tb_perfil` (`id_perfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario2`
    FOREIGN KEY (`id_pergunta_seguranca`)
    REFERENCES `onknowledge`.`tb_pergunta_seguranca` (`id_pergunta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario3`
    FOREIGN KEY (`id_foto`)
    REFERENCES `onknowledge`.`tb_anexo` (`id_anexo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_codigo_seguranca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_codigo_seguranca` (
  `id_codigo` INT NOT NULL AUTO_INCREMENT,
  `codigo` INT(11) NOT NULL,
  `fl_ativo` TINYINT(1) NOT NULL COMMENT 'true - Código Disponível\nfalse - Código Indisponível',
  PRIMARY KEY (`id_codigo`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_professor` (
  `id_professor` INT NOT NULL AUTO_INCREMENT,
  `id_codigo_seguranca` INT NOT NULL,
  PRIMARY KEY (`id_professor`),
  INDEX `fk_professor2_idx` (`id_codigo_seguranca` ASC),
  UNIQUE INDEX `id_codigo_seguranca_UNIQUE` (`id_codigo_seguranca` ASC),
  CONSTRAINT `fk_professor1`
    FOREIGN KEY (`id_professor`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_professor2`
    FOREIGN KEY (`id_codigo_seguranca`)
    REFERENCES `onknowledge`.`tb_codigo_seguranca` (`id_codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_curso` (
  `id_curso` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `id_coordenador` INT NOT NULL,
  PRIMARY KEY (`id_curso`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC),
  INDEX `fk_coordenador_curso_idx` (`id_coordenador` ASC),
  INDEX `idx_nome` (`nome`(50) ASC),
  CONSTRAINT `fk_curso1`
    FOREIGN KEY (`id_coordenador`)
    REFERENCES `onknowledge`.`tb_professor` (`id_professor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_turma` (
  `id_turma` INT NOT NULL AUTO_INCREMENT,
  `cod_turma` VARCHAR(100) NOT NULL,
  `dt_inicio` DATE NOT NULL,
  `dt_encerramento` DATE NOT NULL,
  PRIMARY KEY (`id_turma`),
  INDEX `idx_cod_turma` (`cod_turma` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_aluno` (
  `id_aluno` INT NOT NULL AUTO_INCREMENT,
  `id_turma` INT NOT NULL,
  `id_curso` INT NOT NULL,
  `id_codigo_seguranca` INT NOT NULL,
  PRIMARY KEY (`id_aluno`),
  INDEX `fk_curso_aluno_idx` (`id_curso` ASC),
  INDEX `fk_turma_aluno_idx` (`id_turma` ASC),
  INDEX `fk_aluno4_idx` (`id_codigo_seguranca` ASC),
  UNIQUE INDEX `id_codigo_seguranca_UNIQUE` (`id_codigo_seguranca` ASC),
  CONSTRAINT `fk_aluno1`
    FOREIGN KEY (`id_aluno`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno2`
    FOREIGN KEY (`id_curso`)
    REFERENCES `onknowledge`.`tb_curso` (`id_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno3`
    FOREIGN KEY (`id_turma`)
    REFERENCES `onknowledge`.`tb_turma` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno4`
    FOREIGN KEY (`id_codigo_seguranca`)
    REFERENCES `onknowledge`.`tb_codigo_seguranca` (`id_codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_disciplina` (
  `id_disciplina` INT NOT NULL AUTO_INCREMENT,
  `cod_disciplina` VARCHAR(100) NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `dt_inicio` DATE NOT NULL,
  `dt_encerramento` DATE NOT NULL,
  `dt_cadastro` DATE NOT NULL,
  PRIMARY KEY (`id_disciplina`),
  INDEX `idx_nome` (`nome`(50) ASC),
  INDEX `idx_cod_disciplina` (`cod_disciplina`(50) ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_curso_disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_curso_disciplina` (
  `id_curso` INT NOT NULL,
  `id_disciplina` INT NOT NULL,
  PRIMARY KEY (`id_curso`, `id_disciplina`),
  INDEX `fk_disciplina_idx` (`id_disciplina` ASC),
  CONSTRAINT `fk_curso_disciplina1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `onknowledge`.`tb_curso` (`id_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_curso_disciplina2`
    FOREIGN KEY (`id_disciplina`)
    REFERENCES `onknowledge`.`tb_disciplina` (`id_disciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_professor_disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_professor_disciplina` (
  `id_professor` INT NOT NULL,
  `id_disciplina` INT NOT NULL,
  PRIMARY KEY (`id_professor`, `id_disciplina`),
  INDEX `fk_disciplina_idx` (`id_disciplina` ASC),
  CONSTRAINT `fk_professor_disciplina1`
    FOREIGN KEY (`id_professor`)
    REFERENCES `onknowledge`.`tb_professor` (`id_professor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_professor_disciplina2`
    FOREIGN KEY (`id_disciplina`)
    REFERENCES `onknowledge`.`tb_disciplina` (`id_disciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_modulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_modulo` (
  `id_modulo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id_modulo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_modulo_disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_modulo_disciplina` (
  `id_modulo` INT NOT NULL,
  `id_disciplina` INT NOT NULL,
  PRIMARY KEY (`id_modulo`, `id_disciplina`),
  INDEX `fk_disciplina_idx` (`id_disciplina` ASC),
  CONSTRAINT `fk_modulo_disciplina1`
    FOREIGN KEY (`id_modulo`)
    REFERENCES `onknowledge`.`tb_modulo` (`id_modulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_modulo_disciplina2`
    FOREIGN KEY (`id_disciplina`)
    REFERENCES `onknowledge`.`tb_disciplina` (`id_disciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_atividade` (
  `id_atividade` INT NOT NULL AUTO_INCREMENT,
  `ds_atividade` VARCHAR(200) NULL,
  `dt_cadastro` DATE NOT NULL,
  `dt_prazo_final` DATETIME NULL,
  `id_professor` INT NOT NULL,
  `id_modulo` INT NOT NULL,
  `id_anexo` INT NULL,
  PRIMARY KEY (`id_atividade`),
  INDEX `fk_modulo_atividade_idx` (`id_modulo` ASC),
  INDEX `fk_professor_atividade_idx` (`id_professor` ASC),
  INDEX `fk_anexo_atividade_idx` (`id_anexo` ASC),
  INDEX `idx_ds_atividade` (`ds_atividade`(50) ASC),
  CONSTRAINT `fk_atividade1`
    FOREIGN KEY (`id_modulo`)
    REFERENCES `onknowledge`.`tb_modulo` (`id_modulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atividade2`
    FOREIGN KEY (`id_professor`)
    REFERENCES `onknowledge`.`tb_professor` (`id_professor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atividade3`
    FOREIGN KEY (`id_anexo`)
    REFERENCES `onknowledge`.`tb_anexo` (`id_anexo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_atividade_simples`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_atividade_simples` (
  `id_atividade_simples` INT NOT NULL AUTO_INCREMENT,
  `conteudo` VARCHAR(5000) NULL,
  PRIMARY KEY (`id_atividade_simples`),
  INDEX `idx_conteudo` (`conteudo`(50) ASC),
  CONSTRAINT `fk_atividade_simples1`
    FOREIGN KEY (`id_atividade_simples`)
    REFERENCES `onknowledge`.`tb_atividade` (`id_atividade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_atividade_questionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_atividade_questionario` (
  `id_atividade_questionario` INT NOT NULL AUTO_INCREMENT,
  `qtd_questoes` INT NOT NULL,
  PRIMARY KEY (`id_atividade_questionario`),
  CONSTRAINT `fk_atividade_questionario1`
    FOREIGN KEY (`id_atividade_questionario`)
    REFERENCES `onknowledge`.`tb_atividade` (`id_atividade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_questao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_questao` (
  `id_questao` INT NOT NULL AUTO_INCREMENT,
  `id_atividade_questionario` INT NOT NULL,
  `ds_questao` VARCHAR(5000) NOT NULL,
  `tp_questao` INT NOT NULL COMMENT '0 - Dissertativa\n1 - Múltipla Escolha\n2 - Anexo\n',
  `fl_justivicativa_obrigatoria` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_questao`),
  INDEX `fk_atividade_questionario_questao_idx` (`id_atividade_questionario` ASC),
  INDEX `idx_ds_questao` (`ds_questao`(50) ASC),
  CONSTRAINT `fk_questao1`
    FOREIGN KEY (`id_atividade_questionario`)
    REFERENCES `onknowledge`.`tb_atividade_questionario` (`id_atividade_questionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_alternativa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_alternativa` (
  `id_alternativa` INT NOT NULL AUTO_INCREMENT,
  `id_questao` INT NOT NULL,
  `ds_opcao` VARCHAR(1000) NOT NULL,
  `fl_correta` TINYINT(1) NOT NULL COMMENT 'true - Alternativa Correta\nfalse - Alternativa incorreta',
  PRIMARY KEY (`id_alternativa`),
  INDEX `fk_questao_alternativa_idx` (`id_questao` ASC),
  CONSTRAINT `fk_alternativa1`
    FOREIGN KEY (`id_questao`)
    REFERENCES `onknowledge`.`tb_questao` (`id_questao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_comentario` (
  `id_comentario` INT NOT NULL AUTO_INCREMENT,
  `dt_envio` DATETIME NOT NULL,
  `ds_comentario` VARCHAR(1000) NOT NULL,
  `id_usuario` INT NOT NULL,
  `id_atividade` INT NULL,
  `id_questao` INT NULL,
  PRIMARY KEY (`id_comentario`),
  INDEX `fk_usuario_comentario_idx` (`id_usuario` ASC),
  INDEX `fk_atividade_comentario_idx` (`id_atividade` ASC),
  INDEX `fk_questao_comentario_idx` (`id_questao` ASC),
  CONSTRAINT `fk_comentario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentario2`
    FOREIGN KEY (`id_atividade`)
    REFERENCES `onknowledge`.`tb_atividade` (`id_atividade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentario3`
    FOREIGN KEY (`id_questao`)
    REFERENCES `onknowledge`.`tb_questao` (`id_questao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_resposta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_resposta` (
  `id_resposta` INT NOT NULL AUTO_INCREMENT,
  `ds_justificativa` VARCHAR(5000) NULL,
  `id_questao` INT NOT NULL,
  `id_alternativa_escolhida` INT NULL,
  `fl_resposta_correta` TINYINT(1) NULL,
  `id_aluno` INT NOT NULL,
  PRIMARY KEY (`id_resposta`),
  INDEX `fk_questao_resposta_idx` (`id_questao` ASC),
  INDEX `fk_alternativa_resposta_idx` (`id_alternativa_escolhida` ASC),
  INDEX `fk_aluno_resposta_idx` (`id_aluno` ASC),
  CONSTRAINT `fk_resposta1`
    FOREIGN KEY (`id_questao`)
    REFERENCES `onknowledge`.`tb_questao` (`id_questao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resposta2`
    FOREIGN KEY (`id_alternativa_escolhida`)
    REFERENCES `onknowledge`.`tb_alternativa` (`id_alternativa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resposta3`
    FOREIGN KEY (`id_aluno`)
    REFERENCES `onknowledge`.`tb_aluno` (`id_aluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_aviso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_aviso` (
  `id_aviso` INT NOT NULL AUTO_INCREMENT,
  `txt_aviso` VARCHAR(500) NOT NULL,
  `dt_criacao` DATE NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_aviso`),
  INDEX `fk_usuario_aviso_idx` (`id_usuario` ASC),
  CONSTRAINT `fk_usuario_aviso1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_aviso_disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_aviso_disciplina` (
  `id_aviso` INT NOT NULL,
  `id_disciplina` INT NOT NULL,
  PRIMARY KEY (`id_aviso`, `id_disciplina`),
  INDEX `fk_disciplina_idx` (`id_disciplina` ASC),
  CONSTRAINT `fk_aviso_disciplina1`
    FOREIGN KEY (`id_aviso`)
    REFERENCES `onknowledge`.`tb_aviso` (`id_aviso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aviso_disciplina2`
    FOREIGN KEY (`id_disciplina`)
    REFERENCES `onknowledge`.`tb_disciplina` (`id_disciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_aviso_turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_aviso_turma` (
  `id_aviso` INT NOT NULL,
  `id_turma` INT NOT NULL,
  PRIMARY KEY (`id_aviso`, `id_turma`),
  INDEX `fk_turma_idx` (`id_turma` ASC),
  CONSTRAINT `fk_aviso_turma1`
    FOREIGN KEY (`id_aviso`)
    REFERENCES `onknowledge`.`tb_aviso` (`id_aviso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aviso_turma2`
    FOREIGN KEY (`id_turma`)
    REFERENCES `onknowledge`.`tb_turma` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_aviso_curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_aviso_curso` (
  `id_aviso` INT NOT NULL,
  `id_curso` INT NOT NULL,
  PRIMARY KEY (`id_aviso`, `id_curso`),
  INDEX `fk_curso_idx` (`id_curso` ASC),
  CONSTRAINT `fk_aviso_curso1`
    FOREIGN KEY (`id_aviso`)
    REFERENCES `onknowledge`.`tb_aviso` (`id_aviso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aviso_curso2`
    FOREIGN KEY (`id_curso`)
    REFERENCES `onknowledge`.`tb_curso` (`id_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_evento` (
  `id_evento` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `ds_evento` VARCHAR(300) NULL,
  `dt_inicial_evento` DATETIME NOT NULL,
  `dt_final_evento` DATETIME NOT NULL,
  `fl_evento_recorrente` TINYINT(1) NOT NULL COMMENT 'true - Evento Recorrente\nfalse - Evento Padrão',
  `tp_recorrencia` INT NULL COMMENT '0 - Diária\n1 - Semanal\n2 - Mensal\n3 - Anual',
  `dia_semana` INT NULL COMMENT '0 - Segunda\n1 - Terça\n2 - Quarta\n3 - Quinta\n4 - Sexta\n5 - Sábado\n6 - Domingo',
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_evento`),
  INDEX `fk_usuario_evento_idx` (`id_usuario` ASC),
  CONSTRAINT `fk_evento1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_horario_aula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_horario_aula` (
  `id_horario` INT NOT NULL AUTO_INCREMENT,
  `sala` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id_horario`),
  CONSTRAINT `fk_horario_aula1`
    FOREIGN KEY (`id_horario`)
    REFERENCES `onknowledge`.`tb_evento` (`id_evento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_diretorio_email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_diretorio_email` (
  `id_diretorio` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_diretorio`),
  INDEX `fk_usuario_diretorio_emal_idx` (`id_usuario` ASC),
  INDEX `idx_nome` (`nome`(50) ASC),
  CONSTRAINT `fk_diretorio_email1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_email` (
  `id_email` INT NOT NULL AUTO_INCREMENT,
  `dt_envio` DATETIME NOT NULL,
  `fl_rascunho` TINYINT(1) NOT NULL COMMENT 'true - Rascunho\nfalse - Novo',
  `fl_lixo` TINYINT(1) NOT NULL COMMENT 'true - Reciclado\nfalse - Não Reciclado',
  `fl_excluido` TINYINT(1) NOT NULL COMMENT 'true - Excluído\nfalse - Não Excluído',
  `id_diretorio_email` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_email`),
  INDEX `fk_diretorio_email_idx` (`id_diretorio_email` ASC),
  INDEX `fk_usuario_email_idx` (`id_usuario` ASC),
  CONSTRAINT `fk_email1`
    FOREIGN KEY (`id_diretorio_email`)
    REFERENCES `onknowledge`.`tb_diretorio_email` (`id_diretorio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_email2`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_mensagem_email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_mensagem_email` (
  `id_mensagem_email` INT NOT NULL AUTO_INCREMENT,
  `txt_mensagem` VARCHAR(5000) NOT NULL,
  `assunto` VARCHAR(200) NULL,
  PRIMARY KEY (`id_mensagem_email`),
  INDEX `idx_assunto` (`assunto`(50) ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_email_enviado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_email_enviado` (
  `id_email_enviado` INT NOT NULL AUTO_INCREMENT,
  `id_mensagem` INT NOT NULL,
  PRIMARY KEY (`id_email_enviado`),
  INDEX `fk_mensagem_email_idx` (`id_mensagem` ASC),
  CONSTRAINT `fk_email_enviado1`
    FOREIGN KEY (`id_mensagem`)
    REFERENCES `onknowledge`.`tb_mensagem_email` (`id_mensagem_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_email_enviado2`
    FOREIGN KEY (`id_email_enviado`)
    REFERENCES `onknowledge`.`tb_email` (`id_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_email_recebido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_email_recebido` (
  `id_email_recebido` INT NOT NULL AUTO_INCREMENT,
  `id_email_enviado` INT NOT NULL,
  `fl_lido` TINYINT(1) NOT NULL COMMENT 'true - Mensagem lida\nfalse - Mensagem não lida',
  PRIMARY KEY (`id_email_recebido`),
  INDEX `fk_email_enviado_idx` (`id_email_enviado` ASC),
  CONSTRAINT `fk_email_recebido1`
    FOREIGN KEY (`id_email_recebido`)
    REFERENCES `onknowledge`.`tb_email` (`id_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_email_recebido2`
    FOREIGN KEY (`id_email_enviado`)
    REFERENCES `onknowledge`.`tb_email_enviado` (`id_email_enviado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_anexo_email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_anexo_email` (
  `id_anexo` INT NOT NULL,
  `id_email_enviado` INT NOT NULL,
  INDEX `fk_anexo_email_idx` (`id_anexo` ASC),
  INDEX `fk_email_enviado_anexo_idx` (`id_email_enviado` ASC),
  PRIMARY KEY (`id_anexo`, `id_email_enviado`),
  CONSTRAINT `fk_anexo_email1`
    FOREIGN KEY (`id_anexo`)
    REFERENCES `onknowledge`.`tb_anexo` (`id_anexo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_anexo_email2`
    FOREIGN KEY (`id_email_enviado`)
    REFERENCES `onknowledge`.`tb_email_enviado` (`id_email_enviado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_forum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_forum` (
  `id_forum` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `assunto` VARCHAR(300) NOT NULL,
  `dt_criacao` DATE NOT NULL,
  `dt_encerramento` DATE NULL,
  `tp_status` INT NOT NULL COMMENT '0 - Aberto\n1 - Encerrado',
  `id_curso` INT NOT NULL,
  PRIMARY KEY (`id_forum`),
  INDEX `fk_curso_forum_idx` (`id_curso` ASC),
  INDEX `idx_nome` (`nome`(50) ASC),
  INDEX `idx_assunto` (`assunto`(50) ASC),
  CONSTRAINT `fk_forum1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `onknowledge`.`tb_curso` (`id_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_integrante_forum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_integrante_forum` (
  `id_integrante` INT NOT NULL AUTO_INCREMENT,
  `id_forum` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  `fl_moderador` TINYINT(1) NOT NULL COMMENT 'true - Moderador\nfalse - Integrante',
  `fl_habilitado` TINYINT(1) NOT NULL COMMENT 'true - Habilitado para postar mensagens\nfalse - Não possui permissão para postar mensagens\n',
  PRIMARY KEY (`id_integrante`),
  INDEX `fk_usuario_integrante_forum_idx` (`id_usuario` ASC),
  INDEX `fk_forum_integrante_idx` (`id_forum` ASC),
  CONSTRAINT `fk_integrante_forum1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_integrante_forum2`
    FOREIGN KEY (`id_forum`)
    REFERENCES `onknowledge`.`tb_forum` (`id_forum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_mensagem_forum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_mensagem_forum` (
  `id_mensagem` INT NOT NULL AUTO_INCREMENT,
  `dt_envio` DATETIME NOT NULL,
  `txt_mensagem` VARCHAR(1000) NOT NULL,
  `id_integrante` INT NOT NULL,
  PRIMARY KEY (`id_mensagem`),
  INDEX `fk_integrante_mensagem_idx` (`id_integrante` ASC),
  CONSTRAINT `fk_mensagem_forum1`
    FOREIGN KEY (`id_integrante`)
    REFERENCES `onknowledge`.`tb_integrante_forum` (`id_integrante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_anexo_mensagem_forum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_anexo_mensagem_forum` (
  `id_mensagem_forum` INT NOT NULL,
  `id_anexo` INT NOT NULL,
  PRIMARY KEY (`id_mensagem_forum`, `id_anexo`),
  INDEX `fk_anexo_mensagem_idx` (`id_anexo` ASC),
  CONSTRAINT `fk_anexo_mensagem_forum1`
    FOREIGN KEY (`id_mensagem_forum`)
    REFERENCES `onknowledge`.`tb_mensagem_forum` (`id_mensagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_anexo_mensagem_forum2`
    FOREIGN KEY (`id_anexo`)
    REFERENCES `onknowledge`.`tb_anexo` (`id_anexo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_aluno_disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_aluno_disciplina` (
  `id_aluno` INT NOT NULL,
  `id_disciplina` INT NOT NULL,
  PRIMARY KEY (`id_aluno`, `id_disciplina`),
  INDEX `fk_disciplina_idx` (`id_disciplina` ASC),
  CONSTRAINT `fk_aluno_disciplina1`
    FOREIGN KEY (`id_aluno`)
    REFERENCES `onknowledge`.`tb_aluno` (`id_aluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tb_aluno_disciplina2`
    FOREIGN KEY (`id_disciplina`)
    REFERENCES `onknowledge`.`tb_disciplina` (`id_disciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_tarefa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_tarefa` (
  `id_tarefa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `ds_tarefa` VARCHAR(300) NOT NULL,
  `tp_status` INT NOT NULL COMMENT '0 - Em Andamento\n1 - Encerrada',
  `dt_cadastro` DATETIME NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_tarefa`),
  INDEX `fk_usuario_tarefa_idx` (`id_usuario` ASC),
  CONSTRAINT `fk_tarefa1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_questao_anexo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_questao_anexo` (
  `id_questao` INT NOT NULL,
  `id_anexo` INT NOT NULL,
  PRIMARY KEY (`id_questao`, `id_anexo`),
  INDEX `fk_anexo_idx` (`id_anexo` ASC),
  CONSTRAINT `fk_questao_anexo1`
    FOREIGN KEY (`id_questao`)
    REFERENCES `onknowledge`.`tb_questao` (`id_questao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_questao_anexo2`
    FOREIGN KEY (`id_anexo`)
    REFERENCES `onknowledge`.`tb_anexo` (`id_anexo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_resposta_anexo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_resposta_anexo` (
  `id_resposta` INT NOT NULL,
  `id_anexo` INT NOT NULL,
  PRIMARY KEY (`id_resposta`, `id_anexo`),
  INDEX `fk_anexo_idx` (`id_anexo` ASC),
  CONSTRAINT `fk_resposta_anexo1`
    FOREIGN KEY (`id_resposta`)
    REFERENCES `onknowledge`.`tb_resposta` (`id_resposta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resposta_anexo2`
    FOREIGN KEY (`id_anexo`)
    REFERENCES `onknowledge`.`tb_anexo` (`id_anexo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_evento_turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_evento_turma` (
  `id_evento` INT NOT NULL,
  `id_turma` INT NOT NULL,
  PRIMARY KEY (`id_evento`, `id_turma`),
  INDEX `fk_turma_idx` (`id_turma` ASC),
  CONSTRAINT `fk_evento_turma1`
    FOREIGN KEY (`id_evento`)
    REFERENCES `onknowledge`.`tb_evento` (`id_evento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evento_turma2`
    FOREIGN KEY (`id_turma`)
    REFERENCES `onknowledge`.`tb_turma` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_evento_curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_evento_curso` (
  `id_evento` INT NOT NULL,
  `id_curso` INT NOT NULL,
  PRIMARY KEY (`id_evento`, `id_curso`),
  INDEX `fk_curso_idx` (`id_curso` ASC),
  CONSTRAINT `fk_evento_curso1`
    FOREIGN KEY (`id_evento`)
    REFERENCES `onknowledge`.`tb_evento` (`id_evento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evento_curso2`
    FOREIGN KEY (`id_curso`)
    REFERENCES `onknowledge`.`tb_curso` (`id_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onknowledge`.`tb_evento_disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_evento_disciplina` (
  `id_evento` INT NOT NULL,
  `id_disciplina` INT NOT NULL,
  PRIMARY KEY (`id_evento`, `id_disciplina`),
  INDEX `fk_disciplina_idx` (`id_disciplina` ASC),
  CONSTRAINT `fk_evento_disciplina1`
    FOREIGN KEY (`id_evento`)
    REFERENCES `onknowledge`.`tb_evento` (`id_evento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evento_disciplina2`
    FOREIGN KEY (`id_disciplina`)
    REFERENCES `onknowledge`.`tb_disciplina` (`id_disciplina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
