-- -----------------------------------------------------
-- Table `onknowledge`.`tb_atividade_anexo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_atividade_anexo` (
  `id_atividade` INT NOT NULL,
  `id_anexo` INT NOT NULL,
  PRIMARY KEY (`id_atividade`, `id_anexo`),
  INDEX `fk_atividade_anexo2_idx` (`id_anexo` ASC),
  CONSTRAINT `fk_atividade_anexo1`
    FOREIGN KEY (`id_atividade`)
    REFERENCES `onknowledge`.`tb_atividade` (`id_atividade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atividade_anexo2`
    FOREIGN KEY (`id_anexo`)
    REFERENCES `onknowledge`.`tb_anexo` (`id_anexo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE `onknowledge`.`tb_atividade` 
DROP FOREIGN KEY `fk_atividade3`;
ALTER TABLE `onknowledge`.`tb_atividade` 
DROP COLUMN `id_anexo`,
DROP INDEX `fk_anexo_atividade_idx` ;

ALTER TABLE `onknowledge`.`tb_resposta` 
ADD COLUMN `fl_corrigida` TINYINT(1) NULL AFTER `id_aluno`;

