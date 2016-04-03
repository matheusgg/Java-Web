ALTER TABLE `onknowledge`.`tb_anexo_email` 
DROP FOREIGN KEY `fk_anexo_email2`;
ALTER TABLE `onknowledge`.`tb_anexo_email` 
CHANGE COLUMN `id_email_email` `id_email` INT(11) NOT NULL ;
ALTER TABLE `onknowledge`.`tb_anexo_email` 
ADD CONSTRAINT `fk_anexo_email2`
  FOREIGN KEY (`id_email`)
  REFERENCES `onknowledge`.`tb_email` (`id_email`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
