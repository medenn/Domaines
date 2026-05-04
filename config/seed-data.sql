-- Seed SQL idempotent pour PostgreSQL
-- Rejouable sans doublons grace a ON CONFLICT DO NOTHING

BEGIN;

INSERT INTO public.actualites_categories (id, nomarabe, nomfrancais) VALUES
(1, 'لقاءات', 'Rencontres'),
(2, 'أخبار', 'Actualités')
ON CONFLICT DO NOTHING;

INSERT INTO public.bibliothequecategorie (id, nomarabe, nomfrancais) VALUES
(1, 'لقاءات', 'Rencontres'),
(4, 'صور', 'PHOTOS'),
(3, 'الفيديوهات', 'VIDÉOS')
ON CONFLICT DO NOTHING;

INSERT INTO public.informations (id, adresse, adressear, email, horaires, horairesar, logo, nomsite, nomsitear, telephone) VALUES
(1, 'nouakchott rue 456tevragh zeina', NULL, 'contact@mre.gov.mr', 'الأثنين - الجمعة : 8:00 صباحاً - 4:00 مساءً', NULL, NULL, 'وزارة العقارات وأملاك الدولة والإصلاح العقاري', NULL, '+222 1234 5678')
ON CONFLICT (id) DO UPDATE SET
adresse = EXCLUDED.adresse,
adressear = EXCLUDED.adressear,
email = EXCLUDED.email,
horaires = EXCLUDED.horaires,
horairesar = EXCLUDED.horairesar,
logo = EXCLUDED.logo,
nomsite = EXCLUDED.nomsite,
nomsitear = EXCLUDED.nomsitear,
telephone = EXCLUDED.telephone;


INSERT INTO public.permissions (id, methode, name, role, url_pattern) VALUES
(16, '*', 'PERM_USERS', 'ADMIN', '/api/users/**'),
(18, '*', 'PERMISSION', 'ADMIN', '/api/permissions/**'),
(19, '*', 'ROLES', 'ADMIN', '/api/roles/**'),
(21, '*', 'PERM_CONTENU_ASSOCIER', 'ADMIN', '/api/ministrecontenuassocier/**'),
(22, '*', 'PERM_LEMINISTRE', 'ADMIN', '/api/leministre/**'),
(23, '*', 'PERM_TACHESMINISTERE', 'ADMIN', '/api/tachesministere/**'),
(24, '*', 'PERM_LISTTACHE', 'ADMIN', '/api/listetaches/**'),
(25, '*', 'PERM_STRCTUREAADMIN', 'ADMIN', '/api/structureadministrative/**'),
(26, '*', 'PERM_CATGACTU', 'ADMIN', '/api/actualitescategories/**'),
(27, '*', 'PERM_ACTUALITE', 'ADMIN', '/api/actualites/**'),
(28, '*', 'PERM_ANNONCE', 'ADMIN', '/api/annonces/**'),
(29, '*', 'PERM_LOIS', 'ADMIN', '/api/lois/**'),
(30, '*', 'PERM_MESSAGES', 'ADMIN', '/api/messages/**'),
(31, '*', 'PERM_COMPTESSOCIAUX', 'ADMIN', '/api/comptessociaux/**'),
(32, '*', 'PERM_INFORMATIONS', 'ADMIN', '/api/informations/**'),
(17, 'GET', 'PERM_MINISTERE_USER_ALL', 'USER', '/api/aproposministere/**'),
(33, '*', 'PERM_SLIDES', 'ADMIN', '/api/slides/**'),
(34, '*', 'PERM_MENU', 'ADMIN', '/api/menu/**'),
(35, '*', 'PERM_BIBLIOCATG', 'ADMIN', '/api/bibliothequecategorie/**'),
(36, '*', 'PERM_BIBLIO', 'ADMIN', '/api/bibliotheque/**'),
(20, '*', 'MINISTRE', 'ADMIN', '/api/aproposministere/**'),
(37, '*', 'PERM_SOUSETAB', 'ADMIN', '/api/sousetablissements/**'),
(38, '*', 'PERM_CABINET', 'ADMIN', '/api/cabinetministre/**'),
(39, '*', 'PERM_VIDEOS', 'ADMIN', '/api/videos/**'),
(40, '*', 'PERM_PROJETS', 'ADMIN', '/api/projets/**'),
(41, '*', 'PERM_PROCEDURESERVICES', 'ADMIN', '/api/procedures-services/**'),
(42, '*', 'PERM_MOTMINISTRE', 'ADMIN', '/api/motministre/**')
ON CONFLICT (id) DO UPDATE SET
methode = EXCLUDED.methode,
name = EXCLUDED.name,
role = EXCLUDED.role,
url_pattern = EXCLUDED.url_pattern;



INSERT INTO public.roles (id, name) VALUES
(1, 'ADMIN'),
(3, 'USER')
ON CONFLICT DO NOTHING;


INSERT INTO public.users (id, password, username) VALUES
(7, '$2a$10$rtQFR5j06dEp9nvbh6nrrOOOTnCz5gU0kIORB4JDrEpOc8tjF7C6K', 'admin'),
(8, '$2a$10$WNH.PGZXXiV4NROyfLxt5Od0dfnQEaiuE2LSopkGQ5S8uOK6EGVve', 'user'),
(9, '$2a$10$HRtFI3hpdkvDuU4RHba1te9RBf5hnXioXI0sarOiV6GCwSbMgcAJm', 'poster'),
(10, '$2a$10$fbWPj81LY7n2Xnjd6r9Be.B0ykhjr8T9wnqTWDPTp.3lrbUYnr88O', 'author')
ON CONFLICT DO NOTHING;

INSERT INTO public.users_roles (user_id, role_id) VALUES
(7, 1),
(8, 3),
(9, 1),
(10, 1)
ON CONFLICT DO NOTHING;

-- Recalage des sequences pour eviter les collisions d'ID
SELECT setval('public.actualites_categories_id_seq', COALESCE((SELECT MAX(id) FROM public.actualites_categories), 1), true);
SELECT setval('public.bibliothequecategorie_id_seq', COALESCE((SELECT MAX(id) FROM public.bibliothequecategorie), 1), true);
SELECT setval('public.informations_id_seq', COALESCE((SELECT MAX(id) FROM public.informations), 1), true);
SELECT setval('public.permissions_id_seq', COALESCE((SELECT MAX(id) FROM public.permissions), 1), true);
SELECT setval('public.roles_id_seq', COALESCE((SELECT MAX(id) FROM public.roles), 1), true);
SELECT setval('public.users_id_seq', COALESCE((SELECT MAX(id) FROM public.users), 1), true);

COMMIT;
