
INSERT INTO "ADDRESS" (ID, VERSION, STREET, CITY) VALUES
(61, 1, '123 Maple Street', 'Springfield'),
(62, 1, '456 Oak Avenue', 'Shelbyville'),
(63, 1, '789 Pine Road', 'Capital City'),
(64, 1, '101 Elm Street', 'Ogdenville'),
(65, 1, '202 Birch Boulevard', 'North Haverbrook'),
(66, 1, '303 Cedar Lane', 'Brockway'),
(67, 1, '404 Walnut Drive', 'Springfield'),
(68, 1, '505 Chestnut Street', 'Shelbyville'),
(69, 1, '606 Fir Avenue', 'Capital City'),
(70, 1, '707 Spruce Road', 'Ogdenville');

INSERT INTO "STATUS" (ID, VERSION, NAME) VALUES
(1, 1, 'Imported lead'),
(2, 1, 'Not contacted'),
(3, 1, 'Contacted'),
(4, 1, 'Customer'),
(5, 1, 'Closed (lost)');
INSERT INTO "COMPANY" (ID, VERSION, NAME) VALUES
(6, 1, 'Phillips Van Heusen Corp.'),
(7, 1, 'Avaya Inc.'),
(8, 1, 'Laboratory Corporation of America Holdings'),
(9, 1, 'AutoZone, Inc.'),
(10, 1, 'Linens ''n Things Inc.');   
INSERT INTO "CONTACT" (ID, VERSION, EMAIL, FIRST_NAME, LAST_NAME, COMPANY_ID, STATUS_ID, ADDRESS_ID) VALUES
(11, 1, 'eula.lane@jigrormo.ye', 'Eula', 'Lane', 8, 1, 61),
(12, 1, 'barry.rodriquez@zun.mm', 'Barry', 'Rodriquez', 7, 5, 62),
(13, 1, 'eugenia.selvi@capfad.vn', 'Eugenia', 'Selvi', 6, 3, 63),
(14, 1, 'alejandro.miles@dec.bn', 'Alejandro', 'Miles', 10, 3, 64),
(15, 1, 'cora.tesi@bivo.yt', 'Cora', 'Tesi', 6, 4, 65),
(16, 1, 'marguerite.ishii@judbilo.gn', 'Marguerite', 'Ishii', 10, 2, 66),
(17, 1, 'mildred.jacobs@joraf.wf', 'Mildred', 'Jacobs', 8, 1, 67),
(18, 1, 'gene.goodman@kem.tl', 'Gene', 'Goodman', 8, 5, 68),
(19, 1, 'lettie.bennett@odeter.bb', 'Lettie', 'Bennett', 6, 1, 69),
(20, 1, 'mabel.leach@lisohuje.vi', 'Mabel', 'Leach', 10, 2, 70),
(21, 1, 'jordan.miccinesi@duod.gy', 'Jordan', 'Miccinesi', 8, 3, 61),
(22, 1, 'marie.parkes@nowufpus.ph', 'Marie', 'Parkes', 7, 1, 62),
(23, 1, 'rose.gray@kagu.hr', 'Rose', 'Gray', 9, 4, 63),
(24, 1, 'garrett.stokes@fef.bg', 'Garrett', 'Stokes', 9, 3, 64),
(25, 1, 'barbara.matthieu@derwogi.jm', 'Barbara', 'Matthieu', 7, 5, 65),
(26, 1, 'jean.rhodes@wehovuce.gu', 'Jean', 'Rhodes', 7, 3, 66),
(27, 1, 'jack.romoli@zamum.bw', 'Jack', 'Romoli', 6, 4, 67),
(28, 1, 'pearl.holden@dunebuh.cr', 'Pearl', 'Holden', 8, 1, 68),
(29, 1, 'belle.montero@repiwid.si', 'Belle', 'Montero', 9, 5, 69),
(30, 1, 'olive.molina@razuppa.ga', 'Olive', 'Molina', 6, 2, 70),
(31, 1, 'minerva.todd@kulmenim.ad', 'Minerva', 'Todd', 9, 3, 61),
(32, 1, 'bobby.pearson@ib.kg', 'Bobby', 'Pearson', 9, 1, 62),
(33, 1, 'larry.ciappi@ba.lk', 'Larry', 'Ciappi', 10, 2, 63),
(34, 1, 'ronnie.salucci@tohhij.lv', 'Ronnie', 'Salucci', 9, 1, 64),
(35, 1, 'walter.grossi@tuvo.sa', 'Walter', 'Grossi', 9, 1, 65);

INSERT INTO "CONTACT" (ID, VERSION, EMAIL, FIRST_NAME, LAST_NAME, COMPANY_ID, STATUS_ID, ADDRESS_ID) VALUES
(36, 1, 'frances.koopmans@foga.tw', 'Frances', 'Koopmans', 7, 5, 66),
(37, 1, 'frances.fujimoto@uswuzzub.jp', 'Frances', 'Fujimoto', 6, 5, 67),
(38, 1, 'olivia.vidal@hivwerip.vc', 'Olivia', 'Vidal', 9, 2, 68),
(39, 1, 'edna.henry@gugusu.rw', 'Edna', 'Henry', 8, 4, 69),
(40, 1, 'lydia.brun@zedekak.md', 'Lydia', 'Brun', 7, 3, 70),
(41, 1, 'jay.blake@ral.mk', 'Jay', 'Blake', 10, 4, 61),
(42, 1, 'isabel.serafini@turuhu.bh', 'Isabel', 'Serafini', 10, 1, 62),
(43, 1, 'rebecca.carter@omjo.et', 'Rebecca', 'Carter', 8, 4, 63),
(44, 1, 'maurice.fabbrini@rig.bh', 'Maurice', 'Fabbrini', 9, 3, 64),
(45, 1, 'ollie.turnbull@sicewap.org', 'Ollie', 'Turnbull', 6, 1, 65),
(46, 1, 'jerry.hopkins@fo.mh', 'Jerry', 'Hopkins', 9, 5, 66),
(47, 1, 'nora.lyons@gegijap.na', 'Nora', 'Lyons', 10, 1, 67),
(48, 1, 'anne.weis@kuvesa.pe', 'Anne', 'Weis', 7, 4, 68),
(49, 1, 'louise.gauthier@lapahu.mt', 'Louise', 'Gauthier', 6, 2, 69),
(50, 1, 'lloyd.fani@zev.ru', 'Lloyd', 'Fani', 8, 1, 70),
(51, 1, 'maud.dunn@nabeaga.ni', 'Maud', 'Dunn', 6, 1, 61),
(52, 1, 'henry.gigli@kaot.ps', 'Henry', 'Gigli', 6, 5, 62),
(53, 1, 'virgie.werner@tawuctuj.cf', 'Virgie', 'Werner', 10, 4, 63),
(54, 1, 'gregory.cozzi@eh.ru', 'Gregory', 'Cozzi', 8, 2, 64),
(55, 1, 'lucinda.gil@fajjusuz.kr', 'Lucinda', 'Gil', 7, 5, 65),
(56, 1, 'gertrude.verbeek@pave.cc', 'Gertrude', 'Verbeek', 6, 5, 66),
(57, 1, 'mattie.graham@ispaviw.gt', 'Mattie', 'Graham', 7, 2, 67),
(58, 1, 'bryan.shaw@ha.ee', 'Bryan', 'Shaw', 9, 1, 68),
(59, 1, 'essie.adams@iliat.cw', 'Essie', 'Adams', 8, 5, 69),
(60, 1, 'gary.osborne@do.ga', 'Gary', 'Osborne', 7, 5, 70);
INSERT INTO "PROPERTY" (ID, VERSION, NAME, ADDRESS_ID) VALUES
(71, 1, 'Haus 1',61),
(72, 1, 'Wohnung 1',62),
(73, 1, 'Tiefgarage',63);

INSERT INTO "PROPERTYOBJECT" (ID, VERSION, NAME, PROPERTY_ID) VALUES
(81, 1, '3.5 Zimmer Wohnung',71),
(82, 1, '2.5 Zimmer Wohnung',72),
(83, 1, '4.5 Zimmer Wohnung',73);

