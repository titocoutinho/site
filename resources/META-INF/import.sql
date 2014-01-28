/*
Navicat MySQL Data Transfer

Source Server         : mysql local
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : site_amut

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2013-09-06 01:45:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `artigo`
-- ----------------------------
DROP TABLE IF EXISTS `artigo`;
CREATE TABLE `artigo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime NOT NULL,
  `deletado` varchar(255) DEFAULT NULL,
  `data_atuaizacao` datetime DEFAULT NULL,
  `versao` int(11) DEFAULT NULL,
  `corpo` varchar(5000) DEFAULT NULL,
  `nota` varchar(255) DEFAULT NULL,
  `titulo` varchar(1000) DEFAULT NULL,
  `categoria_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAC02DDCE82F7EB6` (`categoria_id`),
  CONSTRAINT `FKAC02DDCE82F7EB6` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of artigo
-- ----------------------------
INSERT INTO `artigo` VALUES ('2', '2013-09-06 00:59:22', 'NAO', null, '0', '<span style=\"color: rgb(68, 68, 68); font-family: \'Lucida Grande\', Verdana, Arial, sans-serif; font-size: 12px; line-height: 21.59375px; text-align: justify; background-color: rgb(255, 255, 255);\">Observe onde foram inseridas as annotations que definem detalhes da coluna. A posiÃ§Ã£o onde essas annotations sÃ£o colocadas, define o tipo de acesso que o Hibernate usarÃ¡ para trocar as informaÃ§Ãµes entre o banco de dados e a sua entidade. Observe abaixo, como ficaria se, ao invÃ©s de acessos atravÃ©s dos gets e sets, vocÃª quisesse que o acesso fosse direto aos atributos:</span>', null, 'teste', '2');

-- ----------------------------
-- Table structure for `caracteristica`
-- ----------------------------
DROP TABLE IF EXISTS `caracteristica`;
CREATE TABLE `caracteristica` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime NOT NULL,
  `deletado` varchar(255) DEFAULT NULL,
  `data_atuaizacao` datetime DEFAULT NULL,
  `versao` int(11) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  `nomeCaracteristica` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of caracteristica
-- ----------------------------
INSERT INTO `caracteristica` VALUES ('1', '2013-09-03 20:33:53', 'NAO', '2013-09-05 23:00:41', '0', '', 'Dados Gerais');
INSERT INTO `caracteristica` VALUES ('2', '2013-09-03 20:35:00', 'NAO', '2013-09-05 23:00:34', '0', '', 'Demografia');
INSERT INTO `caracteristica` VALUES ('3', '2013-09-03 20:35:04', 'NAO', '2013-09-03 20:38:17', '0', '', 'IDH');
INSERT INTO `caracteristica` VALUES ('4', '2013-09-03 20:35:15', 'NAO', null, '0', '', 'Eleições Municipais');
INSERT INTO `caracteristica` VALUES ('5', '2013-09-03 20:35:29', 'NAO', null, '0', '', 'Finanças Municipais');
INSERT INTO `caracteristica` VALUES ('6', '2013-09-03 20:35:37', 'NAO', null, '0', '', 'Educação');
INSERT INTO `caracteristica` VALUES ('7', '2013-09-03 20:35:47', 'NAO', '2013-09-05 23:00:19', '0', '', 'Notícias');
INSERT INTO `caracteristica` VALUES ('8', '2013-09-03 20:36:00', 'NAO', null, '0', '', 'História');
INSERT INTO `caracteristica` VALUES ('9', '2013-09-03 20:36:08', 'NAO', null, '0', '', 'Festas e Eventos');
INSERT INTO `caracteristica` VALUES ('10', '2013-09-03 20:36:19', 'NAO', null, '0', '', 'Dados Políticos');
INSERT INTO `caracteristica` VALUES ('11', '2013-09-03 20:36:30', 'NAO', null, '0', '', 'Hinos e Simbolos');
INSERT INTO `caracteristica` VALUES ('12', '2013-09-03 20:36:35', 'NAO', null, '0', '', 'Turismo');
INSERT INTO `caracteristica` VALUES ('13', '2013-09-03 20:36:41', 'NAO', null, '0', '', 'Contas Públicas');
INSERT INTO `caracteristica` VALUES ('14', '2013-09-03 20:36:50', 'NAO', null, '0', '', 'Transferências');

-- ----------------------------
-- Table structure for `categoria`
-- ----------------------------
DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime NOT NULL,
  `deletado` varchar(255) DEFAULT NULL,
  `data_atuaizacao` datetime DEFAULT NULL,
  `versao` int(11) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of categoria
-- ----------------------------
INSERT INTO `categoria` VALUES ('1', '2013-09-03 19:32:52', 'NAO', '2013-09-03 19:36:40', '0', '', 'institucional');
INSERT INTO `categoria` VALUES ('2', '2013-09-03 19:37:07', 'NAO', null, '0', '', 'Ã¡rea tÃ©cnica');
INSERT INTO `categoria` VALUES ('3', '2013-09-03 19:37:18', 'NAO', null, '0', '', 'diretoria');
INSERT INTO `categoria` VALUES ('4', '2013-09-03 19:37:26', 'NAO', null, '0', '', 'eventos');
INSERT INTO `categoria` VALUES ('5', '2013-09-03 19:37:37', 'NAO', null, '0', '', 'galeria de fotos');
INSERT INTO `categoria` VALUES ('6', '2013-09-03 19:37:46', 'NAO', null, '0', '', 'fale conosco');
INSERT INTO `categoria` VALUES ('7', '2013-09-03 19:38:12', 'NAO', null, '0', '', 'municipios');
INSERT INTO `categoria` VALUES ('8', '2013-09-03 21:28:35', 'NAO', null, '0', '', 'tste');

-- ----------------------------
-- Table structure for `comentario`
-- ----------------------------
DROP TABLE IF EXISTS `comentario`;
CREATE TABLE `comentario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime NOT NULL,
  `deletado` varchar(255) DEFAULT NULL,
  `data_atuaizacao` datetime DEFAULT NULL,
  `versao` int(11) DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `artigo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK14DFC401767FA69E` (`artigo_id`),
  CONSTRAINT `FK14DFC401767FA69E` FOREIGN KEY (`artigo_id`) REFERENCES `artigo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comentario
-- ----------------------------

-- ----------------------------
-- Table structure for `dado`
-- ----------------------------
DROP TABLE IF EXISTS `dado`;
CREATE TABLE `dado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime NOT NULL,
  `deletado` varchar(255) DEFAULT NULL,
  `data_atuaizacao` datetime DEFAULT NULL,
  `versao` int(11) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `caracteristica_id` bigint(20) DEFAULT NULL,
  `municipio_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2EEDC83909C040` (`caracteristica_id`),
  KEY `FK2EEDC8641F9B54` (`municipio_id`),
  CONSTRAINT `FK2EEDC83909C040` FOREIGN KEY (`caracteristica_id`) REFERENCES `caracteristica` (`id`),
  CONSTRAINT `FK2EEDC8641F9B54` FOREIGN KEY (`municipio_id`) REFERENCES `municipio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dado
-- ----------------------------

-- ----------------------------
-- Table structure for `municipio`
-- ----------------------------
DROP TABLE IF EXISTS `municipio`;
CREATE TABLE `municipio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime NOT NULL,
  `deletado` varchar(255) DEFAULT NULL,
  `data_atuaizacao` datetime DEFAULT NULL,
  `versao` int(11) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sigla` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of municipio
-- ----------------------------
INSERT INTO `municipio` VALUES ('1', '2013-09-03 20:23:59', 'NAO', null, '0', 'Almeirim', '');
INSERT INTO `municipio` VALUES ('2', '2013-09-03 20:24:59', 'NAO', null, '0', 'Altamira', '');
INSERT INTO `municipio` VALUES ('3', '2013-09-03 20:25:04', 'NAO', null, '0', 'Anapu', '');
INSERT INTO `municipio` VALUES ('4', '2013-09-03 20:25:09', 'NAO', null, '0', 'Aveiro', '');
INSERT INTO `municipio` VALUES ('5', '2013-09-03 20:25:26', 'NAO', null, '0', 'Belterra', '');
INSERT INTO `municipio` VALUES ('6', '2013-09-03 20:25:32', 'NAO', null, '0', 'Brasil Novo', '');
INSERT INTO `municipio` VALUES ('7', '2013-09-03 20:25:37', 'NAO', null, '0', 'Itaituba', '');
INSERT INTO `municipio` VALUES ('8', '2013-09-03 20:25:41', 'NAO', null, '0', 'Jacareacanga', '');
INSERT INTO `municipio` VALUES ('9', '2013-09-03 20:25:46', 'NAO', null, '0', 'Medicilândia', '');
INSERT INTO `municipio` VALUES ('10', '2013-09-03 20:25:52', 'NAO', null, '0', 'Novo Progresso', '');
INSERT INTO `municipio` VALUES ('11', '2013-09-03 20:25:59', 'NAO', null, '0', 'Novo Repartimento', '');
INSERT INTO `municipio` VALUES ('12', '2013-09-03 20:26:04', 'NAO', null, '0', 'Pacajá', '');
INSERT INTO `municipio` VALUES ('13', '2013-09-03 20:26:10', 'NAO', null, '0', 'Placas', '');
INSERT INTO `municipio` VALUES ('14', '2013-09-03 20:26:16', 'NAO', null, '0', 'Porto de Moz', '');
INSERT INTO `municipio` VALUES ('15', '2013-09-03 20:26:20', 'NAO', null, '0', 'Rurópolis', '');
INSERT INTO `municipio` VALUES ('16', '2013-09-03 20:26:24', 'NAO', null, '0', 'Santarém', '');
INSERT INTO `municipio` VALUES ('17', '2013-09-03 20:26:31', 'NAO', null, '0', 'Senador José Porfírio', '');
INSERT INTO `municipio` VALUES ('18', '2013-09-03 20:26:36', 'NAO', null, '0', 'Trairão', '');
INSERT INTO `municipio` VALUES ('19', '2013-09-03 20:26:41', 'NAO', null, '0', 'Uruarã', '');
INSERT INTO `municipio` VALUES ('20', '2013-09-03 20:26:47', 'NAO', '2013-09-04 15:42:45', '0', 'Vitória do Xingu', '');

-- ----------------------------
-- Table structure for `subcategoria`
-- ----------------------------
DROP TABLE IF EXISTS `subcategoria`;
CREATE TABLE `subcategoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime NOT NULL,
  `deletado` varchar(255) DEFAULT NULL,
  `data_atuaizacao` datetime DEFAULT NULL,
  `versao` int(11) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `categoria_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE4029EF382F7EB6` (`categoria_id`),
  CONSTRAINT `FKE4029EF382F7EB6` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subcategoria
-- ----------------------------
INSERT INTO `subcategoria` VALUES ('1', '2013-09-06 00:38:43', 'NAO', null, '0', '', 'teste', '3');

-- ----------------------------
-- Table structure for `usuario`
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime NOT NULL,
  `deletado` varchar(255) DEFAULT NULL,
  `data_atuaizacao` datetime DEFAULT NULL,
  `versao` int(11) DEFAULT NULL,
  `administrador` int(11) DEFAULT NULL,
  `ativo` int(11) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image` mediumblob,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usuario
-- ----------------------------
