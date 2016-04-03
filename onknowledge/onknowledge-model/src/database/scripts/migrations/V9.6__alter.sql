ALTER TABLE `onknowledge`.`tb_email_recebido` 
ADD COLUMN `fl_lixo` TINYINT(1) NULL AFTER `id_usuario_destinatario`;

ALTER TABLE `onknowledge`.`tb_email` 
DROP COLUMN `fl_lixo`;
