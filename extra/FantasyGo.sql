-- phpMyAdmin SQL Dump
-- version 4.4.15.9
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Creato il: Gen 29, 2018 alle 18:57
-- Versione del server: 5.6.35
-- Versione PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `FantasyGo`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `GIOCATORI`
--

CREATE TABLE IF NOT EXISTS `GIOCATORI` (
  `G_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_bin NOT NULL,
  `STATO` int(11) NOT NULL,
  `USERNAME` varchar(255) COLLATE utf8_bin NOT NULL,
  `ZDC_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dump dei dati per la tabella `GIOCATORI`
--

INSERT INTO `GIOCATORI` (`G_ID`, `PASSWORD`, `STATO`, `USERNAME`, `ZDC_ID`) VALUES
('G00001', 'nonlaso', 0, 'Gaet', 'ZDC001');

-- --------------------------------------------------------

--
-- Struttura della tabella `MOSTRI`
--

CREATE TABLE IF NOT EXISTS `MOSTRI` (
  `ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `CARATTERISTICHE` tinyblob,
  `EQUIPAGGIAMENTO` tinyblob,
  `RICOMPENSA` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `PERSONAGGI`
--

CREATE TABLE IF NOT EXISTS `PERSONAGGI` (
  `ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `CARATTERISTICHE` longblob,
  `EQUIPAGGIAMENTO` longblob,
  `BOTTINO` int(11) NOT NULL,
  `CLASSE` varchar(255) COLLATE utf8_bin NOT NULL,
  `INVENTARIO` longblob NOT NULL,
  `NOME` varchar(255) COLLATE utf8_bin NOT NULL,
  `ORO` int(11) NOT NULL,
  `PUNTI_ESP` int(11) NOT NULL,
  `RAZZA` varchar(255) COLLATE utf8_bin NOT NULL,
  `SESSO` varchar(255) COLLATE utf8_bin NOT NULL,
  `G_ID` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dump dei dati per la tabella `PERSONAGGI`
--

INSERT INTO `PERSONAGGI` (`ID`, `CARATTERISTICHE`, `EQUIPAGGIAMENTO`, `BOTTINO`, `CLASSE`, `INVENTARIO`, `NOME`, `ORO`, `PUNTI_ESP`, `RAZZA`, `SESSO`, `G_ID`) VALUES
('P0001', 0xaced000573720016456e746974792e4361726174746572697374696368655a9e1a0049694c7a02000c49000d6174746163636f46697369636f49000d6174746163636f4d616769636f49000e6361726963614162696c6974c3a04900116361726963614d61784162696c6974c3a049000c64696665736146697369636149000c6469666573614d61676963614900076c6976656c6c6f49000b70756e746946657269746149000e70756e74694665726974614d617849001176656c6f636974c3a0644174746163636f4c00086162696c6974c3a07400124c6a6176612f6c616e672f537472696e673b4c000b7469706f4174744261736571007e00017870000000320000000a000000000000000d00000015000000140000000200002710000027100000001174000f4174746163636f506f6465726f736f740003466973, 0xaced000573720016456e746974792e457175697061676769616d656e746f0a07c0afe283c8a30200024c000441726d617400124c6a6176612f6c616e672f537472696e673b4c000841726d617475726171007e0001787074000457303031740003413031, 0, 'Tizio', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000077040000000078, 'Gaetano', 0, 0, 'Umano', 'M', 'G00001');

-- --------------------------------------------------------

--
-- Struttura della tabella `REL_MOSTRI_ZDC`
--

CREATE TABLE IF NOT EXISTS `REL_MOSTRI_ZDC` (
  `ZDC_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struttura della tabella `ZONEDICACCIA`
--

CREATE TABLE IF NOT EXISTS `ZONEDICACCIA` (
  `ZDC_ID` varchar(255) COLLATE utf8_bin NOT NULL,
  `CENTRO` longblob NOT NULL,
  `CONFINI` longblob NOT NULL,
  `NOME` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dump dei dati per la tabella `ZONEDICACCIA`
--

INSERT INTO `ZONEDICACCIA` (`ZDC_ID`, `CENTRO`, `CONFINI`, `NOME`) VALUES
('ZDC001', 0xaced00057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00017870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078704045337a20578e5c7371007e0003402a7dd1010ef3b9, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000077704000000077372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00037870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040453786e70f6e327371007e0005402a720a816341ef7371007e00027371007e000540453665a64d06437371007e0005402a5f0e0073c3467371007e00027371007e000540453280e3c02e597371007e0005402a688bff7d23417371007e00027371007e000540452e65c1fbb8737371007e0005402a91b4adf216327371007e00027371007e000540453310acf66db07371007e0005402a9d87ab5a8c3e7371007e00027371007e00054045340fa73cf04b7371007e0005402a9154003254e77371007e00027371007e000540453786e70f6e327371007e0005402a720a816341ef78, 'Area 1'),
('ZDC002', 0xaced00057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00017870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870404534392fcc76667371007e0003402ac6970062258f, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000077704000000077372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00037870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040453310acf66db07371007e0005402a9d87ab5a8c3e7371007e00027371007e00054045302a9a077da37371007e0005402aac0c019602357371007e00027371007e000540452f10045a01d47371007e0005402ae7d0005df3d17371007e00027371007e00054045355be50324787371007e0005402ae7d0005df3d17371007e00027371007e000540453b3f961804da7371007e0005402accca595505ec7371007e00027371007e00054045374e2881ff497371007e0005402ab3c8018bf13a7371007e00027371007e000540453310acf66db07371007e0005402a9d87ab5a8c3e78, 'Area 2'),
('ZDC003', 0xaced00057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00017870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040452b00c44b1e0c7371007e0003402a9c66ff129a36, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000087704000000087372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00037870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040452e65c1fbb8737371007e0005402a91b4adf216327371007e00027371007e000540452d895223b9437371007e0005402a955eff69d80b7371007e00027371007e000540452aad9ad85dfb7371007e0005402a8c68017119487371007e00027371007e0005404526e8ac2664187371007e0005402a982f009e8b717371007e00027371007e0005404527fb39c7a1eb7371007e0005402aa2bafe6e2f897371007e00027371007e000540452a28898c055c7371007e0005402aae8200f6a0057371007e00027371007e000540452d5fc130b7587371007e0005402aa5b7fedf6e907371007e00027371007e000540452e65c1fbb8737371007e0005402a91b4adf2163278, 'Area 3'),
('ZDC004', 0xaced00057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00017870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040452fe81d8b2b427371007e0003402a9f90fec064df, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000057704000000057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00037870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040452d5fc130b7587371007e0005402aa5b7fedf6e907371007e00027371007e00054045302a9a077da37371007e0005402aac0c019602357371007e00027371007e000540453310acf66db07371007e0005402a9d87ab5a8c3e7371007e00027371007e000540452e65c1fbb8737371007e0005402a91b4adf216327371007e00027371007e000540452d5fc130b7587371007e0005402aa5b7fedf6e9078, 'Area 4'),
('ZDC005', 0xaced00057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00017870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040452a6b129d91767371007e0003402abd190158c594, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000087704000000087372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00037870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040452d5fc130b7587371007e0005402aa5b7fedf6e907371007e00027371007e000540452a28898c055c7371007e0005402aae8200f6a0057371007e00027371007e00054045276579009cde7371007e0005402ab4a90115a9b57371007e00027371007e00054045271a9831ba8d7371007e0005402ace26011b88f37371007e00027371007e0005404529b418a0e0b67371007e0005402ad9ed0048fb1c7371007e00027371007e000540452c1b7ee1876f7371007e0005402ac60fff5184577371007e00027371007e000540452d8101f31f477371007e0005402ab3c8018bf13a7371007e00027371007e000540452d5fc130b7587371007e0005402aa5b7fedf6e9078, 'Area 5'),
('ZDC006', 0xaced00057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00017870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040452ea3f613edf47371007e0003402ab44eff41941f, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000067704000000067372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00037870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078704045302a9a077da37371007e0005402aac0c019602357371007e00027371007e000540452d5fc130b7587371007e0005402aa5b7fedf6e907371007e00027371007e000540452d8101f31f477371007e0005402ab3c8018bf13a7371007e00027371007e000540452c983799ca417371007e0005402abf07ffa8c22c7371007e00027371007e000540452fb63fc28daf7371007e0005402ac589019be1727371007e00027371007e00054045302a9a077da37371007e0005402aac0c0196023578, 'Area 6'),
('ZDC007', 0xaced00057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00017870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040452d68116151547371007e0003402ad123018cc7fa, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000067704000000067372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00037870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787040452fb63fc28daf7371007e0005402ac589019be1727371007e00027371007e000540452c983799ca417371007e0005402abf07ffa8c22c7371007e00027371007e000540452c1b7ee1876f7371007e0005402ac60fff5184577371007e00027371007e0005404529b418a0e0b67371007e0005402ad9ed0048fb1c7371007e00027371007e000540452f10045a01d47371007e0005402ae7d0005df3d17371007e00027371007e000540452fb63fc28daf7371007e0005402ac589019be17278, 'Area 7'),
('ZDC008', 0xaced00057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00017870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870404525c570b7f9007371007e0003402ad9c0010c6f7a, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000b77040000000b7372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00037870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078704045276579009cde7371007e0005402ab4a90115a9b57371007e00027371007e000540452295c5cf828a7371007e0005402ace53005814947371007e00027371007e000540451e84e9df2c4b7371007e0005402aded8ff0a36bb7371007e00027371007e00054045219c03d725157371007e0005402aed43002fd0a87371007e00027371007e00054045253f77e513eb7371007e0005402ae4339dbc3e097371007e00027371007e00054045276dcadeb6037371007e0005402aef31fe7fcd407371007e00027371007e00054045281c806946eb7371007e0005402afa18017e85417371007e00027371007e00054045298230c748297371007e0005402af7a1fec2e91e7371007e00027371007e0005404529b418a0e0b67371007e0005402ad9ed0048fb1c7371007e00027371007e00054045271a9831ba8d7371007e0005402ace26011b88f37371007e00027371007e00054045276579009cde7371007e0005402ab4a90115a9b578, 'Area 8'),
('ZDC009', 0xaced00057372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00017870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870404539d5b82dd9577371007e0003402afe7cff2f1fa8, 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000157704000000157372000f5574696c2e436f6f7264696e6174615a9e1a0049694c7a0200024c000a4c617469747564696e657400124c6a6176612f6c616e672f446f75626c653b4c000b4c6f6e6769747564696e6571007e00037870737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870404529b418a0e0b67371007e0005402ad9ed0048fb1c7371007e00027371007e00054045298230c748297371007e0005402af7a1fec2e91e7371007e00027371007e00054045281c806946eb7371007e0005402afa18017e85417371007e00027371007e000540452a62c19637e57371007e0005402b125a0026966d7371007e00027371007e00054045309ef375ae027371007e0005402b2a15011904b47371007e00027371007e0005404533cd324d5a267371007e0005402b3fb40123ec6e7371007e00027371007e0005404533f6bbb39fd57371007e0005402b4a6d018b1a7b7371007e00027371007e000540453857d904e9747371007e0005402b54f8ff5abe927371007e00027371007e000540453b74b68b41e87371007e0005402b409500ada4e97371007e00027371007e000540453b008637bd067371007e0005402b1bd7ff2ff6687371007e00027371007e000540453ed3a2a9e3947371007e0005402b022dffed8b897371007e00027371007e0005404543b7e330bc607371007e0005402ae5b3ff766d457371007e00027371007e0005404544b09635610b7371007e0005402ad23100530c177371007e00027371007e0005404542ae9325e2127371007e0005402ac17dff095ffb7371007e00027371007e0005404540068ce1fb327371007e0005402aaa49ff2792ec7371007e00027371007e000540453cc0a64ddd037371007e0005402aa71fff79c8437371007e00027371007e000540453b3a9e617f777371007e0005402ac399fff0e6887371007e00027371007e000540453b863f49c1eb7371007e0005402acb884761be617371007e00027371007e00054045355be50324787371007e0005402ae7d0005df3d17371007e00027371007e000540452f10045a01d47371007e0005402ae7d0005df3d17371007e00027371007e0005404529b418a0e0b67371007e0005402ad9ed0048fb1c78, 'Area 9');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `GIOCATORI`
--
ALTER TABLE `GIOCATORI`
  ADD PRIMARY KEY (`G_ID`),
  ADD KEY `ZDC_ID_FK` (`ZDC_ID`);

--
-- Indici per le tabelle `MOSTRI`
--
ALTER TABLE `MOSTRI`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `PERSONAGGI`
--
ALTER TABLE `PERSONAGGI`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FKhv63grt0yh6aum345s2u8tr6p` (`G_ID`);

--
-- Indici per le tabelle `REL_MOSTRI_ZDC`
--
ALTER TABLE `REL_MOSTRI_ZDC`
  ADD PRIMARY KEY (`ZDC_ID`,`ID`),
  ADD KEY `FK47g4lurxw0tlq6jbo3l8x2ffa` (`ID`);

--
-- Indici per le tabelle `ZONEDICACCIA`
--
ALTER TABLE `ZONEDICACCIA`
  ADD PRIMARY KEY (`ZDC_ID`),
  ADD UNIQUE KEY `UK_ay119s208m68d4uwgwgyn2u4` (`NOME`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `GIOCATORI`
--
ALTER TABLE `GIOCATORI`
  ADD CONSTRAINT `ZDC_ID_FK` FOREIGN KEY (`ZDC_ID`) REFERENCES `ZONEDICACCIA` (`ZDC_ID`);

--
-- Limiti per la tabella `PERSONAGGI`
--
ALTER TABLE `PERSONAGGI`
  ADD CONSTRAINT `FKhv63grt0yh6aum345s2u8tr6p` FOREIGN KEY (`G_ID`) REFERENCES `GIOCATORI` (`G_ID`);

--
-- Limiti per la tabella `REL_MOSTRI_ZDC`
--
ALTER TABLE `REL_MOSTRI_ZDC`
  ADD CONSTRAINT `FK47g4lurxw0tlq6jbo3l8x2ffa` FOREIGN KEY (`ID`) REFERENCES `MOSTRI` (`ID`),
  ADD CONSTRAINT `FKheg0kykohoi771sm3qau5uwon` FOREIGN KEY (`ZDC_ID`) REFERENCES `ZONEDICACCIA` (`ZDC_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
