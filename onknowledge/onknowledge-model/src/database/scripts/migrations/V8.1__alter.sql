ALTER TABLE `onknowledge`.`tb_usuario` 
ADD UNIQUE INDEX `email_UNIQUE` (`email` ASC),
DROP INDEX `idx_email` ;
