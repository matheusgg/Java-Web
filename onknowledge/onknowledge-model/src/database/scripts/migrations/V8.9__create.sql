-- -----------------------------------------------------
-- Table `onknowledge`.`tb_resposta_alternativa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_resposta_alternativa` (
  `id_resposta` INT NOT NULL,
  `id_alternativa` INT NOT NULL,
  PRIMARY KEY (`id_resposta`, `id_alternativa`),
  INDEX `fk_resposta_alternativa2_idx` (`id_alternativa` ASC),
  CONSTRAINT `fk_resposta_alternativa1`
    FOREIGN KEY (`id_resposta`)
    REFERENCES `onknowledge`.`tb_resposta` (`id_resposta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resposta_alternativa2`
    FOREIGN KEY (`id_alternativa`)
    REFERENCES `onknowledge`.`tb_alternativa` (`id_alternativa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE `onknowledge`.`tb_resposta` 
DROP FOREIGN KEY `fk_resposta2`;
ALTER TABLE `onknowledge`.`tb_resposta` 
DROP COLUMN `id_alternativa_escolhida`,
DROP INDEX `fk_alternativa_resposta_idx` ;