Esta funciona. Es la buena
------------ 

SELECT 
 	u.user_id idusuario, 
   u.username usuario, 
   r.name nombrerole  
FROM users_roles
INNER JOIN users u ON users_roles.user_id = u.user_id
INNER JOIN roles r ON users_roles.role_id = r.role_id
WHERE u.username = 'admin';

----------


SELECT users.user_id, users.username, roles.name FROM users 
  left join users_roles ON users.user_id = users_roles.user_id 
  left join roles 		ON roles.role_id = users_roles.role_id 
where users.username = 'admin';

SELECT 
   u.username usuario, 
   r.name nombrerole  
FROM users_roles
INNER JOIN users u ON users_roles.user_id = u.user_id
INNER JOIN roles r ON users_roles.role_id = r.role_id;


Funciona
--------
SELECT 
   u.username usuario, 
   u.user_id  isuario, 
   r.role_id idrole,
   r.name     nomrole
FROM users u
 LEFT OUTER JOIN users_roles ur ON u.user_id  = ur.user_id
 LEFT OUTER JOIN roles r        ON ur.role_id = r.role_id
where u.username = 'admin';


SELECT 
   u.username usuario, 
   u.user_id  isuario, 
   r.role_id idrole,
   r.name     nomrole
FROM users u
 LEFT JOIN users_roles ur ON u.user_id  = ur.user_id
 LEFT JOIN roles r        ON ur.role_id = r.role_id
where u.username = 'admin';

 -- Listade menus de un usuario elegido.
SELECT * from MENUS_USUARIO 
 INNER JOIN MENU ON MENUS_USUARIO.ID_MENU = MENU.ID_MENU
 INNER JOIN users ON MENUS_USUARIO.ID_USUARIO_FK = users.user_id
 INNER JOIN SUBMENU_NIVEL_1 ON MENUS_USUARIO.COD_NIVEL_2_FK = SUBMENU_NIVEL_1.ID_SUBMENU_NIVEL_1
  WHERE users.user_id = 7
    AND MENUS_USUARIO.ID_MENU > 0
     AND SUBMENU_NIVEL_1.ID_SUBMENU_NIVEL_1 = 0;