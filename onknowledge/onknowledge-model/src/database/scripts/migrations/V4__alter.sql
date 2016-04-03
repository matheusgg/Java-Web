ALTER TABLE `onknowledge`.`tb_usuario` 
CHANGE COLUMN `tp_sexo` `tp_sexo` INT(11) NULL COMMENT '0 - Masculino' /* comment truncated */ /*1 - Feminino
*/ ;

ALTER TABLE `onknowledge`.`tb_professor` 
DROP FOREIGN KEY `fk_professor2`;
ALTER TABLE `onknowledge`.`tb_professor` 
DROP COLUMN `id_codigo_seguranca`,
DROP INDEX `id_codigo_seguranca_UNIQUE` ;

ALTER TABLE `onknowledge`.`tb_codigo_seguranca` 
ADD COLUMN `id_turma` INT NOT NULL AFTER `fl_ativo`,
ADD COLUMN `id_curso` INT NOT NULL AFTER `id_turma`,
ADD INDEX `fk_codigo_seguranca1_idx` (`id_turma` ASC),
ADD INDEX `fk_codigo_seguranca2_idx` (`id_curso` ASC);
ALTER TABLE `onknowledge`.`tb_codigo_seguranca` 
ADD CONSTRAINT `fk_codigo_seguranca1`
  FOREIGN KEY (`id_turma`)
  REFERENCES `onknowledge`.`tb_turma` (`id_turma`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_codigo_seguranca2`
  FOREIGN KEY (`id_curso`)
  REFERENCES `onknowledge`.`tb_curso` (`id_curso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
