-- -----------------------------------------------------
-- Table `onknowledge`.`tb_destinatario_email_enviado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onknowledge`.`tb_destinatario_email_enviado` (
  `id_destinatario` INT NOT NULL,
  `id_email_enviado` INT NOT NULL,
  PRIMARY KEY (`id_destinatario`, `id_email_enviado`),
  INDEX `fk_destinatario_email_enviado2_idx` (`id_email_enviado` ASC),
  CONSTRAINT `fk_destinatario_email_enviado1`
    FOREIGN KEY (`id_destinatario`)
    REFERENCES `onknowledge`.`tb_usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_destinatario_email_enviado2`
    FOREIGN KEY (`id_email_enviado`)
    REFERENCES `onknowledge`.`tb_email_enviado` (`id_email_enviado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;