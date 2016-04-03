ALTER TABLE `onknowledge`.`tb_usuario` 
DROP FOREIGN KEY `fk_usuario2`;
ALTER TABLE `onknowledge`.`tb_usuario` 
CHANGE COLUMN `sobrenome` `sobrenome` VARCHAR(300) NULL ,
CHANGE COLUMN `dt_nascimento` `dt_nascimento` DATE NULL ,
CHANGE COLUMN `id_pergunta_seguranca` `id_pergunta_seguranca` INT(11) NULL ,
CHANGE COLUMN `resposta_pergunta_seguranca` `resposta_pergunta_seguranca` VARCHAR(300) NULL ;
ALTER TABLE `onknowledge`.`tb_usuario` 
ADD CONSTRAINT `fk_usuario2`
  FOREIGN KEY (`id_pergunta_seguranca`)
  REFERENCES `onknowledge`.`tb_pergunta_seguranca` (`id_pergunta`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
