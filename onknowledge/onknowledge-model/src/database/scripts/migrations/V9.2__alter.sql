ALTER TABLE `onknowledge`.`tb_email` 
DROP FOREIGN KEY `fk_email1`,
DROP FOREIGN KEY `fk_email2`;
ALTER TABLE `onknowledge`.`tb_email` 
DROP COLUMN `id_usuario`,
DROP COLUMN `id_diretorio_email`,
ADD COLUMN `txt_mensagem` VARCHAR(5000) NOT NULL AFTER `fl_excluido`,
ADD COLUMN `assunto` VARCHAR(200) NOT NULL AFTER `txt_mensagem`,
ADD COLUMN `id_usuario_remetente` INT NOT NULL AFTER `assunto`,
ADD INDEX `fk_email1_idx` (`id_usuario_remetente` ASC),
DROP INDEX `fk_usuario_email_idx` ,
DROP INDEX `fk_diretorio_email_idx` ;
ALTER TABLE `onknowledge`.`tb_email` 
ADD CONSTRAINT `fk_email1`
  FOREIGN KEY (`id_usuario_remetente`)
  REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  
ALTER TABLE `onknowledge`.`tb_anexo_email` 
DROP FOREIGN KEY `fk_anexo_email2`;
ALTER TABLE `onknowledge`.`tb_anexo_email` 
CHANGE COLUMN `id_email_enviado` `id_email_email` INT(11) NOT NULL ;
ALTER TABLE `onknowledge`.`tb_anexo_email` 
ADD CONSTRAINT `fk_anexo_email2`
  FOREIGN KEY (`id_email_email`)
  REFERENCES `onknowledge`.`tb_email` (`id_email`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  
ALTER TABLE `onknowledge`.`tb_email_enviado` 
DROP FOREIGN KEY `fk_email_enviado1`,
DROP FOREIGN KEY `fk_email_enviado2`;
ALTER TABLE `onknowledge`.`tb_email_enviado` 
CHANGE COLUMN `id_mensagem` `id_email` INT(11) NOT NULL ;
ALTER TABLE `onknowledge`.`tb_email_enviado` 
ADD CONSTRAINT `fk_email_enviado1`
  FOREIGN KEY (`id_email`)
  REFERENCES `onknowledge`.`tb_email` (`id_email`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  
  
ALTER TABLE `onknowledge`.`tb_email_recebido` 
DROP FOREIGN KEY `fk_email_recebido1`,
DROP FOREIGN KEY `fk_email_recebido2`;
ALTER TABLE `onknowledge`.`tb_email_recebido` 
CHANGE COLUMN `id_email_enviado` `id_email` INT(11) NOT NULL ,
ADD COLUMN `id_diretorio` INT NULL AFTER `fl_lido`,
ADD COLUMN `id_usuario_destinatario` INT NOT NULL AFTER `id_diretorio`,
ADD INDEX `fk_email_recebido2_idx` (`id_diretorio` ASC),
ADD INDEX `fk_email_recebido3_idx` (`id_usuario_destinatario` ASC);
ALTER TABLE `onknowledge`.`tb_email_recebido` 
ADD CONSTRAINT `fk_email_recebido1`
  FOREIGN KEY (`id_email`)
  REFERENCES `onknowledge`.`tb_email` (`id_email`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_email_recebido2`
  FOREIGN KEY (`id_diretorio`)
  REFERENCES `onknowledge`.`tb_diretorio_email` (`id_diretorio`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_email_recebido3`
  FOREIGN KEY (`id_usuario_destinatario`)
  REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  
DROP TABLE `onknowledge`.`tb_mensagem_email`;