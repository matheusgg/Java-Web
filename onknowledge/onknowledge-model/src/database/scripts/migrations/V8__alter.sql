ALTER TABLE `onknowledge`.`tb_usuario` 
DROP INDEX `email_UNIQUE` ;

ALTER TABLE `onknowledge`.`tb_usuario` 
ADD INDEX `idx_email` (`email` ASC);
