ALTER TABLE `onknowledge`.`tb_aluno` 
DROP FOREIGN KEY `fk_aluno2`;
ALTER TABLE `onknowledge`.`tb_aluno` 
DROP COLUMN `id_curso`,
DROP INDEX `fk_curso_aluno_idx` ;

ALTER TABLE `onknowledge`.`tb_codigo_seguranca` 
DROP FOREIGN KEY `fk_codigo_seguranca2`;
ALTER TABLE `onknowledge`.`tb_codigo_seguranca` 
DROP COLUMN `id_curso`,
DROP INDEX `fk_codigo_seguranca2_idx` ;

ALTER TABLE `onknowledge`.`tb_turma` 
ADD COLUMN `id_curso` INT NOT NULL AFTER `dt_encerramento`,
ADD INDEX `fk_turma1_idx` (`id_curso` ASC);
ALTER TABLE `onknowledge`.`tb_turma` 
ADD CONSTRAINT `fk_turma1`
  FOREIGN KEY (`id_curso`)
  REFERENCES `onknowledge`.`tb_curso` (`id_curso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
